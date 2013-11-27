package university.pds.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import university.pds.business.Speciality;

@Component
public class SpecialityDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;
	
	public List<Speciality> selectSpeciality() {
		
		String jpql = "select s from " + Speciality.class.getName() + " s";
		
		TypedQuery<Speciality> query = manager.createQuery(jpql, Speciality.class);
						
		return query.getResultList();
	}

}
