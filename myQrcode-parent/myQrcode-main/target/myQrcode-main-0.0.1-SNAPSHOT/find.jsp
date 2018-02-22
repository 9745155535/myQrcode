<!doctype html>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
%>
<html lang="en">

<head>
	<title>Find Page </title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/linearicons/style.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/chartist/css/chartist-custom.css">
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

<body >
<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
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
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
								<ul class="nav">
									<li><a href="<%=basePath%>/mytest/table_judge.action?judge=frims" class="">公司管理</a></li>
									<li><a href="<%=basePath%>/mytest/table_judge.action?judge=goods" class="">商品管理</a></li>
								</ul>
							</div>
						</li>
						
						<li><a class="active" href="<%=basePath%>/GainDate/find.action" class=""><i class="lnr lnr-chart-bars"></i> <span>查看</span></a></li>
						
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						
						<div class="panel-body ">
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">查找</h3>
								</div>
								<div class="panel-body">
									<p>Objectively network visionary methodologies via best-of-breed users. Phosfluorescently initiate go forward leadership skills before an expanded array of infomediaries. Monotonectally incubate web-enabled communities rather than process-centric.</p>
								</div>
							</div>
							<div class="row">
								<div class="col-md-3 col-sm-6" ></div>
								<div class="col-md-3 col-sm-6" >
								
								</div>
								<div class="col-md-3 col-sm-6" >
									<select class="form-control" id="select">
										<option value="df" disabled="disabled"  selected="selected">请选择类型</option>
										<option value="frims">公司查询</option>
										<option value="goods">商品查询</option>
										
									</select>
								</div>
								<div class="col-md-3 col-sm-6" >
									<form  onsubmit="return find();">
										<div class="input-group" id="down">
										
											<input type="text" value="" aria-expanded="false"  class="form-control dropdown-toggle"  placeholder="Search dashboard...">
											<ul class="dropdown-menu" >
												<li><a href="#"><i class="lnr lnr-user"></i> <span>My Profile</span></a></li>
								
											</ul>
											<span class="input-group-btn"><button type="submit" class="btn btn-primary">Go</button></span>
											
										</div>
									
									</form>
								</div>
							
						</div>
						<br>
							<div class="row">
								<div class="panel">
								
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
							</div>
						</div>
					</div>
					<!-- END OVERVIEW -->
				</div>
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">&copy; 2017 <a href="#" target="_blank">Theme I Need</a>. All Rights Reserved.</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	
	<script src="<%=basePath%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="<%=basePath%>/assets/scripts/klorofil-common.js"></script>
	<script src="<%=basePath%>/assets/scripts/find.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery/layer.js"></script>
	<script src="<%=basePath%>/assets/scripts/settings.js"></script>

</body>

</html>
