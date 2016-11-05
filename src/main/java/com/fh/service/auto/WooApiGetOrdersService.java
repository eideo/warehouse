package com.fh.service.auto;

import java.io.IOException;
import java.util.List;
//
//import org.codehaus.jackson.map.ObjectMapper;
//
//import org.codehaus.jackson.map.ObjectMapper;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fh.dao.DaoSupport;
import com.fh.entity.AnfiApi;
import com.fh.entity.warehouse.EnterStock;
import com.fh.entity.warehouse.orders.Line_Items;
import com.fh.entity.warehouse.orderslist.Iterm;
import com.fh.entity.warehouse.orderslist.Order;
import com.fh.service.warehouse.ClientsService;
import com.fh.util.CacheUtil;
import com.fh.util.Const;
import com.fh.util.Const.Status;
import com.fh.util.DateUtil;
import com.fh.util.JsonUtil;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.ehcache.Element;


@Service("autoApiService")
public class WooApiGetOrdersService {
	private String url = Const.url;
	private String username = Const.username;
	private String password = Const.password;
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	@Resource(name = "clientsService")
	private ClientsService clientsService;
	public String getJsonString(String url ,String username,String password) throws UnirestException, JsonParseException, JsonMappingException, IOException {

		HttpResponse<JsonNode> response = Unirest.get(url).basicAuth(username, password).asJson();

	

	return	 response.getBody().getArray().toString();
		
		
	}
	
	
	public void putJsonString(String url ,String body,int num) throws UnirestException, JsonParseException, JsonMappingException, IOException {
     //   url="https://gopost.nz/wp-json/wc/v1/products/4339?consumer_key=ck_e73ecebc956ef311f69691c3123d5c06aa4fb7c0&consumer_secret=cs_1cd279f29a6cab6359efd324d83b3ba44f035914";
		HttpResponse<JsonNode> response = Unirest.put(url).field(body, num).asJson();
		//Unirest.put(url).
		System.out.println( response.getBody().getArray().toString());
		
	}
	
	
	
	
	

	/**
	 * https://myalpha.co.nz/wp-json/wc/v1/orders?per_page=100&after=2016-09-
	 * 22T14:01:54.9571247Z
	 * https://myalpha.co.nz/wp-json/wc/v1/orders?per_page=100&after=2016-09-
	 * 28T17:35:32+1300
	 * 
	 * @return
	 * @throws UnirestException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private List<Order> getOrdersFromAnfa(int num)
			throws UnirestException, JsonParseException, JsonMappingException, IOException {

		String after = DateUtil.getTimeISO_8601(DateUtil.getAfterDayDate(-40));
        String before= DateUtil.getTimeISO_8601(DateUtil.getAfterDayDate(-1));
        String finalurl=this.url + after+"&before="+before+"&page="+num;
	//	HttpResponse<JsonNode> response = Unirest.get(finalurl).basicAuth(this.username, this.password).asJson();

	

		String jsonString = this.getJsonString(finalurl, this.username, this.password);// response.getBody().getArray().toString();
//		System.out.println(jsonString);
//		System.out.println(finalurl);
//		System.out.println("username:" + this.username);
//		System.out.println("password:" + this.password);
		List<Order> lst = JsonUtil.getListFromJson(jsonString, List.class, Order.class);
		System.out.println(lst.size());
		if (lst.isEmpty()) {

			return null;
		}
//		for (Order order : lst) {
//		//System.out.println(order.getDate_created()+order.getDate_created());
//			List<Iterm> iterms = order.getLine_items();
//			if (iterms != null) {
//				for (Iterm iterm : iterms) {
//					iterm.getSku();
//					
//					// System.out.println(iterm.getSku());
//					// System.out.println(iterm.getTotal());
//				}
//			}
//
//		}
		return lst;
	}

	public void saveOrders(List<Order> l, int dept) throws Exception {
		if (l != null && l.size() != 0) {
			for (Order order : l) {
				// dao.findForObject("WarehouseMapper.checkOrder", order);
				// System.out.println(order.getId()+order.getDate_created());
				order.setOriginal_ID(order.getId());
				order.setDept_ID(dept);
                  Object tmp =dao.findForObject("WarehouseMapper.checkOrder", order);
				 int m = (tmp==null)?0:(int)tmp;

				// System.out.println(dao.findForObject("WarehouseMapper.checkOrder",
				// order));

				if (m == 0) {
					String remark = order.getBilling().getCity() + "," + order.getBilling().getCompany();

					order.setRemark(remark);
					order.setDept_ID(dept);
					dao.save("WarehouseMapper.saveOrder", order);
					// System.out.println("productid======"+order.getId());
					List<Iterm> iterms = order.getLine_items();
					for (Iterm iterm : iterms) {
						
						
						Element element = CacheUtil.getCacheObject(iterm.getSku(), "products");
						if (element == null) {
							System.out.println("not this sku "+iterm.getSku());
							continue;
						}
						
						
						
						PageData tmPageData = (PageData) element.getObjectValue();

						int productid = (Integer) tmPageData.get("Product_ID");
		
						iterm.setOrder_ID(order.getId());

						iterm.setProduct_ID(productid);
						// System.out.println("ppp===="+productid);

					}
					dao.save("WarehouseMapper.saveOrderitems", iterms);

				}

			}
		}
	}
    /**
     * 
     * @param l
     * @param dept
     * @throws Exception
     */
	public void saveOrdersCommon(List<com.fh.entity.warehouse.orders.Order> l, int dept) throws Exception {
		if (l != null && l.size() != 0) {
			EnterStock e=null;
			for (com.fh.entity.warehouse.orders.Order order : l) {
				// dao.findForObject("WarehouseMapper.checkOrder", order);
				// System.out.println(order.getId()+order.getDate_created());
				order.setOriginal_ID(order.getOrder_number());
				order.setDept_ID(dept);
				  Object tmp =dao.findForObject("WarehouseMapper.checkOrder", order);
					 int m = (tmp==null)?0:(int)tmp;
				

				// System.out.println(dao.findForObject("WarehouseMapper.checkOrder",
				// order));
					 int t=Status.getIndex( order.getStatus());
					 order.setStatusInt(t);
					 clientsService.saveAutoOrderStock(order);
					
					// saveAutoOrderStock
				if (m == 0) {
					order.setStatusInt(t);
			
					dao.save("WarehouseMapper.saveOrderCommon", order);

					List<Line_Items> iterms = order.getLine_items();
					for (Line_Items iterm : iterms) {
					Element element=	CacheUtil.getCacheObject( iterm.getSku(), "products");
					int productid=0;
					if(element==null){
						continue;
					}else{
						PageData tmPageData = (PageData) element.getObjectValue();

						 productid = (Integer) tmPageData.get("Product_ID");
					}
					//	int productid = (int) dao.findForObject("WarehouseMapper.searchProduct", iterm.getSku());
					
						iterm.setOrder_ID(order.getId());

						iterm.setProduct_ID(productid);


					}
					order.setLine_items(iterms); 
					dao.save("WarehouseMapper.saveCommonOrderitems", iterms);

				} else {
					PageData pD = new PageData();
					pD.put("Order_ID", m);
					//int t=Status.getIndex( order.getStatus());
					pD.put("Status", t);
					dao.update("WarehouseMapper.updateOrderstatus", pD);
				}

			}
		}
	}

	/**
	 * 
	 * @param time
	 * @return
	 * @throws Exception
	 */
	public List<Order> getAnfaOrder(AnfiApi time) throws Exception {
		List<Order> list = (List<Order>) dao.findForList("WarehouseMapper.getOrdersQuery", time);
		System.out.println("list size is              " + list.size());
		return list;

	}

	public void sysOrderAnfaOrder()
			throws JsonParseException, JsonMappingException, UnirestException, IOException, Exception {
		System.out.println("this method be invoked22");
		int num=1;
		List<Order> list =this.getOrdersFromAnfa(num);
	
		while (list!=null&&list.size()!=0) {
			this.saveOrders(list,2);
			num++;
			list =this.getOrdersFromAnfa(num);
		}
		//this.saveOrders(this.getOrdersFromAnfa(), 2);
	}

	public static void main(String[] args) {
		try {
			WooApiGetOrdersService wooApiGetOrders = new WooApiGetOrdersService();
		//	wooApiGetOrders.getOrdersFromAnfa();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// Unirest.get("http://httpbin.org/headers").basicAuth("username",
	// "password").asJson();

}
