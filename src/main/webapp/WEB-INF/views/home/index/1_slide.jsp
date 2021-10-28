<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="https://han3283.cafe24.com/js/lightslider/css/lightslider.css"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="https://han3283.cafe24.com/js/lightslider/js/lightslider.js"></script>

<script type="text/javascript">
    $(document).ready(function(){
        $(".slider").lightSlider({
            mode: 'slide',
            loop: true,
            auto: true,
            keyPress: false,
            pager: false,
            speed: 1500,
            pause: 3500
        });
    });
</script>

<div class="slide">
    <div class="slide-wrap">
        <div class="slide-content">
            <ul id="slider" class="slider">
                <li class="item1">
                    <h3></h3>
                </li>
                <li class="item2">
                    <h3></h3>
                </li>
                <li class="item3">
                    <h3></h3>
                </li>
                <li class="item4">
                    <h3></h3>
                </li>
                <li class="item5">
                    <h3></h3>
                </li>
                <li class="item6">
                    <h3></h3>
                </li>
            </ul>
        </div>
    </div>
</div>
