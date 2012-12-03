{%
    var offersList = resto.offers.offersList;
%}

<div class="offers clearfix"  style="background: url('{%= resto.profile.offersBkg %}') repeat fixed 0 bottom transparent">
    <div class="markup"></div>
    <div class="title">Promociones</div>
    <div class="offersContent container_12">
        <div class="grid_12">
            <div class="navigationControl">
                <div class="leftButton"></div>
                <div class="rightButton"></div>
            </div>
            <div class="overflowContent">
                <div class="scrollContent">
                    <div class="offersSlider clearfix" style="width: {%= (offersList.size() * 234) %}px">
                        {% 
                            for(var i=0; i < offersList.size(); i++){
                        %}
                        <div class="itemOffer {%= offersList[i].offerTemplate %}">
                            <h1>{%= offersList[i].title %}</h1>
                            <h2>{%= offersList[i].subTitle %}</h2>
                            <div class="bottomContent">
                                <div class="desc">{%= offersList[i].description %}</div>
                                <div class="validity">{%= offersList[i].validity %}</div>
                            </div>
                        </div>
                        {%
                            }
                        %}
                    </div>
                </div>
            </div>
        </div>
        <div class="offersSlider"></div>
    </div>
</div>

<script type="text/javascript">
    initOffers();
</script>