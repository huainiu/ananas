package net.worldscale.ee.app.hitaxi.db;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HitaxiDB {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		SessionFactory ret = sessionFactory;
		if (ret == null) {
			ret = new Configuration().configure().buildSessionFactory();
			sessionFactory = ret;
		}
		return ret;
	}

}
