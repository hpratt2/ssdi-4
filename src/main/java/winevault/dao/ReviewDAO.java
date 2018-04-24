package winevault.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import winevault.model.IReview;
import winevault.model.Review;
import winevault.util.ConnectionUtil;
import winevault.util.IConnectionData;

public class ReviewDAO implements IReviewDAO {
	private IConnectionData connData;
	
	public ReviewDAO(IConnectionData connData) {
		this.connData = connData;
	}
	
	public List<IReview> getReviewsByWineID(int wineID) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<IReview> reviews = new ArrayList<IReview>();
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.prepareStatement("SELECT * FROM reviews WHERE wid = ?");
			statement.setInt(1, wineID);
			rs = statement.executeQuery();
			while(rs.next()) {
				Review r = new Review();
				r.setID(rs.getInt("id"));
				r.setWineID(rs.getInt("wid"));
				r.setReviewerID(rs.getInt("uid"));
				r.setRating(rs.getDouble("rating"));
				r.setPrice(rs.getDouble("price"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setCountry(rs.getString("country"));
				r.setProvince(rs.getString("province"));
				r.setRegion(rs.getString("region"));
				r.setSubregion(rs.getString("subregion"));
				r.setWinery(rs.getString("winery"));
				r.setDesignation(rs.getString("designation"));
				reviews.add(r);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}

	public List<IReview> getReviews() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<IReview> reviews = new ArrayList<IReview>();
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.prepareStatement("SELECT * FROM reviews");
			rs = statement.executeQuery();
			while(rs.next()) {
				Review r = new Review();
				r.setID(rs.getInt("id"));
				r.setWineID(rs.getInt("wid"));
				r.setReviewerID(rs.getInt("uid"));
				r.setRating(rs.getDouble("rating"));
				r.setPrice(rs.getDouble("price"));
				r.setTitle(rs.getString("title"));
				r.setContent(rs.getString("content"));
				r.setCountry(rs.getString("country"));
				r.setProvince(rs.getString("province"));
				r.setRegion(rs.getString("region"));
				r.setSubregion(rs.getString("subregion"));
				r.setWinery(rs.getString("winery"));
				r.setDesignation(rs.getString("designation"));
				reviews.add(r);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviews;
	}

}
