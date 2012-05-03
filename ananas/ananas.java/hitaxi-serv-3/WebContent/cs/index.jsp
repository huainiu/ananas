<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>HiTaxi车载模式</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<!--[if IE]>
	<style type="text/css" media="all">.borderitem {border-style: solid;}</style>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="../hitaxi1.css" media="all" />
<script src='../js/ws-js-api.js'></script>
<script type="text/javascript">
	function tellserver() {
		var jsapi=getJSAPI();
		var MyJid = jsapi.getJabberID();//获取登录jid
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = State_Change;
		xmlhttp.open("GET", "./UserStatusByCS", true);
		xmlhttp.setRequestHeader("jid", MyJid);
		xmlhttp.send();
		function State_Change() {
			xmlhttp.readyState == 4;
		}
	}
</script>
</head>
<script type="text/javascript">
	function Call_UserStatusCS(num) {
		var jsapi=getJSAPI();
		document.getElementById("status_ico").src = "../images/status_ico_"
				+ num + ".png";
		if (num == 1) {
			jsapi.setGPSEnable(true, 1, 1);
			reportmypos();
		}
		if (num == 2) {
			jsapi.setGPSEnable(false);
			document.getElementById("GPSstatus").innerHTML = "GPS已经关闭";
		} else {
			window.location = "../error/404.jsp";
		}
	}
	function reportmypos() {
		try {
			var MyPosAll = jsapi.getPos();//获取全部GPS数据
			var MyLat = MyPosAll.getLatitude();//获取纬度
			var MyLon = MyPosAll.getLongitude();//获取经度
			var MyAlt = MyPosAll.getAltitude();//获取海拔
			var MyAcc = MyPosAll.getAccuracy();//获取精度
			var MyTim = MyPosAll.getTimeStamp();//获取当前时间戳
			var MySou = MyPosAll.getSource();//获取GPS设备源
			var MyJid = jsapi.getJabberID();//获取登录jid
			var MyisGPS = jsapi.isGPSEnable();//获取gps工作状态
			var xmlhttp;
			if (window.XMLHttpRequest) {
				xmlhttp = new XMLHttpRequest();
			} else {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = State_Change;
			xmlhttp.open("GET", "./ReportPOS", true);
			xmlhttp.setRequestHeader("geo-pos", MyLon + "," + MyLat);
			xmlhttp.setRequestHeader("MyAlt", MyAlt);
			xmlhttp.setRequestHeader("MyAcc", MyAcc);
			xmlhttp.setRequestHeader("geo-time", MyTim);
			xmlhttp.setRequestHeader("MySou", MySou);
			xmlhttp.setRequestHeader("jid", MyJid);
			xmlhttp.setRequestHeader("MyisGPS", MyisGPS);
			xmlhttp.send();
			function State_Change() {
				xmlhttp.readyState == 4;
			}
			var s = "<br>";
			s = s + "<br>获取登录jid" + MyJid;
			s = s + "<br>获取纬度" + MyLat;
			s = s + "<br>获取经度" + MyLon;
			s = s + "<br>获取海拔" + MyAlt;
			s = s + "<br>获取精度" + MyAcc;
			s = s + "<br>时间戳" + MyTim;
			s = s + "<br>设备源" + MySou;
			s = s + "<br>gps工作状态" + MyisGPS;
			document.getElementById("GPSstatus").innerHTML = s;
		} catch (err) {
			alert(err);
		}
		if (num == 1) {
			setTimeout("reportmypos()", 1000);
		}
		if (num == 2) {
			clearTimeout("reportmypos()");
		}
	}
</script>
<body onload="tellserver()">
	<div id="main">
		<img src="../images/base_map_1.png" id="base_map_1" alt="" />
		<div class="clearFloat"></div>
		<div id="report">
			<div id="status_img">
				<img src="../images/status_ico_0.png" alt="" id="status_ico" /> <input
					name="leisure" type="button" class="hitexi_button"
					onclick="Call_UserStatusCS(1)" value="空车" /> <input
					name="busyness" type="button" class="hitexi_button"
					onclick="Call_UserStatusCS(2)" value="交班" />
			</div>
			<p class="heitxi_char" id="GPSstatus">&nbsp;</p>
			<p class="heitxi_char">&nbsp;</p>
			<p class="heitxi_char">&nbsp;</p>
			<p class="heitxi_char">&nbsp;</p>
			<p class="heitxi_char">&nbsp;</p>
			<p class="heitxi_char">&nbsp;</p>
			<p class="heitxi_char">请设置当前车辆的状态</p>
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