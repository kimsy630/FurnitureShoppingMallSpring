package spring.mvc.project.persistence;

import java.util.List;
import java.util.Map;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.MembersVO;


@Repository
public class MembersDAOImpl implements MembersDAO{
	
	@Autowired
	SqlSession sqlSession;
	

	@Autowired
    private JavaMailSender mailSender; // xml에 등록한 bean autowired
	
	@Override
	public void sendMail(String toEmail, String key) {
		try{
            MimeMessage message = mailSender.createMimeMessage();
            String txt = "회원가입 인증 메일입니다. 링크를 눌러 회원가입을 완료하세요." 
            			+ "<a href='http://localhost/project/emailChk.cc?key=" + key + "'>Please click</a>";
            System.out.println(txt);
          message.setSubject("회원가입 인증 메일입니다.");
          message.setText(txt, "UTF-8", "html");
          message.setFrom(new InternetAddress("admin@mss.com"));
          message.addRecipient(RecipientType.TO, new InternetAddress(toEmail));
          mailSender.send(message);
		}catch(Exception e){
		          e.printStackTrace();
		}  
	}
	
	@Override
	public MembersVO getMemberInfo(String strId) {//회원정보 수정할때 사용
		return sqlSession.getMapper(MembersDAO.class).getMemberInfo(strId);
	}
	
	@Override
	public int insertClient(MembersVO vo) {//가입
		return sqlSession.getMapper(MembersDAO.class).insertClient(vo);
	}
	
	@Override
	public int idCheck(String strId) {//아이디 체크
		return sqlSession.getMapper(MembersDAO.class).idCheck(strId);
	}

	@Override
	public int emailChk(String key) {
		return sqlSession.getMapper(MembersDAO.class).emailChk(key);
	}

	@Override
	public int updateEnabled(String key) {
		return sqlSession.getMapper(MembersDAO.class).updateEnabled(key);
	}

	@Override
	public String pwdCheck(String mb_id) {
		return sqlSession.getMapper(MembersDAO.class).pwdCheck(mb_id);
	}

	@Override
	public String getAuthority(String mb_id) {
		return sqlSession.getMapper(MembersDAO.class).getAuthority(mb_id);
	}


	@Override
	public int updateMember(MembersVO vo) {//회원정보 수정
		return sqlSession.getMapper(MembersDAO.class).updateMember(vo);
	}
	
	@Override
	public void memberPointUpdate(Map<String,Object>map) {
		sqlSession.getMapper(MembersDAO.class).memberPointUpdate(map);
	}
	
	@Override
	public void memberBuyPointUpdate(Map<String, Object>map){
		sqlSession.getMapper(MembersDAO.class).memberBuyPointUpdate(map);
	}
	
	@Override
	public int PwdChk(Map<String, Object>map) {
		return sqlSession.getMapper(MembersDAO.class).PwdChk(map);
	};
	
	@Override
	public List<MembersVO> membersInfo() {
		return sqlSession.getMapper(MembersDAO.class).membersInfo();
	}
	@Override
	public int deleteMember(String mb_id) {//회원 탈퇴 안지우고 탈퇴날짜만
		return sqlSession.getMapper(MembersDAO.class).deleteMember(mb_id);
	}
	/*
	@Override
	public String idPwdChk(String strId, String strPwd){//아이디 비밀번호 체크
		String mb_classifi="noId";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=dataSource.getConnection();
			String sql="SELECT * FROM members WHERE mb_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1,strId);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				mb_classifi="noPwd";
				if(strPwd.equals(rs.getString("mb_pwd"))) {
					mb_classifi =rs.getString("mb_classifi");
				}
			}
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mb_classifi;
	}

	

	

	
	*/
}
