function getRealPath(){
  var curWwwPath=window.document.location.href;
  var pathName=window.document.location.pathname;
  var pos=curWwwPath.indexOf(pathName);
  var localhostPaht=curWwwPath.substring(0,pos);
  var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);

  var realPath=localhostPaht+projectName;
  return realPath;
}
var login;

function openS(){
	$.ajax({
		type: "POST",
		async:false,
		url:  getRealPath()+"/GainDate/admin.action",
		dataType: "json",
		success: function(data, textStatus) {
			login=eval(data.login);
		},
		errer:function(){
			alert("网络错误");
		}
	})
	layer.open({
        type: 2,
        btn: ['确定', '取消'],
        scrollbar:false,
        title: '修改',
        shadeClose: true,
        area: ['380px', '80%'],
        content:  getRealPath()+"/inputTable.jsp" ,//iframe的url
        success:function(layero, index){
        	
        	var body=layer.getChildFrame('.row', index);
        	body.find("#email").find("input").val(login.email);
        	body.find("#web").find("input").val(login.web);
        	body.find("#wxid").find("input").val(login.wx);
        	body.find("#name").find("i").attr("class","lnr lnr-eye");
        	body.find("#name").find("input").attr("placeholder","password").attr("type","password");
        	body.find("#name").append("<input class='form-control'  type='password' placeholder='password'>");
			body.find("#id").val(login.userName);
			
			body.find("#afd").attr("style","display: none");
			body.find("#text").attr("style","display: none");
        	body.find("select").attr("style","display: none");
        	
        //	layer.iframeAuto(index);
        },
        yes:function(index,layero){
        	var body=layer.getChildFrame(".row", index);
        	var password1;
        	var password2;
        	body.find("#name").find("input").each(function(i,t){
        		if(i==0)
        			password1=$(this).val();
        		else 
        			password2=$(this).val();
        	})
			var email=body.find("#email").find("input").val();
			var web=body.find("#web").find("input").val();
			var wx=body.find("#wxid").find("input").val();
			
			var select=body.find("select").find("option:selected").val();
			var text=body.find("#text").val();
			var id=body.find("#id").val();
			var fileName;
			
        	if(password1==password2) 
        		$.ajax({
    				type: "POST",
    				data:{"password":password1,"email":email,"web":web,"wx":wx},
    				async:false,
    				url:  getRealPath()+"/GainDate/upadmin.action",
    				dataType: "json",
    				success: function(data, textStatus) {
    					
    				},
    				errer:function(){
    					alert("网络错误");
    				}
    			})
    			
    			
        	else alert("密码不一致");	
        	
        	
        	
        	layer.close(index);
        	
        }
        
        
    }); 
} 