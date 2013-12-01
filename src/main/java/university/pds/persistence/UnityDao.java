package university.pds.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import university.pds.business.Category;
import university.pds.business.Unity;
import university.pds.business.UnitySearchOptions;

@Component
public class UnityDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;
	

	public Integer searchUnityCount(UnitySearchOptions unityOptions) {
		
		String jpql = "select count(u) from " + Unity.class.getName() + " u";
		TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
		
		return query.getSingleResult().intValue();
	}

	public Unity searchUnityById(Integer unityId) {
		
		String jpql = "select u from " + Unity.class.getName() + " u where u.id = :unityId";
		TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
		query.setParameter("unityId", unityId);		
		return query.getSingleResult();
	}

	public List<Unity> searchUnityByCategory(Category category) {
		
		String jpql = "select u from " + Unity.class.getName() + " u where u.category = :category";			
		TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
		query.setParameter("category", category);
		
		return query.getResultList();
		
	}

	public List<Unity> searchUnityBySpeciality(String speciality) {
		
		String jpql = "select u from " + Unity.class.getName() + " u where lower(u.especialidade) like :especialidade";			
		TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
		query.setParameter("especialidade", "%" + speciality.toLowerCase() + "%");
		
		return query.getResultList();
	}

	public List<Unity> searchUnityByBairro(String bairro) {		
		
		if(bairro.length() > 0){		
			String jpql = "select u from " + Unity.class.getName() + " u where lower(u.bairro) like :bairro";		
			TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
			query.setParameter("bairro", "%" + bairro.toLowerCase() + "%");
			return query.getResultList();
		}else{
			return new ArrayList<Unity>();
		}
				
		
	}

	public List<Unity> searchAllUnitys() {
		
		String jpql = "select u from " + Unity.class.getName() + " u";
		TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
		
		return query.getResultList();
	}
	
}
