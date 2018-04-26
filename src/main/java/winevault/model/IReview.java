package winevault.model;

public interface IReview {
	public void setID(int id);
	public int getID();
	public void setWineID(int id);
	public int getWineID();
	public void setReviewer(IReviewer r);
	public IReviewer getReviewer();
	public void setRating(double rating);
	public double getRating();
	public void setPrice(double price);
	public double getPrice();
	public void setTitle(String title);
	public String getTitle();
	public void setContent(String content);
	public String getContent();
	public void setCountry(String country);
	public String getCountry();
	public void setProvince(String province);
	public String getProvince();
	public void setRegion(String region);
	public String getRegion();
	public void setSubregion(String subregion);
	public String getSubregion();
	public void setWinery(String winery);
	public String getWinery();
	public void setDesignation(String designation);
	public String getDesignation();
}
