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

 <title>CTS测试信息</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" >
        
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" href="css/cts.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
 
    
   
    <!-- 日期时间设置 -->
    <script>
	$(document).ready(function() {
		$("#auto").change(function() {
			// alert($(this).children('option:selected').val());
			$("#auto_submit").click();
		});
	});

	$(document).ready(function() {
		$("#search").bind('click', function() {
			var value = $("#select").children('option:selected').val();
			if (value == 'date') {
				//var dateValid = /^\d{1,2}\-\d{1,2}\-\d{4}$/;
				var dateValid = /^\d{4}\-\d{2}\-\d{2}$/;
				var date = $("#searchProject").val();
				if (!dateValid.test(date)) {
					alert("日期格式不正确,请写成 yyyy-mm-dd 格式");
					return false;
				}
			}
		});
	});

	$(document).ready(function() {
		var projectFlag =0;
		var dateFlag =0;
		var versionFlag =0;
		$("#select").change(function() {
			var value = $(this).children('option:selected').val();
			if (value == "date") {
				var d = new Date(), str = '';
				str += d.getFullYear() + '-'; //获取当前年份
                if (d.getMonth() < 9) {
                	str += "0";
                }
				str += d.getMonth() + 1 + '-'; //获取当前月份（0——11）
                if (d.getDate() < 10) {
                	str += "0";
                }
				str += d.getDate();
				$("#searchProject").val(str);//设置默认日期为今天的日期
				$("#searchProject").datepicker({
					dateFormat : 'yy-mm-dd',
					changeMonth : true,
					changeYear : true,
					showWeek : true,
					firstDay : 1
				});
			} else if (value == "pacVersion") {
				$("#searchProject").val("W14_40_1-02");
				if (versionFlag==0){
					$("#searchProject").unbind();
				}

			} else if (value == "project") {
				$("#searchProject").val("sp731gga_uui");
				if (projectFlag==0){
					$("#searchProject").unbind();
				}
			}
		});
	});
    </script>
  	
</head>
<body>
 <s:form action="cts" theme="simple">
 <div class="page">
 <div class="sidebar">
   <img src="images/logo.png" alt="logo"><br><br><br>
   <ul>
   <li><a href="login" class="button blue medium">返回主页</a></li>
   </ul>
  </div>
 <div class="right">
 	<div class="rightwhole">
 	<div class="bar1" id="bar1">
 	<ul class="bar1_first" style="margin-top:50px;padding-right:5px">
 	<li style="float:right;width=280px;margin-right:-20px;margin-left:150px">
								<s:select style="font-size:16px;" id="select"
									list="#{'project':'工程名','date':'时间','pacVersion':'版次'}"
									name="type">
								</s:select> <s:textfield id="searchProject" name="searchProject"
									value="sp7731gga_uui"
									onFocus="value='';this.style.color='#000';"
									onBlur="if(!value){value=defaultValue;this.style.color='#999';}"
									style="color:#999999;font-size:16px;width:120px">
								</s:textfield> <s:submit style="font-size:16px;" id="search" value="搜索"
									method="search"></s:submit>
 	</li>
 	<li style="float:left;padding-left:50px;font-size:16px">
 	<s:select style="width:400px;text-align:center;" id="auto" list="ctsValidProjects" name="currentProject"></s:select>
    <s:submit id="auto_submit" value="搜索" method="execute" style="display:none"></s:submit>
    <br><br>
    </li>
 	</ul><br><br><br>
    </div><br><br>
 	   <div class="bar_tabbox1" id="bar_tabbox1">
             <ul class="graybtn1" style="float:left"></ul>
              <ul class="bar_tabs1" id="bar_tabs1" style="float:right">
              
              <li>最新报告</li>
              </ul>
        </div><br>
         
         <div class="report_information">
     
        <ul class="report_information_list">
        
        <div><span style="float:left;margin-left:80px">测试报告：</span></div><li><a href=<s:property value="#request.lastTestInfo.reportUrl"/>><s:property value="#request.lastTestInfo.reportUrl"/></a></li>
       
        <li>log路径：<a href=<s:property value="#request.lastTestInfo.logPath"/>><span style="margin-left:20px"><s:property value="#request.lastTestInfo.logPath"/></span></a>	</li>
        
        <li>版本信息:</li> </ul>
        <ul class="detail_information">
        <li>版次<s:property value="#request.lastTestInfo.softwareVsn"/></li>
        <li>版本路径<a href=<s:property value="#request.lastTestInfo.pacPath"/>><s:property value="#request.lastTestInfo.pacPath"/></a></li>
        <li>硬件版本<s:property value="#request.lastTestInfo.hardwareVsn"/></li></ul>
       
        </div><br>
       <div class="bar_tabbox2" id="bar_tabbox2">
             <ul class="graybtn2" style="float:left"></ul>
              <ul class="bar_tabs2" id="bar_tabs2" style="float:right">
              
              <li>更多</li>
              </ul>
            </div><br>            
         
            <s:iterator value="#request.moreTestInfo" id="TestInfo">
       <div style="float:left;margin-left:80px;font-size:17px;margin-top:16px;display:inline">
        <img height="13" width="13" alt="line" src="images/line.png">
       </div>
       <div id="version"><s:property value="#TestInfo.formName"/></div>
       <div id="date"><s:property value="#TestInfo.testDate"/></div>
       <div style="float:left">       
          <h4>
        <a data-toggle="collapse" data-parent="#accordion" 
          href="#<s:property value="#TestInfo.id"/>" style="font-size:17px;text-align:center;float:left;padding-top:3px;margin-left:140px">查看</a>
          </h4><br>
       </div> <br><br>
    
      

    
    <div id="<s:property value="#TestInfo.id"/>" class="panel-collapse collapse ">
      
        <div >
        <div class="oldreport_information" style="margin-left:40px">
        <ul class="oldreport_information_list">
        <li>测试报告：<s:property value="#TestInfo.reportUrl"/></li><br>
        <li>log路径：<s:property value="#TestInfo.logPath"/></li><br>
        <li>版本信息：</li><br>
        </ul>
        </div> 
      </div>
    </div>
    </s:iterator>
    
     
  </div>
     <!-- 报告下载 -->
  <div style="float:left;margin-left:80%">报告下载</div>
     <div style="float:left;margin-left:10px;">
  <a style="color:#0000ff;font-size: 15px;"target="_blank" href="download?testFormName=<s:property value="#request.testFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>   
   
     </div>  
   
        </div>     
          
 	            <div class="container-fluid">
	<div class="row-fluid">
		<div class="span7">qw
		</div>
		<div class="span4">ss
		</div>
		<div class="span1">dd
		</div>
	</div>
</div>
 

 
 	


</s:form> 
  



</body>
</html>