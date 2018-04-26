package winevault.service;

import java.util.List;

import winevault.dao.IReviewDAO;
import winevault.dao.ReviewDAO;
import winevault.model.IReview;
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
