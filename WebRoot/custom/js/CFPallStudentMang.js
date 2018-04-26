/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
/* 初始化时间插件*/
$('#examStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#examEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#matchinfoStarttime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
$('#matchinfoEndtime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
$('#mailStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
$('#mailEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
})
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
customName('#chooseCustomer');
customName('#filterCustomer');	
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
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

/*转移只有主管可见*/
if(roleid=="20"||roleid=="22"||roleid=="24"||roleid=="26"){
	$('#zgtransfer').css('display','block')
}
//第一次加载
var firstLoad = {        
	userid: userid,
	deptid:deptid,
	roleid:roleid,
	menuCode:"CFPtotal"
}
load(firstLoad,'../custom/queryTotalStudents.do',1)
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
		"<th style='width: 140px;position: fixed;background: #fff;left: 197px;'>身份证</th>"+  
		"<th class='phone' style='width: 120px;position: fixed;background: #fff;left:336px;'>手机</th>"+
		"<th style='width: 550px;padding-left: 465px;'>班主任</th>"+
		"<th>班级</th>"+
		"<th>学员状态</th>"+		            
		"<th>固定电话</th>"+
		"<th>邮箱</th>"+
		"<th>单位</th>"+      
		"<th>地区</th>"+   
		"<th>单位地址</th>"+		
		"<th>职务</th>"+
		"<th>课程</th>"+
		"<th>科目</th>"+		
		"<th>投资</th>"+      
		"<th>税务</th>"+   
		"<th>福利</th>"+
		"<th>保险</th>"+
		"<th>综合</th>"+		
		"<th>收款金额</th>"+
		"<th>到账日期</th>"+
		"<th>汇款方式</th>"+
		"<th>LCW用户名</th>"+
		"<th>LCW密码</th>"+		
		"<th>招生老师</th>"+
		"<th>是否全科通过</th>"+		
		"<th>报考密码</th>"+
        "<th>分配时间</th>"+       
        "<th>是否协助结业</th>"+        
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
	    var phone = array.phone|| "";
	    var studentstate =array.studentstate|| "";
	    var studentstateid =array.studentstate|| "";
	    var customerName = array.customerName|| "";
	    var idCard = array.idCard|| "";
	    var tel = array.tel|| "";
	    var email = array.email|| "";
	    var company = array.company|| "";
	    var address = array.address|| "";
	    var companyAddr = array.companyAddr|| "";	    
	    var position= array.position|| "";
	    var courseName = array.courseName|| "";
	    var subjectname = array.subjectname|| "";
	    var touzi = array.touzi|| "";
	    var shuiwu = array.shuiwu|| "";	    
	    var fuli= array.fuli|| "";
	    var baoxian = array.baoxian|| "";
	    var zonghe = array.zonghe|| "";
	    var arrive_money = array.arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";	    
	    var remitWay = array.remitWay|| "";	
	    var LCWname = array.LCWname|| "";	    
	    var LCWpassword = array.LCWpassword|| "";
	    var belongName = array.belongName|| "";	
	    var isAllPass = array.isAllPass|| "";
	    var baokaopassword = array.baokaopassword|| "";
	    var banci = array.banci|| "";
	    var customer_time = array.customer_time|| "";
	    var isjieye = array.isjieye|| "";
	    var specialinfo = array.specialinfo|| "";
	    console.log(specialinfo)
	    if (specialinfo != "") {
	    	studentName = '<font color="red">'+studentName+'</font>';	        
	    } 
	    
	    if (isAllPass == ""||isAllPass==null) {            	       
	    	isAllPass = "";
	    } 
	    else if (0 == isAllPass) {
	    	isAllPass = '未通过'
	    } 
	    else if (1 == isAllPass) {
	    	isAllPass = '通过'
	    } 
	    else if (2 == isAllPass) {
	    	isAllPass = '缺考'
	    }
	    
	    if (isjieye == ""||isjieye==null) {            	       
	    	isjieye = "";
	    } 
	    else if (0 == isjieye) {
	    	isjieye = '否'
	    } 
	    else if (1 == isjieye) {
	    	isjieye = '是'
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
	    	studentstate = '已提交'
	    } 
	    else if (3 == studentstate) {
	    	studentstate = '已到账'
	    } 
	    else if (4 == studentstate) {
	    	studentstate = '<font color="red">已分配</font>'
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
	        "<td style='width: 140px;position: fixed;background: #fff;left: 197px;'>"+idCard+"</td>"+
	        "<td class='phone' style='width: 120px;position: fixed;background: #fff;left: 336px;'>"+phone+"</td>"+
	        "<td style='width: 550px;padding-left: 465px;'>"+customerName+"</td>"+
	        "<td>"+banci+"</td>"+ 
	        "<td>"+studentstate+"</td>"+
	        "<td>"+tel+"</td>"+
	        "<td>"+email+"</td>"+
	        "<td>"+company+"</td>"+ 
	        "<td>"+address+"</td>"+
	        "<td>"+companyAddr+"</td>"+	        
	        "<td>"+position+"</td>"+
	        "<td>"+courseName+"</td>"+	
			"<td>"+subjectname+"</td>"+		
			"<td>"+touzi+"</td>"+      
			"<td>"+shuiwu+"</td>"+   
			"<td>"+fuli+"</td>"+
			"<td>"+baoxian+"</td>"+
			"<td>"+zonghe+"</td>"+	
			"<td>"+arrive_money+"</td>"+
			"<td>"+arrive_time+"</td>"+			
			"<td>"+remitWay+"</td>"+
	        "<td>"+LCWname+"</td>"+	              
	        "<td>"+LCWpassword+"</td>"+	        
	        "<td>"+belongName+"</td>"+	        
	        "<td>"+isAllPass+"</td>"+	              
	        "<td>"+baokaopassword+"</td>"+
	        "<td>"+customer_time+"</td>"+
	        "<td>"+isjieye+"</td>"+	        
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
function cfpfastquery(){
	var search = $('#search').val();
	$('#pagination').html('');
	var phonedata = {        
    	userid:userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName:search,
    	idCard:search,
    	phone:search,
    	menuCode:"CFPtotal"
    }
	if(search=="" || search == null){
		load(firstLoad,'../custom/queryTotalStudents.do')//等于空加载所有
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
			//插入头部
		       $("#AFPallTable").append(tableHeader); 
			   fillTable(list,currentPage)
		}
	})
}
/*清空筛选条件*/
function  showFilter(){
	$('#filter').css('display','block')	  
	$('#studentName').val('');	
	$('#phone').val('');	
	$('#source option').removeAttr("selected"); 
	$('#studentstate option').removeAttr("selected");
	$('#courseid option').removeAttr("selected"); 
	$('#belongName option').removeAttr("selected"); 
	$('#company').val('');	
	$('#scoretime').val('');
	$('#ispass option').removeAttr("selected"); 
	$('#arrive_time').val('');
	$('#classNum').val('');
	$('#courseversion').val('');
	$('#mailStartTime').val('');
	$('#mailEndTime').val('');
	$('#banci option').removeAttr("selected");
	$('#filterCustomer option').removeAttr("selected");
	$('#matchinfoStarttime').val('');
	$('#matchinfoEndtime').val('');
	$('#examStartTime').val('');
	$('#examEndTime').val('');
}
/*根据条件进行查询资源--筛选*/
var filterData;
function filter(){	
	$('#pagination').html('');
	var studentName = $('#studentName').val();	
	var phone = $('#phone').val().trim();	
	var source = $('#source option:selected').val();
	var studentstate = $('#studentstate option:selected').val();
	var courseid = $('#courseid  option:selected').val();
	var belongName = $('#belongName  option:selected').val();
	var banci = $('#banci option:selected').val();	
	var company = $('#company').val();	
	var scoretime = $('#scoretime').val();
	var ispass = $('#ispass option:selected').val();
	var arrive_time = $('#arrive_time').val();
	var classNum = $('#classNum').val();
	var examStartTime = $('#examStartTime').val();
	var examEndTime = $('#examEndTime').val();
	var courseversion = $('#courseversion').val();
	var mailStartTime = $('#mailStartTime').val();
	var mailEndTime = $('#mailEndTime').val();
	var matchinfoStarttime = $('#matchinfoStarttime').val();
	var matchinfoEndtime = $('#matchinfoEndtime').val();
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
		banci:banci,
		company:company,
		scoretim:scoretime,
		ispass:ispass,
		arrive_time:arrive_time,
		mailStartTime:mailStartTime,
		mailEndTime:mailEndTime,
		examStartTime:examStartTime,
		examEndTime:examEndTime,
		matchinfoStarttime:matchinfoStarttime,
		matchinfoEndtime:matchinfoEndtime,
		classNum:classNum,
		courseversion:courseversion,
		customerId:customerId,
		studentstate:studentstate,
		menuCode:"CFPtotal"
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
	studentstates = studentstate.join(',')
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
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
				    menuCode:"CFPtotal"
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
			    menuCode:"CFPtotal"
		};
	}	
	
	var params = $.param(dataParams);
	
	var url = '../custom/StudentExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
};


