package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.api.IAgent;
import net.worldscale.ee.app.hitaxi.api.ICustomer;
import net.worldscale.ee.app.hitaxi.api.ITaxi;
import net.worldscale.ee.app.hitaxi.api.impl.DefaultAgent;
import net.worldscale.ee.app.hitaxi.util.GeoPos;

/**
 * Servlet implementation class GetCarResult
 */
// @WebServlet("/GetCarResult")
public class GetCarResult extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetCarResult() {
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
		int TaxiHow = 5;
		response.setContentType("text/html;charset=utf-8");

		IAgent agent = DefaultAgent.getInstance();
		ICustomer customer;
		double longitude;
		double latitude;
		{
			String jid = request.getHeader("jid");
			String pos = request.getHeader("geo-pos");
			String time = request.getHeader("geo-time");
			customer = agent.getUserManager().getCustomer(jid);
			GeoPos gpos = GeoPos.Factory.parse(pos);
			longitude = gpos.getLongitude();
			latitude = gpos.getLatitude();
		}
		ITaxi[] result = agent.getPosMap().findTaxi(customer, longitude,
				latitude);

		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		out.println("<link href='../hitaxi1.css' rel='stylesheet' type='text/css' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<table width='270' border='1' align='center'>");
		int i = 0;
		for (ITaxi taxi : result) {

			out.println("<tr>");
			out.println("<td width='134' id=" + i + ">");
			out.println(taxi.getNickname());
			out.println("</td>");
			out.println("<td width='120' id='taxisapce" + i + "'>");
			out.println(/* distance */);
			out.println("<img src='../images/checke_result.png' id='checked' align='absmiddle' onclick='Call_GetCarDetailServlet("
					+ i + ")' alt=''/>");
			out.println("</td>");
			out.println("</tr>");

			i++;
		}
		out.println("</table>");
		out.println("</body>");
		out.println("</html>");
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
