package university.pds.business;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import university.pds.persistence.UserDao;

@Component
public class UserController {

	private @Autowired UserDao dao;
	
	@Transactional
	public void saveUser(User user) {
		dao.saveUser(user);		
	}

	public User searchUser(LoginForm loginForm) {		
		return dao.searchUser(loginForm);
	}

	public User searchUser(User user) {		
		return dao.searchUser(user);
	}
	
	

}
