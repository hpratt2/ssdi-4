package winevault.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Wine implements IWine {
	private int id;
	private String name, country;
	private double avgRating, priceLow, priceHigh;
	
	public Wine() { }
	
	public Wine(int id, String name, String country, double avgRating, double priceLow, double priceHigh) {
		this.id = id;
		this.name = name;
		this.country = country;
		this.avgRating = avgRating;
		this.priceLow = priceLow;
		this.priceHigh = priceHigh;
	}
	
	public int getID() { return id; }
	public void setID(int id) { this.id = id; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getCountry() { return country; }
	public void setCountry(String country) { this.country = country; }

	public double getAvgRating() { return avgRating; }
	public void setAvgRating(double avgRating) { this.avgRating = avgRating; }

	public double getPriceLow() { return priceLow; }
	public void setPriceLow(double priceLow) { this.priceLow = priceLow; }

	public double getPriceHigh() { return priceHigh; }
	public void setPriceHigh(double priceHigh) { this.priceHigh = priceHigh; }
	
	public boolean equals(Wine wine) {
		return this.id == wine.getID();
	}
	
}
