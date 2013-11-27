package university.pds.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import university.pds.persistence.CategoryDao;

@Component
public class CategoryController {

	private @Autowired CategoryDao dao;

	public List<Category> selectCategory() {
		
		return dao.selectCategory();
	}
	
}