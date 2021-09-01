package spring.mvc.project.vo;

import java.util.ArrayList;

public class CategorysVo {
    private String category_id;
    private String category_parents;
    private ArrayList<CategorysVo>child;
    private int cnt;
    
	public String getCategory_id() {
		return category_id;
	}
	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	public String getCategory_parents() {
		return category_parents;
	}
	public void setCategory_parents(String category_parents) {
		this.category_parents = category_parents;
	}
	public ArrayList<CategorysVo> getChild() {
		return child;
	}
	public void setChild(ArrayList<CategorysVo> child) {
		this.child = child;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
	
}
