/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
//angular 分页展示数据
var myresource = angular.module('resource', []);
myresource.controller('resourceCtrl',function($scope,$http){
	/*//配置
	var userid = document.getElementById("userid").value;
	var deptid = document.getElementById("deptid").value;
	var roleid = document.getElementById("roleid").value;//角色Id
	parseInt(roleid)
	
	
	$scope.resourceState = function (status) {
		// 资源状态  0未分配 1已分配 2未处理 3已处理
	    var p = "";            	    
	    if (0 == status) {
	        p = '未分配';	        
	    }else if (1 == status) {
	        p = '已分配';
	    }else if (2 == status) {
	        p = '已处理';
	    }
	    return p;
	}; 
	
	$scope.setColor = function (c) {
		
        var p = "";
        if (0 == c) {
            p = '#f00';
        }else if (1 == status) {
	        p = '#333';
	    }else if (2 == status) {
	        p = '#333';
	    } 
        return {"color": p};
    };
    
	$scope.studentstate = function (a) { 
		//学员状态 0新增 1已成交 2已提交 3已到账 4已分配 5已转入 6已通过考试 7已关课 8已退回
	    var red = "";
	    if (a == ""||a==null) {            	       
	    	red = "";
	    } 
	    else if (0 == a) {
	    	red = '新增'
	    } 
	    else if (1 == a) {
	    	red = '已成交'
	    } 
	    else if (2 == a) {
	    	red = '已提交'
	    } 
	    else if (3 == a) {
	    	red = '已到账'
	    } 
	    else if (4 == a) {
	    	red = '已分配'
	    } 
	    else if (5 == a) {
	    	red = '已转入'
	    } 
	    else if (6 == a) {
	    	red = '已通过考试'
	    } 
	    else if (7 == a) {
	    	red = '已关课'
	    }
	    else if (8 == a) {
	    	red = '已退回'
	    } 
	    return red;            	   
	};
	$scope.source = function (r) {
		// 渠道: 0自建  1课程注册  2在线注册  3app下载注册   4电话咨询   5金考网注册用户   6线上渠道  7在线咨询  8大库资源
        var s = "";
        if (0 == r) {
            s = '自建';
        }
        else if (1 == r) {
	    	s = '课程注册'
	    }
        else if (2 == r) {
	    	s = '在线注册'
	    }
        else if (3 == r) {
	    	s = 'app下载注册'
	    }
        else if (4 == r) {
	    	s = '电话咨询'
	    }
        else if (5 == r) {
	    	s = '金考网注册用户'
	    }
        else if (6 == r) {
	    	s = '线上渠道'
	    }
        else if (7 == r) {
	    	s = '在线咨询'
	    }
        else if (8 == r) {
	    	s = '大库资源'
	    }else if (9 == r) {
	    	s = '在线购买'
	    }
        return s;
    };
    
    $scope.count = 0;//总条数
    $scope.pageSize = 15;//每页15条
    //变量
    $scope.currentPage = 1;//当前页
    $scope.pageTotal =0; // 总页数 
    $scope.pages = [];
    var url = "/resource/queryResource.do";
    //初始化第一页
    forPages(url,$scope.currentPage,$scope.pageSize,userid,deptid,function(){});
    //获取数据
    function forPages (url,page,size,userid,deptid,callback){
       $http.get(url+"?currentPage="+page+"&pageSize="+size+"&deptid="+deptid+"&userid="+userid).success(function(Data){
        	$scope.count=Data.total;
        	$scope.list = Data.rows;
           $scope.currentPage = Data.currentPage;
           $scope.pageTotal =Math.ceil($scope.count/$scope.pageSize);
           reloadPno();
           callback();
       });
   }
    //查重
	$scope.checkRepeat = function(){
		$scope.show=true;
		forPages('/resource/queryRepeatResource.do',$scope.currentPage,$scope.pageSize,userid,deptid,function(){});
	}
	//查重加载某一页
	$scope.repeatloadPage = function(page){ 
		$scope.show=true;
    	forPages('/resource/queryRepeatResource.do',page,$scope.pageSize,userid,deptid,function(){});
    };
    //查重首页
    $scope.repeatfirstPage = function(){
    	forPages('/resource/queryRepeatResource.do',$scope.currentPage,$scope.pageSize,userid,deptid,function(){});
    }
    //查重尾页
    $scope.repeatlastPage = function(){
        
        forPages('/resource/queryRepeatResource.do',$scope.pageTotal,$scope.pageSize,userid,deptid,function(){});
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
    	forPages('/resource/queryResource.do',page,$scope.pageSize,userid,deptid,function(){});
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
	 	
	};	*/
	//查询所有课程
	$http.get("../course/queryCourse.do").success(function(Data){
    	$scope.course = Data.rows;
    });	
	//查询资源管理头部统计信息
	$http.get("../resource/queryResourceCount.do?deptid="+deptid+"&roleid="+roleid+"&userid="+userid).success(function(Data){
    	
		 angular.forEach(Data, function(array){
				angular.forEach(array.list, function(arr){					
					$scope.resourceList=arr;//总资源量				
					
				});
				angular.forEach(array.listtoday, function(arr){					
					$scope.resourceListtoday=arr;//今日资源量				
					
				});
		});
		
    });	
	/*查询所有的创建者*/	
	$http.get("../resource/queryAllCreatePerson.do").success(function(Data){
    	$scope.createPerson = Data.rows;
    });
	
});
/* 初始化时间插件*/
$('#taskStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#taskEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
$('#secondTime').datetimepicker({
	lang: 'ch',
	 format:"Y-m-d H:m:i",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#thirdTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i",      //格式化日期
	validateOnBlur:false  //删除选中的时间
});	
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});


var userid = document.getElementById("userid").value;
var deptid = document.getElementById("deptid").value;
var roleid = document.getElementById("roleid").value;//角色Id
//数据分页
var firstload = {        
	userid:userid,
	deptid:'30',
	roleid:'51'
}
load('../resource/queryResource.do',firstload,1)
function load(url,data,currentPage){
	$('#pagination').sjAjaxPager({
		type: "GET",
		url: url,
		pageSize: 15, 
		currentPage: currentPage,
		preText: "上一页",
		nextText: "下一页",
		firstText: "首页",
		lastText: "尾页",
		searchParam: data,
		beforeSend: function () {
		},
		success: function (data) {    	 
			var list = data.rows; 
			var currentPage = data.currentPage;
			$('#filterTotal').html(data.total);
			//清空数据 
			clearDate(); 
			//插入头部
			$("#resourceTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});		
}
var tableHeader = "<tr class='henglan'>"+
	"<td style='width:40px;'><input type='checkbox' id='ck_all' class='all'/></td>"+
	"<th class='create_time' style='width:110px;'>创建日期</th>"+
	"<th class='state' style='width:70px;'>资源状态</th>"+
	"<th class='belongName' style='width:70px;'>归属者</th>"+
	"<th class='source' style='width:90px;'>渠道</th>"+
	"<th class='address' style='width:70px;'>地域</th>"+
	"<th class='resourceName' style='width:80px;'>姓名</th>"+
	"<th class='phone' style='width:110px;'>手机</th>"+
	"<th class='courseName' style='width:90px;'>课程</th>"+
	"<th class='yunYingNote' style='width:250px;'>备注</th>"+
	"<th class='visitRecord' style='width:400px;'>最近回访记录</th>"+
	"<th class='visitCount' style='width:65px;'>回访次数</th>"+
	"<th class='resourceLevel' style='width:65px;'>资源等级</th>"+
	"</tr>";
//填充数据 
function fillTable(list,currentPage){ 
$.each(list, function(index, array) {
	//循环获取数据
	var resourceId = array.resourceId|| "";
	var userid = array.userid|| "";
    var create_time = array.create_time|| "";
    var state = array.state|| "";
    var stateid =array.state|| "";
    var belongName = array.belongName|| "";
    var source = array.source|| "";
    var address = array.address|| "";
    var resourceName = array.resourceName|| "";
    var phone = array.phone|| "";
    var courseName = array.courseName|| "";
    var yunYingNote = array.yunYingNote|| "";
    var firstVisitTime = array.firstVisitTime|| "";
    var nextVisitTime = array.nextVisitTime|| "";
    var visitCount = array.visitCount|| "";
    var resourceLevel = array.resourceLevel|| "";	
    var visitRecord = array.visitRecord || "";	
    if (0 == state) {
    	state = '<font color="red">未分配</font>';	        
    } else if (1 == state) {
    	state = '已分配';
    }
    else if (2 == state) {
    	state = '已处理';
    }    
    
    if (0 == source) {
    	source = '自建';
    }
    else if (1 == source) {
    	source = '课程注册'
    }
    else if (2 == source) {
    	source = '在线注册'
    }
    else if (3 == source) {
    	source = 'app下载注册'
    }
    else if (4 == source) {
    	source = '电话咨询'
    }
    else if (5 == source) {
    	source = '金考网注册用户'
    }
    else if (6 == source) {
    	source = '线上渠道'
    }
    else if (7 == source) {
    	source = '在线咨询'
    }
    else if (8 == source) {
    	source = '大库资源'
    }
    else if (9 == source) {
    	source = '在线购买'
    }
    else if (10 == source) {
    	source = '继续教育'
    } else if (10 == source) {
    	source = '继续教育'
    }
    else if (11 == source) {
    	source = '公司资源'
    }
    tableItem ="<tr data-id="+resourceId+">"+
    	"<td><input type='checkbox'class="+resourceId+" data-state='"+stateid+"'/></td>"+
        "<td class='create_time'>"+create_time+"</td>"+
        "<td class='state'>"+state+"</td>"+                
        "<td class='belongName'>"+belongName+"</td>"+
        "<td class='source'>"+source+"</td>"+
        "<td class='address'>"+address+"</td>"+
        "<td class='resourceName'>"+resourceName+"</td>"+
        "<td class='phone'>"+phone+"</td>"+
        "<td class='courseName'>"+courseName+"</td>"+
        "<td class='yunYingNote'>"+yunYingNote+"</td>"+ 
        "<td class='visitRecord'>"+visitRecord+"</td>"+ 
        "<td class='visitCount'>"+visitCount+"</td>"+ 
        "<td class='resourceLevel'>"+resourceLevel+"</td>"+        
    "</tr>";
	$("#resourceTable").append(tableItem);
}); 
}
//清空数据 
function clearDate(){ 
$("#resourceTable").html("");
} 
//查重
function checkRepeat(){
	$('#pagination').html('');
	var repeatdata = {
		userid: userid,
    	deptid:deptid,
    	roleid:roleid
	}
	//load('/resource/queryRepeatCompanyResource.do',repeatdata,1)
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: '../resource/queryRepeatCompanyResource.do',
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	userid: userid,
	    	deptid:deptid,
	    	roleid:roleid
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {    	 
	    	var list = data.rows; 
	    	var currentPage = data.currentPage;
	    	//清空数据 
		   clearDate(); 
	       //插入头部
	       $("#resourceTable").append(tableHeader); 
	       fillTable(list,currentPage);
	    },
	    complete: function () {
	    }
	});		
}
/*根据条件进行查询资源--筛选*/	
$('#filterDerter').on('click',function(){
	filter();
})	
function filter(){
	taskStartTime = $('#taskStartTime').val();
	taskEndTime = $('#taskEndTime').val();
	filter_state = $('#filter_state option:selected').val();
	filter_createrName = $('#filter_createrName option:selected').val();
	filter_belongName = $('#filter_belongName option:selected').val();
	filter_source = $('#filter_source option:selected').val();	
	filter_address = $('#filter_address option:selected').val();	
	filter_resourceName = $('#filter_resourceName').val();
	filter_phone = $('#filter_phone').val();	
	filter_course = $('#filter_course option:selected').val();
	filter_yunYingNote = $('#filter_yunYingNote').val();	
	secondTime = $('#secondTime').val();
	thirdTime = $('#thirdTime').val();
	filter_visitCount = $('#filter_visitCount option:selected').val();
	filter_resourceLevel = $('#filter_resourceLevel option:selected').val();
	filter_xjkhcount = $('#filter_xjkhcount option:selected').val();
	taskStartTime = taskStartTime ||null;
	taskEndTime = taskEndTime ||null;			
	filter_phone = filter_phone	||null;
	filter_yunYingNote = filter_yunYingNote	||null;
	secondTime = secondTime	||null;
	thirdTime = thirdTime ||null;
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: '../resource/queryResourceBySceen.do',
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	userid: userid,
	    	deptid:deptid,
	    	roleid:roleid,
	    	createStartTime:taskStartTime, //创建时间(开始)
			createEndTime:taskEndTime, //创建时间(结束)			
			state: filter_state,			
			userid :filter_createrName,
			belongid: filter_belongName,
			source : filter_source,			
			address : filter_address,			
			resourceName : filter_resourceName,			
			phone:filter_phone,			
			courseid:filter_course,			
			yunYingNote : filter_yunYingNote,			
			visitStartTime:secondTime,//回访时间(开始)			
			visitEndTime:thirdTime,//回访时间(结束)			
			visitCount : filter_visitCount,			
			resourceLevel :	filter_resourceLevel,
			resourceColor :filter_xjkhcount
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {
	    	 //var dataObj=eval("("+data+")");//转换为json对象	 		   
	 		  //var list = dataObj.rows;
	    	
	    	$('#filter').css('display','none');
	    	
	    	var list = data.rows; 
	    	var currentPage = data.currentPage;
	    	//清空数据 
		   clearDate(); 
	       //插入头部
	       $("#resourceTable").append(tableHeader); 
	       fillTable(list,currentPage);
	    },
	    complete: function () {
	    }
	});		
}
//手机查询
function phonesearch(){
	var phone = $('#mobile').val();
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: '../resource/queryResourceBySceen.do',
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	deptid:deptid,
	    	roleid:roleid,
	    	phone:phone
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {    	 
	    	var list = data.rows; 
	    	var currentPage = data.currentPage;
	    	//清空数据 
		   clearDate(); 
	       //插入头部
	       $("#resourceTable").append(tableHeader); 
	       fillTable(list,currentPage);
	    },
	    complete: function () {
	    }
	});		
}

//增加资源
function ckPhone(changeResourceid){
	var changeResourceval = [];	
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
			
			var boxid = $(this).attr('class');
			
			changeResourceval.push(boxid);		
	});	
		
	changeResourceid =$('#resourceTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID
	
	var source = $('#source option:selected').val();
	var address = $('#address option:selected').val();
	var resourceName = $('#resourceName').val();
	var phone = $('#phone').val();
	var courseid = $('#course option:selected').val();
	var yunYingNote = $('#yunYingNote').val();
	var tel = $('#tel').val();
	var weixin = $('#weixin').val();
	var qq = $('#qq').val();
	//校验手机号格式是否正确
    var reg= /^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(14[0-9]))[0-9]{8}$/;
    
	if(resourceName==""||resourceName==null){
		$('#resourceNameError').html("内容不能为空");
		return false;
	}else{
		$('#resourceNameError').html("");
	}
	if(phone==""||phone==null){
		$('#phoneError').html("内容不能为空");
		return false;
	}else if(!reg.test(phone)){
		$("#phoneError").html("手机号格式有误")
		return false;
	}else{
		$('#phoneError').html("");
	}
	if(yunYingNote==""||yunYingNote==null){
		$('#yunYingNoteError').html("内容不能为空");
		return false;
	}else{
		$('#yunYingNoteError').html("");
	}
	if(changeResourceval.length<0){//保存
		var data = {
				userid:userid,
				deptid:deptid,
				source:source,				
				address:address,
				resourceName:resourceName,
				phone:phone,
				courseid:courseid,
				yunYingNote:yunYingNote,
				tel:tel,
				weixin: weixin,
				qq:qq
			}
	}else{
		data = {//修改
				userid:userid,
				deptid:deptid,
				source:source,
				resourceId:changeResourceid,
				address:address,
				resourceName:resourceName,
				phone:phone,
				courseid:courseid,
				yunYingNote:yunYingNote
			}
	}
	
	$.ajax({
		url: '../resource/addResource.do',
		type: 'POST',
		data: data,			
		success: function(data) {			
		   if(data=="success"){				   
			   window.location.reload();					
		   }else if(data=="0"){
			   $.winTip({
					title: "提示~~~",
					msg: "参数为空",
					src:"./system/img/tishi.png"
				});
		   }else if(data=="5"){
			   $.winTip({
					title: "提示~~~",
					msg: "用户已经存在",
					src:"./system/img/tishi.png"
				});
		   }else if(data=="8"){
			   $.winTip({
					title: "提示~~~",
					msg: "当前用户没有操作权限",
					src:"./system/img/tishi.png"
				});
		   } else if(data=="12"){
			   $.winTip({
					title: "提示~~~",
					msg: "已成交之后的学员，不能进行资源修改",
					src:"./system/img/tishi.png"
				});
		   }
	    }
	})
}
//修改资源
var changeResourceval = [];
function changeResource(){

changeResourceval = [];//清空上次的修改条数	
$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		changeResourceval.push(boxid);		
});	
	
changeResourceid =$('#resourceTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID	
	
	if(changeResourceval.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(changeResourceval.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {
		$('#changetel').css('display','none')
		$('#changeweixin').css('display','none')
		$('#changeqq').css('display','none')
		changeText(changeResourceid);
		
	}
}
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
	var sourceval = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).attr('data-id');
	var source=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	var address=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).html();
	var resourceName=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(6).html();
	var phone=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(7).html();
	var courseid=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(8).attr('data-id');
	var coursename=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(8).html();
	var yunYingNote = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(9).html();
	var resourceLevel= $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(10).html();	
	$('#changeTit').html("资源管理_修改")
	$('#addresource').show();
	
	$('#source option:selected').val(sourceval);
	$('#source option:selected').html(source);
	$('#address option:selected').html(address);
	$('#address option:selected').val(address);
	$('#resourceName').val(resourceName);
	$('#phone').val(phone);
	$('#course option:selected').val(courseid);
	$('#course option:selected').html(coursename);
	$('#yunYingNote').val(yunYingNote);
	$('#resourceLevel option:selected').val(resourceLevel);
	$('#resourceLevel option:selected').html(resourceLevel);
	var temp = '';  //去除重复元素
	$('#resourceLevel option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
	$('#source option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
	$('#address option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
	$('#course option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
	
	
};
//导入Excel		
function importExcel(){
	$('#importExcel').html("正在导入· · ·");
	$('#importExcel').removeAttr("onclick");
	$.ajax({
		url: '../resource/excelImport.do',
		type: 'POST',
		cache: false,
		data: new FormData($('#uploadForm')[0]),
		processData: false,
		contentType: false,
		success: function(data) {						
			if(data=="success"){				
				window.location.reload();
			}else if(data=="9"){
				$.winTip({
					title: "提示~~~",
					msg: "导入文件的内容为空！",
					src:"./system/img/tishi.png"
				});
				window.location.reload();
			}else{
				alert("模板有误！");
				window.location.reload();
			}
			
		}
	})
}
//获取导入的文件名
$('#fileinput-input').change(function(event) {
    var path = $('#fileinput-input').val();
    var pos1 = path.lastIndexOf('/');
    var pos2 = path.lastIndexOf('\\');
    var pos = Math.max(pos1, pos2)
    if( pos<0 ){
        $('#itemnum').text( path);                    
    }
    else {                    
        $('#itemnum').text(path.substring(pos+1));   
    }
});
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
		
	}
};

//删除资源
var delResourceval = [];
var delstudentstate = null;
var deluserid = null;
function showdelResource(){
	
	delResourceval = [];//清空上次的修改条数
	
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		delResourceval.push(boxid);
		
	});	
	
	for(i=0; i<delResourceval.length; i++){
		
		delstudentstate = $('#resourceTable tr[data-id='+'"'+delResourceval[i]+'"'+'] td[class^="studentstate"]').text();
		
		deluserid = $('#resourceTable tr[data-id='+'"'+delResourceval[i]+'"'+'] td[class^="createrName"]').attr('data-id');
		
	}	
	if (delstudentstate == ""||delstudentstate==null) {            	       
		delstudentstate = "";
    } 
    else if ( delstudentstate== "0") {
    	delstudentstate = '新增'
    } 
    else if (delstudentstate== "1") {
    	delstudentstate = '已成交'
    } 
    else if (delstudentstate== "2") {
    	delstudentstate = '已提交'
    } 
    else if (delstudentstate== "3") {
    	delstudentstate = '已到账'
    } 
    else if (delstudentstate== "4") {
    	delstudentstate = '已分配'
    } 
    else if (delstudentstate== "5") {
    	delstudentstate = '已转入'
    } 
    else if (delstudentstate== "6") {
    	delstudentstate = '已通过考试'
    } 
    else if (delstudentstate== "7") {
    	delstudentstate = '已关课'
    }
    else if (delstudentstate== "8") {
    	delstudentstate = '已退回'
    }
	if(delResourceval.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	} else {
		$('.themisWrap').css('display','block');
	}
}
//确定删除
function delResource() {
	$('.themisWrap').css('display','none');
	var delResourcevals = delResourceval.join(",");
	$.ajax({        			
	   type: "POST",
	   data: {
		   userid:'92',
		   resourceId:delResourcevals,
		   studentstate: delstudentstate
		},
	   url: '../resource/deleteResources.do',
	   success: function(data) {
		   console.log(data)
		   if(data=="success"){				   
			   window.location.reload();		
		   }else if(data=="0"){
			   $.winTip({
					title: "提示~~~",
					msg: "参数为空",
					src:"./system/img/tishi.png"
				});
		   }else if(data=="7"){
			   $.winTip({
					title: "提示~~~",
					msg: "已成交资源不能删除",
					src:"./system/img/tishi.png"
				});
		   }
		   else if(data=="6"){
			   $.winTip({
					title: "提示~~~",
					msg: "当前登录用户不是数据创建人",
					src:"./system/img/tishi.png"
				});
		   }
		   else {
			 
		   }
		}
	});		
};
/*获取销售姓名*/
saleName('#filter_belongName');
saleName('#chooseSale');	
function saleName(saleId){
	$.ajax({
		type: "GET",
		url: '../resource/queryAllXiaoShou.do',
		cache: false,
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;			
			$.each(list, function (index, item) {  
                //循环获取数据					
	            var userid = list[index].userid|| "";  
	            var username = list[index].username|| "";
	            var option = $('<option value="'+userid+'">' + username + '</option>');
	            $(saleId).append(option);           
               
            });           
		}
	})
}
//资源分配销售和转移
$('#assignall').on('click',function(){	
	assignall('../resource/assigntoResource.do','分配成功')
})
$('#transfer').on('click',function(){	
	assignall('../resource/assignResource.do','转移成功')
})
var sale = [];
var state = [];
function checkassignall(id,btn,show){	
	sale = [];
	state = [];	
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {		
		var boxid = $(this).attr('class');	
		var stateid = $(this).attr('data-state');	
		sale.push(boxid);	
		state.push(stateid);	
	});
	if(sale.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});		
	}else {
		$(id).css('display','block');
		 $(btn).css('display','none')
		 $(show).css({'display':'block','float': 'left'});
	} 
}
function assignall(url,txt){
	
    var sales = sale.join(","); 
    var states = state.join(",");
    var belongid = $('#chooseSale option:selected').val();
    if(belongid==""||belongid==null){
    	$('#transferError').html("不能为空");
    	return false;
    }else{
    	$('#transferError').html('');
    }
    $.ajax({        			
 	   type: "POST",
 	   data: {
 	       resourceIds:sales,
 		   belongid:belongid,
 		   states:states
 	   },
 	   url: url,
 	   success: function(data) {
 		   if(data=="success"){	
 			   alert(txt)
 			   window.location.reload();				
 		   }else if(data=="0"){
 			   $.winTip({
 					title: "提示~~~",
 					msg: "参数为空",
 					src:"./system/img/tishi.png"
 				});
 		   }else if(data=="4"){
 			   $.winTip({
 					title: "提示~~~",
 					msg: "用户未登录",
 					src:"./system/img/tishi.png"
 				});
 		   }else if(data=="16"){
 			   $.winTip({
					title: "提示~~~",
					msg: "资源中存在已分配的数据，不能进行分配，请重新确认",
					src:"./system/img/tishi.png"
				});
		   }
 		   else {
 			 
 		   }
 		}
 	});
}
/*批量导出资源*/
function exportExcel(){		
	var resourceId = []; //获取选中资源的ID   
	var allresourceId = [];	//获取所以资源的ID   
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		resourceId.push(boxid);
	});	
	resourceIds = resourceId.join(',');
	allresourceIds = allresourceId.join(',');	
	var exportExcel = "export_excel";	
	if(resourceId == 0){		
		$.ajax({  //获取所以资源的ID      			
	 	   type: "GET",
	 	   data: { 
	 		    userid:userid,			 			
	 			roleid:roleid,
				deptid:deptid,
	 	   },
	 	   url: '../resource/queryResource.do',
	 	   success: function(data) {
	 		  var dataObj=eval("("+data+")");//转换为json对象	
	 		  var list = dataObj.rows;
		 	  $.each(list, function(index, array) {
		 			allresourceId.push(array.resourceId);
		      });		 	  
	 		 		 		   
	 		}
	 	});
		var dataParams = {
				resourceIds:allresourceIds,
				userid:userid,			 			
	 			roleid:roleid,
				deptid:deptid
		};
	}else {
		dataParams = {
				resourceIds:resourceIds,
				userid:userid,			 			
	 			roleid:roleid,
				deptid:deptid
		};
	}	
	
	var params = $.param(dataParams);
	
	var url = '../resource/excelExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
};

$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none')
})


















