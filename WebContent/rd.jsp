
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>

<%
	String siteName = (String) request.getAttribute("site");
	String radionCheckedTJ = "";
	String radionCheckedBJ = "";
	String radionCheckedSH = "";
	String currSitePie = "";
	String SitePie2 = "";
	String SitePie3 = "";
	if (siteName.equals("SH")) {
		radionCheckedSH = "checked=" + "\"checked\"";
		currSitePie = "上海";
		SitePie2 = "天津";
		SitePie3 = "北京";
	} else if (siteName.equals("BJ")) {
		radionCheckedBJ = "checked=" + "\"checked\"";
		currSitePie = "北京";
		SitePie2 = "上海";
		SitePie3 = "天津";
	} else {
		radionCheckedTJ = "checked=" + "\"checked\"";
		currSitePie = "天津";
		SitePie2 = "北京";
		SitePie3 = "上海";
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<head>
    <title>RD自测试信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <link rel="stylesheet" href="css/rd.css" type="text/css">
    
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
   <script type="text/javascript" src="jqplot/jqplot.pieRenderer.min.js"></script>  
     <script type="text/javascript" src="jqplot/highcharts.js"></script> 
   


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
           $(".check").change(function() {
           $("#caselist2 tr").each(function(){
                var value = $(this).find("td").eq(5).text();    
                if(value.length > 0){
                if($("#"+value).attr("checked") != "checked"){
                    $(this).hide();
                }
                else{
                    $(this).show();
                }
                   }
               });
           });
           }); 
 
	     

       $(function () {var chart; $(document).ready(function () 
               { // Build the chart 
               var currPieDoData = [];
               var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
               var flag = false;
               var innersize = ['0'];
           
               <s:iterator  value="#request.currPieData" id="pieData">
               <%
               String group = (String)request.getAttribute("groupName");
               Object ratio =  request.getAttribute("performanceRatio");
               %>
                  var currPieTmpData = ['<%=group%>',<%=ratio%>];
                  if(currPieTmpData[1] != "0")
                      flag = true;
                  currPieDoData.push(currPieTmpData);  
               </s:iterator>       
               if(!flag){
                   currPieDoData = [];
                   color = ['#6495ED'];
                   innersize = ['80%'];
                   currPieDoData.push(["暂无数据",2]);
               }
           
               $('#pie1').highcharts({ 
                   chart: { plotBackgroundColor: null,
                       plotBorderWidth: null, 
                       plotShadow: false,
                       backgroundColor: 'rgba(0,0,0,0)' 
                       }, 
                    colors:color,
                   title: { 
                       text: '<%=currSitePie%>'+':执行率', align: 'center',
                       x:-55,
                   }, 
                       tooltip: { 
                    	   formatter:function(){
                    		   if (this.y!=2){
                    			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                    		   else {
                    			   return null
                    		   }
                    	   }
                    	    }, 
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},
                   plotOptions: { 
                   pie: { size:'90%', 
                       allowPointSelect: true, //  是否允许使用鼠标选中数据点
                              cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                             // sliced:  true ,
                              innerSize:innersize, 
                              dataLabels: { enabled: false }, //图上是否显示数据标签
                              showInLegend: true,
                                       point:{
                                           events:{
                                               legendItemClick:function(){
                                                   this.select();
                                                   this.show();},}}}},                                         
                   series: [{ type: 'pie', name: 'Execution rate',    data:currPieDoData }] }); 
               
               }); });

       $(function () { var chart; $(document).ready(function () 
               { // Build the chart 
           var currPieDoData = [];
           var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
           var flag = false;
           var innersize = ['0'];
           
           <s:iterator  value="#request.currPieData" id="pieData">
           <%
           String group = (String)request.getAttribute("groupName");
           Object ratio =  request.getAttribute("performanceRatio");
           %>
              var currPieTmpData = ['<%=group%>',1 - <%=ratio%>];
              if(currPieTmpData[1] != "0")
                  flag = true;
              currPieDoData.push(currPieTmpData);  
           </s:iterator>       
           if(!flag){
               currPieDoData = [];
               color = ['#6495ED'];
               innersize = ['80%'];
               currPieDoData.push(["暂无数据",2]);
           }
               $('#pie2').highcharts({ 
                       chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
                       colors:color,
                       title: { 
                       text: '<%=currSitePie%>'+':未执行率', align: 'center',
                           x:-55,
                           },                     
                        tooltip: { 
                     	   formatter:function(){
                     		   if (this.y!=2){
                     			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                     		   else {
                     			   return null
                     		   }
                     	   }
                     	    }, 
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},                    	 
                   plotOptions: { 
                       pie: { size:'90%', 
                           allowPointSelect: true, //  是否允许使用鼠标选中数据点
                                  cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                                  innerSize:innersize, 
                                  dataLabels: { enabled: false }, //图上是否显示数据标签
                                  showInLegend: true,
                                           point:{
                                               events:{
                                                   legendItemClick:function(){
                                                       this.select();
                                                       this.show();},}}}},                                         
                       series: [{ type: 'pie', name: 'Un-execution rate',    data:currPieDoData }] }); 
                   
                   }); });
       $(function () { var chart; $(document).ready(function () 
               { // Build the chart 
            var PieDoData2 = [];
            var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
            var flag = false;
            var innersize = ['0'];
            
           <s:iterator  value="#request.PieData2" id="pieData">
           <%
           String group = (String)request.getAttribute("groupName");
           Object ratio =  request.getAttribute("performanceRatio");
           %>
              var currPieTmpData = ['<%=group%>',<%=ratio%>];
              if(currPieTmpData[1] != "0")
                  flag = true;
              PieDoData2.push(currPieTmpData); 
           </s:iterator>       
           if(!flag){
        	   PieDoData2 = [];
               color = ['#6495ED'];
               innersize = ['80%'];
               PieDoData2.push(["暂无数据",2]);
           }
               $('#pie3').highcharts({ 
                       chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
                   title: { 
                       text: '<%=SitePie2%>'+':执行率', align: 'center',
                       x:-55,
                       }, 
                       tooltip: { 
                    	   formatter:function(){
                    		   if (this.y!=2){
                    			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                    		   else {
                    			   return null
                    		   }
                    	   }
                    	    }, 
                    colors:color,
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},
                   plotOptions: { 
                   pie: {size:'90%',  
                       allowPointSelect: true, //  是否允许使用鼠标选中数据点
                              cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                              innerSize:innersize, 
                              dataLabels: { enabled: false }, //图上是否显示数据标签
                              showInLegend: true,
                                       point:{
                                           events:{
                                               legendItemClick:function(){
                                                   this.select();
                                                   this.show();},}}}},
                   series: [{ type: 'pie', name: 'Execution rate',    data: PieDoData2 }] }); }); });

       $(function () { var chart; $(document).ready(function () 
               { // Build the chart 
            var PieNotDoData2 = [];
            var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
            var flag = false;
            var innersize = ['0'];
            
           <s:iterator  value="#request.PieData2" id="pieData">
           <%
           String group = (String)request.getAttribute("groupName");
           Object ratio =  request.getAttribute("performanceRatio");
           %>
              var currPieTmpData = ['<%=group%>',1 - <%=ratio%>];
              if(currPieTmpData[1] != "0")
                  flag = true;
              PieNotDoData2.push(currPieTmpData);  
           </s:iterator>       
           if(!flag){
        	   PieDoData2 = [];
               color = ['#6495ED'];
               innersize = ['80%'];
               PieDoData2.push(["暂无数据",2]);
           }
               $('#pie4').highcharts({ 
                       chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
                   title: { 
                       text: '<%=SitePie2%>'+':未执行率', align: 'center',
                       x:-55,
                       }, 
                   colors:color,
                   tooltip: { 
                	   formatter:function(){
                		   if (this.y!=2){
                			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                		   else {
                			   return null
                		   }
                	   }
                	    },  
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},
                   plotOptions: { 
                   pie: { size:'90%', 
                       allowPointSelect: true, //  是否允许使用鼠标选中数据点
                              cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                              innerSize:innersize, 
                              dataLabels: { enabled: false }, //图上是否显示数据标签
                              showInLegend: true,
                                       point:{
                                           events:{
                                               legendItemClick:function(){
                                                   this.select();
                                                   this.show();},}}}},
                   series: [{ type: 'pie', name: 'Un-execution rate',    data: PieNotDoData2 }] }); }); });     

       $(function () { var chart; $(document).ready(function () 
               { // Build the chart 
           var PieDoData3 = [];
            var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
            var flag = false;
            var innersize = ['0'];
            
           <s:iterator  value="#request.PieData3" id="pieData">
           <%
           String group = (String)request.getAttribute("groupName");
           Object ratio =  request.getAttribute("performanceRatio");
           %>
              var currPieTmpData = ['<%=group%>',<%=ratio%>];
              if(currPieTmpData[1] != "0")
                  flag = true;
              PieDoData3.push(currPieTmpData); 
           </s:iterator>       
           if(!flag){
        	   PieDoData3 = [];
               color = ['#6495ED'];
               innersize = ['80%'];
               PieDoData3.push(["暂无数据",2]);
           }
               $('#pie5').highcharts({ 
                       chart: { 
                           plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
                   title: { 
                       text: '<%=SitePie3%>'+':执行率', align: 'center',
                       x:-55,
                       }, 
                   colors:color,
                   tooltip: { 
                	   formatter:function(){
                		   if (this.y!=2){
                			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                		   else {
                			   return null
                		   }
                	   }
                	    }, 
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},
                   plotOptions: { 
                   pie: { size:'90%', 
                       allowPointSelect: true, //  是否允许使用鼠标选中数据点
                              cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                              innerSize:innersize, 
                              dataLabels: { enabled: false }, //图上是否显示数据标签
                              showInLegend: true,
                                       point:{
                                           events:{
                                               legendItemClick:function(){
                                                   this.select();
                                                   this.show();},}}}},
                   series: [{ type: 'pie', name: 'Execution rate', data: PieDoData3 }] }); }); });

       $(function () { var chart; $(document).ready(function () 
               { // Build the chart 
           var PieNotDoData3 = [];
           var color = ['#1aadce', '#492970', '#f28f43', '#77a1e5', '#c42525','#a6c96a','#FFFFCC','#FF00FF'];
            var flag = false;
            var innersize = ['0'];
            
           <s:iterator  value="#request.PieData3" id="pieData">
           <%
           String group = (String)request.getAttribute("groupName");
           Object ratio =  request.getAttribute("performanceRatio");
           %>
              var currPieTmpData = ['<%=group%>',1-<%=ratio%>];
              if(currPieTmpData[1] != "0")
                  flag = true;
              PieNotDoData3.push(currPieTmpData);  
           </s:iterator>       
           if(!flag){
        	   PieDoData3 = [];
               color = ['#6495ED'];
               innersize = ['80%'];
               PieDoData3.push(["暂无数据",2]);
           }
               $('#pie6').highcharts({ 
                       chart: { plotBackgroundColor: null,plotBorderWidth: null, plotShadow: false,backgroundColor: 'rgba(0,0,0,0)' }, 
                   title: { 
                       text: '<%=SitePie3%>'+':未执行率', align: 'center',
                       x:-55,
                       }, 
                   colors:color,
                   tooltip: { 
                	   formatter:function(){
                		   if (this.y!=2){
                			   return '<b>'+ this.point.name +'</b>:'+ '<br/>'+'<b>'+this.series.name +'</b>:'+ this.percentage.toFixed(2)+' %'}
                		   else {
                			   return null
                		   }
                	   }
                	    }, 
                       legend: {layout: 'vertical',align: 'right',verticalAlign: 'top', x: -30,y: 60,borderWidth: 0,
                    	   labelFormatter: function() {
                    		   if (this.y!=2)
                               // return '&nbsp'+this.name+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.y+'&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp'+this.percentage.toFixed(2)+' %';
                        	   {return  '<table width=100px; table-layout:auto; style="color:{series.color};background: none repeat scroll 0 0 #F0FFFF;text-align:left"><tr border-width:0px;><td colspan="2" width=90px>'
                               +this.name +'</b></td></tr><tr><td width=40px><b>'+this.y+'</b></td><td width=40px><b>'+this.percentage.toFixed(2)+' %';+'</b></td></tr></table>';
                           }
                        	   else{ return this.name}
                        	   }, useHTML:true},
                   plotOptions: { 
                   pie: { size:'90%', 
                       allowPointSelect: true, //  是否允许使用鼠标选中数据点
                              cursor: 'pointer', //鼠标移到图表上时鼠标的样式 
                              innerSize:innersize, 
                              dataLabels: { enabled: false }, //图上是否显示数据标签
                              showInLegend: true,
                                       point:{
                                           events:{
                                               legendItemClick:function(){
                                                   this.select();
                                                   this.show();},}}}},
                   series: [{ type: 'pie', name: 'Un-execution rate', data: PieNotDoData3 }] }); }); });


radiovalue=null; 
function getRadioValue()
{
var doc = document.forms[0];
for(var i = 0; i < doc.site.length; i++)
{
if(doc.site[i].checked)
{ radiovalue=doc.site[i].value;
// alert(window.radiovalue);
return radiovalue;
}}};

$(document).ready(function(){
    $(".radio-site").change(function(){
        var value =$("input[name='site']:checked").val();
        location.href = "rdtest?site=" + value;
          //alert(value);
   
});    });


$(document).ready(function(){
    $(".radio-time").change(function(){
        var value =$("input[name='queryDays']:checked").val();        
        location.href = "rdtest?queryDays=" + value +"&site=" + getRadioValue() + "&tongji_select=" + "select" ;        
        });    
   });

/*$(document).ready(function(){
	$("input[name=site]:eq[0]").attr("checked", 'checked');
});*/
//加载到执行率率率人员统计上去
$(document).ready(function(){
	var select = "<s:property value="tongji_select" />";
	var time = "<s:property value="queryDays" />";
	  if("unselect" != select){
		// $("#tongji").click();
          $("#taps_conbox").find("li").hide();
          $("#taps").find("li:last").addClass("thistap").show(); 
          $("#taps_conbox").find("li:last").show();
          $("#taps").find("li:first").removeClass("thistap");
	  }
	  if(time == "1")
		  $("#1").prop("checked","checked");
	  if(time == "7")
          $("#7").prop("checked","checked");
	  if(time == "30")
          $("#30").prop("checked","checked");
  });

</script>

</head>

<body>
<s:form action="rdtest" theme="simple">
        <div class="page">
            <div class="sidebar">
               <img src="images/logo.png" alt="logo">
                <br><br><ul>
                    <li>
                       <a href="project.action" 
                          class="button blue medium">工程详细数据</a>
                    </li>
                      <li>
                       <a href="monkey.action" 
                          class="button blue medium">返回Monkey测试</a>
                    </li>
                    <li>
                        <a href="login.action" class="button blue medium">返回主页</a>
                    </li>
                </ul>              
            </div>
           
            <div class="right">
                <div class="right_whole"> 
                <div class="menu">

                          <ul><li><input type="radio" name="site" class="radio-site" value="TJ" <%=radionCheckedTJ%> />天津<li>
                          <li><input type="radio" name="site" class="radio-site" value="BJ" <%=radionCheckedBJ%>/>北京</li>
                         <li><input type="radio" name="site" class="radio-site" value="SH" <%=radionCheckedSH%>/>上海</li></ul>
                    </div>		            
<!-- taps -->   
        <div class="tapsbox"> 
            <ul class="taps" id="taps">
               <li style="border-left:1px solid #4876FF;"><a href="#">测试详细信息</a></li>
       		<li style="border:1px solid #4876FF;"><a href="#">执行人员名单</a></li>
       		<li style="border-right:1px solid #4876FF;"><a href="#" id="tongji">执行人员统计</a></li></ul>          
            <ul class="taps_conbox" id="taps_conbox">
                <li class="taps_con">
                <%
                    String searchDate;
                    searchDate = request.getAttribute("date").toString();                 
                %>
    			<p  style="font-size:16px;margin-left:275px;">Date:&nbsp<input type="text" id="datepicker" value=<%=searchDate%> name="testInfoDate" >
                <s:submit style="font-size:13px;" id="rd" value="搜索" ></s:submit></p>
    			
<!-- warning -->
          
         <marquee onMouseOut="this.start()" onMouseOver="this.stop()" direction="left" scrollamount="6" 
            style="margin-left:300px; width:250px;margin-top:-5px;margin-bottom:8px;"  id="">
            <div style="text-align:center;color:#FF3300;font-size:16px;margin-top:5px;margin-bottom:5px;"><strong > <s:property value="#request.warning"/> </strong></div> 
         </marquee> 
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
                             <tbody>
                             
                             <s:iterator value="#request.rdTestInfo" id="testInfo">
                                 <tr>
                                     <td><s:property value="#testInfo.deviceName"/></td>
                                     <td><s:property value="#testInfo.stopTime"/></td>
                                     <td><s:property value="#testInfo.firstErrTime"/></td>
                                     <td><s:property value="#testInfo.firstErrType"/></td>
                                     <td><s:property value="#testInfo.javaErrTime"/></td>
                                     <td><s:property value="#testInfo.javaErrCount"/></td>
                                     <td><s:property value="#testInfo.javaErrModule"/></td>
                                     <td><s:property value="#testInfo.nativeErrTime"/></td>
                                     <td><s:property value="#testInfo.nativeErrCount"/></td>
                                     <td><s:property value="#testInfo.nativeErrModule"/></td>
                                     <td><s:property value="#testInfo.anrErrTime"/></td>
                                     <td><s:property value="#testInfo.anrErrCount"/></td>
                                     <td><s:property value="#testInfo.anrErrModule"/></td>                                   
                                 </tr>
                             </s:iterator>
               </table></li>
            
           			<li class="taps_con" id="r" style="margin-left:40px"> 
   		            <div class="select_checkBox" >
				<div class="chartQuota"  id="d">
				<p id="p2">选择指标</p>
			    </div><br>		
			     <div class="chartOptionsFlowTrend">
			     <s:iterator value="#request.group" id="everyGroup">
			     
                <input type="checkbox"  name="checkbox" style="margin:10px 0 10px 20px;" value=<s:property value="#everyGroup"/> class="check" id=<s:property value="#everyGroup"/> ><span>${everyGroup}</span>
                </s:iterator>
                </div>
                </div>       
             <table width="90%" border="1" id="caselist2">
  							<tr>
    						<th width="5%">排名</th>
    						<th width="15%">姓名</th>
    						<th width="15%">本周测试天数</th>
    						<th width="15%">累计测试天数</th>
    						<th width="15%">今日测试情况</th>
    						<th width="25%">小组</th>
  							</tr>
  							
  							    <% 
  							    int i = 0;  							    
  							    %> 							
                             <s:iterator value="#request.rdPerformanceList" id="rdPerformance">
                                 <%
                                     i++;
                                 %>
                                 <tr>
                                     <td><%=i%></td>
                                     <td><s:property value="#rdPerformance.name"/></td>
                                     <td><s:property value="#rdPerformance.doDaysForWeek"/></td>
                                     <td><s:property value="#rdPerformance.doDaysForAll"/></td>
                                     <td><s:property value="#rdPerformance.doIfYesterday"/></td>
                                     <td><s:property value="#rdPerformance.belongGroup"/></td>                                 
                                 </tr>
                             </s:iterator>
			</table>   
        </li> 
                    <li class="tab_con"> 
       	<div class="radio">
                              <input id="1" style="margin-left:250px;" type="radio"  class="radio-time" value="1" name="queryDays" checked="checked"/><s:property value="#request.yesterday"/>
                              <input id="7" style="margin-left:55px;" type="radio"  class="radio-time" value="7" name="queryDays"  />一周
                              <input id="30" style="margin-left:80px;" type="radio"  class="radio-time" value="30" name="queryDays"  />一个月                         
                    </div>
				            <div class="container" id="pie1" style="height:300px; width: 450px;float:left;position:relative;
                                    zoom:1;"></div> 
				         <div class="container" id="pie2" style="height:300px; width: 450px;position:relative;
                                    zoom:1;float:right;"></div> 
				        <div class="container" id="pie3" style="height:300px; width: 450px;float:left;position:relative;
                                    zoom:1;"></div> 
				        <div class="container" id="pie4" style="height:300px; width: 450px;position:relative;
                                    zoom:1;float:right;"></div> 
				        <div class="container" id="pie5" style="height:300px; width: 450px;position:relative;
                                    zoom:1;float:left;"></div> 
				        <div class="container" id="pie6" style="height:300px; width: 450px;position:relative;
                                    zoom:1;float:right;"></div>
                                    
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