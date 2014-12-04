<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html> 
    <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">    
    <title>登录</title> 
    <link rel="stylesheet" href="css/login2.css" type="text/css"> 
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
    var jumpJsp = "user.jsp";
    function showCustomer(str) {
    	
    	if (str == 'cate2'){
    		jumpJsp = 'userManager';
    	} else {
    		jumpJsp = "user.jsp";
    	}
    }
    $(document).ready(function(){
        $("#login").click(function(){
              // alert($(this).children('option:selected').val());
            if( jumpJsp == "userManager"){
            	if($("#userName").val() == "admin" && $("#password").val() == "admin"){
            		location.href = jumpJsp;
            	} else if ($("#userName").val() == "user" && $("#password").val() == "user"){
            		alert("请选择合适分类！");
            	} else {
            		alert("请输入正确密码！");
            	}
            }
            
            if(jumpJsp == "user.jsp"){
            	if($("#userName").val() == "user" && $("#password").val() == "user"){
            		location.href = jumpJsp;
            	} else if ($("#userName").val() == "admin" && $("#password").val() == "admin"){
            		alert("请选择合适分类！");
            	}else {
            		alert("请输入正确密码！");
            	}
            }
        	 
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
            <h1>登录</h1> 
          </header> 
          <div class="loginForm_content"> 
            <fieldset> 
              <div class="inputWrap"> 
                <input type="text" name="userName" id="userName" placeholder="用户名" autofocus required> 
              </div> 
              <div class="inputWrap"> 
                <input type="password" name="password" id="password" placeholder="密码" required> 
              </div>
              <div class="inputWrapSelect">
              <s:select name="select" id="select" list="#{'cate1':'user','cate2':'admin'}" onchange="showCustomer(this.value)" style="width:340px;text-align:center;font-size:18px;color:#FF1500;">
		      </s:select>
		      </div> 
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