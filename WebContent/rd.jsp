
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
 
	     
$(function () { $(document).ready(function () 
		{ // Build the chart 
	    var currPieDoData = [];
	    <s:iterator  value="#request.currPieData" id="pieData">
	    <%
	    String group = (String)request.getAttribute("groupName");
	    Object ratio =  request.getAttribute("performanceRatio");
	    %>
	       var currPieTmpData = ['<%=group%>',<%=ratio%>];
	       currPieDoData.push(currPieTmpData);		       
	    </s:iterator>       
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
			series: [{ type: 'pie', name: 'Browser share',    data:currPieDoData }] }); 
		
		}); });

$(function () {  $(document).ready(function () 
		{ // Build the chart 
    var currPieNoDoData = [];
    <s:iterator  value="#request.currPieData" id="pieData">
    <%
    String group = (String)request.getAttribute("groupName");
    Object ratio =  request.getAttribute("performanceRatio");
    %>
       var currPieTmpData = ['<%=group%>',1 - <%=ratio%>];
       currPieNoDoData.push(currPieTmpData);		       
    </s:iterator>  
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
			series: [{ type: 'pie', name: 'Browser share',    data: currPieNoDoData }] }); }); });

$(function () {  $(document).ready(function () 
		{ // Build the chart 
    var PieDoData2 = [];
    <s:iterator  value="#request.PieData2" id="pieData">
    <%
    String group = (String)request.getAttribute("groupName");
    Object ratio =  request.getAttribute("performanceRatio");
    %>
       var currPieTmpData = ['<%=group%>',<%=ratio%>];
       PieDoData2.push(currPieTmpData);		       
    </s:iterator>  
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
			series: [{ type: 'pie', name: 'Browser share',    data: PieDoData2 }] }); }); });

$(function () {  $(document).ready(function () 
		{ // Build the chart 
    var PieNotDoData2 = [];
    <s:iterator  value="#request.PieData2" id="pieData">
    <%
    String group = (String)request.getAttribute("groupName");
    Object ratio =  request.getAttribute("performanceRatio");
    %>
       var currPieTmpData = ['<%=group%>',1 - <%=ratio%>];
       PieNotDoData2.push(currPieTmpData);		       
    </s:iterator>  
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
			series: [{ type: 'pie', name: 'Browser share',    data: PieNotDoData2 }] }); }); });	 

$(function () {  $(document).ready(function () 
		{ // Build the chart 
    var PieDoData3 = [];
    <s:iterator  value="#request.PieData3" id="pieData">
    <%
    String group = (String)request.getAttribute("groupName");
    Object ratio =  request.getAttribute("performanceRatio");
    %>
       var currPieTmpData = ['<%=group%>',<%=ratio%>];
       PieDoData3.push(currPieTmpData);		       
    </s:iterator>
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
			series: [{ type: 'pie', data: PieDoData3 }] }); }); });

$(function () {  $(document).ready(function () 
		{ // Build the chart 
    var PieNotDoData3 = [];
    <s:iterator  value="#request.PieData3" id="pieData">
    <%
    String group = (String)request.getAttribute("groupName");
    Object ratio =  request.getAttribute("performanceRatio");
    %>
       var currPieTmpData = ['<%=group%>',1-<%=ratio%>];
       PieNotDoData3.push(currPieTmpData);		       
    </s:iterator>
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
			series: [{ type: 'pie', name: 'Browser share', data: PieNotDoData3 }] }); }); });

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
//加载到执行人员统计上去
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
                <%
                    String siteName= (String)request.getAttribute("site");
                    String radionCheckedTJ ="";
                    String radionCheckedBJ ="";
                    String radionCheckedSH ="";
                    String currSitePie = "";
                    String SitePie2 = "";
                    String SitePie3 = "";
                    if (siteName.equals("SH")) {
                    	radionCheckedSH = "checked=" + "\"checked\"" ;
                    	currSitePie = "上海";
                    	SitePie2 = "天津";
                    	SitePie3 = "北京";
                    } else if (siteName.equals("BJ")) {
                    	radionCheckedBJ = "checked=" + "\"checked\"" ; 
                    	currSitePie = "北京";
                    	SitePie2 = "上海";
                    	SitePie3 = "天津";
                    } else {
                    	radionCheckedTJ = "checked=" + "\"checked\"" ;  
                    	currSitePie = "天津";
                    	SitePie2 = "北京";
                    	SitePie3 = "上海";
                    } 
                   
                %>
                          <ul><li><input type="radio" name="site" class="radio-site" value="TJ" <%=radionCheckedTJ%> />天津<li>
                          <li><input type="radio" name="site" class="radio-site" value="BJ" <%=radionCheckedBJ%>/>北京</li>
                         <li><input type="radio" name="site" class="radio-site" value="SH" <%=radionCheckedSH%>/>上海</li></ul>
                    </div>		            
<!-- taps -->   
        <div class="tapsbox"> 
            <ul class="taps" id="taps">
               <li><a href="#">测试详细信息</a></li>
       		<li><a href="#">执行人员名单</a></li>
       		<li><a href="#" id="tongji">执行人员统计</a></li></ul>          
            <ul class="taps_conbox" id="taps_conbox">
                <li class="taps_con">
                <%
                    String searchDate;
                    searchDate = request.getAttribute("date").toString();                 
                %>
    			<p  style="font-size:14px;">Date:<input type="text" id="datepicker" value=<%=searchDate%> name="testInfoDate" >
                <s:submit style="font-size:14px;" id="rd" value="搜索" ></s:submit></p>
    			
<!-- warning -->
          
         <marquee onMouseOut="this.start()" onMouseOver="this.stop()" direction="left" scrollamount="6" 
            style="margin-left:350px; width:250px;margin-top:5px;margin-bottom:5px;"  id="">
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
            
           			<li class="taps_con" id="r"> 
   		            <div class="select_checkBox" >
				<div class="chartQuota"  id="d">
				<p id="p2">选择指标</p>
			    </div><br>		
			     <div class="chartOptionsFlowTrend">
			     <s:iterator value="#request.group" id="everyGroup">
			     
                <input type="checkbox" name="checkbox" value=<s:property value="#everyGroup"/> class="check" id=<s:property value="#everyGroup"/> ><span>${everyGroup}</span>
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
                              <input id="1" type="radio"  class="radio-time" value="1" name="queryDays" checked="checked"/><s:property value="#request.yesterday"/>
                              <input id="7" type="radio"  class="radio-time" value="7" name="queryDays"  />一周
                              <input id="30" type="radio"  class="radio-time" value="30" name="queryDays"  />一个月                         
                    </div>
				    	<p><font size=4px ><%=currSitePie %></font></p>
				        <div class="container" id="pie1" style="height:300px; width: 450px;float:left;"></div> 
				        <div class="container" id="pie2" style="height:300px; width: 450px;"></div> 
				        <p><font size=4px ><%=SitePie2 %></font></p>
				        <div class="container" id="pie3" style="height:300px; width: 450px;float:left;"></div> 
				        <div class="container" id="pie4" style="height:300px; width: 450px;"></div> 
				        <p><font size=4px ><%=SitePie3 %></font></p>
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