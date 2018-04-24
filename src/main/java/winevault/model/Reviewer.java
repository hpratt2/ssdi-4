package winevault.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Reviewer implements IReviewer {
	int id;
	private String name, username;
	
	public Reviewer() { }
	
	public Reviewer(int id, String name, String username) {
		this.id = id;
		this.name = name;
		this.username = username;
	}
	
	public void setID(int id) { this.id = id; }
	
	public int getID() { return this.id; }
	
	public void setName(String name) { this.name = name; }

	public String getName() { return this.name; }

	public void setUsername(String username) { this.username = username; }

	public String getUsername() { return this.username; }

}
