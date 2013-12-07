package university.pds.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import university.pds.persistence.UnityDao;

@Component
public class UnityController {

	private @Autowired UnityDao dao;
	
	public Integer searchUnityCount(UnitySearchOptions unityOptions) {
		return dao.searchUnityCount(unityOptions);		
	}

	public Unity searchUnityById(Integer unityId) {
		return dao.searchUnityById(unityId);
	}

	public List<Unity> searchUnityByCategory(Category category) {		
		return dao.searchUnityByCategory(category);
	}

	public List<Unity> searchUnityBySpeciality(String speciality) {		
		return dao.searchUnityBySpeciality(speciality);
	}

	public List<Unity> searchUnityByBairro(String bairro) {		
		return dao.searchUnityByBairro(bairro);
	}

	public List<Unity> searchAllUnitys() {		
		return dao.searchAllUnitys();
	}

	public List<Unity> searchUnityByInput(String input) {		
		return dao.searchUnityByInput(input);
	}

		
}