package thuis.dullaert.johan.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

public class Verbruik implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private double aantalKilometer;
	private double aantalLiter;
	private BigDecimal literPrijs;
	private Date datum;

	// constructor voor hibernate
	protected Verbruik() {
	}

	public Verbruik(double aantalKilometer, double aantalLiter,
			BigDecimal literPrijs, Date datum) {
		setAantalKilometer(aantalKilometer);
		setAantalLiter(aantalLiter);
		setLiterPrijs(literPrijs);
		setDatum(datum);
	}

	public double getAantalLiterPerKilometer() {
		return (aantalLiter / aantalKilometer) * 100;
	}

	public BigDecimal getTotaal() {
		return literPrijs.multiply(new BigDecimal(aantalLiter));
	}

	public BigDecimal getPrijsPerKilometer() {
		return (getTotaal()).divide(new BigDecimal(aantalKilometer),
				RoundingMode.HALF_DOWN);
	}

	public double getAantalKilometer() {
		return aantalKilometer;
	}

	public void setAantalKilometer(double aantalKilometer) {
		if (aantalKilometer > 0){
			this.aantalKilometer = aantalKilometer;			
		} else {
			throw new IllegalArgumentException("Aantal kilometer moet een positief getal zijn.");
		}
		
	}

	public double getAantalLiter() {
		return aantalLiter;
	}

	public void setAantalLiter(double aantalLiter) {
		if (aantalLiter > 0){
			this.aantalLiter = aantalLiter;
		} else {
			throw new IllegalArgumentException("Aantal liter moet een positief getal zijn.");
		}
	}

	public BigDecimal getLiterPrijs() {
		return literPrijs;
	}

	public void setLiterPrijs(BigDecimal literPrijs) {
		if (literPrijs.compareTo(new BigDecimal(0)) == 1){
			this.literPrijs = literPrijs;
		} else {
			throw new IllegalArgumentException("De literprijs moet een positief getal zijn.");
		}
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(aantalKilometer);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(aantalLiter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + datum.hashCode();
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Verbruik))
			return false;
		Verbruik other = (Verbruik) obj;
		if (Double.doubleToLongBits(aantalKilometer) != Double
				.doubleToLongBits(other.aantalKilometer))
			return false;
		if (Double.doubleToLongBits(aantalLiter) != Double
				.doubleToLongBits(other.aantalLiter))
			return false;
		if (datum != other.datum)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Verbruik [aantalKilometer=").append(aantalKilometer)
				.append(", aantalLiter=").append(aantalLiter)
				.append(", literPrijs=").append(literPrijs).append(", datum=")
				.append(datum).append("]");
		return builder.toString();
	}
}