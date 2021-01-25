package beer.model;

/**
 * Smells class.
 */
public class Smells {
	protected int smellId;
	protected String smellScore;
	protected Users user;
	protected Beers beer;
	
	public Smells(int smellId, String smellScore, Users user, Beers beer) {
		this.smellId = smellId;
		this.smellScore = smellScore;
		this.user = user;
		this.beer = beer;
	}
	
	public Smells(int smellId) {
		this.smellId = smellId;
	}
	
	public Smells(String smellScore, Users user, Beers beer) {
		this.smellScore = smellScore;
		this.user = user;
		this.beer = beer;
	}

	public int getSmellId() {
		return smellId;
	}

	public void setSmellId(int smellId) {
		this.smellId = smellId;
	}

	public String getSmellScore() {
		return smellScore;
	}

	public void setSmellScore(String smellScore) {
		this.smellScore = smellScore;
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
