package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import logica.BolsaLaboral;
import logica.Empresa;
import logica.Solicitud;
import logica.SolicitudObrero;
import logica.SolicitudTecnico;
import logica.SolicitudUniversitario;
import logica.Universitario;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;

public class InsertarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JRadioButton rbtnObrero;
	private JRadioButton rbtnTecnico;
	private JRadioButton rbtnUniversitario;
	private JRadioButton rbtnPostGradoSi;
	private JRadioButton rbtnPostGradoNo;
	private JRadioButton rbtnReubicacionNo;
	private JRadioButton rbtnReubicacionSi;
	private JRadioButton rbtnVehiculoSi;
	private JRadioButton rbtnVehiculoNo;
	private JPanel panelTecnico;
	private JPanel panelObrero;
	private JPanel panelUniversitario;
	private JPanel PanelGeneral;
	private JFormattedTextField ftxtRNC;
	private JComboBox cbxLicencia;
	private JComboBox cbxContrato;
	private JComboBox cbxLocalidad;
	private JSpinner spnVacantes;
	private JSpinner spnEdadMinima;
	private JSpinner spnEdadMaxima;
	private JComboBox cbxIdioma;
	private JList listIdioma;
	private JList ListHabilidad;
	private JSpinner spnUniversitarioExperiencia;
	private JSpinner spnTecnicoExperiencia;
	private JComboBox cbxCarrera;
	private JComboBox cbxArea;
	private JComboBox cbxHabilidades;
	private JSpinner spnObreroExperiencia;

	private ArrayList<String> misIdiomas = new ArrayList<>();
	private ArrayList<String> misHabilidades = new ArrayList<>();

	private String indexListaIdioma;
	private String indexListaHabilidades;
	private BolsaLaboral bolsa = BolsaLaboral.getInstance();
	private Empresa empresa;
	private Solicitud modificarSoli = null;

	public InsertarSolicitud(Solicitud modi) {
		setBackground(new Color(248, 248, 255));
		getContentPane().setBackground(new Color(248, 248, 255));
		modificarSoli = modi;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if (modificarSoli == null) {

					if (rbtnUniversitario.isSelected()) {
						rbtnUniversitario.setSelected(true);
						panelTecnico.setVisible(false);
						panelObrero.setVisible(false);
					}
					if (rbtnTecnico.isSelected()) {
						rbtnUniversitario.setSelected(false);
						panelTecnico.setVisible(true);
						panelObrero.setVisible(false);
					}
					if (rbtnObrero.isSelected()) {
						rbtnUniversitario.setSelected(false);
						panelTecnico.setVisible(false);
						panelObrero.setVisible(true);
					}

				} else {
					for (String idioma : modificarSoli.getIdiomas()) {
						misIdiomas.add(idioma);
					}
					ftxtRNC.setValue(modi.getEmpresa().getRNC());
					ftxtRNC.setEnabled(false);
					
					txtNombre.setText(modi.getEmpresa().getNombre());
					cbxContrato.setSelectedItem(modi.getTipoContrato());
					if (modi.isMudarse()) {
						rbtnReubicacionSi.setSelected(true);
					} else {
						rbtnReubicacionNo.setSelected(true);
					}
					if (modi.isVehiculoPropio()) {
						rbtnVehiculoSi.setSelected(true);
						cbxLicencia.setSelectedItem(modi.getCategoriaLicencia());
					} else {
						rbtnVehiculoNo.setSelected(true);
					}
					cbxLocalidad.setSelectedItem(modi.getLocalidad());
					spnVacantes.setValue(modi.getCantVacantes());
					spnEdadMinima.setValue(modi.getEdadMin());
					spnEdadMaxima.setValue(modi.getEdadMax());
					cargarIdioma();

					if (modificarSoli instanceof SolicitudUniversitario) {
						rbtnUniversitario.setSelected(true);
						panelUniversitario.setVisible(true);
						panelTecnico.setVisible(false);
						panelObrero.setVisible(false);
						rbtnUniversitario.setEnabled(false);
						rbtnTecnico.setEnabled(false);
						rbtnObrero.setEnabled(false);
						spnUniversitarioExperiencia.setValue(((SolicitudUniversitario) modi).getAnnosExperiencia());
						cbxCarrera.setSelectedItem(((SolicitudUniversitario) modi).getCarrera());
						if (((SolicitudUniversitario) modi).isPostGrado()) {
							rbtnPostGradoSi.setSelected(true);
						} else {
							rbtnPostGradoNo.setSelected(true);
						}

					}
					if (modificarSoli instanceof SolicitudTecnico) {
						rbtnTecnico.setSelected(true);
						panelTecnico.setVisible(true);
						panelUniversitario.setVisible(false);
						panelObrero.setVisible(false);
						rbtnUniversitario.setEnabled(false);
						rbtnTecnico.setEnabled(false);
						rbtnObrero.setEnabled(false);
						spnTecnicoExperiencia.setValue(((SolicitudTecnico) modi).getAnnosExperiencia());
						cbxArea.setSelectedItem(((SolicitudTecnico) modi).getArea());

					}
					if (modificarSoli instanceof SolicitudObrero) {
						for (String habili : ((SolicitudObrero) modificarSoli).getHabilidades()) {
							misHabilidades.add(habili);
						}
						rbtnObrero.setSelected(true);
						panelObrero.setVisible(true);
						panelUniversitario.setVisible(false);
						panelTecnico.setVisible(false);
						rbtnUniversitario.setEnabled(false);
						rbtnTecnico.setEnabled(false);
						rbtnObrero.setEnabled(false);
						spnObreroExperiencia.setValue(((SolicitudObrero) modi).getAnnosExperiencia());
						cargarHabilidades();

					}

				}

			}

		});
		setResizable(false);
		if (modificarSoli == null) {
			setTitle("Insertar Solicitud - Bolsa Laboral");

		} else {
			setTitle("Modificar Solicitud - Bolsa Laboral");

		}
		setResizable(false);
		setModal(true);
		setBounds(100, 100, 547, 598);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 255));
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelEmpresa = new JPanel();
			panelEmpresa.setBackground(new Color(248, 248, 255));
			panelEmpresa.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empresa",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelEmpresa.setBounds(10, 35, 507, 72);
			panel.add(panelEmpresa);
			panelEmpresa.setLayout(null);

			JLabel lblRnc = new JLabel("RNC:");
			lblRnc.setBounds(15, 31, 46, 14);
			panelEmpresa.add(lblRnc);

			try {
				MaskFormatter mascara = new MaskFormatter("##########");
				ftxtRNC = new JFormattedTextField(mascara);
				ftxtRNC.setBackground(new Color(255, 255, 255));
				ftxtRNC.setBounds(81, 27, 120, 23);
				panelEmpresa.add(ftxtRNC);

			} catch (Exception e) {
				// TODO: handle exception
			}

			JButton btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/buscar.png")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String RNC = ftxtRNC.getText();

					empresa = bolsa.RetornarEmpresa(RNC);

					if (empresa != null) {
						txtNombre.setText(empresa.getNombre());
					} else {
						if (JOptionPane.showConfirmDialog(null,
								"No se encontro ninguna Empresa. \n ¿Désea registrar una empresa nueva?",
								"Atención Requerida", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
							try {
								InsertarEmpresa nuevaEmpresa = new InsertarEmpresa("Insertar Empresa", false, null,
										ftxtRNC.getText());
								nuevaEmpresa.setLocationRelativeTo(null);
								nuevaEmpresa.setModal(true);
								nuevaEmpresa.setVisible(true);
							} catch (ParseException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}

					}

				}
			});
			btnNewButton.setBounds(204, 26, 29, 25);
			panelEmpresa.add(btnNewButton);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(263, 31, 56, 14);
			panelEmpresa.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setBackground(new Color(255, 255, 255));
			txtNombre.setEnabled(false);
			txtNombre.setBounds(338, 27, 160, 23);
			panelEmpresa.add(txtNombre);
			txtNombre.setColumns(10);

			PanelGeneral = new JPanel();
			PanelGeneral.setBackground(new Color(248, 248, 255));
			PanelGeneral.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "General",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PanelGeneral.setBounds(10, 107, 507, 150);
			panel.add(PanelGeneral);
			PanelGeneral.setLayout(null);

			JLabel lblNewLabel_1 = new JLabel(" Contrato:");
			lblNewLabel_1.setBounds(15, 27, 83, 14);
			PanelGeneral.add(lblNewLabel_1);

			cbxContrato = new JComboBox();
			cbxContrato.setBackground(new Color(255, 255, 255));
			cbxContrato.setModel(new DefaultComboBoxModel(
					new String[] { "< Seleccione >", "Largo Plazo", "Corto Plazo", "Temporal" }));
			cbxContrato.setBounds(81, 23, 149, 23);
			PanelGeneral.add(cbxContrato);

			JLabel lblReubicacin = new JLabel("Reubicaci\u00F3n:");
			lblReubicacin.setBounds(15, 68, 83, 14);
			PanelGeneral.add(lblReubicacin);

			rbtnReubicacionSi = new JRadioButton("Si");
			rbtnReubicacionSi.setBackground(new Color(248, 248, 255));
			rbtnReubicacionSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnReubicacionNo.setSelected(false);

				}
			});
			rbtnReubicacionSi.setBounds(124, 64, 45, 23);
			PanelGeneral.add(rbtnReubicacionSi);

			rbtnReubicacionNo = new JRadioButton("No");
			rbtnReubicacionNo.setBackground(new Color(248, 248, 255));
			rbtnReubicacionNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnReubicacionSi.setSelected(false);
				}
			});
			rbtnReubicacionNo.setBounds(179, 64, 45, 23);
			PanelGeneral.add(rbtnReubicacionNo);

			JLabel lblVehiculo = new JLabel("Requiere veh\u00EDculo:");
			lblVehiculo.setBounds(15, 109, 114, 14);
			PanelGeneral.add(lblVehiculo);

			rbtnVehiculoSi = new JRadioButton("Si");
			rbtnVehiculoSi.setBackground(new Color(248, 248, 255));
			rbtnVehiculoSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnVehiculoNo.setSelected(false);
					cbxLicencia.setEnabled(true);
				}
			});
			rbtnVehiculoSi.setBounds(124, 105, 45, 23);
			PanelGeneral.add(rbtnVehiculoSi);

			rbtnVehiculoNo = new JRadioButton("No");
			rbtnVehiculoNo.setBackground(new Color(248, 248, 255));
			rbtnVehiculoNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnVehiculoSi.setSelected(false);
					cbxLicencia.setEnabled(false);
				}
			});
			rbtnVehiculoNo.setBounds(179, 105, 45, 23);
			PanelGeneral.add(rbtnVehiculoNo);

			JLabel lblVacantes = new JLabel("Vacantes:");
			lblVacantes.setBounds(263, 27, 83, 14);
			PanelGeneral.add(lblVacantes);

			spnVacantes = new JSpinner();
			spnVacantes.setBackground(new Color(255, 255, 255));
			spnVacantes.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnVacantes.setBounds(338, 23, 160, 23);
			PanelGeneral.add(spnVacantes);

			JLabel lblLocalidad = new JLabel("Localidad:");
			lblLocalidad.setBounds(263, 68, 83, 14);
			PanelGeneral.add(lblLocalidad);

			JLabel lblCategoriaLic = new JLabel("Licencia:");
			lblCategoriaLic.setBounds(263, 109, 83, 14);
			PanelGeneral.add(lblCategoriaLic);

			cbxLocalidad = new JComboBox();
			cbxLocalidad.setBackground(new Color(255, 255, 255));
			cbxLocalidad.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Azua ", "Bahoruco ",
					"Barahona ", "Dajab\u00F3n ", "Distrito Nacional ", "Duarte ", "El\u00EDas Pi\u00F1a ", "El Seibo ",
					"Espaillat ", "Hato Mayor ", "Independencia ", "La Altagracia ", "La Romana ", "La Vega ",
					"Mar\u00EDa Trinidad S\u00E1nchez ", "Monse\u00F1or Nouel ", "Montecristi ", "Monte Plata ",
					"Pedernales ", "Peravia ", "Puerto Plata ", "Hermanas Mirabal ", "Saman\u00E1 ",
					"S\u00E1nchez Ram\u00EDrez ", "San Crist\u00F3bal ", "San Jos\u00E9 de Ocoa ", "San Juan ",
					"San Pedro de Macor\u00EDs ", "Santiago ", "Santiago Rodr\u00EDguez ", "Santo Domingo ",
					"Valverde " }));
			cbxLocalidad.setBounds(338, 64, 160, 23);
			PanelGeneral.add(cbxLocalidad);

			JLabel label_2 = new JLabel("*");
			label_2.setForeground(Color.RED);
			label_2.setBounds(10, 27, 46, 14);
			PanelGeneral.add(label_2);

			JLabel label_3 = new JLabel("*");
			label_3.setForeground(Color.RED);
			label_3.setBounds(255, 27, 46, 14);
			PanelGeneral.add(label_3);

			JLabel label_4 = new JLabel("*");
			label_4.setForeground(Color.RED);
			label_4.setBounds(255, 68, 46, 14);
			PanelGeneral.add(label_4);

			cbxLicencia = new JComboBox();
			cbxLicencia.setBackground(new Color(255, 255, 255));
			cbxLicencia.setEnabled(false);
			cbxLicencia.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Categor\u00EDa 1",
					"Categor\u00EDa 2", "Categor\u00EDa 3", "Categor\u00EDa 4" }));
			cbxLicencia.setBounds(338, 105, 160, 23);
			PanelGeneral.add(cbxLicencia);

			JPanel panelEdad = new JPanel();
			panelEdad.setBackground(new Color(248, 248, 255));
			panelEdad.setLayout(null);
			panelEdad.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Rango De Edad",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelEdad.setBounds(10, 260, 245, 59);
			panel.add(panelEdad);

			JLabel lblNewLabel = new JLabel("Minima:");
			lblNewLabel.setBounds(15, 28, 60, 14);
			panelEdad.add(lblNewLabel);

			JLabel lblMxima = new JLabel("M\u00E1xima:");
			lblMxima.setBounds(129, 28, 60, 14);
			panelEdad.add(lblMxima);

			spnEdadMinima = new JSpinner();
			spnEdadMinima.setBackground(new Color(255, 255, 255));
			spnEdadMinima.setModel(new SpinnerNumberModel(18, 17, 65, 1));
			spnEdadMinima.setBounds(62, 24, 51, 23);
			panelEdad.add(spnEdadMinima);

			spnEdadMaxima = new JSpinner();
			spnEdadMaxima.setBackground(new Color(255, 255, 255));
			spnEdadMaxima.setModel(new SpinnerNumberModel(19, 18, 65, 1));
			spnEdadMaxima.setBounds(180, 24, 51, 23);
			panelEdad.add(spnEdadMaxima);

			JLabel label_5 = new JLabel("*");
			label_5.setForeground(Color.RED);
			label_5.setBounds(8, 15, 46, 14);
			panelEdad.add(label_5);

			JPanel panelIdioma = new JPanel();
			panelIdioma.setBackground(new Color(248, 248, 255));
			panelIdioma.setLayout(null);
			panelIdioma.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Idiomas",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelIdioma.setBounds(10, 322, 245, 164);
			panel.add(panelIdioma);

			JLabel lblIdioma = new JLabel("Idioma:");
			lblIdioma.setBounds(15, 31, 46, 14);
			panelIdioma.add(lblIdioma);

			cbxIdioma = new JComboBox();
			cbxIdioma.setBackground(new Color(255, 255, 255));
			cbxIdioma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (!misIdiomas.contains((String) cbxIdioma.getSelectedItem())
							&& cbxIdioma.getSelectedIndex() > 0) {

						misIdiomas.add((String) cbxIdioma.getSelectedItem());
					} else if (misIdiomas.contains((String) cbxIdioma.getSelectedItem())) {
						JOptionPane.showMessageDialog(null, "Ese idioma ha sido seleccionado", "ATENCIÓN",
								JOptionPane.ERROR_MESSAGE, null);
					}
					cbxIdioma.setSelectedIndex(0);
					cargarIdioma();
				}
			});
			cbxIdioma.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Afrikaans", "Alban\u00E9s",
					"Alem\u00E1n", "Amharico", "Arabe", "Armenio", "Bengali", "Bieloruso", "Birman\u00E9s", "Bulgaro",
					"Catalan", "Checo", "Chino", "Coreano", "Croata", "Dan\u00E9s", "Dari", "Dzongkha", "Escoc\u00E9s",
					"Eslovaco", "Esloveniano", "Espa\u00F1ol", "Esperanto", "Estoniano", "Faroese", "Farsi",
					"Finland\u00E9s", "Franc\u00E9s", "Gaelico", "Galese", "Gallego", "Griego", "Hebreo", "Hindi",
					"Holand\u00E9s", "Hungaro", "Ingl\u00E9s", "Indonesio", "Inuktitut (Eskimo)", "Islandico",
					"Italiano", "Japon\u00E9s", "Khmer", "Kurdo", "Lao", "Laponico", "Latviano", "Lituano", "Macedonio",
					"Malay\u00E9s", "Malt\u00E9s", "Nepali", "Noruego", "Pashto", "Polaco", "Portugu\u00E9s", "Rumano",
					"Ruso", "Serbio", "Somali", "Suahili", "Sueco", "Tagalog-Filipino", "Tajik", "Tamil",
					"Tailand\u00E9s", "Tibetano", "Tigrinia", "Tongan\u00E9s", "Turco", "Turkmenistano", "Ucraniano",
					"Urdu", "Uzbekistano", "Vasco", "Vietnam\u00E9s" }));
			cbxIdioma.setBounds(68, 27, 130, 23);
			panelIdioma.add(cbxIdioma);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(15, 67, 215, 86);
			panelIdioma.add(scrollPane);

			listIdioma = new JList();
			listIdioma.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexListaIdioma = (String) listIdioma.getSelectedValue();

				}
			});
			listIdioma.setLocation(18, 0);
			scrollPane.setViewportView(listIdioma);

			JButton btnEliminarIdioma = new JButton("");
			btnEliminarIdioma.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/borrar.png")));
			btnEliminarIdioma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					misIdiomas.remove(indexListaIdioma);
					cargarIdioma();
				}
			});
			btnEliminarIdioma.setBounds(201, 26, 29, 25);
			panelIdioma.add(btnEliminarIdioma);

			JPanel PanelVacante = new JPanel();
			PanelVacante.setBackground(new Color(248, 248, 255));
			PanelVacante.setLayout(null);
			PanelVacante.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Tipo Vacante",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PanelVacante.setBounds(255, 260, 265, 59);
			panel.add(PanelVacante);

			rbtnTecnico = new JRadioButton("T\u00E9cnico ");
			rbtnTecnico.setBackground(new Color(248, 248, 255));
			rbtnTecnico.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnUniversitario.setSelected(false);
					rbtnObrero.setSelected(false);
					panelUniversitario.setVisible(false);
					panelTecnico.setVisible(true);
					panelObrero.setVisible(false);
				}
			});
			rbtnTecnico.setBounds(107, 22, 75, 23);
			PanelVacante.add(rbtnTecnico);

			rbtnUniversitario = new JRadioButton("Universitario");
			rbtnUniversitario.setBackground(new Color(248, 248, 255));
			rbtnUniversitario.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnTecnico.setSelected(false);
					rbtnObrero.setSelected(false);
					panelUniversitario.setVisible(true);
					panelTecnico.setVisible(false);
					panelObrero.setVisible(false);
				}
			});
			rbtnUniversitario.setBounds(6, 22, 99, 23);
			PanelVacante.add(rbtnUniversitario);

			rbtnObrero = new JRadioButton("Obrero");
			rbtnObrero.setBackground(new Color(248, 248, 255));
			rbtnObrero.setBounds(184, 22, 75, 23);
			PanelVacante.add(rbtnObrero);

			JLabel label_6 = new JLabel("*");
			label_6.setBounds(6, 15, 46, 14);
			PanelVacante.add(label_6);
			label_6.setForeground(Color.RED);
			rbtnObrero.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnUniversitario.setSelected(false);
					rbtnTecnico.setSelected(false);
					panelUniversitario.setVisible(false);
					panelTecnico.setVisible(false);
					panelObrero.setVisible(true);

				}
			});

			panelUniversitario = new JPanel();
			panelUniversitario.setBackground(new Color(248, 248, 255));
			panelUniversitario.setLayout(null);
			panelUniversitario.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelUniversitario.setBounds(255, 322, 265, 164);
			panel.add(panelUniversitario);

			JLabel lblExperiencia = new JLabel("Experiencia:");
			lblExperiencia.setBounds(18, 29, 72, 14);
			panelUniversitario.add(lblExperiencia);

			spnUniversitarioExperiencia = new JSpinner();
			spnUniversitarioExperiencia.setBackground(new Color(255, 255, 255));
			spnUniversitarioExperiencia
					.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnUniversitarioExperiencia.setBounds(93, 25, 160, 23);
			panelUniversitario.add(spnUniversitarioExperiencia);

			JLabel lblPostgrado = new JLabel("PostGrado:");
			lblPostgrado.setBounds(18, 100, 72, 14);
			panelUniversitario.add(lblPostgrado);

			rbtnPostGradoSi = new JRadioButton("Si");
			rbtnPostGradoSi.setBackground(new Color(248, 248, 255));
			rbtnPostGradoSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnPostGradoNo.setSelected(false);
				}
			});
			rbtnPostGradoSi.setBounds(114, 96, 40, 23);
			panelUniversitario.add(rbtnPostGradoSi);

			rbtnPostGradoNo = new JRadioButton("No");
			rbtnPostGradoNo.setBackground(new Color(248, 248, 255));
			rbtnPostGradoNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnPostGradoSi.setSelected(false);
				}
			});
			rbtnPostGradoNo.setBounds(180, 96, 51, 23);
			panelUniversitario.add(rbtnPostGradoNo);

			JLabel lblCarrera = new JLabel("Carrera:");
			lblCarrera.setBounds(18, 65, 72, 14);
			panelUniversitario.add(lblCarrera);

			cbxCarrera = new JComboBox();
			cbxCarrera.setBackground(new Color(255, 255, 255));
			cbxCarrera.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Adm. de Empresas",
					"Adm. Hotelera", "Derecho", "Econom\u00EDa", "Contabilidad", "Mercadotecnia", "Arquitectura",
					"Comunicacion Social", "Dise\u00F1o e Interiorismo", "Ecologia", "Educaci\u00F3n", "Filosof\u00EDa",
					"Psicolog\u00EDa", "Ing. Civil", "Ing. Electr\u00F3nica", "Ing. Industrial",
					"Ing. Mecatr\u00F3nica", "Ing. Sistema", "Ing. Telem\u00E1tica", "Enfermeria", "Estomatolog\u00EDa",
					"Medicina", "Nutricion y Dietetica", "Terapia F\u00EDsica" }));
			cbxCarrera.setBounds(93, 61, 160, 23);
			panelUniversitario.add(cbxCarrera);

			JLabel label_7 = new JLabel("*");
			label_7.setForeground(Color.RED);
			label_7.setBounds(10, 65, 46, 14);
			panelUniversitario.add(label_7);

			panelTecnico = new JPanel();
			panelTecnico.setBackground(new Color(248, 248, 255));
			panelTecnico.setLayout(null);
			panelTecnico.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "T\u00E9cnico",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTecnico.setBounds(255, 322, 265, 164);
			panel.add(panelTecnico);

			JLabel label = new JLabel("Experiencia:");
			label.setBounds(18, 29, 72, 14);
			panelTecnico.add(label);

			spnTecnicoExperiencia = new JSpinner();
			spnTecnicoExperiencia.setBackground(new Color(248, 248, 255));
			spnTecnicoExperiencia.setBounds(93, 25, 160, 23);
			panelTecnico.add(spnTecnicoExperiencia);

			JLabel label_8 = new JLabel("*");
			label_8.setForeground(Color.RED);
			label_8.setBounds(10, 65, 46, 14);
			panelTecnico.add(label_8);

			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(18, 65, 72, 14);
			panelTecnico.add(lblArea);

			cbxArea = new JComboBox();
			cbxArea.setBackground(new Color(248, 248, 255));
			cbxArea.setModel(new DefaultComboBoxModel(
					new String[] { "<Seleccionar>", "Emprendimiento", "Mecanograf\u00EDa", "Dise\u00F1o Gr\u00E1fico",
							"Programaci\u00F3n", "Contabilidad", "Programaci\u00F3n Web" }));
			cbxArea.setBounds(93, 61, 160, 23);
			panelTecnico.add(cbxArea);

			panelObrero = new JPanel();
			panelObrero.setBackground(new Color(248, 248, 255));
			panelObrero.setLayout(null);
			panelObrero.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Obrero",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelObrero.setBounds(255, 322, 265, 164);
			panel.add(panelObrero);

			JLabel label_1 = new JLabel("Experiencia:");
			label_1.setBounds(18, 29, 72, 14);
			panelObrero.add(label_1);

			spnObreroExperiencia = new JSpinner();
			spnObreroExperiencia.setBackground(new Color(248, 248, 255));
			spnObreroExperiencia.setBounds(93, 25, 160, 23);
			panelObrero.add(spnObreroExperiencia);

			JLabel lblHabilidades = new JLabel("Habilidades:");
			lblHabilidades.setBounds(18, 65, 72, 14);
			panelObrero.add(lblHabilidades);

			JLabel label_9 = new JLabel("*");
			label_9.setForeground(Color.RED);
			label_9.setBounds(10, 65, 46, 14);
			panelObrero.add(label_9);

			cbxHabilidades = new JComboBox();
			cbxHabilidades.setBackground(new Color(248, 248, 255));
			cbxHabilidades.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if (!misHabilidades.contains((String) cbxHabilidades.getSelectedItem())
							&& cbxHabilidades.getSelectedIndex() > 0) {
						misHabilidades.add((String) cbxHabilidades.getSelectedItem());
					} else if (misHabilidades.contains((String) cbxHabilidades.getSelectedItem())) {
						JOptionPane.showMessageDialog(null, "Esta habilidad ya fue seleccionada", "ERROR",
								JOptionPane.ERROR_MESSAGE, null);
					}
					cbxHabilidades.setSelectedIndex(0);
					cargarHabilidades();
				}

			});
			cbxHabilidades.setModel(new DefaultComboBoxModel(
					new String[] { "< Seleccione >", "Alba\u00F1il", "Anfitri\u00F3n de Fiesta", "Arsano", "Carpintero",
							"Chofer", "Chef", "Constructor", "Decorador", "Ebanista", "Electricista", "Mec\u00E1nico",
							"Pintor", "Plomero", "Salva Vidas", "Modista", "Seguridad", "Sirviente", "Jardinero" }));
			cbxHabilidades.setBounds(93, 61, 126, 23);
			panelObrero.add(cbxHabilidades);

			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(18, 90, 235, 63);
			panelObrero.add(scrollPane_1);

			ListHabilidad = new JList();
			ListHabilidad.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					indexListaHabilidades = (String) ListHabilidad.getSelectedValue();
				}
			});
			ListHabilidad.setLocation(20, 0);
			scrollPane_1.setViewportView(ListHabilidad);

			JButton bntEliminarHabilidad = new JButton("");
			bntEliminarHabilidad.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/borrar.png")));
			bntEliminarHabilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					misHabilidades.remove(indexListaHabilidades);
					cargarHabilidades();
				}
			});
			bntEliminarHabilidad.setBounds(224, 60, 29, 25);
			panelObrero.add(bntEliminarHabilidad);

			JLabel lblcampos = new JLabel("* Campos Obligatorios");
			lblcampos.setForeground(Color.RED);
			lblcampos.setBounds(386, 16, 127, 14);
			panel.add(lblcampos);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
				if(modi == null ){
					okButton.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/add.png")));					
				}else{
					okButton.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/modificar.png")));
				}
				
				
				if (modificarSoli != null) {
					okButton.setText("Modificar");
				} else {
					okButton.setText("Insertar");
				}
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String RNC = ftxtRNC.getText();
						String Contrato = (String) cbxContrato.getSelectedItem();
						Boolean reubicacion = false;
						if (rbtnReubicacionSi.isSelected()) {
							reubicacion = true;
						} else if (rbtnReubicacionNo.isSelected()) {
							reubicacion = false;
						}
						boolean vehiculo = false;
						if (rbtnVehiculoSi.isSelected()) {
							vehiculo = true;
						} else if (rbtnVehiculoNo.isSelected()) {
							vehiculo = false;
						}
						float vacantes = (float) spnVacantes.getValue();
						String localidad = (String) cbxLocalidad.getSelectedItem();
						int categoriaLicencia = 0;
						if (cbxLicencia.getSelectedIndex() == 1) {
							categoriaLicencia = 1;
						}
						if (cbxLicencia.getSelectedIndex() == 2) {
							categoriaLicencia = 2;
						}
						if (cbxLicencia.getSelectedIndex() == 3) {
							categoriaLicencia = 3;
						}
						if (cbxLicencia.getSelectedIndex() == 4) {
							categoriaLicencia = 4;
						}
						int edadMinima = (int) spnEdadMinima.getValue();
						int edadMaxima = (int) spnEdadMaxima.getValue();
						int experienciaUniversitario = (int) spnUniversitarioExperiencia.getValue();
						int experienciaTecnico = (int) spnTecnicoExperiencia.getValue();
						int experienciaObrero = (int) spnObreroExperiencia.getValue();
						String carrera = (String) cbxCarrera.getSelectedItem();
						boolean posGrado = false;
						if (rbtnPostGradoSi.isSelected()) {
							posGrado = true;
						}
						String area = (String) cbxArea.getSelectedItem();
						if (modificarSoli == null) {

							if (txtNombre.getText().isEmpty()) {
								JOptionPane.showMessageDialog(null, "Se debe ingresar la empresa que solicita",
										"ATENCIÓN", JOptionPane.WARNING_MESSAGE, null);
							} else if (cbxContrato.getSelectedIndex() == 0 || cbxLocalidad.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "No deje campos vacios", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);

							} else if (rbtnVehiculoSi.isSelected() && cbxLicencia.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null,
										"Selecciona la categoria de la Licencia de conducir", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);

							} else if (!rbtnTecnico.isSelected() && !rbtnUniversitario.isSelected()
									&& !rbtnObrero.isSelected()) {
								JOptionPane.showMessageDialog(null, "Selecciona el tipo de empleado que se necesita",
										"ATENCIÓN", JOptionPane.WARNING_MESSAGE, null);

							} else if (panelUniversitario.isVisible() && cbxCarrera.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Selecciona la carrera del universitario",
										"ATENCIÓN", JOptionPane.WARNING_MESSAGE, null);

							} else if (panelTecnico.isVisible() && cbxArea.getSelectedIndex() == 0) {
								JOptionPane.showMessageDialog(null, "Selecciona el area del tecnico", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);
							} else if (panelObrero.isVisible() && misHabilidades.size() == 0) {
								JOptionPane.showMessageDialog(null, "Selecciona las habilidades del obrero", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);

							} else if (panelUniversitario.isVisible() && !rbtnPostGradoSi.isSelected()
									&& !rbtnPostGradoNo.isSelected()) {
								rbtnPostGradoNo.setSelected(true);
							} else if ((int) spnEdadMaxima.getValue() < (int) spnEdadMinima.getValue()) {
								JOptionPane.showMessageDialog(null, "Edad minima no puede ser mayor que la máxima",
										"ATENCIÓN", JOptionPane.WARNING_MESSAGE, null);

							} else {
								if (rbtnUniversitario.isSelected()) {
									SolicitudUniversitario nuevaSoli = new SolicitudUniversitario(vacantes,
											experienciaUniversitario, edadMaxima, edadMinima, Contrato, vehiculo,
											localidad, empresa, reubicacion, misIdiomas, categoriaLicencia, posGrado,
											carrera);

									bolsa.insertSolicitud(nuevaSoli);
									JOptionPane.showMessageDialog(null, "La Solicitud se registro correctamente",
											"Información", JOptionPane.INFORMATION_MESSAGE, null);
									clean(1);
								}
								if (rbtnTecnico.isSelected()) {
									SolicitudTecnico nuevaSoli = new SolicitudTecnico(vacantes,
											experienciaTecnico, edadMaxima, edadMinima, Contrato, vehiculo,
											localidad, empresa, reubicacion, misIdiomas, categoriaLicencia, area);
									bolsa.insertSolicitud(nuevaSoli);
									JOptionPane.showMessageDialog(null, "La Solicitud se registro correctamente",
											"Información", JOptionPane.INFORMATION_MESSAGE, null);
									clean(2);
								}
								if (rbtnObrero.isSelected()) {
									SolicitudObrero nuevaSoli = new SolicitudObrero(vacantes, experienciaObrero,
											edadMaxima, edadMinima, Contrato, vehiculo, localidad, empresa, reubicacion,
											misIdiomas, categoriaLicencia, misHabilidades);
									bolsa.insertSolicitud(nuevaSoli);
									JOptionPane.showMessageDialog(null, "La Solicitud se registro correctamente",
											"Información", JOptionPane.INFORMATION_MESSAGE, null);
									clean(3);
								}

							}
							////// MODIFICAR
						} else {

							modificarSoli.setTipoContrato((String) cbxContrato.getSelectedItem());

							if (rbtnReubicacionSi.isSelected()) {
								modificarSoli.setMudarse(true);
							} else {
								modificarSoli.setMudarse(false);
							}
							if (rbtnVehiculoSi.isSelected()) {
								modificarSoli.setVehiculoPropio(true);
								modificarSoli.setCategoriaLicencia((int) cbxLicencia.getSelectedIndex());
							} else {
								modificarSoli.setVehiculoPropio(false);
							}
							modificarSoli.setCantVacantes((float) spnVacantes.getValue());
							modificarSoli.setLocalidad((String) cbxLocalidad.getSelectedItem());
							modificarSoli.setEdadMin((int) spnEdadMinima.getValue());
							modificarSoli.setEdadMax((int) spnEdadMaxima.getValue());
							modificarSoli.setIdiomas(misIdiomas);
							if (modi instanceof SolicitudUniversitario) {
								modificarSoli.setAnnosExperiencia((int) spnUniversitarioExperiencia.getValue());
								((SolicitudUniversitario) modificarSoli)
										.setCarrera((String) cbxCarrera.getSelectedItem());
								if (rbtnPostGradoSi.isSelected()) {
									((SolicitudUniversitario) modificarSoli).setPostGrado(true);
								} else {
									((SolicitudUniversitario) modificarSoli).setPostGrado(false);
								}

							} else if (modi instanceof SolicitudTecnico) {
								modificarSoli.setAnnosExperiencia((int) spnTecnicoExperiencia.getValue());
								((SolicitudTecnico) modificarSoli).setArea((String) cbxArea.getSelectedItem());

							} else if (modi instanceof SolicitudObrero) {
								modificarSoli.setAnnosExperiencia((int) spnObreroExperiencia.getValue());
								((SolicitudObrero) modificarSoli).setHabilidades(misHabilidades);

							}
							BolsaLaboral.getInstance().ActualizarSolicitud(modi, modificarSoli);
							JOptionPane.showMessageDialog(null, "La Solicitud se modifico correctamente", "Información",
									JOptionPane.INFORMATION_MESSAGE, null);
							if (ListarSolicitud.cbxfiltro.getSelectedIndex() == 0) {
								ListarSolicitud.loadtabla(0);
							}
							if (ListarSolicitud.cbxfiltro.getSelectedIndex() == 1) {
								ListarSolicitud.loadtabla(1);

							}
							if (ListarSolicitud.cbxfiltro.getSelectedIndex() == 2) {
								ListarSolicitud.loadtabla(2);

							}
							if (ListarSolicitud.cbxfiltro.getSelectedIndex() == 3) {
								ListarSolicitud.loadtabla(3);

							}

							dispose();

						}

					}

					private void clean(int i) {
						ftxtRNC.setValue("");
						txtNombre.setText("");
						cbxContrato.setSelectedIndex(0);
						rbtnReubicacionNo.setSelected(false);
						rbtnReubicacionSi.setSelected(false);
						rbtnVehiculoSi.setSelected(false);
						rbtnVehiculoNo.setSelected(false);
						spnVacantes.setValue(1);
						cbxLocalidad.setSelectedIndex(0);
						cbxLicencia.setSelectedIndex(0);
						spnEdadMinima.setValue(18);
						spnEdadMaxima.setValue(19);
						misIdiomas.removeAll(misIdiomas);
						cargarIdioma();
						if (i == 1) {
							rbtnUniversitario.setSelected(false);
							spnUniversitarioExperiencia.setValue(0);
							cbxCarrera.setSelectedIndex(0);
							rbtnPostGradoNo.setSelected(false);
							rbtnPostGradoSi.setSelected(false);
						}
						if (i == 2) {
							rbtnTecnico.setSelected(false);
							spnTecnicoExperiencia.setValue(0);
							cbxArea.setSelectedIndex(0);
						}
						if (i == 3) {
							rbtnObrero.setSelected(false);
							misHabilidades.removeAll(misHabilidades);
							cargarHabilidades();
						}

					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}

			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(InsertarSolicitud.class.getResource("/img/cancelar.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void cargarIdioma() {
		DefaultListModel idioma = new DefaultListModel();

		for (String idio : misIdiomas) {
			idioma.addElement(idio);
		}
		listIdioma.setModel(idioma);

	}

	private void cargarHabilidades() {
		DefaultListModel habilidades = new DefaultListModel();

		for (String habili : misHabilidades) {
			habilidades.addElement(habili);
		}
		ListHabilidad.setModel(habilidades);

	}

}
