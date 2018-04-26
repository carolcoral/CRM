/* 初始化时间插件*/
$('#arriveStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#arriveEndTime').datetimepicker({
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

//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
//初次加载
var loadData = {        
	userid: userid,
	deptid:deptid,
	roleid:roleid
}
load(loadData,'../report/queryStudentscaiwu.do',1);
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
        "<th style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='all' /></th>"+
		"<th class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 59px;'>序号</th>"+
		"<th class='studentName' style='width: 120px;position: fixed;background: #fff;left: 118px;'>姓名</th>"+
		"<th class='phone' style='width:330px;padding-left: 245px;'>手机</th>"+
		"<th class='studentstate'>学员状态</th>"+
		"<th class='matchinfo_time'>确认到账时间</th>"+
		"<th class='idCard'>身份证</th>"+              
		"<th class='tel'>固定电话</th>"+
		"<th class='email'>邮箱</th>"+
		"<th class='company'>公司</th>"+
		"<th class='companyAddr'>公司地址</th>"+		
		"<th class='position'>职务</th>"+
		"<th class='school'>毕业院校</th>"+
		"<th class='education'>学历</th>"+
		"<th class='nation'>民族</th>"+
		"<th class='belongName' >招生老师</th>"+
		"<th class='preferinfo' >优惠情况</th>"+
		"<th class='remituser' >代汇款姓名</th>"+
		"<th class='arrive_money' >收款金额</th>"+
		"<th class='arrive_time' >收款日期</th>"+
		"<th class='remitWay' >汇款方式</th>"+
		"<th class='LCWname' >用户名</th>"+
		"<th class='LCWpassword' >LCW密码</th>"+
		"<th class='courseversion' >课件</th>"+
		"<th class='invoiceinfo' >发票情况</th>"+
		"<th class='baokaopassword' >报考密码</th>"+
		"<th class='ispass' >是否通过</th>"+
		"<th class='isSignAgreement' >是否签订协议</th>"+
		"<th class='banci' >班级</th>"+
		"<th class='qici' >期次</th>"+
		"<th class='xingzhengNote' >行政备注</th>"+
		"<th class='subjectname' >科目</th>"+
		"<th class='courseName' >课程名称</th>"+
		"<th class='dealprice' >我司收入</th>"+
		"<th class='tuifei' >退费</th>"+
		"<th class='tuifeitime' >退费日期</th>"+
		"<th class='netedumoney' >网络培训费</th>"+
		"<th class='paytime' >支付日期</th>"+
		"<th class='shenhe' >审核问题</th>"+
		"<th class='caiwunote' >备注</th>"+
	"</tr>";
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
	    var studentstate = array.studentstate|| "";
	    var matchinfo_time = array.matchinfo_time|| "";
	    var idCard = array.idCard|| "";
	    var tel = array.tel|| "";	
	    var email = array.email|| "";
	    var company = array.company|| "";
	    var companyAddr = array.companyAddr|| "";	   
	    var position = array.position|| "";
	    var school = array.school|| "";
	    var education = array.education|| "";
	    var preferinfo = array.preferinfo|| "";
	    var LCWname = array.LCWname|| "";
	    var LCWpassword = array.LCWpassword|| "";
	    var nation = array.nation|| "";
	    var department = array.department|| "";
	    var belongName = array.belongName|| "";
	    var preferinfo = array.preferinfo|| "";
	    var remituser = array.remituser|| "";
	    var arrive_money = array.arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";
	    var remitWay = array.remitWay|| "";
	    var LCWname = array.LCWname|| "";
	    var LCWpassword = array.LCWpassword|| "";
	    var courseversion = array.courseversion|| "";
	    var invoiceinfo = array.invoiceinfo|| "";
	    var baokaopassword = array.baokaopassword|| "";
	    var ispass = array.ispass|| "";
	    var isSignAgreement = array.isSignAgreement|| "";
	    var banci = array.banci|| "";
	    var qici = array.qici|| "";
	    var xingzhengNote = array.xingzhengNote|| "";
	    var courseName = array.courseName|| "";
	    var subjectname = array.subjectname|| "";
	    var dealprice = array.dealprice|| "";
	    var tuifei = array.tuifei|| "";
	    var tuifeitime = array.tuifeitime|| "";
	    var netedumoney = array.netedumoney|| "";
	    var paytime = array.paytime|| "";
	    var shenhe = array.shenhe|| "";
	    var caiwunote = array.caiwunote|| "";
	    
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
	    
	    if (isSignAgreement == ""||isSignAgreement==null) {            	       
	    	isSignAgreement = "";
	    } 
	    else if (0 == isSignAgreement) {
	    	isSignAgreement = '未签订 '
	    } 
	    else if (1 == isSignAgreement) {
	    	isSignAgreement = '已签订'
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
	    tableItem ="<tr data-id="+studentId+">"+
	        "<td style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='"+studentId+"'  data-id='"+resourceId+"'/></td>"+
	        "<td class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 59px;'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='studentName' style='width: 120px;position: fixed;background: #fff;left: 118px;'>"+studentName+"</td>"+
	        "<td class='phone' style='width: 330px;padding-left: 245px;'>"+phone+"</td>"+
	        "<td class='studentstate'>"+studentstate+"</td>"+
	        "<td class='matchinfo_time' >"+matchinfo_time+"</td>"+
	        "<td class='idCard'>"+idCard+"</td>"+  
	        "<td class='tel'>"+tel+"</td>"+               
	        "<td class='email'>"+email+"</td>"+
	        "<td class='company'>"+company+"</td>"+
	        "<td class='homeAddr'>"+companyAddr+"</td>"+
	        "<td class='position'>"+position+"</td>"+
	        "<td class='school'>"+school+"</td>"+
	        "<td class='education'>"+education+"</td>"+
	        "<td class='nation'>"+nation+"</td>"+
	        "<td class='belongName'>"+belongName+"</td>"+
	        "<td class='preferinfo'>"+preferinfo+"</td>"+
	        "<td class='remituser'>"+remituser+"</td>"+
	        "<td class='arrive_money'>"+arrive_money+"</td>"+ 
	        "<td class='arrive_time'><input type='text'value='"+arrive_time+"' id='dit"+(index+1)+"' class='changearrivetime' disabled='disabled'/></td>"+
	        "<td class='remitWay'><input type='text'value='"+remitWay+"' class='changeremitWay' disabled='disabled'/></td>"+
	        "<td class='LCWname'>"+LCWname+"</td>"+
	        "<td class='LCWpassword'>"+LCWpassword+"</td>"+
	        "<td class='courseversion'>"+courseversion+"</td>"+
	        "<td class='invoiceinfo'>"+invoiceinfo+"</td>"+
	        "<td class='baokaopassword'>"+baokaopassword+"</td>"+
	        "<td class='ispass'>"+ispass+"</td>"+
	        "<td class='isSignAgreement'>"+isSignAgreement+"</td>"+
	        "<td class='banci'>"+banci+"</td>"+
	        "<td class='qici'>"+qici+"</td>"+
	        "<td class='xingzhengNote'>"+xingzhengNote+"</td>"+
	        "<td class='subjectname'>"+subjectname+"</td>"+
	        "<td class='courseName'>"+courseName+"</td>"+
	        "<td class='dealprice'>"+dealprice+"</td>"+
	        "<td class='tuifei'>"+tuifei+"</td>"+
	        "<td class='tuifeitime'>"+tuifeitime+"</td>"+
	        "<td class='netedumoney'>"+netedumoney+"</td>"+
	        "<td class='paytime'>"+paytime+"</td>"+
	        "<td class='shenhe'>"+shenhe+"</td>"+
	        "<td class='caiwunote'>"+caiwunote+"</td>"+
	    "</tr>";
		$("#resourceTable").append(tableItem);
		/* 初始化时间插件*/
		$('#dit'+(index+1)).datetimepicker({
			lang: 'ch',
			format:"Y-m-d", //格式化日期
			validateOnBlur:false //删除选中的时间
		});
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
$('#quxiao').on('click', function() {
	$('.themisWrap').css('display','none');
});
//筛选
var filterData;
function filter(){
	$('#pagination').html("");
	clearDate()
	var arriveStartTime = $('#arriveStartTime').val()
	var arriveEndTime = $('#arriveEndTime').val()
	var remitWay = $('#remitWay').val()
	var studentName = $('#studentName').val()
	var idCard = $('#idCard').val()
	var phone = $('#phone').val()
	var belongName = $('#belongName option:selected').val()
	var courseid = $('#course option:selected').val()
	filterData = {
		userid: userid,
    	deptid:deptid,
    	roleid:roleid,
		arriveStartTime:arriveStartTime,
		arriveEndTime:arriveEndTime,
		remitWay:remitWay,
		studentName:studentName,
		idCard:idCard,
		phone:phone,
		belongid:belongName,
		courseid:courseid,
	}
	$('#filter').css('display','none');
	load(filterData,'../report/queryStudentscaiwu.do',1);
}
//手机查询
function phonesearch(){
	var search = $('#search').val();
	$('#pagination').html('');
	var data = {        
    	userid: userid,
    	deptid:deptid,
    	roleid:roleid,	    	
    	phone:search
    }
	if(search=="" || search == null){
		load(loadData,'../report/queryStudentscaiwu.do');
	}else {
		phoneFill (data,'../report/queryStudentscaiwu.do')
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
			fillTable(list,currentPage);
		}
	})
}
/*批量导出资源*/
function exportExcel(){
	var studentId = []; //获取选中资源的ID  
	var exportExcel = "export_excel";	
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});	
	studentIds = studentId.join(',')
	if(studentId == 0){
		if(filterData != null){
			execlurl(filterData)
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
			deptid:deptid,
			studentIds:studentIds
		};
		execlurl(dataParams)
	}
};
function execlurl(dataParams){
var params = $.param(dataParams);
	
	var url = '../matchinfo/excelExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"></form>').appendTo('body').submit().remove();
	
	delete dataParams[exportExcel];
}

/*控制可编辑*/
var a = 0;
function onoff(){
	var tag = $("#onoff"); 
	if(a == 1){		
		tag.html("开");
		
		$('#resourceTable input[type="text"]').attr("disabled",true);
		a = 0;
	}else{
		
		tag.html("关"); 
		$('#resourceTable input[type="text"]').attr('disabled',false);
		a = 1;
	} 
}

/*保存学员收款方式和收款日期内容*/
function editsave(){
	var studentId = [];	
	$("#resourceTable input[type='checkbox']:not(:first)").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});
	
	var time = [];	
	$('#resourceTable  input[class^="changearrivetime"]').each(function() {
		var txt = $(this).val().trim();		
		time.push(txt);
	});
	var way = [];	
	$('#resourceTable  input[class^="changeremitWay"]').each(function() {
		var txt = $(this).val().trim();		
		way.push(txt);
	});
	var datas=[]; 
    for (i = 0; i < time.length; i++) {
         (function(i){    		 
        	 var data = {};    
             data["studentId"] = studentId[i];            
             data["arrive_time"] = time[i];
             data["remitWay"] = way[i];
             datas.push(data);
        })(i);//调用时参数 
    }  
    var tag = $("#onoff").html(); 
   if(tag=="关"){
	   $.winTip({
			title: "提示~~~",
			msg: "请将编辑功能关闭",
			src:"./system/img/tishi.png"
		});
	   return false;
   }else {	   
	   $.ajax({
		   type: "POST",
		   url: '../matchinfo/caiwuAllStudents.do',
		   data: {jsonlist:JSON.stringify(datas)},
		   success: function(data) {
			   if(data=="success"){
				   window.location.reload();				
			   }else{
				   
			   }			
		   }
	   });
   } 
   
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

