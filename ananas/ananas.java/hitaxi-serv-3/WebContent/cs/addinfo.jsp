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
	function web() {
		window.location = "./index.jsp";
	}
	function addinfo() {
		var jsapi = getJSAPI();
		var jid = jsapi.getJabberID();//获取登录jid
		var nickname = document.getElementById("nickname").value;
		var cartel = document.getElementById("cartel").value;
		var province = document.getElementById("province").value;
		var licenseHead = document.getElementById("licenseHead").value;
		var carnum = document.getElementById("carnum").value;
		var xmlhttp;
		if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
			xmlhttp = new XMLHttpRequest();
		} else {// code for IE6, IE5
			xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange = State_Change;
		xmlhttp.open("POST", "./SetCarInfo", true);
		//xmlhttp.setRequestHeader("text/html;charset=utf-8");
		xmlhttp.setRequestHeader("jid", jid);
		xmlhttp.setRequestHeader("nickname", nickname);
		xmlhttp.setRequestHeader("cartel", cartel);
		xmlhttp.setRequestHeader("province", province);
		xmlhttp.setRequestHeader("licenseHead", licenseHead);
		xmlhttp.setRequestHeader("carnum", carnum);
		xmlhttp.send();
		function State_Change() {
			if (xmlhttp.readyState == 4) {
				if (xmlhttp.status == 200) {
					document.getElementById("report").innerHTML = "<br><br><br><br>您的信息已登记成功！<br><br>跳转主页中……";
					setTimeout("web()", 1000);
				} else {
					window.location = "../error/404.jsp";// 服务器维护中！
				}
			}
		}

	}
</script>
<script type="text/javascript">
	function dataclear() {
		document.getElementById("nickname").value = "";
		document.getElementById("cartel").value = "";
		document.getElementById("province").value = "京";
		document.getElementById("licenseHead").value = "A";
		document.getElementById("carnum").value = "";
	}
</script>
</head>
<body>
	<div id="main">
		<img src="../images/base_map_1.png" id="base_map_1" alt="" />
		<div class="clearFloat"></div>
		<div id="report">
			<p class="hitexi_textable">车载用户信息登记</p>
			<p>
				车主昵称 <input name="nickname" type="text" class="heitxi_char"
					id="nickname" size="18" maxlength="14" />
			</p>
			<p>
				车主电话 <input name="cartel" type="text" class="heitxi_char"
					id="cartel" size="18" maxlength="13" />
			</p>
			<p>
				<label for="carnum"></label> 车辆牌号 <label for="where"></label> <select
					name="province" class="heitxi_char" id="province">
					<option value="京">京</option>
					<option value="津">津</option>
					<option value="冀">冀</option>
					<option value="晋">晋</option>
					<option value="蒙">蒙</option>
					<option value="辽">辽</option>
					<option value="吉">吉</option>
					<option value="黑">黑</option>
					<option value="沪">沪</option>
					<option value="苏">苏</option>
					<option value="浙">浙</option>
					<option value="皖">皖</option>
					<option value="闽">闽</option>
					<option value="赣">赣</option>
					<option value="鲁">鲁</option>
					<option value="豫">豫</option>
					<option value="鄂">鄂</option>
					<option value="湘">湘</option>
					<option value="粤">粤</option>
					<option value="桂">桂</option>
					<option value="琼">琼</option>
					<option value="渝">渝</option>
					<option value="川">川</option>
					<option value="贵">贵</option>
					<option value="云">云</option>
					<option value="藏">藏</option>
					<option value="陕">陕</option>
					<option value="甘">甘</option>
					<option value="青">青</option>
					<option value="宁">宁</option>
					<option value="新">新</option>
				</select> <select name="licenseHead" class="heitxi_char" id="licenseHead">
					<option value="A">A</option>
					<option value="B">B</option>
					<option value="C">C</option>
					<option value="D">D</option>
					<option value="E">E</option>
					<option value="F">F</option>
					<option value="G">G</option>
					<option value="H">H</option>
					<option value="I">I</option>
					<option value="J">J</option>
					<option value="K">K</option>
					<option value="L">L</option>
					<option value="M">M</option>
					<option value="N">N</option>
					<option value="O">O</option>
					<option value="P">P</option>
					<option value="Q">Q</option>
					<option value="R">R</option>
					<option value="S">S</option>
					<option value="T">T</option>
					<option value="U">U</option>
					<option value="V">V</option>
					<option value="W">W</option>
					<option value="X">X</option>
					<option value="Y">Y</option>
					<option value="Z">Z</option>
				</select> <input name="carnum" type="text" class="heitxi_char" id="carnum"
					value="" size="7" maxlength="5" />
			</p>
			<p>
				<input name="add" type="button" class="hitexi_button" id="Rinfo"
					value="登记" onclick="addinfo()" /> <input name="addinfo"
					type="button" class="hitexi_button" id="Rwrite" value="清空"
					onclick="dataclear()" /> <a href="index.jsp"> <input
					name="addinfo" type="button" class="hitexi_button" id="addinfo"
					value="返回" />
				</a>
			</p>
			<div align="center">通过登记后能让乘客找到你</div>
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
