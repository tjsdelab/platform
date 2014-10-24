<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.ArrayList" %>
<%
	String pass = (String)request.getAttribute("passList");
String fail = (String)request.getAttribute("failList");
String na = (String)request.getAttribute("naList");
String block = (String)request.getAttribute("blockList");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Sanity and Smoke测试信息</title>
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
     <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
     <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
   
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
.page {
    overflow:hidden;
    border-radius:10px;
}
#form {
	font-size:13px;
	color:#B5B5B5;
	padding:10px 10px 0 10px;
    text-align:center;
    margin-top:310px;
}
.right {
	 border-radius:10px;
    float:left;
    /*width:1000px;*/
    width:935px;
    position: relative;
    height:auto;
    border-color: #dfdfdf;
    border-width: 1px;
    background:#f0ffff;
   -moz-box-shadow: 0 0 5px 3px #d3d3d3;
   -webkit-box-shadow: 0 0 5px 3px #d3d3d3;
    box-shadow: 0 0 5px 3px #d3d3d3;
    
}
.right_whole{
	/* border-radius:8px;*/
    /*background:#99ccff;*/
    
    /*-moz-box-shadow: 0 0 5px 3px #d3d3d3;*/
    /*-webkit-box-shadow: 0 0 5px 3px #d3d3d3;*/
    /*box-shadow: 0 0 5px 3px #d3d3d3;*/
     overflow:hidden;
     float:left;
    /* padding-left:20px;*/
     width:935px;
     min-height:1000px;
     position: relative; 
}
.blue {
        background:#33ddcc;
       /* background:#33ffcc;*/    
}
.white {
        background:#ffffff;
       /* background:#33ffcc;*/    
}
.salmonpink {
        background:#115FB6;
       /* background:#33ffcc;*/    
}

/*summary table css设计*/
table {
    border-collapse: collapse;
    font-size:14px;
    background-color:#FDF2EF;
}
table th{
    font-size: 13px;
	text-align:center;
	padding-top:15px;
	padding-bottom:16px;
	background-color:#FDF2EF;
	color:#000000;
	border-bottom: 2px solid #98bf21;
	border-right: 2px solid #98bf21;
	border-top: 2px solid #98bf21;
	border-left: 2px solid #98bf21;
}
table td {
    text-align:center;
    background-color:#FDF2EF;
    font-size:1em;
	border: 1px solid #98bf21;
	/*padding:2px 6px 2px 6px;*/
	text-align:center;
	padding-top:5px;
	padding-bottom:5px;
}
.parent { background:#FFF38F;cursor:pointer;} 
.odd{ background:#FFFFEE;} 
.selected{ background:#FF6500;color:#fff;}

.summary {
	font-family:"Microsoft YaHei";
	width:880px;
	padding-left:50px;
	border-collapse:collapse;
	text-align:center;
}
.summary td, #summary th 
{
	font-size:1em;
	border-bottom: 1px solid #98bf21;
	border-right: 1px solid #98bf21;
	border-top: 1px solid #98bf21;
	border-left: 1px solid #98bf21;
	padding:2px 6px 2px 6px;
	text-align:center;
	padding-top:14px;
	padding-bottom:14px;
}
.summary td a{
font-family:"Microsoft YaHei";
display:block;
text-decoration:none;
color:#0000ff;
font-size:15px;
text-align:left;
}
.summary th 
{
	font-size:1.1em;
	text-align:center;
	padding-top:5px;
	padding-bottom:6px;
	background-color:#FDF2EF;
	color:#000000;
}

.summary tr.alt td 
{
	color:#000000;
	text-align:center;
	background-color:#FDF2EF;
}


/*题标css设计*/
#bar_tabbox
{ 
	width:925px; 
	overflow:hidden;
	padding-left:10px;	
    position: relative; 
}

.bar_tabs
{
	height: 40px;
	border-left: 5px solid #0000CD;
	width:870px;
	background-color:#87CEFA ;
	margin-left:15px;
}

.bar_tabs li
{
	height:40px;
	line-height:31px;
	float:left;
	margin-bottom: -1px;
	overflow: hidden;
	position: relative;
	padding-left:10px;
	/*padding-right:10px;*/
}
.bar_tabs li a 
{
    display: block;
    margin-top:8px;
    outline: none;
    font-weight: bold;
    font-size: 20px;
    }
.bar_tabs li a:hover 
{
    background: #FFFFF0;
}    
    
.bar_tabs .thistab,.tabs .thistab a:hover
{
    background: #FFFFF0;
    border-bottom: 1px solid #fff;
}

/*taps设计*/
#tapsbox{ 
	width:925px; 
	overflow:hidden;
	border-collapse: collapse;
	}
.taps_conbox{
	width:905px;
	
}
.taps_con{ 
	width:905px;
	padding-left:29px;
	border-collapse:collapse;
	text-align:center;
	margin-top:20px;
}
.taps{
	height: 20px;
	border:none;
	width:925px;
	padding-left:29px
	}
.taps li{
	height:25px;
	line-height:25px;
	float:left;
	border-top:1px solid #87CEFA;
	border-left:1px solid #87CEFA;
	margin-bottom: 0px;
	overflow: hidden;
	position: relative;
	padding-left:10px;
	padding-right:10px;
		}
.taps li a {
	display: block;
    margin-top:4px;
    outline: none;
    font-weight: bold;
    font-size: 15px;
    		}
.taps li a:hover {
	background: #FDF2EF;
	}    
.taps .thistap,.taps .thistap a:hover{
	background: #FDF2EF;
	border-bottom: 1px solid #fff;
	}

/*柱状图设计*/
#histogram{
width:935px; 
overflow:hidden; ;
margin:0 auto;}
}
.histogram_con{ 
	margin-top:0px;
	width:935px;

	}
.histogram_con li
{
	margin-top:0px;
	width:875px;
	padding-left:24px;

}
.figure_project
{
    margin-top:20px;
    height:350px;
    width:875px;
    margin-bottom:-5px;
    
}

 #Sanity_Smoke_hide
{display:none;}
</style>

<script>
//Load the fonts 
Highcharts.createElement('link', { 
	href: 'http://fonts.googleapis.com/css?family=Dosis:400,600', 
			rel: 'stylesheet', 
			type: 'text/css' 
					}, null, document.getElementsByTagName('head')[0]); 
Highcharts.setOptions({
	credits: { enabled: false }
	});

$(function () {
	 var ticks = [];
	    <s:iterator  value="#request.modulesList" id="item">
	       var value = "<s:property value="item" />";
	       ticks.push(value);
	    </s:iterator>
    $('#container').highcharts({
        chart: {
            type: 'column',
            backgroundColor: null,
			style: { 
				fontFamily: "Dosis, sans-serif" 
				} 
        },
        colors: ["#666666","#0066CC","#CC0000","#009933"], 
        title: {
            text: ''
               },
        xAxis: {
           // categories: ['phone', 'filemanager', 'mms', 'sms','flymode','blacklight',
                         //'dialer','gallery','calender','alarm','music','video',
                        // 'camera','browers','map','calculator','recording','radio'
                       //  ],
                categories:ticks,
            
            gridLineWidth: 1, 
            gridLineColor:'#FFDAB9',
			labels: { 
				rotation: -45,//倾斜度 
				style: {
					font: 'bold 13px Microsoft YaHei', 
					
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
        	enabled: false,
            /*itemStyle: { 
				fontWeight: 'bold', 
				fontSize: '13px'
                        },
            align: 'left',
            x: -20,
            verticalAlign: 'top',
            y: -20,
            floating: true,
            backgroundColor:'#FFEFDB' ,
            borderColor: '#99FF99',
            borderWidth: 1,
            shadow: false*/
        },
        tooltip: {
        	borderWidth: 0,
			backgroundColor: 'rgba(219,219,216,0.8)',
			shadow: false,
            formatter: function() {
                return '<b>' +this.x+'-num:'+ this.point.stackTotal+'</b><br/>'
                +this.series.name+'-num:' + this.y+'<br/>'+
                this.series.name+'-ratio:' +': '+ this.percentage.toFixed(2)+'%';
            }
        },
        plotOptions: {
            column: {
                stacking: 'normal',
                borderColor: "",                   //去边框
                pointPadding: 0.1,            //柱子之间的间隔(会影响到柱子的大小)
                pointWidth: 25, 
                dataLabels: {
                    enabled: false,             
                    color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'black'
                },
             
            },
            candlestick: {
            	enabled: false,
				} 
		}, 
        series:[{
            name: 'Block',
            data: [<%=block%>]
        }, {
            name: 'NA',
            data: [<%=na%>]
        }, {
            name: 'Fail',
            data:[<%=fail%>]
        },
        {
            name: 'Pass',
            data:[<%=pass%>]
            
        }
        ]
});
});			

  //添加鼠标点击背景颜色替换
       $(document).ready(function() {
    	    $(".orderedlist tr").hover(function() {
    	        // $("#orderedlist li:last").hover(function() {
    	            $(this).addClass("blue");
    	        }, function() {
    	            $(this).removeClass("blue");
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
   	        $.jqtab("#tapsbox","#taps_conbox","mouseenter");
   	    });
   
    //表格折叠与展开
    $(function(){
		 $('tr.parent').click(function(){   // 获取所谓的父行
		   $(this)
		    .toggleClass("selected")   // 添加/删除高亮
		    .siblings('.child_'+this.id).toggle();  // 隐藏/显示所谓的子行
		 }).click();
               }) 
    
</script>

</head>

<body>
<s:form action="sanity.action" theme="simple">   
     <div class="page">
         <div class="sidebar"><br>
            <img src="images/logo.png" alt="logo">
             <br><br><br><ul>
                <!-- 默认显示Sanity信息 -->
                 <li>
                     <a href="#" class="button blue medium">Sanity</a>
                 </li>
                 <li>
                     <a href="smoke.action" class="button blue medium">Smoke</a>
                 </li>
                 <li>
                     <a href="login.action" class="button blue medium">返回主页</a>
                 </li>
              </ul>
         </div>
           <div id="project_hide">   
              <div id="Sanity_Smoke_hide">
			  <s:submit id="jump" method="Sanity_Smoke_jump"></s:submit>
			  </div></div>  

         <div class="right">
         <div class="right_whole">
         
<!-- search module -->
         <div style="text-align:right;padding:15px;padding-left:600px;width:300px;font-size:10px;">
		      <s:select  style="font-size:14px;" id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" name="type" > 
		      </s:select>
             <s:textfield id="searchProject" name="searchProject" value="sp7731gga_uui" 
                      onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                      onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
                      style="color:#999999;font-size:14px;width:100px">
             </s:textfield>
	         <s:submit style="font-size:14px;" id="search" value="搜索" method="search"></s:submit>
	      </div>
	      
<!-- select module -->
         <div style="text-align:left;padding-left:25px;font-size:18px;margin-top:-20px;">
         <s:select style="width:300px;text-align:center;" id="auto" list="projectList" name="currentProject"></s:select>
         </div><br><br>
<!-- table 1 -->
         <div class="summary" >
                      <!--  <div style="text-align:left;margin-bottom:10px;"><strong>总体数据汇总</strong> </div> -->
                <table width="820px" >
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
                     <td><s:property value="version"/></td>
                     <td><s:property value="total"/></td>
                     <td><s:property value="pass"/></td>
                     <td><s:property value="fail"/></td>
                     <td><s:property value="na"/></td>
                     <td><s:property value="block"/></td>
                     <td><s:property value="pass_ratio"/></td>
                     </tr>
                   </table>           
         </div><br><br>
<!-- 测试种类汇总 -->
        <div id="bar_tabbox">
         <div id="bar">
		   <div class="bar_tabbox">
		     <ul class="bar_tabs" id="bar_tabs">
		       <li><a href="#">测试种类汇总</a></li>
		    </ul>
		   </div>
		  </div>
		  </div>
<!-- 柱状图 -->
         <div class="histogram" >
         <ul class="histogram_con" id="histogram_con">
	         <li class="histogram_con">
	         <div id="container" class="figure_project"></div>
	         </li>
	     </ul>
		 </div>
		 
<!-- 详细测试信息 -->	
         <div id="bar_tabbox">
         <div id="bar"> 
		 <div class="bar_tabbox">
		 <ul class="bar_tabs" id="bar_tabs">
               <li><a href="#">详细测试信息</a></li>
            </ul>
          </div></div></div>
          
<!-- taps -->   
		<div id="tapsbox">
		    <ul class="taps" id="taps">
		       <li><a href="#">自动测试结果</a></li>
		       <li><a href="#">手动测试结果</a></li>
		       <li style="border-right:1px solid #87CEFA"><a href="#">测试版本信息</a></li>     
		    </ul> 
<!--case表格统计-->		 
			<ul class="taps_conbox" id="taps_conbox">
			         <li class="taps_con">
                          <table  class="orderedlist"  width="845px" >
                               <thead> <tr>
                                   <th>No.</th>
                                   <th>Model</th>
                                   <th>Summary</th>
                                   <th>Preconditions</th>
                                   <th>Importance</th>
                                   <th>Actions</th>
                                   <th>Expected Results</th>
                                   <th>Results</th>
                                </tr></thead>
                                <tbody>
                                <tr class="parent" id="row_01">
                                <td>Sanity_001</td><td>system</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td style="background-color:#339933;">pass</td>
                                </tr>
                                <tr class="child_row_01"><td>Sanity_001</td><td>system</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td style="background-color:#339933;">pass</td>
                                </tr>
                                <tr class="child_row_01"><td>Sanity_001</td><td>system</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td style="background-color:#339933;">pass</td>
                                </tr></tbody>
	                    </table>    
			        </li>
			        <li class="taps_con">
                          <table  class="orderedlist"  width="845px" >
                                <tr>
                                   <th>No.</th>
                                   <th>Model</th>
                                   <th>Summary</th>
                                   <th>Preconditions</th>
                                   <th>Importance</th>
                                   <th>Actions</th>
                                   <th>Expected Results</th>
                                   <th>Results</th>
                                </tr>
                                <tr>
                                <td>Sanity_001</td>
                                <td>system</td>
                                <td>log输出</td>
                                <td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td>
                                <td>输出adb log</td>
                                <td>正常输出adb log</td>
                                <td style="background-color:#FF0000;">fail</td>
                                </tr>
	                    </table>    
			        </li>
		         <li class="taps_con">
                    <div style="font-size:14px;padding-left:29px;">
                    <span>SP7731GEA-UUI-W14-43-2-01</span>
                    <br><strong><span><a style="text-align:left;">测试版本路径:</a></span></strong>
                    <span>http://10.5.2.48</span>
                    <br><strong><span>PAC:</span></strong>
                    <span>scx35_sp7731geacuccspecBplus_UUI_dt-userdebug-native.pac.gz</span><br><br>
                </div>              
                </li>
			 </ul>
        </div>        
        


          


    
         

  
</div>
</div>
</div>

</s:form>
</body>
</html>
