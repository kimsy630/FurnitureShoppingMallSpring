package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.CartVO;


@Repository
public class CartDAOImpl implements CartDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertCart(CartVO vo) {
		return sqlSession.getMapper(CartDAO.class).insertCart(vo);
	}
	
	@Override
	public Object selectCart(Map<String, Object> map) {
		Object num=sqlSession.getMapper(CartDAO.class).selectCart(map);
		if(num==null)  return 0;
		return num;
	}
	
	@Override
	public int updateCart(Map<String, Object> map) {
		return sqlSession.getMapper(CartDAO.class).updateCart(map);
	}
	
	@Override
	public List<CartVO> memberCartList(String mb_id) {
		return sqlSession.getMapper(CartDAO.class).memberCartList(mb_id);
	}
	
	@Override
	public int deleteCart(int num) {
		return sqlSession.getMapper(CartDAO.class).deleteCart(num);
	}
	@Override
	public void OderDeleteCart(List<Object> list) {
		sqlSession.getMapper(CartDAO.class).OderDeleteCart(list);
	}
}
