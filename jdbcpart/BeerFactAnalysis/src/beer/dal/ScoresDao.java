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
 * ScoresDao class.
 */
public class ScoresDao {
	protected ConnectionManager connectionManager;
	private static ScoresDao instance = null;
	
	protected ScoresDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ScoresDao getInstance() {
		if (instance == null) {
			instance = new ScoresDao();
		}
		return instance;
	}
	
	public Scores create(Scores score) throws SQLException {
		String insertScore =
			"INSERT INTO Scores(Score,UserName,BeerId) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertScore,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, score.getScore());
			insertStmt.setString(2, score.getUser().getUserName());
			insertStmt.setString(3, score.getBeer().getBeerId());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int scoreId = -1;
			if (resultKey.next()) {
				scoreId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			score.setScoreId(scoreId);
			return score;
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
	
	public Scores getScoreById(int scoreId) throws SQLException {
		String selectScore =
			"SELECT ScoreId,Score,UserName,BeerId " 
			+ "FROM Scores WHERE ScoreId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectScore);
			selectStmt.setInt(1, scoreId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			if (results.next()) {
				int resultScoreId = results.getInt("ScoreId");
				String score = results.getString("Score");
				String userName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Scores scoreNew = new Scores(resultScoreId, score, user, beer);
				return scoreNew;
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
	
	public List<Scores> getScoreByUserName(String userName) throws SQLException {
		List<Scores> scores = new ArrayList<Scores>();
		String selectScores =
			"SELECT ScoreId,Score,UserName,BeerId " 
				+ "FROM Scores WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectScores);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int scoreId = results.getInt("ScoreId");
				String score = results.getString("Score");
				String resultUserName = results.getString("UserName");
				String beerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(resultUserName);
				Beers beer = beersDao.getBeerByBeerId(beerId);
				Scores scoreNew = new Scores(scoreId, score, user, beer);
				scores.add(scoreNew);
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
		return scores;
	}
	
	public List<Scores> getScoreByBeerId(String beerId) throws SQLException {
		List<Scores> scores = new ArrayList<Scores>();
		String selectScores =
			"SELECT ScoreId,Score,UserName,BeerId " 
				+ "FROM Scores WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectScores);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			UsersDao usersDao = UsersDao.getInstance();
			BeersDao beersDao = BeersDao.getInstance();
			while (results.next()) {
				int scoreId = results.getInt("ScoreId");
				String score = results.getString("Score");
				String userName = results.getString("UserName");
				String resultBeerId = results.getString("BeerId");
				
				Users user = usersDao.getUserByUserName(userName);
				Beers beer = beersDao.getBeerByBeerId(resultBeerId);
				Scores scoreNew = new Scores(scoreId, score, user, beer);
				scores.add(scoreNew);
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
		return scores;
	}
	
	public Scores updateScore(Scores score, String newScore) throws SQLException {
		String updatePerson = "UPDATE Scores SET Score=? WHERE ScoreId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePerson);
			updateStmt.setString(1, newScore);
			updateStmt.setInt(2, score.getScoreId());
			updateStmt.executeUpdate();
			
			score.setScore(newScore);
			return score;
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
	
	public Scores delete(Scores score) throws SQLException {
		String deleteScore = "DELETE FROM Scores WHERE ScoreId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteScore);
			deleteStmt.setInt(1, score.getScoreId());
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
