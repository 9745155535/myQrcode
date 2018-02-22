<!doctype html>
<html lang="en">
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
    
%>
<head>
	<title>Goods Page</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/linearicons/style.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/chartist/css/chartist-custom.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/jquery/skin/default/layer.css">
	<link rel="stylesheet" href="<%=basePath%>/assets/vendor/jquery/skin/default/layui.css">
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
     <input type="hidden" value="<%=session.getAttribute("userName")%>" id="userName"/>
  	<input type="hidden" value="<%=request.getAttribute("frimId")%>" id="frimId"/>

	<!-- WRAPPER -->
	<div id="wrapper"  >
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
							<a class="dropdown-toggle" aria-expanded="false" href="#" data-toggle="dropdown"><i class="lnr lnr-cloud-download"></i> <span>导出信息</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
							<ul class="dropdown-menu">
								
								<li><a href="<%=basePath%>/ExclDownLog/goods.action?goodsId=<%=request.getParameter("Id")%>">商品生成使用信息导出</a></li>
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
						<li><a href="<%=basePath%>/mytest/home.action" ><i class="lnr lnr-home"></i> <span>首页</span></a></li>
						<li>
							<a href="#subPages" data-toggle="collapse" class="active" class="collapsed"><i class="lnr lnr-cog"></i> <span>管理</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>
							<div id="subPages" class="collapse in">
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
					<div class="panel panel-profile">
						<div class="clearfix">
							<!-- LEFT COLUMN -->
							<div class="profile-left">
								<!-- PROFILE HEADER -->
								<div class="profile-header">
									<div class="overlay"></div>
									<div class="profile-main">
										<img src="<%=basePath%>/assets/img/user-medium.png" id="user_img" height="90px" class="img-circle" alt="Avatar">
										<h3 class="name">公司名字</h3>
										
										<span class="online-status status-available">974515535@qq.com</span>
									</div>
									<div class="profile-stat">
										<div class="row">
											<div class="col-md-4 stat-item">
												<text>45 </text><span>二维码总数</span>
											</div>
											<div class="col-md-4 stat-item">
												<text>2014</text> <span>使用数目</span>
											</div>
											<div class="col-md-4 stat-item">
												<text>21</text> <span>使用比例</span>
											</div>
										</div>
									</div>
								</div>
								<!-- END PROFILE HEADER -->
								<!-- PROFILE DETAIL -->
								<div class="profile-detail">
									<div class="profile-info">
										<h4 class="heading">基本信息</h4>
										<ul class="list-unstyled list-justify">
											<li>Birthdate <span>24 Aug, 2016</span></li>
											<li>Email <span>samuel@mydomain.com</span></li>
											<li>Website <span><a href="#">www.themeineed.com</a></span></li>
										</ul>
									</div>
									<div class="profile-info">
										<h4 class="heading">简介</h4>
										<p id="introduction">Interactively fashion excellent information after distinctive outsourcing.</p>
									</div>

									<div class="text-center">
										<button  onclick="opening()" class="btn btn-primary">修改商品信息</button>
									</div>
								</div>
								<!-- END PROFILE DETAIL -->
							</div>
							<!-- END LEFT COLUMN -->
							<!-- RIGHT COLUMN -->
							<div class="profile-right">
								<div class="panel">
								<div class="panel-heading">
									<h3 class="panel-title">生成数目/使用数目</h3>
									
								</div>
								<div class="panel-body">
									<div id="visits-chart" class="ct-chart"></div>
									<div class="text-center" ><button onclick="qrcode()" class="btn btn-default">生成商品二维码</button></div>
								</div>
								</div>
								<!-- TABBED CONTENT -->
								<div class="custom-tabs-line tabs-line-bottom left-aligned">
									<ul class="nav" role="tablist">
										<li class="active"><a href="#tab-bottom-left1" role="tab" data-toggle="tab">历史下载<span class="badge" id="downLog">7</span></a></li>
										<li><a href="#tab-bottom-left2" role="tab" data-toggle="tab">Projects <span class="badge">7</span></a></li>
									</ul>
								</div>
								<div class="tab-content">
									<div class="tab-pane fade in active" id="tab-bottom-left1">
										<ul class="list-unstyled activity-timeline">
											
										</ul>
										<div class="margin-top-30 text-center">
											<a class="btn btn-default" id="downlogall" onclick="downlog(false)">显示全部</a></div>
										</div>
										
										
									<div class="tab-pane fade" id="tab-bottom-left2">
										<div class="table-responsive">
											<table class="table project-table">
												<thead>
													<tr>
														<th>Title</th>
														<th>Progress</th>
														<th>Leader</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>
													
												</tbody>
											</table>
										</div>
									</div>
								</div>
								<!-- END TABBED CONTENT -->
							<!-- END RIGHT COLUMN -->
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
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:300px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               
            </div>
            <div class="modal-body text-center">
            	<div class="form-group">
						<input type="text"  class="form-control" id="length"   placeholder="生成个数">
				</div>
            </div>
            <div class="modal-footer">
            	<button type="button" class="btn btn-default" id="model_q">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="end">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
	</div>
	
	
	
	</div>
	
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="<%=basePath%>/assets/scripts/klorofil-common.js"></script>
	<script src="<%=basePath%>/assets/vendor/chartist/js/chartist.min.js"></script>
	<script src="<%=basePath%>/assets/vendor/jquery/layer.js"></script>
	<script src="<%=basePath%>/assets/scripts/goods.js"></script>
	<script src="<%=basePath%>/assets/scripts/settings.js"></script>
	<script type="text/javascript">
	
</script>
</body>

</html>
