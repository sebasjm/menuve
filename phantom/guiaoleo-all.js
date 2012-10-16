// Read the Phantom webpage '#intro' element text using jQuery and "includeJs"
var system = require('system');

var page = require('webpage').create();

page.onConsoleMessage = function(msg) {
    console.log(msg);
};


page.open("http://www.guiaoleo.com.ar/search/list/todos?page="+system.args[1], function(status) {
    if ( status === "success" ) {
        page.includeJs("http://ajax.googleapis.com/ajax/libs/jquery/1.6.1/jquery.min.js", function() {
            page.evaluate(function() {
		var pepe = new Array; 
		$.each($(".restoDatosNombre").children('a'), function(i,a){ 
			console.log("resto -> " + a.href);
		});
            });
            phantom.exit();
        });
    }
});
