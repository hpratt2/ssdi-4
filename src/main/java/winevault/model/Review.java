package winevault.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Review implements IReview {
	private int id, wineID;
	private IReviewer reviewer;
	private double rating, price;
	private String title = null,
			content = null,
			country = null,
			province = null,
			region = null,
			subregion = null,
			winery = null,
			designation = null;
	
	public Review() { }
	
	public void setID(int id) { this.id = id; }

	public int getID() { return this.id; }
	
	public void setWineID(int id) { this.wineID = id; }

	public int getWineID() { return this.wineID; }

	public void setReviewer(IReviewer r) { this.reviewer = r; }

	public IReviewer getReviewer() { return this.reviewer; }

	public void setRating(double rating) { this.rating = rating; }

	public double getRating() { return this.rating; }

	public void setPrice(double price) { this.price = price; }

	public double getPrice() { return this.price; }

	public void setTitle(String title) { this.title = title; }

	public String getTitle() { return this.title; }

	public void setContent(String content) { this.content = content; }

	public String getContent() { return this.content; }

	public void setCountry(String country) { this.country = country; }

	public String getCountry() { return this.country; }

	public void setProvince(String province) { this.province = province; }

	public String getProvince() { return this.province; }

	public void setRegion(String region) { this.region = region; }

	public String getRegion() { return this.region; }

	public void setSubregion(String subregion) { this.subregion = subregion; }

	public String getSubregion() { return this.subregion; }

	public void setWinery(String winery) { this.winery = winery; }

	public String getWinery() { return this.winery; }

	public void setDesignation(String designation) { this.designation = designation; }

	public String getDesignation() { return this.designation; }

}
