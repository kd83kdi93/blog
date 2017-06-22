package serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.BlogContent;
import domain.BlogUser;
import domain.User;
import dto.BlogIndexDto;
import mapper.BlogContentMapper;
import mapper.BlogUserMapper;
import mapper.UserMapper;
import service.BlogService;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BlogUserMapper blogUserMapper;
	
	@Autowired
	private BlogContentMapper blogContentMapper;

	@Override
	public void initBlogUserInfo(int userId) {
		setBlogUserInfo(userId, "images/blogapp/author.jpg", "这个家伙很懒什么也没写");

	}

	@Override
	public void setBlogUserInfo(int userId, String userIcon, String description) {
		BlogUser blogUser = new BlogUser();
		blogUser.setUserId(userId);
		blogUser.setUserIcon(userIcon);
		blogUser.setUserDescription(description);
		blogUserMapper.add(blogUser);

	}

	@Override
	public BlogIndexDto getIndexPageInfoByUserId(int userId) {
		String userName = userMapper.getNameById(userId);
		List<BlogContent> blogContents = blogContentMapper.getLastPostByUserId(userId);
		List<String> categories = blogContentMapper.getCategoryByUserId(userId);
		int maxPageNum = blogContentMapper.getMaxPageNum(userId);
		BlogUser blogUser = blogUserMapper.getByUserId(userId);
		maxPageNum = getMaxPageNum(maxPageNum);
		BlogIndexDto blogIndexDto = new BlogIndexDto();
		blogIndexDto.setCategories(categories);
		blogIndexDto.setBlogUser(blogUser);
		blogIndexDto.setBlogContents(blogContents);
		blogIndexDto.setUserName(userName);
		blogIndexDto.setMaxPageNum(maxPageNum);
		return blogIndexDto;
	}

	public int getMaxPageNum(int maxPageNum) {
		if (maxPageNum % 5 == 0) {
			maxPageNum = maxPageNum / 5;
		} else {
			maxPageNum = maxPageNum / 5 + 1;
		}
		return maxPageNum;
	}

	@Override
	public List<BlogContent> getBlogContentByPage(int userId, int pageNum) {
		int pageStartNum = pageNum * 5;
		List<BlogContent> blogContents = blogContentMapper.getBlogContentByPage(userId, pageStartNum);
		return blogContents;
	}

}
