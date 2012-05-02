package net.worldscale.ee.app.hitaxi.servlet.admin;

import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.IUser;
import net.worldscale.ee.app.hitaxi.api.IUserManager;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class AdminShowRuntimeInfo
 */

// @WebServlet("/admin/AdminShowRuntimeInfo")

public class AdminShowRuntimeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final IAgent mAgent;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminShowRuntimeInfo() {
		super();
		this.mAgent = DefaultAgent.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setHeader("content-type", "text/plain");
		final PrintStream ps = new PrintStream(response.getOutputStream());
		ps.println("HiTaxi Server Runtime Info");
		ps.println();
		final IUserManager um = this.mAgent.getUserManager();
		final IUser[] userlist = um.listUsers(0, 1000);
		for (IUser user : userlist) {
			ps.println(user.getJID() + " | " + user.getClass().getName());
		}
		ps.flush();
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
