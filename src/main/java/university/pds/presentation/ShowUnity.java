package university.pds.presentation;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import university.pds.business.Comment;
import university.pds.business.CommentController;
import university.pds.business.Unity;
import university.pds.business.UnityController;
import university.pds.business.UnityLocation;

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowUnity {

	private @Autowired UnityController unityController;
	private @Autowired CommentController commentController;
	
	@Autowired
	private UserSession userSession;
	
	private Integer unityId;
	private Unity unity;
	private MapModel model;
	private List<Comment> comments;
	private Comment comment;
	private boolean confirm;
	private boolean haveLatlng;
	private UnityLocation unityLocation;
	
	@PostConstruct
	private void initialize(){	
		model = new DefaultMapModel();
		comment = new Comment();
		comment.setStarsNumber(1);
		unityLocation = new UnityLocation();
				
	}
	
	public String addComment(){
		comment.setDate(new Date());		
		comment.setUnity(unity);	
		comment.setUser(userSession.getUser());		
		commentController.saveComment(comment);		
		return "showMap";
		//return "/pds/faces/showMap.xhtml?unityId="+unity.getId();
		
	}
	
	public Integer getUnityId() {
		return unityId;
	}
	public void setUnityId(Integer unityId) {		
		this.unityId = unityId;	
		unity = unityController.searchUnityById(unityId);
		comments = commentController.searchComments(unityId);
		
		if(unity.getLatitude() != null && unity.getLongitude() != null){
			
			LatLng coord = new LatLng(Float.parseFloat(unity.getLatitude().replace(",", ".")), Float.parseFloat(unity.getLongitude().replace(",", ".")));
			model.addOverlay(new Marker(coord,unity.getUnityName()));
			haveLatlng = true;
			
			
			unityLocation.setLat(unity.getLatitude().replace(",", "."));
			unityLocation.setLng(unity.getLongitude().replace(",", "."));

		}else{
			haveLatlng = false;						
		}
		
	}
	
	public Unity getUnity() {
		return unity;
	}
	public void setUnity(Unity unity) {
		this.unity = unity;
	}

	public MapModel getModel() {
		return model;
	}

	public void setModel(MapModel model) {
		this.model = model;
	}
			
	public UnityController getUnityController() {
		return unityController;
	}

	public void setUnityController(UnityController unityController) {
		this.unityController = unityController;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getCenter(){
		String latitude = unity.getLatitude().replace(",", ".");
		String longitude = unity.getLongitude().replace(",", ".");
		
		return latitude + "," + longitude;
		
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public boolean isHaveLatlng() {
		return haveLatlng;
	}

	public void setHaveLatlng(boolean haveLatlng) {
		this.haveLatlng = haveLatlng;
	}

	public UnityLocation getUnityLocation() {
		return unityLocation;
	}

	public void setUnityLocation(UnityLocation unityLocation) {
		this.unityLocation = unityLocation;
	}
	
	
	
}
