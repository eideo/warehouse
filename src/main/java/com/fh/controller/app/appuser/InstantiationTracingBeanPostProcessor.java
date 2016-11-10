package com.fh.controller.app.appuser;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.fh.service.warehouse.ClientsService;
import com.fh.service.warehouse.ProductService;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Resource(name="productService")
	private ProductService productService;
	@Resource(name="clientsService")
	private ClientsService clientsService;
@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
	try {	
		this.productService.listAllProducts();
		this.clientsService.listAlLClients();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}