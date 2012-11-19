
var Tonel = Tonel || {};

Tonel.Utils = (function ($, undefined){
    
    function scrollTop() {

        if(window.pageYOffset){
            return window.pageYOffset;
        }
        else {
            return  Math.max(document.body.scrollTop,document.documentElement.scrollTop);
        }

    }
    
    function dynamicBackgroundFx($element){
        $(window).bind("scroll", function(e) {
            var posY = scrollTop();
            var _$element = $element;
            var contentHeight = parseInt(_$element.offset().top);
            var speed = parseFloat(_$element.attr("data-speed"));
            var aceleration = parseFloat((posY - contentHeight) * speed).toFixed(1);
            var backgroundPosition = "0 "+aceleration+"px";
            _$element.css("backgroundPosition", backgroundPosition);
        });
    }
    
    $.fn.backgroundScroll = function() {
      this.each(function(index, value) {
        var $self = $(this);
        var contentHeight = parseInt($self.offset().top);
        var speed = parseFloat($self.attr("data-speed"));
        $(window).bind('scroll', function(e) {
            var posY = scrollTop();
            var bpos = -parseFloat((posY - contentHeight) * speed).toFixed(1);
            $self.css({'backgroundPosition': '0 ' + bpos  + 'px' });
        });
      });
      return this;
    };
    
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
    
    return{
        scrollTop           : scrollTop,
        dynamicBackgroundFx : dynamicBackgroundFx,
        doRequest           : doRequest,
        shuffle             : shuffle,
        getMap              : getMap
    }
}(jQuery));

