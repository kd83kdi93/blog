package dto;

import java.io.Serializable;
import java.util.List;

import domain.BlogContent;
import domain.BlogUser;

public class BlogIndexDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6049408924116203602L;
	private List<BlogContent> blogContents;
	private List<String> categories;
	private BlogUser blogUser;
	private String userName;
	private int pageNum = 0;
	private int maxPageNum = 0;
	public List<BlogContent> getBlogContents() {
		return blogContents;
	}
	public void setBlogContents(List<BlogContent> blogContents) {
		this.blogContents = blogContents;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public BlogUser getBlogUser() {
		return blogUser;
	}
	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	
	
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
