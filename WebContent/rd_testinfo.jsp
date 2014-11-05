<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>RD自测试信息</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <link rel="stylesheet" href="css/rd.css" type="text/css">
    
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
   <script type="text/javascript" src="jqplot/jqplot.pieRenderer.min.js"></script>  
     <script type="text/javascript" src="jqplot/highcharts.js"></script> 
     <script type="text/javascript" src="jqplot/exporting.js"></script>
   


<script>
//taps切换         
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,taps_conbox,shijian) {
        $(taps_conbox).find("li").hide();
        $(tabtit).find("li:first").addClass("thistap").show(); 
        $(taps_conbox).find("li:first").show();
    
        $(tabtit).find("li").bind(shijian,function(){
          $(this).addClass("thistap").siblings("li").removeClass("thistap"); 
            var activeindex = $(tabtit).find("li").index(this);
            $(taps_conbox).children().eq(activeindex).show().siblings().hide();
            return false;
        });
    
    };
    /*调用方法如下：*/
    $.jqtab("#taps","#taps_conbox","click");
});
$(function() {
$( "#datepicker" ).datepicker({
  dateFormat:'yy-mm-dd',
  changeMonth: true,
  changeYear: true,
  showWeek: true,
  firstDay: 1
});
});
   //下拉选择框
	$(function(){
		$("#c").addClass('down_chartQuota');
		$("#c").toggle(function(){
			var el = $(this);
			el.removeClass("#p1").addClass('up_chartQuota');
			$(".chartOptionsFlowTrend").css("display","inline-block");
		},function(){
		$(".chartOptionsFlowTrend").css("display","none");	
		$("#c").removeClass('up_chartQuota').addClass('down_chartQuota');});
				}); 
        
       $(function(){
    		$("#d").addClass('down_chartQuota');
    		$("#d").toggle(function(){
    			var el = $(this);
    			el.removeClass("#p2").addClass('up_chartQuota');
    			$(".chartOptionsFlowTrend").css("display","inline-block");
    		},function(){
    		$(".chartOptionsFlowTrend").css("display","none");	
    		$("#d").removeClass('up_chartQuota').addClass('down_chartQuota');});
    				});        
   
$(document).ready(function(){ 
    	$("[name = checkbox]:checkbox").prop("checked", 'checked');
    	$(".check1").change(function() { 
    	   $("#caselist1 tr").each(function(){
    	        var value = $(this).find("td").eq(1).text().toUpperCase();
    	 
    	        if(value.length > 0){
    	        if($("#"+value+"1").attr("checked") != "checked"){
    	            $(this).hide();
    	        }
    	        else{
    	                $(this).show(); 
    	        }
    	        }
    	    });
    	}); 
    	}); 
    
$(document).ready(function(){ 
    	$(".check2").change(function() { 
    	$("#caselist2 tr").each(function(){
    	     var value = $(this).find("td").eq(1).text().toUpperCase();    	 
    	     if(value.length > 0){
    	     if($("#"+value+"2").attr("checked") != "checked"){
    	         $(this).hide();
    	     }
    	     else{
    	         $(this).show(); 
    	     }
    	        }
    	    });
    	}); 
    	}); 
$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie1').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '执行', align: 'center'}, 
				tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
	                }, useHTML:true},
			plotOptions: { 
			pie: { size:'95%', 
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share',    data: [ ['Firefox', 100.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.9], ['Others', 0.0] ] }] }); }); });

$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie2').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '未执行', align: 'center'}, 
			tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
	                }, useHTML:true},
			plotOptions: { 
			pie: { size:'95%', 
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share',    data: [ ['Firefox', 45.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.2], ['Others', 0.7] ] }] }); }); });

$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie3').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '执行', align: 'center'}, 
			tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
	                }, useHTML:true},
			plotOptions: { 
			pie: {size:'95%',  
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share',    data: [ ['Firefox', 45.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.2], ['Others', 0.7] ] }] }); }); });

$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie4').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '未执行', align: 'center'}, 
			tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
	                }, useHTML:true},
			plotOptions: { 
			pie: { size:'95%', 
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share',    data: [ ['Firefox', 45.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.2], ['Others', 0.7] ] }] }); }); });	 

$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie5').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '执行', align: 'center'}, 
			tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+'%';
	                }, useHTML:true},
			plotOptions: { 
			pie: { size:'95%', 
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share',    data: [ ['Firefox', 45.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.2], ['Others', 0.7] ] }] }); }); });

$(function () { var chart; $(document).ready(function () 
		{ // Build the chart 
		$('#pie6').highcharts({ 
				chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
			title: { 
				text: '未执行', align: 'center'}, 
			tooltip: { pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>' }, 
			    legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -10,y: 60,borderWidth: 0,
	                labelFormatter: function() {
	                    return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
	                }, useHTML:true},
			plotOptions: { 
			pie: { size:'95%', 
				allowPointSelect: true, //	是否允许使用鼠标选中数据点
					   cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
					   dataLabels: { enabled: false }, //图上是否显示数据标签
					   showInLegend: true,
						        point:{
						            events:{
						                legendItemClick:function(){
						                    this.select();
						                    this.show();},}}}},
			series: [{ type: 'pie', name: 'Browser share', data: [ ['Firefox', 45.0], ['IE', 26.8], 
				            { name: 'Chrome', y: 12.8,  selected: true }, 
				            ['Safari', 8.5], ['Opera', 6.2], ['Others', 0.7] ] }] }); }); });
				            
</script>

</head>

<body>
<s:form action="monkey" theme="simple">
        <div class="page">
            <div class="sidebar">
               <img src="images/logo.png" alt="logo">
                <br><br><ul>
                    <li>
                       <a href="project.action" 
                          class="button blue medium">工程详细数据</a>
                    </li>
                      <li>
                       <a href="monkey.jsp" 
                          class="button blue medium">Monkey测试</a>
                    </li>
                    <li>
                        <a href="login.action" class="button blue medium">返回主页</a>
                    </li>
                </ul>              
            </div>
           
            <div class="right">
                <div class="right_whole"> 
                <div class="menu">
  						<ul><li><input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">天津</label><li>
  					    <li><input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">北京</label></li>
 					    <li><input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">上海</label></li></ul>
				    </div>			            
<!-- taps -->   
        <div class="tapsbox"> 
            <ul class="taps" id="taps">
               <li><a href="#">测试详细信息</a></li>
       		<li><a href="#">执行人员名单</a></li>
       		<li><a href="#">执行人员统计</a></li></ul>          
            <ul class="taps_conbox" id="taps_conbox">
                <li class="taps_con">
    			<p> Date: <input type="text" id="datepicker"></p>
			
<!--case表格统计-->  
                          <table class="orderedcaselist" width="900px" id="caselist1">
                               <thead> <tr>
                                   <th width="2%" rowspan="2">姓名</th>
                                   <th width="3%" rowspan="2">测试时长(h)</th>
                                   <th width="15%" colspan="2">首次出错</th>
                                   <th width="20%" colspan="3">JavaCrash</th>
                                   <th width="20%" colspan="3">NativeCrash</th>
                                   <th width="20%" colspan="3">ANR</th>
                                </tr>
                              
                                    <tr>
                             		<th>最小时长</th>
                                    <th>错误类型</th>
                            
                                    <th width="5%">首次出现时长</th>
                                    <th width="5%">数目</th>
                                    <th width="10%">模块列表</th>
                                  
                                     <th width="5%">首次出现时长</th>
                                    <th width="5%">数目</th>
                                    <th width="10%">模块列表</th>
                                   
                                    <th width="5%">首次出现时长</th>
                                    <th width="5%">数目</th>
                                    <th width="10%">模块列表</th>
                             </tr></thead>   
                                    <tbody><tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
                              <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                              <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                             <tr>
                                    <td>张三</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                              <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>com.android.soundrecorder，com.android.camera2，com.android.music</td>
                             </tr>
                                                 <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
                                                 <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
                                                 <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
                                   <tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
								<tr>
                                    <td>李四</td>
                                    <td>2.3</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>5</td>
                                    <td>5</td>
                                    <td>music</td>
                                    <td>8.63</td>
                                    <td>7</td>
                                    <td>phone</td>
                                    <td>7.3</td>
                                    <td>8</td>
                                    <td>music</td>
                             </tr>
               </table></li>
            
           			<li class="taps_con" id="r"> 
   		            <div class="select_checkBox" >
				<div class="chartQuota"  id="d">
				<p id="p2">选择指标</p>
			    </div><br>		
			    <div class="chartOptionsFlowTrend">
				<input type="checkbox" name="checkbox" value="PASS" class="check2" id="PASS2"><span>Pass</span>
				<input type="checkbox" name="checkbox" value="FAIL" class="check2" id="FAIL2" ><span>Fail</span>
				<input type="checkbox" name="checkbox" value="NA" class="check2" id="NA2" ><span>NA</span>
				<input type="checkbox" name="checkbox" value="BLOCK" class="check2"  id="BLOCK2"><span>Block</span>
				</div>
				</div>          
             <table width="90%" border="1">
  							<tr>
    						<th width="5%">排名</th>
    						<th width="15%">姓名</th>
    						<th width="15%">本周测试天数</th>
    						<th width="15%">累计测试天数</th>
    						<th width="15%">今日测试情况</th>
    						<th width="25%">小组</th>
  							</tr><tr>
    						<td>张三</td><td>1</td><td>张三</td><td>5</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr><tr>
    						<td>李四</td><td>2</td><td>李四</td><td>4</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr>
  							<tr>
    						<td>张三</td><td>1</td><td>张三</td><td>5</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr><tr>
    						<td>李四</td><td>2</td><td>李四</td><td>4</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr>
  							<tr>
    						<td>张三</td><td>3</td><td>张三</td><td>3</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr><tr>
    						<td>李四</td><td>4</td><td>李四</td><td>2</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr>
  							<tr>
    						<td>张三</td><td>3</td><td>张三</td><td>3</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr><tr>
    						<td>李四</td><td>4</td><td>李四</td><td>2</td><td>李四</td><td>TJ_Andr_Multimedia</td>
  							</tr>
							</table>   
        </li> 
                    <li class="tab_con"> 
       	<div class="radio">
	  						<input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">今日</label>
	  					    <input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">昨日</label>
	  					    <input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">一周</label>
	 					    <input type="radio" name="opinions" id="radio-1" value="1" /><label for="radio-1">一个月</label> 					    
				    </div>
				    	<p><font size=4px >天津</font></p>
				        <div class="container" id="pie1" style="height:300px; width: 450px;float:left;"></div> 
				        <div class="container" id="pie2" style="height:300px; width: 450px;"></div> 
				        <p><font size=4px >北京</font></p>
				        <div class="container" id="pie3" style="height:300px; width: 450px;float:left;"></div> 
				        <div class="container" id="pie4" style="height:300px; width: 450px;"></div> 
				        <p><font size=4px >上海</font></p>
				        <div class="container" id="pie5" style="height:300px; width: 450px;float:left;"></div> 
				        <div class="container" id="pie6" style="height:300px; width: 450px;"></div>
		</li> 
             </ul>
       <br>       
  
</div>
</div>
</div>
</div>
</s:form>
</body>
</html>         