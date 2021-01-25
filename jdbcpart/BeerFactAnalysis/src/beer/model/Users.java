package beer.model;

/**
 * Users class.
 */
public class Users {
	protected String userName;
	protected int userId;
	
	
	public Users(String userName, int userId) {
		this.userName = userName;
		this.userId = userId;
	}
	
	public Users(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
