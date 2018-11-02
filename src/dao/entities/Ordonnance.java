package dao.entities;
// Generated 23 sept. 2017 09:52:10 by Hibernate Tools 5.2.5.Final

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Ordonnance generated by hbm2java
 */
@Entity
@Table(name = "ordonnance", catalog = "prestation")
public class Ordonnance implements java.io.Serializable {

	private Integer id;
	private Medecin medecin;
	private Medicament medicament;
	private Patient patient;
	private String numero;
	private Integer nbJour;
	private Date dateCons;

	public Ordonnance() {
	}

	public Ordonnance(Medecin medecin, Medicament medicament, Patient patient, String numero, Integer nbJour,
			Date dateCons) {
		this.medecin = medecin;
		this.medicament = medicament;
		this.patient = patient;
		this.numero = numero;
		this.nbJour = nbJour;
		this.dateCons = dateCons;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medecinid")
	public Medecin getMedecin() {
		return this.medecin;
	}

	public void setMedecin(Medecin medecin) {
		this.medecin = medecin;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "medicamentid")
	public Medicament getMedicament() {
		return this.medicament;
	}

	public void setMedicament(Medicament medicament) {
		this.medicament = medicament;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patientid")
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Column(name = "numero")
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "nbJour")
	public Integer getNbJour() {
		return this.nbJour;
	}

	public void setNbJour(Integer nbJour) {
		this.nbJour = nbJour;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "dateCons", length = 10)
	public Date getDateCons() {
		return this.dateCons;
	}

	public void setDateCons(Date dateCons) {
		this.dateCons = dateCons;
	}

}
