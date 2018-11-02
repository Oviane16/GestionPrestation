package controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.chartistjsf.model.chart.AspectRatio;
import org.chartistjsf.model.chart.Axis;
import org.chartistjsf.model.chart.AxisType;
import org.chartistjsf.model.chart.ChartSeries;
import org.chartistjsf.model.chart.LineChartModel;
import org.chartistjsf.model.chart.LineChartSeries;
import org.primefaces.event.ItemSelectEvent;

import dao.entities.Medecin;
import dao.entities.Ordonnance;
import service.MedecinServices;
import service.MedecinServicesImpl;
import service.OrdonnanceServices;
import service.OrdonnanceServicesImpl;
@ManagedBean(name="GrapheController")
public class GrapheController {
	private LineChartModel lineModel;
	private OrdonnanceServices ordonnanceService = new OrdonnanceServicesImpl();
	private MedecinServices medecinservice = new MedecinServicesImpl();
	public GrapheController()
	{
		createLineModel();
	}
	
	public void createLineModel()
	{
		lineModel = new LineChartModel();
		lineModel.setAspectRatio(AspectRatio.GOLDEN_SECTION);
		lineModel.setLow(20);
		lineModel.setShowTooltip(true);
		lineModel.setShowLine(true);
		lineModel.setShowPoint(true);
		lineModel.setLineSmooth(true);
		lineModel.setAnimateAdvanced(true);
		List<Ordonnance> listOrdo = ordonnanceService.findAll();
		List<Medecin> listMedec = medecinservice.findAll();
		for(Medecin o:listMedec)
		{
			lineModel.addLabel(o.getNom());
		}
		LineChartSeries series1 = new LineChartSeries();
		series1.setName("Prestation");
		for(Medecin o:listMedec)
		{
			double prestation=0;
			listOrdo = ordonnanceService.perstationGraphe(o.getId());
			for(Ordonnance or:listOrdo)
			{
				prestation = prestation + (Double.parseDouble(or.getNbJour().toString())*Double.parseDouble(or.getMedecin().getTauxJournalier().toString()));
			}
			series1.set(prestation/100000);
		}
		Axis xAxis = lineModel.getAxis(AxisType.X);
		lineModel.addSeries(series1);

		lineModel.setAnimateAdvanced(true);
		lineModel.setShowTooltip(true);
	}
	public void itemSelect(ItemSelectEvent event) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Item selected", "Item Value: "
				+ ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getData().get(event.getItemIndex())
				+ ", Series name:" + ((ChartSeries) lineModel.getSeries().get(event.getSeriesIndex())).getName());

		FacesContext.getCurrentInstance().addMessage(event.getComponent().getClientId(), msg);
	}
	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}
	
}
