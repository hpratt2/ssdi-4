package winevault.dao;

import java.util.List;

import winevault.model.IReviewer;

public interface IReviewerDAO {
	public List<IReviewer> getReviewers();
	public IReviewer getReviewerByID(int id);
}
