package spring.mvc.project.persistence;


import java.util.List;

import spring.mvc.project.vo.CategorysVo;
public interface CategoryDAO {
	public List<CategorysVo> categoryView(String search);
	public List<CategorysVo> categoryCount(String search);
}
