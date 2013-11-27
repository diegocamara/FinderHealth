package university.pds.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import university.pds.persistence.SpecialityDao;

@Component
public class SpecialityController {

	private @Autowired SpecialityDao dao;

	public List<Speciality> selectSpeciality() {		
		return dao.selectSpeciality();
	}
	
}