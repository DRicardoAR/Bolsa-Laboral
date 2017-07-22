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
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale.Category;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

import de.javasoft.plaf.synthetica.SyntheticaPlainLookAndFeel;
import logica.BolsaLaboral;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static JPanel panelBarras;
	private static CategoryDataset datasetBarra;
	private static PieDataset datasetPastel;
	private static JFreeChart chartBarra;
	private static JFreeChart chartPastel;
	private static JPanel panelPastel;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());
					BolsaLaboral.getInstance().leerBolsa();
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
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "�Desea guardar los nuevos cambios de la biblioteca?",
						"Atenci�n Requerida", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					BolsaLaboral.getInstance().esribirBolsa();
					dispose();
				}else{
					dispose();
				}
			}
		});
		setBackground(new Color(248, 248, 255));
		setResizable(false);
		setTitle("Bolsa Laboral");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1306, 727);
		dim = super.getToolkit().getScreenSize();
		super.setSize(dim.width - 60, dim.height - 50);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnCandidatos = new JMenu("Solicitante");
		mnCandidatos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnCandidatos.setIcon(new ImageIcon(Principal.class.getResource("/img/24.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnCandidatos.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitante24.png")));
			}
		});
		mnCandidatos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnCandidatos.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitante24.png")));
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
		mnEmpresa.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/edificio.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/empresa24.png")));
			}
		});
		mnEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/empresa24.png")));
		mnEmpresa.setFont(new Font("Segoe UI", Font.BOLD, 13));
		menuBar.add(mnEmpresa);

		JMenuItem mntmRegistrarEmpresa = new JMenuItem("Registrar Empresa");
		mntmRegistrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarEmpresa empre;
				try {
					empre = new InsertarEmpresa("Insertar Empresa", false, null, null);
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
				lista.setLocationRelativeTo(null);
				lista.setVisible(true);
			}
		});
		mnEmpresa.add(mntmListarEmpresas);
		JMenu mnSolicitud = new JMenu("Solicitudes Empresariales");
		mnSolicitud.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitud.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitud24.png")));
			}
		});
		mnSolicitud.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitud24.png")));
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
		JMenu mnMacheo = new JMenu("Ubicaci\u00F3n Laboral");
		mnMacheo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnMacheo.setIcon(new ImageIcon(Principal.class.getResource("/img/macheo.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnMacheo.setIcon(new ImageIcon(Principal.class.getResource("/img/pareo24.png")));
			}
		});
		mnMacheo.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnMacheo.setIcon(new ImageIcon(Principal.class.getResource("/img/pareo24.png")));
		menuBar.add(mnMacheo);

		JMenuItem mntmRealizarMacheo = new JMenuItem("Realizar Pareo");
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

		JMenu mnReporte = new JMenu("Reporte");
		mnReporte.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnReporte.setIcon(new ImageIcon(Principal.class.getResource("/img/reportar.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnReporte.setIcon(new ImageIcon(Principal.class.getResource("/img/reportar24.png")));
			}
		});
		mnReporte.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnReporte.setIcon(new ImageIcon(Principal.class.getResource("/img/reportar24.png")));
		menuBar.add(mnReporte);

		JMenuItem mntmReporteDeEmpresa = new JMenuItem("Reporte de Empresa");
		mntmReporteDeEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarEmpleados emple = new ListarEmpleados();
				emple.setModal(true);
				emple.setLocationRelativeTo(null);
				emple.setVisible(true);

			}
		});
		mnReporte.add(mntmReporteDeEmpresa);

		JMenuItem mntmReporteDeSolicitudes = new JMenuItem("Reporte de Solicitudes");
		mntmReporteDeSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReporteSolicitud soli = new ReporteSolicitud();
				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnReporte.add(mntmReporteDeSolicitudes);

		JMenu mnImportar = new JMenu("Exportar");
		mnImportar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnImportar.setIcon(new ImageIcon(Principal.class.getResource("/img/export.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnImportar.setIcon(new ImageIcon(Principal.class.getResource("/img/export24.png")));
			}
		});
		mnImportar.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnImportar.setIcon(new ImageIcon(Principal.class.getResource("/img/export24.png")));
		menuBar.add(mnImportar);

		JMenuItem mntmImportarEmpresa = new JMenuItem("Exportar Empresa");
		mntmImportarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportarEmpresa imem = new ImportarEmpresa();
				imem.setModal(true);
				imem.setLocationRelativeTo(null);
				imem.setVisible(true);
			}
		});
		mnImportar.add(mntmImportarEmpresa);

		JMenuItem mntmImportarSolicitante = new JMenuItem("Exportar Solicitante");
		mntmImportarSolicitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ImportarEmpleado im = new ImportarEmpleado();
				im.setModal(true);
				im.setLocationRelativeTo(null);
				im.setVisible(true);
			}
		});
		mnImportar.add(mntmImportarSolicitante);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		panelBarras = new JPanel();
		panelBarras.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelBarras.setBounds(44, 11, 579, 294);
		panel.add(panelBarras);
		panelBarras.setLayout(null);

		JLabel lblCharVacio = new JLabel("        No hay solicitantes desempledos");
		lblCharVacio.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCharVacio.setBounds(10, 22, 559, 261);
		panelBarras.add(lblCharVacio);

		panelPastel = new JPanel();
		panelPastel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelPastel.setBounds(667, 11, 579, 294);
		panel.add(panelPastel);
		panelPastel.setLayout(null);

		JLabel lblNoHayEmpledos = new JLabel("                        No hay empledos");
		lblNoHayEmpledos.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNoHayEmpledos.setBounds(10, 22, 559, 261);
		panelPastel.add(lblNoHayEmpledos);
		actualizarChart();
		actualizarPastel();
		reloj();
		// hiloBarras();

	}

	public static void actualizarPastel() {
		panelPastel.removeAll();
		panelPastel.revalidate();
		datasetPastel = dataSetPastel();
		chartPastel = creadorGraficoP(datasetPastel, "Trabajadores Contratados por Tipo");
		panelPastel.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartPastel);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		panelPastel.add(chartPanel, BorderLayout.CENTER);
		panelPastel.repaint();

	}

	public static void actualizarChart() {
		panelBarras.removeAll();
		panelBarras.revalidate();
		datasetBarra = creadorCategoria();
		chartBarra = creadorGraficoB(datasetBarra, "Solicitantes Desempleados");
		panelBarras.setLayout(new BorderLayout(0, 0));
		ChartPanel chartPanel = new ChartPanel(chartBarra);
		chartPanel.setPreferredSize(new java.awt.Dimension(800, 500));
		panelBarras.add(chartPanel, BorderLayout.CENTER);
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

	public static JFreeChart creadorGraficoP(PieDataset dataSet, String titulo) {
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

	public static PieDataset dataSetPastel() {
		DefaultPieDataset result = new DefaultPieDataset();
		if (BolsaLaboral.getInstance().porcientoO() != 0) {
			result.setValue("Obrero", BolsaLaboral.getInstance().porcientoO());
		}
		if (BolsaLaboral.getInstance().porcientoT() != 0) {
			result.setValue("Tecnico", BolsaLaboral.getInstance().porcientoT());
		}
		if (BolsaLaboral.getInstance().porcientoU() != 0) {
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
public void reloj(){
		
		Thread reloj = new Thread(){
		public void run(){
			try {
				for(;;){
				Calendar calen = new GregorianCalendar();
				int dia = calen.get(Calendar.DAY_OF_WEEK_IN_MONTH);
				int mes = calen.get(Calendar.MONTH+1);
				int minutos = calen.get(Calendar.MINUTE);
				int hora = calen.get(Calendar.HOUR);
				int sec = calen.get(Calendar.SECOND);
				int meri = calen.get(Calendar.AM_PM);
				String merid = "";
				if(meri == 1){
					merid = " PM";
				}else {
					merid = " AM";
				}
				//lblClock.setText(hora+":"+minutos+":"+sec+merid);
				
				sleep(1000);}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}	
		};
		reloj.start();
		
	}
}
