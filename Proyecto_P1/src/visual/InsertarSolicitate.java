package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.sun.xml.internal.ws.api.streaming.XMLStreamReaderFactory.Default;
import com.toedter.calendar.JDateChooser;

import logica.BolsaLaboral;
import logica.Obrero;
import logica.Solicitante;
import logica.Tecnico;
import logica.Universitario;
import sun.util.locale.provider.AuxLocaleProviderAdapter;
import sun.util.resources.cldr.dav.LocaleNames_dav;

import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import javax.swing.ImageIcon;

public class InsertarSolicitate extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtApellidos;
	private JTextField txtReferencia;
	private JTextField txtSector;
	private JTextField txtCiudad;
	private JTextField txtCalle;
	private JPanel panel1;
	private JPanel panel2;
	private JPanel panel_infoPersonal;
	private JPanel panel_Contactos;
	private JPanel panel_Obreo;
	private JPanel panel_Tecnico;
	private JPanel panel_Universitario;
	private JButton btnMover;
	private JButton btnRemoverHabilidad;
	private JButton btnEliminarIdioma;
	private JButton cancelButton;
	private JButton btnRegistrar;
	private JRadioButton rdbObrero;
	private JRadioButton rdbTecnico;
	private JRadioButton rdbUniversitario;
	private JRadioButton rdbSiVehiculo;
	private JRadioButton rdbNoVehiculo;
	private JRadioButton rdbSiReelocalizacion;
	private JRadioButton rdbNoReelocalizacion;
	private JRadioButton rdbMasculino;
	private JRadioButton rdbFemenino;
	private JRadioButton rdbSiPost;
	private JRadioButton rdbNoPost;
	private JFormattedTextField txtTelefono;
	private JFormattedTextField txtEmail;
	private JFormattedTextField textCedula;
	private JComboBox cbxEstadoCilvil;
	private JComboBox cbxNacionalidad;
	private JComboBox cbxLicencia;
	private JComboBox cbxProvincia;
	private JComboBox cbxIdiomas;
	private JComboBox cbxHabilidades;
	private JComboBox cbxAreaTecnico;
	private JComboBox cbxCarrera;
	private JSpinner spnAnosExpUniversitario;
	private JSpinner spnNumeroCasa;
	private JSpinner spnAnnosExpObrero;
	private JSpinner spnAnosExpTecnico;
	private JList listIdiomas;
	private JList listHabilidades;
	private ArrayList<String> misIdiomas = new ArrayList<>();
	private ArrayList<String> misHabilidades = new ArrayList<>();
	private MaskFormatter telefono;
	private MaskFormatter cedula;
	private JDateChooser FechaNacimiento;
	private DefaultListModel<String> modeloIdiomas = new DefaultListModel<>();
	private DefaultListModel<String> modeloHabilidad = new DefaultListModel<>();
	private boolean error = false;
	private boolean estado = false;
	private Solicitante modiS = null;
	private Solicitante verSoli = null;

	/**
	 * Launch the application.
	 * 
	 * public static void main(String[] args) { try { InsertarSolicitate dialog
	 * = new InsertarSolicitate();
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * /** Create the dialog.
	 */
	public InsertarSolicitate(String title, boolean modificar, Solicitante solicitante, Solicitante persona) {
		setBackground(new Color(248, 248, 255));
		getContentPane().setBackground(new Color(248, 248, 255));
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
				if (modificar) {
					loadSolicitanteModi();
				}
				if (persona != null) {

				}
			}

		});
		modiS = solicitante;
		verSoli = persona;
		try {
			telefono = new MaskFormatter("(###)-###-####");
			cedula = new MaskFormatter("###-#######-#");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setResizable(false);
		setModal(true);
		setTitle(title);
		setBounds(100, 100, 612, 453);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
			panel1 = new JPanel();
			panel1.setBackground(new Color(248, 248, 255));
			contentPanel.add(panel1, "name_42514397155285");
			panel1.setLayout(null);

			panel_infoPersonal = new JPanel();
			panel_infoPersonal.setBackground(new Color(248, 248, 255));
			panel_infoPersonal.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			panel_infoPersonal.setBounds(10, 29, 590, 164);
			panel1.add(panel_infoPersonal);
			panel_infoPersonal.setLayout(null);

			JLabel lblCedula = new JLabel("C\u00E9dula:");
			lblCedula.setBounds(19, 29, 46, 14);
			panel_infoPersonal.add(lblCedula);

			JLabel lblAsterisco = new JLabel("*");
			lblAsterisco.setHorizontalAlignment(SwingConstants.LEFT);
			lblAsterisco.setForeground(Color.RED);
			lblAsterisco.setBounds(75, 29, 38, 14);
			panel_infoPersonal.add(lblAsterisco);

			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(19, 65, 57, 14);
			panel_infoPersonal.add(lblNombre);

			JLabel label = new JLabel("*");
			label.setHorizontalAlignment(SwingConstants.LEFT);
			label.setForeground(Color.RED);
			label.setBounds(75, 100, 38, 14);
			panel_infoPersonal.add(label);

			txtNombre = new JTextField();
			txtNombre.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validacion(e);
				}

			});
			txtNombre.setBackground(new Color(255, 255, 255));
			txtNombre.setBounds(100, 61, 134, 23);
			panel_infoPersonal.add(txtNombre);
			txtNombre.setColumns(10);

			JLabel lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setBounds(19, 100, 57, 14);
			panel_infoPersonal.add(lblApellidos);

			JLabel label_1 = new JLabel("*");
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setForeground(Color.RED);
			label_1.setBounds(75, 65, 38, 14);
			panel_infoPersonal.add(label_1);

			txtApellidos = new JTextField();
			txtApellidos.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validacion(e);
				}

			});
			txtApellidos.setBackground(new Color(255, 255, 255));
			txtApellidos.setBounds(100, 96, 134, 23);
			panel_infoPersonal.add(txtApellidos);
			txtApellidos.setColumns(10);

			JLabel lblNatalicio = new JLabel("Natalicio:");
			lblNatalicio.setBounds(19, 134, 57, 14);
			panel_infoPersonal.add(lblNatalicio);

			FechaNacimiento = new JDateChooser();
			FechaNacimiento.setBackground(new Color(255, 255, 255));
			FechaNacimiento.setBounds(100, 130, 134, 23);
			panel_infoPersonal.add(FechaNacimiento);

			JLabel label_2 = new JLabel("*");
			label_2.setHorizontalAlignment(SwingConstants.LEFT);
			label_2.setForeground(Color.RED);
			label_2.setBounds(75, 134, 38, 14);
			panel_infoPersonal.add(label_2);

			JLabel lblSexo = new JLabel("Sexo:");
			lblSexo.setBounds(306, 29, 46, 14);
			panel_infoPersonal.add(lblSexo);

			textCedula = new JFormattedTextField(cedula);
			textCedula.setBackground(new Color(255, 255, 255));
			textCedula.setBounds(100, 25, 134, 23);
			panel_infoPersonal.add(textCedula);

			rdbMasculino = new JRadioButton("M");
			rdbMasculino.setBackground(new Color(248, 248, 255));
			rdbMasculino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbMasculino.setSelected(true);
					rdbFemenino.setSelected(false);
				}
			});
			rdbMasculino.setBounds(389, 25, 46, 23);
			panel_infoPersonal.add(rdbMasculino);

			rdbFemenino = new JRadioButton("F");
			rdbFemenino.setBackground(new Color(248, 248, 255));
			rdbFemenino.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rdbMasculino.setSelected(false);
					rdbFemenino.setSelected(true);
				}
			});
			rdbFemenino.setBounds(437, 25, 46, 23);
			panel_infoPersonal.add(rdbFemenino);

			JLabel lblEstadoCivil = new JLabel("Estado Civil:");
			lblEstadoCivil.setBounds(306, 65, 73, 14);
			panel_infoPersonal.add(lblEstadoCivil);

			cbxEstadoCilvil = new JComboBox();
			cbxEstadoCilvil.setBackground(new Color(255, 255, 255));
			cbxEstadoCilvil.setModel(new DefaultComboBoxModel(
					new String[] { "< Seleccione >", "Casado / Unido", "Divorceado / Viudo", "Soltero" }));
			cbxEstadoCilvil.setBounds(389, 61, 134, 23);
			panel_infoPersonal.add(cbxEstadoCilvil);

			JLabel lblNacionalidad = new JLabel("Nacionalidad:");
			lblNacionalidad.setBounds(306, 100, 84, 14);
			panel_infoPersonal.add(lblNacionalidad);

			cbxNacionalidad = new JComboBox();
			cbxNacionalidad.setBackground(new Color(255, 255, 255));
			cbxNacionalidad.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Alemana", "Argentina",
					"Belga", "Boliviana", "Brasile\u00F1a", "Canadiense", "China", "Colombiana", "Costarricense",
					"Cubana", "Danesa", "Dominicana", "Espa\u00F1ola", "Filipina", "Francesa", "Griega", "Guatemalteca",
					"Haitiana", "Holandesa", "Hondure\u00F1a", "Inglesa", "Israel\u00ED", "Italiana", "Jamaiquina",
					"Japonesa", "Estadounidense", "Mexicana", "Peruana", "Puertorrique\u00F1a", "Rusa", "Sueca",
					"Suiza", "Surcoreana", "Venezolana" }));
			cbxNacionalidad.setBounds(389, 96, 134, 23);
			panel_infoPersonal.add(cbxNacionalidad);

			JLabel label_3 = new JLabel("*");
			label_3.setHorizontalAlignment(SwingConstants.LEFT);
			label_3.setForeground(Color.RED);
			label_3.setBounds(345, 29, 38, 14);
			panel_infoPersonal.add(label_3);

			JLabel lblLicencia = new JLabel("Licencia:");
			lblLicencia.setBounds(306, 134, 73, 14);
			panel_infoPersonal.add(lblLicencia);

			cbxLicencia = new JComboBox();
			cbxLicencia.setBackground(new Color(255, 255, 255));
			cbxLicencia.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "No Poseo Licencia",
					"Categor\u00EDa 1", "Categor\u00EDa 2", "Categor\u00EDa 3", "Categor\u00EDa 4" }));
			cbxLicencia.setBounds(389, 130, 134, 23);
			panel_infoPersonal.add(cbxLicencia);

			JLabel label_5 = new JLabel("*");
			label_5.setHorizontalAlignment(SwingConstants.LEFT);
			label_5.setForeground(Color.RED);
			label_5.setBounds(365, 134, 38, 14);
			panel_infoPersonal.add(label_5);

			JLabel lblpriority = new JLabel("Campos con * son obligatorios.");
			lblpriority.setForeground(Color.RED);
			lblpriority.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblpriority.setBounds(445, 11, 155, 13);
			panel1.add(lblpriority);

			JPanel panel_Direccion = new JPanel();
			panel_Direccion.setBackground(new Color(248, 248, 255));
			panel_Direccion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Ubicaci\u00F3n Actual", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_Direccion.setBounds(10, 198, 590, 159);
			panel1.add(panel_Direccion);
			panel_Direccion.setLayout(null);

			JLabel lblProvincia = new JLabel("Provincia:");
			lblProvincia.setBounds(10, 29, 64, 14);
			panel_Direccion.add(lblProvincia);

			JLabel label_4 = new JLabel("*");
			label_4.setHorizontalAlignment(SwingConstants.LEFT);
			label_4.setForeground(Color.RED);
			label_4.setBounds(69, 29, 38, 14);
			panel_Direccion.add(label_4);

			cbxProvincia = new JComboBox();
			cbxProvincia.setBackground(new Color(255, 255, 255));
			cbxProvincia.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Azua", "Bahoruco",
					"Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo",
					"Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana",
					"La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata",
					"Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "S\u00E1nchez Ram\u00EDrez",
					"San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "Santiago",
					"Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde" }));
			cbxProvincia.setBounds(100, 25, 134, 23);
			panel_Direccion.add(cbxProvincia);

			JLabel lblCiudad = new JLabel("Ciudad:");
			lblCiudad.setBounds(307, 29, 46, 14);
			panel_Direccion.add(lblCiudad);

			JLabel lblSector = new JLabel("Sector:");
			lblSector.setBounds(10, 72, 46, 14);
			panel_Direccion.add(lblSector);

			JLabel lblCalle = new JLabel("Calle:");
			lblCalle.setBounds(307, 72, 46, 14);
			panel_Direccion.add(lblCalle);

			JLabel lblReferencia = new JLabel("Referencia:");
			lblReferencia.setBounds(307, 115, 73, 14);
			panel_Direccion.add(lblReferencia);

			txtReferencia = new JTextField();
			txtReferencia.setBackground(new Color(255, 255, 255));
			txtReferencia.setBounds(390, 111, 134, 23);
			panel_Direccion.add(txtReferencia);
			txtReferencia.setColumns(10);

			txtSector = new JTextField();
			txtSector.setBackground(new Color(255, 255, 255));
			txtSector.setBounds(100, 68, 134, 23);
			panel_Direccion.add(txtSector);
			txtSector.setColumns(10);

			txtCiudad = new JTextField();
			txtCiudad.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					validacion(e);
				}

			});
			txtCiudad.setBackground(new Color(255, 255, 255));
			txtCiudad.setBounds(390, 25, 134, 23);
			panel_Direccion.add(txtCiudad);
			txtCiudad.setColumns(10);

			txtCalle = new JTextField();
			txtCalle.setBackground(new Color(255, 255, 255));
			txtCalle.setBounds(390, 68, 134, 23);
			panel_Direccion.add(txtCalle);
			txtCalle.setColumns(10);

			JLabel lblNumeroDeLocalidad = new JLabel("Numero:");
			lblNumeroDeLocalidad.setBounds(10, 115, 64, 14);
			panel_Direccion.add(lblNumeroDeLocalidad);

			spnNumeroCasa = new JSpinner();
			spnNumeroCasa.setBackground(new Color(255, 255, 255));
			spnNumeroCasa.setBounds(100, 111, 134, 23);
			panel_Direccion.add(spnNumeroCasa);
		}

		panel2 = new JPanel();
		panel2.setBackground(new Color(248, 248, 255));
		contentPanel.add(panel2, "name_42539630866140");
		panel2.setLayout(null);

		JLabel label = new JLabel("Campos con * son obligatorios.");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(445, 11, 164, 13);
		panel2.add(label);

		panel_Contactos = new JPanel();
		panel_Contactos.setBackground(new Color(248, 248, 255));
		panel_Contactos
				.setBorder(new TitledBorder(null, "Contactos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Contactos.setBounds(10, 22, 590, 62);
		panel2.add(panel_Contactos);
		panel_Contactos.setLayout(null);

		JLabel lblTelefono = new JLabel("T\u00E9lefono:");
		lblTelefono.setBounds(10, 24, 60, 14);
		panel_Contactos.add(lblTelefono);

		txtTelefono = new JFormattedTextField(telefono);
		txtTelefono.setBackground(new Color(255, 255, 255));
		txtTelefono.setBounds(94, 21, 131, 23);
		panel_Contactos.add(txtTelefono);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(266, 24, 46, 14);
		panel_Contactos.add(lblEmail);

		txtEmail = new JFormattedTextField();
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setBounds(336, 21, 226, 23);
		panel_Contactos.add(txtEmail);

		JLabel label_1 = new JLabel("*");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setForeground(Color.RED);
		label_1.setBounds(70, 24, 38, 14);
		panel_Contactos.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setForeground(Color.RED);
		label_2.setBounds(310, 24, 38, 14);
		panel_Contactos.add(label_2);

		JPanel panel_InformacionG = new JPanel();
		panel_InformacionG.setBackground(new Color(248, 248, 255));
		panel_InformacionG.setBorder(
				new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_InformacionG.setBounds(10, 90, 590, 104);
		panel2.add(panel_InformacionG);
		panel_InformacionG.setLayout(null);

		JLabel lblVehculoPropio = new JLabel("Veh\u00EDculo Propio:");
		lblVehculoPropio.setBounds(10, 26, 107, 14);
		panel_InformacionG.add(lblVehculoPropio);

		rdbSiVehiculo = new JRadioButton("S\u00ED");
		rdbSiVehiculo.setBackground(new Color(248, 248, 255));
		rdbSiVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbSiVehiculo.setSelected(true);
				rdbNoVehiculo.setSelected(false);
			}
		});
		rdbSiVehiculo.setBounds(123, 22, 40, 23);
		panel_InformacionG.add(rdbSiVehiculo);

		rdbNoVehiculo = new JRadioButton("No");
		rdbNoVehiculo.setBackground(new Color(248, 248, 255));
		rdbNoVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				rdbSiVehiculo.setSelected(false);
				rdbNoVehiculo.setSelected(true);
			}
		});
		rdbNoVehiculo.setBounds(165, 22, 53, 23);
		panel_InformacionG.add(rdbNoVehiculo);

		JLabel lblDiponibilidad = new JLabel("Diponible Reelocalizaci\u00F3n:");
		lblDiponibilidad.setBounds(10, 67, 162, 14);
		panel_InformacionG.add(lblDiponibilidad);

		rdbSiReelocalizacion = new JRadioButton("S\u00ED");
		rdbSiReelocalizacion.setBackground(new Color(248, 248, 255));
		rdbSiReelocalizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbSiReelocalizacion.setSelected(true);
				rdbNoReelocalizacion.setSelected(false);
			}
		});
		rdbSiReelocalizacion.setBounds(178, 63, 40, 23);
		panel_InformacionG.add(rdbSiReelocalizacion);

		rdbNoReelocalizacion = new JRadioButton("No");
		rdbNoReelocalizacion.setBackground(new Color(248, 248, 255));
		rdbNoReelocalizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbSiReelocalizacion.setSelected(false);
				rdbNoReelocalizacion.setSelected(true);
			}
		});
		rdbNoReelocalizacion.setBounds(220, 63, 53, 23);
		panel_InformacionG.add(rdbNoReelocalizacion);

		JLabel label_3 = new JLabel("*");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setForeground(Color.RED);
		label_3.setBounds(111, 26, 38, 14);
		panel_InformacionG.add(label_3);

		JLabel label_4 = new JLabel("*");
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setForeground(Color.RED);
		label_4.setBounds(167, 67, 38, 14);
		panel_InformacionG.add(label_4);

		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLUE);
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(293, 11, 2, 82);
		panel_InformacionG.add(separator);

		JLabel lblIdiomas = new JLabel("Idiomas:");
		lblIdiomas.setBounds(340, 22, 71, 14);
		panel_InformacionG.add(lblIdiomas);

		cbxIdiomas = new JComboBox();
		cbxIdiomas.setBackground(new Color(248, 248, 255));
		cbxIdiomas.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!misIdiomas.contains(cbxIdiomas.getSelectedItem().toString())
						&& cbxIdiomas.getSelectedIndex() > 0) {
					misIdiomas.add(cbxIdiomas.getSelectedItem().toString());
					modeloIdiomas.removeAllElements();
					loadIdioma();
					cbxIdiomas.setSelectedIndex(0);
				} else if (misIdiomas.contains(cbxIdiomas.getSelectedItem().toString())) {
					panel2.setVisible(true);
				}
			}
		});
		cbxIdiomas.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Afrikaans", "Alban\u00E9s",
				"Alem\u00E1n", "Amharico", "Arabe", "Armenio", "Bengali", "Bieloruso", "Birman\u00E9s", "Bulgaro",
				"Catalan", "Checo", "Chino", "Coreano", "Croata", "Dan\u00E9s", "Dari", "Dzongkha", "Escoc\u00E9s",
				"Eslovaco", "Esloveniano", "Espa\u00F1ol", "Esperanto", "Estoniano", "Faroese", "Farsi",
				"Finland\u00E9s", "Franc\u00E9s", "Gaelico", "Galese", "Gallego", "Griego", "Hebreo", "Hindi",
				"Holand\u00E9s", "Hungaro", "Ingl\u00E9s", "Indonesio", "Inuktitut (Eskimo)", "Islandico", "Italiano",
				"Japon\u00E9s", "Khmer", "Kurdo", "Lao", "Laponico", "Latviano", "Lituano", "Macedonio", "Malay\u00E9s",
				"Malt\u00E9s", "Nepali", "Noruego", "Pashto", "Polaco", "Portugu\u00E9s", "Rumano", "Ruso", "Serbio",
				"Somali", "Suahili", "Sueco", "Tagalog-Filipino", "Tajik", "Tamil", "Tailand\u00E9s", "Tibetano",
				"Tigrinia", "Tongan\u00E9s", "Turco", "Turkmenistano", "Ucraniano", "Urdu", "Uzbekistano", "Vasco",
				"Vietnam\u00E9s" }));
		cbxIdiomas.setBounds(305, 45, 123, 23);
		panel_InformacionG.add(cbxIdiomas);

		JScrollPane scrollPaneIdiomas = new JScrollPane();
		scrollPaneIdiomas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneIdiomas.setBounds(438, 26, 142, 67);
		panel_InformacionG.add(scrollPaneIdiomas);

		listIdiomas = new JList();
		listIdiomas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnEliminarIdioma.setEnabled(true);
			}
		});
		listIdiomas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		scrollPaneIdiomas.setViewportView(listIdiomas);

		btnEliminarIdioma = new JButton("Remover");
		btnEliminarIdioma.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listIdiomas.getSelectedIndex();
				String idioma = listIdiomas.getSelectedValue().toString();
				modeloIdiomas.remove(index);
				eliminarIdioma(idioma);
				btnEliminarIdioma.setEnabled(false);

			}
		});
		btnEliminarIdioma.setEnabled(false);
		btnEliminarIdioma.setBounds(322, 70, 89, 23);
		panel_InformacionG.add(btnEliminarIdioma);

		JPanel panel_TipoSolicitante = new JPanel();
		panel_TipoSolicitante.setBackground(new Color(248, 248, 255));
		panel_TipoSolicitante.setBorder(
				new TitledBorder(null, "Tipo de Solicitante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TipoSolicitante.setBounds(10, 200, 590, 49);
		panel2.add(panel_TipoSolicitante);
		panel_TipoSolicitante.setLayout(null);

		rdbObrero = new JRadioButton("Obrero");
		rdbObrero.setBackground(new Color(248, 248, 255));
		rdbObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegistrar.setEnabled(true);
				rdbObrero.setSelected(true);
				rdbTecnico.setSelected(false);
				rdbUniversitario.setSelected(false);
				panel_Obreo.setVisible(true);
				panel_Tecnico.setVisible(false);
				panel_Universitario.setVisible(false);
			}
		});
		rdbObrero.setBounds(65, 19, 109, 23);
		panel_TipoSolicitante.add(rdbObrero);

		rdbTecnico = new JRadioButton("T\u00E9cnico");
		rdbTecnico.setBackground(new Color(248, 248, 255));
		rdbTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegistrar.setEnabled(true);
				rdbObrero.setSelected(false);
				rdbTecnico.setSelected(true);
				rdbUniversitario.setSelected(false);
				panel_Obreo.setVisible(false);
				panel_Tecnico.setVisible(true);
				panel_Universitario.setVisible(false);
			}
		});
		rdbTecnico.setBounds(239, 19, 109, 23);
		panel_TipoSolicitante.add(rdbTecnico);

		rdbUniversitario = new JRadioButton("Universitario");
		rdbUniversitario.setBackground(new Color(248, 248, 255));
		rdbUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnRegistrar.setEnabled(true);
				rdbObrero.setSelected(false);
				rdbTecnico.setSelected(false);
				rdbUniversitario.setSelected(true);
				panel_Obreo.setVisible(false);
				panel_Tecnico.setVisible(false);
				panel_Universitario.setVisible(true);
			}
		});
		rdbUniversitario.setBounds(413, 19, 109, 23);
		panel_TipoSolicitante.add(rdbUniversitario);

		JLabel label_12 = new JLabel("*");
		label_12.setHorizontalAlignment(SwingConstants.LEFT);
		label_12.setForeground(Color.RED);
		label_12.setBounds(10, 23, 38, 14);
		panel_TipoSolicitante.add(label_12);

		panel_Obreo = new JPanel();
		panel_Obreo.setBackground(new Color(248, 248, 255));
		panel_Obreo.setBorder(new TitledBorder(null, "Obrero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Obreo.setBounds(10, 255, 590, 104);
		panel2.add(panel_Obreo);
		panel_Obreo.setLayout(null);

		JLabel lblAnosExperienciaObrero = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExperienciaObrero.setBounds(10, 49, 140, 14);
		panel_Obreo.add(lblAnosExperienciaObrero);

		spnAnnosExpObrero = new JSpinner();
		spnAnnosExpObrero.setBounds(146, 46, 123, 23);
		panel_Obreo.add(spnAnnosExpObrero);

		JLabel label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.RED);
		label_5.setBounds(133, 49, 38, 14);
		panel_Obreo.add(label_5);

		JLabel lblHabilidaes = new JLabel("Habilidades");
		lblHabilidaes.setBounds(340, 20, 70, 14);
		panel_Obreo.add(lblHabilidaes);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLUE);
		separator_1.setBounds(293, 11, 2, 82);
		panel_Obreo.add(separator_1);

		cbxHabilidades = new JComboBox();
		cbxHabilidades.setBackground(new Color(248, 248, 255));
		cbxHabilidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!misHabilidades.contains(cbxHabilidades.getSelectedItem().toString())
						&& cbxHabilidades.getSelectedIndex() > 0) {
					misHabilidades.add(cbxHabilidades.getSelectedItem().toString());
					modeloHabilidad.removeAllElements();
					loadHabilidades();
				} else if (misHabilidades.contains(cbxHabilidades.getSelectedItem().toString())) {
					panel2.setVisible(true);
				}

			}
		});
		cbxHabilidades.setModel(new DefaultComboBoxModel(
				new String[] { "< Seleccione >", "Alba\u00F1il", "Anfitri\u00F3n de Fiesta", "Artesano", "Carpintero",
						"Chofer", "Chef", "Constructor", "Decorador", "Ebanista", "Electricista", "Mec\u00E1nico",
						"Pintor", "Plomero", "Salva Vidas", "Modista", "Seguridad", "Sirviente", "Jardinero" }));
		cbxHabilidades.setBounds(305, 40, 123, 23);
		panel_Obreo.add(cbxHabilidades);

		btnRemoverHabilidad = new JButton("Remover");
		btnRemoverHabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = listHabilidades.getSelectedIndex();
				String habilidad = listHabilidades.getSelectedValue().toString();
				modeloHabilidad.remove(index);
				eliminarHabilidad(habilidad);
				btnRemoverHabilidad.setEnabled(false);
			}
		});
		btnRemoverHabilidad.setEnabled(false);
		btnRemoverHabilidad.setBounds(322, 70, 89, 23);
		panel_Obreo.add(btnRemoverHabilidad);

		JScrollPane scrollPaneHabilidades = new JScrollPane();
		scrollPaneHabilidades.setBounds(440, 28, 140, 65);
		panel_Obreo.add(scrollPaneHabilidades);

		listHabilidades = new JList();
		listHabilidades.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnRemoverHabilidad.setEnabled(true);
			}
		});
		listHabilidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneHabilidades.setViewportView(listHabilidades);

		JLabel label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.RED);
		label_7.setBounds(402, 20, 38, 14);
		panel_Obreo.add(label_7);

		panel_Tecnico = new JPanel();
		panel_Tecnico.setBackground(new Color(248, 248, 255));
		panel_Tecnico
				.setBorder(new TitledBorder(null, "T\u00E9cnico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Tecnico.setBounds(10, 255, 590, 104);
		panel2.add(panel_Tecnico);
		panel_Tecnico.setLayout(null);

		JLabel lblAnosExperienciaTec = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExperienciaTec.setBounds(10, 49, 132, 14);
		panel_Tecnico.add(lblAnosExperienciaTec);

		JLabel label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.RED);
		label_8.setBounds(132, 44, 38, 14);
		panel_Tecnico.add(label_8);

		spnAnosExpTecnico = new JSpinner();
		spnAnosExpTecnico.setBackground(new Color(248, 248, 255));
		spnAnosExpTecnico.setBounds(146, 46, 123, 23);
		panel_Tecnico.add(spnAnosExpTecnico);

		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(310, 49, 38, 14);
		panel_Tecnico.add(lblrea);

		JLabel label_9 = new JLabel("*");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setForeground(Color.RED);
		label_9.setBounds(348, 44, 38, 14);
		panel_Tecnico.add(label_9);

		cbxAreaTecnico = new JComboBox();
		cbxAreaTecnico.setBackground(new Color(248, 248, 255));
		cbxAreaTecnico
				.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Emprendimiento", "Mecanograf\u00EDa", "Dise\u00F1o Gr\u00E1fico", "Programaci\u00F3n", "Contabilidad", "Programaci\u00F3n Web"}));
		cbxAreaTecnico.setBounds(396, 46, 123, 23);
		panel_Tecnico.add(cbxAreaTecnico);

		JSeparator separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBackground(Color.BLUE);
		separator_3.setBounds(293, 11, 2, 82);
		panel_Tecnico.add(separator_3);

		panel_Universitario = new JPanel();
		panel_Universitario.setBackground(new Color(248, 248, 255));
		panel_Universitario
				.setBorder(new TitledBorder(null, "Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Universitario.setBounds(10, 255, 590, 104);
		panel2.add(panel_Universitario);
		panel_Universitario.setLayout(null);

		JLabel lblAnosExpUniversitario = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExpUniversitario.setBounds(10, 49, 132, 14);
		panel_Universitario.add(lblAnosExpUniversitario);

		spnAnosExpUniversitario = new JSpinner();
		spnAnosExpUniversitario.setBackground(new Color(248, 248, 255));
		spnAnosExpUniversitario.setBounds(146, 46, 123, 23);
		panel_Universitario.add(spnAnosExpUniversitario);

		JLabel label_10 = new JLabel("*");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.RED);
		label_10.setBounds(130, 51, 38, 14);
		panel_Universitario.add(label_10);

		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(305, 26, 65, 14);
		panel_Universitario.add(lblCarrera);

		JLabel label_11 = new JLabel("*");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setForeground(Color.RED);
		label_11.setBounds(362, 26, 38, 14);
		panel_Universitario.add(label_11);

		cbxCarrera = new JComboBox();
		cbxCarrera.setBackground(new Color(248, 248, 255));
		cbxCarrera.setModel(new DefaultComboBoxModel(new String[] { "< Seleccione >", "Adm. de Empresas",
				"Adm. Hotelera", "Derecho", "Econom\u00EDa", "Contabilidad", "Mercadotecnia", "Arquitectura",
				"Comunicacion Social", "Dise\u00F1o e Interiorismo", "Ecologia", "Educaci\u00F3n", "Filosof\u00EDa",
				"Psicolog\u00EDa", "Ing. Civil", "Ing. Electr\u00F3nica", "Ing. Industrial", "Ing. Mecatr\u00F3nica",
				"Ing. Sistema", "Ing. Telem\u00E1tica", "Enfermeria", "Estomatolog\u00EDa", "Medicina",
				"Nutricion y Dietetica", "Terapia F\u00EDsica" }));
		cbxCarrera.setBounds(391, 23, 123, 23);
		panel_Universitario.add(cbxCarrera);

		JLabel lblPostgrado = new JLabel("PostGrado:");
		lblPostgrado.setBounds(305, 64, 65, 14);
		panel_Universitario.add(lblPostgrado);

		JLabel label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.RED);
		label_6.setBounds(369, 64, 38, 14);
		panel_Universitario.add(label_6);

		rdbSiPost = new JRadioButton("S\u00ED");
		rdbSiPost.setBounds(391, 60, 38, 23);
		panel_Universitario.add(rdbSiPost);

		rdbNoPost = new JRadioButton("No");
		rdbNoPost.setBounds(431, 60, 58, 23);
		panel_Universitario.add(rdbNoPost);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBackground(Color.BLUE);
		separator_2.setBounds(293, 11, 2, 82);
		panel_Universitario.add(separator_2);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnRegistrar = new JButton("Registrar");
				if (!modificar) {
					btnRegistrar.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/add.png")));
				} else {
					btnRegistrar.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/modificar.png")));
				}

				btnRegistrar.setEnabled(false);
				btnRegistrar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean error = false;

						String cedula = textCedula.getText();
						String nombre = txtNombre.getText();
						String apellido = txtApellidos.getText();
						String telefono = txtTelefono.getText();
						String estadoCivil = cbxEstadoCilvil.getSelectedItem().toString();
						String nacionalidad = cbxNacionalidad.getSelectedItem().toString();
						String sexo = "";
						String provincia = cbxProvincia.getSelectedItem().toString();
						String ciudad = txtCiudad.getText();
						String sector = txtSector.getText();
						String calle = txtCalle.getText();
						String direccion = "";
						int numeroCasa = new Integer((int) spnNumeroCasa.getValue());
						String referencia = txtReferencia.getText();
						String email = txtEmail.getText();
						Date fechaN = FechaNacimiento.getDate();
						LocalDate fechaNacimiento = fechaN.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						boolean vehiculoP = false;
						boolean postGrado = false;
						boolean mudarse = false;
						if (rdbSiReelocalizacion.isSelected()) {
							mudarse = true;
						} else if (rdbNoReelocalizacion.isSelected()) {
							mudarse = false;
						}
						if (rdbSiVehiculo.isSelected()) {
							vehiculoP = true;
						} else if (rdbNoVehiculo.isSelected()) {
							vehiculoP = false;
						}
						int licencia = 0;
						if (cbxLicencia.getSelectedIndex() == 1) {
							licencia = 0;
						}
						if (cbxLicencia.getSelectedIndex() == 2) {
							licencia = 1;
						}
						if (cbxLicencia.getSelectedIndex() == 3) {
							licencia = 2;
						}
						if (cbxLicencia.getSelectedIndex() == 4) {
							licencia = 3;
						}
						if (cbxLicencia.getSelectedIndex() == 5) {
							licencia = 4;
						}
						if (rdbFemenino.isSelected()) {
							sexo = "Femenino";
						}
						if (rdbMasculino.isSelected()) {
							sexo = "Masculino";
						}
						if (rdbSiPost.isSelected()) {
							postGrado = true;
						}
						if (telefono.equalsIgnoreCase("") || email.equalsIgnoreCase("")) {
							error = true;
							JOptionPane.showMessageDialog(null, "Favor llenar todos los campos.", "ATENCIÓN",
									JOptionPane.WARNING_MESSAGE, null);
							if (panel2.isVisible()) {
								panel1.setVisible(false);
								panel2.setVisible(true);
							}
						} else if ((!rdbNoReelocalizacion.isSelected() && !rdbSiReelocalizacion.isSelected())
								|| (!rdbSiVehiculo.isSelected() && !rdbNoVehiculo.isSelected())) {
							error = true;
							JOptionPane.showMessageDialog(null, "Favor llenar todos los campos.", "ATENCIÓN",
									JOptionPane.WARNING_MESSAGE, null);

						} else if (rdbObrero.isSelected()) {
							if (misHabilidades.size() == 0) {
								error = true;
								JOptionPane.showMessageDialog(null, "Favor insertar habilidades de Oberro.", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);

							}
						} else if (rdbTecnico.isSelected()) {
							if (cbxAreaTecnico.getSelectedIndex() == 0) {
								error = true;
								JOptionPane.showMessageDialog(null, "Favor insetar area de Ténico.", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);

							}
						} else if (rdbUniversitario.isSelected()) {
							if (cbxCarrera.getSelectedIndex() == 0) {
								error = true;
								JOptionPane.showMessageDialog(null, "Favor insetar carrera de Universitario.",
										"ATENCIÓN", JOptionPane.WARNING_MESSAGE, null);

							} else if (BolsaLaboral.getInstance().validarEmail(email)) {
								error = true;
								JOptionPane.showMessageDialog(null, "Correo Electrónico no válido", "ATENCIÓN",
										JOptionPane.ERROR_MESSAGE, null);

							}
						}
						if (rdbObrero.isSelected()) {
							if (!error) {
								if (!modificar) {
									int annos = new Integer((int) spnAnnosExpObrero.getValue());
									Solicitante solicitante = new Obrero(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia, misHabilidades);

									BolsaLaboral.getInstance().insertSolicitante(solicitante);
									estado = false;
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha registrado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);

									Principal.actualizarChart();
									clean();
									// call();

								}
								if (modificar) {
									int annos = new Integer((int) spnAnnosExpObrero.getValue());
									Solicitante solicitante = new Obrero(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia, misHabilidades);
									BolsaLaboral.getInstance().updateSolicitante(solicitante);
									estado = false;
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha modificado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);
									Principal.actualizarChart();
									dispose();

								}
							}
						}
						if (rdbUniversitario.isSelected()) {
							if (!error) {
								if (!modificar) {
									int annos = new Integer((int) spnAnosExpUniversitario.getValue());
									Solicitante solicitante = new Universitario(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia, false,
											cbxCarrera.getSelectedItem().toString());
									BolsaLaboral.getInstance().insertSolicitante(solicitante);
									estado = false;
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha registrado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);
									Principal.actualizarChart();
									clean();

								}
								if (modificar) {
									int annos = new Integer((int) spnAnosExpUniversitario.getValue());
									Solicitante solicitante = new Universitario(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia, false,
											cbxCarrera.getSelectedItem().toString());

									estado = false;
									BolsaLaboral.getInstance().updateSolicitante(solicitante);
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha modificado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);

									Principal.actualizarChart();
									dispose();
								}
							}
						}
						if (rdbTecnico.isSelected()) {
							if (!error) {
								if (!modificar) {
									int annos = new Integer((int) spnAnosExpTecnico.getValue());
									Solicitante solicitante = new Tecnico(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia,
											cbxAreaTecnico.getSelectedItem().toString());
									BolsaLaboral.getInstance().insertSolicitante(solicitante);
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha registrado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);
									BolsaLaboral.getInstance().updateSolicitante(solicitante);
									Principal.actualizarChart();
									estado = false;
									clean();
								}
								if (modificar) {
									int annos = new Integer((int) spnAnosExpTecnico.getValue());
									Solicitante solicitante = new Tecnico(cedula, nombre, apellido, telefono,
											fechaNacimiento, nacionalidad, sexo, estadoCivil, direccion, provincia,
											email, vehiculoP, licencia, annos, misIdiomas, postGrado, mudarse, ciudad,
											sector, calle, numeroCasa, referencia,
											cbxAreaTecnico.getSelectedItem().toString());
									BolsaLaboral.getInstance().updateSolicitante(solicitante);
									JOptionPane.showMessageDialog(null,
											"El solicitante se ha modificado de manera exitosa.", "Información",
											JOptionPane.INFORMATION_MESSAGE, null);
									estado = false;
									dispose();
								}
							}
						}
					}
				});
				{
					btnMover = new JButton("Continuar");
					btnMover.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/Siguiente.png")));
					btnMover.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if(panel2.isVisible()){
								btnMover.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/Siguiente.png")));
							}else{
								btnMover.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/retroceso.png")));
							}
							
							String fecha = ((JTextField) FechaNacimiento.getDateEditor().getUiComponent()).getText();
							String sexo = "";
							if (rdbFemenino.isSelected()) {
								sexo = "Femenino";
							} else if (rdbMasculino.isSelected()) {
								sexo = "Masculino";
							}
							if (panel2.isVisible()) {
								estado = false;
								panel2.setVisible(false);
								panel1.setVisible(true);
								btnMover.setText("Continuar");

							} else if (panel1.isVisible()) {
								if (txtApellidos.getText().isEmpty() || txtNombre.getText().isEmpty()
										|| textCedula.getText().isEmpty() || fecha.equalsIgnoreCase("")
										|| cbxLicencia.getSelectedIndex() == 0 || sexo.equalsIgnoreCase("")
										|| cbxProvincia.getSelectedIndex() == 0) {
									JOptionPane.showMessageDialog(null, "Favor llenar todos los campos.", "ATENCIÓN",
											JOptionPane.WARNING_MESSAGE, null);
								} else {
									if (!modificar) {
										estado = true;
										btnRegistrar.setEnabled(true);
										rdbObrero.setSelected(true);
										rdbTecnico.setSelected(false);
										rdbUniversitario.setSelected(false);
										panel_Obreo.setVisible(true);
										panel_Tecnico.setVisible(false);
										panel_Universitario.setVisible(false);
										panel1.setVisible(false);
										panel2.setVisible(true);
										btnMover.setText("Retroceder");

									} else {
										btnRegistrar.setEnabled(true);
										btnMover.setText("Retroceder");
										panel1.setVisible(false);
										panel2.setVisible(true);
									}
									if (persona != null) {

										btnRegistrar.setEnabled(false);

									}
								}
							}
						}
					});
					btnMover.setActionCommand("OK");
					buttonPane.add(btnMover);
					getRootPane().setDefaultButton(btnMover);
				}
				buttonPane.add(btnRegistrar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(InsertarSolicitate.class.getResource("/img/cancelar.png")));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		estado();
	}

	public void loadIdioma() {
		for (String idioma : misIdiomas) {
			modeloIdiomas.addElement(idioma);
		}
		listIdiomas.setModel(modeloIdiomas);
	}

	public void loadHabilidades() {
		for (String habilidad : misHabilidades) {
			modeloHabilidad.addElement(habilidad);
		}
		listHabilidades.setModel(modeloHabilidad);
	}

	public void eliminarIdioma(String idioma) {
		int index = 0;
		for (int i = 0; i < misIdiomas.size(); i++) {
			if (misIdiomas.get(i).equalsIgnoreCase(idioma)) {
				index = i;
			}
		}
		misIdiomas.remove(index);
	}

	public void eliminarHabilidad(String habilidad) {
		int index = 0;
		for (int i = 0; i < misHabilidades.size(); i++) {
			if (misHabilidades.get(i).equalsIgnoreCase(habilidad)) {
				index = i;
			}
		}
		misHabilidades.remove(index);
	}

	public void resetWindow() {
		rdbObrero.setSelected(true);
		rdbTecnico.setSelected(false);
		rdbUniversitario.setSelected(false);
		panel_Obreo.setVisible(true);
		panel_Tecnico.setVisible(false);
		panel_Universitario.setVisible(false);
		panel1.setVisible(false);
		panel2.setVisible(true);
		btnMover.setText("<< Retroceder");
		error = false;

	}

	public void clean() {
		txtNombre.setText("");
		txtApellidos.setText("");
		txtCalle.setText("");
		txtCiudad.setText("");
		txtEmail.setText("");
		textCedula.setText("");
		txtReferencia.setText("");
		txtSector.setText("");
		txtTelefono.setText("");
		textCedula.setText("");
		cbxAreaTecnico.setSelectedIndex(0);
		cbxCarrera.setSelectedIndex(0);
		cbxEstadoCilvil.setSelectedIndex(0);
		cbxHabilidades.setSelectedIndex(0);
		cbxIdiomas.setSelectedIndex(0);
		cbxLicencia.setSelectedIndex(0);
		cbxNacionalidad.setSelectedIndex(0);
		cbxProvincia.setSelectedIndex(0);
		rdbObrero.setSelected(true);
		rdbFemenino.setSelected(false);
		rdbMasculino.setSelected(false);
		rdbNoReelocalizacion.setSelected(false);
		rdbSiReelocalizacion.setSelected(false);
		rdbSiVehiculo.setSelected(false);
		rdbNoVehiculo.setSelected(false);
		panel1.setVisible(true);
		panel2.setVisible(false);
		spnAnnosExpObrero.setValue(0);
		spnAnosExpTecnico.setValue(0);
		spnAnosExpUniversitario.setValue(0);
		spnNumeroCasa.setValue(0);
		btnMover.setText("Continuar >>");
		btnRegistrar.setEnabled(false);
		LocalDate fecha = LocalDate.now();
		Date date = java.sql.Date.valueOf(fecha);
		FechaNacimiento.setDate(date);
		misHabilidades = new ArrayList<>();
		misIdiomas = new ArrayList<>();
		modeloHabilidad.clear();
		error = false;
		modeloIdiomas.clear();
	}

	public void estado() {
		Thread ventana = new Thread() {
			public void run() {
				try {
					for (;;) {

						if (estado) {
							panel1.setVisible(false);
							panel2.setVisible(true);
							btnMover.setText("<< Retroceder");
						}

						sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		ventana.start();

	}

	public void call() {
		dispose();
		InsertarSolicitate soli = new InsertarSolicitate("Insertar Solicitante", false, null, null);
		soli.setModal(true);
		soli.setLocationRelativeTo(null);
		soli.setVisible(true);
	}

	public void loadSolicitanteModi() {
		if (modiS != null) {
			btnRegistrar.setText("Modificar");
			LocalDate fecha = modiS.getFechaNacimiento();
			Date date = java.sql.Date.valueOf(fecha);
			txtApellidos.setEnabled(false);
			txtNombre.setEnabled(false);
			rdbFemenino.setEnabled(false);
			rdbMasculino.setEnabled(false);
			textCedula.setEnabled(false);
			FechaNacimiento.setEnabled(false);
			cbxNacionalidad.setEnabled(false);
			rdbSiReelocalizacion.setEnabled(false);
			rdbNoReelocalizacion.setEnabled(false);

			// Incersion de datos
			for (String idio : modiS.getIdiomas()) {
				misIdiomas.add(idio);
			}
			loadIdioma();
			txtCiudad.setText(modiS.getCiudad());
			txtCalle.setText(modiS.getCalle());
			txtSector.setText(modiS.getSector());
			txtReferencia.setText(modiS.getReferencia());
			spnNumeroCasa.setValue(modiS.getNumeroCasa());
			txtNombre.setText(modiS.getNombres());
			txtApellidos.setText(modiS.getApellidos());
			textCedula.setText(modiS.getCedula());
			FechaNacimiento.setDate(date);
			cbxNacionalidad.setSelectedItem(modiS.getNacionalidad());
			cbxEstadoCilvil.setSelectedItem(modiS.getEstadoCivil());
			cbxProvincia.setSelectedItem(modiS.getProvincia());
			txtTelefono.setText(modiS.getTelefono());
			txtEmail.setText(modiS.getEmail());
			if (modiS.getSexo().equalsIgnoreCase("Femenino")) {
				rdbFemenino.setSelected(true);
			} else {
				rdbMasculino.setSelected(true);
			}
			if (modiS.isVehiculoPropio()) {
				rdbSiVehiculo.setSelected(true);
			} else {
				rdbNoVehiculo.setSelected(true);
			}
			if (modiS.getCategoriaLicencia() == 0) {
				cbxLicencia.setSelectedIndex(1);
			}
			if (modiS.getCategoriaLicencia() == 1) {
				cbxLicencia.setSelectedIndex(2);
			}
			if (modiS.getCategoriaLicencia() == 2) {
				cbxLicencia.setSelectedIndex(3);
			}
			if (modiS.getCategoriaLicencia() == 3) {
				cbxLicencia.setSelectedIndex(4);
			}
			if (modiS.getCategoriaLicencia() == 4) {
				cbxLicencia.setSelectedIndex(5);
			}
			if (modiS.isMudarse() == true) {
				rdbSiReelocalizacion.setSelected(true);
			} else {
				rdbNoReelocalizacion.setSelected(true);
			}

			if (modiS instanceof Obrero) {
				for (String habi : ((Obrero) modiS).getHabilidades()) {
					misHabilidades.add(habi);
				}
				loadHabilidades();
				listHabilidades.setEnabled(false);
				spnAnnosExpObrero.setValue(modiS.getAnnosExperiencia());
				cbxHabilidades.setEnabled(false);
				spnAnnosExpObrero.setEnabled(false);
				rdbObrero.setSelected(true);
				rdbTecnico.setSelected(false);
				rdbUniversitario.setSelected(false);
				rdbUniversitario.setEnabled(false);
				rdbTecnico.setEnabled(false);
				rdbObrero.setEnabled(false);
				panel_Obreo.setVisible(true);
				panel_Tecnico.setVisible(false);
				panel_Universitario.setVisible(false);
			}
			if (modiS instanceof Universitario) {
				cbxCarrera.setEnabled(false);
				cbxCarrera.setSelectedItem(((Universitario) modiS).getCarrera());
				spnAnosExpUniversitario.setValue(modiS.getAnnosExperiencia());
				spnAnosExpUniversitario.setEnabled(false);
				rdbObrero.setSelected(false);
				rdbTecnico.setSelected(false);
				rdbUniversitario.setSelected(true);
				panel_Obreo.setVisible(false);
				panel_Tecnico.setVisible(false);
				panel_Universitario.setVisible(true);
				rdbUniversitario.setEnabled(false);
				rdbTecnico.setEnabled(false);
				rdbObrero.setEnabled(false);
				rdbNoPost.setEnabled(false);
				rdbSiPost.setEnabled(false);
				if (((Universitario) modiS).isPostGrado()) {
					rdbSiPost.setSelected(true);
				} else {
					rdbNoPost.setSelected(true);
				}
			}
			if (modiS instanceof Tecnico) {
				spnAnosExpTecnico.setValue(modiS.getAnnosExperiencia());
				spnAnosExpTecnico.setEnabled(false);
				cbxAreaTecnico.setSelectedItem(((Tecnico) modiS).getArea());
				cbxAreaTecnico.setEnabled(false);
				rdbObrero.setSelected(false);
				rdbTecnico.setSelected(true);
				rdbUniversitario.setSelected(false);
				panel_Obreo.setVisible(false);
				panel_Tecnico.setVisible(true);
				panel_Universitario.setVisible(false);
				rdbUniversitario.setEnabled(false);
				rdbTecnico.setEnabled(false);
				rdbObrero.setEnabled(false);
			}

		}
	}

	public void laodVer() {
		panel1.setEnabled(false);
		panel2.setEnabled(false);
		LocalDate fecha = verSoli.getFechaNacimiento();
		Date date = java.sql.Date.valueOf(fecha);
		txtApellidos.setEnabled(false);
		txtNombre.setEnabled(false);
		rdbFemenino.setEnabled(false);
		rdbMasculino.setEnabled(false);
		textCedula.setEnabled(false);
		FechaNacimiento.setEnabled(false);
		cbxNacionalidad.setEnabled(false);
		rdbSiReelocalizacion.setEnabled(false);
		rdbNoReelocalizacion.setEnabled(false);

		// Incersion de datos
		for (String idio : verSoli.getIdiomas()) {
			misIdiomas.add(idio);
		}
		loadIdioma();
		txtCiudad.setText(verSoli.getCiudad());
		txtCalle.setText(verSoli.getCalle());
		txtSector.setText(verSoli.getSector());
		txtReferencia.setText(verSoli.getReferencia());
		spnNumeroCasa.setValue(verSoli.getNumeroCasa());
		txtNombre.setText(verSoli.getNombres());
		txtApellidos.setText(verSoli.getApellidos());
		textCedula.setText(verSoli.getCedula());
		FechaNacimiento.setDate(date);
		cbxNacionalidad.setSelectedItem(verSoli.getNacionalidad());
		cbxEstadoCilvil.setSelectedItem(verSoli.getEstadoCivil());
		cbxProvincia.setSelectedItem(verSoli.getProvincia());
		txtTelefono.setText(verSoli.getTelefono());
		txtEmail.setText(verSoli.getEmail());

		if (verSoli.getSexo().equalsIgnoreCase("Femenino")) {
			rdbFemenino.setSelected(true);
		} else {
			rdbMasculino.setSelected(true);
		}
		if (verSoli.isVehiculoPropio()) {
			rdbSiVehiculo.setSelected(true);
		} else {
			rdbNoVehiculo.setSelected(true);
		}
		if (verSoli.getCategoriaLicencia() == 0) {
			cbxLicencia.setSelectedIndex(1);
		}
		if (verSoli.getCategoriaLicencia() == 1) {
			cbxLicencia.setSelectedIndex(2);
		}
		if (verSoli.getCategoriaLicencia() == 2) {
			cbxLicencia.setSelectedIndex(3);
		}
		if (verSoli.getCategoriaLicencia() == 3) {
			cbxLicencia.setSelectedIndex(4);
		}
		if (verSoli.getCategoriaLicencia() == 4) {
			cbxLicencia.setSelectedIndex(5);
		}
		if (verSoli.isMudarse() == true) {
			rdbSiReelocalizacion.setSelected(true);
		} else {
			rdbNoReelocalizacion.setSelected(true);
		}

		if (modiS instanceof Obrero) {
			for (String habi : ((Obrero) verSoli).getHabilidades()) {
				misHabilidades.add(habi);
			}
			loadHabilidades();
			listHabilidades.setEnabled(false);
			spnAnnosExpObrero.setValue(verSoli.getAnnosExperiencia());
			cbxHabilidades.setEnabled(false);
			spnAnnosExpObrero.setEnabled(false);
			rdbObrero.setSelected(true);
			rdbTecnico.setSelected(false);
			rdbUniversitario.setSelected(false);
			rdbUniversitario.setEnabled(false);
			rdbTecnico.setEnabled(false);
			rdbObrero.setEnabled(false);
			panel_Obreo.setVisible(true);
			panel_Tecnico.setVisible(false);
			panel_Universitario.setVisible(false);
		}
		if (modiS instanceof Universitario) {
			cbxCarrera.setEnabled(false);
			cbxCarrera.setSelectedItem(((Universitario) verSoli).getCarrera());
			spnAnosExpUniversitario.setValue(verSoli.getAnnosExperiencia());
			spnAnosExpUniversitario.setEnabled(false);
			rdbObrero.setSelected(false);
			rdbTecnico.setSelected(false);
			rdbUniversitario.setSelected(true);
			panel_Obreo.setVisible(false);
			panel_Tecnico.setVisible(false);
			panel_Universitario.setVisible(true);
			rdbUniversitario.setEnabled(false);
			rdbTecnico.setEnabled(false);
			rdbObrero.setEnabled(false);
			rdbNoPost.setEnabled(false);
			rdbSiPost.setEnabled(false);
			if (((Universitario) verSoli).isPostGrado()) {
				rdbSiPost.setSelected(true);
			} else {
				rdbNoPost.setSelected(true);
			}
		}
		if (modiS instanceof Tecnico) {
			spnAnosExpTecnico.setValue(verSoli.getAnnosExperiencia());
			spnAnosExpTecnico.setEnabled(false);
			cbxAreaTecnico.setSelectedItem(((Tecnico) verSoli).getArea());
			cbxAreaTecnico.setEnabled(false);
			rdbObrero.setSelected(false);
			rdbTecnico.setSelected(true);
			rdbUniversitario.setSelected(false);
			panel_Obreo.setVisible(false);
			panel_Tecnico.setVisible(true);
			panel_Universitario.setVisible(false);
			rdbUniversitario.setEnabled(false);
			rdbTecnico.setEnabled(false);
			rdbObrero.setEnabled(false);
		}

	}

	public void validacion(KeyEvent e) {
		char c = e.getKeyChar();
		if (Character.isDigit(c)) {
			getToolkit().beep();

			e.consume();
			JOptionPane.showMessageDialog(null, "Este campo no adminte número");
		}

	}
}
