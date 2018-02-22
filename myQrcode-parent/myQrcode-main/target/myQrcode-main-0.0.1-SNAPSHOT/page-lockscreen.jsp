<!doctype html>
<html lang="en" class="fullscreen-bg">
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
    
%>
<head>
	<title>validation </title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
</head>
<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
<script type="text/javascript">
function button(){
	$.ajax({
		type: "get",
		data:{"qr":"<%=request.getParameter("qr")%>","item":"<%=request.getParameter("item")%>"},
		async:false,
		url: "<%=basePath%>/authentication/begin.action",
		dataType: "json",
		success: function(data, textStatus) {
			alert("fsdafsd");
		}
	});
	window.location.href="<%=basePath%>/authentication/end.action?item=<%=request.getParameter("item")%>&qr=<%=request.getParameter("qr")%>";
	return false;
}
</script>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box lockscreen clearfix">
					<div class="content">
						<h1 class="sr-only">Klorofil - Free Bootstrap dashboard</h1>
						<div class="logo text-center"><img src="assets/img/logo-dark.png" alt="Klorofil Logo"></div>
						<div class="user text-center">
							<img src="assets/img/qrcode.bmp" width="120px" class="img-circle" alt="Avatar">
							<h2 class="name">Welcome to use </h2>
						</div>
						<form onsubmit="return button();">
							<div class="text-center" >
								<div class="">
									<button class="btn btn-primary btn-block" type="submit">验  证</button>
								</div>
								
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
</body>

</html>
