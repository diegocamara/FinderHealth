package university.pds.presentation;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.aop.scope.ScopedObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.sun.org.apache.bcel.internal.generic.NEW;

import university.pds.business.*;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class UserSession {

	private @Autowired
	UserController userController;

	private boolean logado;
	private User user;
	private LoginForm loginForm;
	

	@PostConstruct
	private void initialize() {
		this.loginForm = new LoginForm();		

	}

	public String login() {

		String result = "";

		FacesMessage message = new FacesMessage();

		this.user = userController.searchUser(loginForm);

		if (this.user != null) {
			setLogado(true);
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Usuário logado com sucesso!");
			result = "inicio";
		} else {
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			message.setSummary("E-mail ou senha inválidos!");
		}

		FacesContext.getCurrentInstance().addMessage(null, message);

		return result;

	}

	public String resetBean() {
		logado = false;
		user = null;
		loginForm = new LoginForm();

		return "inicio";
	}

	public String showInfoBar() {

		String info = null;

		if (logado) {
			info = "Bem vindo " + user.getName() + "! ";
		} else {
			info = "Olá visitante.";
		}

		return info;
	}
	
	public boolean isLogado() {
		return logado;
	}

	public void setLogado(boolean logado) {
		this.logado = logado;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	
}