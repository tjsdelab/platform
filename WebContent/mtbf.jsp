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
    <title>MTBF_comcat版测试首页</title>   
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link rel="stylesheet" href="css/tab.css" type="text/css">
    <link rel="stylesheet" href="css/mtbf.css" type="text/css">
 
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
/*	
$(function(){
		var options={
			animation:true,
			trigger:'click' //触发tooltip的事件
		}
		$("#a_pop1").popover(options);
		$("#a_pop2").popover(options);
		//$('#tip1').tooltip('show');//指定显示某一个tooltip//还可取值hide toggle
	});*/

$(function(){
    $('tr.parent').click(function(){   // 获取所谓的父行
      $(this)
       .toggleClass("selected")   // 添加/删除高亮
       .siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
    }).click();
  });
  
<!--点击阅读更多内容-->
$(function () {    
    // Grab all the excerpt class  
    $('.excerpt').each(function () {  
        // Run formatWord function and specify the length of words display to viewer       
        $(this).html(formatWords($(this).html(), 50));         
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
            if (i == (show + 1)) new_sentence +='...<span class="more_text hide">';        
            new_sentence += words[i];      
            // close the span tag and add read more link in the very end 
            if (words[i+1] == null) new_sentence += '</span><a href="#" class="more_link" style="color:#0000CDfloat:right;margin-right:10px;"> » more</a>';  
        }         
    }   
    return new_sentence;  
}

</script>
</head>
<body>
<s:form action="mtbf" theme="simple">
<div class="page">
         <div class="sidebar">
            <img src="images/logo.png" alt="logo">
             <br><br><br><ul>
    	<li><a href="login.action" class="button blue medium">返回主页</a></li>
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
    	  </div><br><br>
    <table width="820px" style="margin-left:4%" border="1" class="conclusion" >
     	<tbody >
     		<tr>	
     		<th>version</th>
     		<th>Total</th>
     		<th>执行轮数</th>
     		<th>运行总时间</th>
     		<th>NA</th>
     		<th>总故障数</th>     		
     		<th>MTBF值</th>
     		</tr>
     		<tr>
     			<td><s:property value="softwareVsn"/></td>
     			<td><s:property value="total"/></td>
     			<td><s:property value="runNum"/></td>
     			<td><s:property value="runTimeAll"/></td>
     			<td> </td>
     			<td><s:property value="errorNum"/></td>
     			<td><s:property value="mtbfValue"/></td>    		
     		</tr>
     		<tr>
     		<td style="font-weight:bold;color:#6A5ACD;">结&nbsp&nbsp&nbsp论</td>
     		<td colspan="6" style="text-align:left;"><p class="excerpt"><s:property value="conclusion"/></p></td>
     		</tr> 
     		<tr>
     		<td style="font-weight:bold;color:#4876FF;">新增问题</td>
     		<td colspan="6" style="text-align:left;"><p class="excerpt"><s:property value="issueNew"/></td></tr>
     		<!--<div class="btn-group" style="margin-left:25px;" >  	
     		<a class="btn btn-small btn-success" id="a_pop1" href="javascript:void(0)" style="width:100px"
     		data-placement="bottom" data-container="body" data-content="<s:property value="issueNew"/>"
     		data-original-title="新增问题：">新&nbsp增&nbsp问&nbsp题&nbsp&nbsp&nbsp&nbsp&nbsp
     		<img style="float:left;margin-left:70px;margin-top:-17px;position:absolute"height="15" width="15" alt="line" src="images/opened.png"> </a>
     		</div>
     		<div class="btn-group" style="margin-left:25px;">  	
     		<a class="btn btn-small btn-success" id="a_pop2" href="javascript:void(0)"
     		data-placement="bottom" data-container="body" data-content="<s:property value="issueLeave"/>"
     		data-original-title="遗留问题：">遗&nbsp留&nbsp问&nbsp题&nbsp&nbsp
     		<img style="float:left;margin-left:70px;margin-top:-17px;position:absolute"height="15" width="15" alt="line" src="images/opened.png"> </a>
     		</div>-->
     		<tr>
     		<td style="font-weight:bold;color:#4876FF;">遗留问题</td>
     		<td colspan="6" style="text-align:left;"><p class="excerpt"><s:property value="issueLeave"/></td></tr>   		
    	 </tbody>
     </table><br><br>
	    <!-- <div style="margin-top:7px;margin-bottom:7px;margin-left:42px;">
			<strong style="font-size:16px;background-color:#ffffff;">备&nbsp&nbsp&nbsp注</strong>
		</div> --> 
			
		


    <div class="bar_tabbox1" id="bar_tabbox1">
        <div style="float:left; margin-left:0px; font-size:17px; margin-top:4px; display: inline">
			<img height="20" width="20" alt="line" src="images/aperture.png">
		</div>
        <div style="float:left; margin-left:20px; margin-top:-5px; display: inline">
			<img height="6" width="900" alt="line" src="images/caolv.png">
		</div>
        <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:26px; margin-top:-25px;">设备详细信息</div>   
    </div><br><br>
    
    <table width="820px" style="margin-left:4%;margin-top:15px;" border="1" class="conclusion" >
      <tbody>
       <tr>
       <th>手机编号</th>
       <th>sim卡编号</th>
       <th>开始运行时间</th>
       <th>结束运行时间</th>
       <th>当前测试轮数</th>
       <th>每台故障数</th>
       </tr>
       <s:iterator value="#request.allDeviceList" id="device">
       <tr>
          <td><s:property value="#device.deviceNum"/></td>
          <td><s:property value="#device.simCardType"/></td>
          <td><s:property value="#device.startTime"/></td>
          <td><s:property value="#device.stopTime"/></td>
          <td><s:property value="#device.testTimes"/></td>
          <td><s:property value="#device.singleErrNum"/></td>
       </tr></s:iterator>       
      </tbody>
    </table><br><br>   
  <div class="bar_tabbox2" id="bar_tabbox2">
        <div style="float:left; margin-left:0px; font-size:17px; margin-top:4px; display: inline">
			<img height="20" width="20" alt="line" src="images/aperture.png">
		</div>
        <div style="float:left; margin-left:20px; margin-top:-5px; display: inline">
			<img height="6" width="900" alt="line" src="images/caolv.png">
		</div>
        <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:26px; margin-top:-25px;">测试报告信息</div>   
    </div><br><br>

<!--case表格统计-->
	<table class="caselist"  width="880px" style="margin-left:20px;margin-top:10px">
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
			<td>&nbsp<s:property value="#gp.serial"/></td>
			<td>&nbsp<s:property value="#gp.programName"/></td>
			<td>&nbsp<s:property value="#gp.cycleNum"/></td>
			<td>&nbsp<s:property value="#gp.preCondition"/></td>
			<td>&nbsp<s:property value="#gp.executeStep"/></td>
			<td>&nbsp<s:property value="#gp.cyclePart"/></td>
			<td>&nbsp<s:property value="#gp.checkPoint"/></td>
			<td>&nbsp<s:property value="#gp.testLevel"/></td>
			</tr>			
			</s:iterator>		
       
       
       
     </tbody>
     </table><br><br><br><br>  
   </div>
   
  </div>
</div>
</s:form>
</body>
</html>