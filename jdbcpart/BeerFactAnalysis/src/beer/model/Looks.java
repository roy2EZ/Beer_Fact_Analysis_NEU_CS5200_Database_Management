package beer.model;

/**
 * Looks class.
 */
public class Looks {
	protected int lookId;
	protected String lookScore;
	protected Users user;
	protected Beers beer;
	
	public Looks(int lookId, String lookScore, Users user, Beers beer) {
		this.lookId = lookId;
		this.lookScore = lookScore;
		this.user = user;
		this.beer = beer;
	}
	
	public Looks(int lookId) {
		this.lookId = lookId;
	}
	
	public Looks(String lookScore, Users user, Beers beer) {
		this.lookScore = lookScore;
		this.user = user;
		this.beer = beer;
	}

	public int getLookId() {
		return lookId;
	}

	public void setLookId(int lookId) {
		this.lookId = lookId;
	}

	public String getLookScore() {
		return lookScore;
	}

	public void setLookScore(String lookScore) {
		this.lookScore = lookScore;
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
