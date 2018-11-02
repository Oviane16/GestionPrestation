package service;

import java.util.List;

import dao.MedecinDao;
import dao.MedecinDaoImplement;
import dao.MedicamentDao;
import dao.MedicamentDaoImpl;
import dao.PatientDao;
import dao.PatientDaoImpl;
import dao.entities.Medicament;

public class MedicamentServicesImpl implements MedicamentServices {

	MedicamentDao dao = new MedicamentDaoImpl();
	@Override
	public Medicament add(Medicament m) {
		return dao.add(m);
	}
	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public List<Medicament> findAll() {
		return dao.findAll();
	}

	@Override
	public Medicament findById(Integer id) {
		return dao.findById(id);
	}

	@Override
	public Medicament edite(Medicament m, Integer id) {
		return dao.edite(m, id);
	}
	public Medicament findByNumero(String num) {
		return dao.findByNumero(num);
	}

}
