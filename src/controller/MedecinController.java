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
import service.MedecinServices;
import service.MedecinServicesImpl;
import service.OrdonnanceServices;
import service.OrdonnanceServicesImpl;

@ManagedBean(name="MedecinController")
@RequestScoped
public class MedecinController {
	private String nom;
	private Integer tauxJournalier,idMedec;
	private String numero;
	private List<Medecin> medecinList;
	private MedecinServices medecinservice = new MedecinServicesImpl();
	private OrdonnanceServices ordonnanceService = new OrdonnanceServicesImpl();
	public Logger log = Logger.getLogger(MedecinController.class);

	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Integer getIdMedec() {
		return idMedec;
	}
	public void setIdMedec(Integer idMedec) {
		this.idMedec = idMedec;
	}
	public List<Medecin> getMedecinList() {
		return medecinList;
	}
	public void setMedecinList(List<Medecin> medecinList) {
		this.medecinList = medecinList;
	}
	@PostConstruct
	public void initBean() {
		medecinList =medecinservice.findAll();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Integer getTauxJournalier() {
		return tauxJournalier;
	}
	public void setTauxJournalier(Integer tauxJournalier) {
		this.tauxJournalier = tauxJournalier;
	}
	public void ajoutMedecin()
	{
		Medecin medec = new Medecin();
		medec.setNom(this.nom);
		medec.setTauxJournalier(this.tauxJournalier);
		medecinservice.add(medec);
		medecinList =medecinservice.findAll();

	}
	public void delete(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 

		medecinservice.delete(id); 

		medecinList =medecinservice.findAll();
	}
	public void edite(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Medecin m = new Medecin();
		m.setNom(this.nom);
		m.setTauxJournalier(this.tauxJournalier);
		medecinservice.edite(m,id); 
		medecinList =medecinservice.findAll();
	}
	public void clean(ActionEvent e)
	{
		setNom(null);
		setTauxJournalier(null);
	}
	public void findByid(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Medecin m = new Medecin();
		m = medecinservice.findById(id);
		nom= m.getNom();
		tauxJournalier = m.getTauxJournalier();
		idMedec= m.getId();
		numero = m.getNumero();
	}
	}
