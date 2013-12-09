package university.pds.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="COMENTARIO")
public class Comment {
	
	private Integer id;
	private String title;
	private String description;
	private Unity unity;
	private User user;
	private Date date;
	private Integer starsNumber;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}	
	
	@Column(name="TITULO")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Column(name="DESCRICAO", length=100000)	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@ManyToOne
	@JoinColumn(name="UNIDADE_ID")
	public Unity getUnity() {
		return unity;
	}
	public void setUnity(Unity unity) {
		this.unity = unity;
	}
	
	@ManyToOne
	@JoinColumn(name="USUARIO_ID", nullable=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name="DATA")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Column(name="STARS_NUMBER")
	public Integer getStarsNumber() {
		return starsNumber;
	}
	public void setStarsNumber(Integer starsNumber) {
		this.starsNumber = starsNumber;
	}
	
	

}
