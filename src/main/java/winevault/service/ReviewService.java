package winevault.service;

import java.util.List;

import winevault.dao.IWineDAO;
import winevault.dao.WineDAO;
import winevault.model.IWine;
import winevault.util.ConnectionDataTestLarge;

public class ReviewService {
	private IReviewDAO dao = new ReviewDAO(new ConnectionDataTestLarge());
	
	public List<IReview> getReviews(){
		return dao.getReviews();
	}
	
	public List<IReview> getReviewsByWineID(int wineID){
		return dao.getReviewsByWineID(wineID);
	}
}
