//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

var firstLoad = {
	userid:userid,
	deptid:deptid,
	roleid:roleid
}
load(firstLoad,'../student/queryPassStudents.do',1)
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
			$("#colseTable").append(tableHeader); 
			fillTable(list,currentPage);
			$("#btnJump").after(total);
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
        "<th>课程</th>"+
        "<th>科目</th>"+
        "<th>客服</th>"+
        "<th>班级</th>"+
        "<th>单位</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#colseTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array. studentId|| "";
		var resourceId = array. resourceId|| "";
	    var studentName = array.studentName|| "";
	    var idCard = array.idCard|| "";
	    var phone = array.phone|| "";
	    var courseName = array.courseName|| "";
	    var subjectname = array. subjectname|| "";
		var customerName = array. customerName|| "";
	    var banci = array.banci|| "";
	    var company = array.company|| "";
	    
	    tableItem ="<tr data-id="+studentId+">"+
		    "<td><input type='checkbox' class='"+studentId+"' data='"+resourceId+"'/></td>"+
		    "<td>"+((currentPage-1)*15+(index+1))+"</td>"+ 
		    "<td>"+studentName+"</td>"+
            "<td>"+idCard+"</td>"+
            "<td>"+phone+"</td>"+        
            "<td>"+courseName+"</td>"+
            "<td>"+subjectname+"</td>"+
            "<td>"+customerName+"</td>"+
            "<td>"+banci+"</td>"+
            "<td>"+company+"</td>"+  
	    "</tr>";
		$("#colseTable").append(tableItem);
	}); 
}
//已通过学员进行关课
function sendexEcutive(){	
	var studentId = [];
	var resourceId = [];
	$("#colseTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var dataId = $(this).attr('data-id');
		studentId.push(boxid);
		resourceId.push(dataId);
	});
	studentIds = studentId.join(',')
	resourceIds = resourceId.join(',')
	if(studentId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src: "./system/img/tishi.png"
		});
	} else {
		$.ajax({
			type: "POST",
			url: '../student/closeCources.do',
			data: {
				studentIds: studentIds,
				resourceIds: resourceIds
			},
			success: function(data) {
				console.log(data)
				if(data=="success"){
					window.location.reload();				
				}else{
					
				}
				
			}
		});			
	}
}
/*筛选*/
function networkfilter(){
	$('#pagination').html('');
	var studentName = $('#studentName').val();	
	var phone = $('#phone').val();
	var idCard = $('#idCard').val();
	var banci = $('#banci option:selected').val();
	var company = $('#company').val();
	var companyAddr = $('#companyAddr').val();
	var filterData = {
		userid:userid,
		deptid:deptid,
		roleid:roleid,
		studentName:studentName,
		phone:phone,
		banci:banci,
		idCard:idCard,
		banci:banci,
		company:company,
		companyAddr:companyAddr 
	}
	$('#networkfilter').css('display','none');
	load(filterData,'../student/queryPassStudents.do',1)
}

/*查询*/
function namesearch(){
	$('#pagination').html('');
	var searchname = $('#searchname').val();
	data = {
		userid:userid,
		deptid:deptid,
		roleid:roleid,
		studentName:searchname,
		phone:searchname
	}
	if(searchname=="" || searchname == null){
		load(firstLoad,'../student/queryPassStudents.do')
	}else {
		phoneFill (data,'../student/queryPassStudents.do')
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


/*导出*/
function exportExcel(){
	var allStudent = [];
	$.ajax({  //获取所以的ID      			
		type: "GET",
		async:false,
		data: { 
			userid:userid,			 			
			roleid:roleid,
			deptid:deptid
		},
		url: '../student/queryPassStudents.do',
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象	
			var list = dataObj.rows;
			$.each(list, function(index, array) {
				allStudent.push(array.studentId);
			});		 	  
			
		}
	});
	var studentId = [];
	$("#networkTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});
	
	studentIds = studentId.join(',');
	allStudents = allStudent.join(',');
	
	var exportExcel = "export_excel";	
	if(studentId == 0){
		var dataParams = {
			userid:userid,			 			
			roleid:roleid,
			deptid:deptid
		};
		execlurl(dataParams,allStudents)
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