package service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import domain.BlogContent;
import dto.BlogIndexDto;
import dto.BlogPersonInfoDto;
import dto.BlogPostDto;
import dto.Family;

public interface BlogService {
	void initBlogUserInfo(int userId);
	void setBlogUserInfo(int userId, String userIcon, String description);
	BlogIndexDto getIndexPageInfoByUserId(int userId);
	List<BlogContent> getBlogContentByPage(int userId, int pageNum);
	BlogPostDto getBlog(int blogId);
	BlogPersonInfoDto changeBlogUserInfo(String name, MultipartFile file, String description);
	BlogPersonInfoDto getBlogUser(String name);
	List<Family> getFamily();
}
