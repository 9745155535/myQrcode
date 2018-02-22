function getRealPath(){
  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

  var realPath=localhostPaht+projectName;
  return realPath;
}
var compare_current=0;
	var compare_count;
	var downLog_current=0;
	var downLog_count;
	var findList;
	function getLocalTime(nS) {  
	    var d = new Date(nS);
	    
	    return d.Format("yyyy-MM-dd");
	}
	//goods compare data update function
	//---begin---
	function compare_update(count){
		
		$.ajax({
			type: "POST",
			data:{"compare_count":count},
			async:true,
			url: getRealPath()+"/mytest/updata_compare.action",
			dataType: "json",
			success: function(data, textStatus) { 
					var keep=compare_current+1;
					compare_count=Math.ceil(data.count/4);
					
					$("#compare_select").empty();
					for(var i=1;i<=compare_count;i++){
						$("#compare_select").append("<option value='"+i+"' >"+i+"</option>");
					}
					
					$("#compare_text").html(compare_count);
					$("#compare_select").val(keep);
					
					$("#compare_list").empty();
					findList= eval(data.FindData);
					var dataList= eval(data.dataList);
					for(var i=0;i<findList.length;i++){
						var at=eval(dataList[i]);
						
						$("#compare_list").append("<li onclick='skip(findList["+i+"].goodsId)'><p>"+findList[i].goodsName+"<span class='label-percent'>"+two(at.length)+"%</span></p><div class='progress progress-xs'><div class='progress-bar progress-bar-"+at.color+"' role='progressbar' aria-valuenow='"+at.length+"' aria-valuemin='0' aria-valuemax='100' style='width: "+at.length+"%'><span class='sr-only'>"+at.length+"% Complete</span></div></div></li>");
					}
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
	//---end---
	function downLog_update(count){
		
		$.ajax({
			type: "POST",
			data:{"downLog_count":count},
			async:true,
			url: getRealPath()+"/mytest/updata_downLog.action",
			dataType: "json",
			success: function(data, textStatus) {
				var keep=downLog_current+1;
				downLog_count=Math.ceil(data.count/7);
				
				$("#downLog_select").empty();
				for(var i=1;i<=downLog_count;i++){
					$("#downLog_select").append("<option value='"+i+"' >"+i+"</option>");
				}
				
				$("#downLog_text").html(downLog_count);
				$("#downLog_select").val(keep);
				
				$("#table_data").empty();
				var downlogPaging=eval(data.downLogPaging);
				var goodsInfo=eval(data.goodsInfo);
				var keep;
				for(var i=0;i<downlogPaging.length;i++){
					keep=keep+"<tr><td><a href="+getRealPath()+"/GainDate/skip.action?judge=goods&Id="+goodsInfo[i].goodsId+">"+goodsInfo[i].goodsName+"</td><td>"+downlogPaging[i].length+"</a></td><td>"+getLocalTime(downlogPaging[i].time)+"</td><td> <span class='label label-success'><a  style='color: #ffffff' href='"+getRealPath()+"/resources/Zip/"+downlogPaging[i].fileName+".zip"+"'>SUCCESS</a></span> </td></tr>";
					
				}
				$("#table_data").append(keep);
					
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
//compare up and down function
//---begin---
	function up_compare(type){
		if(type=="compare"){
			if(compare_current<compare_count-1)
			compare_update(++compare_current);
		}else{
			if(downLog_current<downLog_count-1)
				downLog_update(++downLog_current);
		}
	}
	function down_compare(type){
		if(type=="compare"){
			if(compare_current>0)
			compare_update(--compare_current);
		}else{
			if(downLog_current>0)
				downLog_update(--downLog_current);
		}
	}
//DownLog up data
	function updata_basicty(){
		$.ajax({
			type: "POST",
			async:true,
			url: getRealPath()+"/mytest/updata_basicty.action",
			dataType: "json",
			success: function(data, textStatus) {  		
					$("#item_lenght").html(toThousands(data.itemLenght));      
					$("#all_lenght").html(toThousands(data.allLenght));
					$("#use_lenght").html(toThousands(data.useLenght));
					$("#use_percent").html(Percentage(data.useLenght, data.allLenght)+"%");
					var CU=$("#CU");
					CU.find(".number").html(toThousands(data.CU1));
					if(data.CU1>=data.CU2)
						CU.find("text").html(Percentage(data.CU1-data.CU2, data.CU2)+"%");
					else{
						CU.find("text").html(Percentage( data.CU2-data.CU1, data.CU2)+"%");
						CU.find("i").attr("class","fa fa-caret-down text-danger")
					}
					
					var UU=$("#UU");
					UU.find(".number").html(toThousands(data.UU1));
					if(data.UU1>=data.UU2)
						UU.find("text").html(Percentage(data.UU1-data.UU2, data.UU2)+"%");
						else{
							UU.find("text").html(Percentage(data.UU2-data.UU1, data.UU2)+"%");
							UU.find("i").attr("class","fa fa-caret-down text-danger")
						}
					
					var Uall=$("#Uall");
					Uall.find(".number").html(toThousands(data.allLenght));
					
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
	//整数添加点
	function toThousands(num) {  
	    var num = (num || 0).toString(), result = '';  
	    while (num.length > 3) {  
	        result = ',' + num.slice(-3) + result;  
	        num = num.slice(0, num.length - 3);  
	    }  
	    if (num) { result = num + result; }  
	    return result;  
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
	function skip(id){
		
		window.location.href=getRealPath()+"/GainDate/skip.action?judge=goods&Id="+id;
	}
	function Percentage(num, number2) { 
		var k=(num/number2)*100;
		
		if(k==0||num==0||number2==0) return " --";
		else
	    return k.toFixed(2);// 小数点后两位百分比
	}
	function two(num) { 
		
		if(num=="NaN") return "0.00";
	    return num.toFixed(2);// 小数点后两位百分比
	}
	//downLog
	function frimInfo_all(){
		$.ajax({
			type: "GET",
			async:true,
			url: getRealPath()+"/ExclDownLog/frimInfo_all.action",
			dataType: "json",
			success: function(data, textStatus) {  		
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
	
$(document).ready(function() {
	
	//test time
	setInterval(function(){
		var date=new Date();
		   var year=date.getFullYear(); //获取当前年份
		   var mon=date.getMonth()+1; //获取当前月份
		   var da=date.getDate(); //获取当前日
		   var day=date.getDay(); //获取当前星期几
		   var h=date.getHours(); //获取小时
		   var m=date.getMinutes(); //获取分钟
		   if(m<=9)m='0'+m;
		   var s=date.getSeconds(); //获取秒
		   if(s<=9)s='0'+s;
		   var d=document.getElementById('Date'); 
		   var currentdate=year+'年'+mon+'月'+da+'日'+' '+h+':'+m+':'+s;
		$("#updateTime").html(currentdate);
	}, 1000);
	
	//updata
	
	updata_basicty();
	setInterval(updata_basicty, 10000);
	
	//---------map--------
	
	
	$.ajax({
			type: "POST",
			async:true,
			url: getRealPath()+"/mytest/updata_map.action",
			dataType: "json",
			success: function(date, textStatus) {
				var data, options;
				data = {
						labels: eval(date.monthList),
						series:[ eval(date.dateList)],
					};

				// line chart
				options = {
					height: "300px",
					showPoint: true,
					axisX: {
						showGrid: false
					},
					lineSmooth: false,
				};

				new Chartist.Line('#visits-trends-charts', data, options);

				// bar chart
				options = {
					height: "300px",
					axisX: {
						showGrid: false
					},
				};
					new Chartist.Line('#visits-trends-chart', data, options);
				
				
				
				
			},
			errer:function(){
				alert("网络错误");
			}
		})
	//---------------------
	
	
			
	
	
	//compare数据更新
	compare_update(compare_current);
	downLog_update(downLog_current);
	
		
})


