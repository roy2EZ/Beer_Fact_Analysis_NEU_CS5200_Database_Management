package beer.dal;

import beer.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * OverallsDao class.
 */
public class OverallsDao {
	protected ConnectionManager connectionManager;
	private static OverallsDao instance = null;
	
	protected OverallsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static OverallsDao getInstance() {
		if (instance == null) {
			instance = new OverallsDao();
		}
		return instance;
	}
	
	public Overalls create(Overalls overall) throws SQLException {
		String insertOverall =
			"INSERT INTO Overalls(OverallScore,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertOverall,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, overall.getOverallScore());
			insertStmt.setString(2, overall.getUser().getUserName());
			insertStmt.setString(3, overall.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int overallId = -1;
			if (resultKey.next()) {
				overallId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			overall.setOverallId(overallId);
			return overall;
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
	
	public Overalls getOverallById(int overallId) throws SQLException {
		String selectOverall =
			"SELECT OverallId,OverallScore,UserName,BeerId " 
			+ "FROM Overalls WHERE OverallId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOverall);
			selectStmt.setInt(1, overallId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultOverallId = results.getInt("OverallId");
				String overallScore = results.getString("OverallScore");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Overalls overall = new Overalls(resultOverallId, overallScore, user, beer);
				return overall;
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
	
	public List<Overalls> getOverallByUserName(String userName) throws SQLException {
		List<Overalls> overalls = new ArrayList<Overalls>();
		String selectOveralls =
			"SELECT OverallId,OverallScore,UserName,BeerId " 
				+ "FROM Overalls WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOveralls);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int overallId = results.getInt("OverallId");
				String overallScore = results.getString("OverallScore");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Overalls overall = new Overalls(overallId, overallScore, user, beer);
				overalls.add(overall);
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
		return overalls;
	}
	
	public List<Overalls> getOverallByBeerId(String beerId) throws SQLException {
		List<Overalls> overalls = new ArrayList<Overalls>();
		String selectOveralls =
			"SELECT OverallId,OverallScore,UserName,BeerId " 
				+ "FROM Overalls WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectOveralls);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int overallId = results.getInt("OverallId");
				String overallScore = results.getString("OverallScore");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Overalls overall = new Overalls(overallId, overallScore, user, beer);
				overalls.add(overall);
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
		return overalls;
	}
	
	public Overalls updateOverallScore(Overalls overall, String newOverallScore) throws SQLException {
		String updatePerson = "UPDATE Overalls SET OverallScore=? WHERE OverallId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newOverallScore);
			updateStmt.setInt(2, overall.getOverallId());
			updateStmt.executeUpdate();
			
			overall.setOverallScore(newOverallScore);
			return overall;
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
	
	public Overalls delete(Overalls overall) throws SQLException {
		String deleteOverall = "DELETE FROM Overalls WHERE OverallId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteOverall);
			deleteStmt.setInt(1, overall.getOverallId());
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
