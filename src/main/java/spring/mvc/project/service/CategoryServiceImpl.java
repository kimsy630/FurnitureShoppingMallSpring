package spring.mvc.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.CategoryDAOImpl;
import spring.mvc.project.vo.CategorysVo;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	CategoryDAOImpl categoryDao;
	
	@Override
	public void mainMenu(HttpServletRequest req, Model model) {
		List<CategorysVo> menu =categoryDao.categoryView(null);
		model.addAttribute("menu", menu);
	}

}
