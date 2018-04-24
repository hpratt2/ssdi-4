package winevault.dao;

import java.util.List;
import winevault.model.IReview;

public interface IReviewDAO {
	public List<IReview> getReviewsByWineID(int wineID);
	public List<IReview> getReviews();
}
