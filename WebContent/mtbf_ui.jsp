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
<title>mtbf_uiautomator测试首页</title>
   <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" href="css/mtbf_ui.css" type="text/css">
  
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    
    <script type="text/javascript" src="jqplot/jquery.js"></script>
  
   <script src="js/jquery.min.js"></script>
   <script src="js/bootstrap.min.js"></script>
   <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
    
     <script>
 <!--时间日期设置 -->
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
 		var projectFlag = 0;
 		var dateFlag = 0;
 		var versionFlag = 0;
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
 				if (versionFlag == 0) {
 					$("#searchProject").unbind();
 				}

 			} else if (value == "project") {
 				$("#searchProject").val("sp731gga_uui");
 				if (projectFlag == 0) {
 					$("#searchProject").unbind();
 				}
 			}
 		});
 	});

 	$(document).ready(function() {
 		$("#auto").change(function() {
 			// alert($(this).children('option:selected').val());
 			$("#auto_submit").click();
 		});
 	});

<!--表格折叠与展开-->
$(function(){
 	    $('tr.parent').click(function(){   // 获取所谓的父行
 	            $(this)
 	                .toggleClass("selected")   // 添加/删除高亮
 	                .siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
 	    }).click();
 	})
<!--点击阅读更多内容-->
$(function () {    
    // Grab all the excerpt class  
    $('.excerpt').each(function () {  
        // Run formatWord function and specify the length of words display to viewer       
        $(this).html(formatWords($(this).html(), 48));         
        // Hide the extra words  
        $(this).children('span').hide();  
    // Apply click event to read more link  
    }).click(function () {    	
        // Grab the hidden span and anchor  
        var more_text = $(this).children('span.more_text');  
        var more_link = $(this).children('a.more_link'); 
        // Toggle visibility using hasClass  
        // I know you can use is(':visible') but it doesn't work in IE8 somehow...  
        if (more_text.hasClass('hide')) { 
            more_text.show();      
            //document.write(new_sentence.replace('...',' '))
            more_link.html(' « hide');         
            more_text.removeClass('hide'); 
        } else { 
            more_text.hide(); 
            more_link.html(' » more');             
            more_text.addClass('hide'); 
        } 
        return false;  
    }); 
}); 

// Accept a paragraph and return a formatted paragraph with additional html tags 
function formatWords(sentence, show) { 
    // split all the words and store it in an array 
    var words = sentence; 
    var new_sentence = '';
    // loop through each word 
    for (i = 0; i < words.length; i++) { 
        // process words that will visible to viewer 
        if (i <= show) { 
            new_sentence += words[i];         
        // process the rest of the words 
        } else {   
            // add a span at start 
            if (i == (show + 1)) new_sentence +='<span class="more_text hide">';        
            new_sentence += words[i];      
            // close the span tag and add read more link in the very end 
            if (words[i+1] == null) new_sentence += '</span><a href="#" class="more_link" style="color:#0000CD;float:right;margin-right:10px;"> » more</a>';  
        }         
    }   
    return new_sentence;  
}
$(document).ready(function() {
    $(".table tr").hover(function() {
        // $("#orderedlist li:last").hover(function() {
            $(this).addClass("white");
        }, function() {
            $(this).removeClass("white");
        });
    });
    </script>

</head>
<body>
<s:form action="mtbf_ui"  theme="simple">
<div class="page">
  <div class="sidebar">
<img src="images/logo.png" alt="logo"><br><br><br><br>
   	<ul>
    	<li><a href="login" class="button blue medium">返回首页</a></li>
   	</ul>
   </div>
   <div class="right">
    	<div class="right_whole">
    	<div class="bar1" id="bar1">
    	 <ul class="bar1_first" id="bar1_first">
    	 	<li style="float:right;width:290px;margin-right:20px;">
    	    <s:select  style="font-size:14px;" id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" name="type" > 
              </s:select>
             <s:textfield id="searchProject" name="searchProject"
				value="sp7731gga_uui"
				onFocus="value='';this.style.color='#000';"
				onBlur="if(!value){value=defaultValue;this.style.color='#999';}"
				style="color:#999999;font-size:16px;width:120px">
			</s:textfield>
             <s:submit style="font-size:14px;" id="search" value="搜索" method="search"></s:submit>
    		 </li>    		 
    		 <li style="float:left;padding-left:50px;padding-top:2px">
    		 	<s:select id="auto" style="width:450px;text-align:center;" list="projectList" name="currentProject"></s:select>
    		 	<s:submit id="auto_submit" value="搜索" method="execute" style="display:none"></s:submit>			
    		 </li>
    	  </ul>
    	  </div><br>
  
		<table width="820px" style="margin-left:4%" border="1" height="80px" class="pacinformation">
			<tr >
				<th colspan="2">测&nbsp&nbsp试&nbsp&nbsp版&nbsp&nbsp本</th>
			</tr>
			<tr>
				<td width="15%" style="font-size:16px;font-weight:bold;">工程名</td>
				<td width="85%" style="text-align:left;"><s:property value="currentProject"/></td>
			</tr>
			<tr>
				<td style="font-size:16px;font-weight:bold;">测试版本</td>
				<td style="text-align:left;"><s:property value="softwareVsn"/></td>
			</tr>
			<tr>
				<td style="font-size:16px;font-weight:bold;">PAC地址</td>
				<td><a style="color:blue;" href=<s:property value="pacPath"/>><s:property value="pacPath"/></a></td>
			</tr>
			<tr>
				<td style="background-color:#FFFFF0;font-size:16px;font-weight:bold;">初步结论</td>
				<td style="text-align:left;">
				<p class="excerpt"><s:property value="conclusion"/></p></td>
			</tr></table><br>
		<table width="820px" style="margin-left:4%" border="1" height="80px" class="failure">
			<tr >
				<th colspan="2">故&nbsp&nbsp障&nbsp&nbsp信&nbsp&nbsp息</th>
			</tr>
			<tr>
				<td width="50%" style="font-size:16px;font-weight:bold;">设备编号</td>
				<td width="50%" style="font-size:16px;font-weight:bold;">每台设备故障数</td>
			</tr>
			<s:iterator value="#request.allDeviceList" id="device">
			<tr>
				<td><s:property value="#device.deviceNum"/></td>
				<td><s:property value="#device.errorEach"/></td>
			</tr>
			</s:iterator>
		</table>
 <div class="bar_tabbox1" id="bar_tabbox1">
        <ul class="graybtn1" style="float: left"></ul>
        <ul class="bar_tabs1" id="bar_tabs1" style="float:right">
        <li>统计信息</li>
        </ul>
    </div><br> 
    <table width="820px" style="margin-left:4%" border="1" height="80px" class="conclusion">
      <tbody>
       <tr>
      	 <th>设备总数</th>
      	 <th>执行轮数</th>
      	 <th>运行总时间(h)</th>
       	 <th>总故障数(次)</th>
       	 <th>MTBF值</th>
       </tr>
       <tr>
       	<td><s:property value="deviceNum"/></td>
       	<td><s:property value="runNum"/></td>
        <td><s:property value="runTimeAll"/></td>
        <td><s:property value="errorTotal"/></td>
        <td><s:property value="mtbfValue"/></td>
        </tr>   
      </tbody>
    </table>
  <div><span style="font-size:14px;font-weight:bold;margin-left:4%">备注：</span>
  <ul style="margin-left:6%">
  <li>1.MTBF值=所有终端运行总时间/所有终端故障总数。</li>
  <li>2.一旦某台终端出现死机、重启、冻屏等严重问题，则停止测试该台终端，
  已运行时间记为该台终端运行时间。</li>
  <li>3.故障定义：死机、重启、冻屏等严重本地故障；如单台终端的网络交互业务通过率未达到95%，也记为一次故障。</li>
  </ul>
  </div>
      
   		 <div class="bar_tabbox1" id="bar_tabbox1">
        	<ul class="graybtn1" style="float: left"></ul>
        	<ul class="bar_tabs1" id="bar_tabs1" style="float: right">
        	<li>测试报告</li>
       		</ul>
   		 </div><br>
 
     
<!--case表格统计-->
	<table class="caselist"  width="880px" border="1" style="margin-left:20px;margin-top:10px">
		<tbody>
		<tr>
		<th width="5%">编号</th>
		<th width="16%">项目名称</th>
		<th width="4%">轮次</th>
		<th width="15%">预置条件</th>
		<th width="20%">执行步骤</th>
		<th width="20%">循环部分</th>
		<th width="16%">检查点</th>
		<th width="4%">级别</th>
		</tr>
	        <%
			    String str = (String)request.getAttribute("groups0");
				int i = 0;
				int j = 1;
				String mu = "";
			%>			
			<s:iterator value="#request.allCaseList" id="gp">
			<%				
				String var = (String)request.getAttribute("groups");
				if (i==0){
					mu = "mu"+i;
					%><tr class="parent" id="<%=mu%>"><th colspan="8" style="text-align:left;background-color:#B4EEB4;font-weight:normal;">
					&nbsp<%=str%><img style="float:left;margin-left:0px;margin-top:0px;"height="18" width="18" alt="line" src="images/opened.png"></th></tr><%
				}
				else {
				if (!str.equals(var)){
					str = var;
					mu = "mu"+i;
					j++;
	        		%><tr class="parent" id="<%=mu%>"><th colspan="8" style="text-align:left;background-color:#B4EEB4;font-weight:normal;">&nbsp<%=str%>
	        		<img style="float:left;margin-left:0px;margin-top:0px;"height="18" width="18" alt="line" src="images/opened.png"></th></tr><%	        	    
	        	    }
				}
				i++;
			%>
			
			<tr class="child_<%=mu%>">
			<td>&nbsp&nbsp<s:property value="#gp.serial"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.programName"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.cycleNum"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.preCondition"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.executeStep"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.cyclePart"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.checkPoint"/></td>
			<td>&nbsp&nbsp<s:property value="#gp.testLevel"/></td>
			</tr>			
			</s:iterator>		         
     </tbody>
     </table><br><br><br><br>
      <!-- 报告下载 -->  
    <div>
    <div style="float:left;margin-left:80%;">报告下载</div>
     <div style="float:left;margin-left:10px;">
  <a style="color:#0000ff;font-size: 15px;"target="_blank" href="mtbf_uidownload?currentFormName=<s:property value="#request.currentFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>   
     </div>
   </div>
   </div>
 </div>  
</s:form>
</body>
</html>
