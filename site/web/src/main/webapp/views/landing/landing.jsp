<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="landing">
    <div class="headerLanding">
        <div class="logo"></div>
    </div>
    <div class="contentLanding">
        <div class="topSection dynamicBackground" data-speed="0.4">
            <div class="title"></div>
            <div class="featureImg dynamicBackground" data-speed="0.7" data-xpos="right"></div>
            <div class="bottomSection"></div>
        </div>
        <div class="landingContact container_12">
            <div class="grid_12 mainTitle"></div>
            <div class="clearfix">
                <div class="featureBullets">
                    <div class="clearfix">
                        <div class="bulletImg"></div>
                        <div class="featureBox">
                            <div class="featureTitle">Simple</div>
                            <div class="featureDesc">Administrar tu sitio web nunca fue tan fácil y rápido</div>
                        </div>
                    </div>
                    
                    <div class="clearfix">
                        <div class="bulletImg"></div>
                        <div class="featureBox">
                            <div class="featureTitle">Visual</div>
                            <div class="featureDesc">Seduzca con fotos de sus platos y comunique al instante su marca personal</div>
                        </div>
                    </div>

                    <div class="clearfix">
                        <div class="bulletImg"></div>
                        <div class="featureBox">
                            <div class="featureTitle">Social</div>
                            <div class="featureDesc">Incremente su presencia en todas las redes sociales desde un único punto de gestión</div>
                        </div>
                    </div>

                    <div class="clearfix">
                        <div class="bulletImg"></div>
                        <div class="featureBox">
                            <div class="featureTitle">Inteligente</div>
                            <div class="featureDesc">Analice la respuesta de sus comensales a sus promociones y servicios con estadísticas</div>
                        </div>
                    </div>
                </div>
                <div class="contactBox">
                    <div class="clearfix">
                        <div class="contactTitle">Contáctenos</div>
                        <div class="contactSocialButtons clearfix">
                            <div class="socialButton facebookLink"></div>
                            <div class="socialButton twitterLink"></div>
                            <div class="socialButton youtubeLink"></div>
                        </div>
                    </div>
                    <div class="contactDesc">
                        Queremos que <b>que cada dueño de restaurant gestione fácilmente su imagen llegando a todo el mundo y entendiendo a sus comensales</b>. <br/> <br/>
                        Acompañenos en el proceso de generar un producto que se adecue a sus espectativas y sea uno de los primeros en tomar ventaja. <br/> <br/>
                        El servicio está limitado a selectos clientes con intenciones de innovar y multiplicar la comunicación con sus comensales. Si está interesado, deje un contacto a continuación y nos pondremos en contacto con ud. <br/> <br/>
                    </div>
                    <div class="inputContent clearfix">
                        <div class="inputText">
                            <input type="text" placeholder="email o teléfono"/>
                        </div>
                        <div class="button"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footerLanding">
        <div class="divisor"></div>
    </div>
</div>

{%
    $(document).ready(initLanding);
%}

