package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;

import logica.Solicitud;
import logica.SolicitudObrero;
import logica.SolicitudTecnico;
import logica.SolicitudUniversitario;
import sun.misc.PerformanceLogger;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JRadioButton;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DetalleSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtRNC;
	private JTextField txtNombre;
	private JTextField txtContrato;
	private JTextField txtVancante;
	private JTextField txtLocalidad;
	private JTextField txtLicencia;
	private JTextField txtTipo;
	private JTextField txtEdad;
	private JTextField txtExperienciaUniversitario;
	private JTextField txtCarrera;
	private JTextField txtExperienciaTecnico;
	private JTextField txtArea;
	private JTextField txtExperienciaObrero;
	private Solicitud miSolicitud;
	private  JLabel lblReubicacion;
	private JLabel lblVehiculo;
	private JLabel lblPostGrado;
	private JPanel panelObrero;
	private JPanel panelUniversitario;
	private JPanel panelTecnico;
	private JList ListaIdioma;
	private JList listasHabilidades ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DetalleSolicitud dialog = new DetalleSolicitud(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DetalleSolicitud(Solicitud solicitud) {
		miSolicitud = solicitud;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cargarDatos();
			}

			
		});
				
		setTitle("Solicitud");
		setBounds(100, 100, 510, 506);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Detalles Solicitud", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(10, 21, 463, 64);
			panel.add(panel_1);
			panel_1.setLayout(null);
			
			JLabel lblRnc = new JLabel("RNC:");
			lblRnc.setBounds(10, 27, 46, 14);
			panel_1.add(lblRnc);
			
			txtRNC = new JTextField();
			txtRNC.setEnabled(false);
			txtRNC.setBounds(88, 24, 121, 21);
			panel_1.add(txtRNC);
			txtRNC.setColumns(10);
			
			JLabel lblNombre = new JLabel("Nombre:");
			lblNombre.setBounds(230, 27, 57, 14);
			panel_1.add(lblNombre);
			
			txtNombre = new JTextField();
			txtNombre.setEnabled(false);
			txtNombre.setColumns(10);
			txtNombre.setBounds(297, 24, 142, 21);
			panel_1.add(txtNombre);
			
			JPanel panel_2 = new JPanel();
			panel_2.setLayout(null);
			panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_2.setBounds(10, 90, 463, 185);
			panel.add(panel_2);
			
			JLabel lblContrato = new JLabel("Contrato:");
			lblContrato.setBounds(10, 27, 68, 14);
			panel_2.add(lblContrato);
			
			txtContrato = new JTextField();
			txtContrato.setEnabled(false);
			txtContrato.setColumns(10);
			txtContrato.setBounds(88, 24, 121, 21);
			panel_2.add(txtContrato);
			
			JLabel lblVacantes = new JLabel("Vacantes:");
			lblVacantes.setBounds(230, 27, 57, 14);
			panel_2.add(lblVacantes);
			
			txtVancante = new JTextField();
			txtVancante.setEnabled(false);
			txtVancante.setColumns(10);
			txtVancante.setBounds(297, 24, 142, 21);
			panel_2.add(txtVancante);
			
			JLabel lblReubicacin = new JLabel("Reubicaci\u00F3n:");
			lblReubicacin.setBounds(10, 69, 82, 14);
			panel_2.add(lblReubicacin);
			
			JLabel lblRequiereVehiculo = new JLabel("Requiere Veh\u00EDculo:");
			lblRequiereVehiculo.setBounds(10, 108, 107, 14);
			panel_2.add(lblRequiereVehiculo);
			
			JLabel lblLocalidad = new JLabel("Localidad:");
			lblLocalidad.setBounds(230, 69, 68, 14);
			panel_2.add(lblLocalidad);
			
			JLabel lblLicencia = new JLabel("Licencia:");
			lblLicencia.setBounds(230, 108, 57, 14);
			panel_2.add(lblLicencia);
			
			lblReubicacion = new JLabel("New label");
			lblReubicacion.setBounds(126, 69, 46, 14);
			panel_2.add(lblReubicacion);
			
			 lblVehiculo = new JLabel("New label");
			lblVehiculo.setBounds(126, 108, 46, 14);
			panel_2.add(lblVehiculo);
			
			txtLocalidad = new JTextField();
			txtLocalidad.setEnabled(false);
			txtLocalidad.setColumns(10);
			txtLocalidad.setBounds(297, 66, 142, 21);
			panel_2.add(txtLocalidad);
			
			txtLicencia = new JTextField();
			txtLicencia.setEnabled(false);
			txtLicencia.setColumns(10);
			txtLicencia.setBounds(297, 105, 142, 21);
			panel_2.add(txtLicencia);
			
			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 146, 107, 14);
			panel_2.add(lblTipo);
			
			txtTipo = new JTextField();
			txtTipo.setEnabled(false);
			txtTipo.setColumns(10);
			txtTipo.setBounds(88, 143, 121, 21);
			panel_2.add(txtTipo);
			
			JLabel lblEdad = new JLabel("Edad:");
			lblEdad.setBounds(230, 146, 57, 14);
			panel_2.add(lblEdad);
			
			txtEdad = new JTextField();
			txtEdad.setEnabled(false);
			txtEdad.setColumns(10);
			txtEdad.setBounds(297, 143, 142, 21);
			panel_2.add(txtEdad);
			
			JPanel panel_4 = new JPanel();
			panel_4.setLayout(null);
			panel_4.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Idiomas",
								TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panel_4.setBounds(253, 275, 220, 132);
			panel.add(panel_4);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 22, 200, 99);
			panel_4.add(scrollPane);
			
			ListaIdioma = new JList();
			ListaIdioma.setEnabled(false);
			scrollPane.setViewportView(ListaIdioma);
			
			panelUniversitario = new JPanel();
			panelUniversitario.setLayout(null);
			panelUniversitario.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
								"Universitario", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelUniversitario.setBounds(10, 275, 241, 132);
			panel.add(panelUniversitario);
			
			JLabel label_1 = new JLabel("Experiencia:");
			label_1.setBounds(10, 29, 72, 14);
			panelUniversitario.add(label_1);
			
			JLabel label_2 = new JLabel("PostGrado:");
			label_2.setBounds(10, 100, 72, 14);
			panelUniversitario.add(label_2);
			
			JLabel label_3 = new JLabel("Carrera:");
			label_3.setBounds(10, 65, 72, 14);
			panelUniversitario.add(label_3);
			
			txtExperienciaUniversitario = new JTextField();
			txtExperienciaUniversitario.setEnabled(false);
			txtExperienciaUniversitario.setColumns(10);
			txtExperienciaUniversitario.setBounds(88, 26, 121, 21);
			panelUniversitario.add(txtExperienciaUniversitario);
			
			txtCarrera = new JTextField();
			txtCarrera.setEnabled(false);
			txtCarrera.setColumns(10);
			txtCarrera.setBounds(88, 62, 121, 21);
			panelUniversitario.add(txtCarrera);
			
			lblPostGrado = new JLabel("New label");
			lblPostGrado.setBounds(92, 100, 72, 14);
			panelUniversitario.add(lblPostGrado);
			
			panelTecnico = new JPanel();
			panelTecnico.setLayout(null);
			panelTecnico.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "T\u00E9cnico", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelTecnico.setBounds(10, 275, 241, 132);
			panel.add(panelTecnico);
			
			JLabel label_5 = new JLabel("Experiencia:");
			label_5.setBounds(10, 29, 72, 14);
			panelTecnico.add(label_5);
			
			JLabel lblArea = new JLabel("Area:");
			lblArea.setBounds(10, 65, 72, 14);
			panelTecnico.add(lblArea);
			
			txtExperienciaTecnico = new JTextField();
			txtExperienciaTecnico.setEnabled(false);
			txtExperienciaTecnico.setColumns(10);
			txtExperienciaTecnico.setBounds(88, 26, 121, 21);
			panelTecnico.add(txtExperienciaTecnico);
			
			txtArea = new JTextField();
			txtArea.setEnabled(false);
			txtArea.setColumns(10);
			txtArea.setBounds(88, 62, 121, 21);
			panelTecnico.add(txtArea);
			
			panelObrero = new JPanel();
			panelObrero.setLayout(null);
			panelObrero.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Obrero", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			panelObrero.setBounds(10, 275, 241, 132);
			panel.add(panelObrero);
			
			JLabel label_6 = new JLabel("Experiencia:");
			label_6.setBounds(10, 29, 72, 14);
			panelObrero.add(label_6);
			
			JLabel lblHabilidades = new JLabel("Habilidades:");
			lblHabilidades.setBounds(10, 54, 72, 14);
			panelObrero.add(lblHabilidades);
			
			txtExperienciaObrero = new JTextField();
			txtExperienciaObrero.setEnabled(false);
			txtExperienciaObrero.setColumns(10);
			txtExperienciaObrero.setBounds(88, 26, 121, 21);
			panelObrero.add(txtExperienciaObrero);
			
			JScrollPane scrollPane_1 = new JScrollPane();
			scrollPane_1.setBounds(10, 72, 221, 49);
			panelObrero.add(scrollPane_1);
			
			listasHabilidades = new JList();
			listasHabilidades.setEnabled(false);
			scrollPane_1.setViewportView(listasHabilidades);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Salir");
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
	private void cargarDatos() {
		txtRNC.setText(miSolicitud.getEmpresa().getRNC());
		txtNombre.setText(miSolicitud.getEmpresa().getNombre());
		
		txtContrato.setText(miSolicitud.getTipoContrato());
		txtVancante.setText(Integer.toString(miSolicitud.getCantVacantes()));
		txtLocalidad.setText(miSolicitud.getLocalidad());
		txtLicencia.setText("Categoria "+Integer.toString(miSolicitud.getCategoriaLicencia()));
		String edadMin = Integer.toString(miSolicitud.getEdadMin());
		String edadMax = Integer.toString(miSolicitud.getEdadMax());
		txtEdad.setText(edadMin+" a "+edadMax+" años");
		if(miSolicitud.isMudarse()){
			lblReubicacion.setText("Si");
		}else{
			lblReubicacion.setText("No");
		}
		if(miSolicitud.isVehiculoPropio()){
			lblVehiculo.setText("Si");
		}else{
			lblVehiculo.setText("No");
		}
		cargarIdiomas();
		if(miSolicitud instanceof SolicitudUniversitario){
			
			panelUniversitario.setVisible(true);
			panelTecnico.setVisible(false);
			panelObrero.setVisible(false);
			
			txtExperienciaUniversitario.setText(Integer.toString(((SolicitudUniversitario)miSolicitud).getAnnosExperiencia()));
			txtCarrera.setText(((SolicitudUniversitario)miSolicitud).getCarrera());
			if(((SolicitudUniversitario)miSolicitud).isPostGrado()){
				lblPostGrado.setText("Si");				
			}else{
				lblPostGrado.setText("No");
			}
		}
		if(miSolicitud instanceof SolicitudTecnico){
			txtTipo.setText("Técnico");
			panelUniversitario.setVisible(false);
			panelTecnico.setVisible(true);
			panelObrero.setVisible(false);
			txtExperienciaTecnico.setText(Integer.toString(((SolicitudTecnico)miSolicitud).getAnnosExperiencia()));
			txtArea.setText(((SolicitudTecnico)miSolicitud).getArea());
						
		}
		if(miSolicitud instanceof SolicitudObrero){
			txtTipo.setText("Obrero");
			panelUniversitario.setVisible(false);
			panelTecnico.setVisible(false);
			panelObrero.setVisible(true);
			txtExperienciaObrero.setText(Integer.toString(((SolicitudObrero)miSolicitud).getAnnosExperiencia()));
			CargarHabilidades();
		}
		
		
		
	}

	private void cargarIdiomas() {
		DefaultListModel idioma = new DefaultListModel();
		for (String idio : miSolicitud.getIdiomas()) {
			idioma.addElement(idio);			
		}
		ListaIdioma.setModel(idioma);
		
	}

	private void CargarHabilidades() {
		DefaultListModel habilidades = new DefaultListModel();
		for (String habili : ((SolicitudObrero)miSolicitud).getHabilidades()) {
			habilidades.addElement(habili);
		}
		listasHabilidades.setModel(habilidades);
		
		
	}
	
}
