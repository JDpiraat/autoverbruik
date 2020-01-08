package thuis.dullaert.johan.DAO;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import thuis.dullaert.johan.entities.Verbruik;
import thuis.dullaert.johan.exceptions.DAOException;

@Repository("verbruikDAO")
@Transactional
public class VerbruikDAOImpl implements VerbruikDAO {

	private final SessionFactory sessionFactory;

	/**
	 * @param sessionFactory
	 */
	@Autowired
	public VerbruikDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// *********** CRUD operations ***********

	@Override
	public void create(Verbruik verbruik) throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();
			try {
				session.save(Verbruik.class.getName(), verbruik);				
			} catch (RuntimeException e) {
				session.clear();				
				throw new DAOException("De database zegt 'neen'.", e);
			} finally {
				session.clear();
			}
		} catch (Exception e) {			
			throw new DAOException("De database is momenteel op verlof.", e);
		}
		
		
	}

	@Override
	public Verbruik read(int id) {
		// not needed for now
		return null;
	}

	@Override
	public void update(Verbruik verbruik) {
		// not needed for now
	}

	@Override
	public void delete(int id) {
		// not needed for now
	}

	// ************* bulk read method ***********
	@Override
	@SuppressWarnings("unchecked")
	public List<Verbruik> readAll() throws DAOException{
		try {
			Session session = sessionFactory.getCurrentSession();		
			Query query = session.createQuery("from Verbruik v order by v.datum desc, v.id desc");
			return query.list();
		} catch (Exception e) {
			throw new DAOException("De database wil de info niet lossen.", e);
		}
		
	}

	// overbodige methode door sorteren op datum (eerder niet meer bruikbaar)
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<Verbruik> readPages(int page, int recordsPerPage, int noOfRecords) throws DAOException {
//		try {
//			Session session = sessionFactory.getCurrentSession();		
//			Query query = session.createQuery("from Verbruik where id between " + (noOfRecords - (page * recordsPerPage) + 1) + " and " + (noOfRecords - ((page - 1) * recordsPerPage)));
//			List<Verbruik> verbruik = query.list();
//			Collections.reverse(verbruik);
//			return verbruik;
//		} catch (Exception e) {
//			throw new DAOException("De database wil de info niet lossen.", e);
//		}		
//	}
}
