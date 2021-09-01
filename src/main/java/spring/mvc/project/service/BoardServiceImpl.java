package spring.mvc.project.service;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.BoardDAOImpl;
import spring.mvc.project.persistence.MembersDAOImpl;
import spring.mvc.project.vo.BoardVO;
import spring.mvc.project.vo.SearchVO;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	BoardDAOImpl boardDao;
	
	@Autowired
	MembersDAOImpl memberDao;
	
	@Override
	public void boardList(HttpServletRequest req, Model model) {
		//3단계. 화면으로 입력받은 값을 받아온다.
		//페이징 처리
		int pageSize=10;		//한페이지당 출력할 글 갯수
		int pageBlock=5;	//한블럭당 페이지 갯수 
		int cnt	=0;			//글갯수
		int start = 0;  	//현재 페이지 시작 글번호
		int end=0;			//현재 페이지 마지막 글번호
		int number=0;		//출력용 글번호
		String pageNum="";	//페이지 번호
		int currentPage=0;	//현재페이지
		
		int pageCount=0;	//페이지 갯수
		int startPage=0;	//시작페이지
		int endPage=0;		//마지막페이지
		
		SearchVO search= new SearchVO();
		search.setType(0);
		if(req.getParameter("search")!=null) {
			search.setSearch(req.getParameter("search"));
			search.setType(Integer.parseInt(req.getParameter("type")));
		}
		
		cnt=boardDao.getBoardCnt(search);
		System.out.println("cnt : "+cnt);
		
		pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		//글 30건 기준
		currentPage = Integer.parseInt(pageNum);
		
		//페이지 갯수 6 = (30/5)+(0)
		pageCount=(cnt/pageSize)+(cnt%pageSize>0?1:0);
		
		//현재페이지 시작 글번호
		start=(currentPage-1)*pageSize+1;
		
		//현재페이지 마지막 글번호(페이지별)
		end=start+pageSize-1;
		
		//출력용 글번호-최종 페이지
		number= cnt-(currentPage-1)*pageSize;
		
		search.setStart(start);
		search.setEnd(end);
		if(cnt>0) {
			System.out.println(search);
			//5-2단계. 게시글 목록 조회
			ArrayList<BoardVO> dtos=boardDao.getBoardList(search);
			req.setAttribute("dtos",dtos);
		}

		//6단계. request나 session에 처리결과를 저장 (jsp에 전달)
		
		//시작 페이지
		startPage=(currentPage/pageBlock)*pageBlock+1;
		
		if(currentPage%pageBlock==0)startPage-=pageBlock;

		//마지막 페이지
		endPage=startPage+pageBlock-1;
		if(endPage>pageCount)endPage=pageCount;
		model.addAttribute("cnt", cnt);//글갯수
		model.addAttribute("number", number);//출력용 글번호
		model.addAttribute("pageNum", pageNum);//페이지 번호
		if(search.getSearch()!=null) {
			model.addAttribute("search", search.getSearch());//출력용 글번호
			model.addAttribute("type", search.getType());//페이지 번호
		}
		
		if(cnt>0) {
			model.addAttribute("startPage", startPage);//시작페이지
			model.addAttribute("endPage", endPage);//마지막페이지
			model.addAttribute("pageBlock", pageBlock);//한 블럭당 페이지 갯수
			model.addAttribute("pageCount", pageCount);//페이지 갯수
			model.addAttribute("currentPage", currentPage);//현제페이지
		}
	}

	@Override
	public void addQna(HttpServletRequest req, Model model) {
		//화면으로부터 입력받은 값(hidden갑스 input값을 받아온다.
		//신규 제목글(답변글이 아닌경우)
		//num, pageNum,ref,ref_step,ref_level
		int b_id=0;
		int pageNum=0;
		int ref=0;
		int ref_step=0;
		int ref_level=0;
		//답글쓰기=>write.bo?num=${num}&pageNum=${pageNum}&ref=${dto.ref}&ref_step=${dto.ref_step}&ref_level=${dto.ref_level}'
		if(req.getParameter("b_id")!=null) {
			b_id=Integer.parseInt(req.getParameter("b_id"));
			ref=Integer.parseInt(req.getParameter("ref"));
			ref_step=Integer.parseInt(req.getParameter("ref_step"));
			ref_level=Integer.parseInt(req.getParameter("ref_level"));
		}
		pageNum=Integer.parseInt(req.getParameter("pageNum"));
		//request에 처리결과를 저장
		
		model.addAttribute("writer",memberDao.getMemberInfo((String)req.getSession().getAttribute("mb_id")).getMb_name());
		model.addAttribute("b_id",b_id );
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("ref", ref);
		model.addAttribute("ref_step",ref_step );
		model.addAttribute("ref_level", ref_level);
	}
	
	@Override
	public void addQnaAction(HttpServletRequest req, Model model) {
		//3단계 화면으로부터 입력받은값 받아온다
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		
		BoardVO vo=new BoardVO();
		vo.setB_id(Integer.parseInt(req.getParameter("b_id")));
		vo.setB_ref(Integer.parseInt(req.getParameter("ref")));
		vo.setB_ref_step(Integer.parseInt(req.getParameter("ref_step")));
		vo.setB_ref_level(Integer.parseInt(req.getParameter("ref_level")));
		vo.setMb_id((String)req.getSession().getAttribute("mb_id"));
		
		vo.setB_writer(memberDao.getMemberInfo((String)req.getSession().getAttribute("mb_id")).getMb_name());
		System.out.println(vo.getB_writer());
		vo.setB_content(req.getParameter("content"));
		vo.setB_subject(req.getParameter("subject"));
		
		vo.setB_reg_date(new Timestamp(System.currentTimeMillis()));
		int insertCnt =boardDao.insertBoard(vo);
		
		//6단계 request 나 session에 처리결과를 저장
		model.addAttribute("insertCnt", insertCnt);
		model.addAttribute("pageNum", pageNum);
	}

	@Override
	public void deleteQnaAction(HttpServletRequest req, Model model) {
		int b_id= Integer.parseInt(req.getParameter("b_id"));
		int pageNum= Integer.parseInt(req.getParameter("pageNum"));
		
		int deleteCnt =boardDao.deleteBoard(b_id);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("deleteCnt", deleteCnt);
	}
	@Override
	public void updateView(HttpServletRequest req, Model model) {
		int b_id=Integer.parseInt(req.getParameter("b_id"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));


		BoardVO vo =boardDao.getBoardDetail(b_id);
		req.setAttribute("dto", vo);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("b_id", b_id);
	}

	@Override
	public void updateAction(HttpServletRequest req, Model model) {
		BoardVO vo = new BoardVO();
		int b_id= Integer.parseInt(req.getParameter("b_id"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		vo.setB_id(b_id);
		vo.setB_subject(req.getParameter("subject"));
		vo.setB_content(req.getParameter("content"));
		int updateCnt=boardDao.updateBoard(vo);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("updateCnt", updateCnt);
	}
	/*
	*/
}
