package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.logging.Logger;

import dao.entities.Medecin;
import dao.entities.Medicament;
import dao.entities.Ordonnance;
import dao.entities.Patient;
import service.MedecinServices;
import service.MedecinServicesImpl;
import service.MedicamentServices;
import service.MedicamentServicesImpl;
import service.OrdonnanceServices;
import service.OrdonnanceServicesImpl;
import service.PatientServices;
import service.PatientServicesImpl;

@ManagedBean(name="OrdonnanceController")
@RequestScoped
public class OrdonnanceController {
	private String nomPatient,numeroPatient,numeroMedec,numeroMedic;
	private Integer nbJour,idPatient,idOrdo;
	private List<SelectItem> listNumMedec;
	private List<SelectItem> listNumMedic;
	private List<SelectItem> listNumPatient;
	private List<Ordonnance> listOrdonnance;
	private PatientServices patientService = new PatientServicesImpl();
	private MedecinServices medecinService = new MedecinServicesImpl();
	private MedicamentServices medicamentServices = new MedicamentServicesImpl();
	private OrdonnanceServices ordonnanceServices = new OrdonnanceServicesImpl();
	@PostConstruct
	public void initBean() {
		listNumMedec = new ArrayList<>();
		List<Medecin> listMedecin = medecinService.findAll();
		for(Medecin o:listMedecin)
		{
			listNumMedec.add(new SelectItem(o.getId(),o.getNumero()));
		}
		listNumMedic = new ArrayList();
		List<Medicament> listMedic = medicamentServices.findAll();
		for(Medicament medic:listMedic)
		{
			listNumMedic.add(new SelectItem(medic.getId(),medic.getNumero()));
		}
		listOrdonnance =ordonnanceServices.findAll();
	/*	List<Ordonnance> list = listOrdonnance;
		Ordonnance o = new Ordonnance();
		o = list.get(0);
		System.out.println(o.getNumero());
		System.out.println(o.getMedecin().getNom()); */
	}
	
	
	
	public List<Ordonnance> getListOrdonnance() {
		return listOrdonnance;
	}



	public void setListOrdonnance(List<Ordonnance> listOrdonnance) {
		this.listOrdonnance = listOrdonnance;
	}



	public Integer getIdPatient() {
		return idPatient;
	}


	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}


	public Integer getNbJour() {
		return nbJour;
	}


	public void setNbJour(Integer nbJour) {
		this.nbJour = nbJour;
	}


	public List<SelectItem> getListNumMedic() {
		return listNumMedic;
	}


	public void setListNumMedic(List<SelectItem> listNumMedic) {
		this.listNumMedic = listNumMedic;
	}


	public String getNumeroMedic() {
		return numeroMedic;
	}


	public void setNumeroMedic(String numeroMedic) {
		this.numeroMedic = numeroMedic;
	}


	public String getNumeroMedec() {
		return numeroMedec;
	}

	public void setNumeroMedec(String numeroMedec) {
		this.numeroMedec = numeroMedec;
	}

	public List<SelectItem> getListNumMedec() {
		return listNumMedec;
	}

	public void setListNumMedec(List<SelectItem> listNumMedec) {
		this.listNumMedec = listNumMedec;
	}
	
	public String getNomPatient() {
		return nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public String getNumeroPatient() {
		return numeroPatient;
	}

	public void setNumeroPatient(String numeroPatient) {
		this.numeroPatient = numeroPatient;
	}

	public String consultation()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Patient p = new Patient();
		p = patientService.findById(id);
		this.nomPatient = p.getNom(); 
		this.numeroPatient = p.getNumero();
		this.idPatient = id;
		return "ordonnance"; 
	}
	public void add()
	{

			Ordonnance ordo = new Ordonnance();
			ordo.setNbJour(this.nbJour);
			Medicament medic = new Medicament();
			medic = medicamentServices.findById(Integer.parseInt(this.numeroMedic));
			Patient p = new Patient();
			p = patientService.findById(this.idPatient);
			Medecin m = new Medecin();
			m = medecinService.findById(Integer.parseInt(this.numeroMedec));
			ordo.setMedecin(m);
			ordo.setMedicament(medic);
			ordo.setPatient(p);
			ordonnanceServices.add(ordo);
			listOrdonnance =ordonnanceServices.findAll(); 
	}
	public void findById()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Ordonnance o = new Ordonnance();
		o = ordonnanceServices.findById(id);
		
/*		listNumPatient = new ArrayList<>();
		List<Patient> listPatient = patientService.findAll();
		for(Patient p:listPatient)
		{
			listNumMedec.add(new SelectItem(p.getId(),p.getNumero()));
		} */
		
		
		listNumMedec = new ArrayList<>();
		List<Medecin> listMedecin = medecinService.findAll();
		for(Medecin m:listMedecin)
		{
			listNumMedec.add(new SelectItem(m.getId(),m.getNumero()));
		}
		listNumMedic = new ArrayList();
		List<Medicament> listMedic = medicamentServices.findAll();
		for(Medicament medic:listMedic)
		{
			listNumMedic.add(new SelectItem(medic.getId(),medic.getNumero()));
		}
		nbJour = o.getNbJour();
		idOrdo = o.getId();
		numeroPatient = o.getPatient().getNumero();
		numeroMedec = o.getMedecin().getNumero();numeroMedic= o.getMedicament().getNumero() ;
		idPatient = o.getPatient().getId();
	}
	public void delete()
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		ordonnanceServices.delete(id);
		listOrdonnance =ordonnanceServices.findAll();
	}
	public void edit(ActionEvent e)
	{
		System.out.println(numeroMedec);
		System.out.println(numeroMedic);
		System.out.println(numeroMedec);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Ordonnance o = new Ordonnance();
		Medicament medic = new Medicament();
		Medecin m = new Medecin();
		Patient p = new Patient();
		medic = medicamentServices.findById(Integer.parseInt(this.numeroMedic));
		p = patientService.findById(this.idPatient);
		m = medecinService.findById(Integer.parseInt(this.numeroMedec));
		o.setMedecin(m);
		o.setMedicament(medic);
		o.setPatient(p);
		o.setNbJour(nbJour);
		ordonnanceServices.edite(o, id);
		listOrdonnance =ordonnanceServices.findAll();
	}



	public Integer getIdOrdo() {
		return idOrdo;
	}



	public void setIdOrdo(Integer idOrdo) {
		this.idOrdo = idOrdo;
	}



	public List<SelectItem> getListNumPatient() {
		return listNumPatient;
	}



	public void setListNumPatient(List<SelectItem> listNumPatient) {
		this.listNumPatient = listNumPatient;
	}
	
	
}
