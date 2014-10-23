<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Smoke测试信息</title>
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

 #Smoke_hide
{display:none;}
</style>
</head>

<body>
<s:form action="Smoke" theme="simple">   
     <div class="page">
         <div class="sidebar"><br>
            <img src="images/logo.png" alt="logo">
             <br><br><br><ul>
                 <li>
                     <a href="sanity.action" class="button blue medium">Sanity</a>
                 </li>
                 <li>
                     <a href="#" class="button blue medium">Smoke</a>
                 </li>
                 <li>
                     <a href="login.action" class="button blue medium">返回主页</a>
                 </li>
              </ul>
      <div id="Smoke_hide">
      <s:submit id="jump" method="Smoke_jump"></s:submit>
      </div>
      </div>
         </div>  
         <div class="right">
         <div  class="right_whole">
         <div style="text-align:left;font-size:18px;padding-top:150px;">
         <s:select style="width:400px;text-align:center;" id="auto" list="#{'project1':'sp9630_mainTrunk','project2':'sp9630_cmcc','project3':'sp7731gea'}" name="type"></s:select>
         </div><br><br>
         

</div>
</div>
</s:form>
</body>
</html>
