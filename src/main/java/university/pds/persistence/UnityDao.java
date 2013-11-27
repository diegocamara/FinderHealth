package university.pds.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import university.pds.business.Unity;
import university.pds.business.UnitySearchOptions;

@Component
public class UnityDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;
	
	public List<Unity> searchUnity(UnitySearchOptions unityOptions) {
	
		String jpql = "select u from " + Unity.class.getName() + " u";
		TypedQuery<Unity> query = manager.createQuery(jpql, Unity.class);
		
		/*
		if(unityOptions.getFirstResult() != null){
			query.setFirstResult(unityOptions.getFirstResult());
		}
		
		if(unityOptions.getMaxResult() != null){
			query.setMaxResults(unityOptions.getMaxResult());
		}
		*/
		
		return query.getResultList();
	}

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
	
}
