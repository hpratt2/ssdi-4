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
	private static IWineService wineService = new WineService();
	private static ReviewService reviewService = new ReviewService();
	private static SimilarWineService similarWineService = new SimilarWineService();
	
	@Context
	ServletContext sc;
	
	@GET
	@Path("/winelist")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IWine> getWineList(){
		return wineService.getWineList();
	}
	
	@GET
	@Path("/wine/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public IWine getWineByID(@PathParam("id") int id) {
		return wineService.getWineByID(id);
	}
	
	@GET
	@Path("/reviews/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IReview> getReviews(@PathParam("id") int id){
		return reviewService.getReviewsByWineID(id);
	}
	
	@GET
	@Path("/similarwine/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IWine> getSimilarWines(@PathParam("id") int id){
		return similarWineService.getSimilarWine(wineService.getWineByID(id));
	}
}
