package spring.mvc.project.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spring.mvc.project.vo.CategorysVo;
import spring.mvc.project.vo.OrderVO;
import spring.mvc.project.vo.ProductsVO;
import spring.mvc.project.vo.SearchVO;


public interface ProductDAO {
	
	public List<ProductsVO> newProduct();
	
	public List<ProductsVO> productSellerList(Map<String,String> map);

	public List<ProductsVO> productAdminList(int type);
	
	public int insertProduct(ProductsVO vo);
	
	public int updateProduct(ProductsVO vo);

	public List<ProductsVO> productList(SearchVO searchVO);

	public List<CategorysVo> categoryListCount(String category);
	
	public List<CategorysVo> categoryCount(String category);

	public int productCount(SearchVO searchVO);
	
	public Map<String,Object> categoryChildCheck(String category);
	
	public int deleteProducte(int p_id);
	
	@Select("SELECT * FROM products  WHERE p_id= #{p_id}")
	public ProductsVO productDetail(int p_id);
	
	@Update("UPDATE  products SET p_readCount=p_readCount+1 WHERE p_id= #{p_id}")
	public int  productReadCount(int p_id);

	public int productOrderCheck(List<OrderVO> vo);
	
	public int productStockCheck(List<OrderVO> vo);
	
	public void productOrderUpdate(OrderVO vo); 
	
	@Update("UPDATE products SET p_count = p_count+#{count} WHERE p_id=#{p_id} AND 0<=p_count+#{count}")
	public void productOrder(Map<String,Object> map);//int p_id,int count
	
}
