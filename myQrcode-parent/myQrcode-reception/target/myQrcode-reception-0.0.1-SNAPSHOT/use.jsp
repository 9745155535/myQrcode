<!doctype html>
<html lang="en" class="fullscreen-bg">
<%@ page language="java" pageEncoding="utf-8"%>
<%
	String basePath=request.getContextPath();
    
%>
<head>
	<title>Lockscreen | Klorofil - Free Bootstrap Dashboard Template</title>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
	<!-- VENDOR CSS -->
	<link rel="stylesheet" href="<%=basePath %>/assets/vendor/bootstrap/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath %>/assets/vendor/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="<%=basePath %>/assets/vendor/linearicons/style.css">
	<!-- MAIN CSS -->
	<link rel="stylesheet" href="<%=basePath %>/assets/css/main.css">
	<!-- FOR DEMO PURPOSES ONLY. You should remove this in your project -->
	<link rel="stylesheet" href="<%=basePath %>/assets/css/demo.css">
	<!-- GOOGLE FONTS -->
	<link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700" rel="stylesheet">
	<!-- ICONS -->
	<link rel="apple-touch-icon" sizes="76x76" href="<%=basePath %>/assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="<%=basePath %>/assets/img/favicon.png">
</head>

<body>
<script src="<%=basePath%>/assets/vendor/jquery/jquery.min.js"></script>
<script src="<%=basePath%>/assets/vendor/jquery/jquery.watermark.js"></script>
<script type="text/javascript">
		var frim=eval(<%=request.getAttribute("frim")%>);
		var goods=eval(<%=request.getAttribute("goods")%>);
		var goodsInfo=eval(<%=request.getAttribute("goodsInfo")%>);
		
		function getLocalTime(nS) {  
		    var d = new Date(nS);
		    
		    return d.Format("yyyy-MM-dd hh:mm:ss");
		}
		function load(){
		
				//head load
				$("#name").html(goodsInfo.goodsName);
				$("#qrcode").html(goods.qrcode);
				if(goods.judge){
					$(".text-success").html("该商品认证于:"+getLocalTime(eval(goods.useTime).time)).attr("class","text-warning");
				}else{
					$(".text-success").html("认证成功");
				}
				$("#frim").find("span").html(frim.frimName);
				$("#email").find("span").html(frim.email);
				$("#web").find("a").html(frim.web);
				$("#wx").find("a").html(frim.wx).attr("href","<%=basePath%>/qrcode.jsp?w="+frim.wx);
				$("#text").html(goodsInfo.introduction);
					
		}
		function end(){
			$.ajax({
				type: "GET",
				data:{"userName":"<%=request.getAttribute("userName")%>","qrcode":goods.qrcode,"indexItme":"<%=request.getAttribute("indexItme")%>"},
				async:true,
				url: "<%=basePath%>/authentication/end.action",
				dataType: "json",
				success: function(data, textStatus) {
					goods=data.goods;
					$('.auth-box').watermark({
					    texts : [ goodsInfo.goodsId,getLocalTime(eval(goods.useTime))], //水印文字
					    textColor : "#d2d2d2", //文字颜色
					    textFont : '14px 微软雅黑', //字体
					    width : 200, //水印文字的水平间距
					    height : 250,  //水印文字的高度间距（低于文字高度会被替代）
					    textRotate : -30 //-90到0， 负数值，不包含-90
					});
				},
				errer:function(){
					alert("网络错误");
				}
			})
		}
		function is_weixin(){
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				return true;
		 	} else {
				return false;
			}
		}
	</script>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div class="vertical-align-wrap">
			<div class="vertical-align-middle">
				<div class="auth-box clearfix" style="height: 700px">
					<div class="content " >
						<div class="user panel-body ">
						<div class="text-center">
							<img src="<%=basePath %>/assets/img/qrcode.png" width="120px" class="img-circle" alt="Avatar">
							<h5 >商品:<span id="name"></span></h5>
							<h5 >编号:<span id="qrcode"></span></h5>
							<h3 class="text-success">该商品认证于:2015-3-45 23:56:66</h3>
						</div>
							<div class="profile-detail">
									<div class="profile-info">
										<h4 class="heading">基本信息</h4>
										<ul class="list-unstyled list-justify">
											<li id="frim">公司:<span></span></li>
											<li id="email">邮箱: <span></span></li>
											<li id="web">官网 :<span><a href="#"></a></span></li>
											<li id="wx">微信 :<span><a href="#"></a></span></li>
										</ul>
									</div>
									<div class="profile-info ">
										<h4 class="heading">简介</h4>
										<span id="text"></span>
									</div>
								</div>
							
						</div>
					</div>
				</div>
					
			</div>
		</div>
	</div>
	<!-- END WRAPPER -->
	<script src="<%=basePath%>/assets/vendor/jquery/layer.js"></script>
<script type="text/javascript">
function opening(){
	layer.open({
        type: 2,
        btn: ['关闭'],
        scrollbar:false,
        title: '关注',
        shadeClose: true,
        area: ['90%', '90%'],
        content: '<%=basePath%>/qrcode.jsp' ,//iframe的url
        success:function(layero, index){
        		var body=layer.getChildFrame('body', index);
        		body.find("#qrcode").attr("src","http://www.tpy10.net/tp.php?id="+frim.wx);
        	
        	layer.iframeAuto(index);
        },
        yes:function( index,layero){
        	var body=layer.getChildFrame(".row", index);
        	layer.close(index);
        }
        
        
    }); 
}
</script>
	<script type="text/javascript">
	$(function(){
		load();
		end();
		if(is_weixin())
			setTimeout("opening();",6000);
		
		
	})
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	    
	}
	
	</script>
</body>

</html>
