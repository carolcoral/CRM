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
	
	var url = '../student/studentDetails.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
	nowheight = $(document).height();
}
/*展示信息*/
var firstLoad = {
	userid:userid,
	deptid:deptid,
	roleid:roleid
}
load('pagination',firstLoad,'../student/queryStudents.do',1)
function load(tag,data,url,currentPage){
	$('#'+tag).sjAjaxPager({
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
	        $("#studentTable").append(tableHeader); 
		    fillTable(list,currentPage);
	    },
	    complete: function () {
	    }
	});
}
var tableHeader = "<tr class='henglan'>"+
			"<th><input type='checkbox' class='all' /></th>"+
			"<th class='subjectname'>详情</th>"+  
			"<th class='serialnumber'>序号</th>"+        
			"<th class='commit_time'>提交时间</th>"+
			"<th class='studentstate'>学员状态</th>"+
			"<th class='preferinfo'>优惠信息</th>"+
			"<th class='studentName'>姓名</th>"+       
			"<th class='idCard'>身份证</th>"+
			"<th class='phone'>电话</th>"+       
			"<th class='email'>邮箱</th>"+
			"<th class='belongName'>招生老师</th>"+       
			"<th class='banci'>班级</th>"+
			"<th class='coursename'>课程名称</th>"+  
			"<th class='subjectname'>科目名称</th>"+ 
			"<th class='sumPayMoney'>总金额</th>"+
			"<th class='company'>单位</th>"+
			"<th class='companyAddr'>地址</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#studentTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var resourceId =array.resourceId || "";
		var studentId = array.studentId|| "";
		var commit_time = array.commit_time|| "";
	    var studentstate = array.studentstate|| "";
	    var preferinfo = array.preferinfo|| "";
	    var studentName = array.studentName|| "";
	    var idCard = array.idCard|| "";
	    var phone = array.phone|| "";
	    var email = array.email|| "";
	    var belongName = array.belongName|| "";
	    var banci = array.banci|| "";
	    var courseName = array.courseName|| "";
	    var subjectname = array.subjectname|| "";
	    var sumPayMoney = array.sumPayMoney|| "";
	    var company = array.company|| "";
	    var companyAddr = array.companyAddr|| "";
	    
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
		    "<td><input type='checkbox' class='"+studentId+"' data-id='"+resourceId+"'/></td>"+
		    "<td><a onclick='showiframe("+studentId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+studentId+"></span></a></td>"+                                   
	        "<td class='serialnumber'>"+((currentPage-1)*15+(index+1))+"</td>"+        
	        "<td class='commit_time'>"+commit_time+"</td>"+
	        "<td class='studentstate'>"+studentstate+"</td>"+                               
	        "<td class='preferinfo'>"+preferinfo+"</td>"+
	        "<td class='studentName'>"+studentName+"</td>"+       
	        "<td class='idCard'>"+idCard+"</td>"+
	        "<td class='phone'>"+phone+"</td>"+   
	        "<td class='email'>"+email+"</td> "+
	        "<td class='belongName'>"+belongName+"</td>"+
	        "<td class='banci'>"+banci+"</td>"+
	        "<td class='coursename'>"+courseName+"</td>"+
	        "<td class='subjectname'>"+subjectname+"</td>"+
	        "<td class='sumPayMoney'>"+sumPayMoney+"</td>"+
			"<td class='company'>"+company+"</td>"+
			"<td class='companyAddr'>"+companyAddr+"</td>"+
	    "</tr>";
		$("#studentTable").append(tableItem);
	}); 
}
//筛选/查询
var filterdata;
function studentfilter(){
	var taskStartTime = $('#taskStartTime').val();
	var taskEndTime = $('#taskEndTime').val();
	var studentName = $('#studentName').val();
	var phone = $('#phone').val();
	var belongid = $('#belongName option:selected').val();
	var banci = $('#banci option:selected').val();
	var courseid = $('#course option:selected').val();
	var searchname = $('#searchname').val()//按名字查询

	$('#pagination').html('');
	filterdata = {  //按条件筛选
    	userid:userid,
    	roleid:roleid,
    	deptid:deptid,
    	commitStarttime: taskStartTime,//提交日期(开始)
    	commitEndtime:taskEndTime,//提交日期(结束)
    	studentName:studentName,
    	phone:phone,
    	belongid:belongid,
    	banci:banci,
    	courseid:courseid	    	
    }
	$('#studentfilter').css('display','none');
	load('pagination',filterdata,'../student/queryStudentBySceen.do',1)
}
/*查询*/
var searchname = null;
var phonedata;
function searname(){
	$('#pagination').html('');
	searchname = $('#searchname').val()//按名字查询
	phonedata = {        
		userid:userid,
    	roleid:roleid,
    	deptid:deptid,
    	studentName:searchname
	}
	if(searchname=="" || searchname == null){
		$('#backcurrent').attr('data-id','')
		load('pagination',firstLoad,'../student/queryStudents.do')
	}else{
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
		phoneFill(phonedata,'../student/queryStudentBySceen.do')
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
		    $("#studentTable").append(tableHeader); 
		    fillTable(list,currentPage);
		}
	})
}
//退回
function returnNote(){
	var studentId = [];
	var resourceId = [];
	$("#studentTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var dataId = $(this).attr('data-id');
		studentId.push(boxid);
		if(dataId != "" && dataId != null){
			resourceId.push(dataId);
		}
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
		$('#tuihui').css('display','block');
	}
	
}
function deterReturnNote(){
	var returnNote = $('#returnNote').val();
	if(returnNote=="" || returnNote==null){
		$('#returnNoteError').html("退回原因不能为空！");
		return false;
	}else {
		$('#returnNoteError').html("");
	}
	$.ajax({
		type: "POST",
		url: '../student/returnStudent.do',
		data: {
			userid:userid,
			studentIds: studentIds,
			resourceIds: resourceIds,
			returnNote:returnNote
		},
		success: function(data) {
			if(data=="success"){
				window.location.reload();
			}else if(data=="0"){
				$.winTip({
					title: "提示~~~",
					msg: "参数为空",
					src: "./system/img/tishi.png"
				});
			}
			
		}
	});		
}
/*批量导出资源*/
function exportExcel(){	
	var studentId = []; //获取选中资源的ID   
	var allstudentId = [];	//获取所以资源的ID   
	$("#studentTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});	
	studentIds = resourceId.join(',');
	allstudentIds = allstudentId.join(',');	
	var exportExcel = "export_excel";	
	if(resourceId == 0){		
		$.ajax({  //获取所以资源的ID      			
	 	   type: "GET",
	 	   data: { 
	 		    userid:userid,			 			
	 			roleid:roleid,
				deptid:deptid,
	 	   },
	 	   url: '../student/queryStudents.do',
	 	   success: function(data) {
	 		  var dataObj=eval("("+data+")");//转换为json对象	
	 		  var list = dataObj.rows;
		 	  $.each(list, function(index, array) {
		 			allresourceId.push(array.studentId);
		      });		 	  
	 		 		 		   
	 		}
	 	});
		var dataParams = {
				studentIds:allstudentIds,
			    deptid:deptid
		};
	}else {
		dataParams = {
				studentIds:studentIds,
			    deptid:deptid
		};
	}	
	
	var params = $.param(dataParams);
	
	var url = '../student/excelExportStudent.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
};


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
		load('pagination',firstLoad,'../student/queryStudents.do')
	}else if((searchmobile != "" && searchmobile != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill(phonedata,'../student/queryStudentBySceen.do')
	}else if((searchmobile == "" || searchmobile == null) && (filtercurrent != "" || filtercurrent != null) ){//如果是多条件查询的，就重新加载返回curret的列表数据
		load('pagination',filterdata,'../student/queryStudentBySceen.do')
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