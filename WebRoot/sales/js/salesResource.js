/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};

var myresource = angular.module('resource', []);
myresource.controller('resourceCtrl',function($scope,$http){		
	//查询所有课程
	$http.get("../course/queryCourse.do").success(function(Data){
    	$scope.course = Data.rows;
    });	
	//查询资源管理头部统计信息
	$http.get("../resource/queryResourceCount.do?deptid="+deptid+"&userid="+userid+"&roleid="+roleid).success(function(Data){
    	
		 angular.forEach(Data, function(array){
				angular.forEach(array.list, function(arr){					
					$scope.resourceList=arr;//总资源量				
					
				});
				angular.forEach(array.listtoday, function(arr){					
					$scope.resourceListtoday=arr;//今日资源量				
					
				});				
				$scope.listtodayvirecordsize = array.listtodayvirecord;				
		});
		
    });	
	/*查询所有的创建者*/	
	$http.get("../resource/queryAllCreatePerson.do").success(function(Data){
    	$scope.createPerson = Data.rows;
    });
	
});

/*获取销售姓名*/
var deptid = document.getElementById("deptid").value;
saleName('#createrName','../resource/queryAllCreatePerson.do');
saleName('#filter_belongName','../resource/queryXiaoShouByRoleid.do?deptid='+deptid);	
saleName('#chooseSale','../resource/queryXiaoShouByRoleid.do?deptid='+deptid);	
function saleName(saleId,url){
	$.ajax({
		
		type: "GET",
		url: url,
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
/*获取课程*/
queryCourse('#filter_course');	
function queryCourse(tagId){
	
	$.ajax({
		type: "GET",
		url: '../course/queryCourse.do',
		cache: false,
		success: function(data) {			
				var dataObj=eval("("+data+")");//转换为json对象	   		   
			var list = dataObj.rows;			
			$.each(list, function (index, item) {  
                //循环获取数据					
	            var courseid = list[index].courseid|| "";  
	            var courseName = list[index].courseName|| "";
	            var option = $('<option value="'+courseid+'">' + courseName + '</option>');
	            $(tagId).append(option);
            });     
		}
	})
}
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
$('.all').click(function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});

//数据分页
var userid = document.getElementById("userid").value;//用户Id
var roleid = document.getElementById("roleid").value;//角色Id
var nowheight = null;
/*转移和归属人只有主管可见*/
belong();
function belong(){
	if(roleid=="4"||roleid=="6"||roleid=="8"||roleid=="10"||roleid=="12"||roleid=="14"||roleid=="16"||roleid=="18"){
		$('#zgtransfer').css('display','block');
		$('.belongName').css({'display':'block','border-bottom':'none;'});
		$('#gszy').css('display','block');
	}
}

//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
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
var first = {
	userid: userid,
	deptid:deptid,
	roleid:roleid
}

/*$('#chioceitem').on('change',function(){
	var chioceitem = $('#chioceitem option:selected').val();
	firstload(first,'/resource/queryResource.do',chioceitem)
})*/
firstload(first,'../resource/queryResource.do',1)
function firstload(data,url,currentPage){
	$('#filterpagination').sjAjaxPager({
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
	       belong();
	    },
	    complete: function () {
	    }
	});
}
var tableHeader = "<tr class='henglan'>"+
	"<th style='width: 40px;'><input type='checkbox' class='all' /></th>"+
	"<th class='xiaoShouNote' style='width: 40px;'>详情</th>"+
	"<th class='assignTime' style='width: 140px;'>分配时间</th>"+
	"<th class='state' style='width: 65px;'>资源状态</th>"+
	"<th class='isZhuanyi' style='width: 65px;'>是否转移</th>"+
	"<th class='resourceLevel' style='width: 65px;'>资源等级</th>"+
	"<th class='belongName' style='display: none;'>归属者</th>"+
	"<th class='nearVisitTime' style='width:140px;'>最近回访时间</th>"+              
	"<th class='visitCount' style='width: 60px;'>回访次数</th>"+
	"<th class='nextVisitTime' style='width:140px;'>下次回访时间</th>"+    
	"<th class='source' style='width: 110px;'>渠道</th>"+
	"<th class='address' style='width: 45px;'>地域</th>"+
	"<th class='resourceName' style='width: 50px;'>姓名</th>"+
	"<th class='phone' style='width: 80px;'>手机</th>"+
	"<th class='courseName' style='width: 90px;'>课程</th>"+
	"<th class='yunYingNote' style='width: 250px;'>运营备注</th>"+
	"<th class='xiaoShouNote' style='width: 350px;'>销售备注</th>"+
	"<th class='visitRecord' style='width: 300px;'>回访记录</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $("#resourceTable").html("");
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
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var resourceId = array.resourceId|| "";
	    var create_time = array.create_time|| "";
	    var state = array.state|| "";
	    var isZhuanyi = array.isZhuanyi|| "";
	    var resourceLevel = array.resourceLevel|| "";	
	    var belongName = array.belongName || "";
	    var belongid = array.belongid || "";
	    var firstVisitTime = array.firstVisitTime|| "";
	    var source = array.source|| "";
	    var sourceid = array.source|| "";
	    var address = array.address|| "";
	    var resourceName = array.resourceName|| "";
	    var phone = array.phone|| "";
	    var courseName = array.courseName|| "";
	    var courseid = array.courseid|| "";
	    var yunYingNote = array.yunYingNote|| "";
	    var xiaoShouNote = array.xiaoShouNote|| "";
	    var visitRecord = array.visitRecord|| "";
	    var visitCount = array.visitCount|| "";
	    var resourceColor = array.resourceColor|| "";
	    var assignTime = array.assignTime || "";
	    var nextVisitTime = array.nextVisitTime|| "";
	    var nearVisitTime = array.nearVisitTime|| "";
	    if(resourceColor=="" || resourceColor == null){
	    	resourceColor = "none";
	    }else {
	    	resourceColor = resourceColor;
	    }
	    	    
	    if (0 == state) {
	    	state = '未分配';	        
	    } else if (1 == state) {
	    	state = '<font color="red">已分配</font>';
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
	    } else if (10 == source) {
	    	source = '继续教育'
	    }
	    else if (11 == source) {
	    	source = '公司资源'
	    }
	    if (0 == isZhuanyi) {
	    	isZhuanyi = '否';
	    }
	    else if (1 == isZhuanyi) {
	    	isZhuanyi = '<font color="red">是</font>'
	    }
	    tableItem ="<tr data-id="+resourceId+" style='background-color:"+resourceColor+"'>"+
	    	"<td><input type='checkbox' class="+resourceId+" data-level='"+resourceLevel+"'/></td>"+
	    	"<td><a onclick='showiframe("+resourceId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+resourceId+"></span></a></td>"+ 
	    	"<td class='assignTime'>"+assignTime+"</td>"+
	        "<td class='state'>"+state+"</td>"+ 
	        "<td class='isZhuanyi'>"+isZhuanyi+"</td>"+ 
	        "<td class='resourceLevel'>"+resourceLevel+"</td>"+        
	        "<td class='belongName' style='display: none;' data-id="+belongid+">"+belongName+"</td>"+ 
	        "<td class='nearVisitTime'>"+nearVisitTime+"</td>"+       
	        "<td class='visitCount'>"+visitCount+"</td>"+ 
	        "<td class='nextVisitTime'>"+nextVisitTime+"</td>"+ 
	        "<td class='source' data-id="+sourceid+">"+source+"</td>"+
	        "<td class='address'>"+address+"</td>"+
	        "<td class='resourceName'>"+resourceName+"</td>"+
	        "<td class='phone'>"+phone+"</td>"+
	        "<td class='courseName' data-id="+courseid+">"+courseName+"</td>"+
	        "<td class='yunYingNote'>"+yunYingNote+"</td>"+ 
	        "<td class='xiaoShouNote'>"+xiaoShouNote+"</td>"+
	        "<td class='visitRecord'>"+visitRecord+"</td>"+ 
	    "</tr>";
		$("#resourceTable").append(tableItem);
	}); 
}

/*根据条件进行查询资源--筛选*/
//回车筛选
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
     if(e && e.keyCode==13){ // enter 键
    	 filter()
    }
}; 
var filterData;
function filter(){
	$('#filterpagination').html('');
	taskStartTime = $('#taskStartTime').val();
	taskEndTime = $('#taskEndTime').val();
	filter_state = $('#filter_state option:selected').val();
	filter_isZhuanyi = $('#filter_isZhuanyi option:selected').val();
	sxfilter = $('#filter_state option:selected').text();
	filter_source = $('#filter_source option:selected').val();	
	source = $('#filter_source option:selected').text();
	filter_address = $('#filter_address option:selected').val();	
	filter_resourceName = $('#filter_resourceName').val();
	filter_phone = $('#filter_phone').val();	
	filter_course = $('#filter_course option:selected').val();	
	ckfilter = $('#filter_course option:selected').text();	
	secondTime = $('#secondTime').val();
	thirdTime = $('#thirdTime').val();	
	filter_resourceLevel = $('#filter_resourceLevel option:selected').val();
	filter_belongName = $('#filter_belongName option:selected').val();
	belongName = $('#filter_belongName option:selected').text();
	filter_visitCount = $('#filter_visitCount option:selected').val();
	filter_xjkhcount = $('#filter_xjkhcount option:selected').val();
	filter_yunYingNote = $('#filter_yunYingNote').val();
	filter_visitRecord = $('#filter_visitRecord').val();
	$.trim(filter_visitCount)
	
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	
	if(belongName == "请选择归属者"){
		belongName = "";
	}
	
	if ("请选择属性" == sxfilter) {
		sxfilter = "";	        
    }
	
	if("请选择咨询课程" == ckfilter){
		ckfilter = '';
	}  
	
	if("请选择渠道" == source){
		source = "";
	}
	
	$('.cjfilter').html(taskStartTime+"-"+taskEndTime)
	$('.sxfilter').html(sxfilter)
	$('.qdfilter').html(source)
	$('.gszfilter').html(belongName)
	$('.dyfilter').html(filter_address)
	
	$('.xmfilter').html(filter_resourceName)
	$('.sjfilter').html(filter_phone)
	$('.ckfilter').html(ckfilter)
	$('.sjfilter').html(secondTime+"-"+thirdTime)
	$('.djfilter').html(filter_resourceLevel)
	$('.csfilter').html(filter_visitCount)
	filterData = {
    	deptid:deptid,
    	roleid:roleid,
    	createStartTime:taskStartTime,
    	createEndTime: taskEndTime,
    	state:filter_state,
    	isZhuanyi:filter_isZhuanyi,
    	source:filter_source,
    	address:filter_address,	    	
    	resourceName:filter_resourceName,	    	
    	phone:filter_phone,	    	
    	courseid: filter_course,	    	
    	visitStartTime:secondTime,	    	
    	visitEndTime:thirdTime,	    	
    	resourceLevel:filter_resourceLevel,
    	belongid:filter_belongName,
    	visitCount:filter_visitCount,
    	resourceColor:filter_xjkhcount,
    	yunYingNote:filter_yunYingNote,
    	visitRecord:filter_visitRecord
    }
	$('#filter').css('display','none')
	firstload(filterData,'../resource/queryResourceBySceen.do',1)	
}
//头部筛选
function xingjiFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
    	resourceColor:data
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
function levelFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
    	resourceLevel:data
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
function stateFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
    	state:data
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
function sourceFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
    	source:data
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
function weixinFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
    	isWeixin:data
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
//今日资源筛选
var todaydate = new Date();
var todayStarttime = todaydate.getFullYear() + "-" + (todaydate.getMonth() + 1) + "-"+ todaydate.getDate()+ " 00:00:00";
var todayEndtime = todaydate.getFullYear() + "-" + (todaydate.getMonth() + 1) + "-"+ todaydate.getDate()+ " 23:59:59";	
function todayFilter(data){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	filterData = {
		deptid:deptid,
    	roleid:roleid,
		source:data,
		createStartTime:todayStarttime,
    	createEndTime: todayEndtime
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}
//今日回访筛选
function visitFilter(){
	$('#filterpagination').html('');
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata);
	$('#backcurrent').attr('data-visit',backcurrentdata);
	$('#backcurrent').attr('data-todayvisit',backcurrentdata);
	filterData = {
		userid:userid,
		roleid:roleid,
		deptid:deptid
	}
	firstload(filterData,'../resource/nextVisitMessage.do',1)
}
//筛选转移
function filterTransfer(){
	$('#filterpagination').html('');
	filterData = {
    	deptid:deptid,
    	roleid:roleid,
    	isZhuanyi:"1"
	}
	firstload(filterData,'../resource/queryResourceBySceen.do',1)
}

//手机查询
var searchmobile = null;
var phonedata;
function phonesearch(){
	searchmobile = $('#searchmobile').val();
	$('#filterpagination').html('');
	phonedata = {        
		deptid:deptid,
		roleid:roleid,
		studentName:searchmobile,
		idCard:searchmobile,
		phone:searchmobile,
	}
	if(searchmobile =="" || searchmobile == null){
		$('#backcurrent').attr('data-id','')
		firstload(first,'../resource/queryResource.do')//展示所有的数据
	}else {
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
		phoneFill (phonedata,'../resource/queryResourceBySceen.do')
	}
}
function phoneFill(data,url){
	$.ajax({
		url: url,
		type: 'GET',
		data: data,			
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;
			var currentPage = dataObj.currentPage;
			//清空数据 
			clearDate(); 
			//插入头部
			$("#resourceTable").append(tableHeader); 
			fillTable(list,currentPage);
			belong();
		}
	})
}

//增加资源
function ckPhone(tag){	

var changeResourceval = [];	
$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		changeResourceval.push(boxid);		
});	
	
changeResourceid =$('#resourceTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID	
	var createrName = $('#createrName option:selected').val();
	var source = $('#source option:selected').val();
	var address = $('#address option:selected').val();
	var resourceName = $('#resourceName').val();
	var phone = $('#phone').val().trim();
	var courseid = $('#course option:selected').val();
	var resourceLevel = $('#resourceLevel option:selected').val();
	var xiaoShouNote = $('#xiaoShouNote').val();
	var tel =  $('#tel').val();
	
	if(resourceName==""||resourceName==null){
		$('#resourceNameError').html("内容不能为空");
		return false;
	}else{
		$('#resourceNameError').html("");
	}
	if(xiaoShouNote==""||xiaoShouNote==null){
		$('#xiaoShouNoteError').html("内容不能为空");
		return false;
	}else{
		$('#xiaoShouNoteError').html("");
	}
	$(tag).html("正在提交· · ·");
	$(tag).remove('onclick');
	if(createrName == "0"){
		createrName = userid;
	}
	
	if(changeResourceval.length<0){//保存
		var data = {
				deptid:deptid,
				userid:createrName,
				source:source,
				address:address,
				resourceName:resourceName,
				phone:phone,
				courseid:courseid,
				resourceLevel:resourceLevel,
				xiaoShouNote:xiaoShouNote,
				tel:tel
			}
	}else{
		data = {//修改
				deptid:deptid,
				userid:createrName,
				resourceId:changeResourceid,
				source:source,
				address:address,
				resourceName:resourceName,
				phone:phone,
				courseid:courseid,
				resourceLevel:resourceLevel,
				xiaoShouNote:xiaoShouNote,
				tel:tel
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
		   }
		   else {
			 
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
		changeText(changeResourceid);
	}
}
//==============获取循环添加的tr展示修改数据====================
function changeText(objTag){
	var sourceval = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(9).attr('data-id');
	var source=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(9).html();
	var address=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(10).html();
	var resourceName=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(11).html();
	var phone=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(12).html();
	var courseid=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(13).attr('data-id');
	var coursename=$('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(13).html();
	var xiaoShouNote = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(15).html();
	var resourceLevel = $('#resourceTable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html();
	$('.addnewTit').html("资源管理_修改")
	$('#addresource').show();
	
	$('#source option:selected').val(sourceval);
	$('#source option:selected').html(source);
	$('#address option:selected').html(address);
	$('#address option:selected').val(address);
	$('#resourceName').val(resourceName);
	$('#phone').val(phone);
	$('#course option:selected').val(courseid);
	$('#course option:selected').html(coursename);
	$('#resourceLevel option:selected').val($.trim(resourceLevel));
	$('#resourceLevel option:selected').html($.trim(resourceLevel));
	$('#xiaoShouNote').val(xiaoShouNote);
	var temp = '';  //去除重复元素
	
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
//资源转移
var sale = [];
function checkassignall(id){
	sale = [];
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {		
		var boxid = $(this).attr('class');		
		sale.push(boxid);		
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
function assignall(){
	
    sales = sale.join(",");    
    var belongid = $('#chooseSale option:selected').val();
    $.ajax({        			
 	   type: "POST",
 	   data: {
 	       resourceIds:sales,
 		   belongid:belongid
 	   },
 	   url: '../resource/assignResource.do',
 	   success: function(data) {
 		   if(data=="success"){	
 			   //返回筛选状态下
 			  $('#distributeTransfer').css('display','none');
 			  firstload(filterData,'../resource/queryResourceBySceen.do')				
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
 		   }
 		   else {
 			 
 		   }
 		}
 	});
}

/*选择颜色*/
function setWeixinColor(tag,tab,num){
	resourceId = [];
	leveid = [];
	$("#"+tab).find("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');	
		var leve = $(this).attr('data-level');
		
		resourceId.push(boxid);
		leveid.push(leve);
	});
	resourceIds = resourceId.join(',');//获取要修改的信息ID	
	//leveids = leveid.join(',');//获取要资源等级	
//	var colorval = $(tag).css('color');//颜色 
	/*if(num == "1"){
		colorval = "";
	}*/
	if(resourceId.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else {		
		$.ajax({        			
		   type: "GET",
		   cache: false,
		   data: {
			   resourceIds:resourceIds,
			   isWeixin:num
		   },
		   url: '../resource/weixinResource.do',
		   success: function(data){		   
			   	if(data=="success"){
			   		$.winTip({
					title: "提示~~~",
					msg: "成功",
					src:"./system/img/tishi.png"
				    });
			   	}else if(data=="12" || data == "0"){
			   	}
		   } 
		});
	}
	
}

/*选择颜色*/
function setColor(tag,tab,num){
	resourceId = [];
	leveid = [];
	$("#"+tab).find("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');	
		var leve = $(this).attr('data-level');
		
		resourceId.push(boxid);
		leveid.push(leve);
	});
	resourceIds = resourceId.join(',');//获取要修改的信息ID	
	leveids = leveid.join(',');//获取要资源等级	
	var colorval = $(tag).css('color');//颜色 
	if(num == "1"){
		colorval = "";
	}
	if(resourceId.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else {		
		$.ajax({        			
		   type: "GET",
		   cache: false,
		   data: {
			   resourceIds:resourceIds,
			   resourceColor:colorval,
			   resourceLevels:leveids
		   },
		   url: '../resource/resourceColor.do',
		   success: function(data) {		   
			   	if(data=="success"){
			   		
			   		$("#"+tab).find("input[type='checkbox']:not(:first):checked").each(function() {
			   			var boxid = $(this).attr('class');	
			   			if(num == "1"){
			   				$("#"+tab).find('tr[data-id='+'"'+boxid+'"'+']').css('background','none');  
				   		}else {
				   			$("#"+tab).find('tr[data-id='+'"'+boxid+'"'+']').css('background',colorval);  
				   		}
			   		});
			   		          	
			   	}else if(data=="12" || data == "0"){
			   		$.winTip({
						title: "提示~~~",
						msg: "请先将该资源标注为“A级客户”。",
						src:"./system/img/tishi.png"
					});
			   	}
		   }
		});
	}
	
}
var iframe = $(window.parent.document).find("#iframepage");
iframe.height(0);
//点击返回按钮重新请求当前列表页，达到刷新的效果
function showdeiatil(){
	$('#showdeiatil').css('display','none');
	iframe.height(0);
	window.setInterval("reinitIframe()", 200);
	var curret = $('#backcurrent').val();
	var filtercurrent = $('#backcurrent').attr('data-id');
	var visitcurrent = $('#backcurrent').attr('data-visit');
	var todayvisit = $('#backcurrent').attr('data-todayvisit');
	if((curret != "" && curret != null) && (filtercurrent == "" || filtercurrent == null)){ //如果当前页不等于空，就重新加载返回curret的列表数据
		firstload(first,'../resource/queryResource.do')
	}else if((searchmobile != "" && searchmobile != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill (phonedata,'../resource/queryResourceBySceen.do')
	}else if((searchmobile == "" || searchmobile == null) && (filtercurrent != "" || filtercurrent != null) && (todayvisit == "" || todayvisit == null)){//如果是多条件查询的，就重新加载返回curret的列表数据
		firstload(filterData,'../resource/queryResourceBySceen.do')
	}else if((searchmobile == "" || searchmobile == null) && (filtercurrent == "" || filtercurrent == null) && (visitcurrent != "" || visitcurrent != null)){
		firstload(filterData,'../resource/nextVisitMessage.do');
		 $('#backcurrent').attr('data-todayvisit','');
	}
}
//定时检查页面长度

function reinitIframe() {          
    try {               
           var bHeight =document.body.scrollHeight;               
           var dHeight =document.documentElement.scrollHeight;              
           var height = Math.max(bHeight, dHeight);
           iframe.height(bHeight);
       } catch (ex) { }       
}       
window.setInterval("reinitIframe()", 200);