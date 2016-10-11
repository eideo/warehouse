package com.fh.controller.warehouse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.BaseProductsEntity;
import com.fh.service.system.menu.MenuService;
import com.fh.service.warehouse.ProductService;
import com.fh.util.Const;
import com.fh.util.FileUpload;
import com.fh.util.ObjectExcelRead;
import com.fh.util.PageData;
import com.fh.util.PathUtil;


/** 
 * 类名称：UserController
 * 创建人：FH 
 * 创建时间：2016年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/products")
public class ProductController extends BaseController {
	@Resource(name="productService")
	private ProductService productService;
	String menuUrl = "product/listProduct.do"; //菜单地址(权限用)
//	@Resource(name="userService")
//	private UserService userService;
//	@Resource(name="roleService")
//	private RoleService roleService;
//	@Resource(name="menuService")

	

	
	/**
	 * 显示产品列表
	 */
	@RequestMapping(value="/listProduct1")
	public ModelAndView listProducts(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
	
		mv.setViewName("warehouse/products/products");
		//mv.setViewName("google.com");
		//mv.addObject("userList", userList);
		//mv.addObject("roleList", roleList);
		//mv.addObject("pd", pd);
		//mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	
	
	
	

	/**
	 * 显示产品列表(tab方式)
	 */
	@RequestMapping(value="/listProduct")
	public ModelAndView listtabUsers(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData>	productsList = productService.listAllProducts(pd);			//列出用户列表
		mv.setViewName("warehouse/products/products");
		mv.addObject("productsList", productsList);
		System.out.println("testlist:================"+productsList.size());
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}
	/**
	 * 从EXCEL导入到数据库
	 */
	@RequestMapping(value="/readExcel")
	public ModelAndView readExcel(
			@RequestParam(value="csv_file",required=false) MultipartFile file
			) throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
	//	if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;}
	
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE;								//文件上传路径
			String fileName =  FileUpload.fileUp(file, filePath, "productexcel");							//执行上传
			
			List<PageData> listPd = (List)ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0);	//执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet
			
			/*存入数据库操作======================================*/
			pd.put("RIGHTS", "");					//权限
			pd.put("LAST_LOGIN", "");				//最后登录时间
			pd.put("IP", "");						//IP
			pd.put("STATUS", "0");					//状态
			pd.put("SKIN", "default");				//默认皮肤
			
		//	List<Role> roleList = roleService.listAllERRoles();	//列出所有二级角色
			
		//	pd.put("ROLE_ID", roleList.get(0).getROLE_ID());	//设置角色ID为随便第一个
			/**
			 * var0 :SKU
			 * var1 :Name
			 * var2 :Name_CN
			 */
			BaseProductsEntity bf= new BaseProductsEntity();
			for (PageData pageData : listPd) {
				
				String sku=pageData.getString("var0").trim();
				System.out.println(sku);
				System.out.println(pageData.getString("var1").trim());
				System.out.println(pageData.getString("var2").trim());
			
//				pd.put("NAME", pageData.getString("var1"));	
//				pd.put("Name_CN", pageData.getString("var2"));	
//				pd.put("SKU", pageData.getString("var0"));	
				bf.setName(pageData.getString("var1").trim());
				bf.setNameCN(pageData.getString("var2").trim());
				bf.setSKU(sku);
				
				
				productService.saveProduct(bf);
				
			}
		
			/*存入数据库操作======================================*/
			
			mv.addObject("msg","success");
		}
		
		mv.setViewName("warehouse/products/products");
		return mv;
	}
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	

	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
}
