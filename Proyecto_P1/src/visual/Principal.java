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
import java.time.LocalDate;
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
import javax.swing.border.BevelBorder;
import java.awt.Toolkit;

public class Principal extends JFrame {

	private JPanel contentPane;
	private static JPanel panelBarras;
	private static CategoryDataset datasetBarra;
	private static PieDataset datasetPastel;
	private static JFreeChart chartBarra;
	private static JFreeChart chartPastel;
	private static JPanel panelPastel;
	private JLabel lblYear;
	private JLabel lblhora;
	private Dimension dim;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(new SyntheticaPlainLookAndFeel());

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
		setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/img/icon.png")));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (JOptionPane.showConfirmDialog(null, "�Desea guardar los nuevos cambios en la bolsa laboral?",
						"Atenci�n Requerida", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					ProgressBar progress = new ProgressBar(1);
					progress.setVisible(true);
					progress.setLocationRelativeTo(null);
					BolsaLaboral.getInstance().esribirBolsa();
					dispose();
				} else {
					dispose();

				}
			}
		});
		BolsaLaboral.getInstance().leerBolsa();
		setBackground(new Color(248, 248, 255));
		setResizable(false);
		setTitle("Man Power Group");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		mntmRealizarMacheo.setIcon(new ImageIcon(Principal.class.getResource("/img/pareo.png")));
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

		JMenu mnNewMenu = new JMenu("Exportar");
		mnNewMenu.setIcon(new ImageIcon(Principal.class.getResource("/img/exportar.png")));
		mnMacheo.add(mnNewMenu);

		JMenuItem mntmExportarEmpresa = new JMenuItem("Exportar Empresa");
		mntmExportarEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/addEmpresa.png")));
		mntmExportarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportarEmpresa imem = new ImportarEmpresa();
				imem.setModal(true);
				imem.setLocationRelativeTo(null);
				imem.setVisible(true);
			}
		});
		mnNewMenu.add(mntmExportarEmpresa);

		JMenuItem mntmExportarSolicitud = new JMenuItem("Exportar Solicitante");
		mntmExportarSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/persona.png")));
		mntmExportarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ImportarEmpleado im;
				try {
					im = new ImportarEmpleado();
					im.setModal(true);
					im.setLocationRelativeTo(null);
					im.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnNewMenu.add(mntmExportarSolicitud);
		
		JMenuItem mntmGuardarTodo = new JMenuItem("Guardar Todo");
		mntmGuardarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BolsaLaboral.getInstance().esribirBolsa();
				ProgressBar progress = new ProgressBar(4);
				progress.setVisible(true);
				progress.setLocationRelativeTo(null);
				
				
			}
		});
		mntmGuardarTodo.setIcon(new ImageIcon(Principal.class.getResource("/img/guardar.png")));
		mnMacheo.add(mntmGuardarTodo);

		JSeparator separator = new JSeparator();
		mnMacheo.add(separator);

		JMenuItem mntmCerrar = new JMenuItem("Salir");
		mntmCerrar.setIcon(new ImageIcon(Principal.class.getResource("/img/cancelar.png")));
		mntmCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "�Desea guardar los nuevos cambios en la bolsa laboral?",
						"Atenci�n Requerida", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

					ProgressBar progress = new ProgressBar(1);
					progress.setVisible(true);
					progress.setLocationRelativeTo(null);
					BolsaLaboral.getInstance().esribirBolsa();
					dispose();
				} else {
					dispose();

				}
			}
		});
		mnMacheo.add(mntmCerrar);
		mnCandidatos.setFont(new Font("Segoe UI", Font.BOLD, 13));
		mnCandidatos.setIcon(new ImageIcon(Principal.class.getResource("/img/Solicitante24.png")));
		menuBar.add(mnCandidatos);

		JMenuItem mntmRegistrarCandidato = new JMenuItem("Registrar Solicitante");
		mntmRegistrarCandidato.setIcon(new ImageIcon(Principal.class.getResource("/img/agregarSolicitante.png")));
		mntmRegistrarCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitate soli = new InsertarSolicitate("Insertar Solicitante", false, null, null);

				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnCandidatos.add(mntmRegistrarCandidato);

		JMenuItem mntmListarCandidatos = new JMenuItem("Listar Solicitante");
		mntmListarCandidatos.setIcon(new ImageIcon(Principal.class.getResource("/img/Listas.png")));
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
		mntmRegistrarEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/addEmpresa.png")));
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
		mntmListarEmpresas.setIcon(new ImageIcon(Principal.class.getResource("/img/Listas.png")));
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
		mntmRegistrarSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/addSolicitud.png")));
		mntmRegistrarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitud solicitud = new InsertarSolicitud(null);
				solicitud.setModal(true);
				solicitud.setVisible(true);
			}
		});
		mnSolicitud.add(mntmRegistrarSolicitud);

		JMenuItem mntmListarSolicitud = new JMenuItem("Listar Solicitud");
		mntmListarSolicitud.setIcon(new ImageIcon(Principal.class.getResource("/img/Listas.png")));
		mntmListarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListarSolicitud soli = new ListarSolicitud();
				soli.setModal(true);
				soli.setVisible(true);
			}
		});
		mnSolicitud.add(mntmListarSolicitud);

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
		mntmReporteDeEmpresa.setIcon(new ImageIcon(Principal.class.getResource("/img/addEmpresa.png")));
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
		mntmReporteDeSolicitudes.setIcon(new ImageIcon(Principal.class.getResource("/img/addSolicitud.png")));
		mntmReporteDeSolicitudes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ReporteSolicitud soli = new ReporteSolicitud();
				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnReporte.add(mntmReporteDeSolicitudes);
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
		panelBarras.setBounds(10, 250, 613, 294);
		panel.add(panelBarras);
		panelBarras.setLayout(null);

		JLabel lblCharVacio = new JLabel("        No hay solicitantes desempledos");
		lblCharVacio.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblCharVacio.setBounds(10, 22, 559, 261);
		panelBarras.add(lblCharVacio);

		panelPastel = new JPanel();
		panelPastel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelPastel.setBounds(667, 250, 613, 294);
		panel.add(panelPastel);
		panelPastel.setLayout(null);

		JLabel lblNoHayEmpledos = new JLabel("                        No hay empledos");
		lblNoHayEmpledos.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNoHayEmpledos.setBounds(10, 22, 559, 261);
		panelPastel.add(lblNoHayEmpledos);

		lblhora = new JLabel("");
		lblhora.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblhora.setBounds(10, 595, 140, 39);
		panel.add(lblhora);
		
		lblYear = new JLabel("");
		lblYear.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblYear.setBounds(105, 595, 116, 39);
		panel.add(lblYear);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(0, 595, 1290, 58);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Principal.class.getResource("/img/Manpower.png")));
		label.setBounds(322, 26, 645, 182);
		panel.add(label);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 216, 1270, 12);
		panel.add(separator_1);
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

	public void reloj() {

		Thread reloj = new Thread() {
			public void run() {
				try {
					for (;;) {
						Calendar calen = new GregorianCalendar();
						int dia = calen.get(Calendar.DAY_OF_WEEK_IN_MONTH);
						int diaSemana = calen.get(Calendar.DAY_OF_WEEK);
						int mes = calen.get(Calendar.MONTH + 1);
						int minutos = calen.get(Calendar.MINUTE);
						int hora = calen.get(Calendar.HOUR);
						int sec = calen.get(Calendar.SECOND);
						int meri = calen.get(Calendar.AM_PM);
						LocalDate date = LocalDate.now();
						int anno = date.getYear();
						int mess= date.getMonthValue();
						int dias = date.getDayOfMonth();
						String realmes = "";
						String merid = "";
						if (meri == 1) {
							merid = " PM";
						} else {
							merid = " AM";
						}
						if (mess == 1) {
							realmes = "Enero";
						}
						if (mess == 1) {
							realmes = "Enero";
						}
						if (mess == 2) {
							realmes = "Febreo";
						}
						if (mess == 3) {
							realmes = "Marzo";
						}
						if (mess == 4) {
							realmes = "Abril";
						}
						if (mess == 5) {
							realmes = "Mayo";
						}
						if (mess == 6) {
							realmes = "Junio";
						}
						if (mess == 7) {
							realmes = "Julio";
						}
						if (mess == 8) {
							realmes = "Agosto";
						}
						if (mess == 9) {
							realmes = "Septiembre";
						}
						if (mess == 10) {
							realmes = "Octubre";
						}
						if (mess == 11) {
							realmes = "Novienbre";
						}
						if (mess == 12) {
							realmes = "Diciembre";
						}
						lblhora.setText(hora + ":" + minutos + ":" + sec + merid);
						lblYear.setText(dias+", "+realmes+", "+anno);
						

						sleep(1000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		reloj.start();

	}
}
