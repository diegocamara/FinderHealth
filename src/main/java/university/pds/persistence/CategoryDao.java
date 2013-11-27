package university.pds.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import university.pds.business.Category;

@Component
public class CategoryDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;
	
	
	public List<Category> selectCategory() {
		
		String jpql = "select c from " + Category.class.getName() + " c";
		
		TypedQuery<Category> query = manager.createQuery(jpql, Category.class);		
		
		return query.getResultList();
	}
	
	
	
}// fim da classe CategoriaDao.