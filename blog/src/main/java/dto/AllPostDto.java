package dto;

import java.io.Serializable;
import java.util.List;

import domain.BlogContent;
import domain.User;

public class AllPostDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4638233041165120248L;
	private List<String> categories;
	private int maxPageNum;
	private List<BlogContent> blogContents;
	private User user;
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	public List<BlogContent> getBlogContents() {
		return blogContents;
	}
	public void setBlogContents(List<BlogContent> blogContents) {
		this.blogContents = blogContents;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
