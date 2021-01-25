package beer.dal;

import beer.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BreweriesDao class.
 */
public class BreweriesDao {
	protected ConnectionManager connectionManager;
	private static BreweriesDao instance = null;
	
	protected BreweriesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static BreweriesDao getInstance() {
		if (instance == null) {
			instance = new BreweriesDao();
		}
		return instance;
	}
	
	public Breweries create(Breweries brewery) throws SQLException {
		String insertBrewery =
			"INSERT INTO Breweries(BreweryId,BreweryName,BreweryCountry,BreweryState) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBrewery);
			insertStmt.setString(1, brewery.getBreweryId());
			insertStmt.setString(2, brewery.getBreweryName());
			insertStmt.setString(3, brewery.getBreweryCountry());
			insertStmt.setString(4, brewery.getBreweryState());
			insertStmt.executeUpdate();
			return brewery;
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
		}
	}
	
	public Breweries getBreweryById(String breweryId) throws SQLException {
		String selectBrewery =
			"SELECT BreweryId,BreweryName,BreweryCountry,BreweryState " 
			+ "FROM Breweries WHERE BreweryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBrewery);
			selectStmt.setString(1, breweryId);
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultBreweryId = results.getString("BreweryId");
				String breweryName = results.getString("BreweryName");
				String breweryCountry = results.getString("BreweryCountry");
				String breweryState = results.getString("BreweryState");
				
				Breweries brewery = new Breweries(resultBreweryId, breweryName, breweryCountry, breweryState);
				return brewery;
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
	
	public Breweries getBreweryByName(String breweryName) throws SQLException {
		String selectBreweries =
			"SELECT BreweryId,BreweryName,BreweryCountry,BreweryState " 
				+ "FROM Breweries WHERE BreweryName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBreweries);
			selectStmt.setString(1, breweryName);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String breweryId = results.getString("BreweryId");
				String resultBreweryName = results.getString("BreweryName");
				String breweryCountry = results.getString("BreweryCountry");
				String breweryState = results.getString("BreweryState");
				
				Breweries brewery = new Breweries(breweryId, resultBreweryName, breweryCountry, breweryState);
				return brewery;
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
	
	public List<Breweries> getBreweryByBreweryCountry(String breweryCountry) throws SQLException {
		List<Breweries> breweries = new ArrayList<Breweries>();
		String selectBreweries =
			"SELECT BreweryId,BreweryName,BreweryCountry,BreweryState " 
				+ "FROM Breweries WHERE BreweryCountry=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBreweries);
			selectStmt.setString(1, breweryCountry);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String breweryId = results.getString("BreweryId");
				String breweryName = results.getString("BreweryName");
				String resultBreweryCountry = results.getString("BreweryCountry");
				String breweryState = results.getString("BreweryState");
				
				Breweries brewery = new Breweries(breweryId, breweryName, resultBreweryCountry, breweryState);
				breweries.add(brewery);
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
		return breweries;
	}
	
	public List<Breweries> getBreweryByBreweryState(String BreweryState) throws SQLException {
		List<Breweries> breweries = new ArrayList<Breweries>();
		String selectBreweries =
			"SELECT BreweryId,BreweryName,BreweryCountry,BreweryState " 
				+ "FROM Breweries WHERE BreweryState=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBreweries);
			selectStmt.setString(1, BreweryState);
			results = selectStmt.executeQuery();
			while (results.next()) {
				String breweryId = results.getString("BreweryId");
				String breweryName = results.getString("BreweryName");
				String breweryCountry = results.getString("BreweryCountry");
				String resultBreweryState = results.getString("BreweryState");
				
				Breweries brewery = new Breweries(breweryId, breweryName, breweryCountry, resultBreweryState);
				breweries.add(brewery);
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
		return breweries;
	}
	
	public Breweries updateBreweries(Breweries brewery, String newBreweryName, String newBreweryCountry, 
		String newBreweryState) throws SQLException {
		String updateBrewery = "UPDATE Breweries SET BreweryName=?, BreweryCountry=?, BreweryState=? "
			+ "WHERE BreweryId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBrewery);
			updateStmt.setString(1, newBreweryName);
			updateStmt.setString(2, newBreweryCountry);
			updateStmt.setString(3, newBreweryState);
			updateStmt.setString(4, brewery.getBreweryId());
			updateStmt.executeUpdate();
			
			brewery.setBreweryName(newBreweryName);
			brewery.setBreweryCountry(newBreweryCountry);
			brewery.setBreweryState(newBreweryState);
			return brewery;
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
	
	public Breweries delete(Breweries brewery) throws SQLException {
		String deleteBrewery = "DELETE FROM Breweries WHERE BreweryId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBrewery);
			deleteStmt.setString(1, brewery.getBreweryId());
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