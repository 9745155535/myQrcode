<!doctype html>
<%@page import="java.util.Date"%>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
%>
<html lang="en">

<head>
	<title>Home Page </title>
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
	<!-- WRAPPER -->
	
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top"  >
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
							<a class="dropdown-toggle" aria-expanded="false" href="#" data-toggle="dropdown"><i class="lnr lnr-cloud-download"></i> <span>导出信息</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="<%=basePath%>/ExclDownLog/frimInfo_all.action">所有公司信息导出</a></li>
								<li><a href="<%=basePath%>/ExclDownLog/goodsInfo_all.action">所有商品信息导出</a></li>
								<li><a href="<%=basePath%>/ExclDownLog/goods_all.action">所有商品生成使用信息导出</a></li>
							</ul>
						</li>
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
						<li><a href="<%=basePath%>/mytest/home.action" class="active"><i class="lnr lnr-home"></i> <span>首页</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-cog"></i> <span>管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse ">
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
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-heading">
							<h3 class="panel-title">你好! ${sessionScope.userName}</h3>
							<p class="panel-subtitle" id="updateTime"></p>
						</div>
						<div class="panel-body">
							<div class="row">
								
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-shopping-bag"></i></span>
										<p>
											<span class="number" id="item_lenght">###</span>
											<span id="test" class="title">物品种类量</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-download"></i></span>
										<p>
											<span class="number" id="all_lenght">###</span>
											<span class="title">产品总量</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-eye"></i></span>
										<p>
											<span class="number" id="use_lenght">###</span>
											<span class="title">防伪认证量</span>
										</p>
									</div>
								</div>
								<div class="col-md-3">
									<div class="metric">
										<span class="icon"><i class="fa fa-bar-chart"></i></span>
										<p>
											<span class="number" id="use_percent">###</span>
											<span class="title">使用比例</span>
										</p>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-9">
								<!-- MULTI CHARTS -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">使用情况曲线图</h3>
								</div>
								<div class="panel-body">
									<div id="visits-trends-chart" class="ct-chart"></div>
								</div>
							</div>
							<!-- END MULTI CHARTS -->
									
								</div>
								<div class="col-md-3">
									<div class="weekly-summary text-right" id="CU">
										<span class="number" ></span> <span class="percentage"><i class="fa fa-caret-up text-success"></i><text> 12%</text></span>
										<span class="info-label">本月认证量</span>
									</div>
									<div class="weekly-summary text-right" id="UU">
										<span class="number" ></span> <span class="percentage"><i class="fa fa-caret-up text-success"></i><text> 12%</text></span>
										<span class="info-label">本月生成量</span>
									</div>
									<div class="weekly-summary text-right" id="Uall">
										<span class="number" ></span> <span class="percentage"><i class="fa fa-caret-up text-success"></i> --%</span>
										<span class="info-label">产品总量</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- END OVERVIEW -->
					<div class="row">
						
						<div class="col-md-6">
							<!-- TASKS -->
							
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">商品使用比例</h3>
									
									<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>	
									</div>
									
							</div>
							
							
							
								<div class="panel-body" >
									<ul class="list-unstyled task-list" id="compare_list">
										
									</ul>
								</div>
								<div class="panel-footer">
									<div class="row">
										
											<form >
												<div class="input-group">
													<input type="text" value="" class="form-control" placeholder="Search dashboard...">
													<span class="input-group-btn"><button type="button" class="btn btn-primary">Go</button></span>
												</div>
											</form>
										
									</div>
								</div>
								<div class="panel-footer">
								<div class="row">
									<div class="row text-center" >
									<form>
										<button type="button" class="btn btn-info" onclick="down_compare('compare')">上一页</button>
										
										<select class="input-sm" id="compare_select">
											<option value="1" >1</option>
											
										</select>
										<button type="button" class="btn btn-info" onclick="up_compare('compare')">下一页</button>	
										<text id="compare_text"></text>
									</form>
									</div>
									</div>
								</div>
								
							</div>
							<!-- END TASKS -->
						</div>
						<div class="col-md-6">
							<!-- RECENT PURCHASES -->
							<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">历史下载</h3>
									<div class="right">
										<button type="button" class="btn-toggle-collapse"><i class="lnr lnr-chevron-up"></i></button>
										<button type="button" class="btn-remove"><i class="lnr lnr-cross"></i></button>
									</div>
								</div>
								<div class="panel-body no-padding">
									<table class="table table-striped">
										<thead>
											<tr>
												<th>商品</th>
												<th>数量</th>
												<th>时间</th>
												<th>下载</th>
											</tr>
										</thead>
										<tbody id="table_data">
											
										</tbody>
									</table>
								</div>
								<div class="panel-footer">
									<div class="row">
										<div class="row text-center" >
									<form >
										<button type="button" class="btn btn-info" onclick="down_compare('downLog')">上一页</button>
										
										<select class="input-sm" id="downLog_select">
											<option value="cheese">Cheese</option>
											
										</select>
										
										<button type="button" class="btn btn-info" onclick="up_compare('downLog')">下一页</button>
										<text id="downLog_text"></text>
									</form>
									</div>
									</div>
								</div>
							</div>
							<!-- END RECENT PURCHASES -->
					</div>
				</div>
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
	<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery.easy-pie-chart/jquery.easypiechart.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="<%=basePath%>/assets/scripts/klorofil-common.js"></script>
	<script src="<%=basePath%>/assets/scripts/index.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery/layer.js"></script>
	<script src="<%=basePath%>/assets/scripts/settings.js"></script>
</body>

</html>
