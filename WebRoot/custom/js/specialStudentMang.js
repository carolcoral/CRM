var lastindex=($('#resourceTable td').length/($('#resourceTable tr').length-1))-1;  
var lineindex=($('#resourceTable tr').length-1);  
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
/****定义列按钮****/ 
function rowDfine(tag){
	$('#row-def').show();
	$('#show').empty();  
    count=$('#'+tag).find('th').length;  
     for(i=0;i<count;i++){ 
    	 $('#show').append('<option value='+$('#'+tag).find('th:eq('+i+')').attr('class')+'>'+$('#'+tag).find('th:eq('+i+')').text()+'</option>')            
     } 
}
/****隐藏按钮****/
function leftarrow(tag){
     value=[];  
    $('#show option:selected').each(function(){  
        value.push($(this).val());
        $(this).remove();
    });
    
}                             
/****显示按钮****/      
 
function rightarrow(){
    value=[];
     $('#hide option:selected').each(function(){  
         value.push($(this).val());         
     });
     for(i=0;i<value.length;i++){  
         if(value[i]!=null){  
            $('.'+value[i]).css('display','');
            $('#show').append($('#hide option:selected'));  
        }                      
        else{  
             break;  
         }  
     }         
}             
/****上移按钮****/  
function uparrow(){ 	 
     value=[];  
     $('#show option:selected').each(function(){  
         value.push($(this).val());  
     });  
     for(i=0;i<value.length;i++){         
    	 rowindex=$('#show option:selected').index();//获取当前的索引位置
    	 if(rowindex!=0){
    		 $('#show option:eq('+rowindex+')').insertBefore($('#show option:eq('+(rowindex-1)+')'));   
    	 }
     }     
};  
/****下移按钮****/  
function downarrow(){
    value=[];  
     $('#show option:selected').each(function(){  
         value.push($(this).val());  
     });  
     for(i=0;i<value.length;i++){  
		 rowindex=$('#show option:selected').index();//获取当前的索引位置
		 totalindex=($('#show option').length-1);
	     if(rowindex!=totalindex){ 
	    	 $('#show option:eq('+rowindex+')').insertAfter($('#show option:eq('+(rowindex+1)+')'));   
	     }            
    }  
};
//全选
$(document).on('click','.all',function() {
	if(this.checked) {
		$("input[type='checkbox']").prop("checked", true);
	} else {
		$("input[type='checkbox']").prop("checked", false);
	}
});
//确定要选择的列

function deterrow(){
	value=[];	
	$('#show option').each(function(){		           
         var val = $(this).val();
         var text = $(this).text();
         var con = val +"|"+ text;
         value.push(con);
	});
    var cellnamesjson = value.join(',');   
	$.ajax({
		type: "POST",
		url: '../cellinfo/saveOrUpdateCellinfo.do',
		data: {
			userid:userid,
			cellmenu: "special",
			cellnamesjson:cellnamesjson
		},
		cache: false,
		success: function(data) {			
			if(data=="success"){
				$('#diyrow').attr('disabled','disabled');
				$('#row-def').css('display','none');				
				passfillTable();
			}			  
		}
	})
} 
passfillTable()
//展示选择的表头
function passfillTable(){
	$.ajax({
		type: "GET",
	    url: '../cellinfo/queryCellinfo.do',		
		data: {
			userid: userid,
	    	cellmenu: "special"			
		},
		cache: false,
		success: function(data) {
			if(data !="" && data != null){
				$('#passHeader').html('');
				var dataObj=eval("("+data+")");//转换为json对象			   
				var list = dataObj.rows;
			    $.each(list, function(index, array) {
			    	var passsHeader = "<th class='"+array.cellcode+"'>"+array.cellname+"</th>";
			    	$('#passHeader').append(passsHeader);
			    });
			    passsudentTab();			   
			}			  
		}
	})
}

//清空数据 
function clearDate(){ 
	$('#fixedTable tr:not(:first)').remove('');
	$('#passsTable tr:not(:first)').remove('');
}
//展示iframe
function showiframe(studentId,tag,currentPage){
	var dataParams = {
		userid:userid,
		roleid:roleid,
		deptid:deptid,
		studentId:studentId
	}
	$('#backcurrent').val(currentPage)
	var params = $.param(dataParams);
	var url = '../custom/speaialdetails.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
}
passsudentTab(1);
//填充数据
function passsudentTab(currentPage){
	$('#pagination').sjAjaxPager({
		type: "GET",
		url: '../custom/querySpecialStudents.do',
	    pageSize: 20, 
	    currentPage:currentPage,
	    preText: "上一页",
	    nextText: "下一页",
	    firstText: "首页",
	    lastText: "尾页",
	    searchParam: {        
	    	userid:userid,
			deptid: deptid,
			roleid:roleid
	    },
	    beforeSend: function () {
	    },
	    success: function (data) {
	    	clearDate()
	    	var list = data.rows;
	    	var currentPage = data.currentPage;	       
		   fillTable(list,currentPage)
	    },
	    complete: function () {
	    }
	});
}

//填充数据 
function fillTable(list,currentPage){ 
	$.each(list, function(index, array) {
		var all = {};
		var resourceId = array. resourceId|| "";
		var studentId = array. studentId|| "";
	    var studentName = array.studentName|| "";
	    var phone = array.phone|| "";
	    var studentstate =array.studentstate|| "";
	    var studentstateid =array.studentstate|| "";
	    var courseName = array.courseName|| "";
	    var customerName = array.customerName|| "";
	    var idCard = array.idCard|| "";
	    var tel = array.tel|| "";
	    var email = array.email|| "";
	    var company = array.company|| "";
	    var address = array.address|| "";
	    var companyAddr = array.companyAddr|| "";	    
	    var position= array.position|| "";
	    var subjectname = array.subjectname|| "";	   
	    var arrive_money = array.arrive_money|| "";
	    var arrive_time = array.arrive_time|| "";	    
	    var remitWay = array.remitWay|| "";	
	    var LCWname = array.LCWname|| "";	    
	    var LCWpassword = array.LCWpassword|| "";
	    var belongName = array.belongName|| "";	
	    var ispass = array.ispass|| "";
	    var baokaopassword = array.baokaopassword|| "";
	    var banci = array.banci|| "";
	    var customer_time = array.customer_time|| "";
	    var isjieye = array.isjieye|| "";
	    
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
	    
	    if (isjieye == ""||isjieye==null) {            	       
	    	isjieye = "";
	    } 
	    else if ("0" == isjieye) {
	    	isjieye = '否'
	    } 
	    else if ("1" == isjieye) {
	    	isjieye = '是'
	    } 
	    
	    all["studentName"] = studentName;
	    all["phone"] = phone;
	    all["studentstate"] = studentstate;
	    all["courseName"] = courseName;
	    all["customerName"] = customerName;
	    all["idCard"] = idCard;
	    all["tel"] = tel;
	    all["email"] = email;
	    all["companyAddr"] = companyAddr;
	    all["subjectname"] = subjectname;
	    all["arrive_money"] = arrive_money;
	    all["arrive_time"] = arrive_time;
	    all["remitWay"] = remitWay;
	    all["LCWname"] = LCWname;
	    all["LCWpassword"] = LCWpassword;
	    all["belongName"] = belongName;
	    all["ispass"] = ispass;
	    all["baokaopassword"] = baokaopassword;
	    all["banci"] = banci;
	    all["customer_time"] = customer_time;
	    all["isjieye"] = isjieye;
	    
	    
	    fixedItem = "<tr data-id="+studentId+" style='height:35px'>"+	        
	        "<td class='check' style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='"+studentId+"'  data-id='"+resourceId+"'/></td>"+
	        "<td class='details' style='width: 60px;position: fixed;background: #fff;left:59px;'><a onclick='showiframe("+studentId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+studentId+"></span></a></td>"+ 
	        "<td class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 118px;'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='studentName' style='width: 100px;position: fixed;background: #fff;left: 177px;'>"+studentName+"</td>"+
	        "<td class='idCard' style='width: 180px;position: fixed;background: #fff;left: 276px;'>"+idCard+"</td>"+	      
	        "<td class='phone' style='width: 185px;position: fixed;background: #fff;left: 455px;'>"+phone+"</td>"+
      "</tr>"; 
	    $("#fixedTable").append(fixedItem);
	    
	    
	    var passte = "<tr data-id='"+index+"'></tr>"						
	    $('#passsTable').append(passte);
	    
	    var subjectVal = [];
	    $("#passHeader th").each(function() {
	    	var boxid = $(this).attr('class');	
	    	subjectVal.push(boxid);	
	    });
	   
	    for(var i=0; i<subjectVal.length; i++){	    //匹配相应字段	
	    	var sign = "0";
	    	for ( var k in all) {
	    		if (subjectVal[i] == k) {				
	    			passItem = "<td class='"+k+"'>"+all[k]+"</td>";
	    			$('#passsTable tr[data-id="'+index+'"]').append(passItem)
	    			sign = "1";
	    		}
	    	}
	    	if (sign == "0") {
	    		passItem = "<td class='"+k+"'>"+""+"</td>";
  			$('#passsTable tr[data-id="'+index+'"]').append(passItem)
			}
	    	
	    }
	    
		
	}); 
}

/*查询*/
var search = null;
var phonedata;
function passquery(){
	search = $('#search').val();
	$('#pagination').html('');
	phonedata = {        
			userid:userid,
	    	deptid:deptid,
	    	roleid:roleid,
	    	studentName:search,
	    	idCard:search,
	    	phone:search
	}
	if(search=="" || search == null){
		$('#backcurrent').attr('data-id','');
		 passsudentTab()//等于空加载所有
	}else {
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
		phoneFill (phonedata,'../custom/querySpecialStudents.do')
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
			clearDate()
			//插入头部
			fillTable(list,currentPage)
		}
	})
}
var iframe = $(window.parent.document).find("#iframepage");
iframe.height(0);
//子窗口操作返回当前列表页
function showdeiatil(){
	$('#showdeiatil').css('display','none');
	iframe.height(0)
	window.setInterval("reinitIframe()", 200);
	var curret = $('#backcurrent').val();
	var filtercurrent = $('#backcurrent').attr('data-id');
	
	if((curret != "" && curret != null) && (filtercurrent == "" || filtercurrent == null)){ //如果当前页不等于空，就重新加载返回curret的列表数据
		 passsudentTab()
	}else if((search != "" && search != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill (phonedata,'../custom/querySpecialStudents.do')
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

