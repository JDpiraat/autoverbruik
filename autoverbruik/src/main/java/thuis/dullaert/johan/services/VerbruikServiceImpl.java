package thuis.dullaert.johan.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import thuis.dullaert.johan.DAO.VerbruikDAO;
import thuis.dullaert.johan.entities.Verbruik;
import thuis.dullaert.johan.exceptions.DAOException;

@Service("verbruikService")
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class VerbruikServiceImpl implements VerbruikService {

	private final VerbruikDAO verbruikDAO;

	@Autowired
	public VerbruikServiceImpl(VerbruikDAO verbruikDAO) {
		this.verbruikDAO = verbruikDAO;
	}

	
	@Override
	@Transactional
	public List<Verbruik> readAll() throws DAOException {
		return verbruikDAO.readAll();
	}

	@Override
	@Transactional(readOnly = false)
	public void create(Verbruik verbruik) throws DAOException {
		verbruikDAO.create(verbruik);		
	}


//	@Override
//	public List<Verbruik> readPages(int page, int recordsPerPage, int noOfRecords) throws DAOException {
//		return verbruikDAO.readPages(page, recordsPerPage, noOfRecords);
//	}

}