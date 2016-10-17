﻿<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">

	<!-- jsp文件头和头部 -->
		<%@ include file="../../system/admin/top.jsp"%> 
	
	</head> 

<body>
		
<div class="container-fluid" id="main-container">



<div id="page-content" class="clearfix">
						
  <div class="row-fluid">

	
<div class="row-fluid">
	
		<table id="table_report" class="table table-striped table-bordered table-hover">
			
			<thead>
				<tr>
					<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
					</th>
				
					<th>name</th>
					<th>url</th>
					<th>remark</th>
					<th>Consumer_Key</th>
					<th>Consumer_Secret</th>
					<th class="center">操作</th>
					<th class="center">启用</th>
				</tr>
			</thead>
								
			<tbody>
				
		<!-- 开始循环 -->	
		<c:choose>
			<c:when test="${not empty userList}">
				<c:if test="${QX.cha == 1 }">
				<c:forEach items="${userList}" var="user" varStatus="vs">
				<tr>
					<td class='center' style="width: 30px;">
						<c:if test="${user.USERNAME != 'admin'}"><label><input type='checkbox' name='ids' value="${user.Dept_ID }"/><span class="lbl"></span></label></c:if>
						<c:if test="${user.USERNAME == 'admin'}"><label><input type='checkbox' disabled="disabled" /><span class="lbl"></span></label></c:if>
					</td>
					<td>${user.Name }</td>
					<td><a>${user.Domain_Name }</a></td>
					<td>${user.Remark }</td>
					<td>${user.Consumer_Key }</td>
					<td>${user.Consumer_Secret }</td>
					
					<td>
						<div class='hidden-phone visible-desktop btn-group'>
										
							<c:if test="${QX.edit != 1 && QX.del != 1 }">
							<span class="label label-large label-grey arrowed-in-right arrowed-in"><i class="icon-lock" title="无权限"></i></span>
							</c:if>
							
							<c:if test="${QX.edit == 1 }">
							<c:if test="${user.USERNAME != 'admin'}"><a class='btn btn-mini btn-info' title="编辑" onclick="editUser('${user.USER_ID }');"><i class='icon-edit'></i></a></c:if>
							<c:if test="${user.USERNAME == 'admin'}"><a class='btn btn-mini btn-info' title="不能编辑"><i class='icon-edit'></i></a></c:if>
							</c:if>
							<c:choose>
								<c:when test="${user.USERNAME=='admin'}"></c:when>
								<c:otherwise>
									<c:if test="${QX.del == 1 }">
									 <a class='btn btn-mini btn-danger' title="删除" onclick="delUser('${user.Dept_ID }','${user.USERNAME }');"><i class='icon-trash'></i></a>
									</c:if>
								</c:otherwise>
							</c:choose>
						</div>
					</td>
					
					<td style="width:60px;" class="center"><label><input type="checkbox" class="ace-switch ace-switch-3" id="qx21" checked="checked" onclick="kf_qx2(this.id,'2','kfqx2')"><span class="lbl"></span></label></td>
					
				</tr>
				
				</c:forEach>
				</c:if>
				
				<c:if test="${QX.cha == 0 }">
					<tr>
						<td colspan="10" class="center">您无权查看</td>
					</tr>
				</c:if>
			</c:when>
			
		</c:choose>				
				
			</tbody>
		</table>
	
</div>
 
 		<div class="page-header position-relative">
		<table style="width:100%;">
			<tr>
				<td style="vertical-align:top;">
				
					<a class="btn btn-small btn-success" onclick="add();">新增</a>
			
					<a class="btn btn-small btn-danger" onclick="delAll();" title="批量删除" ><i class='icon-trash'></i></a>
					
				</td>
			</tr>
		</table>
		</div>
 
 
	<!-- PAGE CONTENT ENDS HERE -->
  </div><!--/row-->
	
</div><!--/#page-content-->
</div><!--/.fluid-container#main-container-->
		
		<!-- 返回顶部  -->
		<a href="#" id="btn-scroll-up" class="btn btn-small btn-inverse">
			<i class="icon-double-angle-up icon-only"></i>
		</a>
		
		<!-- 引入 -->
		<script type="text/javascript">window.jQuery || document.write("<script src='static/js/jquery-1.9.1.min.js'>\x3C/script>");</script>
		<script src="static/js/bootstrap.min.js"></script>
		<script src="static/js/ace-elements.min.js"></script>
		<script src="static/js/ace.min.js"></script>
		
		<script type="text/javascript" src="static/js/jquery.dataTables.min.js"></script>
		<script type="text/javascript" src="static/js/jquery.dataTables.bootstrap.js"></script>
		
		<script type="text/javascript" src="static/js/bootstrap-datepicker.min.js"></script><!-- 日期框 -->
		<script type="text/javascript" src="static/js/bootbox.min.js"></script><!-- 确认窗口 -->
		<!-- 引入 -->
		
		<script type="text/javascript" src="static/js/jquery.tips.js"></script><!--提示框-->
		
		<script type="text/javascript">
		$(top.hangge());
		$(function() {
			var oTable1 = $('#table_report').dataTable( {
			"aoColumns": [
		      { "bSortable": false },
		      null, null,null, null, null,null,
			  { "bSortable": false }
			] } );
			
			
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
			$('[data-rel=tooltip]').tooltip();
		})
		
		//检索
		function search(){
			top.jzts();
			$("#userForm").submit();
		}
		
		//新增
		function add(){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="新增";
			 diag.URL = '<%=basePath%>clients/goAddU.do';
			 diag.Width = 225;
			 diag.Height = 415;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					setTimeout("self.location.reload()",100);
					top.jzts();
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//修改
		function editUser(user_id){
			 top.jzts();
			 var diag = new top.Dialog();
			 diag.Drag=true;
			 diag.Title ="资料";
			 diag.URL = '<%=basePath%>clients/goEditU.do?USER_ID='+user_id;
			 diag.Width = 225;
			 diag.Height = 415;
			 diag.CancelEvent = function(){ //关闭事件
				 if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					setTimeout("self.location.reload()",100);
					top.jzts();
				}
				diag.close();
			 };
			 diag.show();
		}
		
		//删除
		function delUser(userId,msg){
			bootbox.confirm("确定要删除["+msg+"]吗?", function(result) {
				alert("userId "+userId);
				alert("userId "+result);
				if(result) {
					var url = "<%=basePath%>clients/deleteDept.do?Dept_ID="+userId;
					alert("userId "+url);
					$.get(url,function(data){
						alert("TEST "+url);
						if(data=="success"){
							alert("TEST2 "+url);
							document.location.reload();
							top.jzts();
						}
					});
				}
			});
		}
		
		
		//批量删除
		function delAll(){
			bootbox.confirm("确定要删除选中的数据吗?", function(result) {
				if(result) {
					var str = '';
					for(var i=0;i < document.getElementsByName('ids').length;i++)
					{
						  if(document.getElementsByName('ids')[i].checked){
						  	if(str=='') str += document.getElementsByName('ids')[i].value;
						  	else str += ',' + document.getElementsByName('ids')[i].value;
						  }
					}
					if(str==''){
						bootbox.dialog("您没有选择任何内容!", 
							[
							  {
								"label" : "关闭",
								"class" : "btn-small btn-success",
								"callback": function() {
									//Example.show("great success");
									}
								}
							 ]
						);
						
						$("#zcheckbox").tips({
							side:3,
				            msg:'点这里全选',
				            bg:'#AE81FF',
				            time:8
				        });
						
						return;
					}else{
						$.ajax({
							type: "POST",
							url: '<%=basePath%>user/deleteAllU.do?tm='+new Date().getTime(),
					    	data: {USER_IDS:str},
							dataType:'json',
							//beforeSend: validateData,
							cache: false,
							success: function(data){
								 $.each(data.list, function(i, list){
									 document.location.reload();
									 top.jzts();
								 });
							}
						});
						
					}
				}
			});
		}
		
		</script>
		
		<script type="text/javascript">
		$(function() {
			//日期框
			$('.date-picker').datepicker();
		});
		</script>
		
	</body>
</html>

