<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
	 <% 
	    String tabclicking = (String) request.getAttribute("tabclicked"); 
	 %>

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
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />  
    <link rel="stylesheet" href="css/back-stage.css" type="text/css">
    <link type="text/css" href="css/lrtk.css" rel="stylesheet" />
    <link href="css/uploadify.css" rel="stylesheet" type="text/css"/>
   
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

	<script type="text/javascript" src="jqplot/jquery.js"></script>
	<script type="text/javascript" src="jqplot/lrtk.js"></script>
	<script type="text/javascript" src="jqplot/jquery-ui.js"></script>
	
    
<script>
<!--时间日期设置 -->

var tabclicked='<%=tabclicking%>';
var pSelected;
$(function() {
    if (tabclicked=="tab-monkey"){  
		   $("#tab-left li:eq(0)").addClass('active');
		   $('#monkey').addClass('active');
		   }
	if (tabclicked=="tab-sanity"){  
		   $("#tab-left li:eq(1)").addClass('active');
		   $('#sanity').addClass('active');
		   }
    if (tabclicked=="tab-smoke"){  
		   $("#tab-left li:eq(2)").addClass('active');
		   $('#smoke').addClass('active');
		   }
	if (tabclicked=="tab-mtbf_ui"){  
		   $("#tab-left li:eq(3)").addClass('active');
		   $('#mtbf_ui').addClass('active');
		   }
	if (tabclicked=="tab-mfrd"){  
		   $("#tab-left li:eq(4)").addClass('active');
		   $('#monkeyForRD').addClass('active');
		   }
	
	
		$('#tab-left-nav').click(function (e) {
					tabclicked = $(e.target).attr('id');
					location.href ="back-stageManage?tabclicked=" + tabclicked;
				});			 
         $("#autoM").change(function() {
        	    pSelected = $(this).children('option:selected').val();
	 			location.href = "back-stageManage?tabclicked=" + tabclicked + "&mselected=" + pSelected;        
	         });
         $("#autoSn").change(function() {
        	    pSelected = $(this).children('option:selected').val();
	 			location.href = "back-stageManage?tabclicked=" + tabclicked + "&snselected=" + pSelected;         
	         });
         $("#autoSm").change(function() {
        	    pSelected = $(this).children('option:selected').val();
	 			location.href = "back-stageManage?tabclicked=" + tabclicked + "&smselected=" + pSelected;        
	         });
         $("#autoMu").change(function() {
        	    pSelected = $(this).children('option:selected').val();
	 			location.href = "back-stageManage?tabclicked=" + tabclicked + "&muselected=" + pSelected;          
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
	//发送请求函数post方式
	function sendRequest(mailProp) {
	    createXMLHttpRequest();
	    //XMLHttpReq.onreadystatechange = handleStateChange;
	   // XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.onreadystatechange = processResponseMail;//指定响应函数
	    XMLHttpReq.open("POST","backManageSendMail",true)
	    XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    XMLHttpReq.send(mailProp);  // 发送请求
	}
	
	// 处理返回信息函数
	function processResponseMail() {
	    if (XMLHttpReq.readyState == 4) { // 判断对象状态
	        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	//提示返回信息
	        	//alert("服务器返回信息"+XMLHttpReq.responseText)
	        	alert("邮件发送成功!")
	            var result = XMLHttpReq.responseText;    
	            document.getElementById("data").innerHTML = result;	       
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}
	
	//发送请求函数get方式
	function sendRequestByGET() {
	    createXMLHttpRequest();
	    XMLHttpReq.onreadystatechange = processResponse;//指定响应函数
	    XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.send(null);  // 发送请求
	}
	function processResponse() {
	    if (XMLHttpReq.readyState == 4) { // 判断对象状态
	        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	//提示返回信息
	        	//alert("服务器返回信息"+XMLHttpReq.responseText)
	            var result = XMLHttpReq.responseText;    
	            document.getElementById("data").innerHTML = result;	       
	        } else { //页面不正常
	            window.alert("您所请求的页面有异常。");
	        }
	    }
	}	
	
	//文件上传发送请求函数post方式
	function sendRequestForFile(fileProp) {
	    createXMLHttpRequest();
	    //XMLHttpReq.onreadystatechange = handleStateChange;
	   // XMLHttpReq.open("GET", url, true);
	    XMLHttpReq.onreadystatechange = processResponseFile;//指定响应函数
	    XMLHttpReq.open("POST","upload",true)
	    XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	    XMLHttpReq.send(fileProp);  // 发送请求
	}
	function processResponseFile() {
	    if (XMLHttpReq.readyState == 4) { // 判断对象状态
	        if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息
	        	//提示返回信息
	        	//alert("服务器返回信息"+XMLHttpReq.responseText)
	        	alert("属性成功保存!")
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
		var mailProp = "contents=" +encodeURIComponent(contents) + "&To=" + encodeURIComponent(To) +"&CC=" + encodeURIComponent(CC) +"&subject=" +encodeURIComponent(subject);  
		//window.alert(mailProp)
	    sendRequest(mailProp);
	    $("#myModalM").hide();
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
		var mailProp = "contents=" +encodeURIComponent(contents) + "&To=" + encodeURIComponent(To) +"&CC=" + encodeURIComponent(CC) +"&subject=" +encodeURIComponent(subject);  
	    sendRequest(mailProp);
	    $("#myModalSn").hide();
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
		var mailProp = "contents=" +encodeURIComponent(contents) + "&To=" + encodeURIComponent(To) +"&CC=" + encodeURIComponent(CC) +"&subject=" +encodeURIComponent(subject);
		sendRequest(mailProp);
	    $("#myModalSm").hide();
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
		var mailProp = "contents=" +encodeURIComponent(contents) + "&To=" + encodeURIComponent(To) +"&CC=" + encodeURIComponent(CC) +"&subject=" +encodeURIComponent(subject);  
		sendRequest(mailProp);
	    $("#myModalMu").hide();
		}
	}
// monkeyForRD后台监测	
	$(document).ready(function(){
	    $("#startMonitor").click(function() {
			undo("startMonitor");
			reveal("stopMonitor");
			$("#startMonitor").css("background-color","#919191");
			$("#stopMonitor").css("background-color","#EE0000");
			var url = "rdSendMailCtrl?timerFlag=start";  
			sendRequestByGET(url);
			return false;
	    });
	    $("#stopMonitor").click(function() {
			undo("stopMonitor");
			reveal("startMonitor");
			$("#stopMonitor").css("background-color","#919191");
			$("#startMonitor").css("background-color","#00FF00");
			var url = "rdSendMailCtrl?timerFlag=stop";  
			sendRequestByGET(url);
			return false;
	    });});
	function undo(button_id) {
		document.getElementById(button_id).disabled = true;
		}
	function reveal(button_id) {
		document.getElementById(button_id).disabled = false;
		}	
	
var languageSelect;
var osSelect;
var locationSelect;
function uploadFile(){
		var toolType =$("#toolType").val();
		var introduction = $("#introduction").val();
		var usageMethod = $("#usageMethod").val();
		languageSelect = $("#languageSelect").val();
		osSelect = $("#osSelect").val();
		locationSelect = $("#locationSelect").val();

		//window.alert(toolType+languageSelect+osSelect+locationSelect+introduction+usageMethod);
		 
		var fileProp = "languageSelect=" + encodeURIComponent(languageSelect) + "&osSelect=" + encodeURIComponent(osSelect) +"&locationSelect=" + encodeURIComponent(locationSelect)
			+"&toolType=" +encodeURIComponent(toolType) + "&introduction=" + encodeURIComponent(introduction) +"&usageMethod=" + encodeURIComponent(usageMethod);  
		//$("#form2").submit;
		sendRequestForFile(fileProp);
		
		var value = $("#fileUpload").val();
		if(confirm('开始上传文件?'))
          {
			$("#upSubmit").click();
            return true;
          }
		 
	    //window.alert("上传成功!")    
}

// 邮件可编辑
var originalStyle = 'btn-success';
var afterClickedStyle = 'btn-inverse';
var temp;
function MmailEditF() {
	//alert("For your attentation, the above data modification is temporary, which will not be written to the database!");
    currentState = MmailEditable.isContentEditable;
    newState = !currentState;
    MmailEditable.contentEditable = newState;
    $("#editMailM").removeClass(originalStyle).addClass(afterClickedStyle);
	    temp = originalStyle;
	    originalStyle = afterClickedStyle;
	    afterClickedStyle = temp;
    }
function SnmailEditF() {
	//alert("For your attentation, the above data modification is temporary, which will not be written to the database!");
    currentState = SnmailEditable.isContentEditable;
    newState = !currentState;
    SnmailEditable.contentEditable = newState;
    $("#editMailSn").removeClass(originalStyle).addClass(afterClickedStyle);
	    temp = originalStyle;
	    originalStyle = afterClickedStyle;
	    afterClickedStyle = temp;
    }
function SmmailEditF() {
	//alert("For your attentation, the above data modification is temporary, which will not be written to the database!");
    currentState = SmmailEditable.isContentEditable;
    newState = !currentState;
    SmmailEditable.contentEditable = newState;
    $("#editMailSm").removeClass(originalStyle).addClass(afterClickedStyle);
	    temp = originalStyle;
	    originalStyle = afterClickedStyle;
	    afterClickedStyle = temp;
    }
function MumailEditF() {
	//alert("For your attentation, the above data modification is temporary, which will not be written to the database!");
    currentState = MumailEditable.isContentEditable;
    newState = !currentState;
    MumailEditable.contentEditable = newState;
    $("#editMailMu").removeClass(originalStyle).addClass(afterClickedStyle);
	    temp = originalStyle;
	    originalStyle = afterClickedStyle;
	    afterClickedStyle = temp;
    }


</script>
</head> 
<body>

<div class="page">    
<!-- 导航条taps -->
	<div class="navbar" role="navigation" style="margin-top:5px;background-color:#708069;width:1200px;">	
	   <a class="navbar-brand" style="font-size:22px;font-weight:bold;color:#FF6103">后台管理</a> 
	   <a class="button blue medium" style="float:right;font-size:21px;font-weight:bold;margin-top:5px;margin-right:4px;" href="login.action">返回主页</a> 
		<div class="tabbable">
		  <ul class="nav nav-pills">
			
			<li class="active">
				<a data-toggle="tab" style="font-size:large;font-weight:bold;margin:3px;" href="#report">报告管理</a>
			</li>
			
			<li>
				<a data-toggle="tab" style="font-size:large;font-weight:bold;margin:3px;" href="#fileUpload">文件共享</a>
			</li>
			<li>
				<a data-toggle="tab" style="font-size:large;font-weight:bold;margin:3px;" href="#other">其他管理</a>
			</li>
		   </ul>
		</div></div><br>
<!-- 页面taps 首页展示报告管理report-->	
<div class="tab-content">
		<div class="tab-pane active" id="report">	
		<s:form action="back-stageManage" theme="simple" style="display:inline;">			
	        <div class="tabbable tabs-left" id="tab-left" >
	          <ul class="nav nav-tabs" id="tab-left-nav">  
	            <li>
	            	<a id="tab-monkey"  href="#monkey" data-toggle="tab" >Monkey测试</a>
	            </li>
	            <li>
	                 <a id="tab-sanity" href="#sanity" data-toggle="tab">Sanity测试</a>
	            </li>
	            <li>
	                 <a id="tab-smoke" href="#smoke" data-toggle="tab">Smoke测试</a>
	            </li> 
	            <li>
	                 <a id="tab-mtbf_ui"  href="#mtbf_ui" data-toggle="tab">Mtbf_ui测试</a>
	            </li>  
	            <li>
	                 <a id="tab-mfrd" data-toggle="tab" href="#monkeyForRD">monkeyForRD</a>
	            </li> 
	           </ul>
    </div>
<!-- 侧边栏taps 默认显示monkey-->	
      <div class="tab-content">
        <div class="tab-pane" id="monkey">          
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
	    		 	<s:select id="autoM" style="width:450px;text-align:center;" list="projectListM" name="mselected"></s:select>
	    		 	<s:submit id="auto_submitM" value="搜索"  method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">	
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-top:-25px;">最新报告</div>   
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:28%;margin-top:-25px;">表单名</div>
        	    <div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:82%;margin-top:-25px;">时间</div>
        	   <button type="button" class="btn" data-toggle="modal" data-target="#myModalM" style="font-size:12px;font-weight:bold;float:right;margin-right:-14px;margin-top:8px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
        	   </div>
        	   <div style="position:absolute;width:890px;margin-left:220px;margin-top:70px;word-wrap:break-word;word-break:break-all;white-space:normal">
        	       <div style="float:left;width:40%;font-size:18px;"><s:property value="currentProjectM"/>_<s:property value="#request.monkeyLastInfo.pacVersion"/></div>
        	       <div style="float:left;width:50%;font-size:18px;"><s:property value="#request.monkeyLastInfo.formName"/></div>
				   <div style="float:right;width:10%;font-size:18px;"><s:property value="#request.monkeyLastInfo.tdate"/></div>	    		
        </div>
             <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalM" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:80px 150px 0 200px;word-wrap:break-word;word-break:break-all;white-space:normal">
				      <div class="modal-content" style="width:1000px;">
				        <div id="MmailEditable">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>SENDING EMAIL:&nbsp</strong><strong id="subjectM"><s:property value="currentProjectM"/>_<s:property value="#request.monkeyLastInfo.pacVersion"/> Monkey test results! [focus]</strong></h4>
				            </div>
				         <div class="modal-header" >
				            <h5 class="modal-title" id="myModalLabel"><strong>Recipient:&nbsp</strong><a id="toM" href="#"><s:property value="MmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>CC:&nbsp</strong><a id="ccM" href="#"><s:property value="MmailCC"/></a></h5>  
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
				         </div>
				         <div class="modal-footer">
				             <button type="button" class="btn btn-default" data-dismiss="modal">
				               取消
				            </button>
				             <button type="button" class="btn btn-success" style="width:70px;" data-toggle="button" id="editMailM" onclick="MmailEditF()">编&nbsp&nbsp辑</button>			                      
				            <button class="btn btn-primary" type="button" data-toggle="button" id="monkeyMail" onclick="sendMailM()">
				               确认发送
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				   </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
		<br><br><br><br>		     

			<div style="position:absolute;width:980px;margin-left:195px;margin-top:45px;">
				<img style="float:left; margin-left:0px;margin-top:6px; display: inline" height="20" width="20" alt="picture" src="images/aperture.png">        			
				<img style="float:left; margin-left:5px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:2.5%;margin-top:-25px;">更多报告</div>   
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:28.5%;margin-top:-25px;">表单名</div>
        	    <div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:84.6%;margin-top:-25px;">时间</div>
        	   </div>
        	<div style="position:absolute;width:885px;margin-left:215px;margin-top:70px;word-wrap:break-word;word-break:break-all;white-space:normal">
        	    <s:iterator value="#request.monkeyMoreInfo" id="MMoreInfo">
        	    <div style="clear:both;margin-top:25px;">
        	       <img style="float:left; margin-left:-5px;margin-top:-3px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">
        	       <div style="float:left;width:39%;font-size:18px;"><s:property value="currentProjectM"/>_<s:property value="#MMoreInfo.pacVersion"/></div>
        	       <div style="float:left;width:49%;font-size:18px;"><s:property value="#MMoreInfo.formName"/></div>
				   <div style="float:right;width:9%;font-size:18px;"><s:property value="#MMoreInfo.tdate"/></div>  
				</div>
				<button type="button" class="btn disabled" data-toggle="modal" data-target="#myModalM" style="font-size:12px;font-weight:bold;float:right;margin-right:-165px;margin-bottom:5px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
				</s:iterator>
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
	    		 	<s:select id="autoSn" style="width:450px;text-align:center;" list="projectListSn" name="snselected"></s:select>
	    		 	<s:submit id="auto_submitSn" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
		<div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-top:-25px;">最新报告</div>   
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:28%;margin-top:-25px;">表单名</div>
        	    <div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:82%;margin-top:-25px;">时间</div>
        	   <button type="button" class="btn" data-toggle="modal" data-target="#myModalSn" style="font-size:12px;font-weight:bold;float:right;margin-right:-14px;margin-top:8px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
        	   </div>
        	   <div style="position:absolute;width:890px;margin-left:220px;margin-top:70px;word-wrap:break-word;word-break:break-all;white-space:normal">
        	       <div style="float:left;width:40%;font-size:18px;"><s:property value="currentProjectSn"/>_<s:property value="#request.sanityLastInfo.versionForNum"/></div>
        	       <div style="float:left;width:50%;font-size:18px;"><s:property value="#request.sanityLastInfo.testFormName"/></div>
				   <div style="float:right;width:10%;font-size:18px;"><s:property value="#request.sanityLastInfo.testDate"/></div>	    		
        </div>
		
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalSn" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:80px 150px 0 200px;word-wrap:break-word;word-break:break-all;white-space:normal">
				      <div class="modal-content" style="width:1000px;">
				         <div id="SnmailEditable">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>SENDING EMAIL:&nbsp</strong><strong id="subjectSn"><s:property value="currentProjectSn"/>_<s:property value="#request.sanityLastInfo.versionForNum"/> Sanity test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>Recipient:&nbsp</strong><a id="toSn" href="#"><s:property value="SnmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>CC:&nbsp</strong><a id="ccSn" href="#"><s:property value="SnmailCC"/></a></h5>  
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
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button type="button" class="btn btn-success" style="width:70px;" data-toggle="button" id="editMailSn" onclick="SnmailEditF()">编&nbsp&nbsp辑</button>
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
	    		 	<s:select id="autoSm" style="width:450px;text-align:center;" list="projectListSm" name="smselected"></s:select>
	    		 	<s:submit id="auto_submitSm" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
	    <div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-top:-25px;">最新报告</div>   
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:28%;margin-top:-25px;">表单名</div>
        	    <div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:82%;margin-top:-25px;">时间</div>
        	   <button type="button" class="btn" data-toggle="modal" data-target="#myModalSm" style="font-size:12px;font-weight:bold;float:right;margin-right:-14px;margin-top:8px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
        	   </div>
        	   <div style="position:absolute;width:890px;margin-left:220px;margin-top:70px;word-wrap:break-word;word-break:break-all;white-space:normal">
        	       <div style="float:left;width:40%;font-size:18px;"><s:property value="currentProjectSm"/>_<s:property value="#request.smokeLastInfo.versionForNum"/></div>
        	       <div style="float:left;width:50%;font-size:18px;"><s:property value="#request.smokeLastInfo.testFormName"/></div>
				   <div style="float:right;width:10%;font-size:18px;"><s:property value="#request.smokeLastInfo.testDate"/></div>	    		
        </div>
		
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalSm" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:80px 150px 0 200px;word-wrap:break-word;word-break:break-all;white-space:normal">
				      <div class="modal-content" style="width:1000px;">
				        <div id="SmmailEditable">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>SENDING EMAIL:&nbsp</strong><strong id="subjectSm"><s:property value="currentProjectSm"/>_<s:property value="#request.smokeLastInfo.versionForNum"/> Smoke test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>Recipient:&nbsp</strong><a id="toSm" href="#"><s:property value="SmmailTo"/></a></h5>
				            <h5 class="modal-title"><strong>CC:&nbsp</strong><a id="ccSm" href="#"><s:property value="SmmailCC"/></a></h5>  
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
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button type="button" class="btn btn-success" style="width:70px;" data-toggle="button" id="editMailSm" onclick="SmmailEditF()">编&nbsp&nbsp辑</button>
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
	    		 	<s:select id="autoMu" style="width:450px;text-align:center;" list="projectListMu" name="muselected"></s:select>
	    		 	<s:submit id="auto_submitMu" value="搜索" method="execute" style="display:none"></s:submit>			
	    		 </li>
	    	  </ul>
	    	  </div><br><br>
	    
	    <div style="position:absolute;margin-top:30px;width:980px;margin-left:190px;">        		
				<img style="float:left; margin-left:0px;margin-top:4px; display: inline" height="30" width="30" alt="picture" src="images/mtbfc.gif">	
				<img style="float:left; margin-left:0px; margin-top:23px; display: inline" height="6" width="890" alt="line" src="images/caolv.png">	
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-top:-25px;">最新报告</div>   
        		<div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:28%;margin-top:-25px;">表单名</div>
        	    <div style="float:left; font-weight:bold;font-size:18px;color:#458B74;margin-left:82%;margin-top:-25px;">时间</div>
        	   <button type="button" class="btn" data-toggle="modal" data-target="#myModalMu" style="font-size:12px;font-weight:bold;float:right;margin-right:-14px;margin-top:8px;background-color:#2E8B57;color:#FAFFF0;padding:5px 10px;">发送邮件</button>
        	   </div>
        	   <div style="position:absolute;width:890px;margin-left:220px;margin-top:70px;word-wrap:break-word;word-break:break-all;white-space:normal">
        	       <div style="float:left;width:40%;font-size:18px;"><s:property value="currentProjectMu"/>_<s:property value="#request.mtbf_uiLastInfo.softwareVsn"/></div>
        	       <div style="float:left;width:50%;font-size:18px;"><s:property value="#request.mtbf_uiLastInfo.formName"/></div>
				   <div style="float:right;width:10%;font-size:18px;"><s:property value="#request.mtbf_uiLastInfo.testDate"/></div>	    		
        </div>
        
		    <!-- 模态框（Modal） -->
				<div class="modal fade in" id="myModalMu" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog" style="position:absolute;margin:80px 150px 0 200px;word-wrap:break-word;word-break:break-all;white-space:normal">
				      <div class="modal-content" style="width:1000px;">
				         <div id="MumailEditable">
				         <div class="modal-header">
				            <button type="button" class="close" data-dismiss="modal" 
				               aria-hidden="true">×
				            </button>
				            <h4 class="modal-title"><strong>SENDING EMAIL:&nbsp</strong><strong id="subjectMu"><s:property value="currentProjectMu"/>_<s:property value="#request.mtbf_uiLastInfo.softwareVsn"/> Mtbf_ui test results! [focus]</strong></h4>
				            </div>
				            <div class="modal-header">
				            <h5 class="modal-title" id="myModalLabel"><strong>Recipient:&nbsp</strong><a id="toMu" href="#"><s:property value="MumailTo"/></a></h5>
				            <h5 class="modal-title"><strong>CC:&nbsp</strong><a id="ccMu" href="#"><s:property value="MumailCC"/></a></h5>  
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
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">
				               取消
				            </button>
				            <button type="button" class="btn btn-success" style="width:70px;" data-toggle="button" id="editMailMu" onclick="MumailEditF()">编&nbsp&nbsp辑</button>
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
    </s:form>
 </div>



<!-- 页面taps 次页展示文件共享fileUpload-->					
<div class="tab-pane" id="fileUpload">
<s:form id="form2" name="form2" action="upload" theme="simple" enctype="multipart/form-data" method="post" >
	<div class="container">
	<div class="row" style="margin-left:18px;">
		<div class="span12">
			<div class="media">
				 <a href="#" class="pull-left"><img height="100" width="100" src="images/share.png" class="media-object" alt='' /></a>
				<div class="media-body" style="border:2px #00C78C solid;width:990px;">
					 <div class="box" style="float:left;margin-top:20px;margin-left:20px;width:500px;height:260px;">
					     <span style="float:left;margin-top:1px;font-size:20px;color:#FF4500;text-decoration:blink;"><strong>上传文件:</strong></span>
					    
 					     <input type="file" id="fileUpload" name="file" class="textbox"/>  
<%-- 				         <s:file name="file" cssStyle="width:250px;margin-left:56px;margin-top:4px;"/> --%>
                          <s:submit id="upSubmit" name="fileUpload" method="fileUpload" value="开始上传" style="float:left;margin-top:32px;width:90px;margin-left:-43px;display:none;"/>
                          
                          
                            <div style="float:left;margin-top:20px;margin-left:3px;width:500px;">
					  		<label style="font-size:18px;color:#FF4500;">工具类型：</label>
							<input type="text" id="toolType" style="margin-left:5px;font-size:14px;float：right;width:250px;" placeholder="请输入…"/>			
							</div>
                            
                            <div style="float:left;margin-top:20px;margin-left:3px;width:500px;">
					  		<label style="font-size:18px;color:#FF4500;">软件语言：</label>
							<s:select style="margin-left:5px;font-size:14px;float：right" id="languageSelect" list="#{'C/C++':'C/C++','Java':'Java','Objective-C':'Objective-C','C#':'C#','Python':'Python','JavaScript':'JavaScript','Php':'Php','Others':'Others'}" > 
	                          </s:select>			
							</div>
							
							<div style="float:left;margin-top:20px;margin-left:3px;width:500px;">
	                          <label style="font-size:18px;color:#FF4500;">适用系统：</label>
							<s:select style="margin-left:5px;font-size:14px;float：right" id="osSelect" list="#{'WindowsXP':'WindowsXP','Windows7/8':'Windows7/8','Linux':'Linux','MacOS':'MacOS','Android':'Android','IOS':'IOS','Windows Mobile':'Windows Mobile','FirefoxOS':'FirefoxOS','Others':'Others'}" > 
	                          </s:select>			
							</div>
							
							<div style="float:left;margin-top:20px;margin-left:3px;width:500px;">
	                          <label style="font-size:18px;color:#FF4500;">公司位置：</label>
							<s:select style="margin-left:5px;font-size:14px;float：right" id="locationSelect" list="#{'天津':'天津','北京':'北京','上海':'上海','深圳':'深圳','成都':'成都','圣迭哥':'圣迭哥','韩国':'韩国','印度':'印度','台北':'台北','其他':'其他'}" > 
	                          </s:select>			
							</div>						
                     </div>
                     
                     <div style="float:right;width:450px;">
                      
							<fieldset style="margin-top:24px;">
							<legend style="font-size:20px;color:#FF4500;"><strong>工具简介:</strong></legend>
							<textarea rows="3" id="introduction" type="text" value="请输入工具简介…" placeholder="请输入工具简介…" style="width:420px;height:75px;margin-top:-10px;"></textarea>				
							</fieldset>
							
						    <fieldset style="margin-top:10px;">
							<legend style="font-size:20px;color:#FF4500;"><strong>使用方法:</strong></legend>
							<textarea rows="3" id="usageMethod" type="text" placeholder="请输入详细使用方法…" style="width:420px;height:100px;margin-top:-10px;"></textarea>				
							</fieldset>
	                </div>	
	               
				</div>
				<a type=“button” class="btn" style="float:right;font-size:15px;font-weight:bold;margin-top:2px;margin-right:30px;background-color:#2E8B57;color:#FAFFF0;padding:5px 15px;" href="fileSharing.jsp">查看分享</a>
				<button type="button" class="btn" data-toggle="button" onclick="uploadFile()" style="font-size:15px;font-weight:bold;float:right;margin-right:35px;margin-top:2px;background-color:#1E90FF;color:#FAFFF0;padding:5px 15px;">确认提交</button>  
			</div>
		</div>
	</div>
</div>     
	    


</s:form>


</div>
	
			
		
<!-- 页面taps 次页展示其他管理other-->					
<div class="tab-pane" id="other">
			<h3 class="text-center">
				敬请期待！
			</h3>
</div>


</div>
	
</div>			
			
								
			
		

      

</body>
  
</html>
