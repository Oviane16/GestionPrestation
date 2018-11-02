package controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import dao.entities.Medecin;
import dao.entities.Ordonnance;
import service.MedecinServices;
import service.MedecinServicesImpl;
import service.OrdonnanceServices;
import service.OrdonnanceServicesImpl;
import service.PatientServices;
import service.PatientServicesImpl;

@ManagedBean(name="EtatController")
@RequestScoped
public class EtatController {
	private String numeroMedec,nom,dateDe,dateA;
	private Integer total=0,taux;
	private List<SelectItem> listNumMedec;
	private Medecin medec;
	private List<Ordonnance> listOrdonnance;
	private MedecinServices medecinService = new MedecinServicesImpl();
	private PatientServices patientService = new PatientServicesImpl();
	private OrdonnanceServices ordonnanceService = new OrdonnanceServicesImpl();
	@PostConstruct
	public void initBean() {
		listNumMedec = new ArrayList<>();
		List<Medecin> listMedecin = medecinService.findAll();
		for(Medecin o:listMedecin)
		{
			listNumMedec.add(new SelectItem(o.getId(),o.getNumero()));
		}
	}
	
	
	
	public String getDateDe() {
		return dateDe;
	}



	public void setDateDe(String dateDe) {
		this.dateDe = dateDe;
	}



	public String getDateA() {
		return dateA;
	}



	public void setDateA(String dateA) {
		this.dateA = dateA;
	}



	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public Integer getTaux() {
		return taux;
	}


	public void setTaux(Integer taux) {
		this.taux = taux;
	}


	public Medecin getMedec() {
		return medec;
	}


	public void setMedec(Medecin medec) {
		this.medec = medec;
	}


	public Integer getTotal() {
		return total;
	}


	public void setTotal(Integer total) {
		this.total = total;
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
	
	
	public List<Ordonnance> getListOrdonnance() {
		return listOrdonnance;
	}

	public void setListOrdonnance(List<Ordonnance> listOrdonnance) {
		this.listOrdonnance = listOrdonnance;
	}

	public void patientParMedec(){
		Integer idMedec = Integer.parseInt(numeroMedec);
		Integer montant;
		listOrdonnance =ordonnanceService.patientParMedec(idMedec,dateA,dateDe);
		
	}
	public void total()
	{
		Integer idMedec = Integer.parseInt(numeroMedec);
		Integer montant;
		montant =0;
		
		for(Ordonnance o:ordonnanceService.patientParMedec(idMedec,dateA,dateDe))
		{
			montant =montant + o.getMedicament().getPu();
		}
		total = montant;
	}
	public void infoMedec()
	{
		Integer idMedec = Integer.parseInt(numeroMedec);
		medec = medecinService.findById(idMedec);
		nom = medec.getNom();
		taux = medec.getTauxJournalier();
	}
	
}
