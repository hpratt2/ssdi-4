package winevault.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import winevault.dao.WineDAO;
import winevault.model.IWine;
import winevault.model.Wine;

import static org.mockito.Mockito.*;

public class WineListServiceTest {
	WineDAO dao = mock(WineDAO.class);
	WineListService wls = mock(WineListService.class);
	
	private List<IWine> test = new ArrayList<IWine>();
	Wine w1 = new Wine(1, "White Blend", "Italy", 87.0, 10.0, 12.0);
	Wine w2 = new Wine(2, "Portuguese Red", "Portugal", 87.0, 15.0, 15.0);
	Wine w3 = new Wine(3, "Pinot Gris", "US", 87.33, 14.0, 27.0);
	
	@Test
	void testGetWineList() {
		test.add(w1);
		test.add(w2);
		test.add(w3);
		when(dao.getWineList()).thenReturn(test);
		wls.getWineList();
		verify(wls, times(1)).getWineList();
	}
}
