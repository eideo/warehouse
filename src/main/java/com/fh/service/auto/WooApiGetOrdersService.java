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
import com.fh.entity.warehouse.orderslist.Iterm;
import com.fh.entity.warehouse.orderslist.Order;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.JsonUtil;
import com.fh.util.Logger;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;



@Service("autoApiService")
public class WooApiGetOrdersService {
	private   String url=Const.url;
	private   String username=Const.username;
	private   String password=Const.password;
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "daoSupport")
	private DaoSupport dao;
   /**
    * https://myalpha.co.nz/wp-json/wc/v1/orders?per_page=100&after=2016-09-22T14:01:54.9571247Z
    * https://myalpha.co.nz/wp-json/wc/v1/orders?per_page=100&after=2016-09-28T17:35:32+1300
    * @return
    * @throws UnirestException
 * @throws IOException 
 * @throws JsonMappingException 
 * @throws JsonParseException 
    */
	private  List<Order> getOrdersFromAnfa() throws UnirestException, JsonParseException, JsonMappingException, IOException{

		String date=DateUtil.getTimeISO_8601(DateUtil.getAfterDayDate(-1));
	
		HttpResponse<JsonNode>  response =Unirest.get(this.url+date).basicAuth(this.username, this.password).asJson();
		
		System.out.println(" :"+this.url+date);
		System.out.println("username:"+this.username);
		System.out.println("password:"+this.password);
		
		String jsonString =response.getBody().getArray().toString();
		System.out.println(jsonString);
		List<Order> lst=	JsonUtil.getListFromJson(jsonString, List.class, Order.class);
		if(lst.isEmpty()){
			
			return null;
		}
		         for (Order order : lst) {	        	
		        	System.out.println(order.getBilling().getAddress_1()+order.getBilling().getPhone());
		        	 List<Iterm> iterms=order.getLine_items();
		        	if(iterms!=null){
		        		for (Iterm iterm : iterms) {
							iterm.getSku();
//							System.out.println(iterm.getSku());
//							System.out.println(iterm.getTotal());
						}
		        	}
					
				}
		return lst;
	}
	
	
	public void saveOrders(List<Order> l,int dept) throws Exception{
		if(l!=null&&l.size()!=0){
		for (Order order : l) {
		//	dao.findForObject("WarehouseMapper.checkOrder", order);
			//System.out.println(order.getId()+order.getDate_created());
			order.setOriginal_ID(order.getId());
			order.setDept_ID(dept);
			
			int m=(int)dao.findForObject("WarehouseMapper.checkOrder", order);
			
			
			
			
		//	System.out.println(dao.findForObject("WarehouseMapper.checkOrder", order));
			
			if(m==0){
			  String remark=order.getBilling().getCity()+","+order.getBilling().getCompany();
			
				order.setRemark(remark);
				order.setDept_ID(dept);
				dao.save("WarehouseMapper.saveOrder", order);
				//System.out.println("productid======"+order.getId());
				List<Iterm> iterms = order.getLine_items();
				for (Iterm iterm : iterms) {
					int productid =(int)dao.findForObject("WarehouseMapper.searchProduct", iterm.getSku());
					//System.out.println("sku+++++++++++++++++++++++++"+iterm.getSku());
					iterm.setOrder_ID(order.getId());
					
					iterm.setProduct_ID(productid);
					//System.out.println("ppp===="+productid);
					
				}
				dao.save("WarehouseMapper.saveOrderitems", iterms);
				
				
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
	public List<Order> getAnfaOrder(AnfiApi time) throws Exception{
		List<Order> list=(List<Order>) dao.findForList("WarehouseMapper.getOrdersQuery", time);
		System.out.println("list size is              "+list.size());
		return list;
		
		
		
	}
	
	public void sysOrderAnfaOrder() throws JsonParseException, JsonMappingException, UnirestException, IOException, Exception{
		System.out.println("this method be invoked22");
		this.saveOrders(this.getOrdersFromAnfa(),2);
	}
	
	
	public static void main(String[] args) {
	try {
		WooApiGetOrdersService wooApiGetOrders = new WooApiGetOrdersService();
	wooApiGetOrders.getOrdersFromAnfa();
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	
}
			

		
			
			//Unirest.get("http://httpbin.org/headers").basicAuth("username", "password").asJson();

}
