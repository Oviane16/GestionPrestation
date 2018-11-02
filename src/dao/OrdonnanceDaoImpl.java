package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import dao.entities.Medecin;
import dao.entities.Medicament;
import dao.entities.Ordonnance;
import dao.entities.Patient;

public class OrdonnanceDaoImpl implements OrdonnanceDao {

	static Session session = HibernateUtil.openSession();
	Transaction tx = null;
	
	@Override
	public Ordonnance add(Ordonnance ordo) {
		Date date = new Date();
		java.sql.Date DateCons = new java.sql.Date(date.getTime());
		
		String numMedec= genererNumOrdo();
		ordo.setNumero(numMedec);
		ordo.setDateCons(DateCons);
		tx = session.beginTransaction();
		session.save(ordo);
		tx.commit();
		return ordo;
	}

	@Override
	public Ordonnance edite(Ordonnance ordo, Integer id) {
		Ordonnance ordo2 = new Ordonnance();
		ordo2 = findById(id);
		tx = session.beginTransaction();
		session.saveOrUpdate(ordo2);
		ordo2.setMedecin(ordo.getMedecin());
		ordo2.setMedicament(ordo.getMedicament());
		ordo2.setPatient(ordo.getPatient());
		ordo2.setDateCons(ordo.getDateCons());
		tx.commit();
		return ordo;
	}

	@Override
	public void delete(Integer id) {
		tx = session.getTransaction();
		session.beginTransaction();
		Ordonnance o = findById(id);
		session.delete(o);
		tx.commit();
		
	}

	@Override
	public List<Ordonnance> findAll() {
	    List<Ordonnance> listOrdonnance;
	   // listOrdonnance = session.createSQLQuery("select o,medic from Ordonnance o inner join o.medicament medic ").addEntity("o",Ordonnance.class).addEntity("medic",Medicament.class).addEntity("medec",Medecin.class).list();
	    listOrdonnance = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
	    													+ " inner join fetch o.medicament medic "
	    													+ "inner join fetch o.patient p").list();
	    return listOrdonnance;
	}

	@Override
	public Ordonnance findById(Integer id) {
		return (Ordonnance) session.get(Ordonnance.class, id);
	}


	public String genererNumOrdo()
	{
		String numEmp = null; 
	    String[] splitArray = null;
	    List<Ordonnance> dernierOrdo;
	    dernierOrdo = session.createSQLQuery("select * from Ordonnance order by id desc limit 0,1").addEntity(Ordonnance.class).list();
		if(!dernierOrdo.isEmpty())
		{
			Ordonnance ordo = new Ordonnance();
			ordo =dernierOrdo.get(0);
			numEmp = ordo.getNumero();
               splitArray = numEmp.split("");
               String un = splitArray[splitArray.length - 1];
               String deux = splitArray[splitArray.length - 2];
               String troi = splitArray[splitArray.length - 3];
               String EMP = "ORDO";
               String chiffre = troi + deux + un;
               int chiffreInt = Integer.parseInt(chiffre);
               int chiffreApresEMP = chiffreInt + 1;
               if (chiffreApresEMP < 10) {
                   numEmp = EMP + "00" + String.valueOf(chiffreApresEMP);
               }
               if (chiffreApresEMP < 100 && chiffreApresEMP > 9) {
                   numEmp = EMP + "0" + String.valueOf(chiffreApresEMP);
               }
               if (chiffreApresEMP > 99 && chiffreApresEMP < 1000) {
                   numEmp = EMP + String.valueOf(chiffreApresEMP);
               }
		}
		else
		{
			numEmp ="ORDO001";
		}
		return numEmp;
	}
	@Override
	public List<Ordonnance> patientParMedec(Integer idMedec,String dateA,String dateDe) {
		List<Ordonnance> listOrdo;
		if(dateA=="" | dateDe=="")
		{
			
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
					+ " inner join fetch o.medicament medic"
					+ " inner join fetch o.patient p where medec.id='"+idMedec+"'").list();
		}
		else
		{
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
					+ " inner join fetch o.medicament medic"
					+ " inner join fetch o.patient p where medec.id='"+idMedec+"' and  o.dateCons between '"+dateDe+"' and '"+dateA+"' ").list();
		}
		
		return listOrdo;
	}
	@Override
	public List<Ordonnance> patientConsMedec(Integer idPatient,String dateA,String dateDe) {
		List<Ordonnance> listOrdo;
		if(dateA=="" | dateDe=="")
		{
			
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
					+ " inner join fetch o.patient p where p.id='"+idPatient+"'").list();
		}
		else
		{
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
					+ " inner join fetch o.patient p where p.id='"+idPatient+"' and  o.dateCons between '"+dateDe+"' and '"+dateA+"' ").list();
		}
		
		return listOrdo;
	}
	
	@Override
	public List<Ordonnance> patientFacture(Integer idPatient,String dateA,String dateDe) {
		List<Ordonnance> listOrdo;
		if(dateA=="" | dateDe=="")
		{
			
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medicament medic"
					+ " inner join fetch o.patient p where p.id='"+idPatient+"'").list();
		}
		else
		{
			listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medicament medic"
					+ " inner join fetch o.patient p where p.id='"+idPatient+"' and  o.dateCons between '"+dateDe+"' and '"+dateA+"' ").list();
		}
		
		return listOrdo;
	}

	@Override
	public List<Ordonnance> perstationGraphe(Integer idPatient) {
		List<Ordonnance> listOrdo;
		listOrdo = session.createQuery("from Ordonnance o inner join fetch o.medecin medec"
				+ " inner join fetch o.patient p where medec.id='"+idPatient+"' ").list();
		return listOrdo;
	}

	@Override
	public void deleteMedecin(Integer id) {
		Query q= session.createQuery("delete Ordonnance where medecinid='"+id+"'");
		q.executeUpdate();
	}

	public void deleteMedicament(Integer id) {
		Query q= session.createQuery("delete Ordonnance where medecinid='"+id+"'");
		q.executeUpdate();
		
	}
	@Override
	public void deletePatient(Integer id) {
		Query q= session.createQuery("delete Ordonnance where medecinid='"+id+"'");
		q.executeUpdate();
		
	}

}
