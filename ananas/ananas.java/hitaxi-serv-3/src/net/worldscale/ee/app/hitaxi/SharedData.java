package net.worldscale.ee.app.hitaxi;

import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.UserType;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

public class SharedData {
	String help = "这里是帮助！";
	String version = "基础功能测试版";

	public void setLoginMode() {

		String jid = null;

		UserType loginMode = null;

		// UserType.type_taxi ;

		DefaultAgent.getInstance().getUserManager().openUser(jid, loginMode);
	}

	public void setSreachCar() {
		String jid = null;
		double longitude = 0;
		ICustomer user;
		double latitude = 0;
		user = DefaultAgent.getInstance().getUserManager().getCustomer(jid);
		ITaxi[] Result = DefaultAgent.getInstance().getPosMap()
				.findTaxi(user, longitude, latitude);

	}

	public String getHelp() {
		return help;
	}

	public String getVersion() {
		return version;
	}

}
