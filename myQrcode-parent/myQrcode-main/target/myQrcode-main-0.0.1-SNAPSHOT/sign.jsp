<!doctype html>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
%>
<html lang="en" class="fullscreen-bg">
<head>
	<title>sign</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath%>/assets/css/bootstrap.min.css">
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
	function login(){
		var userName= $("#userName").val();
		var passWord= $("#passWord").val();
		var email=$("#email").val();
		var wx=$("#wx").val();
		var web=$("#web").val();
		if(userName==''||userName==undefined){
			alert("用户名不能为空");
			return false;
		}
		else if(passWord==''||passWord=="undefined"){
			alert("密码不能为空");
			return false;
		}
		else if(email==''||email=="undefined"){
			alert("邮箱不能为空");
			return false;
		}
		else if(wx==''||wx=="undefined"){
			alert("微信公众号不能为空");
			return false;
		}
		else if(web==''||web=="undefined"){
			alert("官网不能为空");
			return false;
		}
		else{
		$.ajax({
			type: "POST",
			async:false,
			data:{"userName":userName,"passWord":passWord,"email":email,"wx":wx,"web":web},
			url: "<%=basePath%>/mytest/sign.action",
			dataType: "json",
			success: function(data, textStatus) {  
			//		$("#test").html(data.userName);
				if(data.judge==false){
					alert("用户名已存在,注册失败") ;   
				}else{
					$("#errerInfo").html("注册成功");
					$.ajax({
						type: "POST",
						async:false,
						data:{"userName":userName,"passWord":passWord},
						url: "<%=basePath%>/mytest/test.action",
						dataType: "json",
						success: function(data, textStatus) {  
						},
						errer:function(){
							alert("网络错误");
						}
					})
					window.location.href ="<%=basePath%>/mytest/home.action";
				}
			},
			errer:function(){
				alert("网络错误");
			}
		})
		return false;
		}
		return false;
	}
	
	</script>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box lockscreen clearfix">
					<div class="content" style="height: 800px">
						<h1 class="sr-only">Klorofil - Free Bootstrap dashboard</h1>
						<div class="logo text-center"><img src="assets/img/logo-dark.png" alt="Klorofil Logo"></div>
						
						<form onsubmit="return login();" method="post">
							<input class="form-control" type="text" id="userName" placeholder="用户名">
							<br>
							<input class="form-control" type="password" id="passWord" placeholder="密码">
							<br>
							<input class="form-control" type="email" id="email" placeholder="邮箱">
							<br>
							<input class="form-control" type="text" id="wx" placeholder="微信公众号ID">
							<br>
							<input class="form-control" type="text" id="web" placeholder="官方网址">
							<br>
							
							<div class="text-center" >		
									<button class="btn btn-primary btn-block" type="submit">注册</button>
								
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
