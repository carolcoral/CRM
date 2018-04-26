//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
/*展示信息*/
var firstload = {
		
}
load(firstload,'../matchinfo/queryNetworkEduMoneyBycaiwu.do',1)
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
		searchParam: {},
		beforeSend: function () {
		},
		success: function (data) {
			var list = data.rows;  
			var currentPage = data.currentPage;
			var total = "<span style='vertical-align:30px;display:block'>共"+data.total+" 条</span>";
			//清空数据 
			clearDate(); 
			//插入头部
			$("#networkTable").append(tableHeader); 
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}
var tableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='all' /></th>"+
		"<th>序号</th>"+        
		"<th>学员姓名</th>"+
		"<th>收款金额</th>"+
		"<th>汇款方式</th>"+        
		"<th>收款日期</th>"+
		"<th>代汇款人</th>"+
		"<th>身份证</th>"+
		"<th>手机</th>"+
		"<th>LCW用户名</th>"+
		"<th>LCW密码</th>"+
		"<th>付网络培训款</th>"+
		"<th>地区</th>"+
		"<th>支付日期</th>"+
		"<th>行政备注</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#networkTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var studentId = array. studentId|| "";
		var resourceId = array. resourceId|| "";
	    var studentName = array.studentName|| "";
	    var arrive_money = array.arrive_money|| "";
	    var remitWay = array.remitWay|| "";
	    var arrive_time = array.arrive_time|| "";
	    var remituser = array. remituser|| "";
		var idCard = array. idCard|| "";
	    var phone = array.phone|| "";
	    var LCWname = array.LCWname|| "";
	    var LCWpassword = array.LCWpassword|| "";
	    var netedumoney = array.netedumoney|| "";
	    var address = array.address|| "";
	    var paytime = array.paytime|| "";
	    var xingzhengNote = array.xingzhengNote|| "";	    
	    
	    tableItem ="<tr data-id="+studentId+">"+
		    "<td><input type='checkbox' class='"+studentId+"'/></td>"+               
	        "<td class='serialnumber'>"+(index+1)+"</td>"+        
	        "<td class='studentName'>"+studentName+"</td>"+
	        "<td class='arrive_money'>"+arrive_money+"</td>"+
	        "<td class='remitWay'>"+remitWay+"</td>"+       
	        "<td class='arrive_time'>"+arrive_time+"</td>"+
	        "<td class='remituser'>"+remituser+"</td>"+
	        "<td class='idCard'>"+idCard+"</td>"+
	        "<td class='phone'>"+phone+"</td>"+
	        "<td class='LCWname'>"+LCWname+"</td>"+
	        "<td class='LCWpassword'>"+LCWpassword+"</td>"+
	        "<td class='netedumoney'>"+netedumoney+"</td>"+
	        "<td class='address'>"+address+"</td>"+
	        "<td class='paytime'><input type='text'value='"+paytime+"' id='dit"+(index+1)+"' class='changepaytime' disabled='disabled'/></td>"+
	        "<td class='xingzhengNote'>"+xingzhengNote+"</td>"+      
	    "</tr>";
		$("#networkTable").append(tableItem);
		/* 初始化时间插件*/
		$('#dit'+(index+1)).datetimepicker({
			lang: 'ch',
			format:"Y-m-d", //格式化日期
			validateOnBlur:false //删除选中的时间
		});
	}); 
}
function namesearch (){
	$('#pagination').html('');
	var phone = $('#searchname').val().trim();
	data = {        
		phone:phone
    }
	if(phone=="" || phone == null){
		load(firstload,'../matchinfo/queryNetworkEduMoneyBycaiwu.do')
	}else {
		phoneFill (data,'../matchinfo/queryNetworkEduMoneyBycaiwu.do')
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
/*控制可编辑*/
var a = 0;
function onoff(){
	var tag = $("#onoff"); 
	if(a == 1){		
		tag.html("开");
		
		$('#networkTable input[type="text"]').attr("disabled",true);
		a = 0;
	}else{
		
		tag.html("关"); 
		$('#networkTable  input[type="text"]').attr('disabled',false);
		a = 1;
	} 
}

/*保存网络培训费填写内容*/
function editsave(){
	var studentId = [];	
	$("#networkTable input[type='checkbox']:not(:first)").each(function() {
		var boxid = $(this).attr('class');		
		studentId.push(boxid);
	});
	
	var time = [];	
	$('#networkTable  input[class^="changepaytime"]').each(function() {
		var txt = $(this).val().trim();		
		time.push(txt);
	});
	
	var datas=[]; 
    for (i = 0; i < time.length; i++) {
         (function(i){    		 
        	 var data = {};    
             data["studentId"] = studentId[i];            
             data["paytime"] = time[i];
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
		   url: '../matchinfo/agreePayDate.do',
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

