<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- TemplateBeginEditable name="doctitle" -->
<title>HiTaxi搜车结果</title>
<!-- TemplateEndEditable -->
<!-- TemplateBeginEditable name="head" -->
<!-- TemplateEndEditable -->
<!--[if IE]>
	<style type="text/css" media="all">.borderitem {border-style: solid;}</style>
	<![endif]-->
<link rel="stylesheet" type="text/css" href="../hitaxi1.css" media="all" />
</head>
<body>
	<div id="main">
		<img src="../images/base_map_1.png" id="base_map_1" alt="" />
		<div class="clearFloat"></div>
		<div id="report">
			<script type="text/javascript">
				function Call_GetCarResult(url) {//今后在javascript中对servlet的调用一律采用call_加servlet名的格式
					var xmlhttp;
					if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
						xmlhttp = new XMLHttpRequest();
					} else {// code for IE6, IE5
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.open("get", url, true);
					xmlhttp.onreadystatechange = function() {
						if (xmlhttp.readyState == 4) {
							if (xmlhttp.status == 200) {
								if (xmlhttp.responseText != "") {
									document.getElementById("resultdiv").innerHTML = unescape(xmlhttp.responseText);
								}
							} else {
								document.getElementById("resultdiv").innerHTML = "数据载入出错,请重新搜车！";
							}
						}
					}
					xmlhttp.send(null);
				}
				setTimeout("Call_GetCarResult('../GetCarResult')", 10);
			</script>
			<script type="text/javascript">
				function Call_GetCarDetailServlet(num) {// 今后在javascript中对servlet的调用一律采用call_加servlet名的格式
					var Taxi = document.getElementById(num);
					var TaxiJid = Taxi.innerText;
					var CheckTaxi;
					var xmlhttp;

					if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
						xmlhttp = new XMLHttpRequest();
					} else {// code for IE6, IE5
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
					}

					xmlhttp.onreadystatechange = State_Change;
					xmlhttp.open("GET", "../GetCarDetail", true);
					xmlhttp.setRequestHeader("CheckTaxi", TaxiJid);
					xmlhttp.send();
					function State_Change() {
						if (xmlhttp.readyState == 4) {
							if (xmlhttp.status == 200) {
								window.location = "../hs/detail.jsp";
							} else {
								window.location = "../error/404.jsp";
							}//不正常状态

						}
					}
				}
			</script>
			<div id="resultdiv">
				<img src="../images/wait.gif" />
			</div>
			<div class="hitexi_button" id="buttondiv">
				<div align="center">
					<a href="./index.jsp"> <input name="back" type="button"
						class="hitexi_button" id="back" value="返回" />
					</a>
					</p>
				</div>
			</div>
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
