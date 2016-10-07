package com.fh.controller.app.appuser;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.fh.service.warehouse.ProductService;

public class InstantiationTracingBeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Resource(name="productService")
	private ProductService productService;
@Override
public void onApplicationEvent(ContextRefreshedEvent event) {
	try {
		
		System.out.println("startdsfaaaaaaaaaaaaaaaaaaaaaa ");
		this.productService.listAllProducts();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}