package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import logica.BolsaLaboral;

public class Reporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static CategoryDataset datasetBarra;
	private static JFreeChart chartBarra;
	private Dimension dim;
	private static JPanel Panel_Solicitante;

	/*
	public static void main(String[] args) {
		try {
			Reporte dialog = new Reporte();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Reporte() {
		setTitle("Reportes");
		setBounds(100, 100, 772, 487);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 736, 61);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JRadioButton rbtEmpresas = new JRadioButton("Empresas");
		rbtEmpresas.setBounds(102, 17, 109, 23);
		panel.add(rbtEmpresas);
		
		JRadioButton rbtSolicitantes = new JRadioButton("Solicitantes");
		rbtSolicitantes.setBounds(313, 17, 109, 23);
		panel.add(rbtSolicitantes);
		
		JRadioButton rbtSilicitudes = new JRadioButton("Solitudes");
		rbtSilicitudes.setBounds(524, 17, 109, 23);
		panel.add(rbtSilicitudes);
		
		
		
		Panel_Solicitante = new JPanel();
		Panel_Solicitante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graficos", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Panel_Solicitante.setBounds(10, 83, 736, 321);
		contentPanel.add(Panel_Solicitante);
		Panel_Solicitante.setLayout(null);
		
		JPanel BarrasSoli = new JPanel();
		BarrasSoli.setBorder(new TitledBorder(null, "Solicitantes por Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		BarrasSoli.setBounds(10, 19, 352, 291);
		Panel_Solicitante.add(BarrasSoli);
		BarrasSoli.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Solicitantes Contratados por tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(379, 19, 347, 291);
		Panel_Solicitante.add(panel_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void actualizarChart() {
		Panel_Solicitante.removeAll();
        Panel_Solicitante.revalidate();
		
		datasetBarra = creadorCategoria();
		chartBarra = creadorGraficoB(datasetBarra, "Solicitantes Desempleados");
		Panel_Solicitante.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartBarra);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		Panel_Solicitante.add(chartPanel,BorderLayout.CENTER);
		Panel_Solicitante.repaint();

	}

	public static JFreeChart creadorGraficoB(CategoryDataset dataSet, String titulo) {
		JFreeChart grafico = ChartFactory.createBarChart(titulo, "Tipo de Solicitante", "Catidad Desempleados", dataSet,
				PlotOrientation.VERTICAL, false, true, false);
		Color color = new Color(255, 249, 234);
		grafico.setBackgroundPaint(color);
		CategoryPlot plot = (CategoryPlot) grafico.getPlot();
		plot.setForegroundAlpha(0.8f);
		plot.setBackgroundPaint(new Color(254, 253, 241));
		return grafico;
	}
	public static JFreeChart creadorGraficoP(PieDataset dataSet, String titulo){
		JFreeChart chart = ChartFactory.createPieChart3D(titulo, dataSet, true, true, false);
		Color col = new Color(255, 249, 234);
		chart.setBackgroundPaint(col);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(0.5);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
		plot.setBackgroundPaint(new Color(254, 253, 241));
		return chart;
		
	}

	public static CategoryDataset creadorCategoria() {
		DefaultCategoryDataset setter = new DefaultCategoryDataset();
		setter.setValue(BolsaLaboral.getInstance().desempleadoO(), "Tipo de Solicitante", "Obreros");
		setter.setValue(BolsaLaboral.getInstance().desempleadoU(), "Tipo de Solicitante", "Universitarios");
		setter.setValue(BolsaLaboral.getInstance().desempleadoT(), "Tipo de Solicitante", "T�cnicos");
		return setter;
	}
public static PieDataset dataSetPastel(){
	DefaultPieDataset result = new DefaultPieDataset();
	if(BolsaLaboral.getInstance().porcientoO() !=0){
		result.setValue("Obrero", BolsaLaboral.getInstance().porcientoO());
	}
	if(BolsaLaboral.getInstance().porcientoT() != 0){
		result.setValue("Tecnico", BolsaLaboral.getInstance().porcientoT());
	}
	if(BolsaLaboral.getInstance().porcientoU() != 0){
		result.setValue("Universitario", BolsaLaboral.getInstance().porcientoU());
	}
	
	return result;
}
	public void hiloBarras() {
		Thread actualizar = new Thread() {
			public void run() {
				try {
					for (;;) {
						actualizarChart();
						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		};
		actualizar.start();
	}
}
