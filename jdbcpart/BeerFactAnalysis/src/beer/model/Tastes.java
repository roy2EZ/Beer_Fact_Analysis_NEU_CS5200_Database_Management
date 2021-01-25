package beer.model;

/**
 * Tastes class.
 */
public class Tastes {
	protected int tasteId;
	protected String tasteScore;
	protected Users user;
	protected Beers beer;
	
	public Tastes(int tasteId, String tasteScore, Users user, Beers beer) {
		this.tasteId = tasteId;
		this.tasteScore = tasteScore;
		this.user = user;
		this.beer = beer;
	}
	
	public Tastes(int tasteId) {
		this.tasteId = tasteId;
	}
	
	public Tastes(String tasteScore, Users user, Beers beer) {
		this.tasteScore = tasteScore;
		this.user = user;
		this.beer = beer;
	}

	public int getTasteId() {
		return tasteId;
	}

	public void setTasteId(int tasteId) {
		this.tasteId = tasteId;
	}

	public String getTasteScore() {
		return tasteScore;
	}

	public void setTasteScore(String tasteScore) {
		this.tasteScore = tasteScore;
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
