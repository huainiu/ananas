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
 * Servlet implementation class GetCarDetail
 */
// @WebServlet("/GetCarDetail")
public class GetCarDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCarDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String jid = request.getHeader("jid");
		IAgent agent = DefaultAgent.getInstance();
		ITaxi taxi = agent.getUserManager().getTaxi(jid);
		ServletOutputStream out = response.getOutputStream();
		// {"jid":"xk@gm","nickname":"xx","phone":"130xxxxx","plate":"bj4095" }
		out.print("{");
		out.print("\"jid\":\"" + taxi.getJID() + "\",");
		out.print("\"nickname\":\"" + taxi.getNickname() + "\",");
		out.print("\"plate\":\"" + taxi.getNumberPlate() + "\",");
		out.print("\"phone\":\"" + taxi.getPhoneNumber() + "\"");
		out.print("}");
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
