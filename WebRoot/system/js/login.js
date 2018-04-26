//左侧导航菜单收缩展开
$('.biaoti').on('click', function(event) {
	$(this).find('.arrow').stop().toggleClass('glyphicon-menu-up');
	$(this).siblings('.bianji').stop().slideToggle(400);
});
$('.bianji li').on('click', function(event) {
	$(this).find('a').addClass('active');
	$(this).siblings().find('a').removeClass('active')
	$(this).parent().parent().siblings().find('a').removeClass('active');
});

//左侧导航收缩
var a = 0;
function sidebarToggle(){	
	if(a == 1) {
		$('.left').animate({
			'left': '0px'
		}, 200);
		$('.con').animate({
			'padding-left': '185px'
		}, 200);
		$('.login').animate({
			'padding-left': '185px'
		}, 200);
		$('.home').hide();
		$('.firstfold').show();
		a = 0;
	} else {
		$('.left').animate({
			'left': '-150px'
		}, 200);
		$('.con').animate({
			'padding-left': '35px'
		}, 200);
		$('.login').animate({
			'padding-left': '35px'
		}, 200);
		$('.home').show();
		$('.firstfold').hide();

		a = 1;
	}
}
var ischange = $('#ischange').text();
if($.trim(ischange) == "0"){
	$('#updpwd').css('display','block')
}else {
	$('#updpwd').css('display','none')
}
//修改密码
function savepwd() {
	var newPassword = $("#newPassword").val().trim();
	var oldPassword = $("#oldPassword").val().trim();
	var repeatPassword = $("#repeatPassword").val().trim();
	var nameid = $('.username').attr('id');	
	if(checkPwd(newPassword,oldPassword,repeatPassword,nameid)){
		$.ajax({        			
		   type: 'POST',
		   data: {
			   newPassword:newPassword,
		       oldPassword:oldPassword,
			   userid:nameid
		   },
		   url: "./system/editPassword.do",		   
		   success: function(data) {		   
			   if(data=="success"){				   
				window.location.href = "./logout.do";					 					
			   }else if(data=="0"){					   
				   $('.oldPasswordError').html('参数为空！');
			   }
			   else if(data=="1"){
				   $('.oldPasswordError').html('用户不存在！');					   
			   }
			   else if(data=="2"){
				   $('.oldPasswordError').html('密码错误！');
			   }
			   else if(data=="3"){
				   $('.oldPasswordError').html('旧密码不匹配！');
			   }
			   else if(data=="4"){
				   $('.deptParanameError').html(data);
			   }
			   else if(data=="5"){
				   $('.oldPasswordError').html('用户已经存在！');
				   window.location.reload();
			   }else {
				 
			   }
		 	}
		})
	}
}
function checkPwd(newpwd,oldpwd,repeatpwd,id) {
	var flag=true;			
	if (oldpwd == "" || oldpwd == null) {
		$('.oldPasswordError').html("旧密码不能为空");
		flag=false; 
	}
	if (newpwd == "" || newpwd == null) {
		$('.newPasswordError').html("新密码不能为空");
		flag=false;
	}
	if (repeatpwd == "" || repeatpwd == null) {		
		$('.repeatPasswordError').html("确认密码不能为空");			
		flag=false;
	}
	if (repeatpwd != newpwd) {
		$('.repeatPasswordError').html("两次密码不一致");
		flag=false;
	}	
	return flag;
}
//今日目标
function addToday(){
	$('#todayNote').css('display','block');
	$('#todayTip').html("添加今日目标");			
}
function chToday(){
	$('#todayNote').css('display','block');
	$('#todayTip').html("修改今日目标");
}
function saveAdd(){
	var todayVal = $('#todayVal').val().trim();	
	var nameid = $('.username').attr('id');					
	$.ajax({        			
	   type: 'POST',
	   data: {
	   		userid:nameid,
	   		note:todayVal 
	   },
	   url: "./system/addOrUpdateTodayNote.do",		   
	   success: function(data) {		   
		   if(data=="success"){				   				   
				window.location.reload();	 					
		   }else {			   
		   		$('#todayValError').html(data);					 	
		   }
	 	}
	})
}