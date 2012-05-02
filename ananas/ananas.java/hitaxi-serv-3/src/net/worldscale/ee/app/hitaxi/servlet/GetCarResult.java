package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int TaxiHow = 5;// �˴�ʵ��ֵ�����ڷ�������������JID�ĸ���
		response.setContentType("text/html;charset=utf-8");// �����������utf-8
		PrintWriter out = response.getWriter();
		out.println("<html>");// �����resultdiv������
		out.println("<head>");
		out.println("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		out.println("<link href='../hitaxi1.css' rel='stylesheet' type='text/css' />");
		out.println("</head>");
		out.println("<body>");
		out.println("<table width='270' border='1' align='center'>");
		for (int i = 0; i < TaxiHow; i++) {
			String[] TaxiNames = new String[5];
			TaxiNames[0] = "LaughingMO";
			TaxiNames[1] = "ռλ���������";
			TaxiNames[2] = "ռλ���������";
			TaxiNames[3] = "ռλ���������";
			TaxiNames[4] = "ռλ���������";
			String[] TaxiSpace = new String[5];
			TaxiSpace[0] = "9000m";
			TaxiSpace[1] = "0m";
			TaxiSpace[2] = "0m";
			TaxiSpace[3] = "0m";
			TaxiSpace[4] = "0m";
			out.println("<tr>");
			out.println("<td width='134' id=" + i + ">");
			out.println(TaxiNames[i]);
			out.println("</td>");
			out.println("<td width='120' id='taxisapce" + i + "'>");
			out.println(TaxiSpace[i]);
			out.println("<img src='../images/checke_result.png' id='checked' align='absmiddle' onclick='Call_GetCarDetailServlet("
					+ i + ")' alt='����'/>");
			out.println("</td>");
			out.println("</tr>");
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
