//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
var firstLoad = {
	userid:userid,
	deptid:deptid,
	roleid:roleid
}
//导入Excel		
function importExcel(){
	$('#importExcel').html("正在导入· · ·");
	$('#importExcel').removeAttr("onclick");
	$.ajax({
		url: '../student/excelImportOnlionBuy.do',
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
load(firstLoad,'../student/queryOnLineStudents.do',1)
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
			$('#filterTotal').html(data.total);
			//清空数据 
			clearDate(); 
			//插入头部
			$("#buyonlineTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
	
}
var tableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='all' /></th>"+
		"<th>序号</th>"+        
        "<th>姓名</th>"+
        "<th>身份证</th>"+
        "<th>手机</th>"+  
        "<th>固话</th>"+ 
        "<th>邮箱</th>"+
        "<th>单位</th>"+
        "<th>地址</th>"+
        "<th>招生老师</th>"+
        "<th>收款费用</th>"+
		"<th>收款日期</th>"+
		"<th>汇款方式</th>"+
		"<th>班级</th>"+
		"<th>优惠信息</th>"+
		 "<th>科目</th>"+
        "<th>课程</th>"+
        "<th>客服</th>"+
        
        
        
        
        
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#buyonlineTable').html("");
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
//筛选
var filterData;
function studentfilter(){
	$('#pagination').html('')
	var courseid= $('#course option:selected').val();
	//var customerId = $('#filterCustomer option:selected').val();
	
	var backcurren = "1" ; //只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)			
	
	filterData = {
       courseid:courseid,
       //customerId:customerId
    	
    }
	$('#studentfilter').css('display','none');
	load(filterData,'../student/queryOnLineStudents.do',1)
}
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array. studentId|| "";
		var resourceId = array. resourceId|| "";
	    var studentName = array.studentName|| "";
	    var idCard = array.idCard|| "";
	    var email = array.email|| "";
	    var phone = array.phone|| "";
	    var tel = array.tel|| "";
	    var remitWay = array.remitWay|| "";
	    var preferinfo = array.preferinfo|| "";
	    var courseName = array.courseName|| "";
	    var courseid = array. courseid || "";
	    var studentstateid =array.studentstate|| "";
	    var subjectname = array. subjectname|| "";
		var customerName = array. customerName|| "";
	    var banci = array.banci|| "";
	    var company = array.company|| "";
	    var address = array.address|| "";
	    var arrive_money = array. arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";	
	    var belongName = array. belongName|| "";
	    
	    tableItem ="<tr data-id="+studentId+">"+
		    "<td><input type='checkbox' class='"+studentId+"' data-id='"+resourceId+"' data-state='"+studentstateid+"' data-coures='"+courseid+"'/></td>"+  
		    "<td>"+((currentPage-1)*15+(index+1))+"</td>"+ 
		    "<td>"+studentName+"</td>"+
            "<td>"+idCard+"</td>"+
            "<td>"+phone+"</td>"+  
            "<td>"+tel+"</td>"+  
            "<td>"+email+"</td>"+
            "<td>"+company+"</td>"+  
            "<td>"+address+"</td>"+
            "<td>"+belongName+"</td>"+ 
            "<td>"+arrive_money+"</td>"+
            "<td>"+arrive_time+"</td>"+
            "<td>"+remitWay+"</td>"+
            "<td>"+banci+"</td>"+
            "<td>"+preferinfo+"</td>"+
            "<td>"+subjectname+"</td>"+
            "<td>"+courseName+"</td>"+
            "<td>"+customerName+"</td>"+
            
             
             
            
            
	    "</tr>";
		$("#buyonlineTable").append(tableItem);
	}); 
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
	
	$("#buyonlineTable input[type='checkbox']:not(:first):checked").each(function() {
		
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
 			   load(filterData,'../student/queryOnLineStudents.do')		   
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
 			   load(filterData,'../student/queryOnLineStudents.do')
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
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
//查询
$('#fastquery').on('click',function(){
	$('#pagination').html('');
	var search = $('#search').val();
	data = {
		phone:search
	}
	if(search=="" || search == null){
		load(firstLoad,'../student/queryOnLineStudents.do')
	}else {
		phoneFill (data,'../student/queryOnLineStudents.do')
	}
})
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
			$("#buyonlineTable").append(tableHeader); 
			fillTable(list,currentPage);
		}
	})
}