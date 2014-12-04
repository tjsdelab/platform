<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
    <html> 
    <head> 
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312">    
    <title>新增用户</title> 
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
    var cate = "user";
    function showCustomer(str) {    	
    	if (str == 'cate2'){
    		cate = 'admin';
    	} else {
    		cate = "user";
    	}
    }
    $(document).ready(function(){
        $("#editUser").click(function(){
        	var userID=<%=request.getParameter("id") %>
        	 location.href ="userEdit?id="+userID+"&userName="+$("#userName").val()+"&passWord="+$("#password").val()+"&type="+cate;	
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
            <h1 style="color:blue;">编辑用户</h1> 
          </header> 
          <div class="loginForm_content"> 
            <fieldset> 
            <div style="float:left;margin-left:3%;">用户名修改: </div>
              <div class="inputWrap" style="float:right;" > 
                <input type="text" name="userName" id="userName" value=<%=request.getParameter("userName") %> autofocus required> 
              </div> 
              <div style="float:left;margin-left:3%;margin-top:4%">密码修改: </div>
              <div class="inputWrap" style="float:right;" > 
                <input type="text" name="password" id="password" value=<%=request.getParameter("passWord") %> required> 
              </div>
                <div style="float:left;margin-left:3%;margin-top:4%">类型修改: </div>
              <div class="inputWrapSelect" style="float:right;"  >  
         
              <s:select name="select" id="select" list="#{'cate1':'user','cate2':'admin'}" onchange="showCustomer(this.value)" style="width:240px;text-align:center;font-size:18px;color:#FF1500;">
		      </s:select>
		      </div> 
            </fieldset> 

            <fieldset> 
              <input type="submit" style="margin-left:70%;" id="editUser" value="确定"> 
            </fieldset> 
          </div> 
        </section> 
      </form> 
    </div> 
    </body> 
    </html> 