package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class SetCarInfo
 */
public class SetCarInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetCarInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String jid = request.getHeader("jid");
		String nickname = request.getHeader("nickname");
		String phoneNum = request.getHeader("cartel");

		String province = request.getHeader("province");
		String licenseHead = request.getHeader("licenseHead");
		String carnum = request.getHeader("carnum");

		IAgent agent = DefaultAgent.getInstance();
		ITaxi taxi = agent.getUserManager().getTaxi(jid);
		taxi.setNickname(nickname);
		taxi.setPhoneNumber(phoneNum);
		taxi.setNumberPlate(province + licenseHead + carnum);

		ServletOutputStream out = response.getOutputStream();
		out.println("jid:" + jid);
		out.println("nickname:" + nickname);
		out.println("phoneNum:" + phoneNum);
		out.println("province:" + province);
		out.println("licenseHead:" + licenseHead);
		out.println("carnum:" + carnum);

	}

}
