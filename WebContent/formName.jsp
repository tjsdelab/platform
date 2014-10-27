

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>表单号选择</title>
    <link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    
    <style>
  .blue {
        background:#33ddcc;
       /* background:#33ffcc;*/    
}
 table {
    border-collapse: collapse;
}

table, td{
    border: 1px solid #98bf21;
    text-align:center;
    
}
table th{
border: 1px solid black;
background-color:#A7C942;color:#ffffff;text-align:center;

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
color:#ffffff;
}
.customers tr.alt td 
{
color:#000000;
text-align:center;

background-color:#EAF2D3;
}
.table
{
margin-top:100px;
margin-left:50px;
margin-right:auto;
width:960px;
}
.page
{
margin:0;
}
.td
{
width:150px;height:40px;
}
.td_title
{
background-color:#A7C942;color:#ffffff;text-align:center;
}
#display
{
display:none;
}
</style>
<script>
$(document).ready(function(){
	$(".link").click(function(){
		$("#input").val($(this).attr("href"));
		$("#submit").click();
		//alert($(this).attr("href"));
		return false;
	});
});
</script>
</head>
<body>

<s:form action="project" theme="simple">
<div class="page">
            <div id="display">  
	            <s:textfield id="input" name="testFormName"></s:textfield>
	            <s:submit id="submit" value="详情" method="getInfoByForm"></s:submit>
            </div>
       <div class="table" >
              <h3 style="margin-left:0px;">请在下面选择表单号:</h3>
                <table width="100%">
                    <tr>
                        <td class="td_title" style="width:30%">表单号</td>
                        <td class="td_title" style="width:20%">硬件</td>
                        <td class="td_title" style="width:20%">版次</td>
                        <td class="td_title" style="width:20%">时间</td>
                    </tr>
                <s:iterator value="testFormList" id="form">
                    <tr>
                           <td class="td" style="cursor:pointer">
                           			<a href="<s:property value="#form.formName"/>" class="link">
                           			<s:property value="#form.formName"/></a></td>
                           <td class="td"><s:property value="#form.hardwareInfo"/></td>
                           <td class="td"><s:property value="#form.pacVersion"/></td>
                           <td class="td"><s:property value="#form.tdate"/></td>      
                    </tr>
                </s:iterator>
                   </table>         
         </div><br><br>
        </div>
        </s:form>
        </body>

</html>
