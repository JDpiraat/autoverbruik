package thuis.dullaert.johan.DAO;

import java.util.List;

import thuis.dullaert.johan.entities.Verbruik;
import thuis.dullaert.johan.exceptions.DAOException;

public interface VerbruikDAO {
	
	//*********** CRUD operations ***********
    void create(Verbruik verbruik) throws DAOException;
    

    public Verbruik read(int id);
    

    public void update(Verbruik verbruik);

    public void delete(int id);

    //************* bulk read method ***********
    public List<Verbruik> readAll() throws DAOException;


//	List<Verbruik> readPages(int start, int recordsPerPage) throws DAOException;


//	List<Verbruik> readPages(int start, int recordsPerPage, int nOfRecords)
//			throws DAOException;
    
}
