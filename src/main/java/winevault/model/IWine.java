package winevault.model;

public interface IWine {
	public int getID();
	public void setID(int id);
	public String getName();
	public void setName(String name);
	public String getCountry();
	public void setCountry(String country);
	public double getAvgRating();
	public void setAvgRating(double avgRating);
	public double getPriceLow();
	public void setPriceLow(double priceLow);
	public double getPriceHigh();
	public void setPriceHigh(double priceHigh);
}
