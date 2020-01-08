package thuis.dullaert.johan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import thuis.dullaert.johan.entities.Verbruik;
import thuis.dullaert.johan.exceptions.DAOException;
import thuis.dullaert.johan.services.VerbruikService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/verbruik")
public class HomeController {

	/*
	 * private static final Logger logger = LoggerFactory
	 * .getLogger(HomeController.class);
	 */

	/*
	 * Simply selects the home view to render by returning its name.
	 */
	/*
	 * @RequestMapping(method = RequestMethod.GET) public void home(Locale
	 * locale, Model model) { logger.info("Welcome home! the client locale is "+
	 * locale.toString());
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate );
	 * 
	 * //return "home"; }
	 */
	
	private final VerbruikService verbruikService;
	
	@Autowired
	public HomeController(VerbruikService verbruikService) {
		this.verbruikService = verbruikService;
	}

	@RequestMapping(value = "overzicht")
	public void verbruikOverzicht(Model model, @RequestParam String wijze,
			@RequestParam Integer page) {
		final int recordsPerPage = 15;
		model.addAttribute("wijze", wijze);
		List<Verbruik> verbruik = null;
		List<Verbruik> verbruikAll = null;
		int noOfRecords = 0;
		try {
			verbruikAll = verbruikService.readAll();
			noOfRecords = verbruikAll.size();
			verbruik = verbruikAll.subList(
					(((((page - 1) * recordsPerPage) < 0) ? 0
							: ((page - 1) * recordsPerPage))),
					(((page * recordsPerPage) > noOfRecords) ? noOfRecords
							: (page * recordsPerPage)));
			// verbruik = verbruikService.readPages(page, recordsPerPage,
			// noOfRecords);
		} catch (DAOException e) {
			model.addAttribute("fouten", e.getMessage());
		}		
		model.addAttribute("verbruik", verbruik);
		model.addAttribute("totaalAantalTankbeurten", noOfRecords);
		int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		model.addAttribute("noOfPages", String.valueOf(noOfPages));
		model.addAttribute("currentPage", page);

		// **** calculating total aantal liter , aantal km and (liter) prijs ****
		double totaalAantalLiter = 0;
		double totaalAantalKm = 0;
		BigDecimal prijs = new BigDecimal(0);
		for (Verbruik verbruikDetail : verbruikAll) {
			prijs = prijs.add(verbruikDetail.getTotaal());
			totaalAantalLiter += verbruikDetail.getAantalLiter();
			totaalAantalKm += verbruikDetail.getAantalKilometer();
		}
		model.addAttribute("totaalPrijs", prijs);
		model.addAttribute("totaalAantalKm", totaalAantalKm);
		if (totaalAantalKm != 0) {
			model.addAttribute("gemiddeldAantalLiterPer100km",
					((totaalAantalLiter / totaalAantalKm) * 100));
			model.addAttribute("gemiddeldePrijsPerKm", (prijs.divide(
					new BigDecimal(totaalAantalKm), RoundingMode.HALF_DOWN)));
		} else {
			model.addAttribute("gemiddeldAantalLiterPer100km", 0);
			model.addAttribute("gemiddeldePrijsPerKm", 0);
		}
	}

	@RequestMapping(value = "invoer", method = RequestMethod.GET)
	public void verbruikInvoerGet(Model model) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = Calendar.getInstance().getTime();		
		model.addAttribute("vandaag", sdf.format(date));
	}
	
	@RequestMapping(value = "invoer", method = RequestMethod.POST)
	public String verbruikInvoerPost(Model model,
			@RequestParam("aantalKm") double aantalKm,
			@RequestParam("aantalLiter") double aantalLiter,
			@RequestParam("literPrijs") BigDecimal literPrijs,
			@RequestParam("datum") String datum) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd");
			Date date = null;
			try {
				date = sdf.parse(datum);
			} catch (ParseException pe) {
				model.addAttribute("datumfout", pe.getMessage());
				return "verbruik/invoer";
			}
			Verbruik verbruik;
			try {
				verbruik = new Verbruik(aantalKm, aantalLiter, literPrijs, date);
				verbruikService.create(verbruik);
			} catch (IllegalArgumentException iae) {
				model.addAttribute("verbruikfouten", iae.getMessage());
				return "verbruik/invoer";
			}
		} catch (DAOException daoe) {
			model.addAttribute("fouten", daoe.getMessage());
			return "verbruik/invoer";
		}
		return "redirect:/";
	}
}
