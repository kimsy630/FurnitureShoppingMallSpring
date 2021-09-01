package spring.mvc.project.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import spring.mvc.project.vo.OrderVO;




public interface OrderDAO {
	public int insertOrder(OrderVO vo);
	public List<OrderVO> orderView(Map<String,Object> map);
	
	@Update("UPDATE orders SET order_state=#{order_state}   WHERE order_id = #{order_id} ")
	public int updateOrderState(Map<String,Object> map);//int order_id,int order_state
	
	@Select("SELECT * FROM orders WHERE order_id = #{order_id} ")
	public OrderVO orderInfo(int order_id);
}
