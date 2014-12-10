<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="renderer" content="webkit">
<head>
<title>EPE测试首页</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <link rel="stylesheet" type="text/css" href="css/epe.css" />
    <link rel="stylesheet" href="css/sanity.css" type="text/css"> 

    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
    <script type="text/javascript" src="jqplot/jquery.jqplot.min.js"></script>
    <script type="text/javascript" src="jqplot/jqplot.highlighter.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.cursor.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.dateAxisRenderer.min.js"></script>  
     <script type="text/javascript" src="jqplot/jqplot.barRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.categoryAxisRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasAxisTickRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.canvasTextRenderer.min.js"></script>
     <script type="text/javascript" src="jqplot/jqplot.pointLabels.min.js"></script>
     <script>
 <!--实现tabs切换效果 -->
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
                    $.jqtab("#tabs1","#tabs1_conbox","mouseenter");
    	    });
    	     
    	    $(document).ready(function(){
    	        $("#search").bind('click', function(){
    	        	var value = $("#select").children('option:selected').val();
    	        	if(value == 'date'){
    	        	//var dateValid = /^\d{1,2}\-\d{1,2}\-\d{4}$/;
    	        	   var dateValid = /^\d{4}\-\d{2}\-\d{2}$/;
    	        	var date = $("#searchProject").val();
    	        	if(!dateValid.test(date)){
    	        		   alert("日期格式不正确,请写成 yyyy-mm-dd 格式");	
    	                   return false;
    	        	}
    	        	}
    	        });
    	    });
    	    <!--日期时间更新 -->
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
  
  <!--APR历史折线图-->  	    
    	    $(document).ready(function(){
                var line1 = [<s:property value="#request.epeValue"/>];
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
                         max:0.0001,
                         numberTicks:6,
                       tickOptions:{
                              showMark: false,
                         formatString:'%.5f'
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
                       tooltipFormatString: '<b style="background-color:#ebe8db;padding:6px 3px"><i><span style="color:red;">Time:</span></i> %.7f</b>',
                       useAxesFormatters: false
                   },
                   cursor: {
                     show: false 
                   }
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
    	                $("#project_jump").click()    	                       
    	           });});
    	    
    	    $(document).ready(function(){
    	        $("#auto").change(function(){
    	              // alert($(this).children('option:selected').val());
    	               $("#auto_submit").click();
    	                    });
    	    });
      </script>
      
</head>
    
<body>
<s:form action="epe" theme="simple">
        <div class="page">
            <div class="sidebar">
               <img src="images/logo.png" alt="logo">
                <br><br><ul>
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
                
	 	<!--bar1 相关设计  -->
	 	<div class="bar1" id="bar1" style="margin-top:20px;margin-bottom:20px" >
	 	<ul class="bar1_first" id="bar1_first">
	 	<li style="float:right;width:280px;margin-right:-20px;">
		 <s:select  style="font-size:14px;" id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" name="type" > 
              </s:select>
             <s:textfield id="searchProject" name="searchProject" value="sp7731gga_uui" 
                      onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                      onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
                      style="color:#999999;font-size:14px;width:100px">
             </s:textfield>
             <s:submit style="font-size:14px;" id="search" value="搜索" method="search"></s:submit>
          </li>
		  <li  style="float:left;">
		  <s:select id="auto" style="width:600px;text-align:center;margin-top:2px;" list="projectList" name="currentProject">
          </s:select>
          <s:submit id="auto_submit" value="搜索" method="dropDownProject" style="display:none"></s:submit>
		  </li>
		  
		  </ul>
		 </div>
		 
<!--测试标准说明信息 -->
 	 <div style="text-align:left;margin:20px 10px;">
  		<strong>测试说明信息</strong>
 		 <ul>
 		 <li>1. 测试参数：throttle：300ms, 每个apk events: 200000个,循环次数：1轮次;</li>
 		 <li>2. 底层问题（定屏/黑屏/重启/sysdump等问题）的平均时间不小于20小时，中值时间达到25小时；<br>
         第一次出现ANR/CRASH等异常的平均时间不小于12小时，中值时间不小于15小时）;</li>
  		<li>3. EPE值标准：PTR1，<0.0005；PTR2，<0.0002；PTR3，<0.0001;</li>

  		<b>注：EPE,即Exception Percent of Events的简称，是个百分比率。<br>
  		EPE值计算公式：EPE= (所有问题数/(单次事件数×总循环数) )x100，即，EPE=(所有问题数/(200000X10X有效设备数))x100';</b>
  		</ul>
  	 </div>
          
          <table width="900px" border="1" height="80px" class="conclusion" >
         
          <tbody>
          
          <tr bgcolor="#CCFF66">
          <th rowspan="2" width="15%"> 测试版本</th>
          <th rowspan="2" width="15%">手机数/台</th>
          <th rowspan="2" width="10%">pass手机数</th>
          <th colspan="6" width="50%">fail手机数</th>
          
          <th rowspan="2" width="10%">EPE值</th>
          
          </tr>
          <tr bgcolor="#CCFF66">
          <th width="%8">锁屏</th>
          <th width="%8">黑屏</th>
           <th width="%8">重启</th>
            <th width="%10">Sysdump</th>
            <th width="%8">掉电</th>
             <th width="%8">其他</th>
            </tr>
            <tr>
            <td><s:property value="version"/></td>
             <td><s:property value="sumDevice"/></td>
              <td><s:property value="passDevice"/></td>
               <td><s:property value="freezeNum"/></td>
                <td><s:property value="blackNum"/></td>
                 <td><s:property value="restartNum"/></td>
                  <td><s:property value="sysdumpNum"/></td>
                   <td><s:property value="unpowerNum"/></td>
                    <td><s:property value="otherNum"/></td>
                     <td><s:property value="epeValue"/></td>
                    
                   </tr>
             <tr>
             <td style="font-weight:bold;color:#FF99FF;height:25px;">Conclusion</td>
             <td colspan="9"><s:property value="conclusion"/></td>
             </tr>
            </tbody>
             </table><br><br>
<!-- tabs -->
          <div id="tabsbox">
              <ul id="tabs1" class="tabs1">
                     <li >测试时间</li>                  
                     <li>测试错误类型</li>
               </ul><br>
              
             
       <ul id="tabs1_conbox" class="tabs1_conbox">
          <li class="tabs_con" style="display:list-item">
                <table class="orderedlist" width="900px" style="margin-top:-10px;">
                <tbody>
                <tr>
                  <th width="25%">时间</th>
                  <th width="25%">运行时间</th>
                  <th width="25%">首错时间</th>
                </tr>
               
               <tr>
                    <td >平均时间</td>
            	    <td ><s:property value="averageRunTime"/></td>
                    <td ><s:property value="averageFirstError"/></td>
               </tr>
               <tr>
       	           <td>中间时间</td>
                   <td ><s:property value="medianRunTime"/></td>
                   <td ><s:property value="medianFirstError"/></td>
               </tr>
             </tbody>
            </table>      
         </li>  
 <!-- 测试错误类型 -->    
      <li class="tabs_con" style="display:list-item">
       <table class="orderedlist" width="900px" style="margin-top:-10px;">
         <tbody>
     <tr>
     <th>模块</th>
     <th>JavaCrash</th>
     <th>NativeCrash</th>
     <th>ANR</th>
     </tr>
     

   <s:iterator value="#request.craInfoList" id="cra">
    <tr>           
           <td><s:property value="#cra.packageName"/></td>      
           <td><s:property value="#cra.javaCrashFrequency"/></td>
           <td><s:property value="#cra.nativeCrashFrequency"/></td>
           <td><s:property value="#cra.ANRFrequency"/></td>
    </tr>
    </s:iterator>
   
     <tr>
           <td>合计</td>
           <td><s:property value="javaCrashTotal"/></td>
           <td><s:property value="nativeCrashTotal"/></td>
           <td><s:property value="anrTotal"/></td>
     </tr>
       </tbody>   
      </table>      
      </li>      
         </ul>    
            
        </div><br><br>
      
<!-- 详细测试信息 --> 
         <div class="bar_tabbox1" id="bar_tabbox1">
             <ul class="graybtn1" style="float:left"></ul>
              <ul class="bar_tabs1" id="bar_tabs1" style="float:right">
              <!--  <li style="circle"></li>-->
              <li>详细测试信息</li>
              </ul>
        </div><br>  
           
       
     <table width="900px" border="1"  height="60px" class="information">
     <tbody>
      <tr>
      <th rowspan="2" width="18%">手机编号</th>
      <th rowspan="2" width="10%">设备有效性</th>
      <th rowspan="2" width="8%">测试时间</th>
      <th rowspan="2" width="13%">停止原因</th>
      <th colspan="3" width="51%">首错</th>
     </tr>
     <tr>
     <th width="10%">时间</th>
     <th width="11%">类型</th>
     <th width="30%">模块</th>
     </tr>
      <s:iterator value="#request.allDeviceList" id="device">
      <tr>
        <td><s:property value="#device.deviceNo"/></td>
        <td><s:property value="#device.deviceFlag"/></td>
        <td><s:property value="#device.runTime"/></td>
        <td><s:property value="#device.typeID.type"/></td>
        <td><s:property value="#device.firstErrorTime"/></td>
        <td><s:property value="#device.firstErrorType"/></td>
        <td><s:property value="#device.firstErrorModule"/></td>
       </tr>
     </s:iterator>
      
      </tbody>
      </table ><br>
        
<!--EPE 历史趋势图  -->
       <div class="bar_tabbox2" id="bar_tabbox2">
           <ul class="graybtn2" style="float:left"></ul>
             <ul class="bar_tabs2" id="bar_tabs2" style="float:right">
               <li style="circle"></li><li>EPE历史趋势图</li>
              </ul>
           </div><br>
      <div id="chart1" class="figure"></div>

     </div>
 <!-- 报告下载 -->  
    <div>
    <div style="float:left;margin-left:80%;">报告下载</div>
     <div style="float:left;margin-left:10px;">
  <a style="color:#0000ff;font-size: 15px;"target="_blank" href="epedownload?currentFormName=<s:property value="#request.currentFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>   
     </div>

		<div id="project_hide" style="display:none;">
                    <s:textarea name="version" id="project_value"></s:textarea>
                    <s:submit id="project_jump" method="versionJump"></s:submit>
            </div> 
      </div>
</div>
</s:form>
</body>
</html>