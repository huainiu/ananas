<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>HiTaxi手持模式</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<!--[if IE]>
	<style type="text/css" media="all">.borderitem {border-style: solid;}</style>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="../hitaxi1.css" media="all" />
<script src='../js/ws-js-api.js'></script>
<script type="text/javascript">
	function Call_UserStatusHS() {// 今后在javascript中对servlet的调用一律采用call_加servlet名的格式

		var jsapi = getJSAPI();
		var jid = jsapi.getJabberID();//获取登录jid

		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = State_Change;
		xmlhttp.open("GET", "./UserStatusByHS", true);
		xmlhttp.setRequestHeader("jid", jid);
		xmlhttp.send();
		function State_Change() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
				} else {
					window.location = "../error/404.jsp";// 服务器维护中！
				}
			}
		}
	}
</script>
<script type="text/javascript">
	function mypos() {
		var MyPosAll = window.jsapi.getPos();
		var MyLat = MyPosAll.getLatitude();
		var MyLon = MyPosAll.getLongitude();
		var MyAlt = MyPosAll.getAltitude();
		var MyAcc = MyPosAll.Accuracy();
		var MyTim = MyPosAll.getTimeStamp();
		var MySou = MyPosAll.getSource();

	}
</script>
</head>
<body onload="Call_UserStatusHS()">
	<div id="main">
		<img src="../images/base_map_1.png" id="base_map_1" alt="" />
		<div class="clearFloat"></div>
		<div id="report">
			<p>&nbsp;</p>
			<p>
				<a href="./result.jsp" /><img src="../images/findcar.png" /></a>
			</p>
			<p>点击“搜车”开始操作！</p>

			<p class="heitxi_char">&nbsp;</p>
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
		<div id="colwrap1">
			<img src="../images/mid_map_4.png" id="mid_map_4" alt="" /> <img
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
