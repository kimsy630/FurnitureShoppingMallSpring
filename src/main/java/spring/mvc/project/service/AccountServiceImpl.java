package spring.mvc.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.project.persistence.AccountDAOImpl;
import spring.mvc.project.vo.AccountVO;


@Service
public class AccountServiceImpl  implements AccountService{

	@Autowired
	AccountDAOImpl accountDao;
	
	@Override
	public void accountView(HttpServletRequest req,Model model) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		
		List<AccountVO> list =accountDao.accountMonth(mb_id);
		List<Integer> approved=accountDao.approvedCount(mb_id);
		
		int listMax = 0;
		for(int i=0;i<list.size()-1;i++) {
			if(listMax<list.get(i).getSumorder()) {
				listMax=list.get(i).getSumorder();
			}
		}

		model.addAttribute("approved", approved);
		model.addAttribute("listMax", listMax);
		model.addAttribute("list", list);		
	}

	@Override
	public void adminAccountView(HttpServletRequest req,Model model) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		
		List<AccountVO> list2 =accountDao.accountMonth(mb_id);

		List<AccountVO> list1 =accountDao.adminAccountMonth();
		
		List<AccountVO> list =new ArrayList<AccountVO>();
		
		List<Integer> approved=accountDao.approvedCount(mb_id);
		
		for(int i=0;i<list1.size();i++) {
			AccountVO vo = new AccountVO();
			vo.setHiredate(list1.get(i).getHiredate());
			vo.setSumorder(list1.get(i).getSumorder()+list2.get(i).getSumorder());
			list.add(vo);
		}
		int listMax = 0;
		for(int i=0;i<list.size()-1;i++) {
			if(listMax<list.get(i).getSumorder()) {
				listMax=list.get(i).getSumorder();
			}
		}

		model.addAttribute("approved", approved);
		model.addAttribute("listMax", listMax);
		model.addAttribute("list", list);		
	}

	@Override
	public List<AccountVO> chartList(HttpServletRequest req, Model model) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");
		
		return accountDao.accountMonth(mb_id);
	}

	@Override
	public List<AccountVO> adminChartList(HttpServletRequest req, Model model) {
		String mb_id =(String)req.getSession().getAttribute("mb_id");

		List<AccountVO> list2 =accountDao.accountMonth(mb_id);

		List<AccountVO> list1 =accountDao.adminAccountMonth();
		
		List<AccountVO> list =new ArrayList<AccountVO>();
		
		for(int i=0;i<list1.size();i++) {
			AccountVO vo = new AccountVO();
			vo.setHiredate(list1.get(i).getHiredate());
			vo.setSumorder(list1.get(i).getSumorder()+list2.get(i).getSumorder());
			list.add(vo);
		}
		return list;
	}

	@Override
	public List<AccountVO> testChart(HttpServletRequest req, Model model) {
		return accountDao.getTest();
	}

}
