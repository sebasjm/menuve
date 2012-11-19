var Tonel = Tonel || {};

Tonel.Home=(function(e,g){
    function init(options){
        _initBackgrounds();
    }
    
    function _initBackgrounds(){
        $.fx.off = false;
        var posY = Tonel.Utils.scrollTop();
        initHeader();
/*
        if (window.addEventListener) {  
            // IE9, Chrome, Safari, Opera  
            window.addEventListener("mousewheel", MouseWheelHandler, false);  
            // Firefox  
            window.addEventListener("DOMMouseScroll", MouseWheelHandler, false);  
        }else {
            // IE 6/7/8  
            window.attachEvent("onmousewheel", MouseWheelHandler);  
        }

        function MouseWheelHandler(e) {  
            e.preventDefault();
            var e = window.event || e; // old IE support  
            var delta = Math.max(-1, Math.min(1, (e.wheelDelta || -e.detail))); 
            if(delta == 1 && posY > 0 || delta == -1 && posY < ($("#content").height() - $(window).height())){
                delta = delta * 70 * -1;
                posY += delta;
                $("html, body").stop().animate({scrollTop:posY}, 800, "easeOutExpo");
            }
        }
*/
        $(".dynamicBackground").each(function(){
            $(this).backgroundScroll();
        });

        function initHeader(){
            $(".home .verticalSelector").click(function(){
                $(".home .verticalSelector").removeClass("current");
                $(this).addClass("current");
                var section = $(this).attr("data-name");
                posY = $("."+section+" .markup").offset().top;
                $("html, body").stop().animate({scrollTop:posY}, 2000, "easeInOutExpo");
            });
        }

    }
    
    return{
        init : init 
    }
}(jQuery));