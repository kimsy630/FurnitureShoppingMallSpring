package spring.mvc.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CartService {
	public void addCart(HttpServletRequest req, Model model);
	public void cartList(HttpServletRequest req, Model model);
	public void delCart(HttpServletRequest req,  Model model);

}
