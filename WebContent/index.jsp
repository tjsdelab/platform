

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>数据平台首页</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link type="text/css" href="css/lrtk.css" rel="stylesheet" />
	<script type="text/javascript" src="jqplot/jquery.js"></script>
	<script type="text/javascript" src="jqplot/lrtk.js"></script>
</head>
<body>

<s:form action="login" theme="simple">
        
        <div class="page">
            <div class="sidebar" >
                <a href="#" id="logo" ><img src="images/logo.png" width="120" alt="logo"></a>
                <br><ul>
                    <li >
                        <a href="#" class="button blue medium ">提测中心</a>
                    </li>
                    <li>
                        <a href="monkey.action" class="button blue medium">Monkey测试</a>
                    </li>
                    <li>
                        <a href="sanity.action" class="button blue medium">Sanity_Smoke</a>
                    </li>
                    <li>
                        <a href="#" class="button blue medium">性能测试</a>
                    </li>
                    <li>
                        <a href="#" class="button blue medium">静态代码测试</a>
                    </li>
                    <li>
                        <a href="#" class="button blue medium">CTS测试</a>
                    </li>
                </ul>
                  <div id="form">
                <p>
                    
                </p>
                </div>
            </div>
            
            <div class="right">
                <div class="right_whole_index">
                
     
                    <img src="images/banner.png" alt="home" height="150" width="900" >
                    
                   
                    <div>
                        <h3 class=""><span class="span1"></span><span>最新报告</span></h3>
                        <div class="shell">
                            <div id="div1">
                                <s:iterator value="#request.news" id="news">
                               <%
                                     String newsDisplay=(String)request.getAttribute("title");                                         
                                     newsDisplay = newsDisplay.substring(newsDisplay.indexOf("-")+1);
                               %>
                               <%   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");                                 
                                    Date newsTime=(Date)request.getAttribute("mdate");
                                    String TimeStr=sdf.format(newsTime);
                                    TimeStr = TimeStr.substring(TimeStr.indexOf(" ")+1);
                         
                               %>
                               <div class="line">
                                <div style="float:left;margin-left:10px;padding-top:3px;">
                                	<img src="images/line.png" alt="line" height="13" width="13"></div>
                                <div style="float:left;margin-left:10px;">
                                	<s:property value="#news.url"/>:</div>
                                <div style="float:left;margin-left:10px;">
                                <a class="link" href="project?testFormName=<s:property value="#news.title"/>">
                                 <%=newsDisplay %>
                                 </a>
                                 </div>
                                 <div style="float:right;margin-right:10px;">
                                	<%=TimeStr%></div>
                                 <div style="clear:both"></div>
                               </div>
                              </s:iterator>
                                
                            </div>
                        </div>
            
                    </div>
                    <div >
                        <h3><span class="span1"></span><span>最新信息</span></h3>
                        
 <!-- 图片代码开始 -->
								<div class="box">
								    <div class="picbox">
								        <ul class="piclist mainlist">
								            <li><img src="images/1.jpg" width="210" height="105" /></li>
								            <li><img src="images/2.jpg" width="210" height="105" /></li>
								            <li><img src="images/3.jpg" width="210" height="105" /></li>
								            <li><img src="images/4.jpg" width="210" height="105" /></li>
								            <li><img src="images/5.jpg" width="210" height="105" /></li>
								            <li><img src="images/6.jpg" width="210" height="105" /></li>
								            <li><img src="images/7.jpg" width="210" height="105" /></li>
								            <li><img src="images/8.jpg" width="210" height="105" /></li>
								        </ul>
								        <ul class="piclist swaplist"></ul>
								    </div>
								    
								    <div class="og_prev"></div>
								    <div class="og_next"></div>
								  
								</div>
	<!-- 代码结束 -->
                       
                       <div class="position_info" style="margin-top:0;padding:0 0 0 300px">
                            <div style="padding:50px auto;">
                               <p><b style="font-size:16px;margin-top:0;padding:0">monkey每日最新报告上午11时之后可查询</b></p><br>
	                       		<p><b style="margin-top:0;padding:0">实验室位置：</b>天津公司_4F</p>
	                       		<p><b>联系方式:</b>022-84841399-1668/5891668</p>
	                       		<p><b>邮箱：</b>tjsdelab@spreadtrum.com</p>
                       		</div>
                            
                    </div>
                       <!--    <div id="info">
                    <s:iterator value="#request.testInfo" id="info">
                      <div class="shadow"> 
                      <table>
                      <tr>
                               <a target="_blank" href="<s:property value="#info.url"/>"><s:property value="#info.url"/></a>
                               <s:iterator value="#info.testPac" id="PAC">
                               <s:property value="#PAC"/><br>
                               </s:iterator><br>
                           </td>
                       </tr>
                       </table>
                       </div>
                      </s:iterator>
                        </div> -->    
                   
                         <br>
                        </div>
                    </div>
                    <p style="text-align:center;color:#B5B5B5;">
                                ©Copyright 2014&nbsp;&nbsp;&nbsp;展讯通信(天津)有限公司
                            </p>
                </div>
            </div>
            </s:form>
</body>
<script>

var c, _ = Function;
with (o = document.getElementById("div1")) 
{ innerHTML += innerHTML; onmouseover = _("c=1"); onmouseout = _("c=0"); }
(F = _("if(#%18||!c)#++,#%=o.scrollHeight>>1;setTimeout(F,#%18?10:1000);".replace(/#/g, "o.scrollTop")))();
</script>

</html>
