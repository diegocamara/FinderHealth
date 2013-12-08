package university.pds.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import university.pds.business.Category;
import university.pds.business.CategoryController;
import university.pds.business.Speciality;
import university.pds.business.SpecialityController;
import university.pds.business.Unity;
import university.pds.business.UnityController;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EntitySearch {

	private static final int UNITYS_PER_PAGES = 10;

	private @Autowired
	CategoryController categoryController;
	private @Autowired
	SpecialityController specialityController;
	private @Autowired
	UnityController unityController;

	private List<Category> categoryList;
	private List<Speciality> specialityList;
	private List<Unity> unityList;
	private List<Integer> pages;
	private int page;
	private Category category;
	private Integer categoryId;
	private String speciality;
	private Integer specialityId;
	private String bairro;
	private String input = "";

	@PostConstruct
	private void initialize() {

		this.pages = null;

		this.categoryList = categoryController.selectCategory();

		this.specialityList = specialityController.selectSpeciality();
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public void specialityConfig() {
		// configurando especialidade selecionada.
		for (Speciality speciality : specialityList) {
			if (speciality.getId().equals(specialityId)) {
				this.speciality = speciality.getName();
				break;
			} else {
				this.speciality = null;
			}
		}
	}

	public void categoryConfig() {
		// configurando categoria selecionada.
		for (Category category : categoryList) {
			if (category.getId().equals(categoryId)) {
				this.category = category;
				break;
			} else {
				this.category = null;
			}
		}
	}

	
	public String searchCategory() {

		if (category != null) {

			this.unityList = unityController.searchUnityByCategory(category);

			this.category = new Category();

			return "results";
		} else {
			FacesMessage message = new FacesMessage();

			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Pelo menos uma categoria deve ser selecionada!");

			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}

	}

	public String searchSpeciality() {

		if (speciality != null) {

			this.unityList = unityController
					.searchUnityBySpeciality(speciality);

			this.speciality = new String();

			return "results";
		} else {
			FacesMessage message = new FacesMessage();

			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Pelo menos uma especialidade deve ser selecionada!");

			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
	}

	public String searchBairro() {

		if (bairro.length() > 0) {

			this.unityList = unityController.searchUnityByBairro(bairro);

			this.bairro = new String();

			return "results";
		} else {
			FacesMessage message = new FacesMessage();

			message.setSeverity(FacesMessage.SEVERITY_INFO);
			message.setSummary("Pelo menos uma especialidade deve ser selecionada!");

			FacesContext.getCurrentInstance().addMessage(null, message);
			return "";
		}
	}

	public String searchAll() {

		this.unityList = unityController.searchAllUnitys();
		this.category = new Category();
		this.speciality = new String();

		return "results";
	}

	public String searchInput() {

		this.unityList = unityController.searchUnityByInput(input);

		return "results";
	}

	public void listIterator(List<Unity> target, List<Unity> temp) {

		for (Unity unity : temp) {
			target.add(unity);
		}

	}

	public List<Speciality> getSpecialityList() {
		return specialityList;
	}

	public void setSpecialityList(List<Speciality> specialityList) {
		this.specialityList = specialityList;
	}

	public List<Unity> getUnityList() {
		return unityList;
	}

	public void setUnityList(List<Unity> unityList) {
		this.unityList = unityList;
	}

	public List<Integer> getPages() {
		return pages;
	}

	public String getRowsNumber() {
		Integer rows = UNITYS_PER_PAGES;
		return rows.toString();
	}

	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public Integer getSpecialityId() {
		return specialityId;
	}

	public void setSpecialityId(Integer specialityId) {
		this.specialityId = specialityId;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

}