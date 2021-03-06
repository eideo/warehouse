package com.fh.service.warehouse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fh.dao.DaoSupport;
import com.fh.entity.warehouse.EnterStock;
import com.fh.entity.warehouse.EnterStockDetail;
import com.fh.entity.warehouse.orders.Line_Items;
import com.fh.entity.warehouse.orderslist.Product;
import com.fh.service.auto.WooApiGetOrdersService;
import com.fh.util.CacheUtil;
import com.fh.util.Const;
import com.fh.util.Const.ClientUrl;
import com.fh.util.DateUtil;
import com.fh.util.JsonUtil;
import com.fh.util.PageData;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service("clientsService")
public class ClientsService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	@Resource(name = "autoApiService")
	private WooApiGetOrdersService autoApiService;

	/*
	 * 保存产品
	 */
	// @CacheEvict(value = "productCache")
	public void saveClients(PageData pd) throws Exception {
		dao.save("WarehouseMapper.saveClients", pd);
	}

	/*
	 * 用户列表(全部)
	 */
	// @Cacheable(value = "productCache", key="products")
	public List<PageData> listAlLClients() throws Exception {
		List<PageData> list = (List<PageData>) (List<PageData>) dao.findForList("WarehouseMapper.listAlLClients", null);
		for (PageData pageData : list) {
			CacheUtil.cacheSave(pageData.get("Consumer_Key"), pageData, "clients");
		}

		return list;
	}

	/*
	 * 仓库列表(全部)
	 */
	/// @Cacheable(value = "productCache", key="products")
	public List<PageData> listAlLWareHouses(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("WarehouseMapper.listAlLWareHouses", pd);
	}

	/*
	 * 入库(全部)
	 */
	/// @Cacheable(value = "productCache", key="products")
	public List<PageData> listAlLStockIn(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("WarehouseMapper.listAlLStockIn", pd);
	}

	// 入库详细
	public List<EnterStockDetail> listStockDetal(PageData pd) throws Exception {
		return (List<EnterStockDetail>) dao.findForList("WarehouseMapper.listStockInDetail", pd);
	}

	/*
	 * 保存仓库
	 */
	// @CacheEvict(value = "productCache")
	public void saveWareHouse(PageData pd) throws Exception {
		// System.out.println("dsfdsfsdf##@@@@@@@Dept_ID "+pd.get("Dept_ID"));
		dao.save("WarehouseMapper.saveWarehouse", pd);
		// System.out.println("dsfdsfsdf@@@@@@@storehosueid
		// "+pd.get("StoreHouse_ID"));
		// pd.put("Dept_ID", dept_id);
		// pd.

		dao.save("WarehouseMapper.saveClientsWarehouse", pd);

	}

	/*
	 * 货单导入
	 */

	public void saveStock(EnterStock enterStock, boolean out) throws Exception {
		if (out) {
			System.out.println("test insert");
			dao.save("WarehouseMapper.saveOutStock", enterStock);

		} else {
			dao.save("WarehouseMapper.saveStock", enterStock);
		}

		// enterStock.getDept_ID();
		// enterStock.getStoreHouse_ID();
		PageData pData = new PageData();
		pData.put("Dept_ID", enterStock.getDept_ID());
		pData.put("StoreHouse_ID", enterStock.getStoreHouse_ID());

		List<EnterStockDetail> list = enterStock.getEnterStockDetailList();
		for (EnterStockDetail enterStockDetail : list) {
			enterStockDetail.setEnterStock_ID(enterStock.getEnterStock_ID());
			enterStockDetail.setLeaveStock_ID(enterStock.getLeaveStock_ID());
			pData.put("Product_ID", enterStockDetail.getProduct_ID());
			this.updateOrInsertStock(pData, enterStockDetail, out);
		}
		if (!out) {
			dao.save("WarehouseMapper.saveStockDetail", list);
		} else {

			dao.save("WarehouseMapper.saveStockOutDetail", list);
		}

	}

	private void updateOrInsertStock(PageData pd, EnterStockDetail d, boolean out) throws Exception {

		int dept = (int) pd.get("Dept_ID");
		String sku = d.getSKU();
		int n = 0;

		List<PageData> pageData = (List) dao.findForList("WarehouseMapper.listStock", pd);
		if (!out) {
			if (pageData == null || pageData.size() == 0) {
				// Quantity,
				// Price
				pd.put("Quantity", d.getQuantity());
				pd.put("Price", d.getPrice());
				dao.save("WarehouseMapper.saveStockpileDetail", pd);
				n = d.getQuantity();

			} else {
				PageData temp = pageData.get(0);
				int num = (int) temp.get("Quantity");
				double price = ((BigDecimal) temp.get("Price")).doubleValue();
				int total = num + d.getQuantity();
				n = total;

				double aveprice = (num * price + d.getPrice() * d.getQuantity()) / total;
				temp.put("Quantity", total);
				temp.put("Price", aveprice);

				// LastLeaveDate
				temp.put("LastLeaveDate", null);
				updateStockDetail(temp);
				// dao.update("WarehouseMapper.updateStockDetail", temp);
			}
		} else {
			if (pageData != null) {
				PageData temp = pageData.get(0);
				int total = (int) temp.get("Quantity") - d.getQuantity();
				n = total;
				temp.put("Quantity", total);
				temp.put("LastLeaveDate", DateUtil.getDay());
				updateStockDetail(temp);
				// dao.update("WarehouseMapper.updateStockDetail", temp);

			}

		}
		this.updateWooStock(sku, n, dept);
	}

	/**
	 * 
	 * @param p
	 * @throws Exception
	 */
	private void updateStockDetail(PageData p) throws Exception {
		dao.update("WarehouseMapper.updateStockDetail", p);

	}

	private void updateFrozeStock(PageData p) throws Exception {
		dao.update("WarehouseMapper.updateFrozenStockDetail", p);
		System.out.println("冻结库存");
	}

	/**
	 * 
	 * @param order
	 * @param stat
	 * @throws Exception
	 */
	public void saveAutoOrderStock(com.fh.entity.warehouse.orders.Order order, int stat) throws Exception {
		int status = order.getStatusInt();
		if (status == 2 || status == 4 || status == 3) {

			List<Line_Items> list = order.getLine_items();
			PageData p = new PageData();
			p.put("Dept_ID", order.getDept_ID());
			for (Line_Items line_Items : list) {
				// System.out.println("product id "+line_Items.getProduct_ID());
				// System.out.println("Freeze_Num id
				// "+line_Items.getQuantity());
				p.put("Product_ID", line_Items.getProduct_ID());
				p.put("Freeze_Num", line_Items.getQuantity());

				this.updateFrozeStock(p);
			}

			// dao.update("WarehouseMapper.updateFrozenStockDetail", p);

		} else if (status == 1) {

			System.out.println("出库");
		}

	}

	/**
	 * 订单出库
	 * 
	 * @param order
	 * @throws Exception
	 */
	private void upadateStockBaseOnOrder(com.fh.entity.warehouse.orders.Order order) throws Exception {
		EnterStock enterStock = new EnterStock();
		enterStock.setOrder_ID(String.valueOf(order.getId()));
		enterStock.setDept_ID(order.getDept_ID());

		dao.save("WarehouseMapper.saveOutStock", enterStock);
		List<Line_Items> lis = order.getLine_items();
		for (Line_Items line_Items : lis) {
			line_Items.setLeaveStock_ID(enterStock.getLeaveStock_ID());
			System.out.println("this is the leaveStockId is "+enterStock.getLeaveStock_ID());

		}

	//	List<EnterStockDetail> lis
		
		dao.save("WarehouseMapper.saveStockOutDetail", lis);
		// enterStock.getLeaveStock_ID();

	}

	/**
	 * 更新woocoomoth 库存
	 * @param sku
	 * @param total
	 * @param Dept
	 * @throws IOException
	 * @throws UnirestException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 */
	public void updateWooStock(String sku, int total, int dept)
			throws JsonParseException, JsonMappingException, UnirestException, IOException {
		// String
		// url=Const.gopost_url+"?sku="+sku+"&"+Const.gopost_username+"&"+Const.gopost_password;

		ClientUrl clientUrl = ClientUrl.getClientUrl(dept);
		String url = clientUrl.getUrl() + "products?sku=" + sku + "&" + clientUrl.getUsername() + "&"
				+ clientUrl.getPassword();

		String jsonString = autoApiService.getJsonString(url, Const.gopost_username, Const.gopost_password);

		// stock_quantity
		int id = 0;
		List<Product> lst = JsonUtil.getListFromJson(jsonString, List.class, Product.class);
		if (lst != null && lst.size() > 0) {

			for (Product product : lst) {
				// System.out.println("id is " + product.getId());
				id = product.getId();
			}
			// String
			// urlPut=Const.gopost_url+"/"+id+"?"+Const.gopost_username+"&"+Const.gopost_password;
			String urlPut = clientUrl.getUrl() + "products/" + id + "?" + clientUrl.getUsername() + "&"
					+ clientUrl.getPassword();

			// UpdateStock t =new UpdateStock();
			// t.setStock_quantity(10);
			autoApiService.putJsonString(urlPut, "stock_quantity", total);
		}
	}
  /**
   * 
   * @param pd
   * @return
   * @throws Exception
   */
	public List<PageData> listStock(PageData pd) throws Exception {

		return (List<PageData>) dao.findForList("WarehouseMapper.listStock", pd);

	}

	/////////////////

	/*
	 * 通过id获取数据
	 */
	public PageData findByUiId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("WarehouseMapper.findByUiId", pd);
	}

	/*
	 * 保存用户
	 */
	public void saveU(PageData pd) throws Exception {
		dao.save("UserXMapper.saveU", pd);
	}

	/*
	 * 保存产品
	 */
	public void saveProduct(com.fh.entity.system.Role pd) throws Exception {
		dao.save("WarehouseMapper.saveProducts", pd);
	}

	/*
	 * 修改用户
	 */
	public void editU(PageData pd) throws Exception {
		dao.update("WarehouseMapper.editU", pd);
	}

	/*
	 * 换皮肤
	 */
	public void setSKIN(PageData pd) throws Exception {
		dao.update("UserXMapper.setSKIN", pd);
	}

	/*
	 * 删除用户
	 */
	public void deleteU(String Dept_ID) throws Exception {
		dao.delete("WarehouseMapper.deleteClients", Dept_ID);
	}

	/*
	 * 删除仓库
	 */
	public void deleteWarehouse(PageData pd) throws Exception {
		dao.delete("WarehouseMapper.deleteWareHouses", pd);
		dao.delete("WarehouseMapper.deleteClientsWarehouse", pd);
	}

	/*
	 * 批量删除用户
	 */
	public void deleteAllU(String[] USER_IDS) throws Exception {
		dao.delete("UserXMapper.deleteAllU", USER_IDS);
	}

}