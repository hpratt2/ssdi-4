package winevault.api;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

public class IndexControllerTest extends JerseyTest {
	
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
	}
}