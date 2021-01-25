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
 * LooksDao class.
 */
public class LooksDao {
	protected ConnectionManager connectionManager;
	private static LooksDao instance = null;
	
	protected LooksDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LooksDao getInstance() {
		if (instance == null) {
			instance = new LooksDao();
		}
		return instance;
	}
	
	public Looks create(Looks look) throws SQLException {
		String insertLook =
			"INSERT INTO Looks(LookScore,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertLook,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, look.getLookScore());
			insertStmt.setString(2, look.getUser().getUserName());
			insertStmt.setString(3, look.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int lookId = -1;
			if (resultKey.next()) {
				lookId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			look.setLookId(lookId);
			return look;
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
	
	public Looks getLookById(int lookId) throws SQLException {
		String selectLook =
			"SELECT LookId,LookScore,UserName,BeerId " 
			+ "FROM Looks WHERE LookId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLook);
			selectStmt.setInt(1, lookId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultLookId = results.getInt("LookId");
				String lookScore = results.getString("LookScore");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Looks look = new Looks(resultLookId, lookScore, user, beer);
				return look;
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
	
	public List<Looks> getLookByUserName(String userName) throws SQLException {
		List<Looks> looks = new ArrayList<Looks>();
		String selectLooks =
			"SELECT LookId,LookScore,UserName,BeerId " 
				+ "FROM Looks WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLooks);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int lookId = results.getInt("LookId");
				String lookScore = results.getString("LookScore");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Looks look = new Looks(lookId, lookScore, user, beer);
				looks.add(look);
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
		return looks;
	}
	
	public List<Looks> getLookByBeerId(String beerId) throws SQLException {
		List<Looks> looks = new ArrayList<Looks>();
		String selectLooks =
			"SELECT LookId,LookScore,UserName,BeerId " 
				+ "FROM Looks WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectLooks);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int lookId = results.getInt("LookId");
				String lookScore = results.getString("LookScore");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Looks look = new Looks(lookId, lookScore, user, beer);
				looks.add(look);
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
		return looks;
	}
	
	public Looks updateLookScore(Looks look, String newLookScore) throws SQLException {
		String updatePerson = "UPDATE Looks SET LookScore=? WHERE LookId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newLookScore);
			updateStmt.setInt(2, look.getLookId());
			updateStmt.executeUpdate();
			
			look.setLookScore(newLookScore);
			return look;
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
	
	public Looks delete(Looks look) throws SQLException {
		String deleteLook = "DELETE FROM Looks WHERE LookId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteLook);
			deleteStmt.setInt(1, look.getLookId());
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
