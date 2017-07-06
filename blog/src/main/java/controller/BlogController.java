package controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import domain.BlogContent;
import dto.AllPostDto;
import dto.BlogIndexDto;
import dto.BlogPersonInfoDto;
import dto.BlogPostDto;
import dto.Family;
import responsestring.ResponseString;
import service.BlogService;
import util.CheckAndResult;
import util.Result;

@Controller
@ResponseBody
@RequestMapping("/blogapi")
public class BlogController {
	@Autowired
	private BlogService blogService;

	@RequestMapping("/index")
	public Object index(String id) {
		Result result = new Result();
		if ((id != null) && (!id.equals("NaN"))) {
			int newid = Integer.parseInt(id);
			BlogIndexDto blogIndexDto = blogService.getIndexPageInfoByUserId(newid);
			boolean blogUserFlag = blogIndexDto.getBlogUser() != null;
			if (blogUserFlag) {
				result.setSuccess(true);
				result.setData(blogIndexDto);
			}
		}
		return result;
	}

	@RequestMapping("/getByPage")
	public Object getByPage(int id, int pageNum) {
		Result result = new Result();
		List<BlogContent> blogContents = null;
		do {
			if (pageNum < 0) {
				result.setData(ResponseString.pageNumErr);
				break;
			}
			blogContents = blogService.getBlogContentByPage(id, pageNum);
			boolean nullFlag = blogContents == null;
			boolean emptyFlag = blogContents.isEmpty();
			if (nullFlag || emptyFlag) {
				result.setData(ResponseString.requestDataErr);
				break;
			}
			result.setSuccess(true);
			result.setData(blogContents);
		} while (false);
		return result;
	}

	@RequestMapping("/getBlog")
	public Object getBlog(int blogId) {
		Result result = new Result();
		BlogPostDto blogPostDto = blogService.getBlog(blogId);
		if (blogPostDto.getContent() != null) {
			blogService.addRecallNums(blogId);
			result.setSuccess(true);
			result.setData(blogPostDto);
		} else {
			result.setData(ResponseString.requestDataErr);
		}
		return result;
	}

	@RequestMapping("/changeBlogUserInfo")
	public Object changeBlogUserInfo(String name, MultipartFile file, String description) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		boolean fileNotEmptyFlag = !file.getOriginalFilename().equals("");
		boolean successFlag = result.isSuccess();
		if (!fileNotEmptyFlag) {
			result.setSuccess(false);
			result.setData(ResponseString.userIconErr);
		}
		if (successFlag && fileNotEmptyFlag) {
			BlogPersonInfoDto blogPersonInfoDto = blogService.changeBlogUserInfo(name, file, description);
			result.setSuccess(true);
			result.setData(blogPersonInfoDto);
		}
		return result;
	}

	@RequestMapping("/getBlogUser")
	public Object getBlogUser(String name) {
		Result result = CheckAndResult.checkEmailBackResult(name);
		BlogPersonInfoDto blogPersonInfoDto = null;
		if (result.isSuccess()) {
			blogPersonInfoDto = blogService.getBlogUser(name);
		}
		if (blogPersonInfoDto == null) {
			result.setSuccess(false);
		} else {
			result.setSuccess(true);
			result.setData(blogPersonInfoDto);
		}
		return result;
	}

	@RequestMapping("/getFamily")
	public Object getFamily() {
		Result result = new Result();
		List<Family> family = blogService.getFamily();
		boolean nullFlag = family == null;
		boolean emptyFlag = family.isEmpty();
		if (!nullFlag && !emptyFlag) {
			result.setSuccess(true);
			result.setData(family);
		}
		return result;
	}

	@RequestMapping("/writeBlog")
	public Object writeBlog(MultipartFile file, String title, String category, String content, int id) {
		Result result = new Result();
		boolean fileEmptyFlag = file.getOriginalFilename().equals("");
		boolean titleNullFlag = title.equals("");
		boolean contentNullFlag = content.equals("");
		do {
			if (fileEmptyFlag) {
				result.setData(ResponseString.blogPictureErr);
				break;
			}
			if (titleNullFlag) {
				result.setData(ResponseString.blogTitleErr);
				break;
			}
			if (contentNullFlag) {
				result.setData(ResponseString.blogContentErr);
				break;
			}
			boolean success = blogService.writeBlog(file, title, category, content, id);
			if (success) {
				result.setSuccess(true);
			}
		} while (false);
		return result;
	}
	
	
	@RequestMapping("/allPost")
	public Object allPost(int id, String category, int pageNum) throws UnsupportedEncodingException {
		Result result = new Result();
		category = URLDecoder.decode(category, "utf-8");
		AllPostDto allPostDto = blogService.getBlogByCategory(id , category , pageNum);
		if (allPostDto != null) {
			result.setSuccess(true);
			result.setData(allPostDto);
		} else {
			result.setData("数据不存在");
		}
		return result;
	}

}
