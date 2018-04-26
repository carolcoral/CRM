/* 初始化时间插件*/
$('#taskStartTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#taskEndTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d", //格式化日期
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
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id

//初次加载
var firstload = {

}
load(firstload,'../matchinfo/queryMatchinfo.do',1);
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
	    	var total = "<span style='vertical-align:30px;display:block'>共"+data.total+" 条</span>";
	    	//清空数据 
		    clearDate(); 
	       //插入头部
	       $("#macthTable").append(tableHeader); 
		   fillTable(list,currentPage);
	    },
	    complete: function () {
	    }
	});
}

var tableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='all'/></th>"+
		"<th class='serialnumber'>序号</th>"+				
		"<th class='matchname'>姓名</th>"+
		"<th class='payMoney'>收款金额</th>"+
		"<th class='receiveTime'>收款日期</th>"+
		"<th class='payType'>汇款方式</th>"+
		"<th class='ismatch'>是否匹配</th>"+
		"<th class='matchnote'>备注</th>"+
	"</tr>";
//清空数据 
function clearDate(){ 
    $('#macthTable').html("");
}  
//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		//循环获取数据
		var matchInfoId = array. matchInfoId|| "";
		var matchname = array. matchname|| "";
	    var payMoney = array.payMoney|| "";
	    var receiveTime = array.receive_time|| "";
	    var payType = array.payType|| "";
	    var matchnote = array.matchnote|| "";
	    var ismatch = array.ismatch|| "";
	    
	    if(ismatch=="0"){
	    	ismatch = "未匹配 ";
	    }else if(ismatch=="1"){
	    	ismatch = "已匹配";
	    }
	    
	    tableItem ="<tr data-id="+matchInfoId+">"+
	        "<td><input type='checkbox' class='"+matchInfoId+"'/></td>"+
	        "<td class='matchname'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='matchname'>"+matchname+"</td>"+
	        "<td class='payMoney'>"+payMoney+"</td>"+
	        "<td class='receiveTime'>"+receiveTime+"</td>"+
	        "<td class='payType'>"+payType+"</td>"+
	        "<td class='ismatch'>"+ismatch+"</td>"+
	        "<td class='matchnote'>"+matchnote+"</td>"+	       
	    "</tr>";
		$("#macthTable").append(tableItem);
	}); 
}

//筛选
function matchfilter(){
	$('#pagination').html('');
	var taskStartTime = $('#taskStartTime').val();
	var taskEndTime = $('#taskEndTime').val();
	var matchname = $('#matchname').val();
	var payType = $('#payType').val();
	filterData = {
		receiveStartTime: taskStartTime,
    	receiveEndTime:taskEndTime,
    	matchname:matchname,
    	payType:payType
	}
	$('#matchfilter').css('display','none');
	load(filterData,'../matchinfo/queryMatchinfo.do',1);
}
//查重
function repeatMatchinfo(){
	$('#pagination').html('');
	load(firstload,'../matchinfo/queryRepeatMatchinfo.do',1);
}
//导入Excel		
function importExcel(){
	$('#importExcel').html("正在导入· · ·");
	$('#importExcel').removeAttr("onclick");
	$.ajax({
		url: '../matchinfo/excelImport.do',
		type: 'POST',
		cache: false,
		data: new FormData($('#uploadForm')[0]),
		processData: false,
		contentType: false,
		success: function(data) {			
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;			
			if(list.state =="success"){
				$('#daotip').css('display','block')
				$('.successtip').html("成功导入："+list.total+"条");				
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

//按姓名查询
function namesearch(){
	$('#pagination').html('');
	var searchname = $('#searchname').val();	
	data = {        
		matchname:searchname
    }
	if(searchname=="" || searchname == null){
		load(firstload,'../matchinfo/queryMatchinfo.do');
	}else {
		phoneFill (data,'../matchinfo/queryMatchinfo.do')
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
			$("#macthTable").append(tableHeader); 
			fillTable(list,currentPage);
		}
	})
}
//删除资源
var matchInfoId = [];
function showdelResource(){
	matchInfoId = [];//清空上次的修改条数
	$("#macthTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		matchInfoId.push(boxid);
	});		
	if(matchInfoId.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	} else {
		$('#deltip').css('display','block');
	}
}
//确定删除
function delResource() {
	$('.themisWrap').css('display','none');
	var matchInfoIds = matchInfoId.join(",");
	$.ajax({        			
	   type: "POST",
	   data: {
		   userid:userid,
		   matchInfoIds:matchInfoIds
		},
	   url: '../matchinfo/deleteMatchinfos.do',
	   success: function(data) {
		   if(data=="success"){				   
			   window.location.reload();		
		   }else if(data=="0"){
			   $.winTip({
					title: "提示~~~",
					msg: "参数为空",
					src:"./system/img/tishi.png"
				});
		   }
		}
	});		
};
function quxiao(){
	$('#deltip').css('display','none');
}