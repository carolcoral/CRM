$(window.parent.document).find("#detailiframe").load(function(){
    var main = $(window.parent.document).find("#detailiframe");
    var thisheight = $(document).height();
    main.height(thisheight);
});
var mydate = new Date();
	var hftime = mydate.getFullYear() + "-" + (mydate.getMonth() + 1) + "-"+ mydate.getDate()+" "+mydate.getHours()+":"+ mydate.getMinutes();
	$('#timeid').html(hftime);
		
$('#scoretime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:s", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#ch-scoretime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:s", //格式化日期
	validateOnBlur:false //删除选中的时间
});
$('#mailTime').datetimepicker({
	lang: 'ch',
	format:"Y-m-d H:m:s", //格式化日期
	validateOnBlur:false //删除选中的时间
});
/*保存详情*/
var studentId = $('#studentId').val();
var userid = document.getElementById("userid").value;//用户Id
var deptid = document.getElementById("deptid").value;//部门Id
var roleid = document.getElementById("roleid").value;//角色Id
var resourceId = $('#resourceId').val();//资源Id
var ispass = $('#ispass').val();//是否全科通过
var phone = $('#phone').val();

function saveDetails(){	
	var specialinfo = $("#specialinfo option:selected").val();	
	var isSignAgreement = $("#isSignAgreement option:selected").val();	
	var isHaveCourse  = $("#isHaveCourse option:selected").val();
	var pingjia = $("#pingjia option:selected").val();	
	var courseversion = $("#courseversion").val();
	var mailTime = $("#mailTime").val();
	var scoretime = $("#scoretime").val();
	var baokaopassword = $("#baokaopassword").val();
	var weixin = $("#weixin").val();
	var kefuNote1 = $("#kefuNote1").val();
	var address = $("#address option:selected").val();
	var banci = $('#banci option:selected').val();
	var classNum = $("#classNum").val();
	if(specialinfo == ""){
		specialinfo =null;
	}
	var data = {		 
		studentId:studentId,		  
		studentEvaluate:pingjia,
		specialinfo:specialinfo,
		isSignAgreement:isSignAgreement,
		isHaveCourse:isHaveCourse ,
		courseversion:courseversion,
		baokaopassword:baokaopassword,
		weixin:weixin,
		kefuNote1:kefuNote1,
		address:address,
		banci:banci,
		classNum:classNum
	}
	if(mailTime != "" && mailTime != null){
		data["mailTime"]=mailTime; 
	}
	if(scoretime != "" && scoretime != null){
		data["scoretime"]=scoretime; 
	}
	$.ajax({        			
	   type: "POST",
	   data: data,
	   url: '../student/addStudent.do',
	   success: function(data) {		   
		   	if(data=="success"){		   		
		   		if($.trim(specialinfo) != "" && $.trim(specialinfo) != null){
		   			$('.themisWrap').css('display','block');
		   			$('#tipinfo').html("信息保存成功,此学员情况特殊，请在【特殊学员】中查看");
		   			$('#showphone').html(phone);
		   		}else {
		   			if($.trim(ispass) == "通过"){
		   				$('.themisWrap').css('display','block');
		   				$('#tipinfo').html("信息保存成功,此学员已全科通过，请在【通过学员】中查看");
		   				$('#showphone').html(phone);
		   			}else{
		   				$.winTip({
							title: "提示~~~",
							msg: "信息保存成功",
							src:"./system/img/tishi.png"
						});
		   			}
		   		}
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
/*增加回访记录*/
function addrecord(){
	var visitRecord = $('#visitRecord').val();
	if(visitRecord==""||visitRecord==null){
		$('#visiterror').html("回访记录不可为空")		
		return false;
	}
	$.ajax({        			
	   type: "POST",
	   data: {
		   userid:userid,		   
		   studentId:studentId,
		   visitRecord:visitRecord			   
	   },
	   url: '../student/addStudentVisitrecord.do',
	   success: function(data) {
		   if(data=="success"){
			  var tr ="<tr>"+
				"<td class='text-primary col-md-2'>"+hftime+"</td>"+
				"<td class='text-primary col-md-6'>"+visitRecord+"</td>"+
				"</tr>"
			  $('#recoredtable').prepend(tr);
			  $('#visitRecord').val('');
		   }		  
	   }
	});
}


/*修改成交记录的数据*/
var dealrecordId = null;
var subjectid = null;
$('.changedit').click(function(){
	dealrecordId = $(this).attr('data-id');
	subjectid = $('#showsubject').attr('data-id');
	$('#changedeal').show();
	changeText(dealrecordId)
})
/*修改成交记录的数据*/
function changeText(objTag){
	
	var score=$('#dealtable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html().trim();
	var scoretime=$('#dealtable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(4).html().trim();
	var ispass=$('#dealtable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).text().trim();	
	var ispassid= $('#dealtable tr[data-id='+'"'+objTag+'"'+']').children('td').eq(5).text().trim();
	
	if($.trim(ispassid)=="未通过"){
		ispassid="0";
	}else if($.trim(ispassid)=="通过"){
		ispassid="1";
	}
	else if($.trim(ispassid)=="缺考"){
		ispassid="2";
	}
	$('#ch-score').val(score);
	$('#ch-scoretime').val(scoretime);
	$('#ch-ispass option:selected').html($.trim(ispass));
	$('#ch-ispass option:selected').val(ispassid);
};	
/*修改成交记录*/
function changedeal(){	
	var score = $('#ch-score').val();
	var scoretime = $('#ch-scoretime').val();
	var ispass = $('#ch-ispass option:selected').val();	
	if(scoretime == "" || scoretime == null){
		$('#scoretimeError').html("考试时间不可为空");
		return false;		
	}else {
		$('#scoretimeError').html("");
		$.ajax({        			
			type: "POST",
			url: '../custom/editDealrecord.do',
			data: {
				userid:userid,
				studentId:studentId,
				resourceId:resourceId,
				score:score,
				scoretime:scoretime,
				ispass:ispass,
				dealrecordId:dealrecordId
			},
			success: function(data) {				
				if(data=="success"){ 
					$('#changedeal').hide();
					parent.showdeiatil();					
				}else if(data==="8"){
					$.winTip({
						title: "提示~~~",
						msg: "当前用户没有操作权限",
						src:"./system/img/tishi.png"
					});
				}
			}
		});
	}
}
