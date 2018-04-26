package winevault.api;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import winevault.model.IReview;
import winevault.model.IWine;
import winevault.service.*;

@Path("/")
public class IndexController {
	private static IWineListService wineListService = new WineListService();
	private static ReviewService reviewService = new ReviewService();
	
	@Context
	ServletContext sc;
	
	@GET
	@Path("/winelist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IWine> getWineList(){
		return wineListService.getWineList();
	}
	
	@GET
	@Path("/reviews/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IReview> getReviews(@PathParam("id") int id){
		return reviewService.getReviewsByWineID(id);
	}
}
