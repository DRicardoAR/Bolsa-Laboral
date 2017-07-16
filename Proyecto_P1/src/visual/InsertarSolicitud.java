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
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.SpinnerNumberModel;

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

	private ArrayList<String> misIdiomas;
	private ArrayList<String> misHabilidades;
	private String indexListaIdioma;
	private String indexListaHabilidades;
	private BolsaLaboral bolsa = BolsaLaboral.getInstance();
	private Empresa empresa;
	private Solicitud modificarSoli = null;
	

	public InsertarSolicitud(Solicitud modi) {
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
					cargarIdiomaModi();
					
					for (String string : modi.getIdiomas()) {
						System.out.println(string);
						
					}

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
						rbtnObrero.setSelected(true);
						panelObrero.setVisible(true);
						panelUniversitario.setVisible(false);
						panelTecnico.setVisible(false);
						rbtnUniversitario.setEnabled(false);
						rbtnTecnico.setEnabled(false);
						rbtnObrero.setEnabled(false);
						spnObreroExperiencia.setValue(((SolicitudObrero) modi).getAnnosExperiencia());
						cargarHabilidadModi();

					}

				}

			}

		});
		setResizable(false);
		if (modificarSoli == null) {
			setTitle("Insertar Solicitud - Bolsa Laboral");
			misIdiomas = new ArrayList<>();
			misHabilidades = new ArrayList<>();
		} else {
			setTitle("Modificar Solicitud - Bolsa Laboral");
			misIdiomas = new ArrayList<>();
			misHabilidades = new ArrayList<>();
			misIdiomas = modificarSoli.getIdiomas();
			if(modificarSoli instanceof SolicitudObrero){
				misHabilidades = ((SolicitudObrero)modificarSoli).getHabilidades();
				
			}
			

		}

		setBounds(100, 100, 547, 580);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panelEmpresa = new JPanel();
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
				ftxtRNC.setBounds(81, 28, 122, 21);
				panelEmpresa.add(ftxtRNC);

			} catch (Exception e) {
				// TODO: handle exception
			}

			JButton btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					String RNC = ftxtRNC.getText();

					empresa = bolsa.RetornarEmpresa(RNC);

					if (empresa != null) {
						txtNombre.setText(empresa.getNombre());
					} else {
						JOptionPane.showMessageDialog(null, "No se encontro ningun empresa", "ATENCIÓN",
								JOptionPane.ERROR_MESSAGE, null);

					}

				}
			});
			btnNewButton.setBounds(204, 28, 27, 21);
			panelEmpresa.add(btnNewButton);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(263, 31, 56, 14);
			panelEmpresa.add(lblNombre);

			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setBounds(338, 28, 160, 20);
			panelEmpresa.add(txtNombre);
			txtNombre.setColumns(10);

			PanelGeneral = new JPanel();
			PanelGeneral.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "General",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PanelGeneral.setBounds(10, 107, 507, 150);
			panel.add(PanelGeneral);
			PanelGeneral.setLayout(null);

			JLabel lblNewLabel_1 = new JLabel(" Contrato:");
			lblNewLabel_1.setBounds(15, 27, 83, 14);
			PanelGeneral.add(lblNewLabel_1);

			cbxContrato = new JComboBox();
			cbxContrato.setModel(new DefaultComboBoxModel(
					new String[] { "< Seleccione >", "Largo Plazo", "Corto Plazo", "Temporal" }));
			cbxContrato.setBounds(81, 24, 149, 21);
			PanelGeneral.add(cbxContrato);

			JLabel lblReubicacin = new JLabel("Reubicaci\u00F3n:");
			lblReubicacin.setBounds(15, 68, 83, 14);
			PanelGeneral.add(lblReubicacin);

			rbtnReubicacionSi = new JRadioButton("Si");
			rbtnReubicacionSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnReubicacionNo.setSelected(false);

				}
			});
			rbtnReubicacionSi.setBounds(124, 64, 45, 23);
			PanelGeneral.add(rbtnReubicacionSi);

			rbtnReubicacionNo = new JRadioButton("No");
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
			rbtnVehiculoSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnVehiculoNo.setSelected(false);
					cbxLicencia.setEnabled(true);
				}
			});
			rbtnVehiculoSi.setBounds(124, 105, 45, 23);
			PanelGeneral.add(rbtnVehiculoSi);

			rbtnVehiculoNo = new JRadioButton("No");
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
			spnVacantes.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
			spnVacantes.setBounds(338, 24, 160, 21);
			PanelGeneral.add(spnVacantes);

			JLabel lblLocalidad = new JLabel("Localidad:");
			lblLocalidad.setBounds(263, 68, 83, 14);
			PanelGeneral.add(lblLocalidad);

			JLabel lblCategoriaLic = new JLabel("Licencia:");
			lblCategoriaLic.setBounds(263, 109, 83, 14);
			PanelGeneral.add(lblCategoriaLic);

			cbxLocalidad = new JComboBox();
			cbxLocalidad.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Azua ", "Bahoruco ",
					"Barahona ", "Dajab\u00F3n ", "Distrito Nacional ", "Duarte ", "El\u00EDas Pi\u00F1a ", "El Seibo ",
					"Espaillat ", "Hato Mayor ", "Independencia ", "La Altagracia ", "La Romana ", "La Vega ",
					"Mar\u00EDa Trinidad S\u00E1nchez ", "Monse\u00F1or Nouel ", "Montecristi ", "Monte Plata ",
					"Pedernales ", "Peravia ", "Puerto Plata ", "Hermanas Mirabal ", "Saman\u00E1 ",
					"S\u00E1nchez Ram\u00EDrez ", "San Crist\u00F3bal ", "San Jos\u00E9 de Ocoa ", "San Juan ",
					"San Pedro de Macor\u00EDs ", "Santiago ", "Santiago Rodr\u00EDguez ", "Santo Domingo ",
					"Valverde " }));
			cbxLocalidad.setBounds(338, 65, 160, 21);
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
			cbxLicencia.setEnabled(false);
			cbxLicencia.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Categor\u00EDa 1",
					"Categor\u00EDa 2", "Categor\u00EDa 3", "Categor\u00EDa 4" }));
			cbxLicencia.setBounds(338, 106, 160, 21);
			PanelGeneral.add(cbxLicencia);

			JPanel panelEdad = new JPanel();
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
			spnEdadMinima.setModel(new SpinnerNumberModel(18, 17, 65, 1));
			spnEdadMinima.setBounds(62, 25, 51, 21);
			panelEdad.add(spnEdadMinima);

			spnEdadMaxima = new JSpinner();
			spnEdadMaxima.setModel(new SpinnerNumberModel(19, 18, 65, 1));
			spnEdadMaxima.setBounds(180, 25, 51, 21);
			panelEdad.add(spnEdadMaxima);

			JLabel label_5 = new JLabel("*");
			label_5.setForeground(Color.RED);
			label_5.setBounds(8, 15, 46, 14);
			panelEdad.add(label_5);

			JPanel panelIdioma = new JPanel();
			panelIdioma.setLayout(null);
			panelIdioma.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Idiomas",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelIdioma.setBounds(10, 322, 245, 164);
			panel.add(panelIdioma);

			JLabel lblIdioma = new JLabel("Idioma:");
			lblIdioma.setBounds(15, 31, 46, 14);
			panelIdioma.add(lblIdioma);

			cbxIdioma = new JComboBox();
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
			cbxIdioma.setBounds(68, 28, 132, 21);
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
			btnEliminarIdioma.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					misIdiomas.remove(indexListaIdioma);
					cargarIdioma();
				}
			});
			btnEliminarIdioma.setBounds(204, 28, 27, 21);
			panelIdioma.add(btnEliminarIdioma);

			JPanel PanelVacante = new JPanel();
			PanelVacante.setLayout(null);
			PanelVacante.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Tipo Vacante",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			PanelVacante.setBounds(255, 260, 265, 59);
			panel.add(PanelVacante);

			rbtnTecnico = new JRadioButton("T\u00E9cnico ");
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
			panelUniversitario.setLayout(null);
			panelUniversitario.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
					"Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelUniversitario.setBounds(255, 322, 265, 164);
			panel.add(panelUniversitario);

			JLabel lblExperiencia = new JLabel("Experiencia:");
			lblExperiencia.setBounds(18, 29, 72, 14);
			panelUniversitario.add(lblExperiencia);

			spnUniversitarioExperiencia = new JSpinner();
			spnUniversitarioExperiencia.setBounds(93, 26, 160, 21);
			panelUniversitario.add(spnUniversitarioExperiencia);

			JLabel lblPostgrado = new JLabel("PostGrado:");
			lblPostgrado.setBounds(18, 100, 72, 14);
			panelUniversitario.add(lblPostgrado);

			rbtnPostGradoSi = new JRadioButton("Si");
			rbtnPostGradoSi.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnPostGradoNo.setSelected(false);
				}
			});
			rbtnPostGradoSi.setBounds(114, 96, 40, 23);
			panelUniversitario.add(rbtnPostGradoSi);

			rbtnPostGradoNo = new JRadioButton("No");
			rbtnPostGradoNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rbtnPostGradoSi.setSelected(false);
				}
			});
			rbtnPostGradoNo.setBounds(180, 96, 40, 23);
			panelUniversitario.add(rbtnPostGradoNo);

			JLabel lblCarrera = new JLabel("Carrera:");
			lblCarrera.setBounds(18, 65, 72, 14);
			panelUniversitario.add(lblCarrera);

			cbxCarrera = new JComboBox();
			cbxCarrera.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Adm. de Empresas",
					"Adm. Hotelera", "Derecho", "Econom\u00EDa", "Contabilidad", "Mercadotecnia", "Arquitectura",
					"Comunicacion Social", "Dise\u00F1o e Interiorismo", "Ecologia", "Educaci\u00F3n", "Filosof\u00EDa",
					"Psicolog\u00EDa", "Ing. Civil", "Ing. Electr\u00F3nica", "Ing. Industrial",
					"Ing. Mecatr\u00F3nica", "Ing. Sistema", "Ing. Telem\u00E1tica", "Enfermeria", "Estomatolog\u00EDa",
					"Medicina", "Nutricion y Dietetica", "Terapia F\u00EDsica" }));
			cbxCarrera.setBounds(93, 62, 160, 21);
			panelUniversitario.add(cbxCarrera);

			JLabel label_7 = new JLabel("*");
			label_7.setForeground(Color.RED);
			label_7.setBounds(10, 65, 46, 14);
			panelUniversitario.add(label_7);

			panelTecnico = new JPanel();
			panelTecnico.setLayout(null);
			panelTecnico.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "T\u00E9cnico",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTecnico.setBounds(255, 322, 265, 164);
			panel.add(panelTecnico);

			JLabel label = new JLabel("Experiencia:");
			label.setBounds(18, 29, 72, 14);
			panelTecnico.add(label);

			spnTecnicoExperiencia = new JSpinner();
			spnTecnicoExperiencia.setBounds(93, 26, 160, 21);
			panelTecnico.add(spnTecnicoExperiencia);

			JLabel label_8 = new JLabel("*");
			label_8.setForeground(Color.RED);
			label_8.setBounds(10, 65, 46, 14);
			panelTecnico.add(label_8);

			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(18, 65, 72, 14);
			panelTecnico.add(lblArea);

			cbxArea = new JComboBox();
			cbxArea.setModel(new DefaultComboBoxModel(
					new String[] { "<Seleccionar>", "Emprendimiento", "Mecanograf\u00EDa", "Dise\u00F1o Gr\u00E1fico",
							"Programaci\u00F3n", "Contabilidad", "Programaci\u00F3n Web" }));
			cbxArea.setBounds(93, 62, 160, 20);
			panelTecnico.add(cbxArea);

			panelObrero = new JPanel();
			panelObrero.setLayout(null);
			panelObrero.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Obrero",
					TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelObrero.setBounds(255, 322, 265, 164);
			panel.add(panelObrero);

			JLabel label_1 = new JLabel("Experiencia:");
			label_1.setBounds(18, 29, 72, 14);
			panelObrero.add(label_1);

			spnObreroExperiencia = new JSpinner();
			spnObreroExperiencia.setBounds(93, 26, 160, 21);
			panelObrero.add(spnObreroExperiencia);

			JLabel lblHabilidades = new JLabel("Habilidades:");
			lblHabilidades.setBounds(18, 65, 72, 14);
			panelObrero.add(lblHabilidades);

			JLabel label_9 = new JLabel("*");
			label_9.setForeground(Color.RED);
			label_9.setBounds(10, 65, 46, 14);
			panelObrero.add(label_9);

			cbxHabilidades = new JComboBox();
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
			cbxHabilidades.setBounds(93, 62, 129, 20);
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
			bntEliminarHabilidad.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					misHabilidades.remove(indexListaHabilidades);
					cargarHabilidades();
				}
			});
			bntEliminarHabilidad.setBounds(226, 61, 27, 21);
			panelObrero.add(bntEliminarHabilidad);

			JLabel lblcampos = new JLabel("* Campos Obligatorios");
			lblcampos.setForeground(Color.RED);
			lblcampos.setBounds(386, 16, 127, 14);
			panel.add(lblcampos);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("");
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
						int vacantes = (int) spnVacantes.getValue();
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
											experienciaUniversitario, edadMaxima, edadMinima, Contrato, vehiculo,
											localidad, empresa, reubicacion, misIdiomas, categoriaLicencia, area);
									bolsa.insertSolicitud(nuevaSoli);
									JOptionPane.showMessageDialog(null, "La Solicitud se registro correctamente",
											"Información", JOptionPane.INFORMATION_MESSAGE, null);
									clean(2);
								}
								if (rbtnObrero.isSelected()) {
									SolicitudObrero nuevaSoli = new SolicitudObrero(vacantes, experienciaUniversitario,
											edadMaxima, edadMinima, Contrato, vehiculo, localidad, empresa, reubicacion,
											misIdiomas, categoriaLicencia, misHabilidades);
									bolsa.insertSolicitud(nuevaSoli);
									JOptionPane.showMessageDialog(null, "La Solicitud se registro correctamente",
											"Información", JOptionPane.INFORMATION_MESSAGE, null);
									clean(3);
								}

							}
						} else {
							modificarSoli.setTipoContrato((String) cbxContrato.getSelectedItem());
							if(rbtnReubicacionSi.isSelected()){
								modificarSoli.setMudarse(true);
							}else{
								modificarSoli.setMudarse(false);
							}
							if(rbtnVehiculoSi.isSelected()){
								modificarSoli.setVehiculoPropio(true);
								modificarSoli.setCategoriaLicencia((int) cbxLicencia.getSelectedIndex());
							}else{
								modificarSoli.setVehiculoPropio(false);									
							}
							modificarSoli.setCantVacantes((int) spnVacantes.getValue());
							modificarSoli.setLocalidad((String) cbxLocalidad.getSelectedItem());
							modificarSoli.setEdadMin((int)spnEdadMinima.getValue());
							modificarSoli.setEdadMax((int)spnEdadMaxima.getValue());
							modificarSoli.setIdiomas(misIdiomas);
							if(modi instanceof SolicitudUniversitario){
								modificarSoli.setAnnosExperiencia((int) spnUniversitarioExperiencia.getValue());
								((SolicitudUniversitario)modificarSoli).setCarrera((String) cbxCarrera.getSelectedItem());
								if(rbtnPostGradoSi.isSelected()){
									((SolicitudUniversitario)modificarSoli).setPostGrado(true);
								}else{
									((SolicitudUniversitario)modificarSoli).setPostGrado(false);									
								}
								
							}else if(modi instanceof SolicitudTecnico){
								modificarSoli.setAnnosExperiencia((int) spnTecnicoExperiencia.getValue());
								((SolicitudTecnico)modificarSoli).setArea((String) cbxArea.getSelectedItem());
								
							}else if(modi instanceof SolicitudObrero){
								modificarSoli.setAnnosExperiencia((int) spnObreroExperiencia.getValue());
								((SolicitudObrero)modificarSoli).setHabilidades(misHabilidades);
								
							}
							BolsaLaboral.getInstance().ActualizarSolicitud(modi, modificarSoli);
							JOptionPane.showMessageDialog(null, "La Solicitud se modifico correctamente",
									"Información", JOptionPane.INFORMATION_MESSAGE, null);
							
							dispose();
							
							
						}

					}

					private void clean(int i) {
						ftxtRNC.setValue("");
						txtNombre.setText("");
						cbxContrato.setSelectedIndex(0);
						rbtnReubicacionNo.setSelected(false);
						rbtnReubicacionSi.setSelected(false);
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
							cbxArea.setSelectedItem(0);
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

	private void cargarIdiomaModi() {
		DefaultListModel idioma = new DefaultListModel();
		System.out.println("tamoano"+modificarSoli.getIdiomas().size());
		for (String idio : modificarSoli.getIdiomas()) {
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

	private void cargarHabilidadModi() {
		DefaultListModel habilidades = new DefaultListModel();
		for (String habili : ((SolicitudObrero) modificarSoli).getHabilidades()) {
			habilidades.addElement(habili);

		}
		ListHabilidad.setModel(habilidades);

	}

}
