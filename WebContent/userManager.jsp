<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <title>管理员界面</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
   <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="css/userManage.css" type="text/css">
    <link rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
     
    <script type="text/javascript" src="jqplot/highcharts.js"></script> 
    <script type="text/javascript" src="jqplot/exporting.js"></script>
    
<script>
Highcharts.setOptions({
    credits: { enabled: false },
    exporting: { enabled:false},
    });
$(function () {
    $('#container1').highcharts({
        chart: {
            type: 'column',
            backgroundColor: null,
            spacingTop:25,    //图表底部和内容的距离空间
            spacingLeft:50,
            style: { 
                fontFamily: "Microsoft YaHei" 
                } 
        },
        colors: ["#1874CD"], 
        title: {
            text: ''
               },
        xAxis: {
            categories: ['南美', '东南亚', '大陆','非洲','欧洲','其它'],
            gridLineWidth: 1, 
            gridLineColor:'#FFDAB9',
            labels: { 
                //rotation: -30,//倾斜度 
                style: {
                	font: '12px Verdana, sans-serif',
                    } 
                 }
            
               },
        yAxis: {
        	min: 0,
            max:100,
            title: {
                text: ''
            },
            gridLineWidth: 1, 
            gridLineColor:'#FFDAB9',
            minorTickInterval: 'auto', 		
			labels: { 
				enabled: false,
				/*style: { 
					fontSize: '12px' 
					} */
				 }
            /*stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }*/
        },
        legend: {
        	enabled: true,
        	//layout:'vertical',         //竖直显示，默认是水平显示的
            align: 'right',            //图例说明在区域的右边，默认在中间
            x: 0,
            //verticalAlign: 'middle',    //竖直方向居中，默认在底部
            verticalAlign: 'top',
            y: -25,
        	floating:true,
        	draggable: true,
        	reversed:true,
        	itemWidth:100,  //每个图例项目的宽度
        	padding:5,   //图例盒子的内边距
        	//itemMarginTop:-1,
        	symbolPadding:3,  //图例内标志和文本的距离
        	//symbolWidth:10,  //图例内标志的宽度
        	//width:260,  //图例的宽度
            itemStyle: { 
				fontWeight: 'normal', 
				fontSize: '13px'
                        },
            floating: true,
            backgroundColor:'#E0FFFF' ,
            borderColor: '#AAAAAA',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            borderWidth: 0,
            backgroundColor: 'rgba(219,219,216,0.8)',
            shadow: false,
            formatter: function() {
                return '<b>' +this.x+':'+ this.point.stackTotal+'%';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                borderColor: "",                   //去边框
                pointPadding: 0.1,            //柱子之间的间隔(会影响到柱子的大小)
                pointWidth: 20, 
                dataLabels: {
                    enabled: true,             
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'black'
                },
             
            },
            candlestick: {
                enabled: false,
                } 
        }, 
        series:[{
            name: '区域统计',
            data: [21.8,35.1,12.3,27.5,2,1.3]
        }]
});
});

$(function () {
    $('#container').highcharts({
        chart: {
            type: 'column',
            backgroundColor: null,
            spacingTop:25,    //图表底部和内容的距离空间
            spacingLeft:50,
            style: { 
                fontFamily: "Microsoft YaHei" 
                } 
        },
        colors: ["#6699FF"], 
        title: {
            text: ''
               },
        xAxis: {
            categories: ['Facebook', 'Flicker', '人人','开心','微博','其它'],
            gridLineWidth: 1, 
            gridLineColor:'#FFDAB9',
            labels: { 
                //rotation: -30,//倾斜度 
                style: {
                	font: '12px Verdana, sans-serif',
                    } 
                 }
            
               },
        yAxis: {
        	min: 0,
            max:100,
            title: {
                text: ''
            },
            gridLineWidth: 1, 
            gridLineColor:'#FFDAB9',
            minorTickInterval: 'auto', 		
			labels: { 
				enabled: false,
				/*style: { 
					fontSize: '12px' 
					} */
				 }
            /*stackLabels: {
                enabled: true,
                style: {
                    fontWeight: 'bold',
                    color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
                }
            }*/
        },
        legend: {
        	enabled: true,
        	//layout:'vertical',         //竖直显示，默认是水平显示的
            align: 'right',            //图例说明在区域的右边，默认在中间
            x: 0,
            //verticalAlign: 'middle',    //竖直方向居中，默认在底部
            verticalAlign: 'top',
            y: -25,
        	floating:true,
        	draggable: true,
        	reversed:true,
        	itemWidth:100,  //每个图例项目的宽度
        	padding:5,   //图例盒子的内边距
        	//itemMarginTop:-1,
        	symbolPadding:3,  //图例内标志和文本的距离
        	//symbolWidth:10,  //图例内标志的宽度
        	//width:260,  //图例的宽度
            itemStyle: { 
				fontWeight: 'normal', 
				fontSize: '13px'
                        },
            floating: true,
            backgroundColor:'#E0FFFF' ,
            borderColor: '#AAAAAA',
            borderWidth: 1,
            shadow: false
        },
        tooltip: {
            borderWidth: 0,
            backgroundColor: 'rgba(219,219,216,0.8)',
            shadow: false,
            formatter: function() {
                return '<b>' +this.x+':'+ this.point.stackTotal+'%';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                borderColor: "",                   //去边框
                pointPadding: 0.1,            //柱子之间的间隔(会影响到柱子的大小)
                pointWidth: 20, 
                dataLabels: {
                    enabled: true,             
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'black'
                },
             
            },
            candlestick: {
                enabled: false,
                } 
        }, 
        series:[{
            name: '类型统计',
            data: [53,14.3,9.5,5.1,12,6.1]
        }]
});
});
//taps切换         
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,taps_conbox,shijian) {
    	  $(taps_conbox).find("li").hide();	
        $(tabtit).find("li:first").addClass("thistap").show(); 
        $(taps_conbox).find("li:first").show();

        $(tabtit).find("li").bind(shijian,function(){
          $(this).addClass("thistap").siblings("li").removeClass("thistap"); 
            var activeindex = $(tabtit).find("li").index(this);
            $(taps_conbox).children().eq(activeindex).show().siblings().hide();
            return false;
        });
    
    };
    /*调用方法如下：*/
    $.jqtab("#taps","#taps_conbox","click");
});
//进度条

//date
$(function() {
$( "#datepicker1" ).datepicker({
  dateFormat:'yy-mm-dd',
  changeMonth: true,
  changeYear: true,
  showWeek: true,
  firstDay: 1,
  
});
});
$(function() {
	$( "#datepicker2" ).datepicker({
	  dateFormat:'yy-mm-dd',
	  changeMonth: true,
	  changeYear: true,
	  showWeek: true,
	  firstDay: 1
	});
	});
$(function() {
	$( "#datepicker3" ).datepicker({
	  dateFormat:'yy-mm-dd',
	  changeMonth: true,
	  changeYear: true,
	  showWeek: true,
	  firstDay: 1
	});
	});
$(function() {
	$( "#datepicker4" ).datepicker({
	  dateFormat:'yy-mm-dd',
	  changeMonth: true,
	  changeYear: true,
	  showWeek: true,
	  firstDay: 1
	});
	});
//进度条
var bar1;
var bar2;
$(document).ready(function(){
    $("#slidebar1").hide(); 
    $("#slidebar2").hide();
    $("#backup").click(function() {
		bar1 = window.setInterval("gob()",50);
		$("#slidebar1").show(); 
		undo("recovery");
		return false;
    })
    $("#recovery").click(function() {
		bar2 = window.setInterval("gor()",50);
		$("#slidebar2").show();
		undo("backup")
		return false;
	});});
function undo(button_id) {
	document.getElementById(button_id).disabled = true;
	}
function reveal(button_id) {
	document.getElementById(button_id).disabled = false;
	}	    
function widthget(wid){ 
	 return document.getElementById(wid); 
	} 
function gob(){ 
	widthget("bar1").style.width = parseInt(widthget("bar1").style.width) + 1 + "%"; 
	widthget("bar1").innerHTML = widthget("bar1").style.width; 
	if(widthget("bar1").style.width == "100%"){ 
	window.clearInterval(bar1); 
	alert("备份完成");
	widthget("bar1").style.width = "0%"
	$("#slidebar1").hide(); 
	reveal("recovery");
	} } 
function gor(){ 
		widthget("bar2").style.width = parseInt(widthget("bar2").style.width) + 1 + "%"; 
		widthget("bar2").innerHTML = widthget("bar2").style.width; 
		if(widthget("bar2").style.width == "100%"){ 
		window.clearInterval(bar2); 
		alert("已恢复");
		widthget("bar2").style.width = "0%"
		$("#slidebar2").hide();
		reveal("backup");
		} }
  //var bar = window.setInterval("go()",50);
  $(document).ready(function(){
    $("#addUser").click(function(){
    	location.href ="addUser.jsp";
    	return false;
    });
    });

</script>
</head>
<body>
<s:form action="manager.action" theme="simple"> 
	<div class="page"> 
    	<div class="tapsbox">      
            <ul class="taps" id="taps">
               <li>用户管理</li>
               <li>区域统计</li>
               <li>类型统计</li>
               <li>备份恢复</li>
               <li>应用设置</li>
            </ul>
            <ul class="taps_conbox" id="taps_conbox">
              <li class="taps_con" >
                 <table width="60%" style="float:left;margin-top:50px;margin-left:14%;">
                    <tr>
                        <th  style="width:10%；">编号</th>
                        <th  style="width:20%">用户名</th>
                        <th  style="width:20%">密码</th>
                        <th  style="width:20%">账户类型</th>
                        <th  style="width:30%">操作</th>
                    </tr>
                    <tr>
                           <td class="td">1</td>
                           <td class="td">admin</td>
                           <td class="td">admin</td>
                           <td class="td">admin</td>
                           <td class="td" ><font color="red">不 可 修 改</font></td>
                    </tr>
                    <s:iterator value="#request.allUser" id="user">
                    <tr>
                           <td class="td"><s:property value="#user.id"/></td>
                           <td class="td"><s:property value="#user.userName"/></td>
                           <td class="td"><s:property value="#user.passWord"/></td>
                           <td class="td"><s:property value="#user.type"/></td>
                           <td class="td" style="cursor:pointer">
                           <a class="link" href="userDelete?id=<s:property value="#user.id"/>" style="float:left;margin-left:10%;" >【删 除】</a>
                           <a class="link" href="editUser.jsp?id=<s:property value="#user.id"/>&userName=<s:property value="#user.userName"/>&passWord=<s:property value="#user.passWord"/>&type=<s:property value="#user.type"/>" style="float:right;margin-right:10%;" >【编 辑】</a>
                           </td>
                    </tr>
                    </s:iterator>
                   </table> 
                   <div class="add" id="add"><input style="background-color:#00CC66;height:40px;font-size:18px;font-color:#080808" type="submit" id="addUser" value="新增用户"> </div>                              
               </li>
                          
               <li class="taps_con">
                <div class="figure_project"  id="container1" ></div>  
               </li>
               
               <li class="taps_con">
                 <div class="figure_project"  id="container" ></div>  	
               </li>
               
               <li class="taps_con">  
	                <div class="bar_tabbox" id="bar_tabbox">
           				<ul class="graybtn" style="float:left"></ul>			
           		    </div>           		   
	                 <p style="font-size:16px;float:left;margin-left:5%;margin-top:85px;background-color:#CAE1FF;">起始日期:&nbsp<input style="width:80px;margin-right:30px" type="text" id="datepicker1" value="2014-11-01">
	                 截止日期:&nbsp<input style="width:80px" type="text" id="datepicker2" value="2014-11-30">
	                  </p>
	                 <button style="float:right;height:40px;margin-top:10px;margin-right:498px;font-size:25px;background-color:#f78d1d" id="backup">&nbsp&nbsp&nbsp备&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp份&nbsp&nbsp&nbsp</button>
						<div class="graph" id="slidebar1"> 
						<strong class="bar1" id="bar1" style="width:1%;"></strong> 
						</div>
                   <div class="bar_tabbox" style="margin-top:105px" id="bar_tabbox">
           	        <ul class="graybtn" style="float:left;"></ul>
           	       </div>           	        
	                  <p style="font-size:16px;float:left;margin-left:5%;margin-top:48px;background-color:#CAE1FF">起始日期:&nbsp<input style="width:80px;margin-right:30px" type="text" id="datepicker3" value="2014-10-01">
	                 截止日期:&nbsp<input style="width:80px" type="text" id="datepicker4" value="2014-10-31">
	                  </p>
	                  <button style="float:right;height:40px;margin-top:10px;margin-right:498px;font-size:25px;background-color:#0095cd" id="recovery">&nbsp&nbsp&nbsp恢&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp复&nbsp&nbsp&nbsp</button>
						<div class="graph" style="margin-top:360px" id="slidebar2"> 
						<strong class="bar2" id="bar2" style="width:1%;"></strong> 
						</div>					          
               </li>
               <li class="taps_con">
                <div class="settings" id="settings"><input style="background-color:#00CC66;height:40px;margin:20px 20px 20px 0px;" type="checkbox" id="settings" > <span style="width:250px;margin-top:10px;margin-bottom:10px;display:block;size:100px"><font  size="6px" color="blue">安 全 传 输&nbsp&nbsp&nbsp&nbsp</font></span></div>
                <div class="settings" id="settings"><input style="background-color:#00CC66;height:40px;margin:20px 20px 20px 0px;" type="checkbox" id="settings" > <span style="width:250px;margin-top:10px;margin-bottom:10px;display:block;size:100px"><font  size="6px" color="blue">自 动 更 新&nbsp&nbsp&nbsp&nbsp</font></span></div>
                <div class="settings" id="settings"><input style="background-color:#00CC66;height:40px;margin:20px 20px 20px 0px;" type="checkbox" id="settings" > <span style="width:250px;margin-top:10px;margin-bottom:10px;display:block;size:100px"><font  size="6px" color="blue">定 时 备 份&nbsp&nbsp&nbsp&nbsp</font></span></div>
               </li>
            </ul>
             
              
        </div>        
	</div>
</s:form>
</body>
</html>