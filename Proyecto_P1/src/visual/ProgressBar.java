package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import visual.ProgressBar.progreso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import java.awt.Color;

public class ProgressBar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private int option = 0;
	private Timer timer;
	private JProgressBar progressBar;
	private JLabel lblTitulo ;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ProgressBar dialog = new ProgressBar(1);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ProgressBar(int i) {
		getContentPane().setBackground(new Color(248, 248, 255));
		setBackground(new Color(248, 248, 255));
		option = 1;
		
		this.setUndecorated(true);
		this.setLocationRelativeTo(null);
		addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				if(option == 1 ){
					timer = new Timer(25, new progreso());
				}
				
				timer.start();
			}

		});		
		
		setBounds(100, 100, 450, 150);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblTitulo = new JLabel("New label");
		if(option == 1 || option ==6){
			lblTitulo.setText("Guardando Cambios...");
		}
		lblTitulo.setBounds(30, 22, 321, 14);
		contentPanel.add(lblTitulo);
		
		progressBar = new JProgressBar();
		progressBar.setForeground(new Color(0, 0, 255));
		progressBar.setStringPainted(true);
		progressBar.setToolTipText("");
		progressBar.setBounds(30, 39, 376, 31);
		contentPanel.add(progressBar);
	}
	public class progreso implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int valor = progressBar.getValue();
			if (valor < 100) {
				valor++;
				progressBar.setValue(valor);

			} else {
				if (option == 1) {
					JOptionPane.showMessageDialog(null, "Los cambios se guardaron correctamente", "Bolsa Laboral",
							JOptionPane.INFORMATION_MESSAGE, null);

				}
				
				timer.stop();
				dispose();

			}

		}

	}
}