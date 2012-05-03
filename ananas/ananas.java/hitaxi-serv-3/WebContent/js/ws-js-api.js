/**
 * factory function
 */

function getJSAPI() {

	var jsapi = window.jsapi;
	if (jsapi == null) {
		jsapi = new VirtualJSAPI();
		window.jsapi = jsapi;
	}
	return jsapi;
}

/**
 * implements VirtualJSAPI.Pos
 */

function VirtualJSAPI_pos() {
	this.color = "b";
	this.length = 1;
}

VirtualJSAPI_pos.prototype.getLatitude = function() {
	return 25.0; // float
};

VirtualJSAPI_pos.prototype.getLongitude = function() {
	return 110.0; // float
};

VirtualJSAPI_pos.prototype.getAltitude = function() {
	return 222.0; // float
};

VirtualJSAPI_pos.prototype.getAccuracy = function() {
	return 10; // integer
};

VirtualJSAPI_pos.prototype.getTimeStamp = function() {
	return "Sun, 06 Nov 1994 08:49:37 GMT"; // String
};

VirtualJSAPI_pos.prototype.getSource = function() {
	return "virtual-api"; // String
};

/**
 * implements VirtualJSAPI
 */

function VirtualJSAPI() {
	this.color = "b";
	this.length = 1;
	this.m_enable_gps = false;
}

VirtualJSAPI.prototype.getJabberID = function() {
	return "virtual.jsapi@gmail.com"; // String
};

VirtualJSAPI.prototype.setGPSEnable = function(enable, timeInterval,
		distanceInterval) {
	this.m_enable_gps = enable;
	return; // void
};

VirtualJSAPI.prototype.isGPSEnable = function() {
	return this.m_enable_gps; // boolean
};

VirtualJSAPI.prototype.getPos = function() {
	return new VirtualJSAPI_pos(); // Pos
};
