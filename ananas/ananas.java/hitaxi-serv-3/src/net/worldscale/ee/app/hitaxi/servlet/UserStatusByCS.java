package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.UserType;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class UserStatusByCS
 */

// @WebServlet("/cs/UserStatusByCS")

public class UserStatusByCS extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IAgent mAgent;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserStatusByCS() {
		super();
		// TODO Auto-generated constructor stub

		this.mAgent = DefaultAgent.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mytaxistatus = request.getHeader("MyTaxiStatus");
		String jid = request.getHeader("jid");
		if (jid == null) {
			jid = "motofans@gmail.com";
		}
		UserType type = UserType.type_taxi;
		this.mAgent.getUserManager().openUser(jid, type);
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
