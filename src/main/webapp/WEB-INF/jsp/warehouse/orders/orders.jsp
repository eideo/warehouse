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
	<base href="<%=basePath%>"><!-- jsp文件头和头部 -->
	<%@ include file="../../system/admin/top.jsp"%> 


	</head>
<body>
	<div class="row-fluid">
	<h3 class="header smaller lighter blue">订单同步(Order synchronization)</h3>
	<a href="#" class="btn btn-app btn-success">
		<i class="icon-refresh"></i>
		Reload
		</a>
	<div class="table-header">
	orders
	</div>
	
		<div id="table_report_wrapper" class="dataTables_wrapper" role="grid"><div class="row-fluid"><div class="span6"><div id="table_report_length" class="dataTables_length"><label>Display <select size="1" name="table_report_length" aria-controls="table_report"><option value="10" selected="selected">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> records</label></div></div><div class="span6"><div class="dataTables_filter" id="table_report_filter"><label>Search: <input type="text" aria-controls="table_report"></label></div></div></div><table id="table_report" class="table table-striped table-bordered table-hover dataTable" aria-describedby="table_report_info">
			<thead>
				<tr role="row"><th class="center sorting_disabled" role="columnheader" rowspan="1" colspan="1" aria-label="
						
					" style="width: 123px;">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" aria-label="Domain: activate to sort column ascending" style="width: 307px;">Domain</th><th class="sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" aria-label="Price: activate to sort column ascending" style="width: 251px;">Price</th><th class="hidden-480 sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" aria-label="Clicks: activate to sort column ascending" style="width: 277px;">Clicks</th><th class="hidden-phone sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" aria-label=" Update: activate to sort column ascending" style="width: 363px;"><i class="icon-time hidden-phone"></i> Update</th><th class="hidden-480 sorting" role="columnheader" tabindex="0" aria-controls="table_report" rowspan="1" colspan="1" aria-label="Status: activate to sort column ascending" style="width: 354px;">Status</th><th class="sorting_disabled" role="columnheader" rowspan="1" colspan="1" aria-label="" style="width: 518px;"></th></tr>
			</thead>
									
			
		<tbody role="alert" aria-live="polite" aria-relevant="all"><tr class="odd">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">ace.com</a></td>
					<td class=" ">$45</td>
					<td class="hidden-480 ">3,330</td>
					<td class="hidden-phone ">Feb 12</td>
					<td class="hidden-480 "><span class="label label-warning">Expiring</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="even">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">base.com</a></td>
					<td class=" ">$35</td>
					<td class="hidden-480 ">2,595</td>
					<td class="hidden-phone ">Feb 18</td>
					<td class="hidden-480 "><span class="label label-success">Registered</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="odd">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">max.com</a></td>
					<td class=" ">$60</td>
					<td class="hidden-480 ">4,400</td>
					<td class="hidden-phone ">Mar 11</td>
					<td class="hidden-480 "><span class="label label-warning">Expiring</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="even">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">best.com</a></td>
					<td class=" ">$75</td>
					<td class="hidden-480 ">6,500</td>
					<td class="hidden-phone ">Apr 03</td>
					<td class="hidden-480 "><span class="label label-inverse arrowed-in">Flagged</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="odd">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">pro.com</a></td>
					<td class=" ">$55</td>
					<td class="hidden-480 ">4,250</td>
					<td class="hidden-phone ">Jan 21</td>
					<td class="hidden-480 "><span class="label label-success">Registered</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="even">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">team.com</a></td>
					<td class=" ">$40</td>
					<td class="hidden-480 ">3,200</td>
					<td class="hidden-phone ">Feb 09</td>
					<td class="hidden-480 "><span class="label label-inverse arrowed-in">Flagged</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="odd">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">up.com</a></td>
					<td class=" ">$95</td>
					<td class="hidden-480 ">8,520</td>
					<td class="hidden-phone ">Feb 22</td>
					<td class="hidden-480 "><span class="label label-info arrowed arrowed-right">Sold</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="even">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">view.com</a></td>
					<td class=" ">$45</td>
					<td class="hidden-480 ">4,100</td>
					<td class="hidden-phone ">Mar 12</td>
					<td class="hidden-480 "><span class="label label-success">Registered</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="odd">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">nice.com</a></td>
					<td class=" ">$38</td>
					<td class="hidden-480 ">3,940</td>
					<td class="hidden-phone ">Feb 12</td>
					<td class="hidden-480 "><span class="label label-info arrowed arrowed-right">Sold</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr><tr class="even">
					<td class="center  sorting_1">
						<label><input type="checkbox"><span class="lbl"></span></label>
					</td>
					<td class=" "><a href="#">fine.com</a></td>
					<td class=" ">$25</td>
					<td class="hidden-480 ">2,983</td>
					<td class="hidden-phone ">Apr 01</td>
					<td class="hidden-480 "><span class="label label-warning">Expiring</span></td>
					<td class=" ">
						<div class="hidden-phone visible-desktop btn-group">
							<button class="btn btn-mini btn-success"><i class="icon-ok"></i></button>
							<button class="btn btn-mini btn-info"><i class="icon-edit"></i></button>
							<button class="btn btn-mini btn-danger"><i class="icon-trash"></i></button>
							<button class="btn btn-mini btn-warning"><i class="icon-flag"></i></button>
						</div>
						<div class="hidden-desktop visible-phone">
							<div class="inline position-relative">
								<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down icon-only"></i></button>
								<ul class="dropdown-menu dropdown-icon-only dropdown-yellow pull-right dropdown-caret dropdown-close">
									<li><a href="#" class="tooltip-success" data-rel="tooltip" title="" data-placement="left" data-original-title="Edit"><span class="green"><i class="icon-edit"></i></span></a></li>
									<li><a href="#" class="tooltip-warning" data-rel="tooltip" title="" data-placement="left" data-original-title="Flag"><span class="blue"><i class="icon-flag"></i></span> </a></li>
									<li><a href="#" class="tooltip-error" data-rel="tooltip" title="" data-placement="left" data-original-title="Delete"><span class="red"><i class="icon-trash"></i></span> </a></li>
								</ul>
							</div>
						</div>
					</td>
				</tr></tbody></table><div class="row-fluid"><div class="span6"><div class="dataTables_info" id="table_report_info">Showing 1 to 10 of 22 entries</div></div><div class="span6"><div class="dataTables_paginate paging_bootstrap pagination"><ul><li class="prev disabled"><a href="#"><i class="icon-double-angle-left"></i></a></li><li class="active"><a href="#">1</a></li><li><a href="#">2</a></li><li><a href="#">3</a></li><li class="next"><a href="#"><i class="icon-double-angle-right"></i></a></li></ul></div></div></div></div>
	
</div>
	
	
	
	
	
		
	
		
		
		<script type="text/javascript">
		
		$(top.hangge());
		
		
		</script>	
		
		
		
		
		
			
	</body>
</html>

