package dao.entities;
// Generated 23 sept. 2017 09:52:10 by Hibernate Tools 5.2.5.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Medicament generated by hbm2java
 */
@Entity
@Table(name = "medicament", catalog = "prestation")
public class Medicament implements java.io.Serializable {

	private Integer id;
	private String numero;
	private String design;
	private Integer pu;
	private Set<Ordonnance> ordonnances = new HashSet<Ordonnance>(0);

	public Medicament() {
	}

	public Medicament(String numero, String design, Integer pu, Set<Ordonnance> ordonnances) {
		this.numero = numero;
		this.design = design;
		this.pu = pu;
		this.ordonnances = ordonnances;
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

	@Column(name = "numero")
	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "design")
	public String getDesign() {
		return this.design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	@Column(name = "pu")
	public Integer getPu() {
		return this.pu;
	}

	public void setPu(Integer pu) {
		this.pu = pu;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "medicament")
	public Set<Ordonnance> getOrdonnances() {
		return this.ordonnances;
	}

	public void setOrdonnances(Set<Ordonnance> ordonnances) {
		this.ordonnances = ordonnances;
	}

}
