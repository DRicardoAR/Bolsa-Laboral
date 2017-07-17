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

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import logica.BolsaLaboral;
import logica.Empresa;

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
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			InsertarEmpresa dialog = new InsertarEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 * @throws ParseException 
	 */
	public InsertarEmpresa() throws ParseException {
		setTitle("Insertar Empresa\r\n");
		setBounds(100, 100, 529, 404);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(
				new TitledBorder(null, "Informaci\u00F3n General", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 26, 493, 118);
		contentPanel.add(panel);
		panel.setLayout(null);

		JLabel lblRnc = new JLabel("RNC:");
		lblRnc.setBounds(10, 35, 46, 14);
		panel.add(lblRnc);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono:");
		lblTelefono.setBounds(10, 84, 62, 14);
		panel.add(lblTelefono);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(252, 35, 62, 14);
		panel.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.setBounds(345, 35, 134, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(252, 84, 46, 14);
		panel.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(345, 81, 134, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		MaskFormatter mascara = new MaskFormatter("##########");
		ftxtRnc = new JFormattedTextField(mascara);
		ftxtRnc.setBounds(93, 32, 134, 20);
		panel.add(ftxtRnc);
		
		MaskFormatter mascara1 = new MaskFormatter("##########");
		txtTel = new JFormattedTextField(mascara1);
		txtTel.setBounds(93, 81, 134, 20);
		panel.add(txtTel);
		txtTel.setColumns(10);

		JLabel label_1 = new JLabel("*");
		label_1.setForeground(Color.RED);
		label_1.setBounds(70, 35, 11, 14);
		panel.add(label_1);

		JLabel label_2 = new JLabel("*");
		label_2.setForeground(Color.RED);
		label_2.setBounds(70, 84, 11, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("*");
		label_3.setForeground(Color.RED);
		label_3.setBounds(313, 35, 11, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("*");
		label_4.setForeground(Color.RED);
		label_4.setBounds(313, 84, 11, 14);
		panel.add(label_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Ubicaci\u00F3n",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 155, 493, 166);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblProvincia = new JLabel("Provincia:   ");
		lblProvincia.setBounds(10, 35, 73, 14);
		panel_1.add(lblProvincia);

		cbxProvincia = new JComboBox();
		cbxProvincia.setModel(new DefaultComboBoxModel(
				new String[] { "<Seleccione>", "Azua ", "Bahoruco ", "Barahona ", "Dajab\u00F3n ", "Distrito Nacional ",
						"Duarte ", "El\u00EDas Pi\u00F1a ", "El Seibo ", "Espaillat ", "Hato Mayor ", "Independencia ",
						"La Altagracia ", "La Romana ", "La Vega ", "Mar\u00EDa Trinidad S\u00E1nchez ",
						"Monse\u00F1or Nouel ", "Montecristi ", "Monte Plata ", "Pedernales ", "Peravia ",
						"Puerto Plata ", "Hermanas Mirabal ", "Saman\u00E1 ", "S\u00E1nchez Ram\u00EDrez ",
						"San Crist\u00F3bal ", "San Jos\u00E9 de Ocoa ", "San Juan ", "San Pedro de Macor\u00EDs ",
						"Santiago ", "Santiago Rodr\u00EDguez ", "Santo Domingo ", "Valverde " }));
		cbxProvincia.setBounds(93, 35, 134, 20);
		panel_1.add(cbxProvincia);

		JLabel lblSector = new JLabel("Sector:");
		lblSector.setBounds(10, 84, 54, 14);
		panel_1.add(lblSector);

		txtSector = new JTextField();
		txtSector.setBounds(93, 81, 134, 20);
		panel_1.add(txtSector);
		txtSector.setColumns(10);

		JLabel lblNewLabel = new JLabel("No. Localidad");
		lblNewLabel.setBounds(10, 134, 88, 14);
		panel_1.add(lblNewLabel);

		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(93, 131, 134, 20);
		panel_1.add(txtLocalidad);
		txtLocalidad.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Cuidad:");
		lblNewLabel_1.setBounds(252, 35, 46, 14);
		panel_1.add(lblNewLabel_1);

		txtCuidad = new JTextField();
		txtCuidad.setBounds(345, 35, 134, 20);
		panel_1.add(txtCuidad);
		txtCuidad.setColumns(10);

		JLabel lblCalle = new JLabel("Calle:");
		lblCalle.setBounds(252, 84, 46, 14);
		panel_1.add(lblCalle);

		txtCalle = new JTextField();
		txtCalle.setBounds(345, 81, 134, 20);
		panel_1.add(txtCalle);
		txtCalle.setColumns(10);

		JLabel lblReferencia = new JLabel("Referencia:");
		lblReferencia.setBounds(252, 134, 73, 14);
		panel_1.add(lblReferencia);

		txtReferencia = new JTextField();
		txtReferencia.setBounds(345, 131, 134, 20);
		panel_1.add(txtReferencia);
		txtReferencia.setColumns(10);

		JLabel label_5 = new JLabel("*");
		label_5.setForeground(Color.RED);
		label_5.setBounds(70, 35, 11, 14);
		panel_1.add(label_5);

		JLabel label = new JLabel("Campos con * son obligatorios.");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label.setBounds(361, 11, 142, 13);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Registrar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String rnc = ftxtRnc.getText();
						String nombre = txtNombre.getText();
						String tele = txtTel.getText();
						String email = txtEmail.getText();
						String provincia = cbxProvincia.getSelectedItem().toString();
						
						String direcion = txtSector.getText() + " " + txtLocalidad.getText() + " " + txtCuidad.getText()
								+ " " + txtReferencia.getText() + " " + txtCalle.getText();
						Empresa miEmpresa = new Empresa(rnc, nombre, tele, email, provincia, direcion);
						

						    if (txtNombre.getText().isEmpty()){
						    	JOptionPane.showMessageDialog(null, "Se debe ingresar  el nombre de la empresa a registrar", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						    }else if(ftxtRnc.getText().isEmpty()){
						    	JOptionPane.showMessageDialog(null, "Se debe ingresar el RNC de la empresa a registrar", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						    }else if (txtTel.getText().isEmpty()){
						    	JOptionPane.showMessageDialog(null, "Se debe ingresar el telefono la empresa a registrar", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						    }else if(txtEmail.getText().isEmpty()){
						    	JOptionPane.showMessageDialog(null, "Se debe ingresar el email la empresa a registrar", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						    }else if(cbxProvincia.getSelectedIndex()==0){
						    	JOptionPane.showMessageDialog(null, "Se debe seleccionar la Provincia de la empresa a registrar", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						   /* }/*else if ((BolsaLaboral.getInstance().validarEmail(email))){
						    	JOptionPane.showMessageDialog(null, "Favor registar un E-mail valido", "ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
						    	txtEmail.setText(null);*/
						    }else{
							BolsaLaboral.getInstance().insertEmpresa(miEmpresa);
							ftxtRnc.setText(null);
							txtNombre.setText(null);
							txtTel.setText(null);
							txtEmail.setText(null);
							cbxProvincia.setSelectedIndex(0);
							txtSector.setText(null);
							txtLocalidad.setText(null);
							txtCuidad.setText(null);
							txtReferencia.setText(null);
							txtCalle.setText(null);
							JOptionPane.showMessageDialog(null, "Empresa Agregada Satisfactoriamente");
						
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
					public void actionPerformed(ActionEvent arg0) {
						dispose();

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}

