/**
 * 
 */
package dao;

import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import dao.entities.Medecin;
import dao.entities.Medicament;

/**
 * @author Dell
 *
 */
public class MedicamentDaoImpl implements MedicamentDao {

	static Session session = HibernateUtil.openSession();
	Transaction tx = null;
	@Override
	public Medicament add(Medicament m) {
		String numMedicament= genererNumMedicament();
		m.setNumero(numMedicament);
		tx = session.beginTransaction();
		session.save(m);
		tx.commit();
		return m;
	}
/*
 *;*/
	@Override
	public void delete(Integer id) {
		tx = session.getTransaction();
		session.beginTransaction();
		Medicament m = findById(id);
		session.delete(m);
		tx.commit();
	}

	@Override
	public List<Medicament> findAll() {
		return session.createQuery("select medic from Medicament medic").list();
	}
	

	public Medicament findByNumero(String num) {
	    List<Medicament> listMedic;
	    listMedic = session.createSQLQuery("select * from Medicament where numero='"+num+"'").addEntity(Medicament.class).list();
	    Medicament m = new Medicament();
	    m = listMedic.get(0);
	    return m;
	} 

	@Override
	public Medicament findById(Integer id) {
		return (Medicament) session.get(Medicament.class, id);
	}
	public String genererNumMedicament()
	{
		String numEmp = null; 
	    String[] splitArray = null;
	    List<Medicament> dernierMedec;
		dernierMedec = session.createSQLQuery("select * from Medicament order by id desc limit 0,1").addEntity(Medicament.class).list();
		if(!dernierMedec.isEmpty())
		{
			Medicament m = new Medicament();
			m =dernierMedec.get(0);
			numEmp = m.getNumero();
               splitArray = numEmp.split("");
               String un = splitArray[splitArray.length - 1];
               String deux = splitArray[splitArray.length - 2];
               String troi = splitArray[splitArray.length - 3];
               String EMP = "MDIC";
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
			numEmp ="MDIC001";
		}
		return numEmp;
	}


	@Override
	public Medicament edite(Medicament m, Integer id) {
		Medicament m2 = findById(id);
		tx = session.beginTransaction();
		session.saveOrUpdate(m2);
		m2.setDesign(m.getDesign());
		m2.setPu(m.getPu());
		tx.commit();
		return m2;
	}



}
