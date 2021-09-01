package spring.mvc.project.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import spring.mvc.project.persistence.ProductDAOImpl;
import spring.mvc.project.vo.CategorysVo;
import spring.mvc.project.vo.ProductsVO;
import spring.mvc.project.vo.SearchVO;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	ProductDAOImpl productDao;
	
	@Override
	public void indexView(HttpServletRequest req, Model model) {
		List<ProductsVO>newlist = productDao.newProduct();
		model.addAttribute("newlist", newlist);
	}
	@Override
	public void ProductSellerList(HttpServletRequest req,  Model model) {
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int type=0;
		if(req.getParameter("type")!=null) {
			type=Integer.parseInt(req.getParameter("type"));
		}
		Map<String, String> map=new HashMap<String, String>();
		map.put("mb_id",mb_id);
		map.put("type",Integer.toString(type));
		List<ProductsVO> dto= productDao.productSellerList(map);
		model.addAttribute("dto", dto);
	}

	@Override
	public void productadminList(HttpServletRequest req,Model model) {
		String mb_id=(String)req.getSession().getAttribute("mb_id");
		int type=0;
		if(req.getParameter("type")!=null) {
			type=Integer.parseInt(req.getParameter("type"));
		}
		List<ProductsVO> dto= productDao.productAdminList(type);
		model.addAttribute("dto", dto);
	}
	@Override
	public void addProduct(MultipartHttpServletRequest req,Model model){
		String[] p_image= new String[3];
		for(int i=1;i<=3;i++) {
			MultipartFile file = req.getFile("p_image"+i);
			String saveDir = req.getRealPath("/resources/images/");
			String realDir = req.getRealPath("/images/");
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));
				
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
					
				int data = 0;
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
			}catch (Exception e) {
				e.getStackTrace();
			}
			
			String fileName = file.getOriginalFilename();
			p_image[i-1]=file.getOriginalFilename();
		}
		
			
		// 파일명 문자열로 가져오기
		// 파일 업로드 
		
		
        String mb_id=(String)((HttpServletRequest) req).getSession().getAttribute("mb_id");
		String p_name=req.getParameter("p_name");
		String p_category=req.getParameter("p_category");
		int p_count=Integer.parseInt(req.getParameter("p_count"));
		int p_price=Integer.parseInt(req.getParameter("p_price"));
		int p_saleprice=0;
		if(req.getParameter("p_saleprice")!=null)
			p_saleprice=Integer.parseInt(req.getParameter("p_saleprice"));
		

		
		ProductsVO vo = new ProductsVO();
		vo.setMb_id(mb_id);
		vo.setP_name(p_name);
		vo.setP_category(p_category);
		vo.setP_count(p_count);
		vo.setP_price(p_price);
		vo.setP_saleprice(p_saleprice);
		vo.setP_image(p_image[0]);
		vo.setP_image2(p_image[1]);
		vo.setP_image3(p_image[2]);
		vo.setP_date(new Timestamp(System.currentTimeMillis()));
		int insertP=productDao.insertProduct(vo);
		
		model.addAttribute("insertP",insertP);
	}
	@Override
	public void updateproductAction(MultipartHttpServletRequest req,  Model model){
		String[] p_image= new String[3];
		
		for(int i=1;i<=3;i++) {
			MultipartFile file = req.getFile("p_image"+i);
			String saveDir = req.getRealPath("/resources/images/");
			String realDir = req.getRealPath("/images/");
			try {
				file.transferTo(new File(saveDir + file.getOriginalFilename()));
				
				FileInputStream fis = new FileInputStream(saveDir + file.getOriginalFilename());
				FileOutputStream fos = new FileOutputStream(realDir + file.getOriginalFilename());
					
				int data = 0;
				while((data = fis.read()) != -1) {
					fos.write(data);
				}
				fis.close();
				fos.close();
			}catch (Exception e) {
				e.getStackTrace();
			}
			
			String fileName = file.getOriginalFilename();
			System.out.println(fileName);
			p_image[i-1]=file.getOriginalFilename();
		}
		
		String p_name=req.getParameter("p_name");
		String p_category=req.getParameter("p_category");
	    int p_id=Integer.parseInt(req.getParameter("p_id"));
		int p_count=Integer.parseInt(req.getParameter("p_count"));
		int p_price=Integer.parseInt(req.getParameter("p_price"));
		int p_saleprice=Integer.parseInt(req.getParameter("p_saleprice"));
		
		 
		ProductsVO vo = new ProductsVO();
		vo.setP_id(p_id);
		vo.setP_name(p_name);
		vo.setP_category(p_category);
		vo.setP_count(p_count);
		vo.setP_price(p_price);
		vo.setP_saleprice(p_saleprice);
		vo.setP_image(p_image[0]);
		vo.setP_image2(p_image[1]);
		vo.setP_image3(p_image[2]);
		
		int updateP=productDao.updateProduct(vo);
		model.addAttribute("updateP",updateP);
	}
	@Override
	public void productCategoryList(HttpServletRequest req,Model model) {
		int pageSize=15;		//한페이지당 출력할 글 갯수
		int pageBlock=5;	//한블럭당 페이지 갯수 
		int cnt	=0;			//글갯수
		int start = 0;  	//현재 페이지 시작 글번호
		int end=0;			//현재 페이지 마지막 글번호
		int number=0;		//출력용 글번호
		String pageNum="";	//페이지 번호
		int currentPage=0;	//현재페이지
		
		int pageCount=0;	//페이지 갯수
		int startPage=0;	//시작페이지
		int endPage=0;		//마지막페이지
		
		int type=1;
		SearchVO search=new SearchVO();
		search.setCategory(null);
		if(req.getParameter("category")!=null)search.setCategory(req.getParameter("category"));
		if(req.getParameter("type")!=null) type =Integer.parseInt(req.getParameter("type"));
		if(req.getParameter("search")!=null) search.setSearch(req.getParameter("search"));
		search.setType(type);
		
		cnt=productDao.productCount(search);
		pageNum=req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum="1";
		}
		
		currentPage = Integer.parseInt(pageNum);
		pageCount=(cnt/pageSize)+(cnt%pageSize>0?1:0);
		start=(currentPage-1)*pageSize+1;
		end=start+pageSize-1;
		number= cnt-(currentPage-1)*pageSize;
		if(cnt>0) {
			search.setStart(start);
			search.setEnd(end);
			//List<ProductsVO> dtos=dao.productCategoryList(start, end, category, type);
			List<ProductsVO> dtos= productDao.productList(search);
			model.addAttribute("dtos",dtos);
		}

		startPage=(currentPage/pageBlock)*pageBlock+1;
		
		if(currentPage%pageBlock==0)startPage-=pageBlock;

		endPage=startPage+pageBlock-1;
		if(endPage>pageCount)endPage=pageCount;
		

		if(search.getCategory()!=null) {
			List<CategorysVo> categortCountList= productDao.categoryCount(search.getCategory());
			model.addAttribute("categortCountList",categortCountList);
		}
		model.addAttribute("cnt", cnt);//글갯수
		model.addAttribute("number", number);//출력용 글번호
		model.addAttribute("pageNum", pageNum);//페이지 번호
		model.addAttribute("type", type);//검색타입
		
		if(search.getCategory()!=null)model.addAttribute("category", search.getCategory());//페이지 번호
		if(search.getSearch()!=null)model.addAttribute("search", search.getSearch());//페이지 번호

		if(cnt>0) {
			model.addAttribute("startPage", startPage);//시작페이지
			model.addAttribute("endPage", endPage);//마지막페이지
			model.addAttribute("pageBlock", pageBlock);//한 블럭당 페이지 갯수
			model.addAttribute("pageCount", pageCount);//페이지 갯수
			model.addAttribute("currentPage", currentPage);//현제페이지
		}
	}
	@Override
	public void deleteproductAction(HttpServletRequest req, Model model) {
		
	    String[] deleteList =req.getParameterValues("chioce");		
		
		for(int i=0;i<deleteList.length;i++) {
			System.out.println(deleteList[i]);
			int num = productDao.deleteProducte(Integer.parseInt(deleteList[i]));
			System.out.println("num : "+num);
		}
		
		//req.setAttribute("deleteP", );
	}

	@Override
	public void productInfo(HttpServletRequest req,Model model) {
		int p_id=Integer.parseInt( req.getParameter("p_id"));
		productDao.productReadCount(p_id);
		ProductsVO vo=productDao.productDetail(p_id);
		req.setAttribute("vo", vo);
	}


}
