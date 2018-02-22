function getRealPath(){
  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

  var realPath=localhostPaht+projectName;
  return realPath;
}
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
function Percentage(num, number2) { 
	var k=(num/number2)*100;
    return k.toFixed(2);// 小数点后两位百分比
}
var frimId=$("#frimId").val();
var frim;
var userName=$("#userName").val();
var frims; 
var pojo;
function getLocalTime(nS) {  
    var d = new Date(nS);
    
    return d.Format("yyyy-MM-dd hh:mm:ss");
}
function loadBegin(){
	$.ajax({
		type: "POST",
		data:{"id":GetQueryString("Id")},
		async:false,
		url: getRealPath()+"/GainDate/goods_goodsId.action",
		dataType: "json",
		success: function(data, textStatus) {
			pojo=eval(data.goods);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	$.ajax({
		type: "POST",
		data:{"id":frimId},
		async:false,
		url: getRealPath()+"/GainDate//frim_frimId.action",
		dataType: "json",
		success: function(data, textStatus) {
			frim=eval(data.frim);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	loadDate();
}
function loadDate(){
	//frim info load
	//begin children()
		
		var fe= $(".profile-main");
		fe.find("h3").html(pojo.goodsName);
		fe.find("span").html(frim.frimName);
		$("#introduction").html(pojo.introduction);
		$.ajax({
		   	url:getRealPath()+"/resources/img/"+pojo.icon+"",
		   	type:'HEAD',
		   	error: function() {
		       //file not exists
		   	},
		   	success: function() {
		       //file exists
		     	  $(".profile-main").find("img").attr("src",getRealPath()+"/resources/img/"+pojo.icon+"");
			
		   	}
			});
		fe= $(".profile-stat").find("text");
		fe.each(function(index,t){
			if(index==0) $(this).html(pojo.allNumber);
			else if(index==1) $(this).html(pojo.useNumber);
			else $(this).html(Percentage(pojo.useNumber,pojo.allNumber)+"%");
		})
		$(".list-unstyled").find("span").each(function(index,t){
			if(index==0) $(this).html(getLocalTime(pojo.time));
			else if(index==1) $(this).html(frim.email);
			else $(this).find("a").html(frim.web).attr("href","https://"+frim.web);
		})
		
		$.ajax({
		type: "POST",
		data:{"id":pojo.goodsId,"judge":"goods"},
		async:true,
		url: getRealPath()+"/GainDate/downlog.action",
		dataType: "json",
		success: function(data, textStatus) {
			var goodsInfo=eval(data.goodsdate);
			var downLog=eval(data.date);
			var keep='';
			for(var i=0;i<downLog.length;i++){
				keep=keep+"<li><a href='"+getRealPath()+"/resources/Zip/"+downLog[i].fileName+".zip' class='fa fa-download activity-icon'></a><p>生成"+downLog[i].length+"个商品防伪二维码 <span class='timestamp'>"+getLocalTime(downLog[i].time)+"</span></p></li>"
			}
			$(".activity-timeline").empty().append(keep);
			$("#downLog").html(downLog.length);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	
	$.ajax({
		type: "POST",
		data:{"id":GetQueryString("Id")},
		async:true,
		url: getRealPath()+"/GainDate/goods_map.action",
		dataType: "json",
		success: function(data, textStatus) {
			
			var data = {
					labels: data.monthList,
					series: [
						data.allList,
						data.useList
					]
				};

			var options = {
					height: 300,
					axisX: {
						showGrid: false,
						scaleShowLabels:true
					},
				};

				new Chartist.Bar('#visits-chart', data, options);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	
}
function qrcode(){
	$("#model_q").attr("style","");
	$('#myModal').modal('show');
	
}
function loadFrims(){
	
	$.ajax({
		type: "POST",
		data:{"judge":GetQueryString("judge")},
		async:false,
		url: getRealPath()+"/GainDate/load_frims.action",
		dataType: "json",
		success: function(data, textStatus) {
			frims=eval(data.date);
		},
		errer:function(){
			alert("网络错误");
		}
	})
}

function opening(){
	
	layer.open({
        type: 2,
        btn: ['确定', '取消'],
        scrollbar:false,
        title: '添加',
        shadeClose: true,
        area: ['380px', '88%'],
        content:  getRealPath()+"/inputTable.jsp" ,//iframe的url
        success:function(layero, index){
        	
        	loadFrims();
        	var body=layer.getChildFrame('.row', index);
        	body.find("#email").attr("style","display: none");
        	body.find("#web").attr("style","display: none");
        	body.find("#wxid").attr("style","display: none");
        	body.find(".1").remove();
        	body.find("#name").find("input").val(pojo.goodsName);
			body.find("#id").val(pojo.goodsId);
			body.find("img").attr("src", getRealPath()+"/resources/img/"+pojo.icon+"");
			body.find("#text").val(pojo.introduction);
        		body.find("select").children().val("请选择公司").html("请选择公司");
        		for(var i=0;i<frims.length;i++){		
        			var keep=eval(frims[i]);
        			body.find("select").append("<option value='"+keep.frimId+"' >"+keep.frimName+"</option>");		
        		}
        	body.find("select").find("[value='"+pojo.frimId+"']").attr("selected","selected")
        	
        //	layer.iframeAuto(index);
        },
        yes:function(index,layero){
        	var body=layer.getChildFrame(".row", index);
        	var name=body.find("#name").find("input").val();
			var email=body.find("#email").find("input").val();
			var web=body.find("#web").find("input").val();
			var wx=body.find("#wxid").find("input").val();
			var id=body.find("#id").val();
			var text=body.find("#text").val();
			var select=body.find("select").find("option:selected").val();
			var fileName;
			var formData= new FormData(body.find("#afd")[0]);
       	 	$.ajax({
                async: false,//要求同步 不是不需看你的需求
                url :  getRealPath()+"/GainDate/upload.action",  
                type : 'POST',  
                data : formData,  
                processData : false,  //必须false才会避开jQuery对 formdata 的默认处理   
                contentType : false,  //必须false才会自动加上正确的Content-Type
                success : function(result) {  
                       fileName=result.fileName;
                },  
                error : function(result) {  
                   
                }  
            });  
        	
        		$.ajax({
    				type: "POST",
    				data:{"judge":"goods","name":name,"id":pojo.goodsId,"img":fileName,"text":text,"select":select,"email":email,"web":web,"wx":wx},
    				async:false,
    				url:  getRealPath()+"/GainDate/upDate.action",
    				dataType: "json",
    				success: function(data, textStatus) {
    					loadBegin();
    				},
    				errer:function(){
    					alert("网络错误");
    				}
    			})
        		
        	
        	
        	
        	layer.close(index);
        	loadBegin();
        }
        
        
    }); 
} 
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

$(document).ready(function() {
	loadBegin();
    $('#myModal').on('shown.bs.modal', function (e) {  
        // 关键代码，如没将modal设置为 block，则$modala_dialog.height() 为零  
        $(this).css('display', 'block');  
        var modalHeight=$(window).height() / 3 ;  
        $(this).find('.modal-dialog').css({  
            'margin-top': modalHeight  
        });  
    }); 
    $("#model_q").click(function(){
    	
    	var plan=0;
    	var setout=0;
    	var zipPlan=0;
    	var length=$("#length").val();
    	$(".modal-body").html("<div class='progress'><div id='plan' class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width: 0%'></div></div><div id='planNumber'><i class='fa fa-spinner fa-spin'></i>1/3 准备中...</div>");
    	
    		if(length!=""&&length!=undefined){
	    		$.ajax({
					type: "GET",
					data:{"id":pojo.goodsId,"length":length},
					async:true,
					url: getRealPath()+"/GainDate/qrcode.action",
					dataType: "json",
					success: function(data, textStatus) {
						
						if(data.file!=null){
							$(".modal-body").html("生成成功");
							$("#model_q").attr("style","display: none");
							setTimeout(function (){
								$(".modal-body").html("<a class='btn btn-primary btn-block' href='"+getRealPath()+"/resources/Zip/"+data.file+".zip'>下载</a>");
							
							}, 1000);
						}else{
							$(".modal-body").html("生成失败");
						}
					},
					errer:function(){
						alert("网络错误");
					}
				})
	    	}
    	
    setTimeout(function(){
    	var interval= setInterval(function(){
    			$.ajax({
    				type: "GET",
    				async:true,
    				url: getRealPath()+"/GainDate/eyeSetout.action",
    				dataType: "json",
    				success: function(data, textStatus) {
    					setout=data.Setout;
    					var js=Percentage(setout,length);
    					$("#plan").attr({"aria-valuenow":js,"style":"width: "+js+"%"});
    					
    				},
    				errer:function(){
	    				alert("网络错误");
	    			}
    			})
    			if(setout>=length) {
    				clearInterval(interval);
    				setTimeout(function(){
    			    	$(".modal-body").html("<div class='progress'><div id='plan' class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width: 0%'></div></div><div id='planNumber'><i class='fa fa-spinner fa-spin'></i>2/3 生成中...</div>");
    			    	
						var j= setInterval(function(){
			    			$.ajax({
			    				type: "GET",
			    				async:true,
			    				url: getRealPath()+"/GainDate/eyePlan.action",
			    				dataType: "json",
			    				success: function(data, textStatus) {
			    					plan=data.plan;
			    					var js=Percentage(plan,length);
			    					$("#plan").attr({"aria-valuenow":js,"style":"width: "+js+"%"});
			    					
			    				},
			    				errer:function(){
				    				alert("网络错误");
				    			}
			    			})
			    			if(plan>=length) {
			    				clearInterval(j);
			  
			    				setTimeout(function(){
			    			    	$(".modal-body").html("<div class='progress'><div id='plan' class='progress-bar progress-bar-striped active' role='progressbar' aria-valuenow='0' aria-valuemin='0' aria-valuemax='100' style='width: 0%'></div></div><div id='planNumber'><i class='fa fa-spinner fa-spin'></i>3/3 压缩中...</div>");
									var k= setInterval(function(){
						    			$.ajax({
						    				type: "GET",
						    				async:true,
						    				url: getRealPath()+"/GainDate/eyeZipPlan.action",
						    				dataType: "json",
						    				success: function(data, textStatus) {
						    					zipPlan=data.ZipPlan;
						    					var js=Percentage(zipPlan,length);
						    					$("#plan").attr({"aria-valuenow":js,"style":"width: "+js+"%"});
						    					
						    				},
						    				errer:function(){
							    				alert("网络错误");
							    			}
						    			})
						    			if(zipPlan>=length) {
						    				clearInterval(k);
						    			}
						    	}, 50);
									
			    				}, 500)
			    			}
			    	}, 50);
						
    				}, 500)
    			}
    	}, 50);
    },500);
    		
    		
    	
    	
    })
    
    $("#end").click(function(){
    	$(".modal-body").html("<div class='form-group'><input type='text'  class='form-control' id='length'   placeholder='生成个数'></div>");
    	loadBegin();
    });
		
})


