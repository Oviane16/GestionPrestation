package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import dao.entities.Medecin;
import dao.entities.Medicament;

import service.MedicamentServices;
import service.MedicamentServicesImpl;


@ManagedBean(name="MedicamentController")
@RequestScoped
public class MedicamentController {
	private String numero,design;
	private Integer pu,id;
	private List<Medicament> listMedicament;
	private MedicamentServices medicamentService = new MedicamentServicesImpl();

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}


	public String getDesign() {
		return design;
	}


	public void setDesign(String design) {
		this.design = design;
	}


	public Integer getPu() {
		return pu;
	}


	public void setPu(Integer pu) {
		this.pu = pu;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public List<Medicament> getListMedicament() {
		return listMedicament;
	}


	public void setListMedicament(List<Medicament> listMedicament) {
		this.listMedicament = listMedicament;
	}


	@PostConstruct
	public void initBean() {
		listMedicament = medicamentService.findAll();
	}
	public void edite(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		Medicament m = new Medicament();
		m.setDesign(this.design);
		m.setPu(this.pu);
		medicamentService.edite(m,id); 
		listMedicament =medicamentService.findAll();
	}
	public void delete(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id = Integer.parseInt(param.get("id")); 
		medicamentService.delete(id); 
		listMedicament =medicamentService.findAll();
	}
	
	public void add()
	{
		Medicament medic = new Medicament();
		medic.setDesign(this.design);
		medic.setPu(this.pu);
		medicamentService.add(medic);
		listMedicament =medicamentService.findAll();
	}
	public void findByid(ActionEvent e)
	{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> param = fc.getExternalContext().getRequestParameterMap();
		Integer id2 = Integer.parseInt(param.get("id")); 
		Medicament m = new Medicament();
		m = medicamentService.findById(id2);
		design= m.getDesign();
		pu = m.getPu();
		id= m.getId();
		numero = m.getNumero();

	}
}
