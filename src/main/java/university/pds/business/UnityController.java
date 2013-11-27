package university.pds.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import university.pds.persistence.UnityDao;

@Component
public class UnityController {

	private @Autowired UnityDao dao;

	public List<Unity> searchUnity(UnitySearchOptions unityOptions) {
		
		return dao.searchUnity(unityOptions);
	}

	public Integer searchUnityCount(UnitySearchOptions unityOptions) {
		return dao.searchUnityCount(unityOptions);		
	}

	public Unity searchUnityById(Integer unityId) {
		return dao.searchUnityById(unityId);
	}
	
}