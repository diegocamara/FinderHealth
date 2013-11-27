package university.pds.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import javax.xml.crypto.Data;

import org.springframework.stereotype.Component;

import university.pds.business.LoginForm;
import university.pds.business.User;

@Component
public class UserDao {

	private @PersistenceUnit EntityManagerFactory factory;
	private @PersistenceContext EntityManager manager;	
	
	public void saveUser(User user) {
		manager.persist(user);		
	}

	public User searchUser(LoginForm loginForm) {
		
		User user = null;
		
		String jpql = "select u from " + User.class.getName() + " u where u.email = :email and u.password = :password";		
		TypedQuery<User> query = manager.createQuery(jpql,User.class);
		query.setParameter("email", loginForm.getEmail().toLowerCase());
		query.setParameter("password", loginForm.getPassword());
		
		try{
		
		user = query.getSingleResult();
		
		}catch(NoResultException e){
			
		}
					
		return user;
	}

	public User searchUser(User user) {
		
		User dataBaseUser = null;
		
		String jpql = "select u from " + User.class.getName() + " u where u.email = :email";		
		TypedQuery<User> query = manager.createQuery(jpql,User.class);
		query.setParameter("email", user.getEmail().toLowerCase());		
		
		try{
		
		dataBaseUser = query.getSingleResult();
		
		}catch(NoResultException e){
			
		}
			
		
		return dataBaseUser;
	}
	
}
