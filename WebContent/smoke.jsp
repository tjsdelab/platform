<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.ArrayList"%>
<%
String pass = (String)request.getAttribute("passList");
String fail = (String)request.getAttribute("failList");
String na = (String)request.getAttribute("naList");
String block = (String)request.getAttribute("blockList");
String moduleCompare = "";

String bug_url="";
String common_url="http://bugzilla.spreadtrum.com/bugzilla/buglist.cgi?quicksearch=";
String bugNums=(String)request.getAttribute("bugList");
if(null != bugNums){
    bug_url = common_url+bugNums;
}

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Smoke测试信息</title>
	<link rel="stylesheet" href="css/style.css" type="text/css">
	<link rel="stylesheet" href="css/tab.css" type="text/css">
	<link rel="stylesheet" href="css/smoke.css" type="text/css">
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    
<script type="text/javascript" src="jqplot/jquery.js"></script>
<script type="text/javascript" src="jqplot/jquery-ui.js"></script>

<script>
	type = "text/javascript" >
	//添加鼠标点击背景颜色替换
	$(document).ready(function() {
		$(".orderedcaselist tr").hover(function() {
			// $("#orderedlist li:last").hover(function() {
			$(this).addClass("white");
		}, function() {
			$(this).removeClass("white");
		});
	});
	//taps切换         
	$(document).ready(
			function() {
				jQuery.jqtab = function(tabtit, taps_conbox, shijian) {
					$(taps_conbox).find("li").hide();
					$(tabtit).find("li:first").addClass("thistap").show();
					$(taps_conbox).find("li:first").show();

					$(tabtit).find("li").bind(
							shijian,
							function() {
								$(this).addClass("thistap").siblings("li")
										.removeClass("thistap");
								var activeindex = $(tabtit).find("li").index(
										this);
								$(taps_conbox).children().eq(activeindex)
										.show().siblings().hide();
								return false;
							});

				};
				/*调用方法如下：*/
				$.jqtab("#taps", "#taps_conbox", "mouseenter");
			});

	//表格折叠与展开
	/*$(function() {
		$('tr.parent').click(function() { // 获取所谓的父行
			$(this).toggleClass("selected") // 添加/删除高亮
			.siblings('.child_' + this.id).toggle(); // 隐藏/显示所谓的子行
		}).click();
	});*/

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
				$("#searchProject").attr("value", str);//设置默认日期为今天的日期
				$("#searchProject").datepicker({
					dateFormat : 'yy-mm-dd',
					changeMonth : true,
					changeYear : true,
					showWeek : true,
					firstDay : 1
				});
			} else if (value == "pacVersion") {
				$("#searchProject").attr("value", "W14_40_1-02");
				$("#searchProject").unbind();
			} else if (value == "project") {
				$("#searchProject").attr("value", "sp7731gga_uui");
				$("#searchProject").unbind();
			}
		});
	});

    $(document).ready(function() {
    	
        $(".orderedcaselist tr").each(function()
       {
       if( $(this).find("td").eq(1).text().toUpperCase() == "PASS"){
           $(this).find("td").eq(1).css("background-color","#71C671");
       }
       if( $(this).find("td").eq(1).text().toUpperCase() == "FAIL"){
           $(this).find("td").eq(1).css("background-color","#CD5555");
       }
       if( $(this).find("td").eq(1).text().toUpperCase() == "NA"){
           $(this).find("td").eq(1).css("background-color","#666666");
       }
       if( $(this).find("td").eq(1).text().toUpperCase() == "BLOCK"){
           $(this).find("td").eq(1).css("background-color","#1A1A1A");
       }
       });
   });

	$(document).ready(function() {
		$("[name = checkbox]:checkbox").prop("checked", 'checked');
		$(".check1").change(function() {
			$("#caselist1 tr").each(function() {
				var value = $(this).find("td").eq(1).text().toUpperCase();
				if (value.length > 0) {
					if ($("#" + value + "1").attr("checked") == "checked") {
						$(this).show();
					} else {
						$(this).hide();
					}
				}
			});
		});
	});

	$(document).ready(function() {
		$(".check2").change(function() {
			$("#caselist2 tr").each(function() {
				var value = $(this).find("td").eq(1).text().toUpperCase();

				if (value.length > 0) {
					if ($("#" + value + "2").attr("checked") != "checked") {
						$(this).hide();
					} else {
						$(this).show();
					}
				}
			});
		});
	});
</script>
</head>

<body>
	<s:form action="smoke.action" theme="simple">
		<div class="page">
			<div class="sidebar">
				<br> <img src="images/logo.png" alt="logo"> <br> <br>
				<br>
				<ul>
					<!-- 默认显示Smoke信息 -->
					<li><a href="sanity.action" class="button blue medium">Sanity</a>
					<li><a href="login.action" class="button blue medium">返回主页</a>
					</li>
				</ul>
			</div>
			<div id="project_hide">
				<div id="Sanity_Smoke_hide">
					<s:submit id="jump" method="search"></s:submit>
				</div>
			</div>

			<div class="right">
				<div class="right_whole">

<!-- search module & select module -->
                    <br>
					<div class="bar1" id="bar1">
						<ul class="bar1_first" id="bar1_first">
							<li style="float: right; width: 280px; margin-right: -20px;">
								<s:select style="font-size:14px;" id="select"
									list="#{'project':'工程名','date':'时间','pacVersion':'版次'}"
									name="type">
								</s:select> <s:textfield id="searchProject" name="searchProject"
									value="sp7731gga_uui"
									onFocus="if(value==defaultValue){value='';this.style.color='#000'}"
									onBlur="if(!value){value=defaultValue;this.style.color='#999'}"
									style="color:#999999;font-size:14px;width:100px">
								</s:textfield> <s:submit style="font-size:14px;" id="search" value="搜索"
									method="search"></s:submit>
							</li>
							<li style="float:left;padding-left:55px"><s:select
									style="width:450px;text-align:center;" id="auto"
									list="projectList" name="currentProject"></s:select> <s:submit
									id="auto_submit" value="搜索" method="dropDownProject"
									style="display:none"></s:submit></li>
						</ul>
					</div>
<!-- 表标题 -->
        <div style="text-align:center;font-size:17px;margin-top:10px;margin-bottom:-10px;"><strong> <s:property value="currentProject"/> </strong></div>
<!-- table 1 -->
					<div class="summary">
						<!--  <div style="text-align:left;margin-bottom:10px;"><strong>总体数据汇总</strong> </div> -->
						<table class="orderedlist" width="820px">
							<tr>
								<th>Version</th>
								<th>Total</th>
								<th>Pass</th>
								<th>Fail</th>
								<th>NA</th>
								<th>Block</th>
								<th>Pass-ratio</th>
							</tr>
							<tr>
								<td><s:property value="version" /></td>
								<td><s:property value="total" /></td>
								<td><s:property value="pass" /></td>
								<td><a style="text-align:center;color:#0000ff;
                         text-decoration: underline;" target="_blank" href='<%=bug_url%>'><s:property value="fail"/></a></td>
								<td><s:property value="na" /></td>
								<td><s:property value="block" /></td>
								<td><s:property value="pass_ratio" /></td>
							</tr>
							<tr>
								<td style="font-weight: bold; color: #6A5ACD; height: 25px;">Comment</td>
								<td colspan="6"><s:property value="comment" /></td>
							</tr>
						</table>
					</div>
					<br></br>

<!-- 详细测试信息 -->
					<div class="bar_tabbox" id="bar_tabbox">
						<ul class="graybtn" style="float: left"></ul>
						<ul class="bar_tabs" id="bar_tabs">
							<li style="circle"></li>
							<li>详细测试信息</li>
						</ul>
					</div>

       <!-- taps -->
					<div class="tapsbox">

					<ul class="taps" id="taps">
		               <li>自动测试结果</li>
		               <li>手动测试结果</li>
		               <li style="border-right:1px solid #CCCCCC">测试版本信息</li></ul> 

				    <ul class="taps_conbox" id="taps_conbox">
		                <li class="taps_con">
			            <div class="select_checkBox" >
						<div class="chartQuota" id="c" style="cursor:pointer">
						<p id="p1">选择指标</p>
					    </div><br>

									<div class="chartOptionsFlowTrend">
										<input type="checkbox" name="checkbox" value="PASS"
											class="check1" id="PASS1"><span>Pass</span> 
										<input type="checkbox" name="checkbox" value="FAIL" class="check1"
											id="FAIL1"><span>Fail</span> 
									    <input type="checkbox" name="checkbox" value="NA" class="check1" id="NA1"><span>NA</span>
										<input type="checkbox" name="checkbox" value="BLOCK"
											class="check1" id="BLOCK1"><span>Block</span>
									</div>
								</div> 
				<!--case表格统计-->
				                <table  class="orderedcaselist" width="850px" style="float:left;position:relative;
                                    zoom:1;margin-left:50px;margin-top:10px;" id="caselist1">
									<thead>
										<tr style="height: 30px" id="gun">
											<th width="3%">No.</th>
											<th width="4%">结果</th>
											<th width="8%">Feature</th>
											<th width="12%">初始条件</th>
											<th width="25%">步骤</th>
											<th width="19%">预期结果</th>
											<th width="10%">BugID</th>
											<th width="19%">备注</th>
										</tr>
									</thead>
									<tbody>
									<%
                                     moduleCompare ="";
                                 %>
                                <s:iterator value="#request.allCaseList_auto" id="case">
                                   <%  
                                       String bug_auto="";
                                       String url="http://bugzilla.spreadtrum.com/bugzilla/buglist.cgi?quicksearch=";
                                       String bugNum=(String)request.getAttribute("bugID");
                                       if(null != bugNum){
                                           bug_auto = url+bugNum;
                                       }
                                     /*  String str_auto="";                                       
                                       String str2_auto=(String)request.getAttribute("module");
                                       if(moduleCompare.equals(str2_auto)){
                                    	   str_auto = "class=\"child_"+str2_auto.replace(" ", "_")+
                                    			   "\"><td>&nbsp;&nbsp;</td";
                                       } else {
                                    	   str_auto = "class=\"parent\" id=\""+str2_auto.replace(" ", "_")+
                                    			   "\"><td class=\"child\"></td";
                                    	   moduleCompare = str2_auto;
                                       }*/
                                   %>
											<tr>
												<td><s:property value="#case.caseID" /></td>
												<td id="result"><s:property value="#case.results" /></td>
												<td><s:property value="#case.feature" /></td>
												<td><s:property value="#case.initCondition" /></td>
												<td><s:property value="#case.steps" /></td>
												<td><s:property value="#case.expectedResults" /></td>
												<td><a style="text-align:left;color:#0000ff;
                                    text-decoration: underline;" target="_blank" href='<%=bug_auto%>'><s:property value="#case.bugID"/></a></td>
												<td><s:property value="#case.comments" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</li>

							<li class="taps_con">
								<div class="select_checkBox">
									<div class="chartQuota" id="d" style="cursor:pointer">
										<p id="p2">选择指标</p>
									</div>
									<br>

									<div class="chartOptionsFlowTrend">
										<input type="checkbox" name="checkbox" value="PASS"
											class="check2" id="PASS2"><span>Pass</span> <input
											type="checkbox" name="checkbox" value="FAIL" class="check2"
											id="FAIL2"><span>Fail</span> <input type="checkbox"
											name="checkbox" value="NA" class="check2" id="NA2"><span>NA</span>
										<input type="checkbox" name="checkbox" value="BLOCK"
											class="check2" id="BLOCK2"><span>Block</span>
										<!--<a href="javascript:;" title="确定" class="a_0">确定</a>
				<a href="javascript:;" title="取消" class="a_1">取消</a>-->
									</div>
								</div>

								    <table  class="orderedcaselist" width="850px" style="float:left;position:relative;
                                    zoom:1;margin-left:50px;margin-top:10px;" id="caselist2">
									<thead>
										<tr style="height: 30px" id="gun">
											<th width="3%">No.</th>
											<th width="4%">结果</th>
											<th width="8%">Feature</th>
											<th width="12%">初始条件</th>
											<th width="25%">步骤</th>
											<th width="19%">预期结果</th>
											<th width="10%">BugID</th>
											<th width="19%">备注</th>
										</tr>
									</thead>
									<tbody>
                                 <%
                                     moduleCompare ="";
                                 %>
                               <s:iterator value="#request.allCaseList" id="case">
                                   <%
                                       String bug="";
                                       String url="http://bugzilla.spreadtrum.com/bugzilla/buglist.cgi?quicksearch=";
                                       String bugNum=(String)request.getAttribute("bugID");
                                       if(null != bugNum){
                                           bug = url+bugNum;
                                       }
                                       /*String str="";                                       
                                       String str2=(String)request.getAttribute("module");
                                       if(moduleCompare.equals(str2)){
                                    	   str = "class=\"child_"+str2.replace(" ", "_")+
                                    			   "\"><td>&nbsp;&nbsp;</td";
                                       } else {
                                    	   str = "class=\"parent\" id=\""+str2.replace(" ", "_")+
                                    			   "\"><td class=\"child\"></td";
                                    	   moduleCompare = str2;
                                       }  */                                   
                                   %>
                                            <tr>
												<td><s:property value="#case.caseID" /></td>
												<td id="result"><s:property value="#case.results" /></td>
												<td><s:property value="#case.feature" /></td>
												<td><s:property value="#case.initCondition" /></td>
												<td><s:property value="#case.steps" /></td>
												<td><s:property value="#case.expectedResults" /></td>
												<td><a style="text-align:left;color:#0000ff;
                                    text-decoration: underline;" target="_blank" href='<%=bug%>'><s:property value="#case.bugID"/></a></td>
												<td><s:property value="#case.comments" /></td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</li>
							<li class="taps_con">
								<div
									style="font-size: 14px; padding-left: 50px; padding-top: 50px;">
									<span><a><s:property value="currentFormName" /></a></span> <br>
									<strong><span><a style="text-align: left;">测试版本路径:</a></span></strong>
									<span><a
										style="text-align: left; color: #0000ff; text-decoration: underline;"
										target="_blank" href="<s:property value="pac"/>"><s:property
												value="pac" /></a></span> <br> <strong><span><a
											style="text-align: left">Tester:</a></span></strong> <span><a
										style="text-align: left;"><s:property value="tester" /></a></span><br>
									<br>
								</div>
							</li>
						</ul>
						<br>

	<div>
    <div style="float:left;margin-left:80%;">报告下载 </div>                                      
         <div style="float:left;margin-left:10px;">
         <a style="color:#0000ff;font-size: 15px;"target="_blank" href="smoke_download?currentFormName=<s:property value="#request.currentFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>                                                           
    </div> 
							<br>
						</div>

					</div>
				</div>
			</div>
	</s:form>
</body>
</html>