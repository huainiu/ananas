package net.worldscale.ee.app.hitaxi.v2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.worldscale.ee.app.hitaxi.db.HitaxiDB;
import net.worldscale.ee.app.hitaxi.db.tables.User;

import org.hibernate.classic.Session;

/**
 * Servlet implementation class RegNewUser2
 */
public class RegNewUser2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegNewUser2() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this._proc(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this._proc(request, response);
	}

	private void _proc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Session session = HitaxiDB.getSessionFactory().getCurrentSession();
		session.beginTransaction();

		User user = new User();
		user.setNickname("kunkun");
		user.setPhone("13097730000");
		user.setUserId("13097730000");
		user.setCarNumber("ak888");

		session.save(user);
		session.getTransaction().commit();

	}

}
