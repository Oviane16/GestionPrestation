package dao;

import java.util.List;

import dao.entities.Ordonnance;
import dao.entities.Patient;

public interface OrdonnanceDao  {
	
	public Ordonnance add(Ordonnance m);
	
	public Ordonnance edite(Ordonnance ordo,Integer id);
	
	public void delete(Integer id);
	
	public List<Ordonnance> findAll();
	
	public Ordonnance findById(Integer id);
	
	public List<Ordonnance> patientParMedec(Integer idMedec,String dateA,String dateDe);
	
	public List<Ordonnance> patientConsMedec(Integer idPatient,String dateA,String dateDe);
	
	public List<Ordonnance> patientFacture(Integer idPatient,String dateA,String dateDe);
	
	public List<Ordonnance> perstationGraphe(Integer idPatient);
	
	public void deletePatient(Integer id);
	public void deleteMedicament(Integer id);
	public void deleteMedecin(Integer id);
}
