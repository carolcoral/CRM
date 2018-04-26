/* 初始化时间插件*/
$('#taskStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#taskEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
	validateOnBlur:false //删除选中的时间
});	
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
/*展示信息*/
var firstLoad = {
	userid:userid,
	deptid:deptid,
	roleid:roleid
}
load(firstLoad,'../student/queryNetworkEduMoney.do',1)
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
			var total = "<span style='vertical-align:30px;display:block;float: left;'>共"+data.total+" 条</span>";
			//清空数据 
			clearDate(); 
			//插入头部
			$("#networkTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}
var tableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='all' /></th>"+
		"<th>序号</th>"+        
		"<th>学员姓名</th>"+
		"<th>收款金额</th>"+
		"<th>是否提交</th>"+
		"<th>汇款方式</th>"+        
		"<th>收款日期</th>"+
		"<th>代汇款人</th>"+
		"<th>身份证</th>"+
		"<th>手机</th>"+
		"<th>LCW用户名</th>"+
		"<th>LCW密码</th>"+
		"<th>付网络培训款</th>"+
		"<th>地区</th>"+
		"<th>支付日期</th>"+
		"<th>行政备注</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#networkTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array. studentId|| "";
		var resourceId = array. resourceId|| "";
	    var studentName = array.studentName|| "";
	    var arrive_money = array.arrive_money|| "";
	    var iscommitcaiwu = array.iscommitcaiwu|| "";
	    var remitWay = array.remitWay|| "";
	    var arrive_time = array.arrive_time|| "";
	    var remituser = array. remituser|| "";
		var idCard = array. idCard|| "";
	    var phone = array.phone|| "";
	    var LCWname = array.LCWname|| "";
	    var LCWpassword = array.LCWpassword|| "";
	    var netedumoney = array.netedumoney|| "";
	    var address = array.address|| "";
	    var paytime = array.paytime|| "";
	    var xingzhengNote = array.xingzhengNote|| "";
	    
	    if (iscommitcaiwu == "0") {
	    	iscommitcaiwu = '<font color="red">未提交 </font>';	        
	    }else if (iscommitcaiwu == "1") {
	    	iscommitcaiwu = '已提交';
	    }
	    
	    tableItem ="<tr data-id="+studentId+">"+
		    "<td><input type='checkbox' class='"+studentId+"' data='"+resourceId+"'/></td>"+               
	        "<td class='serialnumber'>"+((currentPage-1)*15+(index+1))+"</td>"+        
	        "<td class='studentName'>"+studentName+"</td>"+
	        "<td class='arrive_money'>"+arrive_money+"</td>"+
	        "<td class='iscommitcaiwu'>"+iscommitcaiwu+"</td>"+
	        "<td class='remitWay'>"+remitWay+"</td>"+       
	        "<td class='arrive_time'>"+arrive_time+"</td>"+
	        "<td class='remituser'>"+remituser+"</td>"+
	        "<td class='idCard'>"+idCard+"</td>"+
	        "<td class='phone'>"+phone+"</td>"+
	        "<td class='LCWname'><input type='text'value='"+LCWname+"' class='changeLCWname' disabled='disabled'/></td>"+
	        "<td class='LCWpassword'><input type='text'value='"+LCWpassword+"' class='changeLCWpassword' disabled='disabled'/></td>"+
	        "<td class='netedumoney'>"+netedumoney+"</td>"+
	        "<td class='address'>"+address+"</td>"+
	        "<td class='paytime'>"+paytime+"</td>"+
	        "<td class='xingzhengNote'><input type='text'value='"+xingzhengNote+"' class='changeNote' disabled='disabled'/></td>"+      
	    "</tr>";
		$("#networkTable").append(tableItem);		
	}); 
}
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
/*控制可编辑*/
var a = 0;
function onoff(){
	var tag = $("#onoff"); 
	if(a == 1){		
		tag.html("开");
		
		$('#networkTable input[type="text"]').attr("disabled",true);
		a = 0;
	}else{
		
		tag.html("关"); 
		$('#networkTable  input[type="text"]').attr('disabled',false);
		a = 1;
	} 
}
//提交财务
var studentId = [];
var resourceId = [];
function sendexEcutive(){
	var onoff = $("#onoff").html().trim();
	if(onoff=="关"){
		$.winTip({
			title: "提示~~~",
			msg: "请先将编辑功能关闭",
			src: "./system/img/tishi.png"
		});
		return false;
	}
	studentId = [];
	resourceId = [];
	$("#networkTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var dataId = $(this).attr('data-id');
		studentId.push(boxid);
		resourceId.push(dataId);
	});
	studentIds = studentId.join(',');
	resourceIds = resourceId.join(',');
	
	if(studentId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src: "./system/img/tishi.png"
		});
	} else {
		$.ajax({
			type: "POST",
			url: '../student/commit_caiwu.do',
			data: {
				studentIds: studentIds,
				resourceIds: resourceIds
			},
			success: function(data) {
				if(data=="success"){
					alert("提交成功")
					window.location.reload();				
				}else{
					
				}
				
			}
		});			
	}
}
$('#quxiao').on('click', function() {
	$('.themisWrap').css('display','none');
});
/*保存网络培训费填写内容*/
function editsave(){
	var studentId = [];
	$("#networkTable input[type='checkbox']:not(:first)").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});	
	
	var LCWname = [];	
	$('#networkTable  input[class^="changeLCWname"]').each(function() {
		var txt = $(this).val().trim();		
		LCWname.push(txt);
	});
    var LCWpwd = [];	
	$('#networkTable  input[class^="changeLCWpassword"]').each(function() {
		var txt = $(this).val().trim();		
		LCWpwd.push(txt);
	});
	var note = [];	
	$('#networkTable  input[class^="changeNote"]').each(function() {
		var txt = $(this).val().trim();		
		note.push(txt);
	});	
	var datas=[]; 
    for (i = 0; i < LCWname.length; i++) {
         (function(i){    		
    		 var data = {};    
             data["studentId"] = studentId[i];
             data["LCWname"] = LCWname[i];
             data["LCWpassword"] = LCWpwd[i];  
             data["xingzhengNote"] = note[i];
             datas.push(data);
        })(i);//调用时参数 
    } 
    var tag = $("#onoff").html(); 
    if(tag=="关"){
 	   $.winTip({
 			title: "提示~~~",
 			msg: "请将编辑功能关闭",
 			src:"./system/img/tishi.png"
 		});
 	   return false;
    }else {	
    	$.ajax({
    		type: "POST",
    		url: '../student/saveNetWorkStudents.do',
    		data: {jsonlist:JSON.stringify(datas)},
    		success: function(data) {
    			if(data=="success"){
    				window.location.reload();				
    			}else{
    				
    			}
    			
    		}
    	});
    }
   
}
/*支付时间筛选*/
var filterData;
function networkfilter(){
	$('#pagination').html('');
	var taskStartTime = $('#taskStartTime').val();
	var taskEndTime = $('#taskEndTime').val();
	filterData = {
    	payStartTime:taskStartTime,
    	payEndTime:taskEndTime
    }
	$('#networkfilter').css('display','none');
	load(filterData,'../student/queryNetWorkStudentsBySceen.do',1)
}
/*查询*/
function namesearch(){
	var searchname = $('#searchname').val();
	
	$('#pagination').html('');
	var data = {        
		studentName:searchname,
    	phone:searchname
    }
	if(searchname=="" || searchname == null){
		load(firstLoad,'../student/queryNetworkEduMoney.do')
	}else {
		phoneFill (data,'../student/queryNetWorkStudentsBySceen.do')
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
			$("#networkTable").append(tableHeader); 
			fillTable(list,currentPage);
		}
	})
}
/*网络培训费查重*/
function cheakRepeat(){
	$('#pagination').html('');
	load(firstLoad,'../student/cheakRepeatNetWorkStudents.do',1)
}

/*导出*/
function exportExcel(){	
	var allstudentId = [];	//获取所以资源的ID   
	
	var studentId = []; //获取选中资源的ID   
	$("#networkTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});	
	studentIds = studentId.join(',');
	
	var exportExcel = "export_excel";	
	if(studentId == 0){
		if(filterData != null){
			$.ajax({  //获取所以资源的ID      			
		 	   type: "GET",
		 	   async:false,
		 	   data: filterData,
		 	   url: '../student/queryExportNetWorkStudentsBySceen.do',
		 	   success: function(data) {
		 		  var dataObj=eval("("+data+")");//转换为json对象	
		 		  var list = dataObj.rows;
			 	  $.each(list, function(index, array) {
			 		 allstudentId.push(array.studentId);
			      });		 	  
		 		 		 		   
		 		}
		 	});	
			allstudentIds = allstudentId.join(',');	
			var dataParams = {
				userid:userid,			 			
	 			roleid:roleid,
				deptid:deptid
			};
			execlurl(dataParams,allstudentIds)
		}else {
			$.winTip({
				title: "提示~~~",
				msg: "由于资源太多请筛选导出",
				src:"./system/img/tishi.png"
			});
		}
	}else {
		dataParams = {
			userid:userid,			 			
 			roleid:roleid,
			deptid:deptid
		};
		execlurl(dataParams,studentIds)
	}	
	
};
function execlurl(dataParams,studentIds){
	var params = $.param(dataParams);
	
	var url = '../student/netWorkEduMoneyExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"><input type="text" name="studentIds" value="'+studentIds+'"/></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
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