package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import jdk.nashorn.internal.scripts.JO;
import logica.BolsaLaboral;

public class ImportarEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField ftxtrnc;
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			ImportarEmpresa dialog = new ImportarEmpresa();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ImportarEmpresa() {
		setResizable(false);
		setModal(true);
		setTitle("Exportar Empresa");
		setBounds(100, 100, 444, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("RNC:");
			lblNewLabel.setBounds(10, 35, 46, 14);
			panel.add(lblNewLabel);
			{
				JButton okButton = new JButton("Exportar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(BolsaLaboral.getInstance().EmpresaExiste(ftxtrnc.getText())){
							try {
								BolsaLaboral.getInstance().writeEmpresaTXT(ftxtrnc.getText());
								JOptionPane.showMessageDialog(null, "La empresa fue Exportada");
								ftxtrnc.setText(null);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else{
							JOptionPane.showMessageDialog(null, "Empresa no encontrada, Intente de nuevo");
							ftxtrnc.setText(null);
						}
					}

				});
				okButton.setBounds(307, 31, 89, 23);
				panel.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				ftxtrnc = new JFormattedTextField();
				ftxtrnc.setBounds(77, 32, 197, 20);
				panel.add(ftxtrnc);
			}
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
	private static class __Tmp {
		private static void __tmp() {
			  javax.swing.JPanel __wbp_panel = new javax.swing.JPanel();
		}
	}
}
