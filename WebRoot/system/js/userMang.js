 //angular 分页展示数据
var myapp = angular.module('app', []);
myapp.controller('listCtrl',function($scope,$http){       
	$scope.sex = function (status) {
	    var p = "";            	    
	    if (0 == status) {
	        p = '男';	        
	    } else if (1 == status) {
	        p = '女';
	    }
	    return p;
	};            	
	$scope.state = function (aa) {            	    
	    var red = "";
	    if (0 == aa) {            	       
	        red = '可用'
	    } else if (1 == aa) {
	    	red = '不可用'
	    } 
	    return red;            	   
	};
	$scope.setColor = function (r) {
        var p = "";
        if (1 == r) {
            p = '#f00';
        } 
        return {"color": p};
    };
	//配置
    $scope.count = 0;//总条数
    $scope.pageSize = 15;//每页15条
    //变量
    $scope.currentPage = 1;//当前页
    $scope.pageTotal =0; // 总页数 
    $scope.pages = [];
    //初始化第一页
    forPages($scope.currentPage,$scope.pageSize,function(){});
    //获取数据
     function forPages (page,size,callback){
        $http.get("../system/userlist.do?currentPage="+page+"&pageSize="+size).success(function(Data){  
        	 		        	             	
         	$scope.count=Data.total;
         	$scope.list = Data.rows;
            $scope.currentPage = Data.currentPage;
            $scope.pageTotal =Math.ceil($scope.count/$scope.pageSize);
            reloadPno();
            callback();           
        }); 
    }
    //首页
    $scope.firstPage = function(){
        $scope.loadPage(1);
    }
    //尾页
    $scope.lastPage = function(){
        $scope.loadPage($scope.pageTotal);
    }
    //加载某一页
    $scope.loadPage = function(page){
    	forPages(page,$scope.pageSize,function(){ });
    };
    //初始化页码  
   	var reloadPno = function(){  
       	$scope.pages=calculateIndexes($scope.currentPage,$scope.pageTotal,10);  
    };  
	//分页算法  
	var calculateIndexes = function (current, length, displayLength) {  
	   var indexes = [];  
	   var start = Math.round(current - displayLength / 2);  
	   var end = Math.round(current + displayLength / 2);  
	    if (start <= 1) {  
	        start = 1;  
	        end = start + displayLength - 1;  
	       if (end >= length - 1) {  
	           end = length - 1;  
	        }  
	    }  
	    if (end >= length - 1) {  
	       end = length;  
	        start = end - displayLength + 1;  
	       if (start <= 1) {  
	           start = 1;  
	        }  
	    }  
	    for (var i = start; i <= end; i++) {  
	        indexes.push(i);  
	    }  
	    return indexes;  
	 		console.info(indexes);
	};
	
	$http.get("../dept/queryDept.do").success(function(Data){
     	$scope.deptname = Data.rows;
    }); 
	
});

/*根据部门ID，查询部门下的所有角色*/
$("#deptid").change(function() {
	var checkDeptname = $('#deptid option:selected').val();
	checkRolename("#roleid",checkDeptname);
});

$("#ch-deptid").change(function() {
	var checkDeptname = $('#ch-deptid option:selected').val();	
	checkRolename("#ch-roleid",checkDeptname);   
});
/*根据部门ID，查询部门下的所有角色*/
function checkRolename(ele,deptid) {
    var clearname = $(ele);
    clearname.html(''); //清空原有的选项    
    $.ajax({        			
		   type: 'GET',
		   data: {
			 deptid:deptid
		   },
		   url: "../role/queryRoleByDeptid.do",		   
		   success: function(data) {			   
			   var dataObj=eval("("+data+")");//转换为json对象
			   var list = dataObj.rows;			   
		        $.each(list, function(index, array) {
		        	//循环获取数据
	                var roleid = array.roleid|| "";	
	                var rolename = array.rolename || "";
		        	var option = '<option value="'+roleid+'">' + rolename + '</option>';
		        	clearname.append(option);
			    });
		   }
	});
}

//增加
function addUser(){
	 var username = $('#username').val();
	 var email = $('#email').val();
	 var phone = $('#phone').val();
	 var mobile = $('#mobile').val();
	 var sex = $('input:radio[name="sex"]:checked').val();
	 var deptid=$('#deptid option:selected').val();
	 var roleid=$('#roleid option:selected').val();
	 var state = $('input:radio[name="state"]:checked').val();
	 var userphoto = $('#userphoto').val();
	 var note = $('#note').val();
	 var deptgroup = $('#deptgroup option:selected').val();
	 	//var flag=true;
		var regEmail = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
		var regPhone = /^1[3|4|5|8|7]\d{9}$/;
		if (username =="" || username== null) {		
			$('#usernameError').html('用户名不能为空！')
			return false;
		}
		if (email =="" || email== null) {		
			$('#emailError').html('邮箱不能为空！')
			return false;
		}else if (!regEmail.test(email)) {		
			$('#emailError').html('邮箱格式不正确！')
			return false;
		}
		if (phone =="" || phone== null) {		
			$('#phoneError').html('手机号码不能为空！')
			return false;
		}else if (!regPhone.test(phone)) {		
			$('#phoneError').html('请输入正确的手机号码！')
			return false;
		}
		
		if (deptid =="" || deptid== "请选择部门") {		
			$('#deptidError').html('请选择部门！')
			return false;
		}
		if (roleid =="" || roleid== "请选择角色") {		
			$('#roleidError').html('请选择角色！')
			 return false;
		}
		if (state =="" || state== null) {		
			$('#stateError').html('请选择状态！')
			return false;
		};
		$.ajax({
			url: '../system/saveOrUpdate.do',
			type: 'POST',
			data: {
				 username:username,
			     email:email,
			     phone:phone,
			     mobile:mobile,
			     sex:sex,
			     deptid:deptid,
			     roleid:roleid,
			     state:state,
			     userphoto:userphoto,
			     note:note,
			     deptgroup:deptgroup
			},			
			success: function(data) {				   
				   if(data=="success"){	
					   window.location.reload();
						
				   }else if(data=="0"){					   
					   $('#usernameError').html('参数为空！');
				   }
				   else if(data=="1"){
					   $('#usernameError').html('用户不存在！');
					   
				   }
				   else if(data=="2"){
					   $('#usernameError').html('密码错误！');
				   }
				   else if(data=="3"){
					   $('#usernameError').html('旧密码不匹配！');
				   }
				   else if(data=="4"){
					   $('#usernameError').html(data);
				   }
				   else if(data=="5"){					  
					   $.winTip({
							title: "提示~~~",
							msg: "用户已经存在！",
							src:"./system/img/tishi.png"
						});					   
				   }else {
					 
				   }
			 }
		}) 	
			 
}


//全选
$('.all').click(function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
var checkboxval = [];
function changeUser(){
	
	checkboxval = [];//清空上次的修改条数
	
	$("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('id');
		checkboxval.push(boxid);
	});
	var dataid =$('input[type="checkbox"]:checked').parent().parent().attr('data-id');
		
	if(checkboxval.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(checkboxval.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changeText(dataid);
	}
	
}


//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
		
	var username=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var email=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	var password=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	var phone=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).html();
	var mobile=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(6).html();
	var sex=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(11).html();
	var deptname=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(8).html();
	var roleid=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(9).html();
	var state=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(13).html();
	var userphoto=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(10).html();	
	var note=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(14).html();
	
	var deptid=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(7).attr('data-id');
	
	
	if(sex=="男"){
		sex=0;
	}else if(sex=="女"){
		sex=1;
	}
	if(state=="可用"){
		state=0;
	}else if(state=="不可用"){
		state=1;
	}
	
	$('#change').show();
	
	$('#ch-username').val(username);
	$('#ch-email').val(email);
	$('#ch-password').val(password);
	$('#ch-phone').val(phone);
	$('#ch-mobile').val(mobile);
	$('input:radio[name="ch-sex"][value='+'"'+sex+'"'+']').attr("checked",true);
	
	$('#ch-deptid option:selected').html(deptname);
	/*$('#ch-roleid option:selected').html(roleid);*/
	checkRolename("#ch-roleid",deptid); 
	
	$('input:radio[name="ch-state"][value='+'"'+state+'"'+']').attr("checked",true);	
	$('#ch-userphoto').val(userphoto);	
	$('#ch-note').val(note);
	
	
};
//修改
function edit(){		
	var dataid =$('input[type="checkbox"]:checked').parent().parent().attr('data-id');
	var username = $('#ch-username').val();
	var email = $('#ch-email').val();
	var phone = $('#ch-phone').val();
	var mobile = $('#ch-mobile').val();
	var sex = $('input:radio[name="ch-sex"]:checked').val();
	var deptid=$('#ch-deptid option:selected').val();
	var roleid=$('#ch-roleid option:selected').val();
	var state = $('input:radio[name="ch-state"]:checked').val();
	var userphoto = $('#ch-userphoto').val();
	var note = $('#ch-note').val();
	
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 userid:dataid,
			 username:username,
		     email:email,
		     phone:phone,
		     mobile:mobile,
		     sex:sex,
		     deptid:deptid,
		     roleid:roleid,
		     state:state,
		     userphoto:userphoto,
		     note:note
		   },
		   url: "../system/saveOrUpdate.do",		   
		   success: function(data) {			   
			   if(data==5){				   
					alert("用户已经存在!") ; 
			   }else {
				 console.log("修改成功") ;
				 window.location.reload();
			   }
		   }
	});
}

var ids = [];	
function show() {		
	$("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		ids.push(boxid);
	});
	
	if(ids.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	} else {
		$('.themisWrap').css('display','block');
	}
};
//确定删除
function del() {
	$('.themisWrap').css('display','none');
	var id = ids.join(",");
	$.ajax({
		type: "POST",
		url: '../system/deleteUser.do',
		data: {
			ids: id
		},
		success: function() {
			window.location.reload();
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})
