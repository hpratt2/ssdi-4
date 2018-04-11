package winevault.api;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import winevault.model.IWine;
import winevault.model.Wine;
import winevault.service.WineListService;
import static org.mockito.Mockito.*; 
import static org.junit.Assert.assertEquals;


public class IndexControllerTest extends IndexController {
	@Test
	public void testGetWineList() {
		List<IWine> test = new ArrayList<IWine>();
		Wine w1 = new Wine(1,"White Blend", "Italy", 87.00, 10.00, 12.00);
		Wine w2 = new Wine(2,"Portuguese Red", "Portugal", 87.00, 15.00, 15.00);
		Wine w3 = new Wine(3,"Pinot Gris", "US", 87.33, 14.00, 27.00);
		test.add(w1);
		test.add(w2);
		test.add(w3);
		
		WineListService wls = mock(WineListService.class);
		when(wls.getWineList()).thenReturn(test);
		
		IndexController cont = new IndexController();
		List<IWine> returned = cont.getWineList();
		
		assertEquals(w1,returned.get(0));
		assertEquals(w2,returned.get(1));
		assertEquals(w3,returned.get(2));
		
		
	}


}