package spring.mvc.project.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spring.mvc.project.vo.CartVO;

public interface CartDAO {
	public int insertCart(CartVO vo);
	
	@Select("select cart_count  from carts  where mb_id = #{mb_id} and p_id = #{p_id}")
	public Object selectCart(Map<String, Object> map);//String mb_id,int p_id

	@Update("UPDATE carts  SET cart_count =  #{count} WHERE  mb_id =  #{mb_id} and p_id =#{p_id} ")
	public int updateCart(Map<String, Object> map);//String mb_id,int p_id, int count

	public List<CartVO> memberCartList(String mb_id);
	
	@Delete("DELETE carts WHERE cart_id=#{num}")
	public int deleteCart(int num);
	
	public void OderDeleteCart(List<Object> list);
	
}
