package config;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.entities.Patient;
public class Test {
	static Session session = HibernateUtil.openSession();
	public static void main(String[] args)
	{
		Transaction tx = null;
		Patient p = new Patient();
		p.setNumero("aaa");
		p.setNom("aa");
		p.setAdresse("aaa");
		tx = session.beginTransaction();
		session.save(p);
		tx.commit();
	}
	
}
