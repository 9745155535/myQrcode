<!doctype html>
<html lang="en" class="fullscreen-bg">
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
    
%>
<head>
	<title>Qrcode</title>
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
	<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript">
		function load(){
			var w="<%=request.getParameter("w")%>";
			if(w!=null){
				$("#qrcode").attr("src","http://www.tpy10.net/tp.php?id="+w);
			}
		}
	</script>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box lockscreen clearfix">
					<div class="content">
						<h1 class="sr-only">Klorofil - Free Bootstrap dashboard</h1>
						<div class="logo text-center"><img src="<%=basePath%>/assets/img/logo-dark.png" alt="Klorofil Logo"></div>
						<div class="user text-center">
							<img id="qrcode" src="http://www.tpy10.net/tp.php?id=juziwg" width="180px" class="img" alt="Avatar">
							<h2 class="name">关注有惊喜</h2>
						</div>
						<form action="">
							<div class="text-center" >
								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		load();
	</script>
	<!-- END WRAPPER -->
</body>

</html>
