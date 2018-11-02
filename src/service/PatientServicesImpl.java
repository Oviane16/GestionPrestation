package service;

import java.util.List;

import dao.MedecinDao;
import dao.MedecinDaoImplement;
import dao.PatientDao;
import dao.PatientDaoImpl;
import dao.entities.Patient;

public class PatientServicesImpl implements PatientServices {

	PatientDao dao = new PatientDaoImpl();
	@Override
	public Patient add(Patient p) {
		return dao.add(p);
	}
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Patient> findAll() {
		return dao.findAll();
	}

	@Override
	public Patient findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Patient edite(Patient m, Integer id) {
		return dao.edite(m, id);
	}
	@Override
	public Patient findByNum(String num) {
		return dao.findByNum(num);
	}
	@Override
	public List<Patient> patientParMedec(Integer idMedec) {
		return dao.patientParMedec(idMedec);
	}

}
