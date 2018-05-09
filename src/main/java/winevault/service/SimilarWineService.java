package winevault.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import winevault.dao.IReviewDAO;
import winevault.dao.IWineDAO;
import winevault.dao.ReviewDAO;
import winevault.dao.WineDAO;
import winevault.model.IReview;
import winevault.model.IWine;
import winevault.model.UserPreferences;
import winevault.model.Wine;
import winevault.nlp.BagOfWords;
import winevault.util.ConnectionDataTestLarge;

public class SimilarWineService {
	private IWineDAO winedao = new WineDAO(new ConnectionDataTestLarge());
	private IReviewDAO reviewdao = new ReviewDAO(new ConnectionDataTestLarge());
	
	public List<IWine> getSimilarWine(UserPreferences prefs){
		for(Wine w : prefs.getLikes()) System.out.println(w.getName());
		return getSimilarWine(prefs.getLikes(), prefs.getDislikes(), prefs.getDescription());
	}
	
	public List<IWine> getSimilarWine(List<Wine> likes, List<Wine> dislikes, String description){
		List<IWine> similarWines = new ArrayList<IWine>();
		
		List<IWine> wines = winedao.getWineList();
		Map<Integer,BagOfWords> wineBags = getWineBagOfWords();
		
		BagOfWords userWants = new BagOfWords();
		BagOfWords userDoesntWant = new BagOfWords();
		
		if(description != null && !description.isEmpty())
			userWants.add(description);
		
		if(likes != null && !likes.isEmpty()) {
			for(IWine likedWine : likes) {
				// Update bag of words representation of user like
				List<IReview> reviews = reviewdao.getReviewsByWineID(likedWine.getID());
				int i = 0;
				for(IReview review : reviews) {
					userWants.add(review.getContent());
				}
			}
			
			// Remove wines user has tried and liked
			for(IWine likedWine : likes) {
				for(IWine wine : wines) {
					if(wine.equals(likedWine)) { 
						wines.remove(wine);
						break;
					}
				}
			}
		}
		
		System.out.println("Building bag of words for disliked wine...");
		if(dislikes != null && !dislikes.isEmpty()) {
			for(IWine dislikedWine : dislikes) {
				// Update bag of words representation of user likes
				List<IReview> reviews = reviewdao.getReviewsByWineID(dislikedWine.getID());
				for(IReview review : reviews) userDoesntWant.add(review.getContent());
			}
			
			// Remove wines user has tried and disliked
			for(IWine dislikedWine : dislikes) {
				for(IWine wine : wines) {
					if(wine.equals(dislikedWine)) {
						wines.remove(wine);
						break;
					}
				}
			}
		}
		if(wines.size() < 5) return wines;
		
		// Score wines and get top 5
		System.out.println("Comparing preferences to wines...");
		double[] scores = new double[wines.size()];
		for(int i = 0; i < wines.size(); i++) {
			IWine wine = wines.get(i);
			BagOfWords bag = wineBags.get(wine.getID());
			scores[i] = userWants.similarityTo(bag) - userDoesntWant.similarityTo(bag);
			System.out.println(wine.getID() + " -> " + scores[i]);
		}
		
		// Get top 5 scoring wines
		while(similarWines.size() < 5) {
			int maxIndex = 0;
			double maxScore = -100;
			for(int i = 0; i < scores.length; i++) {
				if(scores[i] > maxScore) {
					maxScore = scores[i];
					maxIndex = i;
				}
			}
			similarWines.add(wines.get(maxIndex));
			scores[maxIndex] = -100;
		}
		
		return similarWines;
	}
	
	public List<IWine> getSimilarWine(IWine wine){
		List<IWine> similarWines = new ArrayList<IWine>();
		BagOfWords wineBag = getWineBagOfWords(wine);
		List<IWine> wines = winedao.getWineList();
		
		if(wines.size() < 5) return wines;
		
		double[] scores = new double[wines.size()];
		for(int i = 0; i < wines.size(); i++) {
			System.out.print("computing similarity of " + wines.get(i).getID());
			BagOfWords bag = getWineBagOfWords(wines.get(i));
			scores[i] = wineBag.similarityTo(bag);
			System.out.println(" -> " + scores[i]);
		}
		
		while(similarWines.size() < 5) {
			int maxIndex = 0;
			double maxScore = -100;
			for(int i = 0; i < scores.length; i++) {
				if(scores[i] > maxScore) {
					maxScore = scores[i];
					maxIndex = i;
				}
			}
			if(!wines.get(maxIndex).equals(wine))
				similarWines.add(wines.get(maxIndex));
			scores[maxIndex] = -100;
		}
		
		return similarWines;
	}
	
	private Map<Integer,BagOfWords> getWineBagOfWords(){
		Map<Integer,BagOfWords> bags = new HashMap<Integer,BagOfWords>();
		List<IWine> wines = winedao.getWineList();
		for(IWine wine : wines) {
			bags.put(wine.getID(), new BagOfWords());
			List<IReview> reviews = reviewdao.getReviewsByWineID(wine.getID());
			for(IReview review : reviews) {
				bags.get(wine.getID()).add(review.getContent());
			}
		}
		return bags;
	}
	
	private BagOfWords getWineBagOfWords(IWine wine) {
		BagOfWords bag = new BagOfWords();
		List<IReview> reviews = reviewdao.getReviewsByWineID(wine.getID());
		for(IReview review : reviews) bag.add(review.getContent());
		return bag;
	}
}
