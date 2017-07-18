package visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Paint;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Locale.Category;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import logica.BolsaLaboral;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static JPanel panelBarras;
	private static CategoryDataset datasetBarra;
	private static JFreeChart chartBarra;
	private static JPanel panelPastel;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Bolsa Laboral");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1306, 729);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width-60, dim.height-10);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCandidatos = new JMenu("Solicitante");
		menuBar.add(mnCandidatos);

		JMenuItem mntmRegistrarCandidato = new JMenuItem("Registrar Solicitante");
		mntmRegistrarCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitate soli = new InsertarSolicitate("Insertar Solicitante", false, null);
				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnCandidatos.add(mntmRegistrarCandidato);

		JMenuItem mntmListarCandidatos = new JMenuItem("Listar Solicitante");
		mntmListarCandidatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSolicitante soli = new ListarSolicitante();
				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnCandidatos.add(mntmListarCandidatos);

		JMenu mnEmpresa = new JMenu("Empresa");
		menuBar.add(mnEmpresa);

		JMenuItem mntmRegistrarEmpresa = new JMenuItem("Registrar Empresa");
		mntmRegistrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarEmpresa empre;
				try {
					empre = new InsertarEmpresa();
					empre.setModal(true);
					empre.setLocationRelativeTo(null);
					empre.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		mnEmpresa.add(mntmRegistrarEmpresa);

		JMenuItem mntmListarEmpresas = new JMenuItem("Listar Empresas");
		mntmListarEmpresas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ListarEmpresa lista = new ListarEmpresa();
				lista.setModal(true);
				lista.setVisible(true);
			}
		});
		mnEmpresa.add(mntmListarEmpresas);
		
		JMenu mnSolicitud = new JMenu("Solicitud");
		menuBar.add(mnSolicitud);
		
		JMenuItem mntmRegistrarSolicitud = new JMenuItem("Registrar Solicitud");
		mntmRegistrarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitud solicitud = new InsertarSolicitud(null);
				solicitud.setModal(true);
				solicitud.setVisible(true);
			}
		});
		mnSolicitud.add(mntmRegistrarSolicitud);
		
		JMenuItem mntmListarSolicitud = new JMenuItem("Listar Solicitud");
		mntmListarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSolicitud soli = new ListarSolicitud();
				soli.setModal(true);
				soli.setVisible(true);
			}
		});
		mnSolicitud.add(mntmListarSolicitud);

		JMenu mnMacheo = new JMenu("Macheo");
		menuBar.add(mnMacheo);

		JMenuItem mntmRealizarMacheo = new JMenuItem("Realizar Macheo");
		mntmRealizarMacheo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Macheo macheo;
				try {
					macheo = new Macheo();
					macheo.setModal(true);
					macheo.setLocationRelativeTo(null);
					macheo.setVisible(true);
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		mnMacheo.add(mntmRealizarMacheo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panelBarras = new JPanel();
		panelBarras.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gr\u00E1fico de Desempleado por Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelBarras.setBounds(44, 11, 579, 294);
		panel.add(panelBarras);
		panelBarras.setLayout(null);
		
		JLabel lblCharVacio = new JLabel("        No hay solicitantes desempledos");
		lblCharVacio.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCharVacio.setBounds(10, 22, 559, 261);
		panelBarras.add(lblCharVacio);
		
		panelPastel = new JPanel();
		panelPastel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Gr\u00E1fico de Empleados por Tipo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPastel.setBounds(667, 11, 579, 294);
		panel.add(panelPastel);
		panelPastel.setLayout(null);
		
		JLabel lblNoHayEmpledos = new JLabel("                        No hay empledos");
		lblNoHayEmpledos.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNoHayEmpledos.setBounds(10, 22, 559, 261);
		panelPastel.add(lblNoHayEmpledos);
		//actualizarChart();
		//hiloBarras();
		
	}

	public static void actualizarChart() {
		panelBarras.removeAll();
		panelBarras.revalidate();
		datasetBarra = creadorCategoria();
		chartBarra = creadorGraficoB(datasetBarra, "Solicitantes Desempleados");
		panelBarras.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartBarra);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		panelBarras.add(chartPanel,BorderLayout.CENTER);
		panelBarras.repaint();

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

	public static CategoryDataset creadorCategoria() {
		DefaultCategoryDataset setter = new DefaultCategoryDataset();
		setter.setValue(BolsaLaboral.getInstance().desempleadoO(), "Tipo de Solicitante", "Obreros");
		setter.setValue(BolsaLaboral.getInstance().desempleadoU(), "Tipo de Solicitante", "Universitarios");
		setter.setValue(BolsaLaboral.getInstance().desempleadoT(), "Tipo de Solicitante", "Técnicos");
		return setter;
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
