package controller;

import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.xhtmlrenderer.pdf.ITextRenderer;

import dao.entities.Medecin;
import dao.entities.Ordonnance;
import dao.entities.Patient;
import service.MedecinServices;
import service.MedecinServicesImpl;
import service.OrdonnanceServices;
import service.OrdonnanceServicesImpl;
import service.PatientServices;
import service.PatientServicesImpl;

@ManagedBean(name="FactureController")
@RequestScoped
public class FactureController{
	private String numeroPatient,nom,dateDe,dateA;
	private Integer total=0,taux;
	private List<SelectItem> listNumPatient;
	private Patient p;
	private List<Ordonnance> listOrdonnance;
	private MedecinServices medecinService = new MedecinServicesImpl();
	private PatientServices patientService = new PatientServicesImpl();
	private OrdonnanceServices ordonnanceService = new OrdonnanceServicesImpl();
	@PostConstruct
	public void initBean() {
		listNumPatient = new ArrayList<>();
		List<Patient> listPatient = patientService.findAll();
		for(Patient o:listPatient)
		{
			listNumPatient.add(new SelectItem(o.getId(),o.getNumero()));
		}
	}
	public String getNumeroPatient() {
		return numeroPatient;
	}
	public void setNumeroPatient(String numeroPatient) {
		this.numeroPatient = numeroPatient;
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
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getTaux() {
		return taux;
	}
	public void setTaux(Integer taux) {
		this.taux = taux;
	}
	public List<SelectItem> getListNumPatient() {
		return listNumPatient;
	}
	public void setListNumPatient(List<SelectItem> listNumPatient) {
		this.listNumPatient = listNumPatient;
	}
	public Patient getP() {
		return p;
	}
	public void setP(Patient p) {
		this.p = p;
	}
	public List<Ordonnance> getListOrdonnance() {
		return listOrdonnance;
	}
	public void setListOrdonnance(List<Ordonnance> listOrdonnance) {
		this.listOrdonnance = listOrdonnance;
	}
	public PatientServices getPatientService() {
		return patientService;
	}
	public void setPatientService(PatientServices patientService) {
		this.patientService = patientService;
	}
	
	public void patientFacture(){
		Integer idPatient = Integer.parseInt(numeroPatient);
		listOrdonnance =ordonnanceService.patientFacture(idPatient,dateA,dateDe);
		
	}
	public void total()
	{
		Integer idPatient = Integer.parseInt(numeroPatient);
		Integer montant;
		montant =0;
		
		for(Ordonnance o:ordonnanceService.patientConsMedec(idPatient,dateA,dateDe))
		{
			montant =montant + o.getMedicament().getPu();
		}
		total = montant;
	}
	public void infoPatient()
	{
		Integer idMedec = Integer.parseInt(numeroPatient);
		p = patientService.findById(idMedec);
	}
	public void imprimer()
	{
		Integer idPatient = Integer.parseInt(numeroPatient);
		System.out.println(idPatient);
		listOrdonnance =ordonnanceService.patientFacture(idPatient,dateA,dateDe);
		p = patientService.findById(idPatient);
	
		  FacesContext facesContext = FacesContext.getCurrentInstance();
		    ExternalContext externalContext = facesContext.getExternalContext();
		    HttpSession session = (HttpSession) externalContext.getSession(true);
		//	String url = "http://localhost:8080/Prestation/faces/pdf.xhtml:jsessionid="+session.getId()+"?pdf=true";
		 String url = "http://localhost:8080/Prestation/faces/pdf.xhtml;JSESSIONID="+ session.getId()+"?pdf=true";
		    try {
		    ITextRenderer renderer = new ITextRenderer();
		    renderer.setDocument(new URL(url).toString());
		    renderer.layout();
		    HttpServletResponse response = (HttpServletResponse) externalContext.getResponse();
		    response.reset();
		    response.setContentType("application/pdf");
		    response.setHeader("Content-Disposition","C://user//first.pdf");
		    OutputStream browserStream = response.getOutputStream();
		    renderer.createPDF(browserStream);
		    browserStream.close();
		    session.invalidate();
		    } catch (Exception ex) {
		       ex.printStackTrace();
		    }
		    facesContext.responseComplete();
	}

}
