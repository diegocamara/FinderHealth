package university.pds.presentation;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import university.pds.business.User;
import university.pds.business.UserController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class NewUser {

	private @Autowired
	UserController userController;
	private User user;
	private String mailConfirm;

	@PostConstruct
	public void initialize() {
		this.user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	public String getMailConfirm() {
		return mailConfirm;
	}

	public void setMailConfirm(String mailConfirm) {
		this.mailConfirm = mailConfirm;
	}

	public void cadastrar() {

		FacesMessage message = new FacesMessage();
		
		if (user.getEmail().trim().equals(mailConfirm.trim())) {

			User userDataBase = userController.searchUser(user);

			if (userDataBase == null) {

				user.setName(user.getName().toLowerCase());
				user.setEmail(user.getEmail().toLowerCase());

				try {
					userController.saveUser(user);
					message.setSeverity(FacesMessage.SEVERITY_INFO);
					message.setSummary("Usuário cadastrado com sucesso!");
				} catch (RuntimeException e) {

				}

			} else {
				message.setSeverity(FacesMessage.SEVERITY_WARN);
				message.setSummary("E-mail já existe!");
			}

		}else{
			message.setSeverity(FacesMessage.SEVERITY_WARN);
			message.setSummary("O e-mail não pode ser confirmado!");
		}
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

}
