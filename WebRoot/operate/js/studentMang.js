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

/*根据课程ID，查询课程下的科目*/
$("#courseid").change(function() {
	var courseid = $('#courseid option:selected').val();
	if(courseid==""||courseid==null){
		$('#subjectid').html('');
	}else{
		checkRolename("#subjectid",courseid,'../subject/querySubject.do');
	}
	
});
/*根据课程ID，查询课程下的科目*/
function checkRolename(ele,courseid,url) {
    var clearname = $(ele);
    clearname.html(''); //清空原有的选项    
    $.ajax({        			
	   type: 'GET',
	   data: {
		   courseid:courseid
	   },
	   url: url,		   
	   success: function(data) {		  
		   var dataObj=eval("("+data+")");//转换为json对象
		   var list = dataObj.rows;			   
	        $.each(list, function(index, array) {
	        	//循环获取数据
                var subjectid = array.subjectid|| "";	
                var subjectname = array.subjectname || "";               
	        	var checkbox = ' <option value="' + subjectid + '">' + subjectname + '</option>';
	        	clearname.append(checkbox);
		    });
	   }
	});
}
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
var firstData ={        
	userid: userid,
	deptid:deptid,
	roleid:roleid
}
load(firstData,'../student/queryStudents.do',1)
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
			//$("#studentTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}
var tableHeader = "<tr class='henglan'>"+
	"<th style='width:40px;'><input type='checkbox' class='all' /></th>"+
	"<th class='serialnumber'>序号</th>"+
	"<th class='dealtime' style='width:140px;'>成交时间</th>"+
	"<th class='arrive_time' style='width:140px;'>到账时间</th>"+
	"<th class='state' style='width:70px;'>归属者</th>"+
	"<th class='resourceLevel' style='width:110px;'>学员姓名</th>"+
	"<th class='firstVisitTime' style='width:110px;'>渠道</th>"+              
	"<th class='source' style='width:110px;'>客户电话</th>"+
	"<th class='address' style='width:90px;'>课程</th>"+
	"<th class='resourceName'>报名科目</th>"+
	"<th class='phone' style='width:70px;'>成交金额</th>"+
	"<th class='arrive_money' style='width:70px;'>到账金额</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $("#studentTable tr:not(:first)").html("");
} 
//填充数据 
function fillTable(list,currentPage){
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array.studentId|| "";
	    var dealtime = array.dealtime|| "";
	    var belongName = array.belongName|| "";
	    var studentName = array.studentName|| "";
	    var source = array.source|| "";
	    var sourceid = array.source|| "";
	    var phone = array.phone|| "";
	    var courseName = array.courseName|| "";
	    var courseid = array.courseid|| "";
	    var subjectname = array.subjectname|| "";
	    var dealprice = array.dealprice|| "";
	    var arrive_time = array.arrive_time|| "";
	    var arrive_money = array.arrive_money|| "";
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
	    tableItem ="<tr data-id="+studentId+">"+
	    	"<td><input type='checkbox'class="+studentId+" /></td>"+
	        "<td class='serialnumber'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='dealtime'>"+dealtime+"</td>"+
	        "<td class='arrive_time'>"+arrive_time+"</td>"+
	        "<td class='belongName'>"+belongName+"</td>"+                
	        "<td class='studentName'>"+studentName+"</td>"+
	        "<td class='source' data-id="+sourceid+">"+source+"</td>"+	       
	        "<td class='phone'>"+phone+"</td>"+
	        "<td class='courseName' data-id="+courseid+">"+courseName+"</td>"+
	        "<td class='subjectname'>"+subjectname+"</td>"+ 
	        "<td class='dealprice'>"+dealprice+"</td>"+	
	        "<td class='arrive_money'>"+arrive_money+"</td>"+
	    "</tr>";
		$("#studentTable").append(tableItem);
	}); 
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
});
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});



var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id


/*根据条件进行查询资源--筛选 和查询    */
var filterData;
function filter(){
	var taskStartTime = $('#taskStartTime').val();
	var taskEndTime = $('#taskEndTime').val();
	var belongName = $('#belongName option:selected').val();
	gszfilter = $('#belongName option:selected').text();	
	var studentName = $('#studentName').val();	
	var source = $('#source').val();
	qdfilter = $('#source option:selected').text();
	var phone = $('#phone').val();	
	var courseid = $('#courseid  option:selected').val();
	kcfilter = $('#courseid  option:selected').text();
	$('#pagination').html('');
	
	//展示筛选条件
	if(gszfilter == "请选择归属者"){
		gszfilter = "";
	}
	
	if("请选择渠道" == qdfilter){
		qdfilter = "";
	}
	
	if("请选择课程" == kcfilter){
		kcfilter = '';
	}  
	$('.cjfilter').html(taskStartTime+"-"+taskEndTime)
	$('.gszfilter').html(gszfilter)
	$('.xmfilter').html(studentName)
	$('.qdfilter').html(qdfilter)
	$('.sjfilter').html(phone)
	$('.kcfilter').html(kcfilter)
	
	filterData = {
    	userid:userid,
    	deptid:deptid,
    	roleid:roleid,
    	dealStartTime:taskStartTime, //成交时间(结束)	
    	dealEndTime:taskEndTime, //成交时间(结束)
    	belongid: belongName,			
    	studentName :studentName,			
		source : source,		
		phone :phone,			
		courseid : courseid,
		currentPage:"1"
    }
	$('#filter').css('display','none');
	load(filterData,'../student/queryStudentBySceen.do',1)
}
function fastquery(){
	$('#pagination').html('');
	var mobile = $('#mobile').val();
	phonedata = {
		userid:userid,
    	deptid:deptid,
    	roleid:roleid,
		phone :mobile
	}
	if(mobile == "" || mobile == null){
		load(firstData,'../student/queryStudents.do')
	}else {
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
			//$("#studentTable").append(tableHeader); 
			fillTable(list,currentPage);
		}
	})
}
/*批量导出资源*/
function excelExport(){		
	var allStudent = [];	
	var studentId = [];
	$("#studentTable input[type='checkbox']:not(:first):checked").each(function() {
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
				url: '../student/queryExportStudentBySceen.do',
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
					    userid: userid,
				    	deptid:deptid,
				    	roleid:roleid
				};
				execlurl(dataParams,allStudents)
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
			    userid: userid,
		    	deptid:deptid,
		    	roleid:roleid
		};
		execlurl(dataParams,studentIds)
	}	
	
}
function execlurl(dataParams,studentIds){
	var params = $.param(dataParams);
	
	var url = '../student/excelExportStudent.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"><input type="text" name="studentIds" value="'+studentIds+'"/></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
}
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