<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="contact clearfix dynamicBackground" data-speed="0.5" style="background-image: url({%= resto.profile.contactBkg%})">
    <div class="markup"></div>
    <div class="container_12">
        <div class="contentBox grid_5">
            <div class="contactWrp">
                <div class="mainTitle">Contactenos</div>
                <div class="divisor"></div>
                <div class="contactModule direction">
                    <div class="label">Dirección</div>
                    <div class="desc">Av. Juan B. Justo 876, Palermo</div>
                </div>
                <div class="contactModule phone">
                    <div class="label">Telefono</div>
                    <div class="desc">(+5411) 4425-9877/9878</div>
                </div>
                <div class="contactModule email">
                    <div class="label">Email</div>
                    <div class="desc">info@goldenbar.com</div>
                </div>
                
            </div>
            <div class="socialButtons clearfix">
                <div class="socialButton facebookButton"></div>
                <div class="socialButton twitterButton"></div>
                <div class="socialButton youtubeButton"></div>
            </div>
        </div>
        <div class="mapContent grid_7">
            <div id="mapsContainer"></div>
        </div>
    </div>
</div>

{% 
    $(document).ready(function(){getMap(-34.552344, -58.449948)}); 
%}

