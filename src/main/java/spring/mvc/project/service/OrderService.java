package spring.mvc.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface OrderService {
	public void nowOrderView(HttpServletRequest req,Model model);
	public void orderAction(HttpServletRequest req, Model model);
	public void cartOrderView(HttpServletRequest req, Model model);
	public void orderView(HttpServletRequest req, Model model,int type);
	public void updateOrder(HttpServletRequest req, Model mode);
}

