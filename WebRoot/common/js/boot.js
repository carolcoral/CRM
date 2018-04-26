/* 
* @Author: Marte
* @Date:   2016-08-04 10:49:18
* @Last Modified by:   Marte
* @Last Modified time: 2016-08-11 10:39:39
*/
$('.changepwd').on('click', function(event) {
   $('#updpwd').show();
});
/*$(window.parent.document).find("#iframepage").load(function(){
    var main = $(window.parent.document).find("#iframepage");
    var thisheight = $(document).height()+30;
    main.height(thisheight);
});*/
var wuorder = {
    //模拟单选。添加class;
    Addclass: function (name,event,Addclass){

        $(name).on(''+event,function(){
            $(this).addClass(Addclass).siblings().removeClass(Addclass);
        });
    },
    //Tab切换
    Tab: function (name,event,Addclass,tabclass){

        $(name).on(''+event,function(){

            $(this).addClass(Addclass).siblings().removeClass(Addclass) ;                    
            $(tabclass).eq($(this).index()).show().siblings().hide();

        });
    },
      //双击变为文本框
    Trantxt: function (name,event){
        $(name).on(''+event,function(){
            var o = $(name);
            var str = o.text();
            o.text("");
            var txt = $("<input placeholder='请输入内容'>");
            txt.val(str).appendTo(o).select();
        });
    },
  //至少选择一个checkbox
    LeastBox: function (name,event){
        $(name).on(''+event,function(){
        	$("input[type='checkbox']").each(function(){
    		    if(this.checked == false){		        
    		    	$.winTip({
    					title: "提示~~~",
    					msg: "至少选择一条信息",
    					src:"assets/img/tishi.png"
    				});
    		        return false;
    		    }
    		});
        });
    },
    //弹出隐藏层
    ShowDiv: function (show_div){

        document.getElementById(show_div).style.display='block';
    },
    //关闭隐藏层
    CloseDiv: function (show_div){
        document.getElementById(show_div).style.display='none';
    }
};

function setIframeHeight(iframe) {
	if (iframe) {
		var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
		if (iframeWin.document.body) {
			iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
		}
	}
};
	
window.onload = function () {
	setIframeHeight(document.getElementById('iframepage'));
};	

