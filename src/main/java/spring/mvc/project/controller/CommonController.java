package spring.mvc.project.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.project.HomeController;
import spring.mvc.project.service.CategoryServiceImpl;
import spring.mvc.project.service.MembersServiceImpl;
import spring.mvc.project.service.ProductServiceImpl;

@Controller
public class CommonController extends HttpServlet {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CategoryServiceImpl categoryService;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	MembersServiceImpl memberService;
	
	@RequestMapping("/index.cc")
	public String index(HttpServletRequest req,Model model) {
		logger.info("url ==> /index");
		categoryService.mainMenu(req, model);
		productService.indexView(req, model);
		return "index/index";
	}
	
	@RequestMapping("/login.cc")
	public String login(HttpServletRequest req,Model model) {
		logger.info("url ==> /login");
		categoryService.mainMenu(req, model);
		
		return "common/login";
	}

	@RequestMapping("/join.cc")
	public String join(HttpServletRequest req,Model model) {
		logger.info("url ==> /join");
		categoryService.mainMenu(req, model);
		
		return "common/join";
	}
	
	@RequestMapping("/corfirmId.cc")
	public String corfirmId(HttpServletRequest req,Model model) {
		logger.info("url ==> /corfirmId");
		categoryService.mainMenu(req, model);
		memberService.confirmId(req, model);
		return "common/corfirmId";
	}
	
	@RequestMapping("/joinAction.cc")
	public String joinAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /joinAction");
		categoryService.mainMenu(req, model);
		memberService.signInAction(req, model);
		return "common/joinAction";
	}
	
	@RequestMapping("/emailChk.cc")
	public String emailChk(HttpServletRequest req,Model model) {
		logger.info("url ==> /emailChk");
		categoryService.mainMenu(req, model);
		memberService.emailChk(req, model);
		return "common/login";
	}
	@RequestMapping("/category.cc")
	public String category(HttpServletRequest req,Model model) {
		logger.info("url ==> /category");
		categoryService.mainMenu(req, model);
		productService.productCategoryList(req, model);
		return "common/category";
	}
	
	@RequestMapping("/products.cc")
	public String products(HttpServletRequest req,Model model) {
		logger.info("url ==> /category");
		categoryService.mainMenu(req, model);
		productService.productInfo(req, model);
		return "common/products";
	}
}
