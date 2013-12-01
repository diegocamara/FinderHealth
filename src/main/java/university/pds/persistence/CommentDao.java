package university.pds.persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;

import university.pds.business.Comment;

@Component
public class CommentDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;
	
	public List<Comment> searchComments(Integer unityId) {
		
		String jpql = "select c from " + Comment.class.getName() + " c where c.unity.id = :unityId";
		TypedQuery<Comment> query = manager.createQuery(jpql,Comment.class);
		query.setParameter("unityId", unityId);		
		
		return query.getResultList();
	}

	public void saveComment(Comment comment) {
		manager.persist(comment);		
	}
	
}
