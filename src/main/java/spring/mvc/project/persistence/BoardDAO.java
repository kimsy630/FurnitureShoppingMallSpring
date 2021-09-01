package spring.mvc.project.persistence;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spring.mvc.project.vo.BoardVO;
import spring.mvc.project.vo.SearchVO;

public interface BoardDAO {
	public int getBoardCnt(SearchVO search);

	//게시글 목록
	public ArrayList<BoardVO>  getBoardList(SearchVO search);

	//게시글 쓰기 처리페이지
	public int insertBoard(BoardVO vo);
	
	@Update("UPDATE board SET b_ref_step=b_ref_step+1 WHERE b_ref=#{b_ref} AND b_ref_step>#{b_ref_step} ")
	public void insertUpdateBoard(BoardVO vo);

	@Select("SELECT * FROM board WHERE b_id=#{b_id}")
	public BoardVO getBoardDetail(int b_id);
	
	@Delete("DELETE board WHERE b_id=#{b_id}")
	public int deleteBoard(int b_id);
	
	public int checkReply(BoardVO vo);
	
	@Delete("DELETE board WHERE b_id=#{b_id} OR(b_ref=#{b_ref} AND b_ref_level >#{b_ref_level})")
	public int deleteAll(BoardVO vo);

	@Update("UPDATE board SET b_subject=#{b_subject},b_content=#{b_content} WHERE b_id=#{b_id}")
	public int updateBoard(BoardVO vo);
	/*
	//게시글 상세 페이지, 수정 상세페이지
	public BoardVO getBoardDetail(int b_id);
	
	//게시글 수청- 수정 처리 페이지
	//게시글 삭제 처리페이지
	//게시글 인증- 게시글 삭제 처리페이지
		
	 */
}
