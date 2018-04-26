
/// <summary>辅助方法:格式化字符串</summary>
window.stringFormat = function() {
    if (arguments.length == 0)
        return null;

    var str = arguments[0];

    for (var i = 1; i < arguments.length; i++) {
        var reg = new RegExp('\\{' + (i - 1) + '\\}', 'gm');

        str = str.replace(reg, arguments[i]);
    }

    return str;
}

/// <summary>parseURL</summary>
window.parseURL =  function(url) {
    var a = document.createElement('a');
    a.href = url;
    return {
        source: url,
        protocol: a.protocol.replace(':', ''),
        host: a.hostname,
        port: a.port,
        query: a.search,
        params: (function () {
            var ret = {},
                seg = a.search.replace(/^\?/, '').split('&'),
                len = seg.length, i = 0, s;
            for (; i < len; i++) {
                if (!seg[i]) { continue; }
                s = seg[i].split('=');
                ret[s[0]] = s[1];
            }
            return ret;
        })(),
        file: (a.pathname.match(/\/([^\/?#]+)$/i) || [, ''])[1],
        hash: a.hash.replace('#', ''),
        path: a.pathname.replace(/^([^\/])/, '/$1'),
        relative: (a.href.match(/tps?:\/\/[^\/]+(.+)/) || [, ''])[1],
        segments: a.pathname.replace(/^\//, '').split('/')
    };
}
