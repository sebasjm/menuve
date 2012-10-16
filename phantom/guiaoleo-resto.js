// Read the Phantom webpage '#intro' element text using jQuery and "includeJs"
var system = require('system');
var client = require('webpage').create();

client.injectJs('lib/jquery.min.js');

client.onConsoleMessage = function(msg) {
    console.log(msg);
};
/*
*/
var pages = new Array;
var resto = new Object;
var handler = new Object;

handler.get_resto_data = function (resto) {
    var restoDatos = $(".restoDatos");
    resto.nombre = restoDatos.find(".restoDatosNombre").text().replace(/\s+/g, ' ');
    resto.direccion = restoDatos.find(".restoDatosDireccion").text().replace(/\s+/g, ' ');
    resto.lugar = restoDatos.find(".restoDatosDireccion > a").attr("href");
    resto.cocina = new Array;
    restoDatos.find(".restoDatosCocina > a").each(function(i,e){
        resto.cocina.push( $(e).attr("href") );
    });
    resto.contacto = restoDatos.find(".restoDatosTelefono").contents()[0].data.split(' ').join('').trim();
    resto.contactos = new Array; resto.site = new Array; resto.homepage = new Array;
    restoDatos.find(".restoDatosTelefono > a[href] ").each(function(i,e){
        var href = $(this).attr('href');
        resto.contactos.push(href)
        if ( !!href.match('restaurante/urlredirect/nombreClave') ) resto.site.push(href);
    });
    resto.gallery = new Array; resto.images = new Array;
    $(".gallery").find("a").each(function(i,e){
        resto.gallery.push($(this).attr('href'));
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
    return resto;
}

handler.get_site = function(resto){
    for (var i in resto.site) {
        pages.push({
            url:'http://www.guiaoleo.com.ar/'+resto.site[i],
            extractor: handler.get_resto_homepage
        });
    }
    for (var j in resto.gallery) {
        pages.push({
            url:'http://www.guiaoleo.com.ar/'+resto.gallery[j],
            extractor: handler.get_resto_images
        });
    }
}

handler.get_resto_homepage = function(resto) {
    resto.homepage.push( $("iframe").attr("src") );
    return resto;
}

handler.get_resto_images = function(resto) {
    resto.images.push( $("#galeria_img").attr("src") );
    return resto;
}

function next() {
    console.log("pages -> " + JSON.stringify(pages));
    var the_page = pages.pop();
    if (!the_page) {
        console.log("resto = " + JSON.stringify(resto,undefined,3));
        phantom.exit();
        return;
    }
    console.log("//go for page " + JSON.stringify(the_page,undefined,3));
    client.open(the_page.url, function(status) {
        if ( status === "success" ) {
            resto = client.evaluate(the_page.extractor,resto);
            if (!!the_page.done) the_page.done(resto);
            next();
        } else {
            console.log("could not load " + e + " st " + status);
        }
    });
}

pages.push({
    url:system.args[1],
    extractor: handler.get_resto_data,
    done: handler.get_site
});

next();


