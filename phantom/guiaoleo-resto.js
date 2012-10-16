// Read the Phantom webpage '#intro' element text using jQuery and "includeJs"
var system = require('system');

var page = require('webpage').create();

/*
page.onConsoleMessage = function(msg) {
    console.log(msg);
};
*/
var pages = new Array;
var resto = new Object;

pages.push({url:system.args[1],extractor: get_resto_data});
next();


function get_resto_data() {
                var restoDatos = $(".restoDatos");
                resto.nombre = restoDatos.find(".restoDatosNombre").text().replace(/\s+/g, ' ');
                resto.direccion = restoDatos.find(".restoDatosDireccion").text().replace(/\s+/g, ' ');
                resto.lugar = restoDatos.find(".restoDatosDireccion > a").attr("href");
                resto.cocina = new Array;
                restoDatos.find(".restoDatosCocina > a").each(function(i,e){
                        resto.cocina.push( $(e).attr("href") );
                });
                resto.contacto = restoDatos.find(".restoDatosTelefono").contents()[0].data.split(' ').join('').trim();
                resto.contactos = new Array;
                resto.site = new Array;
                restoDatos.find(".restoDatosTelefono > a[href] ").each(function(i,e){
                        var href = $(this).attr('href');
                        resto.contactos.push(href)
                        if ( !!href.match('restaurante/urlredirect/nombreClave') ) { 
			    resto.site.push(href);
			    pages.push({url:'http://www.guiaoleo.com.ar/'+href, extractor: get_resto_homepage});
			}
                });
		resto.horarios = new Array;
		restoDatos.find(".restoDatosHorarios").each(function(i,e){ 
			resto.horarios.push( $(this).text().replace(/\s+/g, ' ').replace(/horario:/i,''));
		});

		resto.mediosPago = new Array;
		restoDatos.find(".restoDatosMediosPago").find("img").each(function(i,e){
			resto.mediosPago.push( $(e).attr("title") );
		});
		resto.puntos = new Array;
		$(".calificaciones").find("ul.restoDatosRanking").find(".rankBar").parent().each(function(i,e){
			var punto = new Object;
			punto.nombre = $(e).find("label").text();
			punto.valor = $(e).find(".rank")[0].style.width.replace(/pt/,''); 
			resto.puntos.push(punto);
		});
		resto.calificaciones = new Array;
		$("#boxRecomendarEtiquetas").find(".rankBar").parent().each(function(i,e){
                        var punto = new Object;
                        punto.nombre = $(e).find("label").text();
                        punto.valor = $(e).find(".rank").text().replace(/ usuarios/g,'');
                        resto.calificaciones.push(punto);
		});
		resto.desc = new Array;
		$(".restoEspecificaciones > p").each(function(i,e){
			resto.desc.push( $(e).text().replace(/\s\s+/g,'') );
		});
		resto.precio = $(".colSide > .restoPrecios").text().replace(/\s\s+/g,'');
		resto.caracteristicas = new Array;
		$(".goLink").find("li:not(.disabled)").each(function(i,e){
			resto.caracteristicas.push( $(e).text().replace(/\s\s+/g,'') );
		});
}

function get_resto_homepage() {
	resto.homepage = $("iframe").attr("src");
}

function next() {
	var the_page = pages.pop();
	if (!the_page) {
		console.log("resto -> " + JSON.stringify(infor,undefined,3));
		phantom.exit();
	}
	page.open(the_page.url, function(status) {
	    if ( status === "success" ) {
		page.evaluate(function() {
		    the_page.extractor();	    
        	});
    		next();
   	    } else {
		console.log("could not load " + e + " st " + status);
    	    }
	});
}

