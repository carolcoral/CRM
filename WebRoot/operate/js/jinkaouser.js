/* 初始化时间插件*/
$('#startTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#endTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i", //格式化日期
	validateOnBlur:false //删除选中的时间
});
//数据分页
var firstload = {
		
}
load(firstload,'../resource/queryTransferRecord.do',1)
function load(data,url){
	$.ajax({
		url: url,
		type: 'GET',
		data: data,			
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;			
			//清空数据 
			clearDate(); 
			//插入头部
			fillTable(list);
		}
	})
}
//清空数据 
function clearDate(){ 
    $("#regTable tr:not(:first)").html("");
} 
//填充数据 
function fillTable(list){
	$.each(list, function(index, array) {
			
		//循环获取数据
		var nick_name = array.nick_name|| "";
		var create_time = array.create_time|| "";
		var mobile = array.mobile|| "";
	    var last_reg = array.last_reg|| "";
	    
	    tableItem ="<tr>"+
	        "<td>"+(index+1)+"</td>"+
	        "<td>"+nick_name+"</td>"+
	        "<td>"+create_time+"</td>"+
	        "<td>"+mobile+"</td>"+
	        "<td>"+last_reg+"</td>"+	        
	    "</tr>";
		$("#regTable").append(tableItem);
	}); 
}

/*查询*/
function query(){
	var queryval = $('#phone').val();
	clearDate()
	phonedata = {
		phone:queryval
	}
	if(queryval == '' || queryval == null){
		load(firstload,'../resource/queryTransferRecord.do')
	}else {
		 phoneFill(phonedata,'../resource/queryTransferRecord.do')
	}
}

/*时间筛选*/

function timequery(){
	var startTime = $('#startTime').val();
	var endTime = $('#endTime').val();
	clearDate()
	timedata = {
		startTime:startTime,
		endTime:endTime
	}
	if(queryval == '' || queryval == null){
		load(firstload,'../resource/queryTransferRecord.do')
	}else {
		 phoneFill(timedata,'../resource/queryTransferRecord.do')
	}
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
