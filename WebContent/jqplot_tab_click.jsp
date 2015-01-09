<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<head>
    <title>数据平台首页</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
     <script type="text/javascript" src="jqplot/jquery.js"></script>
    
     <script  type="text/javascript" src="jqplot/excanvas.js"></script>
     <script type="text/javascript" src="jqplot/jquery.min.js"></script>
     <script type="text/javascript" src="jqplot/jquery.jqplot.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.highlighter.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.cursor.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.dateAxisRenderer.min.js"></script>
     
   
<script>
$(document).ready(function(){
    var line1=[['23-May-08', 578.55], ['20-Jun-08', 566.5], ['25-Jul-08', 480.88], ['22-Aug-08', 509.84],
        ['26-Sep-08', 454.13], ['24-Oct-08', 379.75]];
    var plot1 = $.jqplot('chart1', [line1], {
    	// Turns on animatino for all series in this plot.
        animate: true,
        // Will animate plot on calls to plot1.replot({resetAxes:true})
        animateReplot: true,
       // title:'Data Point Highlighting',
        axes:{
          xaxis:{
            renderer:$.jqplot.DateAxisRenderer,
            
            tickOptions:{
              formatString:'%#m/%#d/%y'
            }
          },
          yaxis:{
        	  show:false,
        	  min:300,
        	  max:650,
        	  numberTicks:4,
        	  
            tickOptions:{
            	 markSize: 0,
              formatString:'%.2f'
              }
          }
        },
        highlighter: {
             show: true,
             sizeAdjust: 7.5,
            tooltipLocation: 'n',
            tooltipAxes: 'y',
            tooltipFormatString: '<b style="background-color:#ebe8db;padding:6px 3px"><i><span style="color:red;">Time:</span></i> %.2f</b>',
            useAxesFormatters: false
        },
        cursor: {
          show: false
        }
    });
    
    $("#chart1").bind("jqplotDataClick",
    		function(ev, seriesIndex, pointIndex, data){
    			alert(pointIndex + data);
    });
  });
</script>
<style>
#chart1
{

}
</style>
</head>
<body>
<s:form action="monkey" theme="simple">
    <div class="border">
        <div id="bg">
            background
        </div>
        <div class="page">
            <div class="sidebar"><br><br><br>
                <a href="index.html" id="logo"><img src="images/logo.png" alt="logo"></a>
                <br><br><br><ul>
                    <li>
                        <a href="index.jsp">首页</a>
                    </li>
                    <li>
                        <a href="monkey.jsp">Monkey测试</a>
                    </li>
                </ul>
                
            </div>
            
            <div class="body">
	                
                     <div>
                        <h4 class="title"><span>停止均值-今日所有测试工程</span></h4>
                                <div id="chart1" class="figure"></div>
                                <s:textarea name="monkey_project" id="monkey_value"></s:textarea>
                                <s:submit id="jump" method="monkey_jump"></s:submit>
                    </div>
          </div></div></div></s:form>
</body>
</html>