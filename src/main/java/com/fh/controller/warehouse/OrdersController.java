package com.fh.controller.warehouse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.fh.controller.base.BaseController;
import com.fh.service.system.menu.MenuService;
import com.fh.util.Const;

/** 
 * 类名称：UserController
 * 创建人：FH 
 * 创建时间：2016年6月28日
 * @version
 */
@Controller
@RequestMapping(value="/order")
public class OrdersController extends BaseController {
	
	String menuUrl = "order/synOrder.do"; //菜单地址(权限用)
//	@Resource(name="userService")
//	private UserService userService;
//	@Resource(name="roleService")
//	private RoleService roleService;
	@Resource(name="menuService")
	private MenuService menuService;
	
	

	
	
	
	
	
	
	
	
	
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
