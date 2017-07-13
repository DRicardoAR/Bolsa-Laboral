package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import logica.BolsaLaboral;
import logica.Empresa;
import logica.Solicitud;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Macheo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtNombre;
	private JTable table;
	private static Object[] fila;
	private static String[] columnNames = {"Código","Vacantes","Rango Edad","Localidad","Experiencia"};
	private static DefaultTableModel modelo;
	private static DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private JFormattedTextField ftxtRNC;
	private JFormattedTextField ftxtCodigoSolicitud;
	
	BolsaLaboral bolsa = BolsaLaboral.getInstance();
	Empresa miEmpresa = null;
	Solicitud miSolicitud = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Macheo dialog = new Macheo();
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
	public Macheo() throws ParseException {
		setTitle("Macheo");
		setBounds(100, 100, 868, 520);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_1.setBounds(10, 11, 505, 400);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JLabel lblRnc = new JLabel("RNC:");
					lblRnc.setBounds(10, 35, 46, 14);
					panel_1.add(lblRnc);
				}
				{
					JLabel lblNombre = new JLabel("Nombre:");
					lblNombre.setBounds(10, 66, 59, 14);
					panel_1.add(lblNombre);
				}
				{
					MaskFormatter mascara = new MaskFormatter("##########");
					ftxtRNC = new JFormattedTextField(mascara);
					ftxtRNC.setBounds(63, 32, 140, 21);
					panel_1.add(ftxtRNC);
				}
				{
					txtNombre = new JTextField();
					txtNombre.setEnabled(false);
					txtNombre.setBounds(63, 63, 406, 21);
					panel_1.add(txtNombre);
					txtNombre.setColumns(10);
				}
				{
					JLabel lblCodigo = new JLabel("C\u00F3digo Solicitud:");
					lblCodigo.setBounds(238, 35, 102, 14);
					panel_1.add(lblCodigo);
				}
				{
					MaskFormatter mascara = new MaskFormatter("#####");
					ftxtCodigoSolicitud = new JFormattedTextField(mascara);
					ftxtCodigoSolicitud.setBounds(334, 32, 109, 21);
					panel_1.add(ftxtCodigoSolicitud);
				}
				{
					JButton btnNewButton = new JButton("");
					btnNewButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String RNC = ftxtRNC.getText();
							if(bolsa.RetornarEmpresa(RNC) != null){
								miEmpresa = bolsa.RetornarEmpresa(RNC);	
								txtNombre.setText(miEmpresa.getNombre());	
							}else{
								JOptionPane.showMessageDialog(null, "No se encontro ninguna empresa","ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
							}
												
												
							
						}
					});
					btnNewButton.setBounds(205, 32, 24, 21);
					panel_1.add(btnNewButton);
				}
				{
					JButton button = new JButton("");
					button.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							String codigo = ftxtCodigoSolicitud.getText();
							if(ftxtRNC.getText().isEmpty()){
								JOptionPane.showMessageDialog(null, "Digite un RNC","ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
							}else{
								if(bolsa.RetornarSolocitud(codigo) != null){
									miSolicitud = bolsa.RetornarSolocitud(codigo);
									txtNombre.setText(miSolicitud.getEmpresa().getNombre());
									loadTable(2);								
								}else{
									JOptionPane.showMessageDialog(null, "No se encontro ninguna solicitud","ATENCIÓN",	JOptionPane.WARNING_MESSAGE, null);
								}
								
							}
							
							
						}
					});
					button.setBounds(445, 32, 24, 21);
					panel_1.add(button);
				}
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(10, 118, 469, 230);
					panel_1.add(scrollPane);
					
					table = new JTable();
					modelo = new DefaultTableModel();							
					loadTable(1);								
					table.setModel(modelo);
					scrollPane.setViewportView(table);
					
				}
				{
					JLabel lblSolicitudes = new JLabel("Solicitudes");
					lblSolicitudes.setBounds(202, 93, 88, 14);
					panel_1.add(lblSolicitudes);
				}
				{
					JButton btnNewButton_1 = new JButton("Ver Solicitantes");
					btnNewButton_1.setBounds(345, 361, 134, 23);
					panel_1.add(btnNewButton_1);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Solicitantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_1.setBounds(523, 11, 311, 400);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.setBounds(10, 32, 291, 357);
					panel_1.add(scrollPane);
					{
						JList list = new JList();
						scrollPane.setViewportView(list);
					}
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Macheo");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void loadTable(int option) {
		modelo.setColumnIdentifiers(columnNames);	
		modelo.setRowCount(0);
		fila = new Object[modelo.getColumnCount()];	
		
		if(option == 1){
			
			/*
			 * 
			 ///PROGRAR TABLA DADO EL RNC DE LA EMPRSA
			  * 
			 */
			  
			
		}
		
		
		if(option == 2){
			fila[0] = miSolicitud.getCodigo();
			fila[1] = miSolicitud.getCantVacantes();
			String edadmin = Integer.toString(miSolicitud.getEdadMin());
			String edadMax = Integer.toString(miSolicitud.getEdadMax());
			fila[2] = edadmin+" - "+edadMax;
			fila[3] = miSolicitud.getLocalidad();
			fila[4] = miSolicitud.getAnnosExperiencia();
			modelo.addRow(fila);
			
		}
		
		table.setModel(modelo);		
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();	
		centrar.setHorizontalAlignment(SwingConstants.CENTER); 
		
		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}		
		columnModel.getColumn(0).setPreferredWidth(80);
		columnModel.getColumn(1).setPreferredWidth(80);
		columnModel.getColumn(2).setPreferredWidth(106);
		columnModel.getColumn(3).setPreferredWidth(120);
		columnModel.getColumn(4).setPreferredWidth(80);
		
	}
}
