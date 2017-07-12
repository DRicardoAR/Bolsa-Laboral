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
import com.toedter.calendar.JDateChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;

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
	private MaskFormatter telefono;
	private MaskFormatter cedula;
	
	
	
	
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarSolicitate dialog = new InsertarSolicitate();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public InsertarSolicitate() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				panel1.setVisible(true);
				panel2.setVisible(false);
			}
		});
		try {
			telefono = new MaskFormatter("(###)-###-####");
			cedula	= new MaskFormatter("###-#######-#");
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setTitle("Registrar Solicitante");
		setBounds(100, 100, 626, 460);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new CardLayout(0, 0));
		{
		    panel1 = new JPanel();
			contentPanel.add(panel1, "name_42514397155285");
			panel1.setLayout(null);
			
			panel_infoPersonal = new JPanel();
			panel_infoPersonal.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_infoPersonal.setBounds(10, 22, 590, 159);
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
			txtNombre.setBounds(100, 59, 134, 20);
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
			txtApellidos.setBounds(100, 94, 134, 20);
			panel_infoPersonal.add(txtApellidos);
			txtApellidos.setColumns(10);
			
			JLabel lblNatalicio = new JLabel("Natalicio:");
			lblNatalicio.setBounds(19, 134, 57, 14);
			panel_infoPersonal.add(lblNatalicio);
			
			JDateChooser FechaNacimiento = new JDateChooser();
			FechaNacimiento.setBounds(100, 128, 134, 20);
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
			textCedula.setBounds(100, 23, 134, 20);
			panel_infoPersonal.add(textCedula);
			
		    rdbMasculino = new JRadioButton("M");
		    rdbMasculino.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		    		rdbMasculino.setSelected(true);
		    		rdbFemenino.setSelected(false);
		    	}
		    });
			rdbMasculino.setBounds(389, 25, 46, 23);
			panel_infoPersonal.add(rdbMasculino);
			
		    rdbFemenino = new JRadioButton("F");
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
			cbxEstadoCilvil.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Casado / Unido", "Divorceado / Viudo", "Soltero"}));
			cbxEstadoCilvil.setBounds(389, 62, 134, 20);
			panel_infoPersonal.add(cbxEstadoCilvil);
			
			JLabel lblNacionalidad = new JLabel("Nacionalidad:");
			lblNacionalidad.setBounds(306, 100, 84, 14);
			panel_infoPersonal.add(lblNacionalidad);
			
			cbxNacionalidad = new JComboBox();
			cbxNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Alemana", "Argentina", "Belga", "Boliviana", "Brasile\u00F1a", "Canadiense", "China", "Colombiana", "Costarricense", "Cubana", "Danesa", "Dominicana", "Espa\u00F1ola", "Filipina", "Francesa", "Griega", "Guatemalteca", "Haitiana", "Holandesa", "Hondure\u00F1a", "Inglesa", "Israel\u00ED", "Italiana", "Jamaiquina", "Japonesa", "Estadounidense", "Mexicana", "Peruana", "Puertorrique\u00F1a", "Rusa", "Sueca", "Suiza", "Surcoreana", "Venezolana"}));
			cbxNacionalidad.setBounds(389, 97, 134, 20);
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
			cbxLicencia.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "No Poseo Licencia", "Categor\u00EDa 1", "Categor\u00EDa 2", "Categor\u00EDa 3", "Categor\u00EDa 4"}));
			cbxLicencia.setBounds(389, 131, 134, 20);
			panel_infoPersonal.add(cbxLicencia);
			
			JLabel lblpriority = new JLabel("Campos con * son obligatorios.");
			lblpriority.setForeground(Color.RED);
			lblpriority.setFont(new Font("Tahoma", Font.PLAIN, 10));
			lblpriority.setBounds(458, 11, 142, 13);
			panel1.add(lblpriority);
			
			JPanel panel_Direccion = new JPanel();
			panel_Direccion.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ubicaci\u00F3n Actual", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_Direccion.setBounds(10, 210, 590, 159);
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
			cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Azua", "Bahoruco", "Barahona", "Dajab\u00F3n", "Distrito Nacional", "Duarte", "El\u00EDas Pi\u00F1a", "El Seibo", "Espaillat", "Hato Mayor", "Hermanas Mirabal", "Independencia", "La Altagracia", "La Romana", "La Vega", "Mar\u00EDa Trinidad S\u00E1nchez", "Monse\u00F1or Nouel", "Monte Cristi", "Monte Plata", "Pedernales", "Peravia", "Puerto Plata", "Saman\u00E1", "S\u00E1nchez Ram\u00EDrez", "San Crist\u00F3bal", "San Jos\u00E9 de Ocoa", "San Juan", "San Pedro de Macor\u00EDs", "Santiago", "Santiago Rodr\u00EDguez", "Santo Domingo", "Valverde"}));
			cbxProvincia.setBounds(100, 26, 134, 20);
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
			txtReferencia.setBounds(390, 112, 134, 20);
			panel_Direccion.add(txtReferencia);
			txtReferencia.setColumns(10);
			
			txtSector = new JTextField();
			txtSector.setBounds(100, 69, 134, 20);
			panel_Direccion.add(txtSector);
			txtSector.setColumns(10);
			
			txtCiudad = new JTextField();
			txtCiudad.setBounds(390, 26, 134, 20);
			panel_Direccion.add(txtCiudad);
			txtCiudad.setColumns(10);
			
			txtCalle = new JTextField();
			txtCalle.setBounds(390, 69, 134, 20);
			panel_Direccion.add(txtCalle);
			txtCalle.setColumns(10);
			
			JLabel lblNumeroDeLocalidad = new JLabel("Numero:");
			lblNumeroDeLocalidad.setBounds(10, 115, 64, 14);
			panel_Direccion.add(lblNumeroDeLocalidad);
			
			spnNumeroCasa = new JSpinner();
			spnNumeroCasa.setBounds(100, 112, 134, 20);
			panel_Direccion.add(spnNumeroCasa);
		}
		
		panel2 = new JPanel();
		contentPanel.add(panel2, "name_42539630866140");
		panel2.setLayout(null);
		
		JLabel label = new JLabel("Campos con * son obligatorios.");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(458, 11, 142, 13);
		panel2.add(label);
		
		panel_Contactos = new JPanel();
		panel_Contactos.setBorder(new TitledBorder(null, "Contactos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Contactos.setBounds(10, 22, 590, 62);
		panel2.add(panel_Contactos);
		panel_Contactos.setLayout(null);
		
		JLabel lblTelefono = new JLabel("T\u00E9lefono:");
		lblTelefono.setBounds(10, 24, 60, 14);
		panel_Contactos.add(lblTelefono);
		
		txtTelefono = new JFormattedTextField(telefono);
		txtTelefono.setBounds(94, 21, 131, 20);
		panel_Contactos.add(txtTelefono);
		
		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setBounds(266, 24, 46, 14);
		panel_Contactos.add(lblEmail);
		
		txtEmail = new JFormattedTextField();
		txtEmail.setBounds(336, 21, 226, 20);
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
		panel_InformacionG.setBorder(new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_InformacionG.setBounds(10, 95, 590, 104);
		panel2.add(panel_InformacionG);
		panel_InformacionG.setLayout(null);
		
		JLabel lblVehculoPropio = new JLabel("Veh\u00EDculo Propio:");
		lblVehculoPropio.setBounds(10, 26, 107, 14);
		panel_InformacionG.add(lblVehculoPropio);
		
		rdbSiVehiculo = new JRadioButton("S\u00ED");
		rdbSiVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			rdbSiVehiculo.setSelected(true);
			rdbNoVehiculo.setSelected(false);
			}
		});
		rdbSiVehiculo.setBounds(123, 22, 40, 23);
		panel_InformacionG.add(rdbSiVehiculo);
		
	    rdbNoVehiculo = new JRadioButton("No");
	    rdbNoVehiculo.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		
	    		rdbSiVehiculo.setSelected(false);
				rdbNoVehiculo.setSelected(true);
	    	}
	    });
		rdbNoVehiculo.setBounds(165, 22, 40, 23);
		panel_InformacionG.add(rdbNoVehiculo);
		
		JLabel lblDiponibilidad = new JLabel("Diponible Reelocalizaci\u00F3n:");
		lblDiponibilidad.setBounds(10, 67, 162, 14);
		panel_InformacionG.add(lblDiponibilidad);
		
		 rdbSiReelocalizacion = new JRadioButton("S\u00ED");
		 rdbSiReelocalizacion.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		rdbSiReelocalizacion.setSelected(true);
		 		rdbNoReelocalizacion.setSelected(false);
		 	}
		 });
		rdbSiReelocalizacion.setBounds(178, 63, 40, 23);
		panel_InformacionG.add(rdbSiReelocalizacion);
		
		rdbNoReelocalizacion = new JRadioButton("No");
		rdbNoReelocalizacion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbSiReelocalizacion.setSelected(false);
		 		rdbNoReelocalizacion.setSelected(true);
			}
		});
		rdbNoReelocalizacion.setBounds(220, 63, 40, 23);
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
		cbxIdiomas.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Ninguno", "Afrikaans", "Alban\u00E9s", "Alem\u00E1n", "Amharico", "Arabe", "Armenio", "Bengali", "Bieloruso", "Birman\u00E9s", "Bulgaro", "Catalan", "Checo", "Chino", "Coreano", "Croata", "Dan\u00E9s", "Dari", "Dzongkha", "Escoc\u00E9s", "Eslovaco", "Esloveniano", "Espa\u00F1ol", "Esperanto", "Estoniano", "Faroese", "Farsi", "Finland\u00E9s", "Franc\u00E9s", "Gaelico", "Galese", "Gallego", "Griego", "Hebreo", "Hindi", "Holand\u00E9s", "Hungaro", "Ingl\u00E9s", "Indonesio", "Inuktitut (Eskimo)", "Islandico", "Italiano", "Japon\u00E9s", "Khmer", "Kurdo", "Lao", "Laponico", "Latviano", "Lituano", "Macedonio", "Malay\u00E9s", "Malt\u00E9s", "Nepali", "Noruego", "Pashto", "Polaco", "Portugu\u00E9s", "Rumano", "Ruso", "Serbio", "Somali", "Suahili", "Sueco", "Tagalog-Filipino", "Tajik", "Tamil", "Tailand\u00E9s", "Tibetano", "Tigrinia", "Tongan\u00E9s", "Turco", "Turkmenistano", "Ucraniano", "Urdu", "Uzbekistano", "Vasco", "Vietnam\u00E9s"}));
		cbxIdiomas.setBounds(305, 45, 123, 20);
		panel_InformacionG.add(cbxIdiomas);
		
		JScrollPane scrollPaneIdiomas = new JScrollPane();
		scrollPaneIdiomas.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneIdiomas.setBounds(438, 26, 142, 67);
		panel_InformacionG.add(scrollPaneIdiomas);
		
		listIdiomas = new JList();
		scrollPaneIdiomas.setViewportView(listIdiomas);
		
		btnEliminarIdioma = new JButton("Remover");
		btnEliminarIdioma.setEnabled(false);
		btnEliminarIdioma.setBounds(322, 70, 89, 23);
		panel_InformacionG.add(btnEliminarIdioma);
		
		JLabel label_6 = new JLabel("*");
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setForeground(Color.RED);
		label_6.setBounds(395, 22, 38, 14);
		panel_InformacionG.add(label_6);
		
		JPanel panel_TipoSolicitante = new JPanel();
		panel_TipoSolicitante.setBorder(new TitledBorder(null, "Tipo de Solicitante", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TipoSolicitante.setBounds(10, 210, 590, 49);
		panel2.add(panel_TipoSolicitante);
		panel_TipoSolicitante.setLayout(null);
		
	    rdbObrero = new JRadioButton("Obrero");
		rdbObrero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		rdbTecnico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		rdbUniversitario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		
	    panel_Obreo = new JPanel();
		panel_Obreo.setBorder(new TitledBorder(null, "Obrero", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Obreo.setBounds(10, 270, 590, 104);
		panel2.add(panel_Obreo);
		panel_Obreo.setLayout(null);
		
		JLabel lblAnosExperienciaObrero = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExperienciaObrero.setBounds(10, 49, 140, 14);
		panel_Obreo.add(lblAnosExperienciaObrero);
		
		spnAnnosExpObrero = new JSpinner();
		spnAnnosExpObrero.setBounds(146, 46, 123, 20);
		panel_Obreo.add(spnAnnosExpObrero);
		
		JLabel label_5 = new JLabel("*");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setForeground(Color.RED);
		label_5.setBounds(133, 49, 38, 14);
		panel_Obreo.add(label_5);
		
		JLabel lblHabilidaes = new JLabel("Habilidades");
		lblHabilidaes.setBounds(440, 11, 70, 14);
		panel_Obreo.add(lblHabilidaes);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBackground(Color.BLUE);
		separator_1.setBounds(293, 11, 2, 82);
		panel_Obreo.add(separator_1);
		
	    cbxHabilidades = new JComboBox();
		cbxHabilidades.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Alba\u00F1il", "Anfitri\u00F3n de Fiesta", "Arsano", "Carpintero", "Chofer", "Chef", "Constructor", "Decorador", "Ebanista", "Electricista", "Mec\u00E1nico", "Pintor", "Plomero", "Salva Vidas", "Modista", "Seguridad", "Sirviente", "Jardinero"}));
		cbxHabilidades.setBounds(305, 28, 123, 20);
		panel_Obreo.add(cbxHabilidades);
		
		btnRemoverHabilidad = new JButton("Remover");
		btnRemoverHabilidad.setEnabled(false);
		btnRemoverHabilidad.setBounds(325, 58, 89, 23);
		panel_Obreo.add(btnRemoverHabilidad);
		
		JScrollPane scrollPaneHabilidades = new JScrollPane();
		scrollPaneHabilidades.setBounds(440, 28, 140, 65);
		panel_Obreo.add(scrollPaneHabilidades);
		
		listHabilidades = new JList();
		scrollPaneHabilidades.setViewportView(listHabilidades);
		
		JLabel label_7 = new JLabel("*");
		label_7.setHorizontalAlignment(SwingConstants.LEFT);
		label_7.setForeground(Color.RED);
		label_7.setBounds(511, 11, 38, 14);
		panel_Obreo.add(label_7);
		
		panel_Tecnico = new JPanel();
		panel_Tecnico.setBorder(new TitledBorder(null, "T\u00E9cnico", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Tecnico.setBounds(10, 270, 590, 104);
		panel2.add(panel_Tecnico);
		panel_Tecnico.setLayout(null);
		
		JLabel lblAnosExperienciaTec = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExperienciaTec.setBounds(10, 44, 132, 14);
		panel_Tecnico.add(lblAnosExperienciaTec);
		
		JLabel label_8 = new JLabel("*");
		label_8.setHorizontalAlignment(SwingConstants.LEFT);
		label_8.setForeground(Color.RED);
		label_8.setBounds(132, 44, 38, 14);
		panel_Tecnico.add(label_8);
		
		spnAnosExpTecnico = new JSpinner();
		spnAnosExpTecnico.setBounds(142, 41, 123, 20);
		panel_Tecnico.add(spnAnosExpTecnico);
		
		JLabel lblrea = new JLabel("\u00C1rea:");
		lblrea.setBounds(310, 44, 38, 14);
		panel_Tecnico.add(lblrea);
		
		JLabel label_9 = new JLabel("*");
		label_9.setHorizontalAlignment(SwingConstants.LEFT);
		label_9.setForeground(Color.RED);
		label_9.setBounds(348, 44, 38, 14);
		panel_Tecnico.add(label_9);
		
		cbxAreaTecnico = new JComboBox();
		cbxAreaTecnico.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Adm. de Empresas", "Adm. Hotelera", "Derecho", "Econom\u00EDa", "Contabilidad", "Mercadotecnia", "Arquitectura", "Comunicacion Social", "Dise\u00F1o e Interiorismo", "Ecologia", "Educaci\u00F3n", "Filosof\u00EDa", "Psicolog\u00EDa", "Ing. Civil", "Ing. Electr\u00F3nica", "Ing. Industrial", "Ing. Mecatr\u00F3nica", "Ing. Sistema", "Ing. Telem\u00E1tica", "Enfermeria", "Estomatolog\u00EDa", "Medicina", "Nutricion y Dietetica", "Terapia F\u00EDsica"}));
		cbxAreaTecnico.setBounds(396, 41, 123, 20);
		panel_Tecnico.add(cbxAreaTecnico);
		
		panel_Universitario = new JPanel();
		panel_Universitario.setBorder(new TitledBorder(null, "Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_Universitario.setBounds(10, 270, 590, 104);
		panel2.add(panel_Universitario);
		panel_Universitario.setLayout(null);
		
		JLabel lblAnosExpUniversitario = new JLabel("A\u00F1os de Experiencia:");
		lblAnosExpUniversitario.setBounds(10, 51, 132, 14);
		panel_Universitario.add(lblAnosExpUniversitario);
		
		spnAnosExpUniversitario = new JSpinner();
		spnAnosExpUniversitario.setBounds(139, 48, 123, 20);
		panel_Universitario.add(spnAnosExpUniversitario);
		
		JLabel label_10 = new JLabel("*");
		label_10.setHorizontalAlignment(SwingConstants.LEFT);
		label_10.setForeground(Color.RED);
		label_10.setBounds(130, 51, 38, 14);
		panel_Universitario.add(label_10);
		
		JLabel lblCarrera = new JLabel("Carrera:");
		lblCarrera.setBounds(306, 51, 65, 14);
		panel_Universitario.add(lblCarrera);
		
		JLabel label_11 = new JLabel("*");
		label_11.setHorizontalAlignment(SwingConstants.LEFT);
		label_11.setForeground(Color.RED);
		label_11.setBounds(359, 51, 38, 14);
		panel_Universitario.add(label_11);
		
		cbxCarrera = new JComboBox();
		cbxCarrera.setModel(new DefaultComboBoxModel(new String[] {"< Seleccione >", "Adm. de Empresas", "Adm. Hotelera", "Derecho", "Econom\u00EDa", "Contabilidad", "Mercadotecnia", "Arquitectura", "Comunicacion Social", "Dise\u00F1o e Interiorismo", "Ecologia", "Educaci\u00F3n", "Filosof\u00EDa", "Psicolog\u00EDa", "Ing. Civil", "Ing. Electr\u00F3nica", "Ing. Industrial", "Ing. Mecatr\u00F3nica", "Ing. Sistema", "Ing. Telem\u00E1tica", "Enfermeria", "Estomatolog\u00EDa", "Medicina", "Nutricion y Dietetica", "Terapia F\u00EDsica"}));
		cbxCarrera.setBounds(396, 48, 123, 20);
		panel_Universitario.add(cbxCarrera);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
			    btnRegistrar = new JButton("Registrar");
			    btnRegistrar.addActionListener(new ActionListener() {
			    	public void actionPerformed(ActionEvent e) {
			    		String cedula = textCedula.getText();
			    		String nombre = txtNombre.getText();
			    		String apellido = txtApellidos.getText();
			    		String estadoCivil = cbxEstadoCilvil.getSelectedItem().toString();
			    		String nacionalidad = cbxNacionalidad.getSelectedItem().toString();
			    		String sexo = "";
			    		String provincia =cbxProvincia.getSelectedItem().toString();
			    		String direccion = "Ciudad: "+txtCiudad.getText()+" Secotr: "+txtSector+" Calle: "+txtCalle+" N�mero: "+" "+spnNumeroCasa.getValue().toString()+" Referencia: "+txtReferencia;
			    		String email = txtEmail.getText();
			    		boolean vehiculoP =false;
			    		boolean mudarse = false;
			    		//j list idiomas
			    		if(rdbSiReelocalizacion.isSelected()){
			    			mudarse = true;
			    		}
			    		if(rdbSiVehiculo.isSelected()){
			    			vehiculoP = true;
			    		}
			    		int licencia = 0;
			    		if(cbxLicencia.getSelectedIndex()==1){
			    			licencia=0;
			    		}if(cbxLicencia.getSelectedIndex()==2){
			    			licencia=1;
			    		}if(cbxLicencia.getSelectedIndex()==3){
			    			licencia=2;
			    		}if(cbxLicencia.getSelectedIndex()==4){
			    			licencia=3;
			    		}if(cbxLicencia.getSelectedIndex()==5){
			    			licencia=4;
			    		}
			    		if(rdbFemenino.isSelected()){
			    			sexo = "Femenino";
			    		}if(rdbMasculino.isSelected()){
			    			sexo = "Masculino";
			    		}
			    	}
			    });
				buttonPane.add(btnRegistrar);
			}
			{
			    btnMover = new JButton("Continuar >>");
				btnMover.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						

						if(panel2.isVisible()){
							
							
							panel2.setVisible(false);
							panel1.setVisible(true);
							btnMover.setText("Continuar >>");
							
						}else{
							rdbObrero.setSelected(true);
							rdbTecnico.setSelected(false);
							rdbUniversitario.setSelected(false);
							panel_Obreo.setVisible(true);
							panel_Tecnico.setVisible(false);
							panel_Universitario.setVisible(false);
							
							panel1.setVisible(false);
							panel2.setVisible(true);
							btnMover.setText("<< Retroceder");
						}
					}
				});
				btnMover.setActionCommand("OK");
				buttonPane.add(btnMover);
				getRootPane().setDefaultButton(btnMover);
			}
			{
			    cancelButton = new JButton("Cancelar");
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
}