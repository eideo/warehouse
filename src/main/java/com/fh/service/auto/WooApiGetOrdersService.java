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
import com.fh.entity.LatiPay;
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
import com.fh.util.LatipayConfig;
import com.fh.util.LatipayUtils;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import net.sf.ehcache.Element;

@Service("autoApiService")
public class WooApiGetOrdersService {
	private String urlAnfa = Const.url;
	private String username = Const.username;
	private String password = Const.password;
	protected Logger logger = Logger.getLogger(this.getClass());
	@Resource(name = "daoSupport")
	private DaoSupport dao;

	@Resource(name = "clientsService")
	private ClientsService clientsService;

	public String getJsonString(String url, String username, String password)
			throws UnirestException, JsonParseException, JsonMappingException, IOException {
     

        url=  Const.getUrlBaseOnKey(url,username,password);
        System.out.println(""+url);
		HttpResponse<JsonNode> response = Unirest.get(url).basicAuth(username, password).asJson();
		if(response.getStatus()!=200){
			return null;
		}
		return response.getBody().getArray().toString();

	}

	/**
	 * 
	 * @param url
	 * @param body
	 * @param num
	 * @throws UnirestException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void putJsonString(String url, String body, Object num)
			throws UnirestException, JsonParseException, JsonMappingException, IOException {
		// url="https://gopost.nz/wp-json/wc/v1/products/4339?consumer_key=ck_e73ecebc956ef311f69691c3123d5c06aa4fb7c0&consumer_secret=cs_1cd279f29a6cab6359efd324d83b3ba44f035914";
		HttpResponse<JsonNode> response = Unirest.put(url).field(body, num).asJson();
		System.out.println("url  " + url);
		System.out.println("body  " + body);
		System.out.println("num  " + num);
		// Unirest.put(url).

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
	private List<Order> getOrdersFromAnfa(String url, int num, String status)
			throws UnirestException, JsonParseException, JsonMappingException, IOException {

		String after = DateUtil.getTimeISO_8601(DateUtil.getAfterDayDate(-10));
		String before = DateUtil.getTimeISO_8601(DateUtil.getAfterDayDate(0));
		String finalurl = null;
		if (url != null) {
			finalurl = url + status + "&after=" + after + "&before=" + before + "&page=" + num;
		} else {
			finalurl = this.urlAnfa + status + "&after=" + after + "&before=" + before + "&page=" + num;
		}
		String jsonString = this.getJsonString(finalurl, this.username, this.password);// response.getBody().getArray().toString();
		List<Order> lst = JsonUtil.getListFromJson(jsonString, List.class, Order.class);
		System.out.println("the final url is  "+finalurl);
		if (lst==null||lst.isEmpty()) {

			return null;
		}
	
		return lst;
	}

	/**
	 * 
	 * @param l
	 * @param dept
	 * @throws Exception
	 */
	public void saveOrders(List<Order> l, int dept) throws Exception {
		if (l != null && l.size() != 0) {
			for (Order order : l) {
				// dao.findForObject("WarehouseMapper.checkOrder", order);
				// System.out.println(order.getId()+order.getDate_created());
				if(order.getId()==0){
					continue;
				}
				order.setOriginal_ID(order.getId());
				order.setDept_ID(dept);

				PageData tmp = (PageData) dao.findForObject("WarehouseMapper.checkOrder", order);
				int m = (tmp == null) ? 0 : (int) tmp.get("Order_ID");

				// System.out.println(dao.findForObject("WarehouseMapper.checkOrder",
				// order)); date_completed

				if (m == 0) {
					String remark = order.getBilling().getCity() + "," + order.getBilling().getCompany();

					order.setRemark(remark);
					order.setDept_ID(dept);
					dao.save("WarehouseMapper.saveOrder", order);
					// System.out.println("productid======"+order.getId());
					List<Iterm> iterms = order.getLine_items();
					int id = order.getId();
					for (Iterm iterm : iterms) {

						iterm.setOrder_ID(id);
						Element element = CacheUtil.getCacheObject(iterm.getSku(), "products");
						if (element == null) {
							System.out.println("not this sku " + iterm.getSku());
							continue;
						}

						PageData tmPageData = (PageData) element.getObjectValue();

						int productid = (Integer) tmPageData.get("Product_ID");

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
			EnterStock e = null;
			for (com.fh.entity.warehouse.orders.Order order : l) {
				// dao.findForObject("WarehouseMapper.checkOrder", order);
				// System.out.println(order.getId()+order.getDate_created());
				order.setOriginal_ID(order.getOrder_number());
				order.setDept_ID(dept);
				PageData tmp = (PageData) dao.findForObject("WarehouseMapper.checkOrder", order);
				int m = (tmp == null) ? 0 : (int) tmp.get("Order_ID");

				int t = Status.getIndex(order.getStatus());

				order.setStatusInt(t);

				System.out.println("this is the order status " + order.getStatus());
				// saveAutoOrderStock
				if (m == 0) {

					dao.save("WarehouseMapper.saveOrderCommon", order);

					List<Line_Items> iterms = order.getLine_items();
					for (Line_Items iterm : iterms) {
						iterm.setOrder_ID(order.getId());

						Element element = CacheUtil.getCacheObject(iterm.getSku().trim(), "products");
						// int productid = 0;
						if (element == null) {
							System.out.println("element is null");
							continue;
						} else {
							PageData tmPageData = (PageData) element.getObjectValue();

							int productid = (Integer) tmPageData.get("Product_ID");

							iterm.setProduct_ID(productid);
						}
						// int productid = (int)
						// dao.findForObject("WarehouseMapper.searchProduct",
						// iterm.getSku());

					}
					order.setLine_items(iterms);
					clientsService.saveAutoOrderStock(order, 0);
					dao.save("WarehouseMapper.saveCommonOrderitems", iterms);

				} else {
					int sta = Integer.valueOf((String) tmp.get("Status"));
					if (sta != 1) {
						PageData pD = new PageData();
						pD.put("Order_ID", m);
						// int t=Status.getIndex( order.getStatus());
						pD.put("Status", t);
						if (t == 1) {
							clientsService.saveAutoOrderStock(order, sta);
						}
						dao.update("WarehouseMapper.updateOrderstatus", pD);
					}
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
		// System.out.println("list size is " + list.size());
		return list;

	}

	public void sysOrderAnfaOrder()
			throws JsonParseException, JsonMappingException, UnirestException, IOException, Exception {

		int num = 1;
		List<Order> list = this.getOrdersFromAnfa(null, num, "completed");

		while (list != null && list.size() != 0) {
			this.saveOrders(list, 2);
			num++;
			list = this.getOrdersFromAnfa(null, num, "completed");
		}

	}

	public void checkOrderAnfa()
			throws JsonParseException, JsonMappingException, UnirestException, IOException, Exception {

		int num = 1;
		LatiPay latiPay = null;
		List<Order> list = this.getOrdersFromAnfa(Const.testurl, num, "pending");

		while (list != null && list.size() != 0) {
			System.out.println("size is "+list.size());
			num++;
		
			if (list != null && list.size() != 0) {
				for (Order order : list) {
					System.out.println("it get order id   " + order.getId());
					latiPay = this.invokeLatipayOrder(String.valueOf(order.getId()));
					if (latiPay != null&&latiPay.getStatus()!=null && latiPay.getStatus().equalsIgnoreCase("paid")) {
						autoUpdateAnfaOrder(order.getId());
					}
				}
				list = this.getOrdersFromAnfa(Const.testurl, num, "pending");

			}
		}

	}

	/**
	 * 
	 * @param orderId
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws UnirestException
	 * @throws IOException
	 */
	private void autoUpdateAnfaOrder(int orderId)
			throws JsonParseException, JsonMappingException, UnirestException, IOException {

		// 'status' => 'completed'

		String urlPut = Const.basetesturl + "orders/" + orderId + "?consumer_key=" + Const.username
				+ "&consumer_secret=" + Const.password;
		// consumer_key=ck_e73ecebc956ef311f69691c3123d5c06aa4fb7c0&consumer_secret=cs_1cd279f29a6cab6359efd324d83b3ba44f035914";
		System.out.println(urlPut);

		this.putJsonString(urlPut, "status", "completed");
	}

	/**
	 * 
	 * @param orderId
	 * @throws UnirestException
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	private LatiPay invokeLatipayOrder(String orderId)
			throws UnirestException, JsonParseException, JsonMappingException, IOException {

		// orderId+merchantCode+key
		LatiPay rs = null;
		
		String url = 	LatipayConfig.getUrlBaseOnOrderID(orderId);
//		String date = DateUtil.getDays();
//		orderId = date + "-" + LatipayConfig.Merchant_Code + "-" + orderId;
//		System.out.println(orderId);
//
//		String text = orderId + LatipayConfig.Merchant_Code + LatipayConfig.key;
//
//		String url = "https://merchant.latipay.co.nz/api/search.action?orderId=" + orderId + "&merchantCode="
//				+ LatipayConfig.Merchant_Code + "&md5info=" + LatipayUtils.md5(text);
       System.out.println("latipay url:"+url);
		String json = this.getJsonString(url, null, null);
		System.out.println(json);
		List<LatiPay> lst = JsonUtil.getListFromJson(json, List.class, LatiPay.class);
		for (LatiPay latiPay : lst) {
			System.out.println("this staus is " + latiPay.getStatus());
			rs = latiPay;

		}

		return rs;

	}

}
