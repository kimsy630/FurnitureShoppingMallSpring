package spring.mvc.project.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.CartDAOImpl;
import spring.mvc.project.persistence.MembersDAOImpl;
import spring.mvc.project.vo.CartVO;
import spring.mvc.project.vo.MembersVO;


@Service
public class CartServiceImpl implements CartService{
	
	@Autowired
	CartDAOImpl cartDao;
	
	@Autowired
	MembersDAOImpl memberDao;
	
	@Override
	public void addCart(HttpServletRequest req, Model model) {
		
		int count =Integer.parseInt(req.getParameter("count"));//화면에서 받아온
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		int p_id = Integer.parseInt(req.getParameter("p_id"));
		int p_count=Integer.parseInt(req.getParameter("p_count"));
		
		Map<String ,Object> map =new HashMap<String, Object>();
		
		map.put("mb_id", mb_id);
		map.put("p_id", p_id);
					   //이미 담은상품인지 확인 있다면 수량 return 해줌
		int selectCnt = Integer.parseInt(cartDao.selectCart(map).toString());
		int insertC = 0;
		
		if(selectCnt==0) {//카트에없는상품이라면
			CartVO vo =new CartVO();
			vo.setCart_count(count);
			vo.setMb_id(mb_id);
			vo.setP_id(p_id);
			insertC=cartDao.insertCart(vo);
		}
		else {//이미있는상품이라면
			//추가를하면 판매상품의 수량보다 많은지 확인
			if(p_count <selectCnt+count) {
				insertC=3;
				count=p_count;
			}else {//그냥 추가만 하면됨
				count+=selectCnt;
				insertC=2;
			}
			map.put("count", count);
			cartDao.updateCart(map);
		}
		
		model.addAttribute("insertC", insertC);
	}

	@Override
	public void cartList(HttpServletRequest req, Model model) {
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		List<CartVO> dto = cartDao.memberCartList(mb_id);
		req.setAttribute("dto", dto);
		
		MembersVO member= memberDao.getMemberInfo(mb_id);
		model.addAttribute("member", member);
	}
	@Override
	public void delCart(HttpServletRequest req,  Model model) {
		String[] deleteList =req.getParameterValues("chioce");
		int deleteC =0;
		
		for(int i=0;i<deleteList.length;i++) {
			deleteC = cartDao.deleteCart(Integer.parseInt(deleteList[i]));
		}
		model.addAttribute("deleteC",deleteC);
	}

}
