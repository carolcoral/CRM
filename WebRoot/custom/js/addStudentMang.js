/*禁止文字复制*/
document.oncontextmenu=function(evt){ 
    evt.preventDefault(); 
} 

document.onselectstart=function(evt){ 
    evt.preventDefault(); 
};
/*获取课程*/
queryCourse('#filter_courseid');	
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
var nowheight = null;
//展示iframe
function showiframe(studentId,tag,currentPage){
	var dataParams = {
		roleid:roleid,
		userid:userid,
		deptid:deptid,
		studentId:studentId
	}
	$('#backcurrent').val(currentPage)
	var params = $.param(dataParams);
	
	var url = '../student/studentDetails.do'+"?"+params;
	$(tag).attr('href',url)
	$('#showdeiatil').css('display','block');
	nowheight = $(document).height();
}
//数据分页
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
var firstLoad = {        
	userid: userid,
	deptid:deptid,
	roleid:roleid
}
load(firstLoad,'../student/queryStudents.do',1)
function load(data,url,currentPage){
	$('#filterpagination').sjAjaxPager({
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
        "<th style='width: 60px;position: fixed;background: #fff;left: 59px;'>详情</th>"+
		"<th class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 118px;'>序号</th>"+
		"<th class='studentName' style='width: 80px;position: fixed;background: #fff;left: 177px;'>姓名</th>"+
		"<th class='phone' style='width: 320px;padding-left: 260px;'>手机</th>"+
		"<th class='studentstate' style='width: 80px;'>学员状态</th>"+
		"<th class='source' style='width: 80px;'>渠道</th>"+
		"<th class='dealtime'style='width: 140px;'>成交时间</th>"+   
		"<th class='headmaster' style='width: 80px;'>班主任</th>"+              
		"<th class='idCard' style='width: 170px;'>身份证号</th>"+
		"<th class='school'>毕业院校</th>"+
		"<th class='education'>学历</th>"+
		"<th class='preferinfo'>优惠信息</th>"+
		"<th class='LCWname'>理财网用户名</th>"+
		"<th class='LCWpassword'>理财网密码</th>"+
		"<th class='email'>邮箱</th>"+
		"<th class='company'>单位</th>"+
		"<th class='department'>工作部门</th>"+
		"<th class='position'>职务</th>"+
		"<th class='companyTel' >单位电话</th>"+	
		
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
	    var source = array.source|| "";
	    var sourceid = array.source|| "";
	    var dealtime = array.dealtime|| "";	
	    var headmaster = array.headmaster|| "";
	    var idCard = array.idCard|| "";
	    var school = array.school|| "";
	    var education = array.education|| "";
	    var preferinfo = array.preferinfo|| "";
	    var LCWname = array.LCWname|| "";
	    var LCWpassword = array.LCWpassword|| "";
	    var email = array.email|| "";
	    var company = array.company|| "";
	    var department = array.department|| "";
	    var position = array.position|| "";
	    var companyTel = array.companyTel|| "";
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
	    if (0 == source) {
	    	source = '自建';
	    }
	    else if (1 == source) {
	    	source = '课程注册'
	    }
	    else if (2 == source) {
	    	source = '在线注册'
	    }
	    else if (3 == source) {
	    	source = 'app下载注册'
	    }
	    else if (4 == source) {
	    	source = '电话咨询'
	    }
	    else if (5 == source) {
	    	source = '金考网注册用户'
	    }
	    else if (6 == source) {
	    	source = '线上渠道'
	    }
	    else if (7 == source) {
	    	source = '在线咨询'
	    }
	    else if (8 == source) {
	    	source = '大库资源'
	    }
	    else if (9 == source) {
	    	source = '在线购买'
	    }
	    tableItem ="<tr data-id="+studentId+">"+
	        "<td style='width: 60px;position: fixed;background: #fff;'><input type='checkbox' class='"+studentId+"'  data-id='"+resourceId+"'/></td>"+
	        "<td style='width: 60px;position: fixed;background: #fff;left:59px;'><a onclick='showiframe("+studentId+",this,"+currentPage+")' target='detailiframe'><span class='glyphicon glyphicon-edit edit' data-id="+studentId+"></span></a></td>"+ 
	        "<td class='serialnumber' style='width: 60px;position: fixed;background: #fff;left: 118px;'>"+((currentPage-1)*15+(index+1))+"</td>"+
	        "<td class='studentName' style='width: 80px;position: fixed;background: #fff;left: 177px;'>"+studentName+"</td>"+
	        "<td class='phone' style='width: 320px;padding-left: 260px;'>"+phone+"</td>"+
	        "<td class='studentstate'>"+studentstate+"</td>"+
	        "<td class='source' data-id="+sourceid+">"+source+"</td>"+
	        "<td class='dealtime'>"+dealtime+"</td>"+  
	        "<td class='headmaster'>"+headmaster+"</td>"+               
	        "<td class='idCard'>"+idCard+"</td>"+
	        "<td class='school'>"+school+"</td>"+
	        "<td class='education'>"+education+"</td>"+
	        "<td class='preferinfo'>"+preferinfo+"</td>"+
	        "<td class='LCWname'>"+LCWname+"</td>"+
	        "<td class='LCWpassword'>"+LCWpassword+"</td>"+
	        "<td class='email'>"+email+"</td>"+
	        "<td class='company'>"+company+"</td>"+
	        "<td class='department'>"+department+"</td>"+
	        "<td class='position'>"+position+"</td>"+
	        "<td class='companyTel' >"+companyTel+"</td>"+ 	        
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
/*姓名手机校验*/
function nameMobile(studentName,phone,email){	
	var flag = true;
	var reg= /^((13[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(14[0-9]))[0-9]{8}$/;
	var emreg = /^[A-Za-z0-9]+([-_.][A-Za-z0-9]+)*@([A-Za-z0-9]+[-.])+[A-Za-zd]{2,5}$/;
	//姓名
    if(studentName == null || studentName == ""){               
       $("#studentNameError").html("内容不能为空");
       flag = false;                  
    }else {
    	 $("#studentNameError").html("");
    }
    //校验手机号格式是否正确    
    if(phone != null || phone != ""){
        if(!reg.test(phone)){
            $("#phoneError").html("手机号格式有误")
            flag = false; 
        }else{
            $("#phoneError").html("")
        };
    } 
    //邮箱
    if(email != "" && email != null){
    	if(!emreg.test(email)){
            $("#emailError").html("邮箱号格式有误")
            flag = false; 
        }else{
            $("#emailError").html("")
        };
    }        
    return flag;
};
//增加资源
function addnewStudent(){	
	var studentName = $('#studentName').val();
	var phone = $('#phone').val().trim();
	var idCard = $('#idCard').val();
	var school = $('#school').val();
	var education = $('#education').val();
	var email = $('#email').val();
	var company = $('#company').val();
	var companyAddr = $('#companyAddr').val();
	var companyTel = $('#companyTel').val();
	var department = $('#department').val();
	var position = $('#position').val();
	var xiaoShouNote = $('#xiaoShouNote').val();
	if(nameMobile(studentName,phone,email)){
		$.ajax({
			url: '../student/addStudent.do',
			type: 'POST',
			data: {
				studentName:studentName,
				phone:phone,
				idCard:idCard,
				school:school,
				education:education,
				email:email,
				company:company,
				companyAddr:companyAddr,
				companyTel:companyTel,
				department:department,
				position:position,
				xiaoShouNote:xiaoShouNote,
				source:"0"
			},			
			success: function(data) {	
			   if(data=="success"){				   
				   window.location.reload();					
			   } else if(data=="0"){
				   $.winTip({
						title: "提示~~~",
						msg: "参数为空",
						src:"./system/img/tishi.png"
					});
			   } else {
				 
			   }
		    }
		})
	}
}
//提交行政
function sendexEcutive(){
	var studentId = [];
	var resourceId = [];
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		var boxid = $(this).attr('class');
		var dataId = $(this).attr('data-id');
		studentId.push(boxid);
		if(dataId !="" && dataId != null){
			resourceId.push(dataId);
		}
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
			url: '../student/studentCommit.do',
			data: {
				studentIds: studentIds,
				resourceIds: resourceIds
			},
			success: function(data) {
				if(data=="success"){
					alert("提交行政成功")
					window.location.reload();					
				}else if(data=="11"){					
					$.winTip({
						title: "提示~~~",
						msg: "学员状态存在非已成交和已退回，不能提交行政",
						src:"./system/img/tishi.png"
					});
				}
				
			}
		});			
	}
}
function clearadd() {
	$('#addresource').css('display','none');
	$('#studentName').val('');
	$('#phone').val('');
	$('#idCard').val('');
	$('#school').val('');
	$('#education').val('');
	$('#email').val('');
	$('#company').val('');
	$('#companyAddr').val('');
	$('#companyTel').val('');
	$('#department').val('');
	$('#position').val('');
	$('#xiaoShouNote').val('');
	$('#source option:selected').val('');
	$('#phoneError').html('');
	$('#studentNameError').html('');
	$('#sourceError').html('');
	$('#emailError').html('');	
};
//手机查询
var search = null;	
var  phonedata;
function fastquery(){
	search = $('#search').val();
	$('#filterpagination').html('');
	phonedata = {        
		userid: userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName:search,
    	idCard:search,
    	phone:search
	}
	if(search=="" || search == null){
		$('#backcurrent').attr('data-id','')
		load(firstLoad,'../student/queryStudents.do')
	}else {
		var backcurren = "1" ;//只要点击了筛选或者查询
		var backcurrentdata = (backcurren++);
		$('#backcurrent').attr('data-id',backcurrentdata)
		phoneFill (phonedata,'../student/queryStudentBySceen.do')
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

/*根据条件进行查询资源--筛选*/
var filterData;
function filter(){	
	studentName = $('#filter_studentName').val();	
	source = $('#filter_source').val();
	phone = $('#filter_phone').val();	
	courseid = $('#filter_courseid  option:selected').val();
	
	var backcurren = "1" ;//只要点击了筛选或者查询
	var backcurrentdata = (backcurren++);
	$('#backcurrent').attr('data-id',backcurrentdata)
	
	filterData = {
		userid:userid,
    	deptid:deptid,
    	roleid:roleid,
    	studentName:studentName,			
		source:source,		
		phone:phone,			
		courseid:courseid	
	}
	$('#filterpagination').html('');
	$('#filter').css('display','none')	
	load(filterData,'../student/queryStudentBySceen.do',1)
}
/*删除学员(新增状态)*/
function delResource(){
	
	studentId = [];//清空上次的修改条数
	resourceid = [];
	$("#resourceTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		var resid = $(this).attr('data-id');
		studentId.push(boxid);
		resourceid.push(resid);
	});

	studentIds= studentId.join(',')
	resourceids = resourceid.join(',')
	if(studentId.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	} else {
		$.ajax({        			
		   type: "POST",
		   data: {
			   resourceId:resourceids,			  
			   studentId: studentIds
			},
		   url: '../student/deleteStudent.do',
		   success: function(data) {
			   if(data=="success"){				   
				   window.location.reload();		
			   }else if(data=="0"){
				   $.winTip({
						title: "提示~~~",
						msg: "参数为空",
						src:"./system/img/tishi.png"
					});
			   }else if(data=="10"){
				   $.winTip({
						title: "提示~~~",
						msg: "无删除学员权限",
						src:"./system/img/tishi.png"
					});
			   }
			   else if(data=="8"){
				   $.winTip({
						title: "提示~~~",
						msg: "用户没有权限操作",
						src:"./system/img/tishi.png"
					});
			   }
			   else {
				 
			   }
			}
		});		
	}
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
		load(firstLoad,'../student/queryStudents.do')
	}else if((search != "" && search != null) && (filtercurrent != "" || filtercurrent != null)){//如果是手机查询的，就重新加载返回curret的列表数据
		phoneFill (phonedata,'../student/queryStudentBySceen.do')
	}else if((search == "" || search == null) && (filtercurrent != "" || filtercurrent != null) ){//如果是多条件查询的，就重新加载返回curret的列表数据
		load(filterData,'../student/queryStudentBySceen.do')
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


