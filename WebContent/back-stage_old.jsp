<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
  <head>
    <base href="<%=basePath%>">
     
    <title>My JSP 'ajaxIndex.jsp' starting page</title>
    <link rel="shortcut icon" type="image/x-icon" href="images/favicon.ico" media="screen" />
    <link rel="Bookmark" href="images/favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/buttons.css" />
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <link type="text/css" href="css/lrtk.css" rel="stylesheet" />
	<script type="text/javascript" src="jqplot/jquery.js"></script>
	<script type="text/javascript" src="jqplot/lrtk.js"></script>
     
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">    
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <!--
    <link rel="stylesheet" type="text/css" href="styles.css">
    -->
 
<script type="text/javascript">
function monkeytest(){
    var url = "getData.jsp";
    sendRequest(url);
}   
function sendmail(){
    alert("asdasdsasdassdas");
} 

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
</script>
  </head> 
  <body>



<s:form action="login" theme="simple">
        
        <div class="page">
            <div class="sidebar" >
                <a href="#" id="logo" ><img src="images/logo.png" width="120" alt="logo"></a>
                <br><ul>

                    <li>
                        <input type="button" value="monkey测试" onclick="monkeytest()" class="button blue medium" />
                    </li>
                    <li>
                        <a href="sanity.action" class="button blue medium">Sanity_Smoke</a>
                    </li>
                </ul>
                  <div id="form">
                <p>
                    
                </p>
                </div>
            </div>
            
            <div class="right">
                <div class="right_whole_index">
                
                    <div id="data"></div>
                    </div>
                </div>
            </div>
            </s:form>
</body>
  
</html>