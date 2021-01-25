package beer.model;

/**
 * Overalls class.
 */
public class Overalls {
	protected int overallId;
	protected String overallScore;
	protected Users user;
	protected Beers beer;
	
	public Overalls(int overallId, String overallScore, Users user, Beers beer) {
		this.overallId = overallId;
		this.overallScore = overallScore;
		this.user = user;
		this.beer = beer;
	}
	
	public Overalls(int overallId) {
		this.overallId = overallId;
	}
	
	public Overalls(String overallScore, Users user, Beers beer) {
		this.overallScore = overallScore;
		this.user = user;
		this.beer = beer;
	}

	public int getOverallId() {
		return overallId;
	}

	public void setOverallId(int overallId) {
		this.overallId = overallId;
	}

	public String getOverallScore() {
		return overallScore;
	}

	public void setOverallScore(String overallScore) {
		this.overallScore = overallScore;
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
