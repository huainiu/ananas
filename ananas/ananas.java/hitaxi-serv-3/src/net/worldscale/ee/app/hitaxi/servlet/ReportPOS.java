package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;

/**
 * Servlet implementation class ReportPOS
 */
// @WebServlet("/cs/ReportPOS")
public class ReportPOS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportPOS() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String jid = request.getHeader("jid");
		final String pos = request.getHeader("geo-pos");
		final String time = request.getHeader("geo-time");

		System.out.println(jid);
		System.out.println("    geo-pos:" + pos);
		System.out.println("    geo-time:" + time);

		double longitude = 0;
		double latitude = 0;
		{
			int i1 = pos.indexOf(',');
			int i2 = pos.indexOf(",", i1 + 1);
			String strLon = pos.substring(0, i1);
			String strLat = (i2 < 0) ? pos.substring(i1 + 1) : pos.substring(
					i1 + 1, i2);

			longitude = Double.parseDouble(strLon);
			latitude = Double.parseDouble(strLat);
		}
		{
			;
		}

		IAgent agent = DefaultAgent.getInstance();
		ITaxi taxi = agent.getUserManager().getTaxi(jid);
		agent.getPosMap().setPos(taxi, longitude, latitude);

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
