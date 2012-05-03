package net.worldscale.ee.app.hitaxi.servlet.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.IUser;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class MonitorServlet
 */

// @WebServlet("/MonitorServlet")

public class MonitorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonitorServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		response.setStatus(200);
		response.setHeader("Content-Type",
				"application/vnd.google-earth.kml+xml");

		IAgent agent = DefaultAgent.getInstance();
		IUser[] list = agent.getUserManager().listUsers(0, 100);

		ServletOutputStream os = response.getOutputStream();
		os.print("");
		os.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		os.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\" >");
		os.println("<Document><name>Monitor</name>");
		for (IUser item : list) {
			String name = item.getNickname();
			String style = "./monitor-style#"
					+ ((item instanceof ITaxi) ? "style_taxi"
							: "style_customer");
			String coor = item.getLongitude() + "," + item.getLatitude();

			os.print("<Placemark><name>");
			os.print(name);
			os.print("</name><styleUrl>");
			os.print(style);
			os.print("</styleUrl><Point><coordinates>");
			os.print(coor);
			os.print("</coordinates></Point></Placemark>");
			os.println();

		}
		os.println("</Document></kml>");
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
