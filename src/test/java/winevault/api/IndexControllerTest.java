package winevault.api;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import winevault.model.IWine;
import winevault.model.Wine;
import winevault.service.WineService;

public class IndexControllerTest extends JerseyTest {
	
	private List<IWine> test = new ArrayList<IWine>();
	Wine w1 = new Wine(1, "White Blend", "Italy", 87.0, 10.0, 12.0);
	Wine w2 = new Wine(2, "Portuguese Red", "Portugal", 87.0, 15.0, 15.0);
	Wine w3 = new Wine(3, "Pinot Gris", "US", 87.33, 14.0, 27.0);
	
	WineService wls = mock(WineService.class);
	IndexController ctrl = mock(IndexController.class);
	
	@Override
	public Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(IndexController.class);
	}
	
	@Test
	public void testGetWineList() {
		Response r = target("/winelist").request().get();
		assertEquals(200, r.getStatus());
		
		test.add(w1);
		test.add(w2);
		test.add(w3);
		when(wls.getWineList()).thenReturn(test);
		ctrl.getWineList();
		verify(ctrl, times(1)).getWineList();
	}
}