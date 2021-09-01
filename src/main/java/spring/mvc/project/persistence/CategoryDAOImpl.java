package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.CategorysVo;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
	
	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CategorysVo> categoryView(String search) {
		List<CategorysVo> dto  =sqlSession.selectList("spring.mvc.project.persistence.CategoryDAO.categoryView",search);
		List<CategorysVo> list = new ArrayList<CategorysVo>();
		for(int i=0;i<dto.size();i++) {
			CategorysVo vo=new CategorysVo();
			vo.setCategory_id(dto.get(i).getCategory_id());
			vo.setCategory_parents(dto.get(i).getCategory_parents());
			if(vo.getCategory_parents()==null) {
				list.add(vo);
			}else {
				if(list.get(list.size()-1).getChild()==null)
					list.get(list.size()-1).setChild(new ArrayList<CategorysVo>());
				list.get(list.size()-1).getChild().add(vo);
			}
		}
		return list;
	}
	
	@Override
	public List<CategorysVo> categoryCount(String search) {
		return sqlSession.selectList("spring.mvc.project.persistence.CategoryDAO.categoryCount",search);
	}
}
