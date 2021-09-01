package spring.mvc.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface BoardService {
	public void boardList(HttpServletRequest req, Model model);

	public void addQna(HttpServletRequest req, Model model);
	//게시글 쓰기 처리페이지 
	public void addQnaAction(HttpServletRequest req, Model model);
	
	public void deleteQnaAction(HttpServletRequest req, Model model);
	
	//게시글 수정 페이지
	public void updateView(HttpServletRequest req, Model model);
	
	public void updateAction(HttpServletRequest req, Model model);
	/*
	
	//게시글 삭제 처리페이지
	
	*/
}
