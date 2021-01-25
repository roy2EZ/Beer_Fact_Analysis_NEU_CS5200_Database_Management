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
 * TastesDao class.
 */
public class TastesDao {
	protected ConnectionManager connectionManager;
	private static TastesDao instance = null;
	
	protected TastesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static TastesDao getInstance() {
		if (instance == null) {
			instance = new TastesDao();
		}
		return instance;
	}
	
	public Tastes create(Tastes taste) throws SQLException {
		String insertTaste =
			"INSERT INTO Tastes(TasteScore,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertTaste,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, taste.getTasteScore());
			insertStmt.setString(2, taste.getUser().getUserName());
			insertStmt.setString(3, taste.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int tasteId = -1;
			if (resultKey.next()) {
				tasteId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			taste.setTasteId(tasteId);
			return taste;
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
	
	public Tastes getTasteById(int tasteId) throws SQLException {
		String selectTaste =
			"SELECT TasteId,TasteScore,UserName,BeerId " 
			+ "FROM Tastes WHERE TasteId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTaste);
			selectStmt.setInt(1, tasteId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultTasteId = results.getInt("TasteId");
				String tasteScore = results.getString("TasteScore");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Tastes taste = new Tastes(resultTasteId, tasteScore, user, beer);
				return taste;
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
	
	public List<Tastes> getTasteByUserName(String userName) throws SQLException {
		List<Tastes> tastes = new ArrayList<Tastes>();
		String selectTastes =
			"SELECT TasteId,TasteScore,UserName,BeerId " 
				+ "FROM Tastes WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTastes);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int tasteId = results.getInt("TasteId");
				String tasteScore = results.getString("TasteScore");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Tastes taste = new Tastes(tasteId, tasteScore, user, beer);
				tastes.add(taste);
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
		return tastes;
	}
	
	public List<Tastes> getTasteByBeerId(String beerId) throws SQLException {
		List<Tastes> tastes = new ArrayList<Tastes>();
		String selectTastes =
			"SELECT TasteId,TasteScore,UserName,BeerId " 
				+ "FROM Tastes WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectTastes);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int tasteId = results.getInt("TasteId");
				String tasteScore = results.getString("TasteScore");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Tastes taste = new Tastes(tasteId, tasteScore, user, beer);
				tastes.add(taste);
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
		return tastes;
	}
	
	public Tastes updateTasteScore(Tastes taste, String newTasteScore) throws SQLException {
		String updatePerson = "UPDATE Tastes SET TasteScore=? WHERE TasteId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newTasteScore);
			updateStmt.setInt(2, taste.getTasteId());
			updateStmt.executeUpdate();
			
			taste.setTasteScore(newTasteScore);
			return taste;
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
	
	public Tastes delete(Tastes taste) throws SQLException {
		String deleteTaste = "DELETE FROM Tastes WHERE TasteId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteTaste);
			deleteStmt.setInt(1, taste.getTasteId());
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
