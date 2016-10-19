package com.fh.controller.app.appuser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.app.response.ResBase;
import com.fh.controller.base.BaseController;
import com.fh.controller.base.ResponseData;
import com.fh.entity.AnfiApi;
import com.fh.entity.Page;
import com.fh.entity.TestEntity;
import com.fh.entity.system.BaseProductsEntity;
import com.fh.entity.warehouse.orders.JsonRootBean;
import com.fh.entity.warehouse.orders.Order;
import com.fh.entity.warehouse.orderslist.Product;
import com.fh.entity.warehouse.orderslist.ProductRootBean;
import com.fh.service.auto.WooApiGetOrdersService;
import com.fh.service.system.appuser.AppuserService;
import com.fh.service.warehouse.ProductService;
import com.fh.util.CacheUtil;
import com.fh.util.MD5;
import com.fh.util.PageData;

import net.sf.ehcache.Element;

/**
 * 会员-接口类
 * 
 * 
 */
@Controller
@RequestMapping(value = "/appapi")
@SessionAttributes("test")
public class IntAppuserController extends BaseController {
	@Autowired
	private HttpServletRequest request;
	@Resource(name = "appuserService")
	private AppuserService appuserService;
	@Resource(name="autoApiService")
	private WooApiGetOrdersService autoApiService;
	
	
	
	@Resource(name="productService")
	private ProductService productService;
	
	/**
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = { "/version", "/versiontest" }, method = RequestMethod.GET)
	@ResponseBody

	public Object getCurrentVersion() {

		return new ResBase() {
		};

	}
	
	
	
	
	
	/**
	 * 同步订单
	 */
	@RequestMapping(value="/synOrder")
	public ModelAndView listOrders(Page page)throws Exception{
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		autoApiService.sysOrderAnfaOrder();
		
		mv.setViewName("warehouse/orders/orders");
		//mv.setViewName("google.com");
		//mv.addObject("userList", userList);
		//mv.addObject("roleList", roleList);
		//mv.addObject("pd", pd);
		//mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		return mv;
	}

	private boolean checkToken() {
		String token = request.getHeader("password");
		System.out.println("password is "+token);
		boolean rs = false;
		if (StringUtils.isEmpty(token) || !token.equalsIgnoreCase("cs_9c597830c0f6ed61bb157c10106577fb097b402f")) {
			rs = true;
		}

		return rs;
	}
	
	/**
	 * anfa
	 */
	//http://localhost:8080/Warehouse/order/anfaApi/after=2011-07-08
	@RequestMapping(value = "/anfaApi/{start}/{to}", method = RequestMethod.GET)

	@ResponseBody
	public Object getAnfaOrders(@PathVariable String start,@PathVariable String to,HttpServletResponse res)throws Exception{
		if (checkToken()) {
			res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return "please check your password";
		}
		System.out.println();
		//
		if(StringUtils.isEmpty(start)||StringUtils.isEmpty(to)){
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return "pleas input time";
		}
		System.out.println("time is "+start+" end time"+to);
		return autoApiService.getAnfaOrder(new AnfiApi(start,to));
	}
	

	// http://127.0.0.1:8080/Warehouse/appapi/orderApi
	@RequestMapping(value = { "/productApi/{key}" }, method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	
	public void getProduct(@RequestBody ProductRootBean jsonRootBean,@PathVariable String key, HttpServletRequest req) {

		
		Product o = jsonRootBean.getProduct();
		
		
		if(o!=null){
			Element element = CacheUtil.getCacheObject(o.getSku(), "products");
			if (element == null) {
				
				BaseProductsEntity pro= new BaseProductsEntity();
				pro.setName(o.getTitle());
				pro.setSKU(o.getSku());
				pro.setNameCN(o.getTitle());
				try {
					productService.saveProduct(pro);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			
		}
		
		
		//order.setLine_items(o.getLine_items());
		System.out.println("dpt key is "+key);
		//String tmp=o.getStatus();
//		if(StringUtils.isNotEmpty(tmp)){
//			Element element = CacheUtil.getCacheObject(key, "clients");
//			PageData tmPageData = (PageData) element.getObjectValue();
//			int dptint=(Integer) tmPageData.get("Dept_ID");
//			System.out.println("dpt is "+dptint);
//			if(jsonRootBean !=null){
//				System.out.println("this is be invoked id is   "+o .getId());
//				
//				o.setDept_ID(dptint);
//				//System.out.println("this is be invoked ip is   "+o .getCustomer_ip());
//				java.util.List<Order> list= new ArrayList<Order>();
//				list.add(o);
//			
//				try {
//					autoApiService.saveOrdersCommon(list,dptint);
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
		}
	

		// http://127.0.0.1:8080/Warehouse/appapi/orderApi
		@RequestMapping(value = { "/orderApi/{key}" }, method = RequestMethod.POST, produces = {
				"application/json;charset=UTF-8" })
		@ResponseBody
		
		public Object getOrders(@RequestBody JsonRootBean jsonRootBean,@PathVariable String key, HttpServletRequest req) {

			
			Order o = jsonRootBean.getOrder();
			
			//order.setLine_items(o.getLine_items());
			System.out.println("dpt key is "+key);
			String tmp=o.getStatus();
			if(StringUtils.isNotEmpty(tmp)){
				Element element = CacheUtil.getCacheObject(key, "clients");
				PageData tmPageData = (PageData) element.getObjectValue();
				int dptint=(Integer) tmPageData.get("Dept_ID");
				System.out.println("dpt is "+dptint);
				if(jsonRootBean !=null){
					System.out.println("this is be invoked id is   "+o .getId());
					
					o.setDept_ID(dptint);
					//System.out.println("this is be invoked ip is   "+o .getCustomer_ip());
					java.util.List<Order> list= new ArrayList<Order>();
					list.add(o);
				
					try {
						autoApiService.saveOrdersCommon(list,dptint);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		
	
	//	System.out.println(o.getTeString());
////
////		System.out.println("test:&&&&&&&&&&&&&&&&&&&" + o.getOrderKey());
////		System.out.println(o.getPaymentDetails().getMethodTitle());
////		System.out.println(o.getLineItems().size());
////		List<Line_Items> lineItems = o.getLineItems();
//		for (Line_Items l : lineItems) {
//			System.out.println(l.getSku());
//			System.out.println(l.getPrice());
//			System.out.println(l.getQuantity());
//			System.out.println(l.getTotal());
//		}
		return new ResBase() {
		};

	}

	/////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/getTest", method = RequestMethod.POST, produces = { "application/json;charset=UTF-8" })
	@ResponseBody

	public Object getAppuserAll(@RequestBody TestEntity p) {
		logBefore(logger, "TEST @RequestBody");

		return ResponseData.buildSuccessResponseWithMeg("" + p.getName() + p.getRole());

	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTest5/{id}", method = RequestMethod.POST)
	@ResponseBody

	public Object getAppuserAllByCache(@PathVariable String id) {
		logBefore(logger, "TEST cache");

		String s = MD5.md5(id);
		System.out.println(s);
		return ResponseData.buildSuccessResponseWithMeg(s);

	}

	/**
	 * Restful API
	 * 
	 * @param name
	 * @param role
	 * @param phone
	 * @param p
	 * @return
	 */
	@RequestMapping(value = "/getTest2/{name}/{role}", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public Object getAppuserAll2(@PathVariable String name, @PathVariable String role) {
		logBefore(logger, "testAPI");
		return ResponseData.buildSuccessResponseWithMeg("" + name + role);
		/// return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 
	 * @param phone
	 * @param p
	 * @return
	 */

	@RequestMapping(value = "/getTest3", method = RequestMethod.GET)
	@ResponseBody

	public Object getAppuserAll3(@CookieValue(value = "userPhone", required = false) String phone,
			@ModelAttribute("test") TestEntity p) {
		logBefore(logger, "TEST2");
		return ResponseData.buildSuccessResponseWithMeg("" + phone);
		/// return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("springUpload")
	public String springUpload(HttpServletRequest request, @RequestHeader(value = "User-Agent") String userAgent)
			throws IllegalStateException, IOException {
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		if (multipartResolver.isMultipart(request)) {
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();

			while (iter.hasNext()) {
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null) {
					String path = "E:/springUpload" + file.getOriginalFilename();
					// 上传
					file.transferTo(new File(path));
				}

			}

		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "/success";
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("fileUpload2")
	public String fileUpload2(@RequestParam("file") CommonsMultipartFile file) throws IOException {
		long startTime = System.currentTimeMillis();
		System.out.println("fileName：" + file.getOriginalFilename());
		String path = "E:/" + new Date().getTime() + file.getOriginalFilename();

		File newFile = new File(path);
		// 通过CommonsMultipartFile的方法直接写文件（注意这个时候）
		file.transferTo(newFile);
		long endTime = System.currentTimeMillis();
		System.out.println("方法二的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return "/success";
	}

	/**
	 * 根据用户名获取会员信息
	 */
	@RequestMapping(value = "/getAppuserByUm")
	@ResponseBody
	public Object getAppuserByUsernmae() {
		logBefore(logger, "根据用户名获取会员信息");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String result = "00";

		try {

			pd = appuserService.findByUId(pd);

			map.put("pd", pd);
			result = (null == pd) ? "02" : "01";

		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			map.put("result", result);
			logAfter(logger);
		}

		return map;
	}

}
