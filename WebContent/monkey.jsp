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
    <title>Monkey测试首页</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
	 <script type="text/javascript" src="jqplot/jquery.js"></script>
	
	 <script  type="text/javascript" src="jqplot/excanvas.js"></script>
	 <script type="text/javascript" src="jqplot/jquery.min.js"></script>
	 <script type="text/javascript" src="jqplot/jquery.jqplot.min.js"></script>
	 <script type="text/javascript" src="jqplot/jqplot.highlighter.min.js"></script>
	 <script type="text/javascript" src="jqplot/jqplot.cursor.min.js"></script>
	 <script type="text/javascript" src="jqplot/jqplot.dateAxisRenderer.min.js"></script>
	 
	 <script type="text/javascript" src="jqplot/jqplot.barRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.categoryAxisRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasAxisTickRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasTextRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.pointLabels.min.js"></script>
	
	 <script type="text/javascript">
	 
		 
	 $(document).ready(function(){
	     var line1 = [<s:property value="#request.averageStopTime"/>];
	     var line2 =[<s:property value="#request.midStopTime"/>];
	     var ticks = [];
	     <s:iterator  value="#request.list" id="item">
	        var value = "<s:property value="item" />";
	        ticks.push(value);
	     </s:iterator>
	     
	     jQuery.jqchart = function(chart, line){
	    	 $.jqplot(chart, [line], {
	 	        animate: true,
	 	        animateReplot: true,
	 	        axes:{
	 	          xaxis:{
	 	            renderer:$.jqplot.CategoryAxisRenderer,
	 	              ticks:ticks,
	 	              tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	 	              tickOptions: {
	 	                  angle: -30,
	                       fontSize: '12pt'
	 	              }
	 	          },
	 	          yaxis:{
	 	        	  show:false,
	 	        	  min:0,
	 	        	  max:25,
	 	        	  numberTicks:6,
	 	            tickOptions:{
                              showMark: false,
	 	              formatString:'%.2f',
	 	            // fontStretch:3
	 	              },
	 	          }
	 	        },
	 	        seriesDefaults:{
	 	    	  color: '#000080',  
	 	       },
                         grid: { //设置图表框的属性
		 	            shadow: false,              //是否显示图表框阴影                            
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
	     };
	     $.jqchart("chart1",line1);
	     $.jqchart("chart2",line2);
	     
	    $(document).ready(function(){
	    	    var line1 = [<s:property value="#request.averFirstErrTime"/>];
	    	    var ticks = [];
	    	    <s:iterator  value="#request.list" id="item">
	    	       var value = "<s:property value="item" />";
	    	       ticks.push(value);
	    	    </s:iterator>
	    	   var plot1 = $.jqplot('chart3', [line1], {
	    	       // Turns on animatino for all series in this plot.
	    	       animate: true,
	    	       // Will animate plot on calls to plot1.replot({resetAxes:true})
	    	       animateReplot: true,
	    	       
	    	       axes:{
	    	         xaxis:{
	    	           renderer:$.jqplot.CategoryAxisRenderer,
	    	             ticks:ticks,
	    	             tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	    	             tickOptions: {
	    	                 angle: -30,
	    	                 fontSize: '12pt'
	    	             }
	    	         },
	    	         yaxis:{
	    	        	 show:false,
	    	             min:0,
	    	             max:15,
	    	             numberTicks:6,
	    	           tickOptions:{
                              showMark: false,
	    	             formatString:'%.2f'
	    	             },
	    	         }
	    	       },
                       seriesDefaults:{
	 	    	  color: '#000080',  
	 	       },
                       grid: { //设置图表框的属性
              	            shadow: false,              //是否显示图表框阴影                            
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
	    	 });
 
           $(document).ready(function(){
	    	    var line1 = [<s:property value="#request.midFirstErrTime"/>];
	    	    var ticks = [];
	    	    <s:iterator  value="#request.list" id="item">
	    	       var value = "<s:property value="item" />";
	    	       ticks.push(value);
	    	    </s:iterator>
	    	   var plot1 = $.jqplot('chart4', [line1], {
	    	       // Turns on animatino for all series in this plot.
	    	       animate: true,
	    	       // Will animate plot on calls to plot1.replot({resetAxes:true})
	    	       animateReplot: true,
	    	       
	    	       axes:{
	    	         xaxis:{
	    	           renderer:$.jqplot.CategoryAxisRenderer,
	    	             ticks:ticks,
	    	             tickRenderer: $.jqplot.CanvasAxisTickRenderer,
	    	             tickOptions: {
	    	                 angle: -30,
	    	                 fontSize: '12pt'
	    	             }
	    	         },
	    	         yaxis:{
	    	        	 show:false,
	    	             min:0,
	    	             max:15,
	    	             numberTicks:6,
	    	           tickOptions:{
                              showMark: false,
	    	             formatString:'%.2f'
	    	             },
	    	         }
	    	       },
                       seriesDefaults:{
	 	    	  color: '#000080',  
	 	       },
                       grid: { //设置图表框的属性
		 	            shadow: false,              //是否显示图表框阴影                            
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
	    	 });

	    //点击数据节点绑定到点击事件上
	     $("#chart1").bind("jqplotDataClick",
		    		function(ev, seriesIndex, pointIndex, data){
		    			$("#monkey_value").val(data[0]);
		    			$("#jump").click();
		    			
		    });
		 $("#chart2").bind("jqplotDataClick",
		    		function(ev, seriesIndex, pointIndex, data){
		    			$("#monkey_value").val(data[0]);
		    			$("#jump").click();
		    			
		    });
		 $("#chart3").bind("jqplotDataClick",
		    		function(ev, seriesIndex, pointIndex, data){
		    			$("#monkey_value").val(data[0]);
		    			$("#jump").click();
		    			
		    });
		 $("#chart4").bind("jqplotDataClick",
		    		function(ev, seriesIndex, pointIndex, data){
		    			$("#monkey_value").val(data[0]);
		    			$("#jump").click();
		    			
		    });
	     
	  });
	/* $(document).ready(function(){
		 jQuery.jqclick = function(chart){
	    	 $(chart).bind("jqplotDataClick",function(ev, seriesIndex, pointIndex, data){
	    		 $("#monkey_value").val(data[0]);
	    			$("#jump").click();
	    	 });
	     };
	     
	    $.jqclick("chart1");
	    $.jqclick("chart2");
	    $.jqclick("chart3");
	    $.jqclick("chart4");
	 });*/

			
</script>
<style>
#monkey_hide
{display:none;}
</style>
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
                        <a href="login.action" class="button blue medium">返回主页</a>
                    </li>
                </ul>
                
            </div>
           
            <div class="right">
                <div class="right_whole"> 
                   <!-- <h2 style="text-align:center;font-size: 35px;"><span>Monkey 测试</span></h2> --> 
                    <div>
                        <h4 class="title"><span>停止均值-最新测试数据汇总</span></h4>
                                <div id="chart1" class="figure"></div>
                    </div>
                   <div>
                        <h4 class="title"><span>停止中值-最新测试数据汇总</span></h4>
                        
                                <div id="chart2" class="figure"></div>
                    </div>
                    <div>
                        <h4 class="title"><span>首错均值-最新测试数据汇总</span></h4>
                                <div id="chart3" class="figure"></div>
            
                    </div>
                    <div>
                        <h4 class="title"><span>首错中值-最新测试数据汇总</span></h4>
                               <div id="chart4" class="figure"></div>
            
                    </div>
                                      
                   </div>
                   <div id="monkey_hide">
                   	<s:textarea name="projectNum" id="monkey_value"></s:textarea>
                                <s:submit id="jump" method="monkey_jump"></s:submit>
                   </div>
                </div>
            </div>
   </s:form>
</body>
</html>
