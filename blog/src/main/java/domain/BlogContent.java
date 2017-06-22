package domain;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BlogContent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1142687452489786875L;
	
	private int id;
	private String blogTitle;
	private String blogCategory;
	private int recallNums;
	private String blogText;
	private Date time;
	private String blogPicture;
	@JsonIgnore
	private int userId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(String blogCategory) {
		this.blogCategory = blogCategory;
	}
	public int getRecallNums() {
		return recallNums;
	}
	public void setRecallNums(int recallNums) {
		this.recallNums = recallNums;
	}
	public String getBlogText() {
		return blogText;
	}
	public void setBlogText(String blogText) {
		this.blogText = blogText;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getBlogPicture() {
		return blogPicture;
	}
	public void setBlogPicture(String blogPicture) {
		this.blogPicture = blogPicture;
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
