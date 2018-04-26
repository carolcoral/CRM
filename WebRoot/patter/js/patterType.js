//angular 分页展示数据
var myPatterType = angular.module('patterType', []);
myPatterType.controller('patterTypeCtrl',function($scope,$http){
    //获取数据    
    $http.get("../course/queryPattertype.do").success(function(Data){
    	$scope.patter = function (a) {
    	    var p = "";            	    
    	    if (a == "0") {
    	        p = 'AFP';	        
    	    }else if (a == "1") {
    	        p = 'CFP';
    	    }else if (a == "2") {
    	        p = '基金从业';
    	    }
    	    else if (a == "3") {
    	        p = '银行从业';
    	    }
    	    else if (a == "4") {
    	        p = '中级经济师';
    	    }
    	    else if (a == "5") {
    	        p = '会计从业';
    	    }
    	    else if (a == "6") {
    	        p = '初级会计';
    	    }
    	    else if (a == "7") {
    	        p = '期货从业';
    	    }
    	    else if (a == "8") {
    	        p = '证券从业';
    	    }
    	    else if (a == "9") {
    	        p = '其它';
    	    }
    	    return p;
    	}; 
    	$scope.list = Data.rows;  
    });
    $http.get("../course/queryCourse.do").success(function(Data){
    	
    	$scope.course = Data.rows;    	
    });
    
});
//全选
$('.all').click(function() {
	if(this.checked) {
		$("#patterTable input[type='checkbox']").prop("checked", true);
	} else {
		$("#patterTable input[type='checkbox']").prop("checked", false);
	}
});
/*新增场景*/
function addPatterType(){
	var courseId = $('#courseName option:selected').val();
	var patterTypeName = $('#patterTypeName').val();
	$.ajax({        			
		   type: 'POST',
		   data: {
			 courseid:courseId,
			 patterTypeName:patterTypeName
		   },
		   url: "../course/addPattertype.do",		   
		   success: function(data) {
			   if(data=="success"){				   
				   window.location.reload();
 					
 			   }else if(data=="failed"){					   
 				  alert('请求失败！');
 			   }else {
 				 
 			   }
		 }
	});
}

//修改场景
var patterVal = [];
var patterTypeId =null;
function changepatter(){
	
	patterVal = [];//清空上次的修改条数
	
	$("#patterTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		patterVal.push(boxid);
		
	});
	
	patterTypeId =$('#patterTable input[type="checkbox"]:checked').attr('class');//获取要修改的信息ID
	
	
	if(patterVal.length == 0) {				
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
		
	}else if(patterVal.length > 1){
		$.winTip({
			title: "提示~~~",
			msg: "请选择一条信息修改",
			src:"./system/img/tishi.png"
		});
	}else {		
		changePatterText(patterTypeId);
	}
}

//==============获取循环添加的tr展示修改数据====================
function changePatterText(objTag){	
	var courseName=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).html();
	var courseId=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(2).attr('data-id');
	var patterTypeName=$('tr[data-id='+'"'+objTag+'"'+']').children('td').eq(3).html();		
	$('#patterType').show();	
	$('#savePatterType').css("display","none");//保存新增按钮隐藏
	$('#saveChPatter').css({"display":"block","float": "left"});//保存修改按钮显示	
	$('#courseName option:selected').html(courseName);
	$('#courseName option:selected').val(courseId);
	$('#patterTypeName').val(patterTypeName);
	var temp = '';  //去除重复元素
	$('#courseName option').each(function(){
		if(temp.indexOf($(this).html())!=-1)
		$(this).remove();
		temp += $(this).html();
	})
};
//修改保存场景
function saveChPatter(){	
	var patterTypeId = $('#patterTable input[type="checkbox"]:checked').parent().parent().attr('data-id');
	var courseId =  $('#courseName option:selected').val();
	var patterTypeName = $('#patterTypeName').val();
 	$.ajax({        			
		   type: 'POST',
		   data: {
			 patterTypeId:patterTypeId,
			 courseid:courseId,
			 patterTypeName:patterTypeName
		   },
		   url: "../course/addPattertype.do",		   
		   success: function(data) {
			   if(data=="success"){					   
				   
 				   window.location.reload();				   
 					
 			   }else if(data=="failed"){					   
 				  alert('请求失败！');
 			   }else {
 				 
 			   }
		 }
	});
}


//确定删除场景
var delpatterTypeId = [];	
function showpatter() {	
	
	$("#patterTable input[type='checkbox']:not(:first):checked").each(function() {
		
		var boxid = $(this).attr('class');
		
		delpatterTypeId.push(boxid);
		
	});	
	if(delpatterTypeId.length == 0) {
		$.winTip({
			title: "提示~~~",
			msg: "至少选择一条信息",
			src:"./system/img/tishi.png"
		});
	} else {
		$('.themisWrap').css('display','block');
	}
};
function delpatter() {
	$('.themisWrap').css('display','none');
	var delpatterTypeIds = delpatterTypeId.join(",");	
	$.ajax({
		type: "POST",
		url: '../course/deletepatterType.do',
		data: {
			patterTypeIds:delpatterTypeIds
		},
		success: function() {			 	   
			window.location.reload();		
		}
	});
};
$('#quxiao').on('click',function(){
	$('.themisWrap').css('display','none');
})



