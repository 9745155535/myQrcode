function getRealPath(){
  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

  var realPath=localhostPaht+projectName;
  return realPath;
}
var userName=$("#userName").val();
var frimid=$("#frimid").val();
var judge=$("#judge").val();

var date;
var pojo;
var frims;

function getLocalTime(nS) {  
    var d = new Date(nS);
    
    return d.Format("yyyy-MM-dd hh:mm:ss");
}
function loadBegin(){
	
	
	$.ajax({
		type: "POST",
		data:{"id":frimid},
		async:false,
		url: getRealPath()+"/GainDate/frim_frimId.action",
		dataType: "json",
		success: function(data, textStatus) {
			pojo=eval(data.frim);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	
	
	$.ajax({
		type: "POST",
		data:{"id":pojo.frimId},
		async:true,
		url: getRealPath()+"/GainDate/goods_frimId.action",
		dataType: "json",
		success: function(data, textStatus) {
			var goodsdate=eval(data.goodsData);
			//排名
			$("#goodsInfo").children().each(function(index,t){
					if(index>=4||index>=goodsdate.length) return false;
					var keep =$(this).attr("style","");
					keep.find("span").html(goodsdate[index].useNumber);
					keep.find("p").html(goodsdate[index].goodsName);
					keep.find("a").attr("href",getRealPath()+"/GainDate/skip.action?judge=goods&Id="+goodsdate[index].goodsId);
					
					$.ajax({
					   url:getRealPath()+"/resources/img/"+goodsdate[index].icon+"",
					   type:'HEAD',
					   error: function() {
					       //file not exists
					      
					   },
					   success: function() {
					       //file exists
						keep.find("img").attr("src",getRealPath()+"/resources/img/"+goodsdate[index].icon+"");
					    
					   }
					});
				});
			//统计
			var sta=$("#statistical").empty();
			var keep='';
			for(var i=0;i<goodsdate.length;i++){
				keep=keep+"<tr><td><a href='"+getRealPath()+"/GainDate/skip.action?judge=goods&Id="+goodsdate[i].goodsId+"'>"+goodsdate[i].goodsName+"</a></td><td><div class='progress'><div class='progress-bar progress-bar-success' role='progressbar' aria-valuenow='"+Percentage(goodsdate[i].useNumber, goodsdate[i].allNumber)+"' aria-valuemin='0' aria-valuemax='100' style='width: "+Percentage(goodsdate[i].useNumber, goodsdate[i].allNumber)+"%;'><span>"+Percentage(goodsdate[i].useNumber, goodsdate[i].allNumber)+"%</span></div></div></td><td><img src='"+getRealPath()+"/resources/img/"+goodsdate[i].icon+"' alt='Avatar' class='avatar img-circle' /> /<a>"+goodsdate[i].allNumber+"</a></td><td><span class='label label-success'>ACTIVE</span></td></tr>";
			}
			sta.append(keep);
			$("#goods").html(goodsdate.length);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	$.ajax({
		type: "POST",
		data:{"id":pojo.frimId,"judge":"frims"},
		async:true,
		url: getRealPath()+"/GainDate/downlog.action",
		dataType: "json",
		success: function(data, textStatus) {
			var goodsInfo=eval(data.goodsdate);
			var downLog=eval(data.date);
			var keep='';
			for(var i=0;i<downLog.length;i++){
				keep=keep+"<li><a href='"+getRealPath()+"/resources/Zip/"+downLog[i].fileName+".zip' class='fa fa-download activity-icon'></a><p>生成"+downLog[i].length+"个“ <a href='"+getRealPath()+"/GainDate/skip.action?judge=goods&Id="+goodsInfo[i].goodsId+"'>"+goodsInfo[i].goodsName+"</a>”商品防伪二维码 <span class='timestamp'>"+getLocalTime(downLog[i].time)+"</span></p></li>"
			}
			$(".activity-timeline").empty().append(keep);
			$("#downLog").html(downLog.length);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	loadDate();
}
function loadDate(){
	
	var pojoDate=eval(pojo);
	//frim info load
	//begin children()
		var fe= $(".profile-main");
		fe.find("h3").html(pojoDate.frimName);
		fe.find("span").html(userName);
		$.ajax({
		   	url:getRealPath()+"/resources/img/"+pojoDate.icon+"",
		   	type:'HEAD',
		   	error: function() {
		       //file not exists
		   	},
		   	success: function() {
		       //file exists
		     	  $(".profile-main").find("img").attr("src",getRealPath()+"/resources/img/"+pojoDate.icon+"");
			
		   	}
			});
		fe= $(".profile-stat").find("text");
		fe.each(function(index,t){
			if(index==0) $(this).html(pojo.number);
			else if(index==1) $(this).html(pojo.allNumber);
			else $(this).html(pojo.useNumber);
		})
		$(".list-unstyled").find("span").each(function(index,t){
			if(index==0) $(this).html(getLocalTime(pojo.time));
			else if(index==1) $(this).html(pojo.email);
			else $(this).find("a").html(pojo.web).attr("href","https://"+pojo.web);
		})
		$(".profile-info").find("p").html(pojo.introduction);
			
			
	
}
function loadFrims(){
	
	$.ajax({
		type: "POST",
		data:{"judge":judge},
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
function opening(type){
	
	layer.open({
        type: 2,
        btn: ['确定', '取消'],
        scrollbar:false,
        title: '添加',
        shadeClose: true,
        area: ['380px', '88%'],
        content: getRealPath()+'/inputTable.jsp' ,//iframe的url
        success:function(layero, index){
        	if(type=="frims"){
        		var body=layer.getChildFrame('.row', index);
        			var data=pojo;
        			body.find("#name").find("input").val(data.frimName);
        			body.find("#email").find("input").val(data.email);
            		body.find("#web").find("input").val(data.web);
        			body.find("#wxid").find("input").val(data.wx);
        			body.find("#id").val(data.frimId);
        			body.find("#text").val(data.introduction);
        			body.find("select").attr("style","visibility: hidden");
        			body.find("img").attr("src",getRealPath()+"/resources/img/"+data.icon+"");
        		
        	}else{
        		loadFrims();
        		var body=layer.getChildFrame('.row', index);
        		body.find("#email").attr("style","display: none");
        		body.find("#web").attr("style","display: none");
        		body.find("#wxid").attr("style","display: none");
        		body.find("select").attr("style","visibility: hidden");
        		body.find(".1").remove();
        
        		
        	}
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
			var select=pojo.frimId;
			var fileName;
			var formData= new FormData(body.find("#afd")[0]);
       	 	$.ajax({
                async: false,//要求同步 不是不需看你的需求
                url : getRealPath()+"/GainDate/upload.action",  
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
        	if(type=="frims"){
        		$.ajax({
    				type: "POST",
    				data:{"judge":type,"id":id,"name":name,"img":fileName,"text":text,"select":select,"email":email,"web":web,"wx":wx},
    				async:false,
    				url: getRealPath()+"/GainDate/upDate.action",
    				dataType: "json",
    				success: function(data, textStatus) {
    					loadBegin();
    				},
    				errer:function(){
    					alert("网络错误");
    				}
    			})
        	}else{
        		$.ajax({
    				type: "POST",
    				data:{"judge":type,"name":name,"img":fileName,"text":text,"select":select,"email":email,"web":web,"wx":wx},
    				async:false,
    				url: getRealPath()+"/GainDate/create.action",
    				dataType: "json",
    				success: function(data, textStatus) {
    					loadBegin();
    				},
    				errer:function(){
    					alert("网络错误");
    				}
    			})
        		
        	}
        	
        	
        	layer.close(index);
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
function Percentage(num, number2) { 
	var k=(num/number2)*100;
    return k.toFixed(2);// 小数点后两位百分比
}
$(document).ready(function() {
	loadBegin();
		
})


