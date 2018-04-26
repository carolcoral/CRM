//angular 分页展示数据
var myresource = angular.module('resource', []);
myresource.controller('resourceCtrl',function($scope,$http){
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
	userid: userid,
	deptid:deptid,
	roleid:roleid	
}
load(firstload,'../resource/queryResource.do',1)
function load(data,url,currentPage){
	$('#pagination').sjAjaxPager({
		type: "GET",
		url: url,
		pageSize: 15, 
		currentPage:currentPage,
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
	"<th class='create_time' style='width:130px;'>创建日期</th>"+
	"<th class='state' style='width:70px;'>资源状态</th>"+
	"<th class='isZhuanyi' style='width:70px;'>是否转移</th>"+
	"<th class='createrName' style='width:70px;'>创建者</th>"+
	"<th class='belongName' style='width:70px;'>归属者</th>"+
	"<th class='source' style='width:90px;'>渠道</th>"+
	"<th class='address' style='width:70px;'>地域</th>"+
	"<th class='resourceName' style='width:80px;'>姓名</th>"+
	"<th class='phone' style='width:110px;'>手机</th>"+
	"<th class='phone' style='width:110px;'>固定电话</th>"+
	"<th class='courseName' style='width:90px;'>课程</th>"+
	"<th class='yunYingNote' style='width:250px;'>备注</th>"+
	"<th class='visitRecord' style='width:400px;'>最近回访记录</th>"+
	"<th class='visitCount' style='width:65px;'>回访次数</th>"+
	"<th class='yunYingResourceLevel' style='width:65px;'>资源等级</th>"+
	"<th style='width:65px;'>删除</th>"+
	"</tr>";
//填充数据 
function fillTable(list,currentPage){ 
$.each(list, function(index, array) {
	//循环获取数据
	var resourceId = array.resourceId|| "";
	var userid = array.userid|| "";
    var create_time = array.create_time|| "";
    var state = array.state|| "";
    var isZhuanyi = array.isZhuanyi|| "";
    var stateid =array.state|| "";
    var createrName = array.createrName|| "";
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
    var yunYingResourceLevel = array.yunYingResourceLevel|| "";	
    var visitRecord = array.visitRecord || "";	
    var tel = array.tel || "";
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
    }
    if (0 == isZhuanyi) {
    	isZhuanyi = ' 否';	        
    } else if (1 == isZhuanyi) {
    	isZhuanyi = '是';
    }
    else if (11 == source) {
    	source = '公司资源'
    }
    tableItem ="<tr data-id="+resourceId+">"+
    	"<td><input type='checkbox'class="+resourceId+" data-state='"+stateid+"'/></td>"+
        "<td class='create_time'>"+create_time+"</td>"+
        "<td class='state'>"+state+"</td>"+ 
        "<td class='isZhuanyi'>"+isZhuanyi+"</td>"+
        "<td class='createrName' data-id="+userid+">"+createrName+"</td>"+
        "<td class='belongName'>"+belongName+"</td>"+
        "<td class='source'>"+source+"</td>"+
        "<td class='address'>"+address+"</td>"+
        "<td class='resourceName'>"+resourceName+"</td>"+
        "<td class='phone'>"+phone+"</td>"+
        "<td class='tel'>"+tel+"</td>"+
        "<td class='courseName'>"+courseName+"</td>"+
        "<td class='yunYingNote'>"+yunYingNote+"</td>"+ 
        "<td class='visitRecord'><a onclick='showiframe("+resourceId+",this,"+currentPage+")' target='detailiframe'><span  data-id="+resourceId+"></span>"+visitRecord+"</a></td>"+ 
        "<td class='visitCount'>"+visitCount+"</td>"+ 
        "<td class='yunYingResourceLevel'>"+yunYingResourceLevel+"</td>"+   
        "<td><span class='glyphicon glyphicon-trash trash' aria-hidden='true'></span></td>"+
    "</tr>";
	$("#resourceTable").append(tableItem);
}); 
}
//展示iframe
function showiframe(resourceId,tag,currentPage){
	var dataParams = {
		roleid:roleid,
		userid:userid,
		deptid:deptid,
		resourceId:resourceId
	}
	var params = $.param(dataParams);
	$('#backcurrent').val(currentPage)
	var url = '../resource/details.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
	nowheight = $(document).height();
}
//清空数据 
function clearDate(){ 
	$("#resourceTable").html("");
} 

//查重
function checkRepeat(){
	$('#pagination').html("");
	load(firstload,'../resource/queryRepeatResource.do',1)
}
/*根据条件进行查询资源--筛选*/	
$('#filterDerter').on('click',function(){
	filter();
})	
var filterData;
function filter(){
	$('#pagination').html('');
	taskStartTime = $('#taskStartTime').val();
	taskEndTime = $('#taskEndTime').val();
	filter_state = $('#filter_state option:selected').val();
	filter_isZhuanyi = $('#filter_isZhuanyi option:selected').val();
	zyfilter =  $('#isZhuanyi option:selected').text();
	filter_state = $('#filter_state option:selected').val();
	ztfilter = $('#filter_state option:selected').text();
	filter_createrName = $.trim($('#filter_createrName option:selected').text());
	filter_belongName = $('#filter_belongName option:selected').val();
	gszfilter = $('#filter_belongName option:selected').text();
	filter_source = $('#filter_source option:selected').val();
	qdfilter = $('#filter_source option:selected').text();
	visitRecord= $('#filter_visitRecord').val();
	filter_address = $('#filter_address option:selected').val();	
	filter_resourceName = $('#filter_resourceName').val();
	filter_phone = $('#filter_phone').val();	
	filter_course = $('#filter_course option:selected').val();
	kcfilter = $('#filter_course option:selected').text();
	filter_yunYingNote = $('#filter_yunYingNote').val();	
	secondTime = $('#secondTime').val();
	thirdTime = $('#thirdTime').val();
	filter_visitCount = $('#filter_visitCount option:selected').val();
	filter_yunYingResourceLevel = $('#filter_yunYingResourceLevel option:selected').val();
	filter_xjkhcount = $('#filter_xjkhcount option:selected').val();
	xjkhcount = $('#filter_xjkhcount option:selected').text();
	//展示筛选条件
	if(gszfilter == "请选择归属者"){
		gszfilter = "";
	}
	
	if ("请选择资源状态" == ztfilter) {
		ztfilter = "";	        
    }
	
	if("请选择咨询课程" == kcfilter){
		kcfilter = '';
	}  
	
	if("请选择渠道" == qdfilter){
		qdfilter = "";
	}
	
	if("请选择创建者" == filter_createrName){
		filter_createrName = "";
	}
	$('.cjfilter').html(taskStartTime+"-"+taskEndTime)
	$('.ztfilter').html(ztfilter)
	$('.cjzfilter').html(filter_createrName)
	$('.gszfilter').html(gszfilter)
	$('.qdfilter').html(qdfilter)
	$('.dyfilter').html(filter_address)
	$('.xmfilter').html(filter_resourceName)
	$('.sjfilter').html(filter_phone)
	$('.kcfilter').html(kcfilter)
	$('.bzfilter').html(filter_yunYingNote)
	
	$('.sjfilter').html(secondTime+"-"+thirdTime)
	$('.csfilter').html(filter_visitCount)
	$('.djfilter').html(filter_yunYingResourceLevel)
	$('.zyfilter').html(zyfilter)
	$('.xjkhcount').html()
	filterData = {        
    	userid: userid,
    	deptid:deptid,
    	roleid:roleid,
    	createStartTime:taskStartTime, //创建时间(开始)
		createEndTime:taskEndTime, //创建时间(结束)			
		state: filter_state,
		isZhuanyi:filter_isZhuanyi,
		createrName :filter_createrName,
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
		yunYingResourceLevel :	filter_yunYingResourceLevel,
		visitRecord:visitRecord,
		resourceColor :filter_xjkhcount
    }
	$('#filter').css('display','none');
	
	backfilter(filterData)
}

/*后台返回数据不同*/
function backfilter(filterData){
	$('#pagination').sjAjaxPager({
		type: "GET",
		url: '../resource/queryResourceBySceen.do',
		pageSize: 15, 
		currentPage:1,
		preText: "上一页",
		nextText: "下一页",
		firstText: "首页",
		lastText: "尾页",
		searchParam: filterData,
		beforeSend: function () {
		},
		success: function (data) { 
			var rows = data.rows; 
			$.each(rows.listtoday, function(index, array){
				$('.todaylr').html(array.todaytotal)
				$('.todayzxzx').html(array.todayzxzxcount)
				$('.todaydhzx').html(array.todaydhzxcount)
				$('.todaykczc').html(array.todaykczccount)
				$('.todayzxzc').html(array.todayzxzccount)
				$('.todayapp').html(array.todayappcount)
				$('.todayxsqd').html(array.todayxsqdcount)
				$('.todayxsqd').html(array.todayjkwzccount)
				$('.todaydk').html(array.todaydkzycount)
				$('.todayjxjy').html(array.todayjxjycount)
			})
			var currentPage = data.currentPage;
			$('#filterTotal').html(data.total);
			//清空数据 
			clearDate(); 
			//插入头部
			$("#resourceTable").append(tableHeader); 
			fillTable(rows.list,currentPage);
		},
		complete: function () {
		}
	});	
	
}
//手机查询
function phonesearch(){
	var phone = $('#mobile').val();
	$('#pagination').html('');
	var phonedata = {        
		deptid:deptid,
    	roleid:roleid,
    	phone:phone,
    	tel:phone
	}
	if(phone=="" || phone == null){
		load(firstload,'../resource/queryResource.do')
	}else {
		phoneFill(phonedata,'../resource/queryResourceBySceen.do')
	}
}
function phoneFill(data,url){
	$.ajax({
		url: url,
		type: 'GET',
		data: data,			
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var rows = dataObj.rows;
			//清空数据 
			clearDate(); 
			//插入头部
		    $("#resourceTable").append(tableHeader); 
		    fillTable(rows.list,1);
		}
	})
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
	var phone = $('#phone').val().trim();
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
	if(yunYingNote==""||yunYingNote==null){
		$('#yunYingNoteError').html("内容不能为空");
		return false;
	}else{
		$('#yunYingNoteError').html("");
	}
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
	if(changeResourceval.length > 0){//保存
		data['resourceId'] = changeResourceid;
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
	var sourceval = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(6).attr('data-id');
	var source=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(6).html();
	var address=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(7).html();
	var resourceName=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(8).html();
	var phone=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(9).html();
	var courseid=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(11).attr('data-id');
	var coursename=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(11).html();
	var yunYingNote = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(12).html();
	var resourceLevel= $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(15).html();	
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
		   userid:deluserid,
		   resourceId:delResourcevals,
		   studentstate: delstudentstate
		},
	   url: '../resource/deleteResources.do',
	   success: function(data) {
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
saleName('#chooseSales');
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
	assignall('../resource/assignResource.do','转消成功')
})
$('#transferRecord').on('click',function(){	
	assignalls('../resource/assignResourceAndRecord.do','转移成功')
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
function checkassignalls(id,btn,show){	
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
 			   
 			 $('#distributeTransfer').css('display','none'); 			 
 			 //返回筛选状态下
 			 backfilter(filterData);
 			 
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
function assignalls(url,txt){
	
    var sales = sale.join(","); 
    var states = state.join(",");
    var belongid = $('#chooseSales option:selected').val();
    if(belongid==""||belongid==null){
    	$('#transferErrors').html("不能为空");
    	return false;
    }else{
    	$('#transferErrors').html('');
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
 			   
 			 $('#distributeTransfers').css('display','none'); 			 
 			 //返回筛选状态下
 			 backfilter(filterData);
 			 
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
	
	if(resourceId == 0){
		if(filterData != null){
			var dataParams = {
	 			roleid:roleid,
				deptid:deptid
			};
			execlurl(filterData)
		}else {
			$.winTip({
				title: "提示~~~",
				msg: "由于资源太多请筛选导出",
				src:"./system/img/tishi.png"
			});
		}
		
	}else {
		dataParams = {
 			roleid:roleid,
			deptid:deptid,
			resourceIds:resourceIds
		};
		execlurl(dataParams)
	}	
};

function execlurl(dataParams){
	var exportExcel = "export_excel";	
	var params = $.param(dataParams);
	
	var url = '../resource/excelExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
}
//单个删除每条
$(document).on('click','.trash',function(){
	parentId = $(this).parent().parent().attr('data-id');
	delstudentstate = $('#resourceTable tr[data-id='+'"'+parentId+'"'+'] td[class^="studentstate"]').text();
	$.ajax({        			
		type: "POST",
		data: {
			userid:userid,
			resourceId:parentId,
			studentstate:delstudentstate
		},
		url: '../resource/deleteResources.do',
		success: function(data) {
			if(data=="success"){				   
				$('#resourceTable tr[data-id='+'"'+parentId+'"'+']').remove();  	
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
})


$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none')
})

//定时检查页面长度
function reinitIframe() {          
    var iframe = $(window.parent.document).find("#iframepage");          
    try {               
           var bHeight =document.body.scrollHeight;               
           var dHeight =document.documentElement.scrollHeight;              
           var height = Math.max(bHeight, dHeight);
           iframe.height(bHeight);
       } catch (ex) { }       
}       
window.setInterval("reinitIframe()", 200);
