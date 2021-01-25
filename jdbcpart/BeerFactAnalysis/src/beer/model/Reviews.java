package beer.model;

import java.util.Date;

/**
 * Reviews class.
 */
public class Reviews {
	protected int reviewId;
	protected Date reviewDate;
	protected String reviewText;
	protected Beers beer;
	protected Users user;
	
	public Reviews(int reviewId, Date reviewDate, String reviewText, Beers beer, Users user) {
		this.reviewId = reviewId;
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		this.beer = beer;
		this.user = user;
	}
	
	public Reviews(int reviewId) {
		this.reviewId = reviewId;
	}

	public Reviews(Date reviewDate, String reviewText, Beers beer, Users user) {
		this.reviewDate = reviewDate;
		this.reviewText = reviewText;
		this.beer = beer;
		this.user = user;
	}

	public int getReviewId() {
		return reviewId;
	}
	
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	
	public Date getReviewDate() {
		return reviewDate;
	}
	
	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	public String getReviewText() {
		return reviewText;
	}
	
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	public Beers getBeer() {
		return beer;
	}
	
	public void setBeer(Beers beer) {
		this.beer = beer;
	}
	
	public Users getUser() {
		return user;
	}
	
	public void setUserName(Users user) {
		this.user = user;
	}
	
	
}
