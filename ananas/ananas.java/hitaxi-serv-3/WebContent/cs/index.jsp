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
</head>
<script type="text/javascript">
	function Call_UserStatusCS(num) {
		var xmlhttp;
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = State_Change;
		xmlhttp.open("GET", "./UserStatusByCS", true);
		xmlhttp.setRequestHeader("MyTaxiStatus", num);
		xmlhttp.setRequestHeader("no-remote", "yes");
		xmlhttp.send();
		function State_Change() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					document.getElementById("status_ico").src = "../images/status_ico_"
							+ num + ".png";
					if (num == 1) {
						window.jsapi.setGPSEnable(true);
						
					}
					if (num == 2) {
						window.jsapi.setGPSEnable(false);
					}

				} else {
					window.location = "../error/404.jsp";
				}
			}
		}
	}
	

</script>
<body>
	<div id="main">
		<img src="../images/base_map_1.png" id="base_map_1" alt="" />
		<div class="clearFloat"></div>
		<div id="report">
			<p></p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<p>&nbsp;</p>
			<div id="status_img">
				<img src="../images/status_ico_0.png" alt="" id="status_ico" />
			</div>
			<input name="leisure" type="button" class="hitexi_button"
				onclick="Call_UserStatusCS(1)" value="空车" /> <input name="busyness"
				type="button" class="hitexi_button" onclick="Call_UserStatusCS(2)"
				value="交班" />
			</p>
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
