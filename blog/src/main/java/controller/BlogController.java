package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import domain.BlogContent;
import dto.BlogIndexDto;
import service.BlogService;
import util.Result;

@Controller
@ResponseBody
@RequestMapping("/blogapi")
public class BlogController {
	@Autowired
	private BlogService blogService;

	@RequestMapping("/index")
	public Object index(int id) {
		Result result = new Result();
		BlogIndexDto blogIndexDto = blogService.getIndexPageInfoByUserId(id);
		boolean contentsFlag = !blogIndexDto.getBlogContents().isEmpty();
		boolean categoriesFlag = !blogIndexDto.getCategories().isEmpty();
		boolean blogUserFlag = blogIndexDto.getBlogUser() != null;
		if (contentsFlag || categoriesFlag || blogUserFlag) {
			result.setSuccess(true);
			result.setData(blogIndexDto);
		}
		return result;
	}

	@RequestMapping("/getByPage")
	public Object getByPage(int id, int pageNum) {
		Result result = new Result();
		List<BlogContent> blogContents = null;
		do {
			if (pageNum < 0) {
				result.setData("Ò³ÂëºÅ²ÎÊý´íÎó");
				break;
			}
			blogContents = blogService.getBlogContentByPage(id, pageNum);
			boolean nullFlag = blogContents == null;
			boolean emptyFlag = blogContents.isEmpty();
			if (nullFlag || emptyFlag) {
				result.setData("ÇëÇóÊý¾ÝÊ§°Ü");
				break;
			}
			result.setSuccess(true);
			result.setData(blogContents);
		} while (false);
		return result;
	}
}
