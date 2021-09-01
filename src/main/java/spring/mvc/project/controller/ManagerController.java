package spring.mvc.project.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;


import spring.mvc.project.service.AccountServiceImpl;
import spring.mvc.project.service.MembersServiceImpl;
import spring.mvc.project.service.OrderService;
import spring.mvc.project.service.OrderServiceImpl;
import spring.mvc.project.service.ProductServiceImpl;
import spring.mvc.project.vo.AccountVO;


@Controller
public class ManagerController {

	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	AccountServiceImpl accountService;
	
	@Autowired
	ProductServiceImpl productService;
	
	@Autowired
	OrderServiceImpl orderService;
	
	@Autowired
	MembersServiceImpl memberService;

	@RequestMapping("/settlement.mc")
	public String updateAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /settlement");
		accountService.accountView(req, model);
		return "manager/settlement";
	}
	
	@RequestMapping("/adminSettlement.mc")
	public String adminSettlement(HttpServletRequest req,Model model) {
		logger.info("url ==> /adminSettlement");
		accountService.adminAccountView(req, model);
		return "manager/settlement";
	}
	
	@RequestMapping("/productManagement.mc")
	public String productManagement(HttpServletRequest req,Model model) {
		logger.info("url ==> /productManagement");
		productService.ProductSellerList(req, model);
		return "manager/productManagement";
	}
	
	@RequestMapping("/productAdmin.mc")
	public String productAdmin(HttpServletRequest req,Model model) {
		logger.info("url ==> /productManagement");
		productService.productadminList(req, model);
		return "manager/productManagement";
	}
	@RequestMapping("/addproducts.mc")
	public String addproducts(HttpServletRequest req,Model model) {
		logger.info("url ==> /addproducts");
		return "manager/addproducts";
	}
	@RequestMapping("/addproductAction.mc")
	public String addproductAction(MultipartHttpServletRequest req,Model model) throws IOException {
		logger.info("url ==> /addproductAction");
		productService.addProduct(req, model);
		return "manager/addproductAction";
	}
	
	@RequestMapping("/deleteproductAction.mc")
	public String deleteproductAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /deleteproductAction");
		productService.deleteproductAction(req,model);
		return "manager/deleteproductAction";
	}
	@RequestMapping("/updateproducts.mc")
	public String updateproducts(HttpServletRequest req,Model model) {
		logger.info("url ==> /updateproducts");
		productService.productInfo(req,model);
		return "manager/updateproducts";
	}
	@RequestMapping("/orderApproval.mc")
	public String orderApproval(HttpServletRequest req,Model model) {
		logger.info("url ==> /orderApproval");
		orderService.orderView(req, model, 3);
		return "manager/manager_sales";
	}
	
	@RequestMapping("/orderConfirmAction.mc")
	public String orderConfirmAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /orderConfirmAction");
		orderService.updateOrder(req, model);
		return "manager/orderConfirmAction";
	}
	
	@RequestMapping("/orderCancelAction.mc")
	public String orderCancelAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /orderCancelAction");
		orderService.updateOrder(req, model);
		return "manager/orderCancelAction";
	}
	
	@RequestMapping("/refundApproval.mc")
	public String refundApproval(HttpServletRequest req,Model model) {
		logger.info("url ==> /refundApproval");
		orderService.orderView(req, model,4);
		return "manager/manager_refund";
	}
	
	@RequestMapping("/managerRefundConfirmAction.mc")
	public String managerRefundConfirmAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /managerRefundConfirmAction");
		orderService.updateOrder(req, model);
		return "manager/managerRefundConfirmAction";
	}
	
	@RequestMapping("/updateproductAction.mc")
	public String updateproductAction(MultipartHttpServletRequest req,Model model) {
		logger.info("url ==> /updateproductAction");
		productService.updateproductAction(req, model);
		return "manager/updateproductAction";
	}
	
	@RequestMapping("/manager_memberInfo.mc")
	public String manager_memberInfo(HttpServletRequest req,Model model) {
		logger.info("url ==> /manager_memberInfo");
		memberService.memberList(req, model);
		return "manager/manager_memberInfo";
	}
	@RequestMapping("/deleteMemberAction.mc")
	public String deleteMemberAction(HttpServletRequest req,Model model) {
		logger.info("url ==> /deleteMemberAction");
		memberService.adminDeleteMemberAction(req, model);
		return "manager/deleteMemberAction";
	}
	
	@RequestMapping("/settlementChart.mc")
	public @ResponseBody List<AccountVO> settlementChart(HttpServletRequest req,Model model) {
		logger.info("url ==> /settlementChart");
		return accountService.chartList(req, model);
	}
	
	@RequestMapping("/adminSettlementChart.mc")
	public @ResponseBody List<AccountVO> adminSettlementChart(HttpServletRequest req,Model model) {
		logger.info("url ==> /adminSettlementChart");
		return accountService.adminChartList(req, model);
	}
	
	@RequestMapping("/testChart.mc")
	public @ResponseBody List<AccountVO> testChart(HttpServletRequest req,Model model) {
		logger.info("url ==> /testChart");
		return accountService.testChart(req, model);
	}
	
	/*
	
	 */
	
	/*	
	  	else if(url.equals("/manager_memberInfo.mc")) {
			MembersServiceImpl member = new MembersServiceImpl();
			member.memberList(req, res);
			viewPage="/manager/manager_memberInfo.jsp";
		}else if(url.equals("/deleteMemberAction.mc")) {
			MembersServiceImpl member = new MembersServiceImpl();
			member.adminDeleteMemberAction(req, res);
			viewPage="/manager/deleteMemberAction.jsp";
		}

	 */
}
