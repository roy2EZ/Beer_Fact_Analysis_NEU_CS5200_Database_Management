package beer.model;

/**
 * Beers class.
 */
public class Beers {
	protected String beerId;
	protected String beerName;
	protected String beerCountry;
	protected String beerState;
	protected double beerABV;
	protected Breweries breweries;
	
	public Beers(String beerId, String beerName, String beerCountry, String beerState, double beerABV,
			Breweries breweries) {
		this.beerId = beerId;
		this.beerName = beerName;
		this.beerCountry = beerCountry;
		this.beerState = beerState;
		this.beerABV = beerABV;
		this.breweries = breweries;
	}
	
	public Beers(String beerName, String beerCountry, String beerState, double beerABV,
			Breweries breweries) {
		this.beerName = beerName;
		this.beerCountry = beerCountry;
		this.beerState = beerState;
		this.beerABV = beerABV;
		this.breweries = breweries;
	}

	public Beers(String beerId) {
		this.beerId = beerId;
	}

	
	public String getBeerId() {
		return beerId;
	}

	public void setBeerId(String beerId) {
		this.beerId = beerId;
	}
	
	public String getBeerName() {
		return beerName;
	}

	public void setBeerName(String beerName) {
		this.beerName = beerName;
	}

	public String getBeerCountry() {
		return beerCountry;
	}

	public void setBeerCountry(String beerCountry) {
		this.beerCountry = beerCountry;
	}

	public String getBeerState() {
		return beerState;
	}

	public void setBeerState(String beerState) {
		this.beerState = beerState;
	}

	public double getBeerABV() {
		return beerABV;
	}

	public void setBeerABV(double beerABV) {
		this.beerABV = beerABV;
	}

	public Breweries getBrewery() {
		return this.breweries;
	}

	public void setBreweryId(Breweries breweries) {
		this.breweries = breweries;
	}
}
