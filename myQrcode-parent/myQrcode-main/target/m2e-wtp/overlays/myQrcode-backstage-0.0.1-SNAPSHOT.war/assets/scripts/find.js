function getRealPath(){
  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

  var realPath=localhostPaht+projectName;
  return realPath;
}
var frimTap=["#","公司名字","商品种类","创建时间"];
var goodsTap=["#","商品名字","商品数目","创建时间"];

var downDate;
var keep_downDate;
var judge;
var keep_judge;
function down_load(){
	
	judge=$("#select").find("option:selected").val();
	var key=$(".input-group").find("input").val();
	if("df"!=judge&&key!=''){
		$.ajax({
			type: "POST",
			data:{"judge":judge,"key":key},
			async:true,
			url: getRealPath()+"/GainDate/down_info.action",
			dataType: "json",
			success: function(data, textStatus) {
				if(!data.have) {
					$("#down").attr("class","input-group");
					return ;
				}
				downDate=eval(data.downDate);
				var keep;
				if(judge=="frims"){
					for(var i=0;i<downDate.length;i++){
						if(keep!=null)
						keep=keep+"<li><a onclick='skip("+i+",0)'><i class='lnr lnr-user'></i> <span>"+downDate[i].frimName+"</span></a></li>";
						else keep="<li><a onclick='skip("+i+",0)'><i class='lnr lnr-user'></i> <span>"+downDate[i].frimName+"</span></a></li>";
					}
				}else{
					for(var i=0;i<downDate.length;i++){
						if(keep!=null)
						keep=keep+"<li><a onclick='skip("+i+",0)'><i class='lnr lnr-cart'></i> <span>"+downDate[i].goodsName+"</span></a></li>";
						else keep="<li><a onclick='skip("+i+",0)'><i class='lnr lnr-cart'></i> <span>"+downDate[i].goodsName+"</span></a></li>";
					}
				}
				
				$(".dropdown-menu").empty().append(keep);
				$("#down").attr("class","input-group open");
				
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
}
function find(){
	
	keep_judge=judge;
	$("#down").attr("class","input-group");
		if(judge=="frims"){
			var keep="";
			for(var i=0;i<frimTap.length;i++){
				keep=keep+"<th>"+frimTap[i]+"</th>";
			}
			$("#tr_info").empty().append(keep);
			
		}else{
			var keep="";
			for(var i=0;i<goodsTap.length;i++){
				keep=keep+"<th>"+goodsTap[i]+"</th>";
			}
			$("#tr_info").empty().append(keep);
		}
		var table=$("#data_table").empty();
		if(downDate!=null){
			keep_downDate=downDate;
			if(judge=="frims"){
				for(var i=0;i<keep_downDate.length;i++){
					table.append("<tr onclick='skip("+i+",0)'><td>"+(i+1)+"</td><td>"+keep_downDate[i].frimName+"</td><td>"+keep_downDate[i].allNumber+"</td><td>"+getLocalTime(eval(keep_downDate[i]).time)+"</td></tr>");
				}
			}else{
				for(var i=0;i<keep_downDate.length;i++){
					table.append("<tr onclick='skip("+i+",1)'><td>"+(i+1)+"</td><td>"+keep_downDate[i].goodsName+"</td><td>"+keep_downDate[i].allNumber+"</td><td>"+getLocalTime(eval(keep_downDate[i]).time)+"</td></tr>");
				}
			}
			
		}
	
	return false;
}
function skip(count,type){
	var data;
	var j;
	if(type==0){
		data=downDate[count];
		j=judge;
	}
	else {
		data=keep_downDate[count];
		j=keep_judge;
	}
	var id;
	if(judge=="frims")
		id=data.frimId;
		
	else
		id=data.goodsId;
	window.location.href= getRealPath()+"/GainDate/skip.action?judge="+j+"&Id="+id+"";
}
function getLocalTime(nS) {  
    var date = new Date(nS);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return year+"-"+month+"-"+day;
} 
$(document).ready(function() {
	$(".input-group").find("input").bind("input propertychange",function(){
		down_load();
	});
	$("#select").change(function(){
		down_load();
	})
		
})


