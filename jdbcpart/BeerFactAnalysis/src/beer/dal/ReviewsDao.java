package beer.dal;

import beer.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ReviewsDao class.
 */
public class ReviewsDao {
	protected ConnectionManager connectionManager;
	private static ReviewsDao instance = null;
	
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReviewsDao getInstance() {
		if (instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}

	public Reviews create(Reviews review) throws SQLException {
		String insertReview = 
			"INSERT INTO Reviews(ReviewDate,ReviewText,BeerId,UserName) "
			+ "VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setTimestamp(1, new Timestamp(review.getReviewDate().getTime()));
			insertStmt.setString(2, review.getReviewText());
			insertStmt.setString(3, review.getBeer().getBeerId());
			insertStmt.setString(4, review.getUser().getUserName());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int ReviewId = -1;
			if (resultKey.next()) {
				ReviewId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			review.setReviewId(ReviewId);
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (insertStmt != null) {
				insertStmt.close();
			}
			if (resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public Reviews getReviewByReviewId(int reviewId) throws SQLException {
		String selectReview = 
			"SELECT ReviewId,ReviewDate,ReviewText,BeerId,UserName "
			+ "FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReview);
			selectStmt.setInt(1, reviewId);
			results = selectStmt.executeQuery();
			BeersDao beersDao = BeersDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			if (results.next()) {
				int resultReviewId = results.getInt("ReviewId");
				Date reviewDate = new Date(results.getTimestamp("ReviewDate").getTime());
				String reviewText = results.getString("ReviewText");
				String beerId = results.getString("BeerId");
				String userName = results.getString("UserName");
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Users user = usersDao.getUserByUserName(userName);
				Reviews review = new Reviews(resultReviewId, reviewDate, reviewText, beer, user);
				return review;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return null;
	}
	
	public List<Reviews> getReviewByReviewDate(Date reviewDate) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = 
			"SELECT ReviewId,ReviewDate,ReviewText,BeerId,UserName "
			+ "FROM Reviews WHERE ReviewDate=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setTimestamp(1, new Timestamp(reviewDate.getTime()));
			results = selectStmt.executeQuery();
			BeersDao beersDao = BeersDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date resultReviewDate = new Date(results.getTimestamp("ReviewDate").getTime());
				String reviewText = results.getString("ReviewText");
				String beerId = results.getString("BeerId");
				String userName = results.getString("UserName");
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Users user = usersDao.getUserByUserName(userName);
				Reviews review = new Reviews(reviewId, resultReviewDate, reviewText, beer, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public List<Reviews> getReviewByBeerId(String beerId) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = 
			"SELECT ReviewId,ReviewDate,ReviewText,BeerId,UserName "
			+ "FROM Reviews WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			BeersDao beersDao = BeersDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date reviewDate = new Date(results.getTimestamp("ReviewDate").getTime());
				String reviewText = results.getString("ReviewText");
				String resultBeerId = results.getString("BeerId");
				String userName = results.getString("UserName");
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Users user = usersDao.getUserByUserName(userName);
				Reviews review = new Reviews(reviewId, reviewDate, reviewText, beer, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public List<Reviews> getReviewByUserName(String userName) throws SQLException {
		List<Reviews> reviews = new ArrayList<Reviews>();
		String selectReviews = 
			"SELECT ReviewId,ReviewDate,ReviewText,BeerId,UserName "
			+ "FROM Reviews WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			BeersDao beersDao = BeersDao.getInstance();
			UsersDao usersDao = UsersDao.getInstance();
			while (results.next()) {
				int reviewId = results.getInt("ReviewId");
				Date reviewDate = new Date(results.getTimestamp("ReviewDate").getTime());
				String reviewText = results.getString("ReviewText");
				String beerId = results.getString("BeerId");
				String resultUserName = results.getString("UserName");
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Users user = usersDao.getUserByUserName(resultUserName);
				Reviews review = new Reviews(reviewId, reviewDate, reviewText, beer, user);
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (selectStmt != null) {
				selectStmt.close();
			}
			if (results != null) {
				results.close();
			}
		}
		return reviews;
	}
	
	public Reviews updateReview(Reviews review, Date newReviewDate, String newReviewText) throws SQLException {
		String updateReview = "UPDATE Reviews SET ReviewDate=?, ReviewText=? WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReview);
			updateStmt.setTimestamp(1, new Timestamp(newReviewDate.getTime()));
			updateStmt.setString(2, newReviewText);
			updateStmt.setInt(3, review.getReviewId());
			updateStmt.executeUpdate();
			
			review.setReviewDate(newReviewDate);
			review.setReviewText(newReviewText);
			
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	public Reviews delete(Reviews Review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, Review.getReviewId());
			deleteStmt.executeUpdate();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (connection != null) {
				connection.close();
			}
			if (deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
}
