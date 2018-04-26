/* 
*系统提示框
*/
;
$._winTip=$._winTip||{};
$._winTip.defaults={
	title:"提示信息",
    msg:"提示信息",
    src:"tishi.png",    
}
$._winTip.functions={
	init:function(options){
		var id="themisWrap"+new Date().getTime();
        var $box = $("<div class='themisWrap' id='"+id+"'></div>");//弹窗插件容器
        var $layer = $("<div class='themisGray'></div>");//遮罩层
        var $popBox = $("<div class='themis'></div>");//弹窗盒子
        var $ttBox = $("<h3 class='themistit'>"+options.title+"</h3>");//弹窗顶部区域
        var $winTipPic = $("<span class='winTipPic'></span>");
        var $pic = $("<img src="+options.src+">");
        var $conBox = $("<div class='themispay'></div>");//弹窗内容主体区
        var $txtBox = $("<div class='winTip'>"+options.msg+"</div>");//弹窗内容主体区
        var $ok = $("<button class='themissrue'></button>").text("确定");//确定按钮
        var $themisclose = $("<div class='themisclose' title='关闭'></div>");//关闭按钮
		//将盒子放入body中
        function creatDom(){
            $box.append($popBox);
            $popBox.append(
                $ttBox.append(
                    $winTipPic.append($pic)
                )
            ).append(
                $conBox.append(
                    $txtBox
                ).append(
                    $ok
                )
            ).append(
                $themisclose
            );
            $box.append($layer);
            $("body").append($box);            
        }
		creatDom();
        $popBox.css({'top':'30%'});
		function doClose(e){
            $(e.target).closest(".themisWrap").remove();
        }
		$themisclose.bind("click", function(e){   
				doClose(e);
        });
		$ok.bind("click", function(e){   
				doClose(e);
        });
	},
	reload:function(){
		window.location.reload();	
	},
	close:function(){
		//
	}
}
$.extend({
	winTip:function(options){
		var newoptions = $.extend(newoptions,$._winTip.defaults, options||{}); //默认值 
			$._winTip.functions.init(newoptions);
		}
});