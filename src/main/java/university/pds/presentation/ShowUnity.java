package university.pds.presentation;

import java.util.List;

import javax.annotation.PostConstruct;

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

@Component
@Scope(WebApplicationContext.SCOPE_REQUEST)
public class ShowUnity {

	private @Autowired UnityController unityController;
	private @Autowired CommentController commentController;
	
	private Integer unityId;
	private Unity unity;
	private MapModel model;
	private List<Comment> comments;
	
	@PostConstruct
	private void initialize(){	
		model = new DefaultMapModel();		
	}
	
	public Integer getUnityId() {
		return unityId;
	}
	public void setUnityId(Integer unityId) {		
		this.unityId = unityId;	
		unity = unityController.searchUnityById(unityId);
		comments = commentController.searchComments(unityId);
		LatLng coord = new LatLng(Float.parseFloat(unity.getLatitude().replace(",", ".")), Float.parseFloat(unity.getLongitude().replace(",", ".")));
		model.addOverlay(new Marker(coord,unity.getUnityName()));
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
	
}
