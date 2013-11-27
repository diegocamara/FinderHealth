package university.pds.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="USUARIO")
public class User {

	private Integer id;
	private String name;
	private String email;
	private String password;
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="NOME")	
	@Size(min=5, max=100)	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="EMAIL")
	@Email(message="E-mail inválido")
	@Size(min=3, max=100, message="Campo e-mail obrigatório!")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name="PASSWORD")
	@Size(min=5,max=10, message="O campo password deve ter entre 5 e 10 caracteres!")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
			
}
