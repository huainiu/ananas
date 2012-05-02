<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>Hitaxi订车详单</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<!--[if IE]>
	<style type="text/css" media="all">.borderitem {border-style: solid;}</style>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="../hitaxi1.css" media="all" />
</head>
<body onload="Call_GetCarDetail()">
<script type="text/javascript">
		function Call_GetCarDetail() {//今后在javascript中对servlet的调用一律采用call_加servlet名的格式
			var xmlhttp;
			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.open("get", "../GetCarDetail", true);
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4) {
					if (xmlhttp.status == 200) {
						if (xmlhttp.responseText != "") {
							document.getElementById("detaildiv").innerHTML = unescape(xmlhttp.responseText);
						}
					} else {
					  document.getElementById("detaildiv").innerHTML = "<p>数据载入错误，请重新搜车！</p><p><a href='index.jsp'><img src='../images/stop.png' width='64' height='64' alt='backindexHS'/></a></p><p>请点击上面的按钮返回主页</p>";
					}
				}
			}
			xmlhttp.send(null);
		}
	</script>
<div id="main"> <img src="../images/base_map_1.png" id="base_map_1" alt="" />
  <div class="clearFloat"></div>
  <div id="report">
    <div id="detaildiv"></div>
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
