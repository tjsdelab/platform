<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员界面</title>

<link rel="stylesheet" href="css/style.css" type="text/css">
    <script type="text/javascript" src="jqplot/jquery.js"></script>
 <script type="text/javascript">

$(document).ready(function(){
    $("#addUser").click(function(){
    	location.href ="addUser.jsp";
    	//window.open("addUser.jsp", "用户修改","height=400,width=400,toolbar=no,menubar=no,scrollbars=no,resizable=no,location=no,status=no");
    	return false;
    });
    });

</script>
</head>   
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

</style>

<body>
<s:form action="userManager.action" theme="simple">   
<div class="page">
           
       <div class="table" >
              <h3 style="margin-left:0px;">用户管理</h3>
                <table width="60%" style="margin:auto;">
                    <tr>
                        <td class="td_title" style="width:10%">编号</td>
                        <td class="td_title" style="width:20%">用户名</td>
                        <td class="td_title" style="width:20%">密码</td>
                        <td class="td_title" style="width:20%">账户类型</td>
                        <td class="td_title" style="width:30%">操作</td>
                    </tr>
                    <s:iterator value="#request.allUser" id="user">
                    <tr>
                           <td class="td"><s:property value="#user.id"/></td>
                           <td class="td"><s:property value="#user.userName"/></td>
                           <td class="td"><s:property value="#user.passWord"/></td>
                           <td class="td"><s:property value="#user.type"/></td>
                           <td class="td" style="cursor:pointer">
                           <a class="link" href="userDelete?id=<s:property value="#user.id"/>" style="float:left;margin-left:10%;" >【删除】</a>
                           <a class="link" href="userEdit?id=<s:property value="#user.id"/>" style="float:right;margin-right:10%;" >【编辑】</a>
                           </td>
                    </tr>
                    </s:iterator>
                    
                   </table>         
         </div><br><br>
        </div>
        <input type="submit" id="addUser" value="新增用户">
        </s:form>
</body>
</html>