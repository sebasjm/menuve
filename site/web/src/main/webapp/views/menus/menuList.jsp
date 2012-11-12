{% 
var category =  obj.category ? obj.category : 'bebidas';
var list = obj.menu ? obj.menu[category].menuList : obj[category].menuList;
for(var index in list) { 
    var plato = list[index]; %}

<div class="itemSlider grid_3" data-index="{%=index%}">
    <div class="imgContent">
        <img class="menuImg"  src="static/images/resto/{%=resto.name%}/menu/{%=category%}/{%=index%}.jpg"/>
    </div>
    <div class="boxDesc">
        <div class="boxOverlay"></div>
        <div class="infoBox">
            <div class="title">{%=plato.name%}</div>
            <div class="description">{%=plato.description%}</div>
        </div>
    </div>
</div>

{% } %}
