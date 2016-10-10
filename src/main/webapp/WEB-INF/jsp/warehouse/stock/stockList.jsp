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
	<div id="page-content" class="clearfix">

		<div class="row-fluid">
			<!-- PAGE CONTENT BEGINS HERE -->

			<div class="row-fluid">
				<div class="span10 offset1">
					<div class="widget-box transparent invoice-box">
						<div class="widget-header widget-header-large">
							<h3 class="pull-left grey lighter position-relative grey">
								<i class="icon-leaf green"></i> 生成${title}单并导入
								
								
							</h3>

							<div class="widget-toolbar no-border invoice-info">

								<span class="invoice-info-label">Date:</span> <span id="time"
									class="blue"></span>
							</div>

							<div class="widget-toolbar hidden-480">
								<a href="#"><i class="icon-print"></i></a>
							</div>
						</div>




						<form method="post" id="myform" action="enterStock/readExcel.do?out=${isout}"
							enctype="multipart/form-data">
							<div class="row-fluid">


								<label for="form-field-select-1"><h4>请选择仓库</h4></label> <select
									id="choseWarehouse" name="choseWarehouse">

									<option value=""></option>
									<c:forEach items="${wareHousesList}" var="house" varStatus="vs">
										<option value="${house.StoreHouse_ID }">${house.Address }</option>

									</c:forEach>
								</select>

							</div>






							<button type="button" class="btn btn-primary"
								onclick="addStock();">生成${title}单</button>
							<input id="upload" disabled="true" type="submit" value="提交"
								class="btn btn-primary"> <input type="file"
								name="csv_file" id="csv_file" onchange="fileSelected();"
								accept=".xls,.csv,.xlsx">


						</form>
						<div class="widget-body">
							<div class="widget-main padding-24">
								<div class="row-fluid">
									<div class="row-fluid">
										<div id="stock" class="span6" style="display: none;">
											<div class="row-fluid">
												<div
													class="span12 label label-large label-info arrowed-in arrowed-right">
													<b>${title}信息</b>
												</div>
											</div>
											<div class="row-fluid ">
												<ul class="unstyled spaced">
													<!--  
								<li><i class="icon-caret-right blue"></i>编号:<b class="red">Street, City</b></li>
								<li><i class="icon-caret-right blue"></i>商户: <b class="red">Zip Code</b></li>
								-->
													<li><i class="icon-caret-right blue"></i>仓库地址:<b
														id="stockaddress" class="red">State, Country</b></li>
													<li><i class="icon-caret-right blue"></i>时间: <b
														id="timestock" class="red">111-111-111</b></li>
													<li class="divider"></li>

												</ul>
											</div>
										</div>
										<!--/span-->



										<div class="space"></div>
										<div id="queryStock">
											<div class="row-fluid">
												<div
													class="span12 label label-large label-info arrowed-in arrowed-right">
													<b>${title}单查询</b>
												</div>
											</div>








   <form method="post" id="myform" action="enterStock/search.do?out=${isout}"
												enctype="multipart/form-data">
					
											
												<td><input class="span10 date-picker"
													name="lastLoginStart" id="Start" data-provide="datepicker"
													type="text" data-date-format="yyyy-mm-dd"
													readonly="readonly" style="width: 88px;" placeholder="开始日期"
													title="开始" /></td> 到
												<td><input class="span10 date-picker"
													name="lastLoginEnd" id="End" data-provide="datepicker"
													type="text" data-date-format="yyyy-mm-dd"
													readonly="readonly" style="width: 88px;" placeholder="结束日期"
													title="结束" /></td> <input type="submit" value="查找"
													class="btn btn-primary">




											</form>



											<div class="row-fluid">
												<table class="table table-striped table-bordered">
													<thead>
														<tr>
															<th class="center">#</th>
															<th>时间</th>

															<th>仓库地址</th>

															<th>Total</th>
														</tr>
													</thead>

													<tbody>


														<c:choose>
															<c:when test="${not empty stockList}">

																<c:forEach items="${stockList}" var="detail"
																	varStatus="vs">
																	<tr>



																		<td class="center"><a href="enterStock/search.do?EnterStock_ID=${detail.EnterStock_ID }"> 详细信息</a></td>

																		<td class="hidden-phone">${detail.Address }</td>
																		<td class="hidden-phone">${detail.EnterDate }</td>


																		<td>$10</td>





																	</tr>

																</c:forEach>

															</c:when>

														</c:choose>






													</tbody>
												</table>

											</div>
										</div>


										<div class="space"></div>


										<div class="row-fluid">
											<div
												class="span12 label label-large label-info arrowed-in arrowed-right">
												<b>${title}单明细</b>
											</div>
										</div>


										<div class="space"></div>




										<div class="row-fluid">

											<table class="table table-striped table-bordered table-hover">

												<thead>
													<tr>
														<th class="center">#</th>
														<th>SKU</th>

														<th>Product</th>
														<th>数量</th>
														<th>单价</th>
														<th>保质期</th>
														<th>剩余数量</th>
														<th>发票编号</th>
														<th>Total</th>

													</tr>
												</thead>

												<tbody>

													<!-- 开始循环 -->
													<c:choose>
														<c:when test="${not empty EnterStockDetailList}">

															<c:forEach items="${EnterStockDetailList}" var="detail"
																varStatus="vs">
																<tr>
																	<td class='center' style="width: 30px;"></td>
																	<td>${detail.SKU }</td>
																	<td>${detail.NAME }</td>
																	<td>${detail.quantity }</td>
																	<td>${detail.price }</td>
																	<td>${detail.durability }</td>
																	<td>${detail.remaining_amount }</td>
																	<td>${detail.invoiceNum }</td>
																	<td>${detail.quantity*detail.price }</td>

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
													Total amount : <span class="red">$</span>
												</h4>
											</div>

											<div class="span7 pull-left">Extra Information</div>
										</div>

										<div class="space-6"></div>

										<div class="row-fluid">
											<div class="span12 well">
												${title}明细不允许手工调整,如果更改数量或者价格许上传出库单对冲</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<!-- PAGE CONTENT ENDS HERE -->
			</div>
			<!--/row-->

		</div>

		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		<!-- basic scripts -->

		<script type="text/javascript">
			window.jQuery
					|| document
							.write("<script src='js/jquery-1.9.1.min.js'>\x3C/script>");
		</script>


		<!-- page specific plugin scripts -->

		<!-- ace scripts -->

		<!-- 引入 -->



		<script src="static/js/ace.min.js"></script>

		<script type="text/javascript" src="static/js/chosen.jquery.min.js"></script>
		<!-- 下拉框 -->
		<script type="text/javascript"
			src="static/js/bootstrap-datepicker.min.js"></script>
		<!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script>
		<!-- 确认窗口 -->
		<!-- 引入 -->
		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			$(top.hangge());

			$(function() {
				var oTable1 = $('#table_report').dataTable({
					"aoColumns" : [ {
						"bSortable" : false
					}, null, null, null, null, null, {
						"bSortable" : false
					} ]
				});

				$('table th input:checkbox').on(
						'click',
						function() {
							var that = this;
							$(this).closest('table').find(
									'tr > td:first-child input:checkbox').each(
									function() {
										this.checked = that.checked;
										$(this).closest('tr').toggleClass(
												'selected');
									});

						});
				$('[data-rel=tooltip]').tooltip();
			})

			//修改
			function addStock() {
				//	$("#stock").css.(display: none;);
				var temp = $('#choseWarehouse');
				// $(".selector").val();
				if (!jQuery.isEmptyObject(temp.val())) {
					//console.log("dsfdsf "+temp);
					//	alert("dsfdsfsdf");
					var mydate = new Date();
					var t = mydate.toLocaleString()

					$("#timestock").text(t);

					$("#stockaddress").text(temp.text());

					$('#upload').removeAttr("disabled");

					$('#stock').show();
					$('#queryStock').hide();

				} else {
					alert("pleas chose one warehouse");
				}

			}

			$(function() {
				alert("test");
				//日期框
				$('.date-picker').datepicker();

				//下拉框

			});
		</script>
</body>
</html>

