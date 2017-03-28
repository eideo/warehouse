package com.fh.util;

/* *
 *类名：LatipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及前后台通知地址
 *版本：2.0
 *日期：2016-05-16
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究LATIPAY接口使用，只是提供一个参考。
 */
/**
 * 
 * @author PENG
 *
 */
public class LatipayConfig {
	/**
     * MyRapidPay提供给商户的网关地址
     */
    public final static String latipay_gateway ="https://merchant.latipay.co.nz/api/search.action?orderId="; 
	
    /**
     * 设置密钥：为确保商户交易信息的真实性和完整性，
     * 入驻LATIPAY电子支付平台的商户需要设置密钥，以用于数字签名，
     * 该密钥初始值为空，商户登录系统，打开“开发者-更新秘钥”后可修改自己的密钥，
     * 为确保商户交易数据安全，请商户正式上线前务必另行设定密钥
     */
	public final static String key = "2772299";
	
	public final static String   Merchant_Code="M00000863";
	
	
	public static String getUrlBaseOnOrderID(String orderId,String Date_created ){
		
		String date =DateUtil.getDays(Date_created);
		orderId = date + "-" + LatipayConfig.Merchant_Code + "-" + orderId;
		String text = orderId + LatipayConfig.Merchant_Code + LatipayConfig.key;
		String url =latipay_gateway + orderId + "&merchantCode="
				+ LatipayConfig.Merchant_Code + "&md5info=" + LatipayUtils.md5(text);
		return url;
	}
}
// https://merchant.latipay.co.nz/api/search.action?merchantCode=M00000863
