<!doctype html>
<html lang="en">
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
    
%>
<head>
	<title>Table Page</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/linearicons/style.css">

	<!-- MAIN CSS -->
	<link rel="stylesheet" href="<%=basePath%>/assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="<%=basePath%>/assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="<%=basePath%>/assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<%=basePath%>/assets/img/favicon.png">
</head>

<body>
	<!-- WRAPPER -->
	
	<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="<%=basePath%>/mytest/home.action"><img src="<%=basePath%>/assets/img/logo-dark.png" alt="Klorofil Logo" class="img-responsive logo"></a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>
				</div>
				
				
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						
						
						<li class="dropdown">
							<a href="#" class="dropdown-toggle" data-toggle="dropdown"><img src="<%=basePath%>/assets/img/favicon.png" class="img-circle" alt="Avatar"> <span>${sessionScope.userName}</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a onclick="openS()"><i class="lnr lnr-cog"></i> <span>用户信息修改</span></a></li>
								<li><a href="<%=basePath%>/page-login.jsp"><i class="lnr lnr-exit"></i> <span>退出登录</span></a></li>
							</ul>
						</li>
						<!-- <li>
							<a class="update-pro" href="#downloads/klorofil-pro-bootstrap-admin-dashboard-template/?utm_source=klorofil&utm_medium=template&utm_campaign=KlorofilPro" title="Upgrade to Pro" target="_blank"><i class="fa fa-rocket"></i> <span>UPGRADE TO PRO</span></a>
						</li> -->
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="<%=basePath%>/mytest/home.action" ><i class="lnr lnr-home"></i> <span>首页</span></a></li>
						
						<li>
							<a href="#subPages" data-toggle="collapse" class="active" id="sub" aria-expanded="true"><i class="lnr lnr-cog"></i> <span>管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse in" aria-expanded="true" style="">
								<ul class="nav">
									<li><a href="<%=basePath%>/mytest/table_judge.action?judge=frims" class="">公司管理</a></li>
									<li><a href="<%=basePath%>/mytest/table_judge.action?judge=goods" class="">商品管理</a></li>
								</ul>
							</div>
						</li>
						
						<li><a href="<%=basePath%>/GainDate/find.action" class=""><i class="lnr lnr-chart-bars"></i> <span>查看</span></a></li>
						
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid" id="operation-fluid">
					<h3 class="page-title">Table </h3>
					<div class="row">
						<div>
							<!-- TABLE HOVER -->
							<div class="panel">
								<div class="panel-heading">
									<h1 class="panel-title">Hover Row </h1>
									
								</div>
								<div class="panel-body">
									<table class="table table-hover">
										<thead >
											<tr id="tr_info">
																									
											</tr>
										</thead>
										<tbody id="data_table">
											<tr>
												<td>3</td>
												<td>Jane</td>
												<td>Doe</td>
												<td>@jane</td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
							<!-- END TABLE HOVER -->
						</div>
					</div>
					<div class="panel-footer">
					<div class="row">
							<div class="text-right " >
									<form>		
										<button type="button" class="btn btn-info " id="operation" onclick='operation_begin()'>操作</button>
										<button type="button" class="btn btn-info" onclick="down()" >上一页</button>
										<select class="input-sm" id="data_select">
											<option value="1" >1</option>										
										</select>	
										<button type="button" class="btn btn-info" onclick="up()" >下一页</button>
										<text id="data_text"></text>
									</form>
							</div>
						</div>
					</div>
					
								
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 <a href="#" target="_blank">Theme I Need</a>. All Rights Reserved. </p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	</div>
	</div>
	
	<!-- Javascript -->
	<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery/layer.js"></script>
	<script src="<%=basePath%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>/assets/scripts/klorofil-common.js"></script>
	<script src="<%=basePath%>/assets/scripts/table.js"></script>
	<script src="<%=basePath%>/assets/scripts/settings.js"></script>
	<script type="text/javascript">
	
	
	</script>
</body>

</html>
