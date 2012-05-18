package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.UserType;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class UserStatusByHS
 */
// @WebServlet("/hs/UserStatusByHS")
public class UserStatusByHS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IAgent mAgent;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserStatusByHS() {
		super();

		this.mAgent = DefaultAgent.getInstance();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/*
		 * 
		 * 
		 * 
		 * xmlhttp.setRequestHeader("geo-pos", MyLon + "," + MyLat);
		 * xmlhttp.setRequestHeader("MyAlt", MyAlt);
		 * xmlhttp.setRequestHeader("MyAcc", MyAcc);
		 * xmlhttp.setRequestHeader("geo-time", MyTim);
		 * xmlhttp.setRequestHeader("MySou", MySou);
		 * xmlhttp.setRequestHeader("jid", MyJid);
		 * xmlhttp.setRequestHeader("MyisGPS", MyisGPS); *
		 */

		String jid = request.getHeader("jid");
		String pos = request.getHeader("geo-pos");
		double longitude;
		double latitude;
		{
			int index = pos.indexOf(',');
			String strLon = pos.substring(0, index);
			String strLat = pos.substring(index + 1);
			longitude = Double.parseDouble(strLon);
			latitude = Double.parseDouble(strLat);
		}
		ICustomer customer = (ICustomer) this.mAgent.getUserManager().openUser(
				jid, UserType.type_customer);
		ITaxi[] rlt = this.mAgent.getPosMap().findTaxi(customer, longitude,
				latitude);

		StringBuffer json = new StringBuffer();
		json.append("[");
		int index = 0;
		for (ITaxi taxi : rlt) {
			if (taxi != null) {
				if (index > 0) {
					json.append(",");
				}
				json.append("{");
				json.append("\"jid\":\"" + taxi.getJID() + "\",");
				json.append("\"nickname\":\"" + taxi.getNickname() + "\",");
				json.append("\"distance\":" + 99 + "");
				json.append("}");
				index++;
			}
		}
		json.append("]");

		ServletOutputStream out = response.getOutputStream();
		String strJson = json.toString();
		out.print(strJson);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
