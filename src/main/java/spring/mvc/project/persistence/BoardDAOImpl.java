package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.BoardVO;
import spring.mvc.project.vo.SearchVO;

@Repository
public class BoardDAOImpl implements BoardDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int getBoardCnt(SearchVO search) {
		return sqlSession.getMapper(BoardDAO.class).getBoardCnt(search);
	}

	@Override
	public ArrayList<BoardVO> getBoardList(SearchVO search) {
		return sqlSession.getMapper(BoardDAO.class).getBoardList(search);
	}

	@Override
	public void insertUpdateBoard(BoardVO vo) {
		sqlSession.getMapper(BoardDAO.class).insertUpdateBoard(vo);
	}
	
	@Override
	public int insertBoard(BoardVO vo) {
		if(vo.getB_id()==0) {
			vo.setB_ref(0);
			vo.setB_ref_level(0);
			vo.setB_ref_step(0);
		}else {
			insertUpdateBoard(vo);
			vo.setB_ref_level(vo.getB_ref_level()+1);
			vo.setB_ref_step(vo.getB_ref_step()+1);
		}
		return sqlSession.getMapper(BoardDAO.class).insertBoard(vo);
	}
	
	

	@Override
	public int checkReply(BoardVO vo) {
		return sqlSession.getMapper(BoardDAO.class).checkReply(vo);
	}

	@Override
	public int deleteAll(BoardVO vo) {
		return sqlSession.getMapper(BoardDAO.class).deleteAll(vo);
	}
	@Override
	public int deleteBoard(int b_id) {
		int deleteCnt=0;
		BoardVO vo=getBoardDetail(b_id);
		if(!vo.equals(null)) {
			if(checkReply(vo)>0) {
				deleteCnt= deleteAll(vo);
			}else {
				deleteCnt= sqlSession.getMapper(BoardDAO.class).deleteBoard(b_id);
			}
		}
		return deleteCnt;
	}
	
	@Override
	public BoardVO getBoardDetail(int b_id) {
		return sqlSession.getMapper(BoardDAO.class).getBoardDetail(b_id);
	}
	
	@Override
	public int updateBoard(BoardVO vo) {
		return sqlSession.getMapper(BoardDAO.class).updateBoard(vo);
		
	}
	/*
	

	

	

	
	*/

}
