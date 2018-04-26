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
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
//展示iframe
function showiframe(studentId,tag,currentPage){
	var dataParams = {
		deptid:deptid,
		studentId:studentId,
		menuCode:"CFPhf"
	}
	$('#backcurrent').val(currentPage)
	var params = $.param(dataParams);
	var url = '../custom/hfDetails.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
	nowheight = $(document).height();
}

//加载
var firstLoad=  {        
	userid: userid,
	deptid:deptid,
	roleid:roleid,
	menuCode:"AFPhf"
}
load(firstLoad,'../custom/queryhfStudents.do',1)
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
		searchParam:data,
		beforeSend: function () {
		},
		success: function (data) {    	
			var list = data.rows;
			var currentPage = data.currentPage;
			$('#filterTotal').html(data.total)
			//清空数据 
			clearDate(); 
			//插入头部
			$("#resourceTable").append(tableHeader); 
			fillTable(list,currentPage)
		},
		complete: function () {
		}
	});
}

var tableHeader = "<tr class='henglan'>"+
		"<th class='check' style='width: 60px;position: fixed;background:#fff;'><input type='checkbox' class='all' /></th>"+
		"<th class='details' style='width: 60px;position: fixed;left: 59px;background:#fff;'>详情</th>"+
		"<th class='serialnumber' style='width: 60px;position: fixed;left: 118px;background:#fff;'>序号</th>"+
		"<th class='studentName' style='width: 80px;position: fixed;left: 177px;background:#fff;'>姓名</th>"+
		"<th class='phone' style='width: 120px;position: fixed;left: 256px;background:#fff;'>手机</th>"+
		"<th class='idCard' style='width: 150px;position: fixed;left: 375px;background:#fff;'>身份证</th>"+
		"<th class='banci' style='width: 620px;padding-left: 510px;'>班次</th>"+
		"<th class='studentstate'>学员状态</th>"+
		"<th class='customerName'>班主任</th>"+
		"<th class='company'>单位</th>"+
		"<th class='companyAddr'>公司地址</th>"+
		"<th class='baokaopassword'>报考密码</th>"+
		"<th class='acscoretime'>考试日期</th>"+
		"<th class='ispass'>通过情况</th>"+   
		"<th class='belongName'>招生老师</th>"+
		"<th class'arrive_money'>收款金额</th>"+
		"<th class='arrive_time'>到账日期</th>"+
		"<th class='classNum'>上课班号</th>"+
		"<th class='courseversion'>课件</th>"+
		"<th class='mailTime'>邮寄时间</th>"+		
	"</tr>";
//清空数据 
//清空数据 
function clearDate(){ 
    $('#resourceTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {		
		//循环获取数据
		var resourceId = array. resourceId|| "";
		var studentId = array. studentId|| "";
	    var studentName = array.studentName|| "";
	    var phone = array.phone|| "";	    
	    var banci = array.banci|| "";
	    var studentstate = array.studentstate|| "";
	    var idCard = array.idCard|| "";
	    var company = array.company|| "";
	    var baokaopassword = array.baokaopassword|| "";
	    var acscoretime = array.acscoretime|| "";
	    var ispass = array.ispass|| "";
	    var belongName = array.belongName|| "";	
	    var arrive_time = array.arrive_time|| "";
	    var arrive_money = array.arrive_money|| "";
	    var classNum = array.classNum|| "";
	    var courseversion = array.courseversion|| "";
	    var mailTime = array.mailTime|| "";  
	    var customerName = array.customerName || "";
	    var studentColor = array.studentColor|| "";
	    var companyAddr = array.companyAddr|| "";
	    if (studentColor == ""||studentColor==null) {            	       
	    	studentColor = "#fff";
	    }else{
	    	studentColor = studentColor;
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
	   	    
	    if (ispass == ""||ispass==null) {            	       
	    	ispass = "";
	    } 
	    else if (0 == ispass) {
	    	ispass = '未通过'
	    } 
	    else if (1 == ispass) {
	    	ispass = '通过'
	    } 
	    else if (2 == ispass) {
	    	ispass = '缺考'
	    } 
	    
	    if(arrive_time == ""){
	    	arrive_time = "";
	    }else {
	    	arrive_time = /\d{4}-\d{1,2}-\d{1,2}/g.exec(arrive_time);	    	
	    }
	    
	    if(acscoretime == ""){
	    	acscoretime = "";
	    }else {
	    	acscoretime = /\d{4}-\d{1,2}-\d{1,2}/g.exec(acscoretime);	    	
	    }
	    
	    if(mailTime == ""){
	    	mailTime = "";
	    }else {
	    	mailTime = /\d{4}-\d{1,2}-\d{1,2}/g.exec(mailTime);	    	
	    }
	    
	    
	    
	    var menuCode = "AFPhf";
	    tableItem ="<tr data-id="+studentId+" style='background-color:"+studentColor+"' >"+
		    "<td class='check' style='width: 60px;position: fixed;background-color:"+studentColor+"'><input type='checkbox' class='"+studentId+"'  data-id='"+resourceId+"'/></td>"+
	        "<td class='details' style='width: 60px;position: fixed;left:59px;background-color:"+studentColor+"'><a onclick='showiframe("+studentId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+studentId+"></span></a></td>"+ 
	        "<td class='serialnumber' style='width: 60px;position: fixed;left: 118px;background-color:"+studentColor+"'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='studentName' style='width: 80px;position: fixed;left: 177px;background-color:"+studentColor+"'>"+studentName+"</td>"+
	        "<td class='phone' style='width: 120px;position: fixed;left: 256px;background-color:"+studentColor+"'>"+phone+"</td>"+
	        "<td class='idCard' style='width: 150px;position: fixed;left: 375px;background-color:"+studentColor+"'>"+idCard+"</td>"+
	        "<td class='banci' style='width: 600px;padding-left: 520px;'>"+banci+"</td>"+
	        "<td class='studentstate'>"+studentstate+"</td>"+
	        "<td class='customerName'>"+customerName+"</td>"+
            "<td class='company'>"+company+"</td>"+ 
            "<td class='company'>"+companyAddr+"</td>"+ 
            "<td class='baokaopassword'>"+baokaopassword+"</td>"+
            "<td class='acscoretime'>"+acscoretime+"</td>"+
            "<td class='ispass'>"+ispass+"</td>"+                
            "<td class='belongName'>"+belongName+"</td>"+    
            "<td class='arrive_time'>"+arrive_money+"</td>"+
            "<td class='arrive_time' data-id="+arrive_time+">"+arrive_time+"</td>"+
            "<td class='classNum'>"+classNum+"</td>"+
            "<td class='courseversion'>"+courseversion+"</td>"+
            "<td class='mailTime'>"+mailTime+"</td>"+           
	    "</tr>";
		$("#resourceTable").append(tableItem);
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
/*清空筛选条件*/
function filtershow(){
	$('#filter').css('display','block')	  
	$('#studentName').val('');	
	$('#phone').val('');	
	$('#source option').removeAttr("selected"); 
	$('#courseid option').removeAttr("selected"); 
	$('#belongName option').removeAttr("selected"); 
	$('#banci').val('');	
	$('#company').val('');	
	$('#scoretime').val('');
	$('#ispass option').removeAttr("selected"); 
	$('#arrive_time').val('');
	$('#classNum').val('');
	$('#courseversion').val('');
	$('#studentColor option').removeAttr("selected");
	$('#banci option:selected').removeAttr("selected");
	$('#matchinfoStarttime').val('');
	$('#matchinfoEndtime').val('');
	$('#mailStartTime').val('');
	$('#mailEndTime').val('');
	$('#kefuNote1').val('');
	$('#filterCustomer option').removeAttr("selected");
	$('#examStartTime').val('');
	$('#examEndTime').val('');
}
/*根据条件进行查询资源--筛选*/
var filterData;
function filter(){	
	$('#pagination').html('');
	var backcurren = "1" ;//只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	var studentName = $('#studentName').val();	
	var phone = $('#phone').val().trim();	
	var source = $('#source option:selected').val();
	var courseid = $('#courseid option:selected').val();
	var belongName = $('#belongName option:selected').val();
	var banci = $('#banci option:selected').val();	
	var company = $('#company').val();	
	var scoretime = $('#scoretime').val();
	var ispass = $('#ispass option:selected').val();
	var arrive_time = $('#arrive_time').val();
	var classNum = $('#classNum').val();
	var courseversion = $('#courseversion').val();
	var matchinfoStarttime = $('#matchinfoStarttime').val();
	var matchinfoEndtime = $('#matchinfoEndtime').val();
	var studentColor = $('#studentColor option:selected').val();
	var mailStartTime = $('#mailStartTime').val();
	var mailEndTime = $('#mailEndTime').val();
	var kefuNote1 = $('#kefuNote1').val();
	var examStartTime = $('#examStartTime').val();
	var examEndTime = $('#examEndTime').val();
	var customerId = $('#filterCustomer option:selected').val();
	filterData = {
    	userid:userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName :studentName,
    	phone:phone,
		source:source,		
		courseid:courseid,
		belongid:belongName,
		matchinfoStarttime:matchinfoStarttime,
		matchinfoEndtime:matchinfoEndtime,
		examStartTime:examStartTime,
		examEndTime:examEndTime,
		mailStartTime:mailStartTime,
		mailEndTime:mailEndTime,
		banci:banci,
		company:company,
		scoretim:scoretime,
		ispass:ispass,
		arrive_time:arrive_time,
		classNum:classNum,
		courseversion:courseversion,
		studentColor:studentColor,
		kefuNote1:kefuNote1,
		customerId:customerId,
		menuCode:"AFPhf"
    }
	$('#filter').css('display','none')	    	
	load(filterData,'../custom/queryCustomStudentBySceen.do',1)
}

/*查询*/
var search = null;
var phonedata;
function fastquery(){
	search = $('#search').val();
	$('#pagination').html('');
	phonedata = {        
		userid: userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName:search,
    	idCard:search,
    	phone:search,
    	menuCode:"AFPhf"
	}
	if(search=="" || search == null){
		$('#backcurrent').attr('data-id','');
		load(firstLoad,'../custom/queryhfStudents.do')//等于空加载所有
	}else {
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
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
	       $("#resourceTable").append(tableHeader); 
		   fillTable(list,currentPage)
		}
	})
}

/*导出*/
function exportExcel(){	
	
	var allStudent = [];	
	var studentId = [];
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});
	
	studentIds = studentId.join(',');
	var exportExcel = "export_excel";	
	if(studentId == 0){
		if(filterData != null){
			execlurl(filterData)
		}else{
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
			deptid:deptid,
			studentIds:studentIds,
			menuCode:"AFPhf"
		};
		execlurl(dataParams)
	}	
};

function execlurl(dataParams){
	var params = $.param(dataParams);
	
	var url = '../custom/StudentExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
    delete dataParams[exportExcel];
}


var iframe = $(window.parent.document).find("#iframepage");
iframe.height(0);
//子窗口操作列表页
function showdeiatil(){
	$('#showdeiatil').css('display','none');
	iframe.height(0);
	window.setInterval("reinitIframe()", 200);
	var curret = $('#backcurrent').val();
	var filtercurrent = $('#backcurrent').attr('data-id');
	
	if((curret != "" && curret != null) && (filtercurrent == "" || filtercurrent == null)){ //如果当前页不等于空，就重新加载返回curret的列表数据
		load(firstLoad,'../custom/queryhfStudents.do')
	}else if((search != "" && search != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill (phonedata,'../custom/queryCustomStudentBySceen.do')
	}else if((search == "" || search == null) && (filtercurrent != "" || filtercurrent != null) ){//如果是多条件查询的，就重新加载返回curret的列表数据
		load(filterData,'../custom/queryCustomStudentBySceen.do')
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


