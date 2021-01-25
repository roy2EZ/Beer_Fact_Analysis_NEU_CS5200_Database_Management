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
 * SmellsDao class.
 */
public class SmellsDao {
	protected ConnectionManager connectionManager;
	private static SmellsDao instance = null;
	
	protected SmellsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static SmellsDao getInstance() {
		if (instance == null) {
			instance = new SmellsDao();
		}
		return instance;
	}
	
	public Smells create(Smells smell) throws SQLException {
		String insertSmell =
			"INSERT INTO Smells(SmellScore,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertSmell,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, smell.getSmellScore());
			insertStmt.setString(2, smell.getUser().getUserName());
			insertStmt.setString(3, smell.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int smellId = -1;
			if (resultKey.next()) {
				smellId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			smell.setSmellId(smellId);
			return smell;
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
	
	public Smells getSmellById(int smellId) throws SQLException {
		String selectSmell =
			"SELECT SmellId,SmellScore,UserName,BeerId " 
			+ "FROM Smells WHERE SmellId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSmell);
			selectStmt.setInt(1, smellId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultSmellId = results.getInt("SmellId");
				String smellScore = results.getString("SmellScore");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Smells smell = new Smells(resultSmellId, smellScore, user, beer);
				return smell;
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
	
	public List<Smells> getSmellByUserName(String userName) throws SQLException {
		List<Smells> smells = new ArrayList<Smells>();
		String selectSmells =
			"SELECT SmellId,SmellScore,UserName,BeerId " 
				+ "FROM Smells WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSmells);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int smellId = results.getInt("SmellId");
				String smellScore = results.getString("SmellScore");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Smells smell = new Smells(smellId, smellScore, user, beer);
				smells.add(smell);
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
		return smells;
	}
	
	public List<Smells> getSmellByBeerId(String beerId) throws SQLException {
		List<Smells> smells = new ArrayList<Smells>();
		String selectSmells =
			"SELECT SmellId,SmellScore,UserName,BeerId " 
				+ "FROM Smells WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectSmells);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int smellId = results.getInt("SmellId");
				String smellScore = results.getString("SmellScore");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Smells smell = new Smells(smellId, smellScore, user, beer);
				smells.add(smell);
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
		return smells;
	}
	
	public Smells updateSmellScore(Smells smell, String newSmellScore) throws SQLException {
		String updatePerson = "UPDATE Smells SET SmellScore=? WHERE SmellId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newSmellScore);
			updateStmt.setInt(2, smell.getSmellId());
			updateStmt.executeUpdate();
			
			smell.setSmellScore(newSmellScore);
			return smell;
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
	
	public Smells delete(Smells smell) throws SQLException {
		String deleteSmell = "DELETE FROM Smells WHERE SmellId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteSmell);
			deleteStmt.setInt(1, smell.getSmellId());
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
