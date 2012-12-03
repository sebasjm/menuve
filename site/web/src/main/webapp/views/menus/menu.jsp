
<div class="menu" style="background-image: url('{%=resto.profile.menuBkg%}')">
    <div class="clearfix">
        <div class="menuListContent">
            <div class="menuGroup clearfix">
                {% for(var i in resto.menu) { %}
                    <div class="itemLabel" data-name="{%= i %}"> {%= resto.menu[i].name %} </div>
                {% } %}
            </div>
            <div class="menuTab">
                <div class="menuTitle">menu</div>
            </div>
        </div>
        
        <div class="navigationControl">
            <div class="prev"></div>
            <div class="next"></div>
        </div> 
        <div class="clearfix"></div>
        <div class="markup"></div>
        <div id="sliderContent" class="sliderContent clearfix">
            <%@include file="menuList.jsp" %>
        </div>
    </div>        
</div>

<script type="text/javascript">
  $(document).ready(initMenuSlider);
</script>
