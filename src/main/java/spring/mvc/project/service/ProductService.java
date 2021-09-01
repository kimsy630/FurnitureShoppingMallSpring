package spring.mvc.project.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public interface ProductService {
	public void indexView(HttpServletRequest req, Model model);
	public void ProductSellerList(HttpServletRequest req, Model model);
	public void productadminList(HttpServletRequest req, Model model);
	public void addProduct(MultipartHttpServletRequest req, Model model);

	public void updateproductAction(MultipartHttpServletRequest req, Model model) ;

	public void productCategoryList(HttpServletRequest req, Model model);
	public void deleteproductAction(HttpServletRequest req, Model model);
	public void productInfo(HttpServletRequest req, Model model);

}
