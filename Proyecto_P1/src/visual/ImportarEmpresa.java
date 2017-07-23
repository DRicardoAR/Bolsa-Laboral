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
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

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
		getContentPane().setBackground(new Color(248, 248, 255));
		setBackground(new Color(248, 248, 255));
		setResizable(false);
		setModal(true);
		setTitle("Exportar Empresa");
		setBounds(100, 100, 404, 167);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			panel.setBackground(new Color(248, 248, 255));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JLabel lblNewLabel = new JLabel("RNC:");
			lblNewLabel.setBounds(10, 35, 46, 14);
			panel.add(lblNewLabel);
			{
				JButton okButton = new JButton("Exportar");
				okButton.setIcon(new ImageIcon(ImportarEmpresa.class.getResource("/img/exportar.png")));
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(BolsaLaboral.getInstance().EmpresaExiste(ftxtrnc.getText())){
							try {
								BolsaLaboral.getInstance().writeEmpresaTXT(ftxtrnc.getText());
								ProgressBar pr = new ProgressBar(2);
								pr.setLocationRelativeTo( null);
								pr.setVisible(true);			
								JOptionPane.showMessageDialog(null, "Empresa Exportadas");
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
				okButton.setBounds(259, 31, 111, 23);
				panel.add(okButton);
				okButton.setActionCommand("OK");
				getRootPane().setDefaultButton(okButton);
			}
			{
				ftxtrnc = new JFormattedTextField();
				ftxtrnc.setBounds(52, 32, 197, 20);
				panel.add(ftxtrnc);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(ImportarEmpresa.class.getResource("/img/cancelar.png")));
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
