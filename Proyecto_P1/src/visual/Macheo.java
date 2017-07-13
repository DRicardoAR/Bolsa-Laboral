package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.List;
import java.text.ParseException;
import java.util.ArrayList;

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
import logica.Solicitante;
import logica.Solicitud;

import javax.swing.border.EtchedBorder;

import java.awt.Color;

import javax.swing.DefaultListModel;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Macheo extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static Object[] fila;
	private static String[] columnNames = {"Código","Vacantes","Rango Edad","Localidad","Experiencia"};
	private static DefaultTableModel modelo;
	private static DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private JFormattedTextField ftxtRNC;
	BolsaLaboral bolsa = BolsaLaboral.getInstance();
	Empresa miEmpresa = null;
	private JTextField txtNombreEmpresa;
	private JTable table;
	private String cod;
	private JButton btnCandidatos;
	private DefaultListModel<String> model;
	private JList list;
	private ArrayList<Solicitante> misSolicitantesC= new ArrayList<Solicitante>();
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
		setBounds(100, 100, 903, 535);
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
				panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				panel_1.setBounds(10, 11, 846, 430);
				panel.add(panel_1);
				panel_1.setLayout(null);
				{
					JPanel panel_2 = new JPanel();
					panel_2.setLayout(null);
					panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Empresa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
					panel_2.setBounds(10, 11, 505, 400);
					panel_1.add(panel_2);
					{
						JLabel label = new JLabel("RNC:");
						label.setBounds(10, 35, 46, 14);
						panel_2.add(label);
					}
					{
						JLabel label = new JLabel("Nombre:");
						label.setBounds(10, 66, 59, 14);
						panel_2.add(label);
					}
					{
						txtNombreEmpresa = new JTextField();
						txtNombreEmpresa.setEnabled(false);
						txtNombreEmpresa.setColumns(10);
						txtNombreEmpresa.setBounds(63, 63, 406, 21);
						panel_2.add(txtNombreEmpresa);
					}
					{
						JLabel label = new JLabel("C\u00F3digo Solicitud:");
						label.setBounds(238, 35, 102, 14);
						panel_2.add(label);
					}
					{
						JButton button = new JButton("");
						button.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if(bolsa.RetornarEmpresa(ftxtRNC.getText()) != null ){
									miEmpresa = bolsa.RetornarEmpresa(ftxtRNC.getText());
									txtNombreEmpresa.setText(miEmpresa.getNombre());
								}else{
									JOptionPane.showMessageDialog(null, "No se encontro una empresa con el RNC digitado.", "Información", JOptionPane.WARNING_MESSAGE, null);
								}
								
								
							}
						});
						button.setBounds(205, 32, 24, 21);
						panel_2.add(button);
					}
					{
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseClicked(MouseEvent arg0) {
								int aux = table.getSelectedRow();
								if(aux > -1){
									btnCandidatos.setEnabled(true);
									cod= (String) table.getModel().getValueAt(aux, 0);
								}else{
									 String cod="";
									btnCandidatos.setEnabled(false);
								}
								
							}
						});
						scrollPane.setBounds(10, 118, 469, 230);
						panel_2.add(scrollPane);
						{
							table = new JTable();
							modelo = new DefaultTableModel();
													
							
							table.setModel(modelo);
							loadTable();		
							scrollPane.setViewportView(table);
						}
					}
					{
						JLabel label = new JLabel("Solicitudes");
						label.setBounds(202, 93, 88, 14);
						panel_2.add(label);
					}
					{
						btnCandidatos = new JButton("Candidatos");
						btnCandidatos.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								solicitantes();
								cargarSolicitante();
							}
						});
						btnCandidatos.setEnabled(false);
						btnCandidatos.setBounds(377, 361, 102, 23);
						panel_2.add(btnCandidatos);
					}
					{
						MaskFormatter mascara = new MaskFormatter("##########");
						 ftxtRNC = new JFormattedTextField(mascara);
						ftxtRNC.setBounds(63, 32, 140, 21);
						panel_2.add(ftxtRNC);
					}
					{
						JFormattedTextField ftxtCodSolicitud = new JFormattedTextField();
						ftxtCodSolicitud.setEnabled(false);
						ftxtCodSolicitud.setBounds(335, 32, 134, 21);
						panel_2.add(ftxtCodSolicitud);
					}
				}
				{
					JPanel panel_2 = new JPanel();
					panel_2.setLayout(null);
					panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Solicitantes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
					panel_2.setBounds(525, 11, 311, 400);
					panel_1.add(panel_2);
					{
						JScrollPane scrollPane = new JScrollPane();
						scrollPane.setBounds(10, 32, 291, 357);
						panel_2.add(scrollPane);
						{
							list = new JList();
							scrollPane.setViewportView(list);
						}
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

	private void loadTable() {
		modelo.setColumnIdentifiers(columnNames);	
		modelo.setRowCount(0);
		fila = new Object[modelo.getColumnCount()];
for (Solicitud soli : bolsa.RetornaSolicitudEmp(miEmpresa)) {
	
			fila[0] =soli.getCodigo();
			fila[1] = soli.getCantVacantes();
			String min= Integer.toString(soli.getEdadMin());
			String max= Integer.toString(soli.getEdadMax());
			String rango= max + "-" +min;
			fila[3] = rango;
			fila[4] = soli.getLocalidad();
			fila[5] = soli.getAnnosExperiencia();
					
			

	modelo.addRow(fila);
	
	
}
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
	
	public void solicitantes(){
		Solicitud miSolicitudc = bolsa.RetornarSolocitudCod(cod);
		misSolicitantesC.addAll(bolsa.matcheo(miSolicitudc));
	}
	public void cargarSolicitante(){
		for (Solicitante soli : misSolicitantesC) {
			String nombre= soli.getCedula()+" "+soli.getNombres()+" "+soli.getApellidos();
			model.addElement(nombre);
		}
		list.setModel(model);
	}
	
}
