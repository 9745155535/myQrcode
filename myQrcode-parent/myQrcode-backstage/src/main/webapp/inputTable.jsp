<!doctype html>
<html lang="en">
<%@ page language="java" pageEncoding="utf-8"%>
<head>
	<title>Elements | Klorofil - Free Bootstrap Dashboard Template</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="assets/vendor/linearicons/style.css">
	
	<link rel="stylesheet" href="assets/vendor/jquery/skin/default/layui.css">
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

<body>
	<!-- WRAPPER -->
	<div>
		
		<div class="main">
			<!-- MAIN CONTENT -->
			<div class="main-content">
				<div class="container-fluid">
					<div class="row">
						<div class="col-md-6">
							<!-- INPUTS -->
							<div class="panel" >
								<div class="panel-heading">
									<h3 class="panel-title">Inputs</h3>
								</div>
								<div class=" panel-body">
									<form class="text-center" id="afd" action="<%=request.getContextPath()%>/GainDate/upload.action" method="post" enctype="multipart/form-data">
										<img class="img-circle "   alt="Avatar" width="90px" src="assets/img/user-medium.png"> 								
										<input class="layui-upload-file"  name="file" id="img" value="" type="file"></input>
										<input class="form-control" id="id"  style="visibility: hidden" placeholder="商品名字" type="text"></input>
									</form>
									
									
									<div class="input-group"  id="name">
										<span class="input-group-addon"><i class="lnr lnr-user"></i></span>
										<input class="form-control" type="text" placeholder="名称">
										
									</div>
									<br>
									<div class="input-group" id="wxid">
										<span class="input-group-addon"><i class="lnr lnr-bubble"></i></span>
										<input class="form-control"  type="text" placeholder="微信">
									</div>
									<br class="1">
									<div class="input-group" id="email">
										<span class="input-group-addon"><i class="lnr lnr-envelope"></i></span>
										<input class="form-control"  type="text" placeholder="邮箱">
									</div>
									<br class="1">
									
									<div class="input-group"  id="web">
										<span class="input-group-addon"><i class="lnr lnr-enter"></i></span>
										<input class="form-control"  type="text" placeholder="官网">
									</div>
									<br class="1">
									
									<textarea class="form-control" id="text" placeholder="简介" rows="4"></textarea>
									<br>
									<select class="form-control" id="select">
										<option value="cheese" disabled="disabled"  selected="selected">Cheese</option>
										
									</select>
									
								</div>
							</div>
							<!-- END INPUTS -->
							
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
	<script src="assets/vendor/jquery/jquery.min.js"></script>
	<script src="assets/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/vendor/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	<script src="assets/scripts/klorofil-common.js"></script>
	<script type="text/javascript">
	$("#img").change(function(){
	var formData= new FormData($("#afd")[0]);
	var file=document.getElementById("img").files[0];
	$(".img-circle").attr("src",window.URL.createObjectURL(file));
	})
	function clp(){
   		return  $("#img").click();
	}
	</script>
</body>

</html>
