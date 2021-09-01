package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.OrderVO;


@Repository
public class OrderDAOImpl implements OrderDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int insertOrder(OrderVO vo) {
		return sqlSession.getMapper(OrderDAO.class).insertOrder(vo);
	}

	@Override
	public List<OrderVO> orderView(Map<String,Object> map) {
		return sqlSession.getMapper(OrderDAO.class).orderView(map);
	}
	@Override
	public int updateOrderState(Map<String,Object> map) {
		return sqlSession.getMapper(OrderDAO.class).updateOrderState(map);
	}


	@Override
	public OrderVO orderInfo(int order_id) {
		return sqlSession.getMapper(OrderDAO.class).orderInfo(order_id);
	}
}
