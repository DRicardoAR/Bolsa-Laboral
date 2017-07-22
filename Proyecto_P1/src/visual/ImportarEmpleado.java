package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import logica.BolsaLaboral;

public class ImportarEmpleado extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCedula;

	/**
	 * Launch the application.
	 */
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
	 */
	public ImportarEmpleado() {
		setTitle("Exportar Empleado");
		setBounds(100, 100, 445, 168);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblCedula = new JLabel("Cedula:");
			lblCedula.setBounds(10, 35, 46, 14);
			panel.add(lblCedula);
			
			txtCedula = new JTextField();
			txtCedula.setBounds(77, 32, 197, 20);
			panel.add(txtCedula);
			txtCedula.setColumns(10);
			
			JButton btnNewButton = new JButton("Exportar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						BolsaLaboral.getInstance().writeSolicitanteTXT(txtCedula.getText());
						JOptionPane.showMessageDialog(null, "El empleado ha sido Importado");
						txtCedula.setText(null);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(307, 31, 89, 23);
			panel.add(btnNewButton);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
