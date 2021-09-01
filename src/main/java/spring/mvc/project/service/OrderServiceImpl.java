package spring.mvc.project.service;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.CartDAOImpl;
import spring.mvc.project.persistence.MembersDAOImpl;
import spring.mvc.project.persistence.OrderDAOImpl;
import spring.mvc.project.persistence.ProductDAOImpl;
import spring.mvc.project.vo.MembersVO;
import spring.mvc.project.vo.OrderFormVO;
import spring.mvc.project.vo.OrderVO;


@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	MembersDAOImpl memberDao;
	
	@Autowired
	ProductDAOImpl productDao;
	
	@Autowired
	OrderDAOImpl orderDao;
	
	@Autowired
	CartDAOImpl cartDao;
	
	@Override
	public void nowOrderView(HttpServletRequest req, Model model) {
		
		int count = Integer.parseInt(req.getParameter("count"));
		int p_id= Integer.parseInt(req.getParameter("p_id"));
		int p_price=Integer.parseInt(req.getParameter("price"));
		String p_name=req.getParameter("p_name");
		String p_image=req.getParameter("p_image");
		MembersVO member=memberDao.getMemberInfo((String)req.getSession().getAttribute("mb_id"));
		
		List<OrderFormVO> dto =new ArrayList<OrderFormVO>();
		OrderFormVO vo=new OrderFormVO();
		vo.setId(0);
		vo.setP_id(p_id);
		vo.setP_image(p_image);
		vo.setP_name(p_name);
		vo.setCount(count);
		vo.setPrice(p_price);
		
		p_price-=member.getRankCal(p_price);
		vo.setSaleprice(p_price);
		
		vo.setTotalprice(p_price*vo.getCount());
		
		vo.setPoint(member.getRankCal(vo.getTotalprice()));
		dto.add(vo);
		
		model.addAttribute("member",member);
		model.addAttribute("dto", dto);
		model.addAttribute("state",0);
	}
	@Override
	public void orderAction(HttpServletRequest req,Model model) {
		int salepoint =  Integer.parseInt(req.getParameter("point"));//사용한 포인트
		
		String[] orderArr=req.getParameterValues("id");
		String order_name=req.getParameter("order_name");
		String order_ph=req.getParameter("order_ph");
		String order_address=req.getParameter("order_address");
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int state = Integer.parseInt(req.getParameter("state"));//장바구니 구매인지 바로 구매인지 확인용
		List<Object> cartIdList=null;
		
		if(state==1) cartIdList=new ArrayList<Object>(); 
		
		List<OrderVO> list =new ArrayList<OrderVO>();
		for(int i=0;i<orderArr.length;i++) { //구매한 상품수만큼 돌림
			int p_id =Integer.parseInt(req.getParameter(orderArr[i]+"p_id"));
			int count=Integer.parseInt(req.getParameter(orderArr[i]+"count"));
			int price=Integer.parseInt(req.getParameter(orderArr[i]+"price"));
			int point=Integer.parseInt(req.getParameter(orderArr[i]+"point"));
			
			if(state==1) {//장바구니 상품이라면 
				int cartId=Integer.parseInt(req.getParameter(orderArr[i]+"cart_id"));
				cartIdList.add(cartId);//구매 완료라면 지워주기위해 담아둠
			}
			
			OrderVO vo=new OrderVO();
			vo.setP_id(p_id);
			vo.setOrder_count(count);
			vo.setMb_id(mb_id);
			vo.setOrder_price(price);
			vo.setOrder_point(point);
			vo.setOrder_name(order_name);
			vo.setOrder_ph(order_ph);
			vo.setOrder_address(order_address);
			vo.setOrder_date(new Timestamp(System.currentTimeMillis()));
			vo.setOrder_state(0);//state 상태로 구매, 환불등 상태 보여줌
			list.add(vo);
		}
		
		//구매하려는 상품의 재고수량이 전부있는지확인하고 재고수량 뺴주기용
		int updateP=productDao.productOrderCheck(list);
		if(updateP!=0) {//0이아니면 구매상품수량만큼 지워준거 
			if(salepoint>0) {//사용한 포인트가있다면 지워줌
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("mb_id", mb_id);
				map.put("point", -salepoint);
				memberDao.memberPointUpdate(map);
			}
			if(state==1) {//선택한 장바구니 리스트 지워줌
				System.out.println("asdsadasd");
				cartDao.OderDeleteCart(cartIdList);
			}
			for(int i=0;i<list.size();i++) orderDao.insertOrder(list.get(i));
		}
		model.addAttribute("updateP",updateP);
	}
	@Override
	public void cartOrderView(HttpServletRequest req, Model model) {
		MembersVO member=memberDao.getMemberInfo((String)req.getSession().getAttribute("mb_id"));
		List<OrderFormVO> dto =new ArrayList<OrderFormVO>();
		String[] cart_id=req.getParameterValues("chioce");
		
		for(int i=0;i<cart_id.length;i++) {
			int count = Integer.parseInt(req.getParameter(cart_id[i]+"count"));
			int p_id= Integer.parseInt(req.getParameter(cart_id[i]+"p_id"));
			int p_price=Integer.parseInt(req.getParameter(cart_id[i]+"price"));
			
			String p_name=req.getParameter(cart_id[i]+"p_name");
			String p_image=req.getParameter(cart_id[i]+"p_image");
			OrderFormVO vo=new OrderFormVO();
			vo.setId(i);
			vo.setP_id(p_id);
			vo.setCart_id(Integer.parseInt(cart_id[i]));
			vo.setP_image(p_image);
			vo.setP_name(p_name);
			vo.setCount(count);
			vo.setPrice(p_price);
			p_price-=member.getRankCal(p_price);
			vo.setSaleprice(p_price);
			vo.setTotalprice(p_price*vo.getCount());
			vo.setPoint(member.getRankCal(p_price));
			dto.add(vo);
		}
		
		model.addAttribute("member",member);
		model.addAttribute("dto", dto);
		model.addAttribute("state",1);
	}
	@Override
	public void orderView(HttpServletRequest req, Model model,int type) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mb_id",mb_id);
		map.put("type",type);
		List<OrderVO> dto = orderDao.orderView(map);
		model.addAttribute("dto", dto);
	}

	
	@Override
	public void updateOrder(HttpServletRequest req,Model model) {
		int order_state = Integer.parseInt(req.getParameter("state"));
		// order_state  
		//					가능한 선택지?
		//	0 : 손님이 주문함						   (손님 : 주문취소(5)				| 관리자 : 주문 승인(1), 주문 취소(4))
		//	1 :	판매자가  주문승인한후  					   (손님 : 환불(6), 구매확정(2)		| 관리자 : x )
		//	2 : 손님이 구매확정 누름 , 환불 요청한뒤 취소하면 여기로 (손님 : 리뷰달기(3)				| 관리자 : x )		
		//	3 : 손님이 리뷰단후  						   (손님 : x 						| 관리자 : x )     
		//	4 : 판매자가 구매요청 거절					   (손님 : x  					| 관리자 : x )
		//	5 : 0번에서 손님이 구매취소					   (손님 : x						| 관리자 : x )
		//	6 : 손님이 환불요청 						   (손님 : 환불 취소(2)				| 관리자 : 환불승인(7))
		//	7 : 판매자가 환불승인 						   (손님 : x						| 관리자 : x )
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		
		if(order_state==2) {
			OrderVO vo=orderDao.orderInfo(order_id);
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("mb_id",(String)req.getSession().getAttribute("mb_id"));
			map.put("point",vo.getOrder_point());
			memberDao.memberBuyPointUpdate(map);
		}



		Map<String, Object> map=new HashMap<String, Object>();
		map.put("order_id",order_id);
		map.put("order_state",order_state);
		System.out.println("order_state : " + order_state);
		int updateO = orderDao.updateOrderState(map);
		System.out.println("updateO : " + updateO);
		model.addAttribute("updateO", updateO);
	}

}
