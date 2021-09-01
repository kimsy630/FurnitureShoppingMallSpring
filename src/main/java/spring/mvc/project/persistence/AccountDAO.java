package spring.mvc.project.persistence;

import java.util.List;

import spring.mvc.project.vo.AccountVO;


public interface AccountDAO {
	public List<AccountVO> accountMonth(String mb_id);
	
	public List<Integer> approvedCount(String mb_id);

	public List<AccountVO> adminAccountMonth();
	
	public List<AccountVO> getTest();
	
}
