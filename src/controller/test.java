package controller;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.entities.Patient;
import service.PatientServices;
import service.PatientServicesImpl;
public class test {
	static Session session = HibernateUtil.openSession();
    
	public static void main(String[] args)
	{
		PatientServices patientservice = new PatientServicesImpl();
		Patient p = new Patient();
		p.setNom("aaa");
		patientservice.add(p);
	}
	
}