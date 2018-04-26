/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
/* 初始化时间插件*/
$('#scoretime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#arrive_time').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
$('#mailTime').datetimepicker({
	lang: 'ch',
	 format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
/*获取销售姓名*/
saleName('#belongName');	
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
/*获取课程*/
queryCourse('#courseid');	
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
/*所有客服人员*/
customName('#filterCustomer');	
customName('#chooseCustomer');	
function customName(Id){
	$.ajax({
		type: "GET",
		url: '../custom/queryAllCustoms.do',
		cache: false,
		success: function(data) {			
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;			
			$.each(list, function (index, item) {  
                //循环获取数据					
	            var userid = list[index].userid|| "";  
	            var username = list[index].username|| "";
	            var option = $('<option value="'+userid+'">' + username + '</option>');
	            $(Id).append(option);
            });   
		}
	})
}
/*根据课程ID，查询课程下的科目*/
subject('#subject')
function subject(Id) {
    $.ajax({        			
	   type: 'GET',
	   url: '../subject/querySubject.do',		   
	   success: function(data) {
		   var dataObj=eval("("+data+")");//转换为json对象
		   var list = dataObj.rows;			   
	        $.each(list, function(index, array) {
	        	//循环获取数据
                var subjectid = array.subjectid|| "";	
                var subjectname = array.subjectname || "";
	        	var checkbox = $('<option value="'+subjectid+'">' + subjectname + '</option>');
	        	$(Id).append(checkbox);
		    });
	   }
	});
}

//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
/*转移只有主管可见*/
if(roleid=="20"||roleid=="22"||roleid=="24"||roleid=="26"){
	$('#zgtransfer').css('display','block')
}
var firstData = {        
	userid: userid,
	deptid:deptid,
	roleid:roleid,
	menuCode:"qihuototal"
}
load(firstData,'../custom/queryTotalStudents.do',1)
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
	    	$('#filterTotal').html(data.total)
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
var tableHeader = "<tr class='henglan'>"+
		"<th style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='all' /></th>"+
		"<th class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 59px;'>序号</th>"+
		"<th class='studentName' style='width: 80px;position: fixed;background: #fff;left: 118px;'>姓名</th>"+
		"<th class='phone' style='width: 120px;position: fixed;background: #fff;left: 197px;'>手机</th>"+
		"<th style='width: 140px;position: fixed;background: #fff;left: 316px;'>身份证</th>"+
		"<th style='width: 550px;padding-left: 465px;'>学员状态</th>"+
		"<th>招生老师</th>"+
		"<th>收款金额</th>"+
		"<th>科目</th>"+
		"<th>毕业院校</th>"+
		"<th>学历</th>"+
		"<th>地区</th>"+   
		"<th>班主任</th>"+
		"<th>分配时间</th>"+
		"<th>固定电话</th>"+
		"<th>邮箱</th>"+
		"<th>单位</th>"+      
		"<th>单位地址</th>"+
		"<th>职务</th>"+
		"<th>民族</th>"+
		"<th>收款日期</th>"+
		"<th>支付方式</th>"+		
	"</tr>";
//清空数据 
//清空数据 
function clearDate(){ 
    $('#AFPallTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
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
	    var customerName = array.customerName|| "";
	    
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
	    	studentstate = '<font color="red">已到账</font>'
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
	        "<td class='phone' style='width: 120px;position: fixed;background: #fff;left: 197px;'>"+phone+"</td>"+
	        "<td style='width: 140px;position: fixed;background: #fff;left: 316px;'>"+idCard+"</td>"+
	        "<td style='width: 550px;padding-left: 465px;'>"+studentstate+"</td>"+	
	        "<td>"+belongName+"</td>"+
	        "<td>"+arrive_money+"</td>"+
	        "<td>"+subjectname+"</td>"+
	        "<td>"+school+"</td>"+
	        "<td>"+education+"</td>"+
	        "<td>"+address+"</td>"+
	        "<td>"+customerName+"</td>"+
	        "<td>"+customer_time+"</td>"+	       
	        "<td>"+tel+"</td>"+
	        "<td>"+email+"</td>"+
	        "<td>"+company+"</td>"+ 
	        "<td>"+companyAddr+"</td>"+
	        "<td>"+position+"</td>"+
	        "<td>"+nation+"</td>"+
	        "<td>"+arrive_time+"</td>"+
	        "<td>"+remitWay+"</td>"+	              
	    "</tr>";
		$("#AFPallTable").append(tableItem);
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
/*查询*/
function qhquery(){
	var search = $('#search').val();
	$('#pagination').html('');
	var phonedata = {        
		userid: userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName:search,
    	idCard:search,
    	phone:search,
    	menuCode:"qihuototal"
	}
	if(search=="" || search == null){
		load(firstData,'../custom/queryTotalStudents.do')
	}else {
		phoneFill (phonedata,'../custom/queryCustomStudentBySceen.do')
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
			clearDate(); 
			//插入头部
		       $("#AFPallTable").append(tableHeader); 
			   fillTable(list,currentPage)
		}
	})
}

/*根据条件进行查询资源--筛选*/
var filterData;
function filter(){	
	$('#pagination').html('');
	var studentName = $('#studentName').val();	
	var phone = $('#phone').val().trim();	
	var source = $('#source').val();
	var courseid = $('#courseid option:selected').val();
	var belongName = $('#belongName option:selected').val();
	var subject = $('#subject option:selected').val();	
	var studentstate = $('#studentstate option:selected').val();
	var studentEvaluate = $('#studentEvaluate option:selected').val();
	var ispass = $('#ispass option:selected').val();
	var address = $('#address option:selected').val();
	var scoretime = $('#scoretime').val();
	var arrive_time = $('#arrive_time').val();
	var customerId = $('#filterCustomer option:selected').val();
	filterData = {
		userid:userid,
		deptid:deptid,
    	roleid:roleid,
    	studentName:studentName,
    	phone:phone,
		source:source,		
		courseid:courseid,
		belongid:belongName,
		subjectid:subject,
		studentEvaluate:studentEvaluate,
		ispass:ispass,
		address:address,
		scoretim:scoretime,
		arrive_time:arrive_time,
		customerId:customerId,
		menuCode:"qihuototal"
    }
	$('#filter').css('display','none')	    
	load(filterData,'../custom/queryCustomStudentBySceen.do',1)
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
			src:"./system/img/tishi.png"
		});		
	}else {
		 $(id).css('display','block');		 
	} 
}
function transfer(){
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
	studentstates = studentstate.join(',')
	var customerId = $('#chooseCustomer option:selected').val();
	$.ajax({
		type: "POST",
		data: {
			userid:userid,
			deptid:deptid,
			roleid:roleid,
			customerId:customerId,
			studentIds:studentIds,
			resourceIds:resourceIds,
			studentstates:studentstates
		},
		url: '../custom/transCustomStudent.do',
		cache: false,
		success: function(data) {			
			if(data=="success"){				   
	 			   alert("转移成功")
	 			   window.location.reload();
	 			   
	 		   }else if(data=="0"){
	 			   $.winTip({
	 					title: "提示~~~",
	 					msg: "参数为空",
	 					src:"./system/img/tishi.png"
	 				});
	 		   }else if(data=="11"){
	 			   $.winTip({
						title: "提示~~~",
						msg: "学员状态存在非已分配状态，不能转移客服",
						src:"./system/img/tishi.png"
					});
			   }
	 		   else {
	 			 
	 		   }  
		}
	})
	
}
/*转入回访表(客服)*/
function huifang(){
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
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
	studentstates = studentstate.join(',')
	if(studentId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});		
	}else {
		$.ajax({
			type: "POST",
			data: {
				userid:userid,
				deptid:deptid,
				roleid:roleid,
				studentIds:studentIds,
				resourceIds:resourceIds,
				studentstates:studentstates
			},
			url: '../custom/transferStudentsToCourse.do',
			cache: false,
			success: function(data) {			
				if(data=="success"){				   
		 			   window.location.reload();
		 			   
		 		   }else if(data=="0"){
		 			   $.winTip({
		 					title: "提示~~~",
		 					msg: "参数为空",
		 					src:"./system/img/tishi.png"
		 				});
		 		   }else if(data=="11"){
		 			   $.winTip({
							title: "提示~~~",
							msg: "学员状态存在非已分配状态，不能转入回访表",
							src:"./system/img/tishi.png"
						});
				   }
		 		   else {
		 			 
		 		   }  
			}
		})	 
	} 
	
}
/*导出*/
function exportExcel(){
	
	var allStudent = [];
	var studentId = [];
	$("#AFPallTable input[type='checkbox']:not(:first):checked").each(function() {
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
		 	   url: '../custom/queryExportCustomStudentBySceen.do',
		 	   success: function(data) {
		 		  var dataObj=eval("("+data+")");//转换为json对象	
		 		  var list = dataObj.rows;
		 		  $.each(list, function(index, array) {	
		 			  allStudent.push(array.studentId);	
		 		  });
		 		}
		 	});	
			allStudents = allStudent.join(',');
			if (allStudent == 0){
				$.winTip({
					title: "提示~~~",
					msg: "当前条件下没有可以导出的数据",
					src:"./system/img/tishi.png"
				});
			}else {
				var dataParams = {
					studentIds:allStudents,
					deptid:deptid,
					menuCode:"qihuototal"
				};
			}
			
		}else {
			$.winTip({
				title: "提示~~~",
				msg: "由于资源太多请筛选导出",
				src:"./system/img/tishi.png"
			});
		}
	}else {
		dataParams = {
				studentIds:studentIds,
			    deptid:deptid,
			    menuCode:"qihuototal"
		};
	}	
	
	var params = $.param(dataParams);
	
	var url = '../custom/StudentExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
};


