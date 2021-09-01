package spring.mvc.project.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

import spring.mvc.project.vo.AccountVO;

public interface AccountService {
	public void accountView(HttpServletRequest req,Model model);
	
	public void adminAccountView(HttpServletRequest req,Model model);
	
	public List<AccountVO> chartList(HttpServletRequest req,Model model);
	public List<AccountVO> adminChartList(HttpServletRequest req,Model model);
	
	public List<AccountVO> testChart(HttpServletRequest req,Model model);
}
