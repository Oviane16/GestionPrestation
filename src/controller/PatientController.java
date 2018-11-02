package controller;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import dao.entities.Medecin;
import dao.entities.Patient;
import service.PatientServices;
import service.PatientServicesImpl;

@ManagedBean(name="PatientController")
@RequestScoped
public class PatientController {
	private String nom,adresse,numero;
	private Integer idPatient;
	private List<Patient> listPatient;
	private PatientServices patientservice = new PatientServicesImpl();


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Integer getIdPatient() {
		return idPatient;
	}

	public void setIdPatient(Integer idPatient) {
		this.idPatient = idPatient;
	}
	

	public List<Patient> getListPatient() {
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	@PostConstruct
	public void initBean() {
		listPatient =patientservice.findAll();
	}
	public String consultation()
	{
		return "ordonnance.xhtml";
	}
	public void add()
	{
		Patient p = new Patient();
		p.setNom(this.nom);
		p.setAdresse(this.adresse);
		patientservice.add(p);
		listPatient =patientservice.findAll();
	}
	public void delete(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		patientservice.delete(id); 
		listPatient =patientservice.findAll();
	}
	public void edite(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Patient p = new Patient();
		p.setNom(this.nom);
		p.setAdresse(this.adresse);
		patientservice.edite(p,id); 
		listPatient =patientservice.findAll();
	}

	public Patient findByid(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Patient p = new Patient();
		p = patientservice.findById(id);
		idPatient = p.getId();
		numero = p.getNumero();
		nom = p.getNom();
		adresse =p.getAdresse();
		return p;
	}
	}
