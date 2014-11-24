<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="java.util.ArrayList"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
 // String str = (String)request.getAttribute("averageStopTime");//这个是不需要的，可以使用标签替换掉
 // ArrayList<String> list = (ArrayList<String>)request.getAttribute("list");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>RD自测试</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
     <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
	 <script type="text/javascript" src="jqplot/jquery.js"></script>
	 <script type="text/javascript" src="jqplot/jquery.min.js"></script>
	 <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
	 <script  type="text/javascript" src="jqplot/excanvas.js"></script>
	 <script type="text/javascript" src="jqplot/jquery.jqplot.min.js"></script>
	 <script type="text/javascript" src="jqplot/jqplot.cursor.min.js"></script>
	 <script type="text/javascript" src="jqplot/customInput.jquery.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.dateAxisRenderer.min.js"></script>	 
     <script type="text/javascript" src="jqplot/jqplot.pieRenderer.min.js"></script>
	 <script type="text/javascript" src="jqplot/highcharts.js"></script>
	 <script type="text/javascript" src="jqplot/highcharts-more.js"></script> 
	 <script type="text/javascript">	
		    
     $(document).ready(function() {
		        jQuery.jqtab = function(tabtit,tab_conbox,shijian) {
		            $(tab_conbox).find("li").hide();
		            $(tabtit).find("li:first").addClass("thistab").show(); 
		            $(tab_conbox).find("li:first").show();
		        
		            $(tabtit).find("li").bind(shijian,function(){
		              $(this).addClass("thistab").siblings("li").removeClass("thistab"); 
		                var activeindex = $(tabtit).find("li").index(this);
		                $(tab_conbox).children().eq(activeindex).show().siblings().hide();
		                return false;
		            });
		        
		        };
		        /*调用方法如下：*/
		        $.jqtab("#tabs","#tab_conbox","click");
		        
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
	 
	 	$(document).ready(function(){
			$("input:checkbox").each(function(){
				$(this).click(function(){
					//alert($(this).attr('checked'));
					$(this).toggleClass('label_on');
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
		 
	        $(function () { 
	            // checkbox确定提交
	            $("#btnSubmit").bind("click", function () {
	                var result = new Array();
	                $("[name = chkItem]:checkbox").each(function () {
	                    if ($(this).is(":checked")) {
	                        result.push($(this).attr("value"));
	                    }
	                });
	                alert(result.join(","));
	            });
	        });
	        
	      //下拉选择框
	    	$(".select_checkBox").ready(function(){
	    		$("#c").toggle(function(){
	    		$(".chartOptionsFlowTrend").css("display","inline-block");
	    		},function(){
	    		$(".chartOptionsFlowTrend").css("display","none");
	    		});
	    		}); 
	     /*   $(".select_checkBox").ready(function(){
	            	    $("a").click(function(){
	            	    $(".chartOptionsFlowTrend").slideToggle();   //确定后触发筛选
	            	  });});
	        */
	        

</script>
<style>
.blue {background:#bcd4ec;}
.tabs {height: 30px;width:99.5%; margin-top:5px;margin-left:0;background:#F0FFFF;}
.tabs li{list-style:none;float:left;background:#63C7FE;line-height:30px;width:150px;}
.tabs li a{display:block;text-align:center;height:30px;maggin:0px,10px;color:#000000;font-size:16px;margin-top:0px;outline:none; }
.tab_conbox{width:100%;}
.tabs .thistab, .tabs .thistab a:hover {background: #F0ffff;}
.tabs li a:hover {background: none;} 

li{ list-style:none; }
.menu{width:100%;height:50px;padding-top:20px;margin-top:30px auto;}
.menu ul{ width:95%;float:left; display:inline;margin-top:15px;}
.menu ul li{ width:96px;float:left;font-size:18px;margin-right:40px;}
table {border-collapse:collapse;text-align:center;font-size:12px;}
table, td{border: 1px solid #98bf21;}
table th{border: 1px solid black;background-color:#A7C942;color:#ffffff;}

.checkbox{width:100%;margin-top:10px 40px 0px 0px;float:left;font-size:14px; }
.label_on { border:2px solid red; }
.radio{width:100%;height:30px;margin:5px 40px 30px 0px;float:left;font-size:18px;}

/*fuxuankuang*/
.select_checkBox{
border:0px solid red;
position: relative;
display:inline-block;
margin-bottom:12px;
width:200px; 
}
.chartQuota{
height:23px;
float:left;
display:inline-block;
border:0px solid black;
position: relative;
}
 
.chartOptionsFlowTrend{
background-color:white;
border:1px solid gray;
display:none;
position: absolute;
left:0px;
top:23px;
width:800px;
float:left;
}
a:-webkit-any-link {
color: -webkit-link;
text-decoration: underline;
cursor: auto;
}
.chartQuota p a {
float: left;
height: 21px;
outline: 0 none;
border: 1px solid #ccc;
line-height: 22px;
padding: 0 5px;
overflow: hidden;
background: #FDF2EF;
color: #313131;
text-decoration: none;
width:100px;
text-align:center;
border-radius:20px;
}
 
.chartQuota p {
margin:0px;
folat:left;
overflow: hidden;
height: 23px;
line-height:24px;
display: inline-block;

}
</style>
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
                        <a href="monkey.action" class="button blue medium">Monkey测试</a>
                    </li>
                    <li>
                        <a href="login.action" class="button blue medium">返回主页</a>
                    </li>
                </ul>              
            </div>
           
            <div class="right">
                <div class="right_whole"> 
                	<div class="menu">
  						<ul><li><a href="rdtest.action?site=SH"><input type="radio" name="opinions1" id="radio-site" value="1" />
  						<label for="radio-1">上海</label></a><li>
  					    <li><input type="radio" name="opinions1" id="radio-1" value="1" /><label for="radio-site">北京</label></li>
 					    <li><input type="radio" name="opinions1" id="radio-1" value="1" /><label for="radio-site">天津</label></li></ul>
				    </div>
             
             <div class="ta" id="r"> 
    					<ul class="tabs" id="tabs">
       				    	<li><a href="#">测试详细信息</a></li>
       						<li><a href="#">执行人员名单</a></li>
       						<li><a href="#">执行人员统计</a></li></ul> 
    					<ul class="tab_conbox" id="tab_conbox">
    						<li class="tab_con" >
    						<p>Date: <input type="text" id="datepicker"></p>	
    		<div class="select_checkBox" >
			<div class="chartQuota">
			<p><a href="javascript:;" title="请选择指标"><span id="c">选择指标</span><b></b></a></p>
			</div><br>
			
			<div class="chartOptionsFlowTrend">
			<input type="checkbox" value="1"><span>TJ_Andr_Multimedia</span>
			<input type="checkbox" value="1"><span>TJ_Andr_APP</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Framework</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Telephony</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Telephony</span><br>
			<input type="checkbox" value="1"><span>TJ_Andr_Telephony</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Telephony</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Telephony</span>
			</div>
			</div>
							
             				<table style="table-layout:fixed;word-break:break-all;word-wrap:break-word;
             				height:700px;overflow:scroll;width:900px;">
                             <tr>
                                    <th width="30px" rowspan="2">姓名</th>
                                    <th width="30px" rowspan="2">测试时长(h)</th>
                                    <th width="140px" colspan="2">首次出错</th>
                                    <th width="230px" colspan="3">JavaCrash</th>
                                    <th width="230px"  colspan="3">NativeCrash</th>
                                    <th width="230px"  colspan="3">ANR</th>
                             </tr>
                             <tr>
                             		<th>最小时长</th>
                                    <th>错误类型</th>
                            
                                    
                                    <th>首次出现时长</th>
                                    <th>数目</th>
                                    <th>模块列表</th>
                                  
                                    <th>首次出现时长</th>
                                    <th>数目</th>
                                    <th>模块列表</th>
                                   
                                    <th>首次出现时长</th>
                                    <th>数目</th>
                                    <th>模块列表</th>
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
  
   			<li class="tab_con" id="r"> 
   			<div class="select_checkBox" >
			<div class="chartQuota">
			<p><a href="javascript:;" title="请选择指标"><span id="c">选择指标</span><b></b></a></p>
			</div><br>
			
			<div class="chartOptionsFlowTrend">
			<input type="checkbox" value="1"><span>TJ_Andr_Multimedia</span>
			<input type="checkbox" value="1"><span>TJ_Andr_APP</span>
			<input type="checkbox" value="1"><span>TJ_Andr_Framework</span>
			</div>
			</div>                     
             <table width="90%" border="1">
  							<tr>
    						<th>排名</th>
    						<th>姓名</th>
    						<th>本周测试天数</th>
    						<th>累计测试天数</th>
    						<th>今日测试情况</th>
    						<th>小组</th>
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
</div><br><br>
             </div>
                </div>
            </div>
   </s:form>
</body>
</html>
