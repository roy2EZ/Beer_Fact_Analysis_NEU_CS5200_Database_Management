package beer.dal;

import beer.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BeersDao class.
 */
public class BeersDao {
	protected ConnectionManager connectionManager;
	private static BeersDao instance = null;
	
	protected BeersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static BeersDao getInstance() {
		if (instance == null) {
			instance = new BeersDao();
		}
		return instance;
	}

	public Beers create(Beers beer) throws SQLException {
		String insertBeer = 
			"INSERT INTO Beers(BeerId,BeerName,BreweryId,BeerCountry,BeerState,BeerABV) "
			+ "VALUES(?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertBeer);
			insertStmt.setString(1, beer.getBeerId());
			insertStmt.setString(2, beer.getBeerName());
			insertStmt.setString(3, beer.getBrewery().getBreweryId());
			insertStmt.setString(4, beer.getBeerCountry());
			insertStmt.setString(5, beer.getBeerState());
			insertStmt.setDouble(6, beer.getBeerABV());
			
			insertStmt.executeUpdate();
			return beer;
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
	
	public Beers getBeerByBeerId(String beerId) throws SQLException {
		String selectBeer = 
			"SELECT BeerId,BeerName,BreweryId,BeerCountry,BeerState,BeerABV "
			+ "FROM Beers WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeer);
			selectStmt.setString(1, beerId);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			if (results.next()) {
				String resultBeerId = results.getString("BeerId");
				String beerName = results.getString("BeerName");
				String breweryId = results.getString("BreweryId");
				String beerCountry = results.getString("BeerCountry");
				String beerState = results.getString("BeerState");
				double beerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(breweryId);
				Beers beer = new Beers(resultBeerId, beerName, beerCountry, beerState, beerABV, brewery);
				return beer;
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
	
	public Beers getBeerByBeerName(String beerName) throws SQLException {
		String selectBeer = 
			"SELECT BeerId,BeerName,BreweryId,BeerCountry,BeerState,BeerABV "
			+ "FROM Beers WHERE BeerName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeer);
			selectStmt.setString(1, beerName);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			if (results.next()) {
				String beerId = results.getString("BeerId");
				String resultBeerName = results.getString("BeerName");
				String breweryId = results.getString("BreweryId");
				String beerCountry = results.getString("BeerCountry");
				String beerState = results.getString("BeerState");
				double beerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(breweryId);
				Beers beer = new Beers(beerId, resultBeerName, beerCountry, beerState, beerABV, brewery);
				return beer;
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
	
	public List<Beers> getBeerByBeerCountry(String beerCountry) throws SQLException {
		List<Beers> beers = new ArrayList<Beers>();
		String selectBeers = 
			"SELECT BeerId,BeerName,BeerCountry,BeerState,BeerABV,BreweryId "
			+ "FROM Beers WHERE BeerCountry=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeers);
			selectStmt.setString(1, beerCountry);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			while (results.next()) {
				String beerId = results.getString("BeerId");
				String beerName = results.getString("BeerName");
				String breweryId = results.getString("BreweryId");
				String resultBeerCountry = results.getString("BeerCountry");
				String beerState = results.getString("BeerState");
				double beerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(breweryId);
				Beers beer = new Beers(beerId, beerName, resultBeerCountry, beerState, beerABV, brewery);
				beers.add(beer);
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
		return beers;
	}
	
	public List<Beers> getBeerByBeerState(String beerState) throws SQLException {
		List<Beers> beers = new ArrayList<Beers>();
		String selectBeers = 
			"SELECT BeerId,BeerName,BeerCountry,BeerState,BeerABV,BreweryId "
			+ "FROM Beers WHERE BeerState=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeers);
			selectStmt.setString(1, beerState);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			while (results.next()) {
				String beerId = results.getString("BeerId");
				String beerName = results.getString("BeerName");
				String breweryId = results.getString("BreweryId");
				String beerCountry = results.getString("BeerCountry");
				String resultBeerState = results.getString("BeerState");
				double beerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(breweryId);
				Beers beer = new Beers(beerId, beerName, beerCountry, resultBeerState, beerABV, brewery);
				beers.add(beer);
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
		return beers;
	}
	
	public List<Beers> getBeerByBeerABV(double beerABV) throws SQLException {
		List<Beers> beers = new ArrayList<Beers>();
		String selectBeers = 
			"SELECT BeerId,BeerName,BeerCountry,BeerState,BeerABV,BreweryId "
			+ "FROM Beers WHERE BeerABV=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeers);
			selectStmt.setDouble(1, beerABV);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			while (results.next()) {
				String beerId = results.getString("BeerId");
				String beerName = results.getString("BeerName");
				String breweryId = results.getString("BreweryId");
				String beerCountry = results.getString("BeerCountry");
				String beerState = results.getString("BeerState");
				double resultBeerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(breweryId);
				Beers beer = new Beers(beerId, beerName, beerCountry, beerState, resultBeerABV, brewery);
				beers.add(beer);
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
		return beers;
	}
	
	public List<Beers> getBeerByBreweryId(String breweryId) throws SQLException {
		List<Beers> beers = new ArrayList<Beers>();
		String selectBeers = 
			"SELECT BeerId,BeerName,BeerCountry,BeerState,BeerABV,BreweryId "
			+ "FROM Beers WHERE BreweryId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectBeers);
			selectStmt.setString(1, breweryId);
			results = selectStmt.executeQuery();
			BreweriesDao breweriesDao = BreweriesDao.getInstance();
			while (results.next()) {
				String beerId = results.getString("BeerId");
				String beerName = results.getString("BeerName");
				String resultBreweryId = results.getString("BreweryId");
				String beerCountry = results.getString("BeerCountry");
				String beerState = results.getString("BeerState");
				double beerABV = results.getDouble("BeerABV");
				Breweries brewery = breweriesDao.getBreweryById(resultBreweryId);
				Beers beer = new Beers(beerId, beerName, beerCountry, beerState, beerABV, brewery);
				beers.add(beer);
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
		return beers;
	}
	
	public Beers updateBeer(Beers beer, String newBeerName, String newBeerCountry, String newBeerState, 
		double newBeerABV) throws SQLException {
		String updateBeer = "UPDATE Beers SET BeerName=?, BeerCountry=?, BeerState=?, BeerABV=? WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateBeer);
			updateStmt.setString(1, newBeerName);
			updateStmt.setString(2, newBeerCountry);
			updateStmt.setString(3, newBeerState);
			updateStmt.setDouble(4, newBeerABV);
			updateStmt.setString(5, beer.getBeerId());
			updateStmt.executeUpdate();
			
			beer.setBeerName(newBeerName);
			beer.setBeerCountry(newBeerCountry);
			beer.setBeerState(newBeerState);
			beer.setBeerABV(newBeerABV);
			
			return beer;
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
	
	public Beers delete(Beers Beer) throws SQLException {
		String deleteBeer = "DELETE FROM Beers WHERE BeerId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBeer);
			deleteStmt.setString(1, Beer.getBeerId());
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
