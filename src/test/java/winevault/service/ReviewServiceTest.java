package winevault.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;

import winevault.dao.ReviewDAO;
import winevault.model.IReview;

public class ReviewServiceTest {
	ReviewDAO dao = mock(ReviewDAO.class);
	ReviewService rsl = mock(ReviewService.class);
	
	private List<IReview> test = new ArrayList<IReview>();
	//REVIEW 1
	IReview r1 = null;
	//REVIEW 2
	IReview r2 = null;

	@Test
	public void testGetReviews() {
		test.add(r1);
		test.add(r2);
		
		when(dao.getReviews()).thenReturn(test);
		rsl.getReviews();
		verify(rsl, times(1)).getReviews();
	}

	@Test
	public void testGetReviewsByWineID() {
		int id = 83;
		test.add(r1);
		test.add(r2);
		
		when(dao.getReviewsByWineID(id)).thenReturn(test);
		rsl.getReviewsByWineID(id);
		verify(rsl, times(1)).getReviewsByWineID(id);
		
	}

}
