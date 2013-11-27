package university.pds.business;

import org.primefaces.model.map.LatLng;

public class UserLocation {
	
	private LatLng latLng;
	private String lat;
	private String lng;
	
	public void setCoord(){
		this.latLng = new LatLng(Float.parseFloat(lat), Float.parseFloat(lng));
	}
	
	public LatLng getLatLng() {
		return latLng;
	}

	public void setLatLng(LatLng latLng) {
		this.latLng = latLng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}	
	
	

}
