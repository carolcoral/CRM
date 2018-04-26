var userid = $('.userid').attr('data-id'); //获取用户ID
//导航菜单收缩展开
var courseid = null; 
$(document).on('click','.subNav li', function(event) {
	$(this).addClass('subactive').siblings().removeClass('subactive');
	$('.navMenu ul').eq($(this).index()).show().siblings().hide();
	courseid = $(this).attr('data-id');
	patterType(userid,courseid);
});
patterType(userid,"0")//第一次加载场景名称
/*查询场景名称*/
function patterType(userid,courseid){
	$.ajax({        			
		   type: 'GET',
		   data: {
			   userid:userid,
			   courseid:courseid
		   },
		   url: "../patter/queryPattertype.do",		   
		   success: function(data) {
			   $('.navMenu ul').html('');
			   var dataObj=eval("("+data+")");//转换为json对象			   
			   var list = dataObj.rows;
			   $.each(list, function(index, array) {			        	
		        	//循环获取数据				  
				   var patterTypeId = list[index].patterTypeId || "";
				   var patterTypeName = list[index].patterTypeName || "";
				   var li = '<li data-id="'+patterTypeId+'">'+patterTypeName+'</li>';
				   $('.navMenu ul').append(li);
			   });
			   $('.navMenu ul li').eq(0).addClass('menuActive');
			   var  firstpatterid = $('.navMenu ul li').eq(0).attr('data-id');//第一次加载话术
			   selectPatter(userid,courseid,firstpatterid)
		   }
		});
}

$(document).on('click', '.navMenu li',function(event) {	
	$(this).addClass('menuActive').siblings().removeClass('menuActive');
	$(this).parent().siblings().find('li').removeClass('menuActive')
	var patterTypeId = $(this).attr('data-id');
	selectPatter(userid,courseid,patterTypeId);
});
/*查询话术内容*/
function selectPatter(userid,courseid,patterTypeId){
	$.ajax({        			
		   type: 'GET',
		   data: {
			   userid:userid,
			   courseid:courseid,
			   patterTypeId:patterTypeId
		   },
		   url: "../patter/queryPatter.do",		   
		   success: function(data) {
			   $('#mypatterTab tr:not(:first)').remove();//清空表格内容
			   var dataObj=eval("("+data+")");//转换为json对象
			   
			   var list = dataObj.rows;
			   
			   $.each(list, function(index, array) {			        	
		        	//循环获取数据
				   var contentTypeId = list[index].contentTypeId
				   var patterid = list[index].patterid || "";
				   var shortTitle = list[index].shortTitle;				   
				   var isshare = list[index].isshare;
				   var username = list[index].username
				   var content = list[index].content
				   if(isshare=="0"){
					   isshare = "不共享"
				   } else if(isshare=="1"){
					   isshare = "共享"
				   } else{
					   
				   }
				   var mytr = '<tr data-id="'+patterid+'">'+
		                '<td><input type="checkbox" class="'+patterid+'" /></td>'+
		                '<td>'+(index+1)+'</td>'+
		                //'<td>'+contentTypeId+'</td>'+
		                '<td>'+shortTitle+'</td>'+
		                '<td style="display:none">'+content+'</td>'+
		                '<td>'+isshare+'</td>'+
		                '<td>'+username+'</td>'+
		                '<td><span class="glyphicon glyphicon-edit edit" data-id="'+patterid+'"></span></td>'+                                                         
		            '</tr>';
				   var ul = '<ul></ul>';
				   $('#mypatterTab').append(mytr);
			   });	   
		   }
	});
}
var editorhtml;//富文本内容  
var addpatterTypeId = null;
var addcouserid = null;
//新增话术
function hasclass(){
	if($('.navMenu').find('li').hasClass('menuActive')) {
		addpatterTypeId = $(".navMenu li[class='menuActive']").attr("data-id");
		addcouserid = $(".subNav li[class='subactive']").attr("data-id");
		$('#shortTitle').val('');
		ue.setContent('')
		$('#addPatter').show();
		$('.addnewTit').html("我的话术_增加");
	}else {	
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一个场景进行添加!!!",
			src:"./system/img/tishi.png"
		});
	}
}
function addNewPatter(){
	var roleid = $('.roleid').attr('id');
	var shortTitle = $('#shortTitle').val();
	getContent();  //获取带格式的富文本内容      
    $.ajax({        			
		   type: 'POST',
		   data: {
			   userid:userid,
			   roleid:roleid,
			   couserid: addcouserid,
			   patterTypeId:addpatterTypeId,
			   shortTitle:shortTitle,
			   content: editorhtml
		   },
		   url: "../patter/saveOrUpdatePatter.do",		   
		   success: function(data) {
			   if(data == "success"){
				   window.location.reload();
			   }else{
				   
			   }
			   
		   }
	});
}
//获取带格式的富文本内容
function getContent() {
    var arr = [];
    arr.push(UE.getEditor('editor').getContent());
    editorhtml=arr.join("\n")
}

//修改话术
var mypatterVal = [];
var mypatterid =null;
function changemyPatter(){
	
	mypatterVal = [];//清空上次的修改条数
	
	$("#mypatterTab input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		mypatterVal.push(boxid);
		
	});
	
	mypatterid =$('#mypatterTab input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID
	
	
	if(mypatterVal.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(mypatterVal.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changePatterText(mypatterid);
		$('#addPatter').show();
	}
}

//==============获取循环添加的tr展示修改数据====================
function changePatterText(objTag){	
	var shortTitle=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var content=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();
	
	$('.addnewTit').html("我的话术_修改");
	$('.addsave').css("display","none");//保存新增按钮隐藏
	$('.changesave').css({"display":"block","float": "left"});//保存修改按钮显示	
	$('#editor').val(content);//先将数据读到textarea中	
	ue.setContent($('#editor').val());//然后编辑器再从textarea中读取
	$('#shortTitle').val(shortTitle);
	 
	$('.detailsTit').html(shortTitle);//查看话术详情
	$('.detailsCon').html($('#editor').val());//查看话术详情
};
//修改保存话术
function saveChmyPatter(){	
	var mypatterid =$('#mypatterTab input[type="checkbox"]:checked').attr('class');
	
	var shortTitle =  $('#shortTitle').val();
	getContent();  //获取带格式的富文本内容  
	
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 userid:userid,
			 patterid:mypatterid,
			 shortTitle:shortTitle,
			 content: editorhtml //富文本内容  
		   },
		   url: "../patter/saveOrUpdatePatter.do",		   
		   success: function(data) {
			   if(data=="success"){					   
				   
 				   window.location.reload();				   
 					
 			   }else if(data=="failed"){					   
 				  
 			   }else {
 				 
 			   }
		 }
	});
}

//确定删除场景
var delmypatterid = [];	
function showDelmypatter() {	
	
	$("#mypatterTab input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		delmypatterid.push(boxid);
		
	});	
	if(delmypatterid.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	} else {
		$('.themisWrap').css('display','block');
	}
};
function delmypatter() {
	$('.themisWrap').css('display','none');
	var patterids = delmypatterid.join(",");	
	$.ajax({
		type: "POST",
		url: '../patter/deletePatter.do',
		data: {
			userid:userid,
			patterids:patterids
		},
		success: function(data) {
			
		   if(data=="success"){
		       window.location.reload();
		   }else if(data=="6"){					   
			   $.winTip({
					title: "提示~~~",
					msg: "当前登录用户不是数据创建人",
					src:"./system/img/tishi.png"
				});
		   }else {
			 
		   }	
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})
//查看话术内容

$(document).on('click','.edit',function(){
	var mypatterid =$(this).parent().parent().attr('data-id');//获取要修改的信息ID
	changePatterText(mypatterid);
	$('#details').show();
 });
//共享话术
var sharemypatterid =[];
function isshare(){
	sharemypatterid =[];
	$("#mypatterTab input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		sharemypatterid.push(boxid);
		
	});
	var patterid =$('#mypatterTab input[type="checkbox"]:checked').attr('class');
	if(sharemypatterid.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	}else if(sharemypatterid.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息",
			src:"./system/img/tishi.png"
		});
	}	
	$.ajax({
		type: "POST",
		url: '../patter/updatePatterIsShare.do',
		data: {
			patterid:patterid
		},
		success: function(data) {
			console.log(data);
		   if(data=="success"){
		       window.location.reload();
		   }else {					   
			   $.winTip({
					title: "提示~~~",
					msg: ""+data,
					src:"./system/img/tishi.png"
				});
		   }	
		}
	});
	
}


