<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="events dynamicBackground" data-speed="0.5" data-pos="0" style="background-image: url('{%= resto.profile.eventsBkg %}')">
    <div style="position: relative;">
        <div class="markup"></div>
    </div>
    <div class="container_12">
        <div class="contentBox grid_5">
            <div class="eventContent">
                <h1 class="mainTitle">Eventos</h1>
                <div id="calendarContent" class="calendarContent">
                    <div class="monthContent clearfix">
                        <label class="monthName">OCT 2012</label>
                        <div class="prevMonth"></div>
                        <div class="nextMonth"></div>
                    </div>
                    
                    <div class="daysContent">
                        <table class="daysTitle">
                            <tr>
                                <td>L</td> <td>M</td> <td>M</td> <td>J</td> <td>V</td> <td>S</td> <td>D</td>
                            </tr>
                        </table>

                        <table class="bodyCalendar">
                            <tr>
                                <td>1</td> <td>2</td> <td>3</td> <td>4</td> <td>5</td> <td>6</td> <td>7</td>
                            </tr>
                            <tr>
                                <td>8</td> <td>9</td> <td>10</td> <td>11</td> <td>12</td> <td>13</td> <td>14</td>
                            </tr>
                            <tr>
                                <td>15</td> <td>16</td> <td>17</td> <td>18</td> <td class="eventDay" data-name="Jazz Night" data-description="Disfrute del mejor Jazz contemporaneo junto a la variedad de bebidas y aperitibos especialmente seleccionadas." >19</td> <td>20</td> <td>21</td>
                            </tr>
                            <tr>
                                <td>22</td> <td>23</td> <td>24</td> <td>25</td> <td>26</td> <td>27</td> <td>28</td>
                            </tr>
                            <tr>
                                <td>29</td> <td>30</td> <td>31</td> <td></td> <td></td> <td></td> <td></td>
                            </tr>
                        </table>
                    </div>
                    <div class="tooltipContent">
                        <div class="tooltip">
                            <label></label>
                            <div class="bottomArrow"></div>
                        </div>
                    </div>
                </div>
                
                <div class="divisor"></div>
                
                <div class="eventBox">
                    <h2 class="title">Jazz Night</h2>
                    <p class="desc">Disfrute del mejor Jazz contemporaneo junto a la variedad de bebidas y aperitibos especialmente seleccionadas.</p>
                    <ul class="additionalData">
                        <li class="">Viernes 19 de Octubre 22:00hs</li>
                        <li class="">Reservar con anticipación</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>




