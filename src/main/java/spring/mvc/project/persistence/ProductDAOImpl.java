package spring.mvc.project.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.project.vo.CategorysVo;
import spring.mvc.project.vo.OrderVO;
import spring.mvc.project.vo.ProductsVO;
import spring.mvc.project.vo.SearchVO;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<ProductsVO> newProduct(){
		return sqlSession.selectList("spring.mvc.project.persistence.ProductDAO.newProduct");
	}
	
	@Override
	public List<ProductsVO> productSellerList(Map<String, String> map) {
		return sqlSession.getMapper(ProductDAO.class).productSellerList(map);
	}

	@Override
	public List<ProductsVO> productAdminList(int type) {
		return sqlSession.getMapper(ProductDAO.class).productAdminList(type);
	}
	

	@Override
	public int insertProduct(ProductsVO vo) {
		return  sqlSession.getMapper(ProductDAO.class).insertProduct(vo);
	}
	@Override
	public int updateProduct(ProductsVO vo) {
		return  sqlSession.getMapper(ProductDAO.class).updateProduct(vo);
	}
	
	@Override
	public List<ProductsVO> productList(SearchVO searchVO) {
		if(searchVO.getCategory()!=null)System.out.println("category : "+searchVO.getCategory());
		if(searchVO.getSearch()!=null)System.out.println("search : "+searchVO.getSearch());
		System.out.println("type : "+searchVO.getType());
		return  sqlSession.getMapper(ProductDAO.class).productList(searchVO);
	}
	


	@Override
	public int productCount(SearchVO searchVO) {	
		return  sqlSession.getMapper(ProductDAO.class).productCount(searchVO);
	}

	@Override
	public Map<String, Object> categoryChildCheck(String category) {
		return  sqlSession.getMapper(ProductDAO.class).categoryChildCheck(category);
	}
	
	@Override
	public List<CategorysVo> categoryListCount(String category){
		return sqlSession.getMapper(ProductDAO.class).categoryListCount(category);
	}
	
	@Override
	public List<CategorysVo> categoryCount(String category) {
		Map<String,Object> map = categoryChildCheck(category);
	
		if(Integer.parseInt(map.get("LEAF").toString())!=0){//자식이있을경우 0
			category= map.get("CATEGORY_PARENTS").toString();
		}
		System.out.println(category);
		return categoryListCount(category);
	}


	@Override
	public int deleteProducte(int p_id) {
		return sqlSession.getMapper(ProductDAO.class).deleteProducte(p_id);
	}
	@Override
	public ProductsVO productDetail(int p_id) {
		return sqlSession.getMapper(ProductDAO.class).productDetail(p_id);
	}

	@Override
	public int productReadCount(int p_id) {
		return sqlSession.getMapper(ProductDAO.class).productReadCount(p_id);
	}
	

	@Override
	public int productStockCheck(List<OrderVO> vo) {
		return sqlSession.getMapper(ProductDAO.class).productStockCheck(vo);
	}

	@Override
	public void productOrderUpdate(OrderVO vo) {
		sqlSession.getMapper(ProductDAO.class).productOrderUpdate(vo);
	}
	@Override
	public int productOrderCheck(List<OrderVO> list) {
		int count = productStockCheck(list);
		if(count ==list.size())
			for(int i=0;i<list.size();i++) 
				productOrderUpdate(list.get(i));
		else count = 0; 
		return count;
	}
	
	@Override
	public void productOrder(Map<String,Object> map) {
		sqlSession.getMapper(ProductDAO.class).productOrder(map);
	}
}
