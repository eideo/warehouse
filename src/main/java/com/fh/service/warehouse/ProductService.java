package com.fh.service.warehouse;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.system.BaseProductsEntity;
import com.fh.util.CacheUtil;
import com.fh.util.PageData;

@Service("productService")
public class ProductService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	/*
	 * 保存产品
	 */
	// @CacheEvict(value = "productCache")
	public void saveProduct(BaseProductsEntity pd) throws Exception {
		dao.save("WarehouseMapper.saveProducts", pd);
	}

	/*
	 * 产品列表(全部)
	 */
	@Cacheable(value = "productLists")
	public List<PageData> listAllProducts(PageData ... pd) throws Exception {

		System.out.println("cach be invoked");
		List<PageData> list = (List<PageData>) dao.findForList("WarehouseMapper.listAlLProducts", pd);
		for (PageData pageData : list) {
			CacheUtil.cacheSave(pageData.get("SKU"), pageData, "products");
		}
		//System.out.println(CacheUtil.getCacheObject("9400569011003", "products"));

		return list;
	}

}
