package spring.mvc.project.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.CategoryDAOImpl;
import spring.mvc.project.persistence.MembersDAO;
import spring.mvc.project.vo.CategorysVo;
import spring.mvc.project.vo.UserVO;


public class UserLoginSuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public UserLoginSuccessHandler(SqlSessionTemplate sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	// 로그인이 성공한 경우에 실행한 코드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		UserVO vo = (UserVO)authentication.getPrincipal();
		System.out.println("UserVO==> " + vo);
		
		System.out.println("아이디 ==> " + authentication.getName());

		String grade = sqlSession.getMapper(MembersDAO.class).getAuthority(authentication.getName());
		String mb_classifi = "";
		//관리자  사업자   일반회원
		if(grade.equals("ROLE_USER")) {
			mb_classifi = "일반회원";
		}else if(grade.equals("ROLE_MANAGER")){
			mb_classifi = "사업자";
		}else if(grade.equals("ROLE_ADMIN")){
			mb_classifi = "관리자";
		}
		request.getSession().setAttribute("mb_id", authentication.getName());
		request.getSession().setAttribute("mb_classifi", mb_classifi);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/loginAction.jsp");

		//List<CategorysVo> menu =categoryDao.categoryView(null);
		//request.setAttribute("menu", menu);
		
		rd.forward(request, response);
	}

}
