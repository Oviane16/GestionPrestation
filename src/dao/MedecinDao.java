package dao;

import java.util.List;

import dao.entities.Medecin;

public interface MedecinDao {
	
	public Medecin add(Medecin m);
	
	public Medecin edite(Medecin m,Integer id);
	
	public void delete(Integer id);
	
	public List<Medecin> findAll();
	
	public Medecin findById(Integer id);
	
	public Medecin findByNum(String num);

}
