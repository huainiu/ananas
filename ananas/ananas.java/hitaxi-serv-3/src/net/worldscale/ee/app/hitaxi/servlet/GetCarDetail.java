package net.worldscale.ee.app.hitaxi.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		// TODO Auto-generated method stub
		String CheckTaxi = request.getHeader("CheckTaxi");
		String GetCheckTaxi = CheckTaxi;
		String GetTaxiPlate = "��H-WJ652";
		String GetTaxiTel = "18677305767";
		response.setContentType("text/html;charset=utf-8");// �����������utf-8
		PrintWriter out = response.getWriter();
		out.println("<html>");// ���������Ҫ����body��
		out.println("<body>");
		out.println("<table width='270' border='1' align='center'>");
		out.println("<tr>");
		out.println("<td id='NickName' align='center'>�ǳƣ�" + GetCheckTaxi
				+ "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td id='CarNumberPlate' align='center'>���ƣ�"
				+ GetTaxiPlate + "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td id='TaxiTel' align='center'>�绰��" + GetTaxiTel
				+ "</td>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td id='declare' align='left'>��HiTaxi������������������ڲ��Խ׶Σ��ݲ��ܱ�֤�ͻ���˫������ʵ�д�ɵĽ������������½⡣</td>");
		out.println("</tr>");
		out.println("</table>");
		out.println("<p> <a href='tel:"
				+ GetTaxiTel
				+ "'><img src='../images/tel.png' width='64' height='64' alt='tel' /></a> ");
		out.println("<a href='index.jsp'><img src='../images/stop.png' width='64' height='64' alt='stop' /></a> </p>");
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
