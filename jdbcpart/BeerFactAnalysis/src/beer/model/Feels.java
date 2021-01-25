package beer.model;

/**
 * Feels class.
 */
public class Feels {
	protected int feelId;
	protected String feelScore;
	protected Users user;
	protected Beers beer;
	
	public Feels(int feelId, String feelScore, Users user, Beers beer) {
		this.feelId = feelId;
		this.feelScore = feelScore;
		this.user = user;
		this.beer = beer;
	}
	
	public Feels(int feelId) {
		this.feelId = feelId;
	}
	
	public Feels(String feelScore, Users user, Beers beer) {
		this.feelScore = feelScore;
		this.user = user;
		this.beer = beer;
	}

	public int getFeelId() {
		return feelId;
	}

	public void setFeelId(int feelId) {
		this.feelId = feelId;
	}

	public String getFeelScore() {
		return feelScore;
	}

	public void setFeelScore(String feelScore) {
		this.feelScore = feelScore;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public Beers getBeer() {
		return beer;
	}

	public void setBeer(Beers beer) {
		this.beer = beer;
	}
}
