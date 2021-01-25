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
 * FeelsDao class.
 */
public class FeelsDao {
	protected ConnectionManager connectionManager;
	private static FeelsDao instance = null;
	
	protected FeelsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static FeelsDao getInstance() {
		if (instance == null) {
			instance = new FeelsDao();
		}
		return instance;
	}
	
	public Feels create(Feels feel) throws SQLException {
		String insertFeel =
			"INSERT INTO Feels(FeelScore,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertFeel,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, feel.getFeelScore());
			insertStmt.setString(2, feel.getUser().getUserName());
			insertStmt.setString(3, feel.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int feelId = -1;
			if (resultKey.next()) {
				feelId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			feel.setFeelId(feelId);
			return feel;
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
	
	public Feels getFeelById(int feelId) throws SQLException {
		String selectFeel =
			"SELECT FeelId,FeelScore,UserName,BeerId " 
			+ "FROM Feels WHERE FeelId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFeel);
			selectStmt.setInt(1, feelId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultFeelId = results.getInt("FeelId");
				String feelScore = results.getString("FeelScore");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Feels feel = new Feels(resultFeelId, feelScore, user, beer);
				return feel;
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
	
	public List<Feels> getFeelByUserName(String userName) throws SQLException {
		List<Feels> feels = new ArrayList<Feels>();
		String selectFeels =
			"SELECT FeelId,FeelScore,UserName,BeerId " 
				+ "FROM Feels WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFeels);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int feelId = results.getInt("FeelId");
				String feelScore = results.getString("FeelScore");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Feels feel = new Feels(feelId, feelScore, user, beer);
				feels.add(feel);
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
		return feels;
	}
	
	public List<Feels> getFeelByBeerId(String beerId) throws SQLException {
		List<Feels> feels = new ArrayList<Feels>();
		String selectFeels =
			"SELECT FeelId,FeelScore,UserName,BeerId " 
				+ "FROM Feels WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectFeels);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int feelId = results.getInt("FeelId");
				String feelScore = results.getString("FeelScore");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Feels feel = new Feels(feelId, feelScore, user, beer);
				feels.add(feel);
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
		return feels;
	}
	
	public Feels updateFeelScore(Feels feel, String newFeelScore) throws SQLException {
		String updatePerson = "UPDATE Feels SET FeelScore=? WHERE FeelId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newFeelScore);
			updateStmt.setInt(2, feel.getFeelId());
			updateStmt.executeUpdate();
			
			feel.setFeelScore(newFeelScore);
			return feel;
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
	
	public Feels delete(Feels feel) throws SQLException {
		String deleteFeel = "DELETE FROM Feels WHERE FeelId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteFeel);
			deleteStmt.setInt(1, feel.getFeelId());
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
