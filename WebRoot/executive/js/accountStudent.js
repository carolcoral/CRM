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
$('#deterStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d  H:m:i", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#deterEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d  H:m:i", //格式化日期
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
/*所有客服人员*/
customName('#TransferCustomer','../custom/queryAllCustoms.do');	
customName('#assginCustomer','../custom/queryxzCustoms.do');
customName('#filterCustomer','../custom/queryAllCustoms.do');
function customName(Id,url){
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
	            $(Id).append(option);
            });   
		}
	})
}
/*获取课程*/
queryCourse('#course');	
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

//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});

//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

/*转移只有主管可见*/
if(roleid=="31"){
	$('#zgtransfer').css('display','block')
}
//展示iframe
function showiframe(studentId,tag,currentPage){
	var dataParams = {
		roleid:roleid,
		userid:userid,
		deptid:deptid,
		studentId:studentId
	}
	var params = $.param(dataParams);
	$('#backcurrent').val(currentPage)
	
	var url = '../student/accountDetails.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
}
/*展示信息*/
var firstLoad = {
	userid:userid,
	deptid:deptid,
	roleid:roleid	
}
load(firstLoad,'../student/queryYesStudentMatchinfo.do',1)
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
			var total = "<span style='vertical-align:30px;display:block'>共"+data.total+" 条</span>";
			//清空数据 
			clearDate(); 
			//插入头部
			$("#accountTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}
var tableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='all' /></th>"+
		"<th style='width:10px'>详情</th>"+ 
		"<th>序号</th>"+        
		"<th>分配客服时间</th>"+
		"<th>确认到账时间</th>"+
		"<th>学员状态</th>"+
		"<th>优惠信息</th>"+
		"<th>姓名</th>"+ 
		"<th>身份证</th>"+
		"<th>手机</th>"+
		"<th>班级</th>"+
		"<th>课程名称</th>"+  
		"<th>收款费用</th>"+
		"<th>收款日期</th>"+
		"<th>招生老师</th>"+
		"<th>客服员</th>"+  
		"<th>是否通过</th>"+
		"<th>投资</th>"+
		"<th>保险</th>"+
		"<th>税务</th>"+
		"<th>福利</th>"+
		"<th>综合</th>"+               
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#accountTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array. studentId|| "";
		var resourceId = array. resourceId|| "";
		var courseid = array. courseid || "";
	    var studentName = array.studentName|| "";
	    var idCard = array.idCard|| "";
	    var phone = array.phone|| "";
	    var studentstate =array.studentstate|| "";
	    var studentstateid =array.studentstate|| "";
	    var matchinfo_time =array.matchinfo_time|| "";
	    var courseName = array.courseName || "";
	    var customerName =array.customerName|| "";
	    var company = array.company|| "";
	    var companyAddr = array.companyAddr|| "";
	    var belongName = array. belongName|| "";	    
		var arrive_money = array. arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";	    
	    var ispass = array.ispass|| "";	
	    var customer_time = array.customer_time|| "";	
	    var preferinfo = array.preferinfo|| "";
	    var touzi = array.touzi|| "";  
	    var shuiwu = array.shuiwu|| "";  
	    var fuli = array.fuli|| "";  
	    var baoxian = array.baoxian|| "";  
	    var zonghe = array.zonghe|| "";
	    var banci = array.banci|| "";
	    if(ispass=="0"){
	    	ispass = "未通过";
	    }else if(ispass=="1"){
	    	ispass = "通过";
	    }else if(ispass=="2"){
	    	ispass = "缺考";
	    }
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
	    	studentstate = '<font color="red">已提交</font>'
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
		    "<td><input type='checkbox' class='"+studentId+"' data-id='"+resourceId+"' data-state='"+studentstateid+"' data-coures='"+courseid+"'/></td>"+
		    "<td><a onclick='showiframe("+studentId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+studentId+"></span></a></td>"+ 
            "<td>"+((currentPage-1)*15+(index+1))+"</td>"+
            "<td>"+customer_time+"</td>"+ 
            "<td>"+matchinfo_time+"</td>"+ 
            "<td>"+studentstate+"</td>"+ 
            "<td>"+preferinfo+"</td>"+ 
            "<td>"+studentName+"</td>"+       
            "<td>"+idCard+"</td>"+
            "<td>"+phone+"</td>"+  
            "<td>"+banci+"</td>"+  
            "<td>"+courseName+"</td>"+ 
            "<td>"+arrive_money+"</td>"+
            "<td>"+arrive_time+"</td>"+
            "<td>"+belongName+"</td>"+                
            "<td>"+customerName+"</td>"+ 
            "<td>"+ispass+"</td>"+
            "<td>"+touzi+"</td>"+
            "<td>"+shuiwu+"</td>"+
            "<td>"+fuli+"</td>"+
            "<td>"+baoxian+"</td>"+
            "<td>"+zonghe+"</td>"+
	    "</tr>";
		$("#accountTable").append(tableItem);
	}); 
}
//筛选
var filterData;
function studentfilter(){
	$('#pagination').html('')
	var belongName= $('#belongName option:selected').val();	
	var address= $('#address option:selected').val();
	var banci= $('#banci option:selected').val();
	var courseid= $('#course option:selected').val();
	var arrive_money= $('#arrive_money').val();
	var taskStartTime= $('#taskStartTime').val();
	var taskEndTime= $('#taskEndTime').val();
	var remitWay= $('#remitWay').val();
	var matchinfoStarttime = $('#deterStartTime').val();
	var matchinfoEndtime = $('#deterEndTime').val();
	var studentstate = $('#studentstate option:selected').val();	
	var customerId = $('#filterCustomer option:selected').val();
	
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)			
	
	filterData = {
       userid:userid,
       deptid:deptid,
       roleid:roleid,
       belongid:belongName,
       address:address,
       banci:banci,
       courseid:courseid,
       arrive_money:arrive_money,
       arriveStartTime:taskStartTime,
       arriveEndTime:taskEndTime,	       
       remitWay:remitWay,
       matchinfoStarttime:matchinfoStarttime,
       matchinfoEndtime:matchinfoEndtime,
       studentstate:studentstate,
       customerId:customerId
    	
    }
	$('#studentfilter').css('display','none');
	load(filterData,'../student/queryYesStudentMatchinfoBySceen.do',1)
}
/*查询*/
var searchname = null;
var phonedata;
function fastquery(){
	$('#pagination').html('');
	searchname = $('#searchname').val();
	phonedata = {        
			userid:userid,
			deptid:deptid,
			roleid:roleid,
			studentName:searchname,
			phone:searchname,
			idCard:searchname
	}
	if(searchname=="" || searchname == null){
		$('#backcurrent').attr('data-id','')
		load(firstLoad,'../student/queryYesStudentMatchinfo.do')
	}else{
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
		phoneFill(phonedata,'../student/queryYesStudentMatchinfoBySceen.do')
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
		    $("#accountTable").append(tableHeader); 
		    fillTable(list,currentPage);
		}
	})
}
//分配和转移
var studentId = [];
var resourceId = [];
var studentstate = [];
var datacourseid = [];
function checkassignall(id,btn,show){
	
	studentId = [];
	resourceId = [];
	studentstate = [];
	datacourseid = [];
	
	$("#accountTable input[type='checkbox']:not(:first):checked").each(function() {		
		var stuid = $(this).attr('class');	
		var resid = $(this).attr('data-id');	
		var stateid = $(this).attr('data-state');	
		var couresid = $(this).attr('data-coures');		
		studentId.push(stuid);			
		studentstate.push(stateid);	
		datacourseid.push(couresid);
		if(resid != "" && resid != null){
			resourceId.push(resid);	
		}
	});
	if(studentId.length == 0) {
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
function assignall(){
	
	var studentIds = studentId.join(",");    
    var resourceIds = resourceId.join(",");    
    var studentstates = studentstate.join(",");
    var datacourseids = datacourseid.join(",")
    var customerId = $('#assginCustomer option:selected').val();    
    $.ajax({        			
 	   type: "POST",
 	   data: {
 		  studentIds:studentIds,
 	      resourceIds:resourceIds,
 	      studentstates:studentstates,
 	      customerId:customerId,
 	      courseids: datacourseids
 	   },
 	   url: '../student/assignStudent.do',
 	   success: function(data) {
 		   if(data=="success"){				   
 			   alert("分配成功")
 			   $('#assgincustom').css('display','none');
 			   load(filterData,'../student/queryYesStudentMatchinfoBySceen.do')		   
 		   }else if(data=="0"){
 			   $.winTip({
 					title: "提示~~~",
 					msg: "参数为空",
 					src:"./system/img/tishi.png"
 				});
 		   }else if(data=="11"){
			   $.winTip({
					title: "提示~~~",
					msg: "学员状态存在非已到账状态，不能提交客服",
					src:"./system/img/tishi.png"
				});
		   }else if(data=="17"){
 			   $.winTip({
					title: "提示~~~",
					msg: "存在非同类课程的学员，不能分配客服",
					src:"./system/img/tishi.png"
				});
		   }else if(data=="18"){
			   $.winTip({
					title: "提示~~~",
					msg: "分配的学员课程和分配客服人员职责不匹配",
					src:"./system/img/tishi.png"
				});
		   }
 		   else {
 			 
 		   }
 		}
 	});
}

function transfer(){
	var studentIds = studentId.join(",");    
    var resourceIds = resourceId.join(",");    
    var studentstates = studentstate.join(",");
    
    var customerId = $('#TransferCustomer option:selected').val();    
    $.ajax({        			
 	   type: "POST",
 	   data: {
 		  studentIds:studentIds,
 	      resourceIds:resourceIds,
 	      studentstates:studentstates,
 	      customerId:customerId 	      
 	   },
 	   url: '../student/transferassignStudent.do',
 	   success: function(data) {
 		   if(data=="success"){				   
 			   alert("转移成功")
 			   $('#Transfercustom').css('display','none');
 			   load(filterData,'../student/queryYesStudentMatchinfoBySceen.do')
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
 	});
}
/*导出*/
function exportExcel(){
	var allStudent = [];
	var studentId = [];
		
	$("#accountTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});	
	studentIds = studentId.join(',');
	var exportExcel = "export_excel";	
	if(studentId == 0){
		if(filterData != null){
			$.ajax({  //获取所以筛选以后资源的ID      			
				type: "GET",
				data: filterData,
				async:false,
				url: '../student/queryExportYesStudentMatchinfoBySceen.do',
				success: function(data) {
					var dataObj=eval("("+data+")");//转换为json对象	
					var list = dataObj.rows;
					$.each(list, function(index, array) {
						allStudent.push(array.studentId);
					});		 	  
				}
			});
			allStudents = allStudent.join(',');
			var dataParams = {
				userid:userid,			 			
				roleid:roleid,
				deptid:deptid
			};
			execlurl(dataParams,allStudents)
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
	
	var url = '../student/excelExportStudent.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"><input type="text" name="studentIds" value="'+studentIds+'"/></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
}
var iframe = $(window.parent.document).find("#iframepage");
iframe.height(0);
//子窗口操作返回当前列表页
function showdeiatil(){
	$('#showdeiatil').css('display','none');
	iframe.height(0);
	window.setInterval("reinitIframe()", 200);
	var curret = $('#backcurrent').val();
	var filtercurrent = $('#backcurrent').attr('data-id');
	
	if((curret != "" && curret != null) && (filtercurrent == "" || filtercurrent == null)){ //如果当前页不等于空，就重新加载返回curret的列表数据
		load(firstLoad,'../student/queryYesStudentMatchinfo.do')
	}else if((searchname != "" && searchname != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill(phonedata,'../student/queryYesStudentMatchinfoBySceen.do')
	}else if((searchname == "" || searchname == null) && (filtercurrent != "" || filtercurrent != null) ){//如果是多条件查询的，就重新加载返回curret的列表数据
		load(filterData,'../student/queryYesStudentMatchinfoBySceen.do')
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