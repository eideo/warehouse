package com.fh.util;

import org.springframework.context.ApplicationContext;

/**
 * 项目名称：
 * 
 * @author:fh
 * 
 */
public class Const {
	public static final String SESSION_SECURITY_CODE = "sessionSecCode";
	public static final String SESSION_USER = "sessionUser";
	public static final String SESSION_ROLE_RIGHTS = "sessionRoleRights";
	public static final String SESSION_menuList = "menuList"; // 当前菜单
	public static final String SESSION_allmenuList = "allmenuList"; // 全部菜单
	public static final String SESSION_QX = "QX";
	public static final String SESSION_userpds = "userpds";
	public static final String SESSION_USERROL = "USERROL"; // 用户对象
	public static final String SESSION_USERNAME = "USERNAME"; // 用户名
	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String LOGIN = "/login_toLogin.do"; // 登录地址
	public static final String SYSNAME = "admin/config/SYSNAME.txt"; // 系统名称路径
	public static final String PAGE = "admin/config/PAGE.txt"; // 分页条数配置路径
	public static final String EMAIL = "admin/config/EMAIL.txt"; // 邮箱服务器配置路径
	public static final String SMS1 = "admin/config/SMS1.txt"; // 短信账户配置路径1
	public static final String SMS2 = "admin/config/SMS2.txt"; // 短信账户配置路径2
	public static final String FWATERM = "admin/config/FWATERM.txt"; // 文字水印配置路径
	public static final String IWATERM = "admin/config/IWATERM.txt"; // 图片水印配置路径
	public static final String WEIXIN = "admin/config/WEIXIN.txt"; // 微信配置路径
	public static final String FILEPATHIMG = "uploadFiles/uploadImgs/"; // 图片上传路径
	public static final String FILEPATHFILE = "uploadFiles/file/"; // 文件上传路径
	public static final String FILEPATHTWODIMENSIONCODE = "uploadFiles/twoDimensionCode/"; // 二维码存放路径
	public static final String NO_INTERCEPTOR_PATH = ".*/((login)|(logout)|(code)|(app)|(weixin)|(static)|(main)|(websocket)).*"; // 不对匹配该值的访问路径拦截（正则）

	public static ApplicationContext WEB_APP_CONTEXT = null; // 该值会在web容器启动时由WebAppContextListener初始化

	/**
	 * APP Constants
	 */
	// app注册接口_请求协议参数)
	public static final String[] APP_REGISTERED_PARAM_ARRAY = new String[] { "countries", "uname", "passwd", "title",
			"full_name", "company_name", "countries_code", "area_code", "telephone", "mobile" };
	public static final String[] APP_REGISTERED_VALUE_ARRAY = new String[] { "国籍", "邮箱帐号", "密码", "称谓", "名称", "公司名称",
			"国家编号", "区号", "电话", "手机号" };

	// app根据用户名获取会员信息接口_请求协议中的参数
	public static final String[] APP_GETAPPUSER_PARAM_ARRAY = new String[] { "USERNAME" };
	public static final String[] APP_GETAPPUSER_VALUE_ARRAY = new String[] { "用户名" };

	// App version

	public static final String VERSION = "0.5";
	public static final int secEx = 90;
	// Response
	private static final String commonUrl = "/wp-json/wc/v1/";///wp-json/wc/v1/orders/<id>
	/// anfa
	public static final String baseurl = "https://myalpha.co.nz"+commonUrl;
	public static final String url = baseurl+"orders?per_page=100&status=";
	public static final String username = "ck_9c69de5c44e4aa66588d610f17f6d0c09d25cc78";// "ck_3db1c674a6c6bbd9ccd50671ebdfda99b8f46067";
	public static final String password = "cs_cf89e7f43aef93aeb44f0063994166909820d73f";// "cs_9c597830c0f6ed61bb157c10106577fb097b402f";
	
	//test anfa
	public static final String basetesturl = "https://test.myalpha.co.nz"+commonUrl;
	public static final String testurl = basetesturl+"orders?per_page=100&status=";

	/// gopost

	public static final String gopost_url = "https://gopost.nz" + commonUrl + "products";
	public static final String gopost_username = "consumer_key=ck_e73ecebc956ef311f69691c3123d5c06aa4fb7c0";// "ck_3db1c674a6c6bbd9ccd50671ebdfda99b8f46067";
	public static final String gopost_password = "consumer_secret=cs_1cd279f29a6cab6359efd324d83b3ba44f035914";// "cs_9c597830c0f6ed61bb157c10106577fb097b402f";

	// https://gopost.nz/wp-json/wc/v1/products?sku=9400501003738&consumer_key=ck_cfe48598765854a8c828c46832a7725db4039637&consumer_secret=cs_ecee78830d883dc0b0c54cc9ce71d92884c41e24//
	
	public static String getUrlBaseOnKey(String url,String consumer_key ,String consumer_secret){
		return url+"&consumer_key="+consumer_key+"&consumer_secret="+consumer_secret;
	}

	// sysOrder
	public static final String status = "completed";

	public enum ClientUrl {
		gopost(11, "https://gopost.nz" + commonUrl, "consumer_key=ck_e73ecebc956ef311f69691c3123d5c06aa4fb7c0",
				"consumer_secret=cs_1cd279f29a6cab6359efd324d83b3ba44f035914");

		private ClientUrl(int dep_id, String url, String username, String password) {
			this.dep_id = dep_id;
			this.url = url;
			this.username = username;
			this.password = password;
		}

		public int getDep_id() {
			return dep_id;
		}

		public void setDep_id(int dep_id) {
			this.dep_id = dep_id;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		private int dep_id;
		private String url;
		private String username;
		private String password;

		// 普通方法
		public static ClientUrl getClientUrl(int index) {
			for (ClientUrl c : ClientUrl.values()) {
				if (c.getDep_id() == index) {
					return c;
				}
			}
			return null;
		}

	}

	// xfd.co.nz
	// newco.co.nz
	// lovehealth.nz
	// myalpha.co.nz
	// gopost.nz
	//
	public static final int SERVER_ERROR = 500;
	public static final int Not_Acceptable = 406;
	public static final int Unsupported_Media = 415;
	public static final int Bad_Request = 406;
	public static final int Method_Not_Allowed = 405;
	public static final int Bad_Reques = 400;
	public static final int Not_Found = 404;

	// wwh 13:54:14
	// pending, processing, on-hold, completed, cancelled, refunded , failed.

	public enum Status {
		// 冻结库存
		pending("pending", 2), processing("processing", 3), onhold("on-hold", 4),
		// 扣减库存
		completed("completed", 1),
		// check 上一步状态
		cancelled("cancelled", 5),
		// 不做操作
		refunded("refunded", 6), failed("failed", 7),
		// 不做库存操作
		packaged("package", 8);

		// 普通方法
		public static String getName(int index) {
			for (Status c : Status.values()) {
				if (c.getIndex() == index) {
					return c.name;
				}
			}
			return null;
		}

		// 普通方法
		public static int getIndex(String name) {
			for (Status c : Status.values()) {
				if (c.getName().equalsIgnoreCase(name)) {
					return c.index;
				}
			}
			return 0;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

		private String name;
		private int index;

		// 构造方法
		private Status(String name, int index) {
			this.name = name;
			this.index = index;
		}

	}

	/*
	 * 100 Continue 继续。客户端应继续其请求 101 Switching Protocols
	 * 切换协议。服务器根据客户端的请求切换协议。只能切换到更高级的协议，例如，切换到HTTP的新版本协议 2开头的状态码 200 OK
	 * 请求成功。一般用于GET与POST请求 201 Created 已创建。成功请求并创建了新的资源 202 Accepted
	 * 已接受。已经接受请求，但未处理完成 203 Non-Authoritative Information
	 * 非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本 204 No Content
	 * 无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档 205 Reset Content
	 * 重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域 206 Partial Content
	 * 部分内容。服务器成功处理了部分GET请求 3开头的状态码 300 Multiple Choices
	 * 多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择 301 Moved Permanently
	 * 永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替 302
	 * Found 临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI 303 See Other
	 * 查看其它地址。与301类似。使用GET和POST请求查看 304 Not Modified
	 * 未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，
	 * 通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源 305 Use Proxy 使用代理。所请求的资源必须通过代理访问 306
	 * Unused 已经被废弃的HTTP状态码 307 Temporary Redirect 临时重定向。与302类似。使用GET请求重定向
	 * 4开头的状态码 400 Bad Request 客户端请求的语法错误，服务器无法理解 401 Unauthorized 请求要求用户的身份认证
	 * 402 Payment Required 保留，将来使用 403 Forbidden 服务器理解请求客户端的请求，但是拒绝执行此请求 404
	 * Not Found 服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面 405
	 * Method Not Allowed 客户端请求中的方法被禁止 406 Not Acceptable 服务器无法根据客户端请求的内容特性完成请求
	 * 407 Proxy Authentication Required 请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权 408
	 * Request Time-out 服务器等待客户端发送的请求时间过长，超时 409 Conflict
	 * 服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突 410 Gone
	 * 客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
	 * 411 Length Required 服务器无法处理客户端发送的不带Content-Length的请求信息 412 Precondition
	 * Failed 客户端请求信息的先决条件错误 413 Request Entity Too Large
	 * 由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry
	 * -After的响应信息 414 Request-URI Too Large 请求的URI过长（URI通常为网址），服务器无法处理 415
	 * Unsupported Media Type 服务器无法处理请求附带的媒体格式 416 Requested range not
	 * satisfiable 客户端请求的范围无效 417 Expectation Failed 服务器无法满足Expect的请求头信息 5开头的状态码
	 * 500 Internal Server Error 服务器内部错误，无法完成请求 501 Not Implemented
	 * 服务器不支持请求的功能，无法完成请求 502 Bad Gateway 充当网关或代理的服务器，从远端服务器接收到了一个无效的请求 503
	 * Service Unavailable
	 * 由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中 504 Gateway
	 * Time-out 充当网关或代理的服务器，未及时从远端服务器获取请求 505 HTTP Version not supported
	 * 服务器不支持请求的HTTP协议的版本，无法完成处理
	 */

}
