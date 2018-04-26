<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
	<meta name="msapplication-tap-highlight" content="no" />
	<meta name="robots" content="noindex" />
	<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />
	<meta name="renderer" content="webkit" />
	<link rel="stylesheet" type="text/css" href="../common/css/bootstrap.min.css" />	
	<link rel="stylesheet" type="text/css" href="../common/css/common.css" />
	<link rel="stylesheet" type="text/css" href="../common/css/winTip.css" />
	<link rel="stylesheet" type="text/css" href="../patter/css/myPatter.css" />
	<title>部门管理</title>
</head>
<!-- ng-app=myPatter"  ng-controller="myPatterCtrl" -->
<body>
	<span class="userid" data-id="${sessionScope.login_user.userid}"></span>
	<span class="roleid" id="${sessionScope.login_user.roleid}"></span>
	<div class="wordsNav">
		<ul class="subNav">
            <li class="subactive" data-id="0">AFP</li>
            <li data-id="1">CFP</li>
            <li data-id="2">基金从业</li>
            <li data-id="3">银行从业</li>
            <li data-id="4">中级经济师</li>
            <li data-id="5">会计从业</li>
            <li data-id="6">初级会计</li>
            <li data-id="7">期货从业</li>
            <li data-id="8">证券从业</li>
            <li data-id="9">其它</li>
        </ul>
        <div class="navMenu">
        	<ul>
        		<!-- <li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li>
        		<li><a href="javascript:;">课程1开场白</a></li> -->
        	</ul>
        </div>        	
	</div>	
	<div class="meun-btn">
       	<ul class="nav navbar-nav">
			<li onclick="hasclass()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-plus"></span>增加</a>
			</li>
			<li class="orange" onclick="changemyPatter()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-pencil"></span>修改</a>
			</li>
			<li class="red" onclick="showDelmypatter()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-minus"></span>删除</a>
			</li>
			<li class="blue" onclick="isshare()">
				<a href="javascript:;" class="create"><span class="glyphicon glyphicon-share-alt"></span>共享</a>
			</li>						
		</ul>
    </div>
   	<div class="bs-example table-responsive" data-example-id="simple-table">               
        <table class="table table-bordered table-hover table-striped" id="mypatterTab">                    
            <tr class="henglan">
                <td class="text-primary" style="width: 40px;"><input type="checkbox" class="all" /></td>
                <td class="text-primary">序号</td>
                <!-- <td class="text-primary">话术类别</td> -->
                <td class="text-primary">内容标题</td>                
                <td class="text-primary">是否共享</td>
                <td class="text-primary">话术创建人</td>
                <td class="text-primary">备注</td>    
                                         
            </tr> 
            <!-- <tr>
                <td><input type="checkbox"/></td>
                <td>序号</td>
                <td>话术类别</td>
                <td>内容标题</td>
                <td>是否共享</td>
                <td>话术来源</td>
                <td>详细内容</td>                                                         
            </tr> -->
        </table>
        <!-- 分页 -->
		<!-- <nav>
		     <ul class="pagination">
		         <li ng-class="{true:'disabled'}[currentPage==1]"><a href="javascript:void(0);" ng-click="firstPage()">首页</a></li>
		         <li ng-class="{true:'active'}[currentPage==page]" ng-repeat="page in pages"><a href="javascript:void(0);" ng-click="loadPage(page)">{{ page }}</a></li>
		         <li ng-class="{true:'disabled'}[currentPage==pageTotal]"><a href="javascript:void(0);" ng-click="lastPage()">尾页</a></li>
		     </ul>
		     <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{count}} 条</span>
		   <span style="vertical-align: 30px;">&nbsp;&nbsp;共：{{pageTotal}} 页</span>
		</nav> -->
    </div>
    <!-- 话术 -->
	<div id="addPatter"  class="ui-wrap">
		<div class="addnew-wrap addPatter">
			<h2 class="wrap-tit"><b class="addnewTit">我的话术_增加</b><span class="close closebtn"  onclick="delcon('addPatter')">×</span></h2>
			
			<form class="form-horizontal add_form">
				<div class="form-group">
					<label for="inputPassword3" class="col-md-3 control-label">话术标题：</label>
					<div class="col-md-8">
						<input type="text" class="form-control" id="shortTitle">
					</div>
				</div>
				<p class="help-block" id="courseMoneyError"></p>				
				<!-- 加载编辑器的容器 -->
    			<textarea name="content" id="editor" style="width:500px;height:250px;"></textarea>
				<div class="form-group">
					<div class="col-md-offset-4 col-md-6">
						<a href="javascript:;" class="btn addsave" onclick="addNewPatter()">保 存</a>
						<a href="javascript:;" class="btn changesave" onclick="saveChmyPatter()" style="display:none">保 存</a>
						<a href="javascript:;"  class="btn"  onclick="delcon('addPatter')" style="background: #4c7cba;">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>	
	<!-- 提示 -->
    <div class="themisWrap" style="display:none;" >
      <div class="themisGray"></div>
        <div class="themis" style="top:30%;">
           <h3 class="themistit"><span class="themisTipPic" style="float: left;padding-top: 17px;padding-left: 10px;margin-right: 10px;"><img class="pic" src="../system/img/tishi.png" height="25" width="25" alt="" /></span>友情提示</h3>
           <div class="themispay">
                <div class="themistip" style="margin-bottom: 20px; color:red; font-size:14px;">确定删除这些信息吗!</div>
                <button class="btn navbar-right" id="quxiao" >取消</button>                
                <button class="btn navbar-right" id="patterTypeDel" style="background: #4c7cba;" onclick="delmypatter()">确定</button>                    
           </div>
        </div>
      </div>
      <!-- 话术 -->
	<div id="details"  class="ui-wrap">
		<div class="addnew-wrap addPatter">
			<h2 class="wrap-tit"><b class="detailsTit"></b><span class="close closebtn"  onclick="wuorder.CloseDiv('details','fade')">×</span></h2>
			
			<div class="detailsCon"></div>
		</div>
	</div>
<script type="text/javascript" src="../common/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../common/js/angular.min.js"></script>
<script type="text/javascript" src="../common/js/winTip.js"></script>
<script type="text/javascript" src="../common/js/boot.js"></script>
<script type="text/javascript" src="../patter/js/myPatter.js"></script>
<!-- 配置文件 -->
<script type="text/javascript" src="../ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="../ueditor/ueditor.all.js"></script>
<!-- 实例化编辑器 -->
<script type="text/javascript">

   var ue = UE.getEditor('editor', {    
   		autoHeightEnabled: false, //是否自动长高，默认true
   		autoFloatEnabled : true, //是否保持toolbar的位置不动，默认true  		          
        allHtmlEnabled: false, //提交到后台的数据是否包含整个html字符串
        allowDivTransToP: false,  //阻止div标签自动转换为p标签   
        enableAutoSave: false,  //启用自动保存
        initialFrameHeight: 300              
    });
    ue.ready(function() {
        //设置编辑器的内容
        //ue.setContent('hello');
        //获取html内容，返回: <p>hello</p>
        var html = ue.getContent();
        //获取纯文本内容，返回: hello
        var txt = ue.getContentTxt();
    });
    function delcon(tag){
		$('#shortTitle').val('');
		ue.setContent('')
		$('#'+tag).css('display','none')
	}
</script>
</body>
</html>