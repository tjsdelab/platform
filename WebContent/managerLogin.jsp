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
    <title>后台管理</title> 
    <link rel="stylesheet" href="css/back-stage.css" type="text/css"> 
    <script type="text/javascript" src="jqplot/jquery.js"></script>

    <!--为了让IE支持HTML5元素，做如下操作：--> 
    <!--[if IE]> 
    <script type="text/javascript"> 
    document.createElement("section"); 
    document.createElement("header"); 
    document.createElement("footer"); 
    </script> 
    <![endif]--> 
    
    <script type="text/javascript">
    $(document).ready(function(){
        $("#login").click(function(){
            location.href ="managerLogin?userName="+$("#userName").val()+
            		"&passWord="+$("#password").val();
        	return false;        
        });
    });
   
    </script> 
    </head> 
    
    <body> 
    <div class="wrap"> 
      <form > 
        <section class="loginForm"> 
          <header> 
            <h1>后台登录</h1> 
          </header> 
          <div class="loginForm_content"> 
            <fieldset> 
              <div class="inputWrap"> 
                <input type="text" name="userName" id="userName" placeholder="用户名" autofocus required> 

              </div> 
                <br>
              <div class="inputWrap"> 
                <input type="password" name="password" id="password" placeholder="密码" required> 
              </div>
                <br>
            </fieldset> 
              <br>
            <fieldset> 
              <input type="submit" style="margin-left:20%;" id="login" value="登录"> 
              <input type="checkbox"  style="margin-left:10%;" > 
              <span>下次自动登录</span> 
            </fieldset> 
          </div> 
        </section> 
      </form> 
    </div> 
    </body> 
    </html> 