<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<!-- jsp文件头和头部 -->
<%@ include file="../../system/admin/top.jsp"%>


</head>
<body>


	<div class="row-fluid">
		<div class="span10 offset1">
			<div class="widget-box transparent invoice-box">
				<div class="widget-header widget-header-large">
					<h3 class="pull-left grey lighter position-relative grey">
						<i class="icon-leaf green"></i> 订单详细
					</h3>



				</div>


				<div class="control-group">
					<label class="control-label" for="form-field-6"><h3>请输入订单ID</h3>
					</label>

					<div class="controls">
						<form action="order/detail.do" name="userForm" id="userForm"
							method="post">
							<input class="ace-tooltip" type="text" name="orderid"
								id="orderid" placeholder="please input orderid" title=""
								data-placement="bottom" value="${order.Original_ID}">
							<button type="summit" class="btn btn-primary" id="searchid">查询</button>
						</form>
					</div>
				</div>
				

				
				
				
				
				<div class="widget-body">
					<div class="widget-main padding-24">
						<div class="row-fluid">
							<div class="row-fluid">





								<div class="span12">
									<div class="widget-box pricing-box">
										<div class="widget-header  header-color-blue">
											<h5 class="bigger lighter">Basic Package</h5>
										</div>
										<div class="widget-body">
											<div class="widget-main">
												<ul class="unstyled spaced2">
													<li><h4>
															订单ID: <b class="red">${order.id }</b>
															<h4></li>
													<h4>
														<li>原始订单ID: <b class="red">${order.Original_ID }</b>
														<h4></li>

														<h4>
															<li>订单状态 : <b class="red">${order.Status } </b>
															<h4></li>
															<h4>
																<li>订单地址: <b class="red">${order.Address } </b>
																<h4></li>
																<h4>
																	<li>收货人: <b class="red">${order.Name } </b>
																	<h4></li>
																	<h4>
																		<li>电话: <b class="red">${order.Tel } </b>
																		<h4></li>
																		<h4>
																			<li class="divider"></li>
												</ul>

												<hr>
												<div class="price">
													订单详情<small></small>
												</div>
											</div>

										</div>
									</div>
								</div>
				<form action="order/stackOut.do" name="outStack" id="outStack">
<input class="ace-tooltip"  type="text" name="sku" id="sku">
<input type="hidden" name="order" id="order" value="${Original_ID}">
		<button type="summit" class="btn btn-primary" id="out">出库</button>


</form>
								<div class="space"></div>

								<div class="row-fluid">
									<table class="table table-striped table-bordered">
										<thead>
											<tr>

												<th class="center">#</th>
												<th>产品</th>
												<th>数量</th>
												<th>单价</th>
												<th>状态</th>


											</tr>
										</thead>

										<tbody>

											<c:choose>
												<c:when test="${not empty itermList}">

													<c:forEach items="${itermList}" var="detail" varStatus="vs">
														<tr>

															<td class="center">${detail.id }</td>

															<td class="center">${detail.name } SKU: ${detail.sku }</td>

															<td class="hidden-phone">${detail.quantity }</td>
															<td class="hidden-phone">${detail.price }</td>
															<td class="hidden-phone">${detail.status }</td>
														</tr>

													</c:forEach>

												</c:when>

											</c:choose>

										</tbody>
									</table>

								</div>

								<div class="hr hr8 hr-double hr-dotted"></div>

								<div class="row-fluid">
									<div class="span5 pull-right">
										<h4 class="pull-right">
											Total amount : <span class="red">${order.TotalPrice }
											</span>
									</div>

									<div class="span7 pull-left">Extra Information</div>
								</div>

								<div class="space-6"></div>

								<div class="row-fluid">
									<div class="span12 well"></div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>




		<!-- PAGE CONTENT ENDS HERE -->

		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		<!-- basic scripts -->
		<script src="1.9.1/jquery.min.js"></script>
		<script type="text/javascript">
			window.jQuery
					|| document
							.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>

		<script src="js/bootstrap.min.js"></script>
		<!-- page specific plugin scripts -->

		<!-- ace scripts -->
		<script src="js/ace-elements.min.js"></script>
		<script src="js/ace.min.js"></script>
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			$(function() {
				var temp=$('#orderid').val();
				if(temp==""){
					 $("#orderid").focus();
				}else{
					  $("#sku").focus();
				}
			
				
				 $('#sku').bind('keypress',function(event){

		             if(event.keyCode == "13")   

		             {

		          
		                 $("#out").click();

		             }

		         });
				 
				
			
				 
				 $('#orderid').bind('keypress',function(event){

		             if(event.keyCode == "13")   

		             {

		          
		                 $("#searchid").click();

		             }

		         });
				 
				 
			})
		</script>






		<script type="text/javascript">
		
		$(top.hangge());
		
		
		</script>
</body>
</html>

