package dto;

import java.io.Serializable;

public class BlogPersonInfoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3610771553293816665L;
	private String userIcon;
	private String description;
	public String getUserIcon() {
		return userIcon;
	}
	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
