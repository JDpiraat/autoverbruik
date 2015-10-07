package thuis.dullaert.johan.services;

import java.util.List;

import thuis.dullaert.johan.entities.Verbruik;
import thuis.dullaert.johan.exceptions.DAOException;

public interface VerbruikService {

	List<Verbruik> readAll() throws DAOException;

    void create(Verbruik verbruik) throws DAOException;

//	List<Verbruik> readPages(int start, int recordsPerPage, int noOfRecords) throws DAOException;

}
