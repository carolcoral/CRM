/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
/* 初始化时间插件*/
$('#timesearch').datetimepicker({
	lang: 'ch',
	format:"Y-m-d",      //格式化日期
	validateOnBlur:false //删除选中的时间
});
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
//初次加载
var first = {
	userid: userid,
	deptid:deptid,
	roleid:roleid    
}
firstload(first,'../resource/queryResourceTel.do',1)
function firstload(data,url,currentPage){
	$('#pagination').sjAjaxPager({
		type: "GET",
		url: url,
		pageSize: 15,
	    currentPage:currentPage,
		cache:false,
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
			$('#totalStudent').html(data.total)
			//清空数据 
			clearDate(); 
			//插入头部
			fillTable(list,currentPage);
		},
		complete: function () {
		}
	});
}

//清空数据 
function clearDate(){ 
    $('#telTable tr:not(:first)').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var createrName = array. createrName|| "";
		var todaytotal = array. todaytotal|| "";
	    var acount = array.acount|| "";
	    var bcount = array.bcount|| "";
	    var ccount = array.ccount|| "";
	    var dcount = array.dcount|| "";
	    var wclcount = array.wclcount|| "";
	    var yclcount = array.yclcount|| "";	
	    
	    tableItem ="<tr data-id="+createrName+">"+
	        "<td>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td>"+createrName+"</td>"+  
	        "<td>"+todaytotal+"</td>"+               
	        "<td>"+acount+"</td>"+
	        "<td>"+bcount+"</td>"+
	        "<td>"+ccount+"</td>"+
	        "<td>"+dcount+"</td>"+
	        "<td>"+wclcount+"</td>"+
	        "<td>"+yclcount+"</td>"+
	    "</tr>";
		$("#telTable").append(tableItem);
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
function timesearch (){
	var timesearch = $('#timesearch').val();
	var timedata = {
			userid: userid,
			deptid:deptid,
			roleid:roleid,
			timesearch:timesearch
		}
		firstload(timedata,'../resource/queryResourceTel.do',1)
}
var iframe = $(window.parent.document).find("#iframepage");
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
