//全选
$(document).on('click','.saleall',function() {
	if(this.checked) {
		$("#saleTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#saleTable input[type='checkbox']").prop("checked", false);
	}
});
//全选
$(document).on('click','.financeall',function() {
	if(this.checked) {
		$("#financeTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#financeTable input[type='checkbox']").prop("checked", false);
	}
});
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
//清空数据 
function clearDate(tag){ 
    $('#'+tag).html("");
}  
/*展示销售信息*/
$.ajax({
	type: "GET",
	url: '../student/queryStudnetMatchs.do',
	cache: false,
	success: function(data) {
		
		var dataObj=eval("("+data+")");//转换为json对象			   
		var list = dataObj.rows;
    	//清空数据 
	    clearDate('saleTable'); 
        //插入头部
        $("#saleTable").append(saletableHeader); 
	    fillSaleTable(list);	   
	}
})
/*销售按姓名查询
*/
function searchSalename(){
	var searchSalename =$('#searchSalename').val().trim();
	$.ajax({
		type: "GET",
		url: '../student/queryStudnetMatchs.do',
		cache: false,
		data: {
			studentName:searchSalename,
			remituser:searchSalename
		},
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;
	    	//清空数据 
		    clearDate('saleTable'); 
	        //插入头部
	        $("#saleTable").append(saletableHeader); 
		    fillSaleTable(list);		    
		}
	})
}
var saletableHeader = "<tr class='henglan'>"+
		"<th class='serialnumber' style='width: 60px;'>序号</th>"+        
		"<th class='courseName' style='width: 80px;'>课程名称</th>"+ 
		"<th class='subjectname' style='width: 80px;'>优惠信息</th>"+    
		"<th class='subjectname'>科目名称</th>"+       
		"<th class='studentName' style='width: 80px;'>学员姓名</th>"+           
		"<th class='sumPayMoney' style='width: 80px;'>总金额</th>"+
		"<th class='remituser' style='width: 80px;'>汇款人姓名</th>"+        
		"<th><input type='checkbox' class='saleall' /></th>"+
	"</tr>";

//填充数据 
function fillSaleTable(list){
	$.each(list, function(index, array) {		
		//循环获取数据
		var resourceId =array.resourceId || "";
		var studentId = array.studentId|| "";
		var courseName = array.courseName|| "";
	    var subjectname = array.subjectname|| "";
	    var studentName = array.studentName || "";
	    var sumPayMoney = array.sumPayMoney|| "";
	    var remituser = array.remituser|| "";
	    var preferinfo = array.preferinfo || "";
	    
	    tableItem ="<tr data-id="+studentId+">"+
	    "<td class='serialnumber'>"+(index+1)+"</td>"+        
        "<td class='courseName'>"+courseName+"</td>"+
        "<td class='preferinfo'>"+preferinfo+"</td>"+
        "<td class='subjectname'>"+subjectname+"</td>"+
        "<td class='studentName'>"+studentName+"</td>"+   
        "<td class='sumPayMoney'>"+sumPayMoney+"</td>"+
        "<td class='remituser'>"+remituser+"</td>"+ 
        "<td><input type='checkbox' class='"+studentId+"' data-id='"+resourceId+"' data-remituser='"+remituser+"'/></td>"+
	    "</tr>";
		$("#saleTable").append(tableItem);
		 $("#saletotal").html("共"+list.length+" 条");
	}); 
}


/*展示财务信息*/
$.ajax({
	type: "GET",
	url: '../student/queryMatchinfo.do',
	cache: false,
	success: function(data) {
		var dataObj=eval("("+data+")");//转换为json对象			   
		var list = dataObj.rows;
    	//清空数据 
	    clearDate('financeTable'); 
        //插入头部
        $("#financeTable").append(financetableHeader); 
	    fillFinanceTable(list);
	   
	}
})
/*财务按姓名查询
*/
function searchFinancename(){
	var searchFinancename =$('#searchFinancename').val().trim();
	$.ajax({
		type: "GET",
		url: '../student/queryMatchinfo.do',
		cache: false,
		data: {
			matchname:searchFinancename
		},
		success: function(data) {
			var dataObj=eval("("+data+")");//转换为json对象			   
			var list = dataObj.rows;
	    	//清空数据 
		    clearDate('financeTable'); 
	        //插入头部
	        $("#financeTable").append(financetableHeader); 
		    fillFinanceTable(list);		    
		}
	})
}
var financetableHeader = "<tr class='henglan'>"+
		"<th><input type='checkbox' class='financeall' /></th>"+   
		"<th class='serialnumber'>序号</th>"+        
		"<th class='matchname'>学员姓名</th>"+           
		"<th class='payMoney'>收款金额</th>"+  
		"<th class='receiveTime'>收款日期</th>"+       
		"<th class='payType'>汇款方式</th>"+        
		"<th class='matchnote'>备注</th>"+
	"</tr>";

//填充数据 
function fillFinanceTable(list){ 
	$.each(list, function(index, array) {		
		//循环获取数据
		var matchInfoId =array.matchInfoId || "";
		var matchname = array.matchname|| "";
	    var payMoney = array.payMoney|| "";
	    var receiveTime = array.receiveTime || "";
	    var payType = array.payType|| "";
	    var matchnote = array.matchnote|| "";	   
	    if(receiveTime == ""){
	    	receiveTime = "";
	    }else {
	    	receiveTime = /\d{4}-\d{1,2}-\d{1,2}/g.exec(receiveTime);	    	
	    }
	    tableItem ="<tr data-id="+matchInfoId+">"+
		    "<td><input type='checkbox' class='"+matchInfoId+"' data-matchinfoName='"+matchname+"'/></td>"+
	        "<td class='serialnumber'>"+(index+1)+"</td>"+        
	        "<td class='matchname'>"+matchname+"</td>"+
	        "<td class='payMoney'>"+payMoney+"</td>"+
	        "<td class='arrive_time'>"+receiveTime+"</td>"+ 
	        "<td class='payType'>"+payType+"</td>"+  
	        "<td class='matchnote'>"+matchnote+"</td>"+ 
	    "</tr>";
		$("#financeTable").append(tableItem);
		 $("#financetotal").html("共"+list.length+" 条");
	}); 
}

/*匹配到账*/
var studentId = [];
var resourceId= [];
var remituser = [];
var matchInfoId = [];
var matchinfoName = [];
function deterMatch(){
	studentId = [];
	resourceId= [];
	matchInfoId = [];
	matchInfoId = [];
	matchinfoName = [];
	$("#saleTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');	
		var resid = $(this).attr('data-id');
		var dataremituser = $(this).attr('data-remituser');
		studentId.push(boxid);	
		resourceId.push(resid);	
		remituser.push(dataremituser);	
	});	
	$("#financeTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var datamatchname = $(this).attr('data-matchinfoName');
		matchInfoId.push(boxid);
		matchinfoName.push(datamatchname);
	});
	
	if(studentId.length == 0){
		 $.winTip({
			title: "提示~~~",
			msg: "销售至少选择一条",
			src:"./system/img/tishi.png"
		});	
		return false;
	}
	
	if(matchInfoId.length == 0){
		 $.winTip({
				title: "提示~~~",
				msg: "匹配财务至少选择一条",
				src:"./system/img/tishi.png"
		});	
		return false;
	}
	
	if (studentId.length != matchInfoId.length){
		$('.themisWrap').css('display','block')
	}else {
		matchdeter();
	}	
}
function matchdeter(){
	studentIds = studentId.join(',');
	resourceIds = resourceId.join(',');	
	matchInfoIds = matchInfoId.join(',');
	remitusers = remituser.join(',');	
	matchinfoNames = matchinfoName.join(',');
	$.ajax({
		type: "POST",
		url: '../student/matchMoney.do',
		data: {
			userid:userid,
			resourceIds:resourceIds,
			studentIds:studentIds,
			matchInfoIds:matchInfoIds,
			remitusers:remitusers,
			matchinfoNames:matchinfoNames
		},
		success: function(data) {
			console.log(data)
			if(data=="success"){				   
				 window.location.reload();	
	 		} else if(data=="13"){
				$.winTip({
					title: "提示~~~",
					msg: "成交金额+网络培训费 != 收款金额，不能进行确认对账",
					src:"./system/img/tishi.png"
				});					
			} else if(data=="14"){				   
				 $.winTip({
					title: "提示~~~",
					msg: "代汇款人为空，不能进行匹配到账",
					src:"./system/img/tishi.png"
				});		
	 		} else if(data=="15"){				   
				 $.winTip({
					title: "提示~~~",
					msg: "两边未有匹配成功学员数据",
					src:"./system/img/tishi.png"
				});		
	 		}else {
	 			 
	 		}
		}
	})
}
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none')
})

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

