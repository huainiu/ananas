<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*,net.worldscale.ee.app.hitaxi.SharedData"
	errorPage=""%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>客户端类型选择</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<!--[if IE]>
	<style type="text/css" media="all">.borderitem {border-style: solid;}</style>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="../hitaxi1.css" media="all" />
<script type="text/javascript">
	function startTime() {
		var today = new Date();
		var h = today.getHours();
		var m = today.getMinutes();
		var s = today.getSeconds();
		// add a zero in front of numbers<10
		m = checkTime(m);
		s = checkTime(s);
		document.getElementById('txt').innerHTML = h + ":" + m + ":" + s;
		t = setTimeout('startTime()', 1000);
	}

	function checkTime(i) {
		if (i < 10) {
			i = "0" + i;
		}
		return i;
	}
</script>
<script type="text/javascript">
function getjid(){
	var jid=window.jsapi.getJabberID();
	//var jid="motofans@vip.qq.com";
	document.getElementById("showjid").innerHTML=jid;
	}
</script>
</head>
<body onload="startTime(),getjid()">
<div id="main"> <img src="../images/base_map_1.png" id="base_map_1" alt="" />
  <div class="clearFloat"></div>
  <div id="report">
    <p class="heitxi_char">
    <div id="showjid">hitaxi-show-jid</div>
    </p>
    <a href="../cs/index.jsp"><img name="CS" type="image"
				src="../images/mode_d.png" alt="我是司机" align="middle" width="152"
				height="48" /></a>
    <p>&nbsp;</p>
    <a href="../hs/index.jsp"><img name="HS" type="image"
				src="../images/mode_p.png" alt="我是乘客" align="middle" width="152"
				height="48" /></a>
    </p>
    <%
				out.print("<div class='heitxi_char' id='txt'></div>");
			%>
    <jsp:useBean id="version"
				class="net.worldscale.ee.app.hitaxi.SharedData" scope="application" />
    
    <p class="heitxi_char"> 软件版本：<%=version.getVersion()%></p>
  </div>
  <img src="../images/base_map_9.png" id="base_map_9" alt="" /> <img
			src="../images/mid_map_1.png" id="mid_map_1" alt="" /><img
			src="../images/base_map_2.png" id="base_map_2" alt="" />
  <div class="clearFloat"></div>
  <img src="../images/base_map_8.png" id="base_map_8" alt="" /> <img
			src="../images/mid_map_5.png" id="mid_map_5" alt="" /> <img
			src="../images/top_map_1.png" id="top_map_1" alt="" /> <img
			src="../images/mid_map_2.png" id="mid_map_2" alt="" /> <img
			src="../images/base_map_3.png" id="base_map_3" alt="" />
  <div class="clearFloat"></div>
  <img src="../images/base_map_7.png" id="base_map_7" alt="" />
  <div id="colwrap1"> <img src="../images/mid_map_4.png" id="mid_map_4" alt="" /> <img
				src="../images/top_map_2.png" id="top_map_2" alt="" /> <img
				src="../images/mid_map_3.png" id="mid_map_3" alt="" />
    <div class="clearFloat"></div>
    <img src="../images/base_map_6.png" id="base_map_6" alt="" />
    <div class="clearFloat"></div>
  </div>
  <img src="../images/base_map_4.png" id="base_map_4" alt="" />
  <div class="clearFloat"></div>
</div>
</body>
</html>
