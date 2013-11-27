package university.pds.business;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="UNIDADE")
public class Unity {
	
	private Integer id;
	private Category category;
	private String unityName;
	private String endereco;
	private String bairro;
	private String fone;
	private String especialidade;
	private String latitude;
	private String longitude;
	private List<Comment> comments;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name="ID_CATEGORIA")
	public Category getCategory() {
		return category;
	}
	
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column(name="UNIDADE")
	@Size(min=1, max=1000)
	public String getUnityName() {
		return unityName;
	}
	public void setUnityName(String unityName) {
		this.unityName = unityName;
	}
	
	@Column(name="ENDERECO")
	@Size(min=1, max=1500)
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Column(name="BAIRRO")
	@Size(min=1, max=1000)
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	@Column(name="FONE")
	@Size(min=1, max=20)
	public String getFone() {
		return fone;
	}
	public void setFone(String fone) {
		this.fone = fone;
	}
	
	@Column(name="ESPECIALIDADE")
	@Size(min=1, max=1000)
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	
	@Column(name="LATITUDE")
	@Size(min=1, max=1000)
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Column(name="LONGITUDE")
	@Size(min=1, max=1000)
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	@OneToMany
	@JoinColumn(name="COMMENTS")
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	

}// fim da classe Unity.