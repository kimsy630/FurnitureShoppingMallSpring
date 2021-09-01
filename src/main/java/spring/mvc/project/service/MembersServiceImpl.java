package spring.mvc.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.MembersDAOImpl;
import spring.mvc.project.vo.MembersVO;


@Service
public class MembersServiceImpl implements MembersService{

	@Autowired
	MembersDAOImpl memberDAO;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@Override//아이디중복
	public void confirmId(HttpServletRequest req,Model model) { 
		String id=req.getParameter("mb_id");
		model.addAttribute("selectMb", memberDAO.idCheck(id)); 
		model.addAttribute("mb_id",id);
	}

	@Override//회원가입 
	public void signInAction(HttpServletRequest req,Model model) {
		MembersVO vo=new MembersVO();
		vo.setMb_id(req.getParameter("mb_id"));

	    String encryptPassword = passwordEncoder.encode(req.getParameter("mb_pwd"));
	    
		vo.setMb_pwd(encryptPassword);
		vo.setMb_name(req.getParameter("mb_name"));
		vo.setMb_certifiNum(req.getParameter("mb_certifiNum"));

		System.out.println("mb_id : " + vo.getMb_id());
		System.out.println("mb_certifiNum : " + vo.getMb_certifiNum());
		vo.setMb_phone(req.getParameter("mb_phone"));
		vo.setMb_email(req.getParameter("mb_email"));
		vo.setMb_address(req.getParameter("mb_address"));
		vo.setMb_authority(req.getParameter("mb_authority"));
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for(int i=0;i<6;i++) {
			int rIndex = rnd.nextInt(2);
			switch(rIndex) {
			case 0:
				temp.append((char)((int)(rnd.nextInt(26))+65));
				break;
			case 1:
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String key = temp.toString();
		vo.setMb_key(key);
		vo.setMb_join_date(new Timestamp(System.currentTimeMillis()));
		int insertMb= memberDAO.insertClient(vo);
		if(insertMb==1) {
			memberDAO.sendMail(vo.getMb_email(), vo.getMb_key());
		}
		model.addAttribute("insertMb",insertMb);
	}
	@Override
	public void emailChk(HttpServletRequest req,Model model) {
		String key = req.getParameter("key");
		int selectCnt = memberDAO.emailChk(key);
		if(selectCnt==1) {
			memberDAO.updateEnabled(key);
		}
	}
	
	@Override//수정 보이기
	public void memberInfo(HttpServletRequest req,  Model model) {
		String strid=(String)req.getSession().getAttribute("mb_id");
		model.addAttribute("dto",memberDAO.getMemberInfo(strid));
	}
	
	@Override//
	public void updateMemberAction(HttpServletRequest req,  Model model) {
		MembersVO vo = new MembersVO();
		vo.setMb_id((String)req.getSession().getAttribute("mb_id"));
		vo.setMb_pwd(passwordEncoder.encode(req.getParameter("mb_pwd")));
		vo.setMb_address(req.getParameter("mb_address"));
		vo.setMb_phone(req.getParameter("mb_phone"));
		vo.setMb_email(req.getParameter("mb_email"));
		model.addAttribute("updateMb", memberDAO.updateMember(vo));
	}
	@Override
	public void memberPwdCheck(HttpServletRequest req, Model model)  {
		String mb_id = (String)req.getSession().getAttribute("mb_id");
		String mb_pwd = req.getParameter("mb_pwd");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mb_id",mb_id);
		map.put("mb_pwd",mb_pwd);
		model.addAttribute("selectM", memberDAO.PwdChk(map));
	}
	
	@Override
	public void memberList(HttpServletRequest req,Model model) {
		List<MembersVO> list = memberDAO.membersInfo();
		model.addAttribute("list", list);
	}
	
	@Override//탈퇴
	public void deleteMemberAction(HttpServletRequest req,Model model) {
		int deleteM=memberDAO.deleteMember((String)req.getSession().getAttribute("mb_id"));
		model.addAttribute("deleteM", deleteM);
	}
	@Override
	public void adminDeleteMemberAction(HttpServletRequest req, Model model) {
		int deleteM=memberDAO.deleteMember(req.getParameter("mb_id"));
		req.setAttribute("deleteM", deleteM);
	}

}
