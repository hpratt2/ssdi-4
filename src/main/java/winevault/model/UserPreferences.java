package winevault.model;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UserPreferences {
	private List<Wine> likes;
	private List<Wine> dislikes;
	private String description;
	
	public UserPreferences() { 
		this.likes = new ArrayList<Wine>();
		this.dislikes = new ArrayList<Wine>();
		this.description = "";
	}
	
	public void setLikes(List<Wine> wines) { this.likes = wines; }
	public void setLikes(Wine[] wines) {
		this.likes = new ArrayList<Wine>();
		for(Wine wine : wines) this.likes.add(wine);
	}
	public List<Wine> getLikes(){ return this.likes; }
	
	public void setDislikes(List<Wine> wines) { this.dislikes = wines; }
	public void setDislikes(Wine[] wines) {
		this.likes = new ArrayList<Wine>();
		for(Wine wine : wines) this.dislikes.add(wine);
	}
	public List<Wine> getDislikes(){ return this.dislikes; }
	
	public void setDescription(String desc) { this.description = desc; }
	public String getDescription() { return this.description; }
}
