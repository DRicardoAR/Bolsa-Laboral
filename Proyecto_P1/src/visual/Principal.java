package visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	  Create the frame.
	 */
	public Principal() {
		setResizable(false);
		setTitle("Bolsa Laboral");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 472);
		setLocationRelativeTo(null);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnCandidatos = new JMenu("Solicitante");
		menuBar.add(mnCandidatos);
		
		JMenuItem mntmRegistrarCandidato = new JMenuItem("Registrar Solicitante");
		mntmRegistrarCandidato.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitate soli = new InsertarSolicitate();
				soli.setModal(true);
				soli.setLocationRelativeTo(null);
				soli.setVisible(true);
			}
		});
		mnCandidatos.add(mntmRegistrarCandidato);
		
		JMenuItem mntmListarCandidatos = new JMenuItem("Listar Solicitante");
		mnCandidatos.add(mntmListarCandidatos);
		
		JMenu mnEmpresa = new JMenu("Empresa");
		menuBar.add(mnEmpresa);
		
		JMenuItem mntmRegistrarEmpresa = new JMenuItem("Registrar Empresa");
		mntmRegistrarEmpresa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarEmpresa empre = new InsertarEmpresa();
				empre.setModal(true);
				empre.setLocationRelativeTo(null);
				empre.setVisible(true);
			}
		});
		mnEmpresa.add(mntmRegistrarEmpresa);
		
		JMenuItem mntmRealizarSolicitud = new JMenuItem("Realizar Solicitud");
		mntmRealizarSolicitud.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertarSolicitud solicitud = new InsertarSolicitud();
				solicitud.setModal(true);
				solicitud.setVisible(true);
			}
		});
		mnEmpresa.add(mntmRealizarSolicitud);
		
		JMenuItem mntmListarEmpresas = new JMenuItem("Listar Empresas");
		mnEmpresa.add(mntmListarEmpresas);
		
		JMenu mnMacheo = new JMenu("Macheo");
		menuBar.add(mnMacheo);
		
		JMenuItem mntmRealizarMacheo = new JMenuItem("Realizar Macheo");
		mntmRealizarMacheo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Macheo macheo;
				try {
					macheo = new Macheo();
					macheo.setModal(true);
					macheo.setLocationRelativeTo(null);
					macheo.setVisible(true);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		mnMacheo.add(mntmRealizarMacheo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
	}

}
