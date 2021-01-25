package beer.model;

/**
 * Breweries class.
 */
public class Breweries {
	protected String breweryId;
	protected String breweryName;
	protected String breweryCountry;
	protected String breweryState;
	
	public Breweries(String breweryId, String breweryName, String breweryCountry, String breweryState) {
		this.breweryId = breweryId;
		this.breweryName = breweryName;
		this.breweryCountry = breweryCountry;
		this.breweryState = breweryState;
	}
	
	public Breweries(String breweryId) {
		this.breweryId = breweryId;
	}
	
	public Breweries(String breweryName, String breweryCountry, String breweryState) {
		this.breweryName = breweryName;
		this.breweryCountry = breweryCountry;
		this.breweryState = breweryState;
	}

	public String getBreweryId() {
		return breweryId;
	}
	
	public void setBreweryId(String breweryId) {
		this.breweryId = breweryId;
	}
	
	public String getBreweryName() {
		return breweryName;
	}
	
	public void setBreweryName(String breweryName) {
		this.breweryName = breweryName;
	}
	
	public String getBreweryCountry() {
		return breweryCountry;
	}
	
	public void setBreweryCountry(String breweryCountry) {
		this.breweryCountry = breweryCountry;
	}
	
	public String getBreweryState() {
		return breweryState;
	}
	
	public void setBreweryState(String breweryState) {
		this.breweryState = breweryState;
	}
}
