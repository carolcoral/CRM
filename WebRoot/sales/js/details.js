$(window.parent.document).find("#detailiframe").load(function(){
    var main = $(window.parent.document).find("#detailiframe");
    var thisheight = $(document).height();
    main.height(thisheight);
});
var todaydate = new Date();
var todayStarttime = todaydate.getFullYear() + "-" + (todaydate.getMonth() + 1) + "-"+ todaydate.getDate()+ " "+todaydate.getHours()+":"+todaydate.getMinutes()+":"+todaydate.getSeconds();
$('#dealtime').val(todayStarttime)
/*获取课程*/
queryCourse('#courseid');
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
	        	var checkbox = '<label class="checkbox-inline">' + '<input type="checkbox" value="' + subjectid + '">' + subjectname + '</label>';
	        	clearname.append(checkbox);
		    });
	   }
	});
}
var mydate = new Date();
	var hftime = mydate.getFullYear() + "-" + (mydate.getMonth() + 1) + "-"+ mydate.getDate()+" "+mydate.getHours()+":"+ mydate.getMinutes();
	$('#timeid').html(hftime);
	
var userid = $("#userid").val();//用户Id
var deptid = $("#deptid").val();//部门Id
var roleid = $("#roleid").val();//角色Id
var resourceId = $('#resourceId').val();//资源Id
var showphone = $('#resourcephone').val();
var deldata = null;
/* 初始化时间插件*/
$('#dealtime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:s", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#nextVisitTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:s", //格式化日期
	validateOnBlur:false //删除选中的时间
});
/*增加回访记录*/
/*function addrecord(){	
	var visitRecord = $('#visitRecord').val();
	var course = $('#course option:selected').attr('data-id');
	var resourceLevel = $("#resourceLevel option:selected").val();
	var recoredlength = $('#recoredtable tr').length;
	if((resourceLevel == "" || resourceLevel == null) ){
			$('#visitRecordError').html("请判定资源等级！");
			return false;
	}else{
		$('#resourceLevelError').html("");
	}
	if(visitRecord===""||visitRecord==null){
		$('#visitRecordError').html("回访记录不可为空");
		return false;
	}else {
		$('#visitRecordError').html("");
	}
	$.ajax({        			
	   type: "POST",
	   data: {
		   userid:userid,
		   courseid:course,
		   resourceId:resourceId,
		   visitRecord:visitRecord
	   },
	   url: '/resource/addResourceVisitrecord.do',
	   success: function(data) {
		   if(data=="success"){
			  var tr ="<tr>"+
				"<td class='text-primary col-md-1'>"+hftime+"</td>"+
				"<td class='text-primary col-md-6'>"+visitRecord+"</td>"+
				"</tr>"
		  	$('#recoredtable').append(tr);
			$('#visitRecord').val('');
		   }		  
	   }
	});
	
}*/
/*添加成交记录*/
function check(dealtime,courseid,dealprice,ishavenetedu,netedumoney,remituser,idCard,email,phone,address){
	 var reg = /^(([0-9]+)|([0-9]+\.[0-9]{1,2}))$/;  
	 var flag = true;
	 if(dealtime==""||dealtime==null){
		 $('#dealtimeError').html("成交时间不能为空");
		 flag = false;
	 } else {
		 $('#dealtimeError').html("")
	 }	
	 
	 if(courseid==""||courseid==null){
		 $('#courseError').html("成交课程不能为空");
		 flag = false;
	 } else {
		 $('#courseError').html("")
	 }	
	 
	 if(dealprice==""||dealprice==null){  
		 $('#dealpriceError').html("成交金额不能为空");
		 flag = false;
	 }else if(!reg.test(dealprice)){  
		 $('#dealpriceError').html("请输入大于0的数字");
		 flag = false;
	 }else{
		 $('#dealpriceError').html("");
	 }
	 
	 if (remituser==""||remituser==null){		 
		 $('#remituserError').html("代汇款人不能为空");
		 flag = false;
	 }else {
		 $('#remituserError').html("")
	 }
	 
	 if(ishavenetedu=="1"){
		 if(netedumoney =="" || netedumoney == null){ 
			 $('#netedumoneyError').html("金额不能为空");
			 flag = false;
		 }else if(!reg.test(netedumoney)){
			 $('#netedumoneyError').html("请输入数字");
			 flag = false;
		 }else if(netedumoney<0){
			 $('#netedumoneyError').html("金额需大于0");
			 flag = false;
		 }else{
			 $('#netedumoneyError').html("");
		 } 
	 }	
	 
	 if((phone == "" || phone == null) || (idCard == "" || idCard == null) || (email == "" || email == null) || (address == "" || address == null)){
		$('#dealError').html("手机号、身份证号、邮箱、地址不可为空");
		flag = false; 
	 }else {
		$('#dealError').html("");
	 }
	 
	return flag
}
function deal(){
	
	var idCard= $('#idCard').val();	
	var email= $('#email').val();		
	var phone = $('#phone').val();
	var address = $("#address option:selected").val();
	
	var dealtime = $('#dealtime').val();
	var courseid = $('#courseid option:selected').val();
	var subjectid = [];
	$("#subjectid input[type='checkbox']:checked").each(function() {
		var boxid = $(this).val();
		subjectid.push(boxid);
	});
	var dealprice = $('#dealprice').val();
	var ishavenetedu = $("input[name='ishavenetedu']:checked").val();
	var isnetmoney = ishavenetedu;
	var netedumoney = $('#netedumoney').val();
	var preferinfo = $('#preferinfo').val();
	var remituser = $('#remituser').val();
	var isOnlineBuy = $("input[name='isOnlineBuy']:checked").val();
	var onlineBuy = isOnlineBuy;
	subjectids = subjectid.join(',');
	if(isnetmoney == "0"){
		isnetmoney = "无"
	}else if(isnetmoney == "1"){
		isnetmoney = "有"
	}
	if(isOnlineBuy == "0"){
		isOnlineBuy = "否"
	}else if(isOnlineBuy == "1"){
		isOnlineBuy = "是"
	}
	deldata = {
	   userid:userid,                                                
	   resourceId:resourceId,
	   courseid:courseid,
	   dealtime:dealtime,
	   subjects:subjectids,
	   dealprice:dealprice,
	   isOnlineBuy:onlineBuy,
	   ishavenetedu:ishavenetedu,
	   netedumoney:netedumoney,
	   preferinfo:preferinfo,
	   remituser:remituser
	}
	if(check(dealtime,courseid,dealprice,ishavenetedu,netedumoney,remituser,idCard,email,phone,address)){
		$('#network').css('display','block');
		center($('#networkFade'))
		$('#isnetmoney').html(isnetmoney);
		$('#netmoney').html(netedumoney+"元");
	}
}
function dernetwork(){
	var phone = $('#phone').val();

	$.ajax({        			
	   type: "POST",
	   url: '../resource/addDealrecord.do',
	   data: deldata,
	   success: function(data) {
		   if(data=="success"){
			   $('#network').css('display','none');
			   $('#dealtip').css('display','block');
			   center($('#dealtipFade'))
			   $('#showphone').html(phone);
		   }else if(data=="-1"){
			  alert("匹配失败，请联系行政部");
		   }
	   }
	});	
}
/*保存详情*/
function saveDetails(){
	var resourceName = $('#resourceName').val();
	var idCard= $('#idCard').val();	
	var email= $('#email').val();		
	var phone = $('#phone').val();
	var course = $("#course option:selected").val();
	var tel = $('#tel').val();
	var weixin = $('#weixin').val();
	var qq = $('#qq').val();	
	var address = $("#address option:selected").val();
	var nextVisitTime = $('#nextVisitTime').val();	
	var xiaoShouNote = $('#xiaoShouNote').val();	
	var resourceLevel = $("#resourceLevel option:selected").val();
	var recoredlength = $('#recoredtable tr').length;
	var visitRecord = $('#visitRecord').val();
	if(recoredlength == 0){
		if((resourceLevel == "" || resourceLevel == null) || (visitRecord == "" || visitRecord == null)){
			$('#visitRecordError').html("资源等级不能为空或回访记录不可为空");
			return false;
			
		}else{
			$('#visitRecordError').html("");
		}
	}else{
		
	}
	
	$.ajax({        			
	   type: "POST",
	   data: {
		   userid:userid,
		   deptid:deptid,
		   resourceId:resourceId,
		   resourceName:resourceName,
		   idCard:idCard,
		   email:email,
		   phone:phone,
		   courseid:course,
		   phone:phone,
		   tel:tel,
		   weixin:weixin,
		   qq:qq,
		   address:address,
		   nextVisitTimes:nextVisitTime,
		   xiaoShouNote:xiaoShouNote,
		   resourceLevel:resourceLevel,
		   visitRecord:visitRecord
	   },
	   url: '../resource/addResource.do',
	   success: function(data) {
		   	if(data=="success"){
		   		window.location.reload();
		   }else if(data=="8"){
			   $.winTip({
					title: "提示~~~",
					msg: "当前用户没有操作权限",
					src:"./system/img/tishi.png"
				});
		   }
		   
	   }
	});
}


function reload(){
	window.location.href="../sales/resourcesMang.do";
}
$('#quxiao').on('click',function(){
	$('#dealtip').css('display','none');
})
// 居中
function center(obj) {
	var screenHeight = $(window).height();
    var scrolltop = GetScrollTop();//获取当前窗口距离页面顶部高度
    var objTop = scrolltop+20;

    obj.css({top: objTop + 'px','display': 'block'});
    //浏览器窗口大小改变时
    $(window).resize(function() {
        screenHeight = $(window).height();
        scrolltop = scrolltop = GetScrollTop();

        objTop = scrolltop+20;

        obj.css({top: objTop + 'px','display': 'block'});
    });
    //浏览器有滚动条时的操作、
    $(window).scroll(function() {
        screenHeight = $(widow).height();
        scrolltop = scrolltop = GetScrollTop();
        objTop = scrolltop+20;

        obj.css({top: objTop + 'px','display': 'block'});
    });

}
function GetScrollTop() {
	return (top.document.documentElement. scrollTop || 0) + (top.document.body.scrollTop || 0);

}