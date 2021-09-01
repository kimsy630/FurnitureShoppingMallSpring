package spring.mvc.project.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.project.service.BoardServiceImpl;
import spring.mvc.project.service.CartService;
import spring.mvc.project.service.CartServiceImpl;
import spring.mvc.project.service.CategoryService;
import spring.mvc.project.service.CategoryServiceImpl;
import spring.mvc.project.service.MembersServiceImpl;
import spring.mvc.project.service.OrderService;
import spring.mvc.project.service.ProductServiceImpl;

@Controller
public class CustomerController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CategoryServiceImpl categoryService;

	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	MembersServiceImpl memberService;
	
	@Autowired
	CartServiceImpl cartService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	BoardServiceImpl boardService;
	
	/*
	@RequestMapping("/logout.do")
	public String logout(HttpServletRequest req,Model model) {
		logger.info("url ==> /logout");
		req.getSession().invalidate();//세션 지움
		categoryService.mainMenu(req, model);
		 productService.indexView(req, model);
		return "index/index";
	}*/
	
	@RequestMapping("/mypage.do")
	public String mypage(HttpServletRequest req,Model model) {
		logger.info("url ==> /mypage");
		categoryService.mainMenu(req, model);
		memberService.memberInfo(req, model);
		return "customer/mypage";
	}
	@RequestMapping("/memberUpdate.do")
	public String memberUpdate(HttpServletRequest req,Model model) {
		logger.info("url ==> /memberUpdate");
		categoryService.mainMenu(req, model);
		memberService.memberInfo(req, model);
		return "customer/memberUpdate";
	}
	
	@RequestMapping("/updateAction.do")
	public String updateAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /updateAction");
		memberService.updateMemberAction(req, model);
		return "customer/memberUpdateAction";
	}
	@RequestMapping("/addcart.do")
	public String addcart(HttpServletRequest req,Model model) {
		logger.info("url ==> /addcart");
		categoryService.mainMenu(req, model);
		cartService.addCart(req, model);
		return "customer/addcartAction";
	}
	@RequestMapping("/cart.do")
	public String cart(HttpServletRequest req,Model model) {
		logger.info("url ==> /cart");
		categoryService.mainMenu(req, model);
		cartService.cartList(req, model);
		return "customer/cart";
	}
	
	@RequestMapping("/deleteCartAction.do")
	public String deleteCartAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /deleteCartAction");
		categoryService.mainMenu(req, model);
		cartService.delCart(req, model);
		return "customer/deleteCartAction";
	}
	
	@RequestMapping("/noworderform.do")
	public String noworderform(HttpServletRequest req,Model model) {
		logger.info("url ==> /noworderform");
		categoryService.mainMenu(req, model);
		orderService.nowOrderView(req, model);
		return "customer/orderform";
	}
	@RequestMapping("/orderAction.do")
	public String orderAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /noworderform");
		categoryService.mainMenu(req, model);
		orderService.orderAction(req, model);
		return "customer/orderAction";
	}
	@RequestMapping("/cartOrderView.do")
	public String cartOrderView(HttpServletRequest req,Model model) {
		logger.info("url ==> /cartOrderView");
		categoryService.mainMenu(req, model);
		orderService.cartOrderView(req, model);
		return "customer/orderform";
	}
	@RequestMapping("/buy.do")
	public String buy(HttpServletRequest req,Model model) {
		logger.info("url ==> /buy");
		categoryService.mainMenu(req, model);
		orderService.orderView(req, model, 1);
		return "customer/buy";
	}
	
	@RequestMapping("/orderCancelAction.do")
	public String orderCancelAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /orderCancelAction");
		categoryService.mainMenu(req, model);
		orderService.updateOrder(req, model);
		return "customer/orderCancelAction";
	}

	@RequestMapping("/customer_refund.do")
	public String customer_refund(HttpServletRequest req,Model model) {
		logger.info("url ==> /customer_refund");
		categoryService.mainMenu(req, model);
		orderService.orderView(req, model,2);
		return "customer/customer_refund";
	}
	@RequestMapping("/refundAction.do")
	public String refundAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /refundAction");
		categoryService.mainMenu(req, model);
		orderService.updateOrder(req, model);
		return "customer/refundAction";
	}
	
	@RequestMapping("/memberRefundCancelAction.do")
	public String memberRefundCancelAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /memberRefundCancelAction");
		categoryService.mainMenu(req, model);
		orderService.updateOrder(req, model);
		return "customer/memberRefundCancelAction";
	}
	
	@RequestMapping("/withdrawalPwdCheck.do")
	public String withdrawalPwdCheck(HttpServletRequest req,Model model) {
		logger.info("url ==> /withdrawalPwdCheck");
		memberService.memberPwdCheck(req, model);
		return "customer/withdrawal";
	}
	
	@RequestMapping("/orderConfirmAction.do")
	public String orderConfirmAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /orderConfirmAction");
		categoryService.mainMenu(req, model);
		orderService.updateOrder(req, model);
		return "customer/orderConfirmAction";
	}
	@RequestMapping("/withdrawal.do")
	public String withdrawal(HttpServletRequest req,Model model) {
		logger.info("url ==> /withdrawal");
		model.addAttribute("selectM", 0);
		return "customer/withdrawal";
	}
	@RequestMapping("/withdrawalAction.do")
	public String withdrawalAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /withdrawalAction");
		memberService.deleteMemberAction(req, model);
		req.getSession().invalidate();//세션 지움
		return "customer/withdrawalAction";
	}
	
	@RequestMapping("/qna.do")
	public String qna(HttpServletRequest req,Model model) {
		logger.info("url ==> /qna");
		categoryService.mainMenu(req, model);
		boardService.boardList(req, model);

		return "customer/qna";
	}
	
	@RequestMapping("/addqna.do")
	public String addqna(HttpServletRequest req,Model model) {
		logger.info("url ==> /addqna");
		categoryService.mainMenu(req, model);
		boardService.addQna(req, model);

		return "customer/addqna";
	}
	
	@RequestMapping("/addqnaAction.do")
	public String addqnaAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /addqnaAction");
		categoryService.mainMenu(req, model);
		
		boardService.addQnaAction(req, model);
		boardService.boardList(req, model);
		return "customer/qna";
	}
	
	@RequestMapping("/deleteqna.do")
	public String deleteqna(HttpServletRequest req,Model model) {
		logger.info("url ==> /addqnaAction");
		categoryService.mainMenu(req, model);
		
		boardService.deleteQnaAction(req, model);
		boardService.boardList(req, model);
		return "customer/qna";
	}
	@RequestMapping("/updateqna.do")
	public String updateqna(HttpServletRequest req,Model model) {
		logger.info("url ==> /updateqna");
		categoryService.mainMenu(req, model);
		
		boardService.updateView(req, model);
		return "customer/addqna";
	}
	
	@RequestMapping("/updateqnaAction.do")
	public String updateqnaAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /updateqnaAction");
		categoryService.mainMenu(req, model);
		
		boardService.updateAction(req, model);
		boardService.boardList(req, model);
		return "customer/qna";
	}
}
