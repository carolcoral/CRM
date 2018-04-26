/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
/*选择颜色*/
function setColor(tag,tab){
	colorId = [];
	$("#"+tab).find("input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		colorId.push(boxid);
	});
	colorsid = colorId.join(',');//获取要修改的信息ID	
	var colorval = $(tag).css('background-color');//颜色      
	if(colorId.length == 0) {				
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
			   studentIds:colorsid,
			   studentColor:colorval
		   },
		   url: '../custom/studentColorSign.do',
		   success: function(data) {		   
			   	if(data=="success"){
			   		$("#"+tab).find("input[type='checkbox']:not(:first):checked").each(function() {
			   			var boxid = $(this).attr('class');		
			   			$('#resourceTable tr[data-id='+'"'+boxid+'"'+']').children('td').css('background',colorval);  
			   		});
			   		          	
			   	}	
		   }
		});
	}
	
}
//数据分页
/*var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

$(function(){
	var table = $('.fillTable').attr('id');

	if(table=="jijinallTable"){
		fristfill('/custom/queryTotalStudents.do','jijintotal','jijinallTable');
	}
	else if(table=="yincongallTable"){
		
		fristfill('/custom/queryTotalStudents.do','yincongtotal','yincongallTable');
	} 
	else if(table=="zhongjiallTable"){
		fristfill('/custom/queryTotalStudents.do','zhongjitotal','zhongjiallTable');
	} 
	else if(table=="zhengquanallTable"){
		fristfill('/custom/queryTotalStudents.do','zhengquantotal','zhengquanallTable');
	} 
	else if(table=="qihuoallTable"){
		fristfill('/custom/queryTotalStudents.do','qihuototal','qihuoallTable');
	} 
	else if(table=="kuaijiallTable"){
		fristfill('/custom/queryTotalStudents.do','kuaijitotal','kuaijiallTable');
	} 
	else if(table=="chujiallTable"){
		fristfill('/custom/queryTotalStudents.do','chujitotal','chujiallTable');
	}
})
function fristfill(url,menuCode,tag){
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: url,
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	userid: userid,
	    	deptid:deptid,
	    	roleid:roleid,
	    	menuCode:menuCode
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {
	    	var list = data.rows;
	    	var currentPage = data.currentPage;
	    	//清空数据 
		   clearDate(); 
	       //插入头部
	       $('#'+tag).append(tableHeader); 
		   fillTable(list,currentPage,tag)
	    },
	    complete: function () {
	    }
	});
}
var tableHeader = "<tr class='henglan'>"+
		"<th style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='all' /></th>"+
		"<th class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 59px;'>序号</th>"+
		"<th class='studentName' style='width: 80px;position: fixed;background: #fff;left: 118px;'>姓名</th>"+
		"<th class='phone' style='padding-left: 200px;'>手机</th>"+
		"<th>学员状态</th>"+
		"<th>分配时间</th>"+  
		"<th>身份证</th>"+
		"<th>固定电话</th>"+
		"<th>邮箱</th>"+
		"<th>单位</th>"+      
		"<th>地区</th>"+   
		"<th>单位地址</th>"+
		"<th>职务</th>"+
		"<th>毕业院校</th>"+
		"<th>学历</th>"+
		"<th>民族</th>"+
		"<th>招生老师</th>"+
		"<th>收款金额</th>"+
		"<th>收款日期</th>"+
		"<th>支付方式</th>"+		
		"<th>科目</th>"+
	"</tr>";
//清空数据 
//清空数据 
function clearDate(tag){ 
    $('#'+tag).html("");
}  
//填充数据 
function fillTable(list,currentPage,tag){ 
	$.each(list, function(index, array) {		
		//循环获取数据
		var resourceId = array. resourceId|| "";
		var studentId = array. studentId|| "";
	    var studentName = array.studentName|| "";
	    var customer_time = array.customer_time|| "";
	    var phone = array.phone|| "";
	    var studentstate =array.studentstate|| "";
	    var studentstateid =array.studentstate|| "";
	    var idCard = array.idCard|| "";
	    var tel = array.tel|| "";	
	    var email = array.email|| "";
	    var company = array.company|| "";
	    var address = array.address|| "";
	    var companyAddr = array.companyAddr|| "";	
	    var school = array.school|| "";
	    var education = array.education|| "";
	    var nation = array.nation|| "";
	    var position= array.position|| "";
	    var belongName = array.belongName|| "";	
	    var arrive_money = array.arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";	
	    var remitWay = array.remitWay|| "";
	    var subjectname = array.subjectname|| "";
	    
	   
	    if (studentstate == ""||studentstate==null) {            	       
	    	studentstate = "";
	    } 
	    else if (0 == studentstate) {
	    	studentstate = '新增'
	    } 
	    else if (1 == studentstate) {
	    	studentstate = '已成交'
	    } 
	    else if (2 == studentstate) {
	    	studentstate = '已提交'
	    } 
	    else if (3 == studentstate) {
	    	studentstate = '已到账'
	    } 
	    else if (4 == studentstate) {
	    	studentstate = '已分配'
	    } 
	    else if (5 == studentstate) {
	    	studentstate = '已转入'
	    } 
	    else if (6 == studentstate) {
	    	studentstate = '已通过考试'
	    } 
	    else if (7 == studentstate) {
	    	studentstate = '已关课'
	    }                            
	    else if (8 == studentstate) {
	    	studentstate = '已退回'
	    }
	    tableItem ="<tr data-id="+studentId+">"+
		    "<td style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='"+studentId+"'  data-id='"+resourceId+"'  data-state='"+studentstateid+"'/></td>"+
	        "<td class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 59px;'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='studentName' style='width: 80px;position: fixed;background: #fff;left: 118px;'>"+studentName+"</td>"+
	        "<td class='phone' style='padding-left: 200px;'>"+phone+"</td>"+
	        "<td class='studentstate'>"+studentstate+"</td>"+
	        "<td>"+customer_time+"</td>"+
	        "<td>"+idCard+"</td>"+
	        "<td>"+tel+"</td>"+
	        "<td>"+email+"</td>"+
	        "<td>"+company+"</td>"+ 
	        "<td>"+address+"</td>"+
	        "<td>"+companyAddr+"</td>"+
	        "<td>"+position+"</td>"+
	        "<td>"+school+"</td>"+
	        "<td>"+education+"</td>"+
	        "<td>"+nation+"</td>"+
	        "<td>"+belongName+"</td>"+
	        "<td>"+arrive_money+"</td>"+
	        "<td>"+arrive_time+"</td>"+
	        "<td>"+remitWay+"</td>"+	              
	        "<td>"+subjectname+"</td>"+
	    "</tr>";
		$('#'+tag).append(tableItem);
	}); 
}
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("#AFPallTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#AFPallTable input[type='checkbox']").prop("checked", false);
	}
});


查询
function search(){
	var search = $('#search').val();
	$('#pagination').html('');
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: '/custom/queryCustomStudentBySceen.do',
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	userid: userid,
	    	deptid:deptid,
	    	roleid:roleid,
	    	studentName:search,
	    	idCard:search,
	    	phone:search,
	    	menuCode:"AFPtotal"
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {    	
	    	var list = data.rows;
	    	var currentPage = data.currentPage;
	    	//清空数据 
		   clearDate(); 
	       //插入头部
	       $("#AFPallTable").append(tableHeader); 
		   fillTable(list,currentPage)
	    },
	    complete: function () {
	    }
	});
}
根据条件进行查询资源--筛选
function filter(){	
	var studentName = $('#studentName').val();	
	var phone = $('#phone').val();	
	var source = $('#source').val();
	var courseid = $('#courseid  option:selected').val();
	var belongName = $('#belongName  option:selected').val();
	$('#pagination').sjAjaxPager({
		type: "GET",
	    url: '/custom/queryCustomStudentBySceen.do',
	    pageSize: 15, 
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {
	    	userid:userid,
	    	deptid:deptid,
	    	roleid:roleid,
	    	studentName :studentName,
	    	phone:phone,
			source:source,		
			courseid:courseid,
			belongid:belongName,
			menuCode:"AFPtotal"
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {	
	    	$('#filter').css('display','none')	    	
	    	var list = data.rows;
	    	var currentPage = data.currentPage;
	    	//清空数据 
		    clearDate(); 
	        //插入头部
	        $("#AFPallTable").append(tableHeader); 
		    fillTable(list,currentPage)
	    },
	    complete: function () {
	    }
	});		
}
//客服主管转移总表学员
var studentId = [];
var resourceId = [];
var studentstate = [];
function checkassignall(id){
	
	studentId = [];
	resourceId = [];
	studentstate = [];
	
	$("#AFPallTable input[type='checkbox']:not(:first):checked").each(function() {		
		var stuid = $(this).attr('class');	
		var resid = $(this).attr('data-id');	
		var stateid = $(this).attr('data-state');	
		studentId.push(stuid);
		if(resid != "" && resid != null){
			resourceId.push(resid);	
		}			
		studentstate.push(stateid);		
	});
	if(studentId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"../system/img/tishi.png"
		});		
	}else {
		 $(id).css('display','block');		 
	} 
}
function transfer(){
	var studentId = [];	
	var resourceId = [];
	$("#AFPallTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var dataid = $(this).attr('data-id');
		studentId.push(boxid);
		resourceId.push(dataid);
	});
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
	$.ajax({
		type: "POST",
		data: {
			userid:userid,
			deptid:deptid,
			roleid:roleid,
			studentIds:studentIds,
			resourceIds:resourceIds
		},
		url: '/custom/transferStudentsToCourse.do',
		cache: false,
		success: function(data) {			
			if(data=="success"){				   
	 			   alert("转移成功")
	 			   window.location.reload();
	 			   
	 		   }else if(data=="0"){
	 			   $.winTip({
	 					title: "提示~~~",
	 					msg: "参数为空",
	 					src:"../system/img/tishi.png"
	 				});
	 		   }else if(data=="11"){
	 			   $.winTip({
						title: "提示~~~",
						msg: "学员状态存在非已分配状态，不能转移客服",
						src:"../system/img/tishi.png"
					});
			   }
	 		   else {
	 			 
	 		   }  
		}
	})
	
}
转入回访表(客服)
function huifang(){
	studentId = [];
	resourceId = [];	
	$("#AFPallTable input[type='checkbox']:not(:first):checked").each(function() {		
		var stuid = $(this).attr('class');	
		var resid = $(this).attr('data-id');	
		studentId.push(stuid);
		resourceId.push(resid);			
	});
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
	if(studentId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"../system/img/tishi.png"
		});		
	}else {
		$.ajax({
			type: "POST",
			data: {
				userid:userid,
				deptid:deptid,
				roleid:roleid,
				studentIds:studentIds,
				resourceIds:resourceIds
			},
			url: '/custom/transferStudentsToCourse.do',
			cache: false,
			success: function(data) {			
				if(data=="success"){				   
		 			   alert("转移成功")
		 			   window.location.reload();
		 			   
		 		   }else if(data=="0"){
		 			   $.winTip({
		 					title: "提示~~~",
		 					msg: "参数为空",
		 					src:"../system/img/tishi.png"
		 				});
		 		   }else if(data=="11"){
		 			   $.winTip({
							title: "提示~~~",
							msg: "学员状态存在非已分配状态，不能转移客服",
							src:"../system/img/tishi.png"
						});
				   }
		 		   else {
		 			 
		 		   }  
			}
		})	 
	} 
	
}*/
/*导出*/
/*function exportExcel(){
	
	var studentId = [];
	var allStudent = [];	
	$("#AFPallTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});
	
	$("#AFPallTable input[type='checkbox']:not(:first)").each(function() {
		var allid = $(this).attr('class');		
		allStudent.push(allid);
	});
	
	studentIds = studentId.join(',');
	allStudents = allStudent.join(',');
	
	var exportExcel = "export_excel";	
	if(studentId == 0){
		var dataParams = {
				studentIds:allStudents,
			    deptid:deptid,
			    menuCode:"AFPtotal"
		};
	}else {
		dataParams = {
				studentIds:studentIds,
			    deptid:deptid,
			    menuCode:"AFPtotal"
		};
	}	
	
	var params = $.param(dataParams);
	
	var url = '/custom/StudentExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
};*/







