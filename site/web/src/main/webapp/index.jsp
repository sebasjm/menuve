<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
<head>
    <title>Resto</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <%@include file="views/staticLoader.jsp" %>


    <script>
        var params = get_params();
        include_url_rewrite_params(params);
        var resto = 0;
        if (params.pageName) $.ajax({
                url:'views/restos.json', 
                async: false,
                success: function(res,t,e){
                    resto = res[params.pageName[0]];
                    resto.name = params.pageName[0];
                    $(function(){
                        document.body.innerHTML = tmpl('body', resto)
                        $(document).ready(initBackgrounds);
                        $(document.body).addClass(resto.profile.layerStyle);
                    });
                }
            }) 
        else 
            $(function(){
                document.body.innerHTML = tmpl('landingtemplate', {})
            });
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

</html>
 
