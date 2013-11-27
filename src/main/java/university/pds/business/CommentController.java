package university.pds.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import university.pds.persistence.CommentDao;

@Component
public class CommentController {

	private @Autowired CommentDao dao;

	public List<Comment> searchComments(Integer unityId) {		
		return dao.searchComments(unityId);
	}
	
}
