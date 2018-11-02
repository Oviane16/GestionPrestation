package service;

import java.util.List;
import dao.entities.Patient;

public interface PatientServices {
	public Patient add(Patient p);
	
	public Patient edite(Patient p,Integer id);
	
	public void delete(Integer id);
	
	public List<Patient> findAll();
	
	public Patient findById(Integer id);
	
	public Patient findByNum(String num);
	
	public List<Patient> patientParMedec(Integer idMedec);
}
