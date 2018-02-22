<!doctype html>
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
%>
<html lang="en" class="fullscreen-bg">
<head>
	<title>Login</title>
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
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	function login(){
		$('#myModal').modal('show');
		$.ajax({
			type: "POST",
			async:false,
			data:{"userName":$("#signin-email").val(),"passWord":$("#signin-password").val()},
			url: "<%=basePath%>/mytest/test.action",
			dataType: "json",
			success: function(data, textStatus) {  
			//		$("#test").html(data.userName);
				if(data.judge==false){
					$(".modal-body").html("账号密码错误");
					
				}else{
					$(".modal-body").html("登陆成功");
					window.location.href ="<%=basePath%>/mytest/home.action";
				}
			},
			errer:function(){
				alert("网络错误");
			}
		})
		return false;
	}
	
		
	</script>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box">
					<div class="left">
						<div class="content">
							<div class="header">
								<div class="logo text-center"><img src="<%=basePath%>/assets/img/logo-dark.png" alt="Klorofil Logo"></div>
								<p id="errerInfo" class="lead">Login to your account</p>
							</div>
							
							<form class="form-auth-small"  method="post" onsubmit="return login();">
								<div class="form-group">
									<label for="signin-email" class="control-label sr-only">username</label>
									<input type="text"  class="form-control" id="signin-email"  name="userName" placeholder="Username">
								</div>
								<div class="form-group">
									<label for="signin-password" class="control-label sr-only">Password</label>
									<input type="password" class="form-control" id="signin-password" name="passWord" placeholder="Password">
								</div>
								<div class="form-group clearfix">
									<label class="fancy-checkbox element-left">
										<input type="checkbox">
										<span>Remember me</span>
									</label>
								</div>
								<button type="submit"   class="btn btn-primary btn-lg btn-block">登陆</button>
								<div class="bottom">
									<span class="helper-text"><i class="fa fa-lock"></i> <a href="#">忘记密码?</a></span>
								</div>
							</form>
						</div>
					</div>
					<div class="right">
						<div class="overlay"></div>
						<div class="content text">
							<h1 class="heading">世界可见与不可见的真实。</h1>
							<p>by The Develovers</p>
						</div>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>
		<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
               
            </div>
            <div class="modal-body text-center">
            	正在登陆中<i class="fa fa-spinner fa-spin"></i>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
	</div>
	<!-- END WRAPPER -->
</div>
<script type="text/javascript">
		
</script>
</body>

</html>
