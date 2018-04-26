package winevault.service;

import java.util.List;

import winevault.util.ConnectionDataTestLarge;

public class ReviewerService {
	
	private IReviewerDAO dao = new ReviewerDAO(new ConnectionDataTestLarge());
	
	public List<IReviewer> getReviewers(){
		return dao.getReviewers();
	}
}
