package university.pds.presentation;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import university.pds.business.Comment;
import university.pds.business.CommentController;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class CommentMB {

	private @Autowired CommentController commentController;
	
	private Comment comment;
	
	@PostConstruct
	public void initialize(){
		comment = new Comment();
		comment.setStarsNumber(1);
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}	

	
	
}
