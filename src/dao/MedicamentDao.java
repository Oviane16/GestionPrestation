package dao;

import java.util.List;

import dao.entities.Medicament;

public interface MedicamentDao {
	
	public Medicament add(Medicament m);
	
	public Medicament edite(Medicament m,Integer id);
	
	public void delete(Integer id);
	
	public List<Medicament> findAll();
	
	public Medicament findById(Integer id);

	public Medicament findByNumero(String num);
}
