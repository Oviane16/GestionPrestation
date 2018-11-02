/**
 * 
 */
package dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import controller.HibernateUtil;
import dao.entities.Medicament;
import dao.entities.Patient;

/**
 * @author Dell
 *
 */
public class PatientDaoImpl implements PatientDao {
	static Session session = HibernateUtil.openSession();
	Transaction tx = null;
	@Override
	public Patient add(Patient p) {
		String numPatient= genererNumPatient();
		p.setNumero(numPatient);
		tx = session.beginTransaction();
		session.save(p);
		tx.commit();
		return p;
	}

	
/*
 *;*/
	@Override
	public void delete(Integer id) {;
		tx = session.getTransaction();
		session.beginTransaction();
		Patient p = findById(id);
		session.delete(p);
		tx.commit();
	}

	@Override
	public List<Patient> findAll() {
		return session.createQuery("select p from Patient p").list();
	}

	@Override
	public Patient findById(Integer id) {
		return (Patient) session.get(Patient.class, id);
	}
	public String genererNumPatient()
	{
		String numPat = null; 
	    String[] splitArray = null;
	    List<Patient> dernierPat;
	    Session session = HibernateUtil.openSession();
	    dernierPat = session.createSQLQuery("select * from Patient order by id desc limit 0,1").addEntity(Patient.class).list();
		if(!dernierPat.isEmpty())
		{
			Patient m = new Patient();
			m =dernierPat.get(0);
			numPat = m.getNumero();
               splitArray = numPat.split("");
               String un = splitArray[splitArray.length - 1];
               String deux = splitArray[splitArray.length - 2];
               String troi = splitArray[splitArray.length - 3];
               String EMP = "PAT";
               String chiffre = troi + deux + un;
               int chiffreInt = Integer.parseInt(chiffre);
               int chiffreApresEMP = chiffreInt + 1;
               if (chiffreApresEMP < 10) {
                   numPat = EMP + "00" + String.valueOf(chiffreApresEMP);
               }
               if (chiffreApresEMP < 100 && chiffreApresEMP > 9) {
            	   numPat = EMP + "0" + String.valueOf(chiffreApresEMP);
               }
               if (chiffreApresEMP > 99 && chiffreApresEMP < 1000) {
            	   numPat = EMP + String.valueOf(chiffreApresEMP);
               }
		}
		else
		{
			numPat ="PAT001";
		}
		return numPat;
	}


	@Override
	public Patient edite(Patient m, Integer id) {
		Patient p2 = findById(id);
		tx = session.beginTransaction();
		session.saveOrUpdate(p2);
		p2.setNom(m.getNom());
		tx.commit();
		return p2;
	}

	@Override
	public Patient findByNum(String num) {
		  List<Patient> listPatient;
		  listPatient = session.createSQLQuery("select * from Patient where numero='"+num+"'").addEntity(Patient.class).list();
		  Patient m = new Patient();
		    m = listPatient.get(0);
		    return m;
	}


	@Override
	public List<Patient> patientParMedec(Integer idMedec) {
		List<Patient> listPatient;
		listPatient = session.createQuery("from Ordonnance o inner join fetch o.patient inner join o.medecin and medecin.id='"+idMedec+"'").list();
		return listPatient;
	} 
}
