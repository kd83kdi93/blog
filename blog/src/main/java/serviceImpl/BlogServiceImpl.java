package serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import domain.BlogContent;
import domain.BlogUser;
import domain.User;
import dto.BlogIndexDto;
import dto.BlogPersonInfoDto;
import dto.BlogPostDto;
import mapper.BlogContentMapper;
import mapper.BlogUserMapper;
import mapper.UserMapper;
import responsestring.DefaultMessage;
import service.BlogService;
import util.ProClass;

@Service
public class BlogServiceImpl implements BlogService {
	@Autowired
	private ProClass proClass;

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

	@Override
	public BlogPostDto getBlog(int blogId) {
		BlogContent blogContent = blogContentMapper.getBlogContent(blogId);
		int userId = blogContent.getUserId();
		String userName = userMapper.getNameById(userId);
		List<BlogContent> blogContents = blogContentMapper.getLastPostByUserId(userId);
		List<String> categories = blogContentMapper.getCategoryByUserId(userId);
		BlogUser blogUser = blogUserMapper.getByUserId(userId);
		BlogPostDto blogPostDto = new BlogPostDto();
		blogPostDto.setBlogUser(blogUser);
		blogPostDto.setCategories(categories);
		blogPostDto.setContent(blogContent);
		blogPostDto.setFeaturedPosts(blogContents);
		blogPostDto.setUserName(userName);
		return blogPostDto;
	}

	@Override
	public BlogPersonInfoDto changeBlogUserInfo(String name, MultipartFile file, String description) {
		BlogPersonInfoDto result = new BlogPersonInfoDto();
		User user = userMapper.getByName(name);
		if (user != null) {
			String url = createFileAndWrite(file, user);
			BlogUser blogUser = new BlogUser();
			blogUser.setUserIcon(url);
			if (description.equals("")) {
				description = DefaultMessage.noDescription;
			}
			blogUser.setUserDescription(description);
			blogUser.setUserId(user.getId());
			blogUserMapper.update(blogUser);
			result.setDescription(description);
			result.setUserIcon(url);
		}
		return result;
	}

	private String createFileAndWrite(MultipartFile file, User user) {
		StringBuilder sb = new StringBuilder(proClass.getUserIconUrl());
		sb.append("\\");
		String fileName = user.getName()+
				          "-"+
				          UUID.randomUUID().toString().substring(0, 4)+
				          "."+
				          file.getOriginalFilename().split("\\.")[1];
		sb.append(fileName);
		String url = sb.toString();
		File tmpFile = new File(url);
		try {
			tmpFile.createNewFile();
			FileOutputStream out = new FileOutputStream(tmpFile);
			InputStream in = file.getInputStream();
			byte[] buf = new byte[512];
			int size = 0;
			while ((size = in.read(buf)) != -1) {
				out.write(buf, 0, size);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return proClass.getUserIconPath()+fileName;
	}

	@Override
	public BlogPersonInfoDto getBlogUser(String name) {
		User user = userMapper.getByName(name);
		BlogUser blogUser = null;
		BlogPersonInfoDto result = null;
		do {
			if (user == null) {
				break;
			}
			blogUser = blogUserMapper.getByUserId(user.getId());
			if (blogUser == null) {
				break;
			}
			result = new BlogPersonInfoDto();
			result.setDescription(blogUser.getUserDescription());
			result.setUserIcon(blogUser.getUserIcon());
			result.setUserId(user.getId());
		} while (false);
		return result;
	}

}
