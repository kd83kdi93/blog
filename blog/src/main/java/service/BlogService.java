package service;

import java.util.List;

import domain.BlogContent;
import dto.BlogIndexDto;

public interface BlogService {
	void initBlogUserInfo(int userId);
	void setBlogUserInfo(int userId, String userIcon, String description);
	BlogIndexDto getIndexPageInfoByUserId(int userId);
	List<BlogContent> getBlogContentByPage(int userId, int pageNum);
}
