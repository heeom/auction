<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">

google.charts.load('current', {'packages':['corechart','bar','Linechart']});
google.charts.setOnLoadCallback(drawChart_01);
google.charts.setOnLoadCallback(drawChart_02);
google.charts.setOnLoadCallback(drawChart_03);

function drawChart_01() {
	var data = google.visualization.arrayToDataTable([
		['keywords','count']
		${keyword }
	]);
	var options = {
			title:"땅땅땅 인기 검색 키워드",
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart'));

	chart.draw(data,options);
}

function drawChart_02() {
	var data2 = google.visualization.arrayToDataTable([
		['날짜', '가입자']
		${numOfuser }
	]);
	var options = {
			title:"땅땅땅 일별 가입자 수",
			colors:['#fc5230']

	};


	var chart_02 = new google.visualization.LineChart(document.getElementById('columnchart'));

	chart_02.draw(data2,options);
}

function drawChart_03() {
	var data = google.visualization.arrayToDataTable([
		['낙찰가','낙찰건수']
		${priceVar }
	]);
	var options = {
			title:"땅땅땅 낙찰 가격대별 건수",
			bar : {
				groupWidth : '50%'
			},
			colors:['#fc5230']

	};
	var chart_03 = new google.visualization.ColumnChart(document.getElementById('barchart'));

	chart_03.draw(data,options);
}
</script>

<div class="graph">
	<div class="chart_wrap">
	<div id="piechart" class="chart" style="width:350px;height:260px">
		</div>
		<div id="columnchart" class="chart" style="width:350px;height:260px">

		</div>

		<div id="barchart" class="chart" style="width:600px;height:260px">

		</div>
	</div>
</div>
