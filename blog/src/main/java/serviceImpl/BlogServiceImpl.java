package serviceImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import domain.BlogContent;
import domain.BlogUser;
import domain.User;
import dto.AllPostDto;
import dto.BlogIndexDto;
import dto.BlogPersonInfoDto;
import dto.BlogPostDto;
import dto.Family;
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
		List<Family> family = userMapper.getFamily();
		checkDtoFiled(blogContents, blogUser, family);
		maxPageNum = getMaxPageNum(maxPageNum,5);
		BlogIndexDto blogIndexDto = new BlogIndexDto(blogContents, categories, family, blogUser, userName, maxPageNum);
		return blogIndexDto;
	}

	private void checkDtoFiled(List<BlogContent> blogContents, BlogUser blogUser, List<Family> family) {
			if (blogUser == null) {
				return;
			}
			if (blogContents.isEmpty()) {
				BlogContent blogContent = new BlogContent();
				blogContent.setBlogTitle("没有数据");
				blogContents.add(blogContent);
			}
			if (family.isEmpty()) {
				Family f = new Family();
				f.setId(-1);
				f.setName("没有数据");
			}
	}

	public int getMaxPageNum(int maxPageNum, int size) {
		if (maxPageNum % size == 0) {
			maxPageNum = maxPageNum / size;
		} else {
			maxPageNum = maxPageNum / size + 1;
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
			BlogUser blogUser = blogUserMapper.getByUserId(user.getId());
			if (!blogUser.getUserIcon().equals("")) {
				deleteOldUserIcon(blogUser.getUserIcon());
			}
			String url = createFileAndWrite(file, user);
			blogUser = new BlogUser();
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

	private void deleteOldUserIcon(String userIcon) {
		String[] buf = userIcon.split("/");
		String path = proClass.getUserIconUrl()+"/"+buf[buf.length-1];
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	private String createFileAndWrite(MultipartFile file, User user) {
		StringBuilder sb = new StringBuilder(proClass.getUserIconUrl());
		sb.append("\\");
		String fileName = user.getName() + "-" + UUID.randomUUID().toString().substring(0, 4) + "."
				+ file.getOriginalFilename().split("\\.")[1];
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
		return proClass.getUserIconPath() + fileName;
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

	@Override
	public List<Family> getFamily() {
		List<Family> family = userMapper.getFamily();
		return family;
	}

	@Override
	public boolean writeBlog(MultipartFile file, String title, String category, String content, int id) {
		boolean result = false;
		User user = null;
		user = userMapper.getById(id);
		boolean nullFlag = user == null;
		if (!nullFlag) {
			String path = createFileAndWrite(file, user);
			BlogContent blogContent = new BlogContent();
			if (!category.equals("")) {
				blogContent.setBlogCategory(category);
			}
			blogContent.setBlogContent(content);
			blogContent.setBlogPicture(path);
			int size = 0;
			if (content.length() > 20) {
				size = 20;
			} else {
				size = content.length();
			}
			blogContent.setBlogText(content.substring(0,size));
			blogContent.setBlogTitle(title);
			blogContent.setRecallNums(0);
			blogContent.setTime(new Date(Calendar.getInstance().getTimeInMillis()));
			blogContent.setUserId(id);
			blogContentMapper.add(blogContent);
			result = true;
		}
		return result;
	}

	@Override
	public void addRecallNums(int id) {
		blogContentMapper.addRecallNums(id);
	}

	@Override
	public AllPostDto getBlogByCategory(int id, String category, int pageNum) {
		AllPostDto allPostDto = null;
		User user = userMapper.getById(id);
		int pageDiv = 1;
		if (user != null) {
			allPostDto = new AllPostDto();
			int maxPageNum = blogContentMapper.getMaxPageNumByCategory(category,id);
			maxPageNum = getMaxPageNum(maxPageNum,pageDiv);
			List<BlogContent> blogContents = blogContentMapper.getByCategory(category, pageNum*pageDiv, id, pageDiv);
			List<String> categories = blogContentMapper.getCategories(id);
			allPostDto.setBlogContents(blogContents);
			allPostDto.setMaxPageNum(maxPageNum);
			allPostDto.setCategories(categories);
			allPostDto.setUser(user);
		}
		return allPostDto;
	}

}
