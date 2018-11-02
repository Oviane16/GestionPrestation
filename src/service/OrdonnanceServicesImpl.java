package service;

import java.util.List;

import dao.MedicamentDao;
import dao.MedicamentDaoImpl;
import dao.OrdonnanceDaoImpl;
import dao.entities.Ordonnance;
import dao.entities.Patient;

public class OrdonnanceServicesImpl implements OrdonnanceServices{

	OrdonnanceDaoImpl dao = new OrdonnanceDaoImpl();
	@Override
	public Ordonnance add(Ordonnance m) {
		return dao.add(m);
	}

	@Override
	public Ordonnance edite(Ordonnance ordo, Integer id) {
		return dao.edite(ordo,id);
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Ordonnance> findAll() {
		return dao.findAll();
	}

	@Override
	public Ordonnance findById(Integer id) {
		return dao.findById(id);
	}


	@Override
	public List<Ordonnance> patientParMedec(Integer idMedec,String dateA,String dateDe) {
		return dao.patientParMedec(idMedec,dateA,dateDe);
	}
	
	@Override
	public List<Ordonnance> patientConsMedec(Integer idPatient,String dateA,String dateDe) {
		return dao.patientConsMedec(idPatient,dateA,dateDe);
	}
	@Override
	public List<Ordonnance> patientFacture(Integer idPatient,String dateA,String dateDe) {
		return dao.patientFacture(idPatient,dateA,dateDe);
	}

	@Override
	public Ordonnance findByNum(String num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ordonnance> perstationGraphe(Integer idPatient) {
		return dao.perstationGraphe(idPatient);
	}

	@Override
	public void deletePatient(Integer id) {
		// TODO Auto-generated method stub
		 dao.deletePatient(id);
	}

	@Override
	public void deleteMedicament(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteMedicament(id);
	}

	@Override
	public void deleteMedecin(Integer id) {
		// TODO Auto-generated method stub
		dao.deleteMedecin(id);
	}



}
