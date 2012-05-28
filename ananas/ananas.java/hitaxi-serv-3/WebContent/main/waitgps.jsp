<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src='../js/ws-js-api.js'></script>

<script type="text/javascript">
	var targetURL = "../hs/index.jsp";

	function startTimer() {
		var api = getJSAPI();
		api.setGPSEnable(false, 0, 0);
		api.setGPSEnable(true, 10 * 1000, 20);
		setTimeout("timer()", 1000);
	}

	function timer() {
		try {
			onTimer();
		} catch (e) {
		}
		setTimeout("timer()", 1000);
	}

	function onTimer() {
		var dotbar = document.getElementById("dotbar");
		var str = dotbar.innerHTML;
		dotbar.innerHTML = str + ".";
		//
		var api = getJSAPI();
		var pos = api.getPos();
		var acc = pos.getAccuracy();
		if ((0 < acc) && (acc < 3000)) {
			// ok
			window.location = targetURL;
		}

	}
</script>

</head>
<body onload='startTimer()'>
	waiting for location ...

	<div id='dotbar'></div>

</body>
</html>
