<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
    <title>Resto</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="views/staticLoader.jsp" %>


    <script>
            var params = get_params();
            (function( $ ){
                $.fn.serializeJSON=function() {
                    var json = {};
                    jQuery.map($(this).serializeArray(), function(n, i){
                        json[n['name']] = n['value'];
                    });
                    return json;
                };
            })( jQuery );
            function get_params() {
                var result = []; var idx;
                var list = window.location.search.split('?')[1]; if (!list) return result;
                list = list.split('#')[0]; if (!list) return result;
                list = list.split('&');
                for (idx in list) {
                    var par = list[idx].split('=');
                    if (!result[par[0]]) result[par[0]] = [];
                    result[par[0]].push(par[1] ? par[1] : null);
                }
                return result;
            }
            String.prototype.format = function() {
                var args = arguments;
                return this.replace(/{(\d+)}/g, function(match, number) {
                    return typeof args[number] != 'undefined'
                        ? args[number]
                    : match
                    ;
                });
            };
            function render(id,data) {
                  document.getElementById(id).innerHTML = tmpl(id,data?data:{});
            }
    </script>



    <script>
// Simple JavaScript Templating
// John Resig - http://ejohn.org/ - MIT Licensed
(function(){
  var cache = {};
  
  this.tmpl = function tmpl(str, data){
    // Figure out if we're getting a template, or if we need to
    // load the template - and be sure to cache the result.
    var fn = !/\W/.test(str) ?
      cache[str] = cache[str] ||
        tmpl(document.getElementById(str).innerHTML) :
      
      // Generate a reusable function that will serve as a template
      // generator (and which will be cached).
      new Function("obj",
        "var p=[],print=function(){p.push.apply(p,arguments);};" +
        
        // Introduce the data as local variables using with(){}
        "with(obj){p.push('" +
        
        // Convert the template into pure JavaScript
        str
          .replace(/[\r\t\n]/g, " ")
          .split("{%").join("\t")
          .replace(/((^|%})[^\t]*)'/g, "$1\r")
          .replace(/\t=(.*?)%}/g, "',$1,'")
          .split("\t").join("');")
          .split("%}").join("p.push('")
          .split("\r").join("\\'")
      + "');}return p.join('');");
    
    // Provide some basic currying to the user
    return data ? fn( data ) : fn;
  };
})();
    </script>



    <script>
    var resto = 0;
    if (params.pageName) $.ajax({
            url:'views/restos.json', 
            async: false,
            success: function(res,t,e){
                resto = res[params.pageName[0]];
                resto.name = params.pageName[0];
                document.body.innerHTML = tmpl('body', resto);
            }
        }) 
    else 
        document.body.innerHTML = tmpl('landingtemplate', resto);
    </script>


<script id="body" type="text/html">
    <%@include file="views/layout.jsp" %>
</script>
<script id="menuestemplate" type="text/html">
    <%@include file="views/menus/menuList.jsp" %>
</script>
<script id="landingtemplate" type="text/html">
    <%@include file="views/landing/landing.jsp" %>
</script>

</head>

<body>
</body>


<script>
    $(document).ready(initBackgrounds);
    $(document.body).addClass(resto.profile.layerStyle);
</script>

</html>
 
