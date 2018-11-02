/**
 * 
 */
package dao;

import java.math.BigInteger;
import java.util.List;
import java.util.ListIterator;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import controller.HibernateUtil;
import dao.entities.Medecin;

/**
 * @author Dell
 *
 */
public class MedecinDaoImplement implements MedecinDao {

	static Session session = HibernateUtil.openSession();
	Transaction tx = null;
	
	@Override
	public Medecin findByNum(String num) {
	    List<Medecin> listMedec;
	    listMedec = session.createSQLQuery("select * from Medecin where numero='"+num+"'").addEntity(Medecin.class).list();
	    Medecin m = new Medecin();
	    m = listMedec.get(0);
	    return m;
	} 
	
	@Override
	public Medecin add(Medecin m) {
		String numMedec= genererNumMedec();
		m.setNumero(numMedec);
		tx = session.beginTransaction();
		session.save(m);
		tx.commit();
		
		return m;
	}
	
	@Override
	public void delete(Integer id) {
		tx = session.getTransaction();
		session.beginTransaction();
		Medecin m = findById(id);
		session.delete(m);
		tx.commit();
	}
	

	@Override
	public List<Medecin> findAll() {
		return session.createQuery("select o from Medecin o").list();
	}

	@Override
	public Medecin findById(Integer id) {
		return (Medecin) session.get(Medecin.class, id);
	}
	public String genererNumMedec()
	{
		String numEmp = null; 
	    String[] splitArray = null;
	    List<Medecin> dernierMedec;
		dernierMedec = session.createSQLQuery("select * from Medecin order by id desc limit 0,1").addEntity(Medecin.class).list();
		if(!dernierMedec.isEmpty())
		{
			Medecin m = new Medecin();
			m =dernierMedec.get(0);
			numEmp = m.getNumero();
               splitArray = numEmp.split("");
               String un = splitArray[splitArray.length - 1];
               String deux = splitArray[splitArray.length - 2];
               String troi = splitArray[splitArray.length - 3];
               String EMP = "MED";
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
			numEmp ="MED001";
		}
		return numEmp;
	}


	@Override
	public Medecin edite(Medecin m, Integer id) {
		Medecin m2 = findById(id);
		tx = session.beginTransaction();
		session.saveOrUpdate(m2);
		m2.setNom(m.getNom());
		m2.setTauxJournalier(m.getTauxJournalier());
		tx.commit();
		return m2;
	}


}
