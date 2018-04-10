package winevault.api;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import winevault.model.IWine;
import winevault.service.*;

@Path("/")
public class IndexController {
	private static IWineListService wls = new WineListService();
	
	@Context
	ServletContext sc;
	
	@GET
	@Path("/winelist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IWine> getWineList(){
		return wls.getWineList();
	}
}
