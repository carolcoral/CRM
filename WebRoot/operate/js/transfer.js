/* 初始化时间插件*/
$('#createStarttime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#createEndtime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:i", //格式化日期
	validateOnBlur:false //删除选中的时间
});
/*获取销售姓名*/
saleName('#sourceName');
function saleName(saleId){
	$.ajax({
		type: "GET",
		url: '../resource/queryAllDirectors.do',
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

var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

//数据分页
var firstload = {
		
}
load('pagination',firstload,'../resource/queryTransferRecord.do',1)
function load(tag,data,url,currentPage){
	$('#'+tag).sjAjaxPager({
		type: "GET",
		url: url,
		currentPage:currentPage,
		pageSize: 15, 
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
			clearDate()
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}
//清空数据 
function clearDate(){ 
    $("#transferTable tr:not(:first)").html("");
} 
//填充数据 
function fillTable(list,currentPage){
	$.each(list, function(index, array) {
		//循环获取数据
		var transferrecordId = array.transferrecordId|| "";
		var create_time = array.create_time|| "";
		var operationName = array.operationName|| "";
	    var phone = array.phone|| "";
	    var tel = array.tel|| "";
	    var sourceName = array.sourceName|| "";
	    var belongName = array.belongName|| "";
	    var state = array.state|| "";
	    var resourceLevelBefore = array.resourceLevelBefore|| "";
	    var resourceLevelAfter = array.resourceLevelAfter|| "";
	    if (0 == state) {
	    	state = '未分配 ';
	    }
	    else if (1 == state) {
	    	state = '已分配'
	    }
	    else if (2 == state) {
	    	state = '已处理'
	    }
	    tableItem ="<tr data-id="+transferrecordId+">"+
	        "<td class='serialnumber'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td>"+create_time+"</td>"+
	        "<td>"+operationName+"</td>"+
	        "<td>"+phone+"</td>"+
	        "<td>"+tel+"</td>"+
	        "<td>"+sourceName+"</td>"+
	        "<td>"+belongName+"</td>"+
	        "<td>"+state+"</td>"+
	        "<td>"+resourceLevelBefore+"</td>"+
	        "<td>"+resourceLevelAfter+"</td>"+
	    "</tr>";
		$("#transferTable").append(tableItem);
	}); 
}
/*筛选*/
var filterData;
function filter(){
	$('#pagination').html('');
	var createStarttime = $('#createStarttime').val();
	var createEndtime = $('#createEndtime').val();
	var sourceName = $('#sourceName option:selected').val()
	filterData = {
		createStarttime:createStarttime,
		createEndtime:createEndtime,
		operationId:sourceName
	}
	clearFilter();
	load('pagination',filterData,'../resource/queryTransferRecordBysceen.do',1)
}
function clearFilter(){
	$('#filter').css('display','none')
	$('#createStarttime').val('');
	$('#createEndtime').val('');
	$('#sourceName option').removeAttr("selected"); 
}
/*查询*/
function query(){
	var queryval = $('#phone').val();
	$("#pagination").html('');
	clearDate()
	phonedata = {
			phone:queryval,
			tel:queryval
	}
	if(queryval == '' || queryval == null){
		load('pagination',firstload,'../resource/queryTransferRecord.do')
	}else {
		 phoneFill(phonedata,'../resource/queryTransferRecord.do')
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
			fillTable(list,currentPage);
		}
	})
}
/*批量导出资源*/
function excelExport(){		
	var transferrecordId = [];	
	var exportExcel = "export_excel";	
	
	if(filterData != null){
		$.ajax({  //获取所以筛选以后资源的ID      			
			type: "GET",
			data: filterData,
			async:false,
			url: '../resource/queryExportTransferRecordBysceen.do',
			success: function(data) {
				var dataObj=eval("("+data+")");//转换为json对象	
				var list = dataObj.rows;
				$.each(list, function(index, array) {
					transferrecordId.push(array.transferrecordId);
				});	 	  
			}
		});
		transferrecordIds = transferrecordId.join(',');
		if (transferrecordId == 0){
			$.winTip({
				title: "提示~~~",
				msg: "当前条件下没有可以导出的数据",
				src:"./system/img/tishi.png"
			});
		}else {
			var dataParams = {
		    	deptid:deptid
			};
			execlurl(dataParams,transferrecordIds)
		}
	}else {
		$.winTip({
			title: "提示~~~",
			msg: "由于资源太多请筛选导出",
			src:"./system/img/tishi.png"
		});
	}	
}
function execlurl(dataParams,transferrecordIds){
	var params = $.param(dataParams);
	
	var url = '../resource/exceltransferrecordExport.do'+"?"+params;
	
	$('<form method="post" action="' + url + '"><input type="text" name="transferrecordIds" value="'+transferrecordIds+'"/></form>').appendTo('body').submit().remove();
	
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