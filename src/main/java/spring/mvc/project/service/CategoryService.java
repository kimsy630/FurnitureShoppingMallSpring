package spring.mvc.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CategoryService {
	public void mainMenu(HttpServletRequest req,Model model);
}
