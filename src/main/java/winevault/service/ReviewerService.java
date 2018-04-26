package winevault.service;

import java.util.List;

import winevault.dao.IReviewerDAO;
import winevault.dao.ReviewerDAO;
import winevault.model.IReviewer;
import winevault.util.ConnectionDataTestLarge;

public class ReviewerService {
	
	private IReviewerDAO dao = new ReviewerDAO(new ConnectionDataTestLarge());
	
	public List<IReviewer> getReviewers(){
		return dao.getReviewers();
	}
	
	public IReviewer getReviewerByID(int id) {
		return dao.getReviewerByID(id);
	}
}
