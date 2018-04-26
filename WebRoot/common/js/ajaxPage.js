/*
* Version 1.0
* 2015-12-20 by sullivan
* AJAX Pager
*/
;
(function (window, $) {

    var sjPager = window.sjPager = {
        opts: {
            pageSize: 10,
            preText: "上一页",
            nextText: "下一页",
            firstText: "首页",
            lastText: "尾页",
            shiftingLeft: 3,
            shiftingRight: 3,
            preLeast: 2,
            nextLeast: 2,
            showFirst: true,
            showLast: true,
            cache:false,
            url: "",
            type: "POST",
            dataType: "JSON",
            searchParam: {},
            beforeSend: null,
            success: null,
            complete: null,
            error: function() {
                console.log("抱歉,请求出错,请重新请求！");
            },

            currentPage: null,
            totalCount: 0,
            totalPage: 0
        },
        pagerElement: null,
        commonHtmlText: {
            spanHtml: "<span class='{0}'>{1}</span>",
            pageIndexHtml: "<a href='javascript:void(0)' onclick='sjPager.doPage({0},{1},{2})'>{3}</a>",
            rightHtml: "<span class='text'> &nbsp; 共 {0} 页， 到第</span> <input type='text' id='txtToPager' value={1} /><span class='text'>页</span> <button id='btnJump' onclick='sjPager.jumpToPage();return false;' >跳转</button>",
            clearFloatHtml: "<div style='clear:both;'></div>",
            stringEmpty: ""
        },
        init: function (obj,op) {
            var This = this;

            This.opts = $.extend({}, This.opts, op);
            This.pagerElement = obj;

            This.doPage(This.opts.currentPage, This.opts.pageSize, This.opts.searchParam);

            return This.opts;
        },
        doPage: function (index,pageSize, searchParam) {
            var This = this;
            
            This.opts.currentPage = index;
            This.opts.pageSize = pageSize;
            This.opts.searchParam = searchParam;

            $.ajax({
                type: This.opts.type,
                cache:This.opts.cache,
                data: $.extend(This.opts.searchParam || {}, {
                    currentPage: This.opts.currentPage,
                    pageSize: This.opts.pageSize || 10
                }),
                dataType: This.opts.dataType,
                url: This.opts.url,
                beforeSend: function () {
                    This.opts.beforeSend();
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                	This.opts.error(XMLHttpRequest, textStatus, errorThrown);
                },
                success: function (data) {
                    This.opts.success(data);

                    //后台返回数据格式：{"total":0,"items":[]}
                    This.opts.totalCount = data.total;
                    This.getTotalPage();
                    if (This.opts.totalCount > 0 && This.opts.currentPage > 0) {
                        var pageTextArr = new Array;

                        This.createPreAndFirstBtn(pageTextArr);
                        This.createIndexBtn(pageTextArr);
                        This.createNextAndLastBtn(pageTextArr);
                        This.renderHtml(pageTextArr);
                    }
                },
                complete: function () {
                    This.opts.complete();
                }
            });
        },
        getTotalPage: function() {
            var This = this;

            This.opts.totalPage = Math.ceil(This.opts.totalCount / This.opts.pageSize);
        },
        createPreAndFirstBtn: function (pageTextArr) {
            var This = this;

            if (This.opts.currentPage == 1) {
                if (This.opts.showFirst)
                    pageTextArr.push(This.createSpan(This.opts.firstText, 'disenable'));

                pageTextArr.push(This.createSpan(This.opts.preText, 'disenable'));
            } else {
                if (This.opts.showFirst) {
                    pageTextArr.push(This.createIndexText(1, This.opts.firstText));
                }

                pageTextArr.push(This.createIndexText(This.opts.currentPage - 1, This.opts.preText));
            }
        },
        createNextAndLastBtn: function (pageTextArr) {
            var This = this;
            if (This.opts.currentPage == This.opts.totalPage) {
                pageTextArr.push(This.createSpan(This.opts.nextText, 'disenable'));

                if (This.opts.showLast)
                    pageTextArr.push(This.createSpan(This.opts.lastText, 'disenable'));
            } else {
                pageTextArr.push(This.createIndexText(This.opts.currentPage + 1, This.opts.nextText));
                if (This.opts.showLast)
                    pageTextArr.push(This.createIndexText(This.opts.totalPage, This.opts.lastText));
            }
        },
        createIndexBtn: function (pageTextArr) {
            /*
                前：当前页 > 偏移量 + 至少保留 + 1
                后：当前页 < 总页码 - 偏移量 - 至少保留
            */

            var This = this;

            var shiftingLeftStart = This.opts.shiftingLeft + This.opts.preLeast + 1;
            var shiftingRightStart = This.opts.totalPage - This.opts.shiftingRight - This.opts.nextLeast - 1;

            /*页码*/
            if (This.opts.currentPage > shiftingLeftStart) {
                for (i = 1; i <= This.opts.preLeast; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }

                pageTextArr.push(This.createSpan('...'));

                for (i = This.opts.currentPage - This.opts.shiftingLeft; i < This.opts.currentPage; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }

            } else {
                for (i = 1; i < This.opts.currentPage; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }
            }

            pageTextArr.push(This.createSpan(This.opts.currentPage, 'current'));

            if (This.opts.currentPage <= shiftingRightStart) {

                for (i = This.opts.currentPage + 1; i < This.opts.currentPage + 1 + This.opts.shiftingRight; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }

                pageTextArr.push(This.createSpan('...'));

                for (i = This.opts.totalPage - 1; i <= This.opts.totalPage; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }

            } else {
                for (i = This.opts.currentPage + 1; i <= This.opts.totalPage; i++) {
                    pageTextArr.push(This.createIndexText(i, i));
                }
            }
        },
        renderHtml: function (pageTextArr) {
            var This = this;

            var pageText = This.commonHtmlText.stringEmpty;

            for (var i = 0; i < pageTextArr.length; i++) {
                pageText += pageTextArr[i];
            }

            This.pagerElement.html(pageText).append(stringFormat(This.commonHtmlText.rightHtml, This.opts.totalPage, This.opts.currentPage)).append(This.commonHtmlText.clearFloatHtml);
        },
        createSpan: function (text, className) {
            var This = this;

            return stringFormat(This.commonHtmlText.spanHtml, className ? className : This.commonHtmlText.stringEmpty, text);
        },
        createIndexText: function (index, text) {
            var This = this;
            return stringFormat(This.commonHtmlText.pageIndexHtml, index, This.opts.pageSize, JSON.stringify(This.opts.searchParam), text);
        },
        jumpToPage: function() {
            var This = this;

            var $txtToPager = $("#txtToPager", This.pagerElement);
            var index = parseInt($txtToPager.val());

            if (!isNaN(index) && index > 0 && index <= This.opts.totalPage) {
                This.doPage(index, This.opts.pageSize, This.opts.searchParam);
            } else {
                $txtToPager.focus();
            }
        }
    }

    $.fn.sjAjaxPager = function (option) {
        return sjPager.init($(this),option);
    };

})(window, jQuery);