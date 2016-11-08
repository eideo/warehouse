<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<h3 class="header smaller lighter blue">库存查看</h3>



	
<div class="row-fluid">
<tr>
</tr>
<td>导出到EXCEL</td>
		<td style="vertical-align:top;"><a class="btn btn-mini btn-light" onclick="toExcel();" title="导出到EXCEL"><i id="nav-search-icon" class="icon-download-alt"></i></a></td>
		<table id="table_report" class="table table-striped table-bordered table-hover">
				
			<thead>
				<tr>
					<th class="center">
						<label><input type="checkbox" id="zcheckbox" /><span class="lbl"></span></label>
					</th>
					<th>SKU</th>
					<th>NAME</th>
					<th>数量</th>
						<th>冻结库存</th>
					<th>平均成本价</th>
					<th>仓库</th>
					
					
					<th><i class="icon-calendar"></i>首次入库时间</th>
					<th class="icon-calendar">最后出库时间</th>
				</tr>
			</thead>
								
			<tbody>
				
		<!-- 开始循环 -->	
		<c:choose>
			<c:when test="${not empty productsList}">
			
				<c:forEach items="${productsList}" var="user" varStatus="vs">
				<tr>
					<td class='center' style="width: 30px;">
						
					</td>
					<td>${user.SKU }</td>
					<td><a>${user.Name_CN }</a></td>
					<td>${user.Quantity }</td>
					<td>${user.Freeze_Num }</td>
					<td>${user.Price }</td>
					
					<td>${user.Address }</td>
					<td>${user.FirstEnterDate }</td>
					<td>
						${user.	LastLeaveDate }
					</td>
				</tr>
				
				</c:forEach>		
			
			</c:when>
			
		</c:choose>				
				
			</tbody>
		</table>
	
</div>
 
 		<div class="page-header position-relative">
	
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
		      null, null,null, null, null,null,null,
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
			 diag.URL = '<%=basePath%>user/goAddU.do';
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
			 diag.URL = '<%=basePath%>user/goEditU.do?USER_ID='+user_id;
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
				if(result) {
					var url = "<%=basePath%>user/deleteU.do?USER_ID="+userId+"&tm="+new Date().getTime();
					$.get(url,function(data){
						if(data=="success"){
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
		
		$(function() {
			console.log(6+ 6);
			//$('.table_report_filter').css("text-align","left");
			$('.table_report_filter').css("color","red");
			
		});
		
		//导出excel
		function toExcel(){

	
		window.location.href='<%=basePath%>enterStock/excel.do';
		}
		
		

        $(function(){  

      	  
      	  
      	   var totalRow = 0  ;
      	    $('#table_report tr').each(function() {  
      	        $(this).find('(3)').each(function(){  
      	                totalRow += parseFloat($(this).text());   
      	                alert(""+totalRow);
      	        });  
      	    });  
      	      
      	    $('#totalRow').append('<td>合计</td><td></td><td>'+totalRow+'</td><td></td>');  
      	  
      	  
      	  
      	  
      	 
      	});  
		
		
		</script>
		
	</body>
</html>

