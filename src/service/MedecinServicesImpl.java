package service;

import java.util.List;

import dao.MedecinDao;
import dao.MedecinDaoImplement;
import dao.entities.Medecin;

public class MedecinServicesImpl implements MedecinServices {

	MedecinDao dao = new MedecinDaoImplement();
	@Override
	public Medecin add(Medecin m) {
		return dao.add(m);
	}
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Medecin> findAll() {
		return dao.findAll();
	}

	@Override
	public Medecin findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Medecin edite(Medecin m, Integer id) {
		return dao.edite(m, id);
	}
	@Override
	public Medecin findByNum(String num) {
		return dao.findByNum(num);
	}

}
