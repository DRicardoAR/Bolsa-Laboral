package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JFormattedTextField;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import logica.BolsaLaboral;
import logica.Empresa;
import logica.Solicitud;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.EtchedBorder;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

public class InsertarEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTextField txtEmail;
	private JTextField txtSector;
	private JTextField txtLocalidad;
	private JTextField txtCuidad;
	private JTextField txtCalle;
	private JTextField txtReferencia;
	private JFormattedTextField txtTel;
	private JFormattedTextField ftxtRnc;
	private JComboBox cbxProvincia;
	private JButton btnregistrar;
	private Empresa modificarEmpre = null;

	/*
	 * public static void main(String[] args) { try { InsertarEmpresa dialog =
	 * new InsertarEmpresa(null);
	 * dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	 * dialog.setVisible(true); } catch (Exception e) { e.printStackTrace(); } }
	 * 
	 * /** Create the dialog.
	 * 
	 * @throws ParseException
	 */
	public InsertarEmpresa(String title, boolean modi, Empresa empresa,
			String RNCsoli) throws ParseException {
		setBackground(SystemColor.inactiveCaptionBorder);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				if (RNCsoli != null) {
					ftxtRnc.setText(RNCsoli);
					ftxtRnc.setEnabled(false);
				}
			}
		});
		modificarEmpre = empresa;

		setBounds(100, 100, 614, 413);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setTitle(title);
		JPanel panel = new JPanel();
		panel.setBackground(new Color(248, 248, 255));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(5, 26, 605, 118);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setBounds(10, 35, 46, 14);
		panel.add(lblRnc);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(10, 84, 62, 14);
		panel.add(lblTelefono);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(312, 32, 62, 14);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setBounds(405, 32, 174, 23);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(312, 81, 46, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBackground(Color.WHITE);
		txtEmail.setBounds(405, 78, 174, 23);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		MaskFormatter mascara = new MaskFormatter("##########");
		ftxtRnc = new JFormattedTextField(mascara);
		ftxtRnc.setBackground(Color.WHITE);
		ftxtRnc.setBounds(93, 32, 174, 23);
		panel.add(ftxtRnc);

		MaskFormatter mascara1 = new MaskFormatter("###-###-####");
		txtTel = new JFormattedTextField(mascara1);
		txtTel.setBackground(Color.WHITE);
		txtTel.setBounds(93, 81, 174, 23);
		panel.add(txtTel);
		txtTel.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(248, 248, 255));
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Ubicaci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(5, 155, 605, 166);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblProvincia = new JLabel("Provincia:   ");
		lblProvincia.setBounds(10, 35, 73, 14);
		panel_1.add(lblProvincia);

		cbxProvincia = new JComboBox();
		cbxProvincia.setBackground(Color.WHITE);
		cbxProvincia.setModel(new DefaultComboBoxModel(new String[] {
				"<Seleccione>", "Azua ", "Bahoruco ", "Barahona ",
				"Dajab\u00F3n ", "Distrito Nacional ", "Duarte ",
				"El\u00EDas Pi\u00F1a ", "El Seibo ", "Espaillat ",
				"Hato Mayor ", "Independencia ", "La Altagracia ",
				"La Romana ", "La Vega ", "Mar\u00EDa Trinidad S\u00E1nchez ",
				"Monse\u00F1or Nouel ", "Montecristi ", "Monte Plata ",
				"Pedernales ", "Peravia ", "Puerto Plata ",
				"Hermanas Mirabal ", "Saman\u00E1 ",
				"S\u00E1nchez Ram\u00EDrez ", "San Crist\u00F3bal ",
				"San Jos\u00E9 de Ocoa ", "San Juan ",
				"San Pedro de Macor\u00EDs ", "Santiago ",
				"Santiago Rodr\u00EDguez ", "Santo Domingo ", "Valverde " }));
		cbxProvincia.setBounds(93, 35, 174, 23);
		panel_1.add(cbxProvincia);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 84, 54, 14);
		panel_1.add(lblSector);

		txtSector = new JTextField();
		txtSector.setBackground(Color.WHITE);
		txtSector.setBounds(93, 81, 174, 23);
		panel_1.add(txtSector);
		txtSector.setColumns(10);

		JLabel lblNewLabel = new JLabel("No. Localidad");
		lblNewLabel.setBounds(10, 134, 88, 14);
		panel_1.add(lblNewLabel);

		txtLocalidad = new JTextField();
		txtLocalidad.setBackground(Color.WHITE);
		txtLocalidad.setBounds(93, 131, 174, 23);
		panel_1.add(txtLocalidad);
		txtLocalidad.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cuidad:");
		lblNewLabel_1.setBounds(312, 32, 62, 14);
		panel_1.add(lblNewLabel_1);

		txtCuidad = new JTextField();
		txtCuidad.setBackground(Color.WHITE);
		txtCuidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (Character.isDigit(c)) {
					getToolkit().beep();

					e.consume();
					JOptionPane.showMessageDialog(null,"Este campo no adminte número");

				}

			}
		});
		txtCuidad.setBounds(405, 32, 174, 23);
		panel_1.add(txtCuidad);
		txtCuidad.setColumns(10);

		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(312, 81, 46, 14);
		panel_1.add(lblCalle);

		txtCalle = new JTextField();
		txtCalle.setBackground(Color.WHITE);
		txtCalle.setBounds(405, 78, 174, 23);
		panel_1.add(txtCalle);
		txtCalle.setColumns(10);

		JLabel lblReferencia = new JLabel("Referencia:");
		lblReferencia.setBounds(312, 131, 73, 14);
		panel_1.add(lblReferencia);

		txtReferencia = new JTextField();
		txtReferencia.setBackground(Color.WHITE);
		txtReferencia.setBounds(405, 128, 174, 23);
		panel_1.add(txtReferencia);
		txtReferencia.setColumns(10);

		JLabel lblTodosLosCampos = new JLabel(
				"Todos los campos son obligatorios");
		lblTodosLosCampos.setForeground(Color.RED);
		lblTodosLosCampos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblTodosLosCampos.setBounds(454, 11, 220, 13);
		contentPanel.add(lblTodosLosCampos);

		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnregistrar = new JButton("Registrar");
				btnregistrar.setIcon(new ImageIcon(InsertarEmpresa.class.getResource("/img/add.png")));
				btnregistrar.setBackground(UIManager.getColor("Button.darkShadow"));
				if(modi){
					btnregistrar.setText("Salvas Modificaciones");
				}
				btnregistrar.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						if (!(modi)) {
							String rnc = ftxtRnc.getText();
							String nombre = txtNombre.getText();
							String tele = txtTel.getText();
							String email = txtEmail.getText();
							String provincia = cbxProvincia.getSelectedItem()
									.toString();

							String direcion = txtSector.getText() + " "
									+ txtLocalidad.getText() + " "
									+ txtCuidad.getText() + " "
									+ txtReferencia.getText() + " "
									+ txtCalle.getText();
							Empresa miEmpresa = new Empresa(rnc, nombre, tele,
									email, provincia, direcion);

							if (txtNombre.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar  el nombre de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (ftxtRnc.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar el RNC de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtTel.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar el telefono la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtEmail.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar el email la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (cbxProvincia.getSelectedIndex() == 0) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe seleccionar la Provincia de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtCalle.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar la calle  de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtCuidad.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe la cuidad de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtSector.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar el sector de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtReferencia.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar una referencia  de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (txtLocalidad.getText().isEmpty()) {
								JOptionPane
										.showMessageDialog(
												null,
												"Se debe ingresar el No. de localidad de la empresa a registrar",
												"ATENCIÓN",
												JOptionPane.WARNING_MESSAGE,
												null);
							} else if (empresaRep(rnc)) {
								JOptionPane.showMessageDialog(null,
										"Empresa existente", "ATENCIÓN",
										JOptionPane.WARNING_MESSAGE, null);
							} else {
								if ((BolsaLaboral.getInstance()
										.validarEmail(email))) {
									JOptionPane.showMessageDialog(null,
											"Favor registar un E-mail valido",
											"ATENCIÓN",
											JOptionPane.WARNING_MESSAGE, null);
									txtEmail.setText(null);

								} else {
									BolsaLaboral.getInstance().insertEmpresa(
											miEmpresa);
									if (RNCsoli == null) {
										ftxtRnc.setText(null);
									}

									txtNombre.setText(null);
									txtTel.setText(null);
									txtEmail.setText(null);
									cbxProvincia.setSelectedIndex(0);
									txtSector.setText(null);
									txtLocalidad.setText(null);
									txtCuidad.setText(null);
									txtReferencia.setText(null);
									txtCalle.setText(null);
									JOptionPane
											.showMessageDialog(null,
													"Empresa Agregada Satisfactoriamente");
									if (RNCsoli != null) {
										dispose();
									}

								}

							}

						} else {String rnc = ftxtRnc.getText();
						String nombre = txtNombre.getText();
						String tele = txtTel.getText();
						String email = txtEmail.getText();
						String provincia = cbxProvincia.getSelectedItem()
								.toString();

						String direcion = txtSector.getText() + " "
								+ txtLocalidad.getText() + " "
								+ txtCuidad.getText() + " "
								+ txtReferencia.getText() + " "
								+ txtCalle.getText();
						Empresa modiEmpre = new Empresa(rnc, nombre, tele,
								email, provincia, direcion);
							BolsaLaboral.getInstance().modificaEmpresa(modiEmpre);
							JOptionPane.showMessageDialog(null, "Empresa Modificada");
							
						}
					}

				});
				btnregistrar.setActionCommand("OK");
				buttonPane.add(btnregistrar);
				getRootPane().setDefaultButton(btnregistrar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(InsertarEmpresa.class.getResource("/img/cancelar.png")));
				cancelButton.setBackground(UIManager.getColor("Button.darkShadow"));
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dispose();

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
if(modi){
	loadEmpresaModi();
}
	}

	private void loadEmpresaModi() {
		if (modificarEmpre != null) {
			ftxtRnc.setEnabled(false);
			ftxtRnc.setText(modificarEmpre.getRNC());
			txtEmail.setText(modificarEmpre.getEmail());
			txtNombre.setText(modificarEmpre.getNombre());
			txtTel.setText(modificarEmpre.getTelefono());
			String[] parts = modificarEmpre.getDireccion().split(" ");
			String sector = parts[0];
			String localidad = parts[1];
			String cuidad = parts[2];
			String referencia = parts[3];
			String calle = parts[4];
			txtSector.setText(sector);
			txtLocalidad.setText(localidad);
			txtCuidad.setText(cuidad);
			txtReferencia.setText(referencia);
			txtCalle.setText(calle);
			cbxProvincia.setSelectedItem(modificarEmpre.getArea());
		}

	}

	public boolean empresaRep(String rnc) {
		boolean aux = false;
		for (Empresa empresa : BolsaLaboral.getInstance().getMisEmpresas()) {
			if (empresa.getRNC().equalsIgnoreCase(rnc)) {
				aux = true;
			}

		}
		return aux;
	}
}
