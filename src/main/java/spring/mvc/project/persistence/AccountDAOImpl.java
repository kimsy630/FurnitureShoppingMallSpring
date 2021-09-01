package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.AccountVO;

@Repository
public class AccountDAOImpl implements AccountDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<AccountVO> accountMonth(String mb_id) {
		return sqlSession.getMapper(AccountDAO.class).accountMonth(mb_id);
	}

	@Override
	public List<Integer> approvedCount(String mb_id) {
		return sqlSession.getMapper(AccountDAO.class).approvedCount(mb_id);
	}
	
	@Override
	public List<AccountVO> adminAccountMonth() {
		return sqlSession.getMapper(AccountDAO.class).adminAccountMonth();
	}

	@Override
	public List<AccountVO> getTest() {
		return sqlSession.getMapper(AccountDAO.class).getTest();
	}
}
