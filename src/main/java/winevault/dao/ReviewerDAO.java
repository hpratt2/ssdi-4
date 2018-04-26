package winevault.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import winevault.model.IReviewer;
import winevault.model.Reviewer;
import winevault.util.ConnectionUtil;
import winevault.util.IConnectionData;

public class ReviewerDAO implements IReviewerDAO {
	private IConnectionData connData;
	
	public ReviewerDAO(IConnectionData connData) {
		this.connData = connData;
	}
	
	public List<IReviewer> getReviewers() {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		List<IReviewer> reviewers = new ArrayList<IReviewer>();
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.prepareStatement("SELECT * FROM users");
			rs = statement.executeQuery();
			while(rs.next()) {
				reviewers.add(new Reviewer(
						rs.getInt("id"),
						rs.getString("fullname"),
						rs.getString("username")
				));
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviewers;
	}

	public IReviewer getReviewerByID(int id) {
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		IReviewer reviewer = null;
		try {
			conn = ConnectionUtil.getConnection(connData);
			statement = conn.prepareStatement("SELECT * FROM users WHERE id = ?");
			statement.setInt(1, id);
			rs = statement.executeQuery();
			if(rs.next()) {
				reviewer = new Reviewer(
						rs.getInt("id"),
						rs.getString("fullname"),
						rs.getString("username")
				);
			}
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return reviewer;
	}

}
