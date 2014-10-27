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
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
    <link rel="stylesheet" href="css/sanity.css" type="text/css">
    
    <script type="text/javascript" src="jqplot/jquery.js"></script>
    <script type="text/javascript" src="jqplot/jquery-ui.js"></script>
     
     <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script> 
     <script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script>
   


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
        colors: ["#666666","#87CEEB","#CD5555","#71C671"], 
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
                //rotation: -30,//倾斜度 
                style: {
                    font: 'bold 12px Microsoft YaHei', 
                    
                    } 
                 }
            
               },
        yAxis: {
            min: 0,
            max:80,
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
           show: true,     
           placement:'outsideGrid'
        },
        seriesColors: ['#000080','#EE2C2C', '#00FF7F','#8B1C62'],
        series:[
                {label:'停止均值'},       
                {label:'停止中值'},
                {label:'首错均值'},
                {label:'首错中值'}
            ],
        legend: {
            enabled: true,
            placement:'outsideGrid',
            itemStyle: { 
                fontWeight: 'normal', 
                fontSize: '13px'
                        },
            align: 'right',
            x: 0,
            verticalAlign: 'top',
            y: 0,
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
                pointWidth: 15, 
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
                    $(this).addClass("white");
                }, function() {
                    $(this).removeClass("whitr");
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
               });   
   //评论文本框自动下拉
    function borderColor(){
        if(self['oText'].style.borderColor=='red'){
        self['oText'].style.borderColor = 'yellow';
        }else{
        self['oText'].style.borderColor = 'red';
        }
        oTime = setTimeout('borderColor()',400);
        }
   
    $(document).ready(function(){
        $("#auto").change(function(){
              // alert($(this).children('option:selected').val());
               $("#auto_submit").click();
                    });
    });
    
    $(document).ready(function(){
        $("#search").bind('click', function(){
        	var value = $("#select").children('option:selected').val();
        	if(value == 'date'){
        	//var dateValid = /^\d{1,2}\-\d{1,2}\-\d{4}$/;
        	   var dateValid = /^\d{4}\-\d{1,2}\-\d{1,2}$/;
        	var date = $("#searchProject").val();
        	if(!dateValid.test(date)){
        		   alert("日期格式不正确,请写成 yyyy-mm-dd 格式");	
        	}
        	return false;
        	}
        });
    });
    
    $(document).ready(function(){
        $("#select").change(function(){
                var value = $(this).children('option:selected').val();
                if(value == "date"){
                    var d=new Date(),str='';
                    str += d.getFullYear()+'-'; //获取当前年份
                    str +=d.getMonth()+1+'-'; //获取当前月份（0——11）
                    str +=d.getDate();
                    $("#searchProject").attr("value",str);//设置默认日期为今天的日期
                    $("#searchProject").datepicker({
                        dateFormat:'yy-mm-dd',
                        changeMonth: true,
                        changeYear: true,
                        showWeek: true,
                        firstDay: 1
                        });
                    }
                    else if(value == "pacVersion"){
                     $("#searchProject").attr("value","W14_40_1-02");
                     $("#searchProject").unbind();
                    }
                    else if(value == "project"){
                     $("#searchProject").attr("value","sp7731gga_uui");
                     $("#searchProject").unbind();
                    }
                    });
    });
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
         
<!-- search module & select module -->
         <div class="bar1" id="bar1">
         <ul class="bar1_first" id="bar1_first">
         <li style="float:right;width:280px;margin-right:-20px;">
              <s:select  style="font-size:14px;" id="select" list="#{'project':'工程名','date':'时间','pacVersion':'版次'}" name="type" > 
              </s:select>
             <s:textfield id="searchProject" name="searchProject" value="sp7731gga_uui" 
                      onFocus="if(value==defaultValue){value='';this.style.color='#000'}" 
                      onBlur="if(!value){value=defaultValue;this.style.color='#999'}" 
                      style="color:#999999;font-size:14px;width:100px">
             </s:textfield>
             <s:submit style="font-size:14px;" id="search" value="搜索" method="search"></s:submit>
          </li>
        <li style="float:center;">
        <s:select style="width:300px;text-align:center;" id="auto" list="projectList" name="currentProject"></s:select>
        <s:submit id="auto_submit" value="搜索" method="dropDownProject" style="display:none"></s:submit>
        </li></ul>        
        </div>
<!-- table 1 -->
         <div class="summary" >
                      <!--  <div style="text-align:left;margin-bottom:10px;"><strong>总体数据汇总</strong> </div> -->
                <table class="orderedlist" width="820px" >
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
                     </table> </div>
               <div class="summary" >
                <table class="orderedlist" width="500px" style="margin-left:150px;background-color:#B0E0E6;border-color:#E0FFFF;border:2px;" >
                <tr>
                <td width="30px">Comment</td>
                <td><s:property value="comment"/></td>
                </tr></table></div><br>
           
<!-- Comment -->
        <!--<div class="multieditbox">
         <p><input name="comment" type="submit" class="comment" id="comment" value="Comment" /></p>
         <div class="comment_text">
         <textarea name="content" id="content" rows="1.5" cols="80" onchange="if(this.scrollHeight>80) this.style.posHeight=this.scrollHeight+5" 
         onmouseover="this.style.backgroundColor='#FFE4B5'" onmouseout="this.style.backgroundColor='#ffffff'"
         style="border:2px solid #FF6600"
         ></textarea>
        </div></div><br>-->
<!-- 测试种类汇总 -->
           <div class="bar_tabbox" id="bar_tabbox">
           <ul class="graybtn" style="float:left"></ul>
             <ul class="bar_tabs" style="float:right" id="bar_tabs">
               <li style="circle"></li><li>测试种类汇总</li>
            </ul>
           </div>
          <!--  <label for="comment">Comment：</label>-->
<!-- 柱状图 -->
         <div class="histogram" >
         <ul class="histogram_con" id="histogram_con">
             <li class="histogram_con">
             <div id="container" class="figure_project"></div>
             </li>
         </ul>
         </div>
         
<!-- 详细测试信息 --> 
         <div class="bar_tabbox" id="bar_tabbox">
           <ul class="graybtn" style="float:left"></ul>
             <ul class="bar_tabs" style="float:right" id="bar_tabs">
               <li style="circle"></li><li>详细测试信息</li>
            </ul>
           </div>
          
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
                          <table class="orderedlist" width="860px" >
                               <thead> <tr>
                                   <th width="3%">No.</th>
                                   <th width="3%">结果</th>
                                   <th width="3%">模块</th>
                                   <th width="5%">概要</th>
                                   <th width="30%">前提条件</th>
                                   <th width="8%">优先级</th>
                                   <th width="25%">步骤</th>
                                   <th width="18%">期望结果</th>
                                   <th width="5%">BugID</th>
                                </tr></thead>
                                 <tbody>
                                <s:iterator value="#request.allCaseList" id="case">
                                <tr>
                                    <td><s:property value="#case.caseID"/></td>
                                    <td id="result"><s:property value="#case.results"/></td>
                                    <td><s:property value="#case.module"/></td>
                                    <td><s:property value="#case.summary"/></td>
                                    <td><s:property value="#case.preconditions"/></td>
                                    <td><s:property value="#case.importance"/></td>
                                    <td><s:property value="#case.actions"/></td>
                                    <td><s:property value="#case.expectedResults"/></td>
                                    <td><s:property value="#case.bugID"/></td>
                                </tr>
                                </s:iterator>
                                </tbody>
                        </table>    
                    </li>
                    <li class="taps_con">
                         <table class="orderedlist" width="860px" >
                               <thead> <tr>
                                   <th width="3%">No.</th>
                                   <th width="3%">结果</th>
                                   <th width="3%">模块</th>
                                   <th width="5%">概要</th>
                                   <th width="30%">前提条件</th>
                                   <th width="8%">优先级</th>
                                   <th width="25%">步骤</th>
                                   <th width="18%">期望结果</th>
                                   <th width="5%">BugID</th>
                                </tr></thead>
                                <tbody>
                                <tr class="parent" id="row_01"><td>Sanity_001</td> <td style="background-color:#339933;">pass</td><td>system</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                <tr class="child_row_01"><td>Sanity_001.1</td> <td style="background-color:#339933;">pass</td><td>setting</td><td>log输出</td><td>1、进入文件管理器2、找到SD卡存储公司可根据色结果快速结果空间思考可根据司空见惯快速结果快速的估计司空见惯快速三个健康司机</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                <tr class="child_row_01"><td>Sanity_001.2</td> <td style="background-color:#339933;">pass</td><td>setting</td><td>log输出</td><td>aphagamabelta</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                <tr class="parent" id="row_02"><td>Sanity_002</td> <td style="background-color:#339933;">pass</td><td>system</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                <tr class="child_row_02" id="t1"><td>Sanity_002.1</td> <td style="background-color:#339933;">pass</td><td>setting</td><td>log输出</td><td>1、进入文件管理器<br>2、找到SD卡存储</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                <tr class="child_row_02"><td>Sanity_002.2</td> <td style="background-color:#339933;">pass</td><td>setting</td><td>log输出</td><td>aphagamabelta</td>
                                <td>A</td><td>输出adb log</td><td>正常输出adb log</td><td>333333</td></tr>
                                </tbody>
                        </table>    
                    </li>
                 <li class="taps_con">
                    <div style="font-size:14px;padding-left:29px;">
                    <span><a href="#">SP7731GEA-UUI-W14-43-2-01</a></span>
                    <br><strong><span><a style="text-align:left;">测试版本路径:</a></span></strong>
                    <span><a style="text-align:left;color:#0000ff;text-decoration: underline;" target="_blank" href="#">http://10</a></span>
                    <br><strong><span><a style="text-align:left">PAC:</a></span></strong>
                    <span><a style="text-align:left;" href="#">scx35_sp7731geacuccspecBplus_UUI_dt-userdebug-native.pac.gz</a></span>
                    <br><strong><span><a style="text-align:left">Tester:</a></span></strong>
                    <span><a style="text-align:left;" href="#">李娜</a></span><br><br>
                </div>              
                </li>
             </ul>
        </div> <br>       
        
 <div>
    <div style="float:left;margin-left:80%;">报告下载 </div>                                      
         <div style="float:left;margin-left:10px;">
         <a style="color:#0000ff;font-size: 15px;"target="_blank" href="download?testFormName=<s:property value="#request.testFormName"/>"> <img src="images/download.png" alt="download" height="25" width="30" ></a> </div><br>
                                                            
</div> 

          


    
         

  
</div>
</div>
</div>

</s:form>
</body>
</html>
         