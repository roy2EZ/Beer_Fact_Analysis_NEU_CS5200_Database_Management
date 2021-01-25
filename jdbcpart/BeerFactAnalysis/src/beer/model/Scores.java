package beer.model;

/**
 * Scores class.
 */
public class Scores {
	protected int scoreId;
	protected String score;
	protected Users user;
	protected Beers beer;
	
	public Scores(int scoreId, String score, Users user, Beers beer) {
		this.scoreId = scoreId;
		this.score = score;
		this.user = user;
		this.beer = beer;
	}
	
	public Scores(int scoreId) {
		this.scoreId = scoreId;
	}
	
	public Scores(String score, Users user, Beers beer) {
		this.score = score;
		this.user = user;
		this.beer = beer;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
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
