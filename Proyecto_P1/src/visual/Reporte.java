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
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Reporte extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static CategoryDataset datasetBarra;
	private static JPanel BarrasSoli;
	private static JPanel pastel;
	private static PieDataset datasetPastel;
	private static JFreeChart chartPastel;
	private static JFreeChart chartBarra;
	private Dimension dim;
	private static JPanel Panel_Solicitante;
	private JTextField txtrnc;
	private static String rnc;

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
		setBounds(100, 100, 772, 473);
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
		Panel_Solicitante.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graficos Solicitantes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		Panel_Solicitante.setBounds(10, 83, 736, 321);
		contentPanel.add(Panel_Solicitante);
		Panel_Solicitante.setLayout(null);
		
		BarrasSoli = new JPanel();
		BarrasSoli.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graficos Desempleados por tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		BarrasSoli.setBounds(10, 52, 352, 258);
		Panel_Solicitante.add(BarrasSoli);
		BarrasSoli.setLayout(null);
		
		pastel = new JPanel();
		pastel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Graficos empleados por tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pastel.setBounds(379, 52, 347, 258);
		Panel_Solicitante.add(pastel);
		
		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setBounds(20, 27, 46, 14);
		Panel_Solicitante.add(lblRnc);
		
		txtrnc = new JTextField();
		txtrnc.setBounds(76, 24, 121, 21);
		Panel_Solicitante.add(txtrnc);
		txtrnc.setColumns(10);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rnc = txtrnc.getText();
			}
		});
		btnNewButton.setBounds(207, 23, 25, 23);
		Panel_Solicitante.add(btnNewButton);
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
		
		actualizarChart();
		actualizarPastel();
	}

	public static void actualizarChart() {
		BarrasSoli.removeAll();
		BarrasSoli.revalidate();
		datasetBarra = creadorCategoria();
		chartBarra = creadorGraficoB(datasetBarra, "Solicitudes de una empresa por tipo");
		BarrasSoli.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartBarra);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		BarrasSoli.add(chartPanel,BorderLayout.CENTER);
		BarrasSoli.repaint();

	}
	
	public static void actualizarPastel(){
		pastel.removeAll();
		pastel.revalidate();
		datasetPastel = dataSetPastel();
		chartPastel = creadorGraficoP(datasetPastel, "Trabajadores Contratados por Tipo");
		pastel.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartPastel);
	    chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
	    pastel.add(chartPanel, BorderLayout.CENTER);
	    pastel.repaint(); 
		
	}

	public static JFreeChart creadorGraficoB(CategoryDataset dataSet, String titulo) {
		JFreeChart grafico = ChartFactory.createBarChart(titulo, "Tipo de Solicitante", "Catidad Solicitudes", dataSet,
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
		setter.setValue(BolsaLaboral.getInstance().CantSoliO(rnc), "Tipo de Solicitante", "Obreros");
		setter.setValue(BolsaLaboral.getInstance().CantSoliU(rnc), "Tipo de Solicitante", "Universitarios");
		setter.setValue(BolsaLaboral.getInstance().CantSoliT(rnc), "Tipo de Solicitante", "Técnicos");
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
