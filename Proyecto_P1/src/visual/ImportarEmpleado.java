package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.text.ParseException;

import logica.BolsaLaboral;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Color;
import javax.swing.ImageIcon;

public class ImportarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField txtCedula;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			ImportarEmpleado dialog = new ImportarEmpleado();
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
	public ImportarEmpleado() throws ParseException {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(248, 248, 255));
		setBackground(new Color(248, 248, 255));
		setResizable(false);
		setTitle("Exportar Empleado");
		setBounds(100, 100, 371, 168);
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
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(10, 35, 46, 14);
			panel.add(lblCedula);
			
			
			MaskFormatter mascara = new MaskFormatter("###-#######-#");
			txtCedula = new JFormattedTextField(mascara);
			txtCedula.setBounds(77, 31, 130, 23);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JButton btnNewButton = new JButton("Exportar");
			btnNewButton.setIcon(new ImageIcon(ImportarEmpleado.class.getResource("/img/exportar.png")));
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					if(BolsaLaboral.getInstance().SolicitanteExiste(txtCedula.getText())){
									try {
										BolsaLaboral.getInstance().writeSolicitanteTXT(txtCedula.getText());
										ProgressBar pr = new ProgressBar(3);
										pr.setLocationRelativeTo( null);
										pr.setVisible(true);			
										JOptionPane.showMessageDialog(null, "Empleado Exportado");
										txtCedula.setText(null);
									} catch (IOException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
								}else{

									JOptionPane.showMessageDialog(null, "Solicitante no encontrado");
									txtCedula.setText(null);
								}
								

				}
			});
			btnNewButton.setBounds(217, 31, 116, 23);
			panel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.setIcon(new ImageIcon(ImportarEmpleado.class.getResource("/img/aceptar.png")));
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
