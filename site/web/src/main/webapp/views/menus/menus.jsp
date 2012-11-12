
<div class="menus">
    <div class="menuBkgMask">
        <div class="menuBkgContent clearfix" style="width: 300%">
            <div data-id="pan" data-index="1" class="itemBkg resizable first dynamicBackground" data-speed="0.5" style="background-image: url('/static/images/menus/pan.jpg'); width: 34%; margin-left: -34%;"></div>
            <div data-id="canape" data-index="0" class="itemBkg resizable current dynamicBackground" data-speed="0.5" style="background-image: url('/static/images/menus/canape.jpg'); width: 34%;"></div>
            <div data-id="pan" data-index="1" class="itemBkg resizable last dynamicBackground" data-speed="0.5" style="background-image: url('/static/images/menus/pan.jpg'); width: 34%;"></div>
        </div>
    </div>
    <div class="dataContent">
        <div class="container_12">
            <div class="grid_7" style="height: 100%"></div>

            <div class="navigationControl">
                <div class="prev"></div>
                <div class="next"></div>
            </div>        

            <div class="contentBox grid_5">
                <div id="canapeItemDesc" class="itemDesc">
                    <div class="title">Pechuga<br/>atiernisada</div>
                    <div class="description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut</div>
                    <div class="price"></div>
                    <div class="socialBox"></div>
                </div>
                
                <div id="panItemDesc" class="itemDesc" style="display: none;">
                    <div class="title">Arroz con calamar</div>
                    <div class="description">Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut</div>
                    <div class="price"></div>
                    <div class="socialBox"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var options = {
        marginWidth : "34%"
    };
    initMenuNav(options);
</script>