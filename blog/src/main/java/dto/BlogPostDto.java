package dto;

import java.io.Serializable;
import java.util.List;

import domain.BlogContent;
import domain.BlogUser;

public class BlogPostDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -145432343866418118L;
	private BlogUser blogUser;
	private List<BlogContent> featuredPosts;
	private List<String> categories;
	private BlogContent content;
	private String userName;
	public BlogUser getBlogUser() {
		return blogUser;
	}
	public void setBlogUser(BlogUser blogUser) {
		this.blogUser = blogUser;
	}
	public List<BlogContent> getFeaturedPosts() {
		return featuredPosts;
	}
	public void setFeaturedPosts(List<BlogContent> featuredPosts) {
		this.featuredPosts = featuredPosts;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public BlogContent getContent() {
		return content;
	}
	public void setContent(BlogContent content) {
		this.content = content;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
