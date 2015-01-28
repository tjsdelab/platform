<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
<meta name="viewport" content="width=device-width, initial-scale=1">

  <head>
    <title>后台管理界面</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">    
    <link rel="stylesheet" href="css/back-stage.css" type="text/css">
    <link type="text/css" href="css/lrtk.css" rel="stylesheet" />
   
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="jqplot/jquery.js"></script>
	<script type="text/javascript" src="jqplot/lrtk.js"></script>
	<script type="text/javascript" src="jqplot/jquery-ui.js"></script>
	<script src="js/respond.js"></script>
    
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
		$("#autoM,#autoSn,#autoSm,#autoMu").change(function() {
			// alert($(this).children('option:selected').val());
			$("#auto_submitM,#auto_submitSn,#auto_submitSm,#auto_submitMu").click();
		});});
	
	
	

//Ajax实现邮件发送
	var XMLHttpReq = false;
	//创建XMLHttpRequest对象       
	function createXMLHttpRequest() {
	    if(window.XMLHttpRequest) { //Mozilla 浏览器
	        XMLHttpReq = new XMLHttpRequest();
	    }
	    else if (window.ActiveXObject) { // IE浏览器
	        try {
	            XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
	        } catch (e) {
	            try {
	                XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	            } catch (e) {}
	        }
	    }
	}
	//发送请求函数
	function sendRequest(url) {
	    createXMLHttpRequest();
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	    XMLHttpReq.send(null);  // 发送请求
	}
	// 处理返回信息函数
	function processResponse() {
	    if (XMLHttpReq.readyState == 4) { // 判断对象状态
	        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	            var result = XMLHttpReq.responseText;    
	            document.getElementById("data").innerHTML = result;     
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}	
	function sendMailM(){
		var contents =$("#contentsM").html();
		var To = $("#toM").text();
		var CC = $("#ccM").text();
		var subject = $("#subjectM").text();
		if (contents==""){
			window.alert("没有工程数据！")
		} else {
		var url = "backManageSendMail?contents=" +contents + "&To=" + To +"&CC=" + CC +"&subject=" +subject;  
		url=encodeURI(url); 
		url=encodeURI(url); 
	    sendRequest(url);
	    $("#myModalM").hide();
	    window.alert("邮件已发送!")
		}
	}
	function sendMailSn(){
		var contents =$("#contentsSn").html();
		var To = $("#toSn").text();
		var CC = $("#ccSn").text();
		var subject = $("#subjectSn").text();
		if (contents==""){
			window.alert("没有工程数据！")
		} else {
		var url = "backManageSendMail?contents=" +contents + "&To=" + To +"&CC=" + CC +"&subject=" +subject;  
		url=encodeURI(url); 
		url=encodeURI(url); 
	    sendRequest(url);
	    $("#myModalSn").hide();
	    window.alert("邮件已发送!")
		}
	}
	function sendMailSm(){
		var contents =$("#contentsSm").html();
		var To = $("#toSm").text();
		var CC = $("#ccSm").text();
		var subject = $("#subjectSm").text();
		if (contents==""){
			window.alert("没有工程数据！")
		} else {
		var url = "backManageSendMail?contents=" +contents + "&To=" + To +"&CC=" + CC +"&subject=" +subject;  
		url=encodeURI(url); 
		url=encodeURI(url); 
	    sendRequest(url);
	    $("#myModalSm").hide();
	    window.alert("邮件已发送!")
		}
	}
	function sendMailMu(){
		var contents =$("#contentsMu").html();
		var To = $("#toMu").text();
		var CC = $("#ccMu").text();
		var subject = $("#subjectMu").text();
		if (contents==""){
			window.alert("没有工程数据！")
		} else {
		var url = "backManageSendMail?contents=" +contents + "&To=" + To +"&CC=" + CC +"&subject=" +subject;  
		url=encodeURI(url); 
		url=encodeURI(url); 
	    sendRequest(url);
	    $("#myModalMu").hide();
	    window.alert("邮件已发送!")
		}
	}
	
	$(document).ready(function(){
	    $("#startMonitor").click(function() {
			undo("startMonitor");
			reveal("stopMonitor");
			$("#startMonitor").css("background-color","#919191");
			$("#stopMonitor").css("background-color","#EE0000");
			var url = "rdSendMailCtrl?timerFlag=start";  
		    sendRequest(url);
			return false;
	    });
	    $("#stopMonitor").click(function() {
			undo("stopMonitor");
			reveal("startMonitor");
			$("#stopMonitor").css("background-color","#919191");
			$("#startMonitor").css("background-color","#00FF00");
			var url = "rdSendMailCtrl?timerFlag=stop";  
		    sendRequest(url);
			return false;
	    });});
	function undo(button_id) {
		document.getElementById(button_id).disabled = true;
		}
	function reveal(button_id) {
		document.getElementById(button_id).disabled = false;
		}	

</script>
</head> 
<body>
<s:form action="back-stageManage" theme="simple">
<div class="page">    
<!-- 导航条taps -->
	<div class="navbar" role="navigation" style="margin-top:5px;background-color:#708069;width:1200px;">	
	   <a class="navbar-brand" style="font-size:22px;font-weight:bold;color:#FF6103">后台管理</a> 
		<div class="tabbable">
		  <ul class="nav nav-pills">
			<li class="active">
				<a data-toggle="tab" style="font-size:large;font-weight:bold;margin:3px;" href="#report">报告管理</a>
			</li>
			<li>
				<a data-toggle="tab" style="font-size:large;font-weight:bold;margin:3px;" href="#other">其他管理</a>
			</li>
		   </ul>
		</div></div><br>
<!-- 页面taps 首页展示报告管理report-->	
    <div class="tab-content">
		<div class="tab-pane active" id="report">				
	        <div class="tabbable tabs-left" id="tab-left">
	          <ul class="nav nav-tabs" id="tab-left-nav">  
	            <li class="active">
	            	<a data-toggle="tab" href="#monkey">Monkey测试</a>
	            </li>
	            <li>
	                 <a data-toggle="tab" href="#sanity">Sanity测试</a>
	            </li>
	            <li>
	                 <a data-toggle="tab" href="#smoke">Smoke测试</a>
	            </li> 
	            <li>
	                 <a data-toggle="tab" href="#mtbf_ui">Mtbf_ui测试</a>
	            </li> 
	            <li>
	                 <a data-toggle="tab" href="#monkeyForRD">monkeyForRD</a>
	            </li> 
	           </ul>
    </div>
<!-- 侧边栏taps 默认显示monkey-->	
      <div class="tab-content">
        <div class="tab-pane active" id="monkey">      
          
		 <div class="selectANDsearch" style="width:980px;margin-left:200px;">
	    	 <ul class="bar1_first" id="bar1_first">
	    	 	<li style="float:right;width:290px;margin-right:30px;">
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
	    		 <li style="float:left;padding-left:20px;padding-top:2px">
	    		 	<s:select id="autoM" style="width:450px;text-align:center;" list="projectListM" name="currentProjectM"></s:select>
	    		 	<s:submit id="auto_submitM" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">
        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:0px; margin-top:-25px;">最新报告</div>   
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:232px; margin-top:-25px;">表单名</div>
        	    <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:790px; margin-top:-25px;">时间</div>
        	   </div>
        	   <div style="position:absolute;width:980px;margin-left:220px;margin-top:70px;">
        	       <span style="float:left;width:30%;font-size:19px;"><s:property value="currentProjectM"/>_<s:property value="#request.monkeyLastInfo.pacVersion"/></span>
        	       <span style="width:50%;margin-left:20px;font-size:19px;"><s:property value="#request.monkeyLastInfo.formName"/></span>
				   <span style="float:right;width:10%;margin-right:90px;font-size:19px;"><s:property value="#request.monkeyLastInfo.tdate"/></span>
				    <button type="button" class="btn" data-toggle="modal"  data-target="#myModalM" 
				 	style="font-size:12px;font-weight:bold;clear:both;float:right;margin-right:5px;margin-top:-27px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>		
        </div>
             <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalM" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:252px 0px 0 380px;">
				      <div class="modal-content" style="width:750px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>发送邮件：</strong><strong id="subjectM"><s:property value="currentProjectM"/>_<s:property value="#request.monkeyLastInfo.pacVersion"/> Monkey test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>收件人：</strong><a id="toM" href="#"><s:property value="MmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>抄送人：</strong><a id="ccM" href="#"><s:property value="MmailCC"/></a></h5>  
				         </div>
				         <div class="modal-body" id="contentsM">
				            <dl>
				            <dt>Dear all,</dt>
				            <dd style="text-indent:1em;">Monkey daily test results of <mark><strong><s:property value="currentProjectM"/></strong></mark> have come out:</dd>
				            <dd style="text-indent:1em;">Test Date: <mark><strong><s:property value="#request.monkeyLastInfo.tdate"/></strong><mark></dd>
				            <dd style="text-indent:1em;">Test Version: <mark><strong><s:property value="currentProjectM"/>_<s:property value="#request.monkeyLastInfo.pacVersion"/></strong><mark></dd>   
				            <dd style="text-indent:1em;">Test Details:</dd>
				            <dd style="margin-left:15px;"><a class="text-justify" href="http://tjsdelab.spreadtrum.com/project?testFormName=<s:property value="#request.monkeyLastInfo.formName"/>">http://tjsdelab.spreadtrum.com/project?testFormName=<s:property value="#request.monkeyLastInfo.formName"/></a></dd>
				            <dd> </dd>
				            <dd style="text-indent:1em;">Please give this matter your prompt attention.</dd>
				            <dd style="text-indent:1em;">Thanks!</dd>
				         </dl></div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button class="btn btn-primary" type="button" data-toggle="button" id="monkeyMail" onclick="sendMailM()">
				               确认发送
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				   </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
		<br><br><br><br>		     

			<div style="position:absolute;width:980px;margin-left:195px;margin-top:35px;">
				<img style="float:left; margin-left:0px;margin-top:6px; display: inline" height="20" width="20" alt="picture" src="images/aperture.png">        			
				<img style="float:left; margin-left:5px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:25px; margin-top:-25px;">最新报告</div>   
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:250px; margin-top:-25px;">表单名</div>
        	    <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:815px; margin-top:-25px;">时间</div>
        	   </div>
        	<div style="position:absolute;width:980px;margin-left:215px;margin-top:60px;">
        	    <s:iterator value="#request.monkeyMoreInfo" id="MMoreInfo">
        	    <div style="clear:both;margin-top:20px;">
        	       <img style="float:left; margin-left:-5px;margin-top:-3px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">
        	       <span style="float:left;width:30%;font-size:19px;"><s:property value="currentProjectM"/>_<s:property value="#MMoreInfo.pacVersion"/></span>
        	       <span style="width:50%;margin-left:20px;font-size:19px;"><s:property value="#MMoreInfo.formName"/></span>
				   <span style="float:right;width:10%;margin-right:90px;font-size:19px;"><s:property value="#MMoreInfo.tdate"/></span>
				   <button type="button" class="btn disabled" data-toggle="modal"  data-target="#myModalM"
				 	style="font-size:12px;font-weight:bold;clear:both;float:right;margin-right:5px;margin-top:-27px;background-color:#32CD32;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
				</div></s:iterator>
             </div>
	 </div>
				
									    	 
<!-- 侧边栏taps 第二显示sanity-->						    	 					      
   <div class="tab-pane " id="sanity">
	  <div class="selectANDsearch" style="width:980px;margin-left:200px;">
	    	 <ul class="bar1_first" id="bar1_first">
	    	 	<li style="float:right;width:290px;margin-right:30px;">
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
	    		 <li style="float:left;padding-left:20px;padding-top:2px">
	    		 	<s:select id="autoSn" style="width:450px;text-align:center;" list="projectListSn" name="currentProjectSn"></s:select>
	    		 	<s:submit id="auto_submitSn" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">
        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:0px; margin-top:-25px;">最新报告</div>   
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:318px; margin-top:-25px;">表单名</div>
        	    <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:790px; margin-top:-25px;">时间</div>
        	   </div>
        	   <div style="position:absolute;width:980px;margin-left:220px;margin-top:70px;">
        	       <span style="float:left;width:40%;font-size:19px;"><s:property value="currentProjectSn"/>_<s:property value="#request.sanityLastInfo.versionForNum"/></span>
        	       <span style="width:50%;margin-left:10px;font-size:19px;"><s:property value="#request.sanityLastInfo.testFormName"/></span>
				   <span style="float:right;width:10%;margin-right:90px;font-size:19px;"><s:property value="#request.sanityLastInfo.testDate"/></span>
				    <button type="button" class="btn" data-toggle="modal"  data-target="#myModalSn" 
				 	style="font-size:12px;font-weight:bold;clear:both;float:right;margin-right:5px;margin-top:-27px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>		
        </div>
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalSn" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:252px 0px 0 380px;">
				      <div class="modal-content" style="width:750px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>发送邮件：</strong><strong id="subjectSn"><s:property value="currentProjectSn"/>_<s:property value="#request.sanityLastInfo.versionForNum"/> Sanity test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>收件人：</strong><a id="toSn" href="#"><s:property value="SnmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>抄送人：</strong><a id="ccSn" href="#"><s:property value="SnmailCC"/></a></h5>  
				         </div>
				         <div class="modal-body" id="contentsSn">
				            <dl>
				            <dt>Dear all,</dt>
				            <dd style="text-indent:1em;">Sanity daily test results of <mark><strong><s:property value="currentProjectSn"/></strong></mark> have come out:</dd>
				            <dd style="text-indent:1em;">Test Date: <mark><strong><s:property value="#request.sanityLastInfo.testDate"/></strong><mark></dd>
				            <dd style="text-indent:1em;">Test Version: <mark><strong><s:property value="currentProjectSn"/>_<s:property value="#request.sanityLastInfo.versionForNum"/></strong><mark></dd>   
				            <dd style="text-indent:1em;">Test Details:</dd>
				            <dd style="margin-left:15px;"><a class="text-justify" href="http://tjsdelab.spreadtrum.com/sanity?currentFormName=<s:property value="#request.sanityLastInfo.testFormName"/>">http://tjsdelab.spreadtrum.com/sanity?currentFormName=<s:property value="#request.sanityLastInfo.testFormName"/></a></dd>
				            <dd> </dd>
				            <dd style="text-indent:1em;">Please give this matter your prompt attention.</dd>
				            <dd style="text-indent:1em;">Thanks!</dd>
				         </dl></div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button class="btn btn-primary" type="button" data-toggle="button" id="sanityMail" onclick="sendMailSn()">
				               确认发送
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				   </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
		<br><br><br><br>
			            
   </div>
	  
<!-- 侧边栏taps 第三显示smoke--> 
   <div class="tab-pane " id="smoke">
	   <div class="selectANDsearch" style="width:980px;margin-left:200px;">
	    	 <ul class="bar1_first" id="bar1_first">
	    	 	<li style="float:right;width:290px;margin-right:30px;">
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
	    		 <li style="float:left;padding-left:20px;padding-top:2px">
	    		 	<s:select id="autoSm" style="width:450px;text-align:center;" list="projectListSm" name="currentProjectSm"></s:select>
	    		 	<s:submit id="auto_submitSm" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">
        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:0px; margin-top:-25px;">最新报告</div>   
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:318px; margin-top:-25px;">表单名</div>
        	    <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:790px; margin-top:-25px;">时间</div>
        	   </div>
        	   <div style="position:absolute;width:980px;margin-left:220px;margin-top:70px;">
        	       <span style="float:left;width:40%;font-size:19px;"><s:property value="currentProjectSm"/>_<s:property value="#request.smokeLastInfo.versionForNum"/></span>
        	       <span style="width:50%;margin-left:10px;font-size:19px;"><s:property value="#request.smokeLastInfo.testFormName"/></span>
				   <span style="float:right;width:10%;margin-right:90px;font-size:19px;"><s:property value="#request.smokeLastInfo.testDate"/></span>
				    <button type="button" class="btn" data-toggle="modal"  data-target="#myModalSm" 
				 	style="font-size:12px;font-weight:bold;clear:both;float:right;margin-right:5px;margin-top:-27px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>		
        </div>
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalSm" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:252px 0px 0 380px;">
				      <div class="modal-content" style="width:750px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>发送邮件：</strong><strong id="subjectSm"><s:property value="currentProjectSm"/>_<s:property value="#request.smokeLastInfo.versionForNum"/> Smoke test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>收件人：</strong><a id="toSm" href="#"><s:property value="SmmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>抄送人：</strong><a id="ccSm" href="#"><s:property value="SmmailCC"/></a></h5>  
				         </div>
				         <div class="modal-body" id="contentsSm">
				            <dl>
				            <dt>Dear all,</dt>
				            <dd style="text-indent:1em;">Smoke daily test results of <mark><strong><s:property value="currentProjectSm"/></strong></mark> have come out:</dd>
				            <dd style="text-indent:1em;">Test Date: <mark><strong><s:property value="#request.smokeLastInfo.testDate"/></strong><mark></dd>
				            <dd style="text-indent:1em;">Test Version: <mark><strong><s:property value="currentProjectSm"/>_<s:property value="#request.somkeLastInfo.versionForNum"/></strong><mark></dd>   
				            <dd style="text-indent:1em;">Test Details:</dd>
				            <dd style="margin-left:15px;"><a class="text-justify" href="http://tjsdelab.spreadtrum.com/smoke?currentFormName=<s:property value="#request.smokeLastInfo.testFormName"/>">http://tjsdelab.spreadtrum.com/smoke?currentFormName=<s:property value="#request.smokeLastInfo.testFormName"/></a></dd>
				            <dd> </dd>
				            <dd style="text-indent:1em;">Please give this matter your prompt attention.</dd>
				            <dd style="text-indent:1em;">Thanks!</dd>
				         </dl></div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button class="btn btn-primary" type="button" data-toggle="button" id="smokeMail" onclick="sendMailSm()">
				               确认发送
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				   </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
		<br><br><br><br>   
			            
   </div>
<!-- 侧边栏taps 第三显示mtbf_ui--> 
   <div class="tab-pane " id="mtbf_ui">
       <div class="selectANDsearch" style="width:980px;margin-left:200px;">
	    	 <ul class="bar1_first" id="bar1_first">
	    	 	<li style="float:right;width:290px;margin-right:30px;">
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
	    		 <li style="float:left;padding-left:20px;padding-top:2px">
	    		 	<s:select id="autoMu" style="width:450px;text-align:center;" list="projectListMu" name="currentProjectMu"></s:select>
	    		 	<s:submit id="auto_submitMu" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">
        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:0px; margin-top:-25px;">最新报告</div>   
        		<div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:318px; margin-top:-25px;">表单名</div>
        	    <div style="float:left;font-weight:bold;font-size:18px;color:#458B74;margin-left:790px; margin-top:-25px;">时间</div>
        	   </div>
        	   <div style="position:absolute;width:980px;margin-left:220px;margin-top:70px;">
        	       <span style="float:left;width:40%;font-size:19px;"><s:property value="currentProjectMu"/>_<s:property value="#request.mtbf_uiLastInfo.softwareVsn"/></span>
        	       <span style="width:50%;margin-left:10px;font-size:19px;"><s:property value="#request.mtbf_uiLastInfo.formName"/></span>
				   <span style="float:right;width:10%;margin-right:90px;font-size:19px;"><s:property value="#request.mtbf_uiLastInfo.testDate"/></span>
				    <button type="button" class="btn" data-toggle="modal"  data-target="#myModalMu" 
				 	style="font-size:12px;font-weight:bold;clear:both;float:right;margin-right:5px;margin-top:-27px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>		
        </div>
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalMu" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:252px 0px 0 380px;">
				      <div class="modal-content" style="width:750px;">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>发送邮件：</strong><strong id="subjectMu"><s:property value="currentProjectMu"/>_<s:property value="#request.mtbf_uiLastInfo.softwareVsn"/> Mtbf_ui test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>收件人：</strong><a id="toMu" href="#"><s:property value="MumailTo"/></a></h5>
				            <h5 class="modal-title"><strong>抄送人：</strong><a id="ccMu" href="#"><s:property value="MumailCC"/></a></h5>  
				         </div>
				         <div class="modal-body" id="contentsMu">
				            <dl>
				            <dt>Dear all,</dt>
				            <dd style="text-indent:1em;">Mtbf_ui daily test results of <mark><strong><s:property value="currentProjectMu"/></strong></mark> have come out:</dd>
				            <dd style="text-indent:1em;">Test Date: <mark><strong><s:property value="#request.mtbf_uiLastInfo.testDate"/></strong><mark></dd>
				            <dd style="text-indent:1em;">Test Version: <mark><strong><s:property value="currentProjectMu"/>_<s:property value="#request.mtbf_uiLastInfo.softwareVsn"/></strong><mark></dd>   
				            <dd style="text-indent:1em;">Test Details:</dd>
				            <dd style="margin-left:15px;"><a class="text-justify" href="http://tjsdelab.spreadtrum.com/mtbf_ui?currentFormName=<s:property value="#request.mtbf_uiLastInfo.formName"/>">http://tjsdelab.spreadtrum.com/mtbf_ui?currentFormName=<s:property value="#request.mtbf_uiLastInfo.formName"/></a></dd>
				            <dd> </dd>
				            <dd style="text-indent:1em;">Please give this matter your prompt attention.</dd>
				            <dd style="text-indent:1em;">Thanks!</dd>
				         </dl></div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button class="btn btn-primary" type="button" data-toggle="button" id="mtbf_uiMail" onclick="sendMailMu()">
				               确认发送
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				   </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
		<br><br><br><br> 
            
   </div>
		  <div class="tab-pane " id="monkeyForRD">

	  
	                 <button style="float:right;height:40px;margin-top:10px;margin-right:498px;font-size:25px;background-color:#00FF00" id="startMonitor">开始监控</button>
	                  <button style="float:right;height:40px;margin-top:10px;margin-right:498px;font-size:25px;background-color:#EE0000" id="stopMonitor">停止监控</button>

	  </div>  
	 
   </div>
 </div>


	
		
		
		
		
		
<!-- 页面taps 次页展示其他管理other-->					
<div class="tab-pane" id="other">
			<h3 class="text-center">
				敬请期待！
			</h3>
</div>


</div>
	
</div>			
			
								
			
		

      
</s:form>
</body>
  
</html>
