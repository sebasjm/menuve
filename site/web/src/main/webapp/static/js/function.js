

function initMenuNav(options){
    var marginWidth = options.marginWidth;
    var itemMenuList = [
        {
            id    : "canape",
            image : "/static/images/menus/canape.jpg"
        },
        {
            id    : "pan",
            image : "/static/images/menus/pan.jpg"
        }
    ];
    var _inTransition = false;
    
    $(".menus .navigationControl .prev").click(function(){
        if(!_inTransition){
            _inTransition = true;
            var _pcallBack = function(){
                _inTransition = false;
            }
            prevImage(_pcallBack);
        }
    });
    
    $(".menus .navigationControl .next").click(function(){
        if(!_inTransition){
            _inTransition = true;
            var _ncallBack = function(){
                _inTransition = false;
            } 
            nextImage(_ncallBack);
        }
    });
    
    function nextImage(trCallback){
        $(".menus .dataContent .itemDesc").hide();
        $(".menus .menuBkgContent .first").remove();
        $(".menus .menuBkgContent .current").animate({
            marginLeft: "-"+marginWidth
        },{
            duration : 1000,
            easing   : "easeOutExpo",
            queue    : true,
            complete : function(){
                var $element = $(this);
                var index = parseInt($(".menus .menuBkgContent .last").attr("data-index"));
                index = (index < itemMenuList.length - 1)? index + 1 : 0;
                $element.removeClass("current").addClass("first");
                $(".menus .menuBkgContent .last").removeClass("last").addClass("current");
                $(".menus .menuBkgContent").append('<div data-id="'+itemMenuList[index].id+'" data-index="'+index+'" data-speed="0.5" class="itemBkg resizable last dynamicBackground" style="background-image: url('+itemMenuList[index].image+'); width:'+marginWidth+';"></div>');
                showItemDetail($(".menus .menuBkgContent .current").attr("data-id"));
                dynamicBackgroundFx($(".menus .menuBkgContent .last"));
                trCallback();
            }
        });
    }
    
    function prevImage(trCallback){
        $(".menus .dataContent .itemDesc").hide();
        $(".menus .menuBkgContent .last").remove();
        $(".menus .menuBkgContent .first").animate({
            marginLeft: "0%"
        },{
            duration : 1000,
            easing   : "easeOutExpo",
            queue    : true,
            complete : function(){
                var $element = $(this);
                var index = parseInt($element.attr("data-index"));
                index = (index > 0)? index - 1 : itemMenuList.length - 1;
                $(".menus .menuBkgContent .current").removeClass("current").addClass("last");
                $element.removeClass("first").addClass("current");
                $(".menus .menuBkgContent").prepend('<div data-id="'+itemMenuList[index].id+'" data-index="'+index+'" data-speed="0.5" class="itemBkg resizable first dynamicBackground" style="background-image: url('+itemMenuList[index].image+'); width:'+marginWidth+'; margin-left:-'+marginWidth+'"></div>');
                showItemDetail($(".menus .menuBkgContent .current").attr("data-id"));
                dynamicBackgroundFx($(".menus .menuBkgContent .first"));
                trCallback();
            }
        });
    }
    
    function showItemDetail(id){
        var _id = id;
        $(".menus .dataContent #"+_id+"ItemDesc").fadeIn();
    }
}

function initMenuActive(){
    var $home    = $("#content .home"),
        $menu    = $("#content .menu"),
        $events  = $("#content .events"),
        $contact = $("#content .contact"),
        sectionsRate = {};
    
    function initRates(){
    
        sectionsRate = {
            home : {
                min: 0,
                max: $home.height()
            },
            menu : {
                min: $menu.offset().top,
                max: $menu.offset().top + $menu.height()
            },
            events : {
                min: $events.offset().top,
                max: $events.offset().top + $events.height()
            },
            contact : {
                min: $contact.offset().top,
                max: $contact.offset().top + $contact.height()
            }
        };
    }
    
    
    function activeMenu(){
        var posY = scrollTop();
        
        for(var section in sectionsRate){
            var _section = sectionsRate[section];
            if(posY > _section.min && posY < _section.max){
                $(".header .navButtons").removeClass("current");
                $(".header .navButtons[data-name="+section+"]").addClass("current");
                break;
            }
        }
    }
    
    initRates();
    activeMenu();
    
    $(window).scroll(function(){
        activeMenu();
    });
    
    $(window).resize(function(){
        initRates();
    });
}

function initBackgrounds(){
    var currentScrollTop = 0;
    $.fx.off = false;
    initEventTooltip();
    var posY = scrollTop();
    initHeader();

    $.fn.backgroundScroll = function() {
        this.each(function(index, value) {
        var $self = $(this);
        var contentHeight = parseInt($self.offset().top);
        var speed = parseFloat($self.attr("data-speed"));
        $(window).bind('scroll', function(e) {
            var posY = scrollTop();
            var bpos = -parseFloat((posY - contentHeight) * speed).toFixed(1);
            $self.css({'backgroundPosition': '0 ' + bpos  + 'px'});
        });
      });
      return this;
    };

    $(".dynamicBackground").each(function(){
        $(this).backgroundScroll();
    });

    $(window).bind("scroll", function(e) {
        currentScrollTop = $(window).scrollTop();
        var menuScrollTop = $(".menu").offset().top;
        var eventScrollTop = $(".events").offset().top;
        
        if(currentScrollTop >= menuScrollTop && currentScrollTop < eventScrollTop){
            $(".menu .menuListContent").fadeIn("fast");
        }else{
            $(".menu .menuListContent").fadeOut("fast");
        }
        
        
    });
    
    function initHeader(){
        $(".header .navButtons").click(function(){
            var section = $(this).attr("data-name");
            posY = $("."+section+" .markup").offset().top;
            $("html, body").stop().animate({scrollTop:posY}, 2000, "easeInOutExpo");
        });
    }
    
    initMenuActive();
   
}

function scrollTop() {

    if(window.pageYOffset){
        return window.pageYOffset;
    }
    else {
        return  Math.max(document.body.scrollTop,document.documentElement.scrollTop);
    }
    

}

 function dynamicBackgroundFx($element){
            var posY = scrollTop();
            var _$element = $element;
            var contentHeight = parseInt(_$element.offset().top);
            var speed = parseFloat(_$element.attr("data-speed"));
            var aceleration = (posY - contentHeight) * speed;
            var backgroundPosition = (($element).hasClass("rightDirection"))? "right "+aceleration+"px" : "center "+aceleration+"px";
            _$element.css("background-position", backgroundPosition);
        
}

function initEventTooltip(){
    $(".events td.eventDay").mouseover(function(){
        var posX = $(this).offset().left + ($(this).width() / 2);
        var posY = $(this).offset().top;
        var name = $(this).attr("data-name");
        $(".events .tooltipContent .tooltip label").text(name);
        
        var itemHeight = $(".events .tooltipContent .tooltip").outerHeight() + 10;
        var itemWidth = $(".events .tooltipContent .tooltip").outerWidth() / 2;
        $(".events .tooltipContent .tooltip").css("top", posY - itemHeight+"px").css("left",posX - itemWidth +"px").fadeIn("fast");
        
    }).mouseout(function(){
        $(".events .tooltipContent .tooltip").hide();
    });
}

function initMenuSlider(){
    
    var itemsArray = [1,2,3,4,5,6,7,8,9];
    $(".menu .itemSlider").css("opacity", "1");
    
    $(".menu .itemLabel").click(function(){
        var itemLabel = $(this).attr("data-name");
        $(".menu .itemLabel").removeClass("current");
        $(this).addClass("current");
        hideShowItems(itemLabel);
    });
    
    $(".menu .menuListContent .menuTab").click(function(){
        var posTop = ($(this).hasClass("open"))? "-50px" : "0";
            
        
        $(".menu .menuListContent").animate({
                top: posTop
            },{
                duration: 200,
                queue: true,
                easing: "easeOutExpo",
                complete: function(){
                    if($(".menu .menuListContent .menuTab").hasClass("open")){
                        $(".menu .menuListContent .menuTab").removeClass("open");
                    }else{
                        $(".menu .menuListContent .menuTab").addClass("open");
                    } 
                }
            });
    });
    
    
    
    function hideShowItems(itemLabel){
        
        var shuffleArray = shuffle([1,2,3,4,5,6,7,8,9]);
        
        var _rshcallBack = function(){
            $.ajax({
                url: "resto/menu/{0}/{1}.json".format(params.pageName, itemLabel),
                success: function(response){
//                    $(".menu .itemSlider.hidden").remove();
                    shuffleArray = shuffle([1,2,3,4,5,6,7,8,9]);
                    response.category = itemLabel;
                    $('#sliderContent').html( tmpl('menuestemplate', response) );
                    randomShowHide(1);
                }
            });
        };
        
        randomShowHide(0, _rshcallBack);
        
        
        function randomShowHide(opacity, rshCallback){
            var itemSelected = shuffleArray.pop();
            if($(".menu .itemSlider[data-index="+itemSelected+"]").length > 0){
                $(".menu .itemSlider[data-index="+itemSelected+"]").animate({
                    opacity : opacity
                },{
                    duration: 20,
                    queue: true,
                    easing: "easeOutExpo",
                    complete: function(){
                                    if(opacity == 0)
                                        $(".menu .itemSlider").addClass("hidden");
                                    randomShowHide(opacity, rshCallback);
                                }
                });
            }else{
                if(rshCallback) 
                    rshCallback();
            }
        }
    }
}

function doRequest(_url,data,_onsuccess) {
    var target = (data && data.target)? data.target : "#content";
    var _data = (data && data.values)? data.values : "";
    $.post(_url, _data,function(response){
        $(target).html(response);
        if(_onsuccess)
            _onsuccess();
    });
}

function shuffle(array){
    var tempSlot;
    var randomNumber;
    for(var i =0; i != array.length; i++){
            randomNumber = Math.floor(Math.random() * array.length);
            tempSlot = array[i];
            array[i] = array[randomNumber];
            array[randomNumber] = tempSlot;
    }
    return array;
}

function getMap(lat, lng) {
    
    var cm = new google.maps.LatLng(lat, lng);
    var myOptions = {
      zoom: 15,
      center: cm,
      scrollwheel: false,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("mapsContainer"), myOptions);
    
  var image = 'static/images/gallery/contact/marker.png';
  var cmIcon = new google.maps.LatLng(lat + 0.000011, lng + 0.000011);
  var beachMarker = new google.maps.Marker({
      position: cmIcon,
      map: map,
      icon: image
  });

}
