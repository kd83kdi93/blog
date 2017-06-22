package domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BlogUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360025135274992545L;
	private int id;
	private String userIcon;
	private String userDescription;
	@JsonIgnore
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getUserDescription() {
		return userDescription;
	}
	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
