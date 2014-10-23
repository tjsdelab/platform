<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>

  <%
      String formNameDisplay=(String)request.getAttribute("testFormName");
      formNameDisplay = formNameDisplay.substring(formNameDisplay.indexOf("-")+1);
  %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Monkey详细信息</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
     <script type="text/javascript" src="jqplot/excanvas.js"></script>
     <script type="text/javascript" src="jqplot/jquery.jqplot.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.highlighter.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.cursor.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.dateAxisRenderer.min.js"></script>
    
     <script type="text/javascript" src="jqplot/jqplot.barRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.categoryAxisRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasAxisTickRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasTextRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.pointLabels.min.js"></script>
    
 <style>
 
 
 
 body {
   /* background:url(../images/bg-body.jpg);*/
   /* background:#0099cc;*/
   background:#ffffff;
   font-family:"Microsoft YaHei";
   font-weight: normal;
   width:1100px;
   margin:0 auto;
}
  .blue {
        background:#33ddcc;
       /* background:#33ffcc;*/    
}
  .white {
        background:#ffffff;
       /* background:#33ffcc;*/    
}

 table {
    border-collapse: collapse;
    font-size:14px;
}

table, td{
    border: 1px solid #98bf21;
    text-align:center;
    
}
table th{
border: 1px solid black;
background-color:#A7C942;
color:#ffffff;
text-align:center;

}
.customers
{
font-family:"Microsoft YaHei";
width:100%;
border-collapse:collapse;
text-align:center;
}
.customers td, #customers th 
{
font-size:1em;
border:1px solid #98bf21;
padding:3px 7px 2px 7px;
text-align:center;

}
.customers th 
{
font-size:1.1em;
text-align:center;
padding-top:5px;
padding-bottom:4px;
background-color:#A7C942;
color:#ffffff;
}
.customers tr.alt td 
{
color:#000000;
text-align:center;

background-color:#EAF2D3;
}

#project_hide
{
    display:none;
}

</style>
<script>

$(document).ready(function() {
    $(".orderedlist tr").hover(function() {
        // $("#orderedlist li:last").hover(function() {
            $(this).addClass("white");
        }, function() {
            $(this).removeClass("white");
        });
    });


$(document).ready(function(){
	var line1 = [<s:property value="#request.averageStopTime"/>];
    var ticks = [];
    <s:iterator  value="#request.list" id="item">
       var value = "<s:property value="item" />";
       ticks.push(value);
    </s:iterator>
   var plot1 = $.jqplot('chart1', [line1], {
   	// Turns on animatino for all series in this plot.
       animate: true,
       // Will animate plot on calls to plot1.replot({resetAxes:true})
       animateReplot: true,
   	
       title:'',
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
             showMark:false,
             formatString:'%.2f'
             },
         }
       },
       seriesDefaults:
       {
    	   color:'#000080'
       },
        grid: { 
       shadow: false,              //是否显示图表框阴影                            
        },
       highlighter: {
            show: true,
            sizeAdjust: 7.5,
           tooltipLocation: 'n',
           tooltipAxes: 'y',
           tooltipFormatString:'<b style="background-color:#ebe8db;padding:6px 3px"><i><span style="color:red;">Time:</span></i> %.2f</b>',
           useAxesFormatters: false
       },
       cursor: {
         show: false 
       }
   });
 });
$(document).ready(function(){
    var line1 =[<s:property value="#request.midStopTime"/>];
    var ticks = [];
    <s:iterator  value="#request.list" id="item">
       var value = "<s:property value="item" />";
       ticks.push(value);
    </s:iterator>
   var plot1 = $.jqplot('chart2', [line1], {
       // Turns on animatino for all series in this plot.
       animate: true,
       // Will animate plot on calls to plot1.replot({resetAxes:true})
       animateReplot: true,
       
       title:'',
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
             showMark:false,
             formatString:'%.2f'
             },
         }
       },
       seriesDefaults:
       {
    	   color:'#000080'
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
       
       title:'',
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
             showMark:false,
             formatString:'%.2f'
             },
         }
       },
       seriesDefaults:
       {
    	   color:'#000080'
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
       
       title:'',
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
             showMark:false,
             formatString:'%.2f'
             },
         }
       },
       seriesDefaults:
       {
    	   color:'#000080'
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
	var line1 = [<s:property value="#request.averageStopTime"/>];
	var line2 =[<s:property value="#request.midStopTime"/>];
	var line3 = [<s:property value="#request.averFirstErrTime"/>];
	var line4 = [<s:property value="#request.midFirstErrTime"/>];
	
    var ticks = [];
    <s:iterator  value="#request.list" id="item">
       var value = "<s:property value="item" />";
       ticks.push(value);
    </s:iterator>
   var plot1 = $.jqplot('chart5', [line1,line2,line3,line4], {
   	// Turns on animatino for all series in this plot.
       animate: true,
       // Will animate plot on calls to plot1.replot({resetAxes:true})
       animateReplot: true,
   	
       title:'',
       legend: {
    	   show: true,	   
    	   placement:'outsideGrid'
       },
       seriesColors: ['#000080','#EE2C2C', '#00FF7F','#8B1C62'],
       series:[
               {label:'停止均值'},       
               {label:'停止中值'},
               {label:'首错均值'},
               {label:'首错中值'}
           ],

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
             showMark:false,
             formatString:'%.2f'
             },
         }
       },

        grid: { 
       shadow: false,              //是否显示图表框阴影                            
        },
       highlighter: {
            show: true,
            sizeAdjust: 7.5,
           tooltipLocation: 'n',
           tooltipAxes: 'y',
           tooltipFormatString:'<b style="background-color:#ebe8db;padding:6px 3px"><i><span style="color:red;">Time:</span></i> %.2f</b>',
           useAxesFormatters: false
       },
       cursor: {
         show: false 
       }
   });
 });

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
    $.jqtab("#tabs","#tab_conbox","mouseenter");
    $.jqtab("#chart_tabs","#chart_tab_conbox","mouseenter");
});

$(document).ready(function(){
    $("#select").change(function(){
            var value = $(this).children('option:selected').val();
            if(value == "date"){
                var d=new Date(),str='';
                str += d.getFullYear()+'-'; //获取当前年份
                str +=d.getMonth()+1+'-'; //获取当前月份（0——11）
                str +=d.getDate();
                $("#searchProject").attr("value",str);//设置默认日期为今天的日期
                $("#searchProject").datepicker({
                    dateFormat:'yy-mm-dd',
                    changeMonth: true,
                    changeYear: true,
                    showWeek: true,
                    firstDay: 1
                    });
                }
                else if(value == "pacVersion"){
                 $("#searchProject").attr("value","W14_40_1-02");
                 $("#searchProject").unbind();
                }
                else if(value == "project"){
                 $("#searchProject").attr("value","sp7731gga_uui");
                 $("#searchProject").unbind();
                }
                });
});

$(document).ready(function(){
    $("#auto").change(function(){
          // alert($(this).children('option:selected').val());
           $("#auto_submit").click();
                });
});

$(document).ready(function(){

    //点击数据节点绑定到点击事件上
    $("#chart1").bind("jqplotDataClick",
               function(ev, seriesIndex, pointIndex, data){
            	   var ticks = [];
            	    <s:iterator  value="#request.list" id="item">
            	       var value = "<s:property value="item" />";
            	       ticks.push(value);
            	    </s:iterator>
            $("#project_value").val(ticks[data[0] - 1]);
            $("#project_jump").click();	                  
                   
       });
    $("#chart2").bind("jqplotDataClick",
    		function(ev, seriesIndex, pointIndex, data){
        var ticks = [];
         <s:iterator  value="#request.list" id="item">
            var value = "<s:property value="item" />";
            ticks.push(value);
         </s:iterator>
         $("#project_value").val(ticks[data[0] - 1]);
		 $("#project_jump").click();
       });
    $("#chart3").bind("jqplotDataClick",
    		function(ev, seriesIndex, pointIndex, data){
        var ticks = [];
         <s:iterator  value="#request.list" id="item">
            var value = "<s:property value="item" />";
            ticks.push(value);
         </s:iterator>
         $("#project_value").val(ticks[data[0] - 1]);
		 $("#project_jump").click();
                   
       });
    $("#chart4").bind("jqplotDataClick",
    		function(ev, seriesIndex, pointIndex, data){
        var ticks = [];
         <s:iterator  value="#request.list" id="item">
            var value = "<s:property value="item" />";
            ticks.push(value);
         </s:iterator>
         $("#project_value").val(ticks[data[0] - 1]);
		 $("#project_jump").click();
		                   
       });
    $("#chart5").bind("jqplotDataClick",
    		function(ev, seriesIndex, pointIndex, data){
        var ticks = [];
         <s:iterator  value="#request.list" id="item">
            var value = "<s:property value="item" />";
            ticks.push(value);
         </s:iterator>
         $("#project_value").val(ticks[data[0] - 1]);
		 $("#project_jump").click();
		                   
       });
});

$(document).ready(function(){
        $("#download").click(function(){
                var value = "<s:property value="testFormName"/>";//从当前页面获取表单名。
                location.href = "project!download.action?testFormName=" + value;//这句话是在当前页面跳转。 跳转到project.action下的
               // location.href = "newActionName?testFormName=" + value;                               //download方法，（.action的位置没有写错）并传递参数testFormName到project的testFormName属性上。
                                               //如果上述方法不能调转到指定方法。你可以尝试第二种方法。在（3）中说明。                                                                   
                                             //window.open("project!download.action?testFormName=" + value) ; 这句话是在新窗口打开。       
                return false;//停止点击按钮的后续工作。就跟没有点击一样。
        });
});
</script>

</head>
<body>
<s:form action="project" theme="simple">
        <div class="page">
            <div class="sidebar">
               <img src="images/logo.png" alt="logo">
                <br><br><ul>
                    <li>
                        <a href="monkey.action" class="button blue medium">Monkey测试</a>
                    </li>
                    <li>
                        <a href="login.action" class="button blue medium">返回主页</a>
                    </li>
                </ul>
                
            </div>
            
            <div class="right">
                    <div  class="right_whole">
                      <!--   <h2 style="text-align:left;font-size: 30px;"><span><s:property value="projectSearchNameString"/>测试详细信息</span></h2>--> 
                      <!--  <div style="text-align:left;font-size: 18px;"><s:property value="projectSearchNameString"/>测试详细信息-->
                      <div style="text-align:right;padding:20px;font-size:10px;">
                          <s:select  style="font-size:14px;" id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" 
                                            name="type"  > 
                              </s:select>
                                   <s:textfield id="searchProject" name="searchProject" value="sp7731gga_uui" 
                                            onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                                            onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
                                            style="color:#999999;font-size:14px;width:100px">
                                   </s:textfield>
                           <s:submit style="font-size:14px;" id="search" value="搜索" method="search"></s:submit>
                                          
                    </div>
                        <h2 style="text-align:center;font-size: 30px;"><span><s:property value="projectSearchNameString"/>测试详细信息</span></h2>
                       <div style="text-align:center;font-size:18px;">
                        <s:select style="width:400px;text-align:center;" id="auto" list="sortedProjectList" name="projectSearchNameString"></s:select>
                        <s:submit id="auto_submit" value="搜索" method="dropDownProject" style="display:none"></s:submit>
                        </div><br><br>
                    <div class="div_project">
                 		<!--  <div >  
                 		   
                          <s:select id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" 
                                            name="type"  > 
                              </s:select>
                                   <s:textfield id="searchProject" name="searchProject" value="sp7731gga_uui" 
                                            onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                                            onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
                                            style="color:#999999" >
                                   </s:textfield>
                                             &nbsp;&nbsp; 
                           <s:submit id="search" value="搜索" method="search"></s:submit>
                                          
                    </div><br>-->
                    
                    <!--  <div >
                              当前工程：<s:select id="auto" list="sortedProjectList" name="projectSearchNameString"></s:select>        
                                       <s:submit id="auto_submit" value="搜索" method="dropDownProject" style="display:none"></s:submit>
                                      <a target="_blank" href="download?testFormName=<s:property value="#request.testFormName"/>">数据导出</a> 
                                                               
                        </div><br>-->
                <!--    <div >
                     <span> 测试版本路径:</span><br/>
                     <span><a style="text-align:left;" target="_blank" href="<s:property value="#request.pACPath.split(' ')[0]"/>">
                     <s:property value="#request.pACPath.split(' ')[0]"/></a>
                     </span>
                   <span> PAC:&nbsp;<s:property value="#request.pACPath.split(' ')[1]"/></span><br>
                   </div><br>
                       -->   
   </div>          
                        
        <div class="customers" >
                      <!--  <div style="text-align:left;margin-bottom:10px;"><strong>总体数据汇总</strong> </div> -->
                <table width="860">
                                    <tr>
                                    <th>测试版本</th>
                                    <th>停止时间均值</th>
                                    <th>停止时间中值</th>
                                    <th>首错时间均值</th>
                                    <th>首错时间中值</th>
                                    <th>JavaCrash均值</th>
                                    <th>NativeCrash均值</th>
                                    <th>ANR均值</th>
                                    </tr>
                                    
                                    <tr>
                                    <td><s:property value="#request.overallTimeInfo.testVersion"/></td>
                                    <td><s:property value="#request.overallTimeInfo.averageStopTime"/></td>
                                    <td><s:property value="#request.overallTimeInfo.averageMiddleTime"/></td>
                                    <td><s:property value="#request.overallTimeInfo.firstErrAverageTime"/></td>
                                    <td><s:property value="#request.overallTimeInfo.firstErrMiddleTime"/></td>
                                    <td><s:property value="#request.overallTimeInfo.averJavaCrashCount"/></td>
                                    <td><s:property value="#request.overallTimeInfo.averNativeCrashCount"/></td>
                                    <td><s:property value="#request.overallTimeInfo.AverAnrCount"/></td>
                                    </tr>
                   </table>           
         </div><br><br>
<div id="tabbox">
    <ul class="tabs" id="tabs">
       <li><a href="#">底层测试信息</a></li>
       <li><a href="#">上层测试信息</a></li>
       <li><a href="#">ANR</a></li>
       <li><a href="#">JavaCrash</a></li>
       <li><a href="#">NativeCrash</a></li>
       <li><a href="#">测试版本信息</a></li>     
    </ul>
    <ul class="tab_conbox" id="tab_conbox">
    <li class="tab_con">
             <table class="orderedlist" width="860">
                                    <tr>
                                    <th>设备</th>
                                    <th>BUG ID</th>
                                    <th>运行时长</th>
                                    <th>最终状态</th>
                                    <th>现象描述</th>
                                    <th>初步分析</th>
                                    </tr>
                       <s:iterator value="#request.lowerTestInfoList" id="lowerTestInfo">
                      <tr>
                       	   <%
                             String str="";
                             String str1="http://bugzilla.spreadtrum.com/bugzilla/buglist.cgi?quicksearch=";
                             String str2=(String)request.getAttribute("bugID");
                             str = str1+str2;
                           %>
                           <td><s:property value="#lowerTestInfo.testPhone"/></td>
                           <td><a style="text-align:left;color:#0000ff;text-decoration: underline;" target="_blank" href='<%=str%>'><s:property value="#lowerTestInfo.bugID"/> </a> </td>
                           <td><s:property value="#lowerTestInfo.testDuringTime"/></td>
                           <td><s:property value="#lowerTestInfo.testFinalState"/></td>
                           <td><s:property value="#lowerTestInfo.description"/></td>
                           <td><s:property value="#lowerTestInfo.preliminaryAnalysis"/></td>
                           </tr>
                       </s:iterator>
              </table>    
        </li>
        
        <li class="tab_con">
            <table class="orderedlist" width="860">
                                    <tr>
                                    <th>设备</th>
                                    <th>BUG ID</th>
                                    <th>首错时间</th>
                                    <th>首错错误类型</th>
                                     
                                    </tr>
                        <s:iterator value="#request.uperTestInfoList" id="uperTestInfo">
                        <tr>
                               <%
                             String str="";
                             String str1="http://bugzilla.spreadtrum.com/bugzilla/buglist.cgi?quicksearch=";
                             String str2=(String)request.getAttribute("bugID");
                             str = str1+str2;
                               %>

                           <td><s:property value="#uperTestInfo.testPhone"/></td>
                           <td><a style="text-align:left;color:#0000ff;text-decoration: underline;" target="_blank" href='<%=str%>'><s:property value="#uperTestInfo.bugID"/> </a> </td>
                           <td><s:property value="#uperTestInfo.firstErrTime"/></td>
                           <td><s:property value="#uperTestInfo.firstErrorType"/></td>
                           
                        </tr>
                      </s:iterator>
             </table>     
        </li>
        
        <li class="tab_con">
            <table  class="orderedlist" width="860">
                                    <tr>
                                    <th>设备</th>
                                    <th>首次ANR时间</th>
                                    <th>ANR数目</th>
                                    <th>模块列表</th>
                                   </tr>
                       <s:iterator value="#request.uperTestInfoList" id="uperTestInfo">
                       <tr>
                           <td><s:property value="#uperTestInfo.testPhone"/></td>
                           <td><s:property value="#uperTestInfo.firstANRTime"/></td>
                           <td><s:property value="#uperTestInfo.numOfANR"/></td>
                           <td><s:property value="#uperTestInfo.modleOfANR"/></td>
                       </tr>
                      </s:iterator>
                 </table> 
          </li> 
                        
        <li class="tab_con">
            <table  class="orderedlist" width="860">
                                    <tr>
                                    <th>设备</th>
                                    <th>首次JavaCrash时间</th>
                                    <th>JavaCrash数目</th>
                                    <th>模块列表</th>
                                    </tr>
                       <s:iterator value="#request.uperTestInfoList" id="uperTestInfo">
                       <tr>
                           <td><s:property value="#uperTestInfo.testPhone"/></td>
                           <td><s:property value="#uperTestInfo.firstJavaCrashTime"/></td>
                           <td><s:property value="#uperTestInfo.numOfJavaCrash"/></td>
                           <td><s:property value="#uperTestInfo.modleOfJavaCrash"/></td>
                       </tr>
                      </s:iterator>
                 </table> 
          </li>

          <li class="tab_con">
            <table  class="orderedlist" width="860">
                                    <tr>
                                    <th>设备</th>
                                    <th>首次NativeCrash时间</th>
                                    <th>NativeCrash数目</th>
                                    <th>模块列表</th>
                                    </tr>
                       <s:iterator value="#request.uperTestInfoList" id="uperTestInfo">
                       <tr>
                           <td><s:property value="#uperTestInfo.testPhone"/></td>
                           <td><s:property value="#uperTestInfo.firstNativeCrashTime"/></td>
                           <td><s:property value="#uperTestInfo.numOfNativeCrash"/></td>
                           <td><s:property value="#uperTestInfo.modleOfNativeCrash"/></td>
                       </tr>
                      </s:iterator>
                 </table> 
          </li>
          
          <li class="tab_con">
                     <div style="font-size:14px;"><%=formNameDisplay%><br>
                     <strong><span>测试版本路径:</span></strong><br/>
                     <span><a style="text-align:left;color:#0000ff;text-decoration: underline;" target="_blank" href="<s:property value="#request.pACPath.split(' ')[0]"/>">
                     <s:property value="#request.pACPath.split(' ')[0]"/></a>
                     </span>
                   <span> <strong>PAC:</strong>&nbsp;<s:property value="#request.pACPath.split(' ')[1]"/></span><br>
                   <span> <strong>log路径:</strong>&nbsp;<s:property value="#request.logPath"/></span><br><br>
                 </div>              
          </li>
          
     </ul>
</div><br><br>
<!-- tab特效的切换jqplot -->
                               
<div class="chart_tabbox">
 <ul class="chart_tabs" id="chart_tabs">
       <li><a href="#">停止均值</a></li>
       <li><a href="#">停止中值</a></li>
       <li><a href="#">首错均值</a></li>
       <li><a href="#">首错中值</a></li>
       <li><a href="#">整体汇总</a></li>
    </ul>
    <ul class="chart_tab_conbox" id="chart_tab_conbox">
    <li class="chart_tab_con">
       <div id="chart1" class="figure_project"></div>
    </li>
    <li class="chart_tab_con">
       <div id="chart2" class="figure_project"></div>
    </li>
    <li class="chart_tab_con">
       <div id="chart3" class="figure_project"></div>
    </li>
    <li class="chart_tab_con">
       <div id="chart4" class="figure_project"></div>
    </li>
    <li class="chart_tab_con">
       <div id="chart5" class="figure_project"></div>
    </li>
    
    </ul>
 <div>
    <div style="float:left;margin-left:80%;">报告下载 </div>                                      
         <div style="float:left;margin-left:10px;">
         <a style="color:#0000ff;font-size: 15px;"target="_blank" href="download?testFormName=<s:property value="#request.testFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>
                                                            
</div>  
</div><br><br>           

            <div id="project_hide">
                    <s:textarea name="version" id="project_value"></s:textarea>
                                <s:submit id="project_jump" method="byProjectAndVersion"></s:submit>
            </div>                                         
                    </div>
                </div>
            </div>
   </s:form>
</body>
</html>
