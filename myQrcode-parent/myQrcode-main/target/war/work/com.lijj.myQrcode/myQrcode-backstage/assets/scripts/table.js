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
var type=GetQueryString("judge");
var judge=false;
var datas;
var frims;
var data_count;
var data_current=0;
	function frimsUp(count) {
		
		
		$.ajax({
			type: "POST",
			data:{"number":12,"count":count,"judge":type},
			async:true,
			url: getRealPath()+"/GainDate/frims.action",
			dataType: "json",
			success: function(data, textStatus) { 
					var frimsdate=eval(data.frimsdate);
					var keep=data_current+1;
					data_count=Math.ceil(data.count/12);
					//test info
					$("#data_select").empty();
					for(var i=1;i<=data_count;i++){
						$("#data_select").append("<option value='"+i+"' >"+i+"</option>");
					}
					$("#data_text").html(keep+"/"+data_count);
					$("#data_select").val(keep);
					//thead info
					$("#tr_info").empty();
					var tr_info=eval(data.thead);
					var tr;
					for(var i=0;i<tr_info.length;i++){
						tr=tr+"<th>"+tr_info[i]+"</th>";
					}
					$("#tr_info").append(tr);
					$("#data_table").empty();
					datas= eval(data.date);
					
					var list;
					if(type=="frims")
					for(var i=0;i<datas.length;i++){
						var at=eval(datas[i]);
						list=list+"<tr><td>"+(count*12+i+1)+"</td><td>"+at.frimName+"</td><td>"+at.number+"</td><td>"+getLocalTime(at.time)+"</td></tr>";
					}
					else
						for(var i=0;i<datas.length;i++){
							var at=eval(datas[i]);
							$("#data_table").append("<tr><td>"+(count*12+i+1)+"</td><td>"+at.goodsName+"</td><td>"+frimsdate[i]+"</td><td>"+getLocalTime(at.time)+"</td></tr>");
						}
					$("#data_table").append(list).children().each(function(index,element){
						$(this).attr("onclick","skip("+index+")");
					});
					
					if(judge) operation_up();
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
	function operation(){
		operation_up();
		$("#operation-fluid").append("<div class='panel-footer' id='del'><div class='text-center' ><form><button type='button' class='btn btn-danger ' onclick='del_more()' ><i class='fa fa-trash-o'></i>删除</button> <button type='button' class='btn btn-success ' onclick='opening(-1)' ><i class='fa fa-plus-square'></i>添加</button></form></div></div>");
		$("#operation").html("取消").attr({class:"btn btn-default",onclick:"operation_end()"});
	}
	function operation_begin(){
		judge=true;
		operation();
	}
	function operation_end(){
		judge=false;
		$("#operation").html("操作").attr({class:"btn btn-info",onclick:"operation_begin()"});
		$("#del").remove();
		frimsUp(data_current);
	}
	function up(){	
		if(data_current<data_count-1){
			frimsUp(++data_current);
		}
	}
	function down(){
		if(data_current>0)
			frimsUp(--data_current);
	}
	function del_one(one){
		var a=new Array();
		if(type=="frims"){
			a.push(datas[one].frimId);
		}
		else
			a.push(datas[one].goodsId);
		del(a);
			
	}
	function del_more(){
		var a=new Array();
		$(":checkbox").each(function(index,element){
			
			if($(this).prop('checked'))
			{
				if(type=="frims")
					a.push(datas[index].frimId);
				else
					a.push(datas[index].goodsId);
			}
		})
		del(a);
			
	}
	function del(list){
		
		alert("删除成功");
		$.ajax({
			type: "POST",
			data:{"judge":type,"ids":JSON.stringify(list)},
			async:true,
			url: getRealPath()+"/GainDate/del.action",
			dataType: "json",
			success: function(data, textStatus) {
				frimsUp(data_current);
			},
			errer:function(){
				alert("网络错误");
			}
		})
	}
	function loadFrims(){
		
		$.ajax({
			type: "POST",
			data:{"judge":type},
			async:true,
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
	function skip(count){
		var data=eval(datas)[count];
		var id;
		if(type=="frims")
			id=data.frimId;
		else
			id=data.goodsId;
		window.location.href= getRealPath()+"/GainDate/skip.action?judge="+type+"&Id="+id+"";
	}
	
function opening(len){
		
		layer.open({
            type: 2,
            btn: ['确定', '取消'],
            scrollbar:false,
            title: '添加',
            shadeClose: true,
            area: ['380px', '88%'],
            content: getRealPath()+"/inputTable.jsp" ,//iframe的url
            success:function(layero, index){
            	if(type=="frims"){
            		var body=layer.getChildFrame('.row', index);
            		if(len!=-1){
            			var data=datas[len];
            			body.find("#name").find("input").val(data.frimName);
            			body.find("#email").find("input").val(data.email);
                		body.find("#web").find("input").val(data.web);
                		body.find("#wxid").find("input").val(data.wx);
            			body.find("#text").val(data.introduction);
            			body.find("select").attr("style","visibility: hidden");
            			body.find("#id").val(data.frimId);
            			body.find("img").attr("src",getRealPath()+"/resources/img/"+data.icon+"");
            		}else{
            			body.find("select").attr("style","visibility: hidden");
            		}
            	}else{
            		var body=layer.getChildFrame('.row', index);
            		body.find("#email").attr("style","display: none");
            		body.find("#web").attr("style","display: none");
            		body.find("#wxid").attr("style","display: none");
            		body.find(".1").remove();
            		if(len!=-1){
            			var data=datas[len];
            			body.find("#name").find("input").val(data.goodsName);
            			body.find("img").attr("src",getRealPath()+"/resources/img/"+data.icon+"");
            			body.find("#text").val(data.introduction);
            			body.find("#id").val(data.goodsId);
            			body.find("select").children().val("请选择公司").html("请选择公司");
            			for(var i=0;i<frims.length;i++){
            				var keep=eval(frims[i]);
            				body.find("select").append("<option value='"+keep.frimId+"' >"+keep.frimName+"</option>");
            				
            			}
            			body.find("select").find("[value='"+data.frimId+"']").attr("selected","selected")
            		}else{
            			body.find("select").children().val("请选择公司").html("请选择公司");
            			for(var i=0;i<frims.length;i++){		
            				var keep=eval(frims[i]);
            				body.find("select").append("<option value='"+keep.frimId+"' >"+keep.frimName+"</option>");
            				
            			}
            		}
            	}
          //  	layer.iframeAuto(index);
            },
            yes:function( index,layero){
            	var body=layer.getChildFrame(".row", index);
            	var name=body.find("#name").find("input").val();
    			var email=body.find("#email").find("input").val();
    			var web=body.find("#web").find("input").val();
    			var wx=body.find("#wxid").find("input").val();
    			var text=body.find("#text").val();
    			var id=body.find("#id").val();
    			var select=body.find("select").find("option:selected").val();
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
            	if(len!=-1){
            		$.ajax({
        				type: "POST",
        				data:{"judge":type,"id":id,"name":name,"img":fileName,"text":text,"select":select,"email":email,"web":web,"wx":wx},
        				async:true,
        				url: getRealPath()+"/GainDate/upDate.action",
        				dataType: "json",
        				success: function(data, textStatus) {
        					frimsUp(data_current);
        				},
        				errer:function(){
        					alert("网络错误");
        				}
        			})
            	}else{
            		$.ajax({
        				type: "POST",
        				data:{"judge":type,"name":name,"img":fileName,"text":text,"select":select,"email":email,"web":web,"wx":wx},
        				async:true,
        				url: getRealPath()+"/GainDate/create.action",
        				dataType: "json",
        				success: function(data, textStatus) {
        					frimsUp(data_current);
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
	function operation_up(){
			$("#tr_info").append("<th></th>");
			$("#tr_info").prepend("<th></th>");
			$("#data_table").children().each(function(index,element){
				$(this).append("<div><button type='button' class='btn btn-default' onclick='opening("+index+")'><span class='lnr lnr-pencil'></span></button><button type='button' class='btn btn-danger' onclick='del_one("+index+")' ><i class='fa fa-trash-o'></i></button></div>");
				$(this).prepend("<td><label class='fancy-checkbox'><input type='checkbox'><span></span></label></td>");
				$(this).attr("onclick","");
			});
			
		}
	function getLocalTime(nS) {  
    var date = new Date(nS);
    var year = date.getFullYear();
    var month = date.getMonth()+1;
    var day = date.getDate();
    return year+"-"+month+"-"+day;
} 
$(document).ready(function() {
	frimsUp(data_current);
	if(type=="goods"){
		loadFrims();
	}
	
		
})


