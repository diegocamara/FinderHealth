package university.pds.presentation;

import java.util.List;

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
import university.pds.business.UnitySearchOptions;

@Component
@Scope(WebApplicationContext.SCOPE_SESSION)
public class EntitySearch {
	
	private static final int UNITYS_PER_PAGES = 10;
	
	private @Autowired CategoryController categoryController;
	private @Autowired SpecialityController specialityController;
	private @Autowired UnityController unityController;
	
	private List<Category> categoryList;
	private List<Speciality> specialityList;
	private List<Unity> unityList;
	private UnitySearchOptions unityOptions;
	private List<Integer> pages;
	private int page;
	
	
	public EntitySearch(){
		
		initialize();
	}



	private void initialize() {
		
		this.pages = null;
		
		this.unityOptions = new UnitySearchOptions();
		
		this.unityOptions.setCategoryId(0);
		
		this.categoryController = SpringUtils.getBean(CategoryController.class);
		this.specialityController = SpringUtils.getBean(SpecialityController.class);	
		
		
		
		this.categoryList = categoryController.selectCategory();
		
		this.specialityList = specialityController.selectSpeciality();
	}
	

	
	public List<Category> getCategoryList() {
		return categoryList;
	}


	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}
	

	public UnitySearchOptions getUnityOptions() {
		
		//configurando categoria selecionada.
		for(Category category : categoryList){
			if(category.getId().equals(unityOptions.getCategoryId())){
				unityOptions.setCategory(category);
			}
		}
		
		return unityOptions;
	}
	

	public void setUnityOptions(UnitySearchOptions unityOptions) {
		
		this.unityOptions = unityOptions;
		
	}


	public String search(){
		
		/*
		Integer unityCount = unityController.searchUnityCount(unityOptions);
						
		int pageCount = unityCount / UNITYS_PER_PAGES;
		
		if(unityCount % UNITYS_PER_PAGES > 0){
			pageCount++;
		}
		
		this.pages = new ArrayList<>();
		
		for(int page = 1; page < pageCount; ++page){
			this.pages.add(page);
		}
		
		goToPage(1);
		
		*/
		
		this.unityList = unityController.searchUnity(unityOptions);
		
		return "results";
	}
	
	public void goToPage(int page){
		this.page = page;
		
		int firstResult = (page-1) * UNITYS_PER_PAGES;
		
		this.unityOptions.setFirstResult(firstResult);
		this.unityOptions.setMaxResult(UNITYS_PER_PAGES);
		
		this.unityList = unityController.searchUnity(unityOptions);
		
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

	public String getRowsNumber(){
		Integer rows = UNITYS_PER_PAGES;		
		return rows.toString();
	}


	public void setPages(List<Integer> pages) {
		this.pages = pages;
	}
	
	
	
		
}