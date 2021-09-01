package spring.mvc.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface MembersService {
	
	//중복확인 처리
	public void confirmId(HttpServletRequest req,Model model);

	//회원가입 처리
	public void signInAction(HttpServletRequest req,Model model);
	
	public void emailChk(HttpServletRequest req,Model model);
	
	public void memberInfo(HttpServletRequest req, Model model);
	
	//회원정보 수정 처리
	public void updateMemberAction(HttpServletRequest req, Model model);

	public void memberPwdCheck(HttpServletRequest req, Model model) ;
	
	public void memberList(HttpServletRequest req, Model model) ;
	
	//회원정보 인증및 탈퇴 삭제 처리
	public void deleteMemberAction(HttpServletRequest req,  Model model);
	
	public void adminDeleteMemberAction(HttpServletRequest req, Model model);
	
	/*
	//로그인 처리
	public void loginAction(HttpServletRequest req, HttpServletResponse res);
	
	
	//회원정보 인증및 탈퇴 삭제 처리
	
	//회원정보  인증 및 상세 페이지
	
	
	*/
}
