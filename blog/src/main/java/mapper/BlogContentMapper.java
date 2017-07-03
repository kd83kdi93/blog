package mapper;

import java.util.List;

import domain.BlogContent;

public interface BlogContentMapper {
	List<BlogContent> getLastPostByUserId(int userId);
	List<String> getCategoryByUserId(int userId);
	List<BlogContent> getBlogContentByPage(int userId, int pageStartNum);
	int getMaxPageNum(int userId);
	BlogContent getBlogContent(int blogId);
	void add(BlogContent blogContent);
}
