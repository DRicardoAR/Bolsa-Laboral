package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import logica.BolsaLaboral;
import logica.Solicitud;
import logica.SolicitudObrero;
import logica.SolicitudTecnico;
import logica.SolicitudUniversitario;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private static DefaultTableModel modeloTabla;
	private static DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private JComboBox cbxfiltro;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarSolicitud dialog = new ListarSolicitud();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarSolicitud() {
		setBounds(100, 100, 1067, 519);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Lista de Solicitudes", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			{
				JLabel lblFiltrarPor = new JLabel("Filtrar por:");
				lblFiltrarPor.setBounds(10, 25, 68, 14);
				panel.add(lblFiltrarPor);
			}
			{
				cbxfiltro = new JComboBox();
				cbxfiltro.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(cbxfiltro.getSelectedIndex() == 0){
							loadtabla(0);
						}
						else if(cbxfiltro.getSelectedIndex() ==1){
							loadtabla(1);
						}else if(cbxfiltro.getSelectedIndex()==2){
							loadtabla(2);
						}else if(cbxfiltro.getSelectedIndex()==3){
							loadtabla(3);
						}
						
					}

					private void loadtabla(int i) {
						if(i == 0){
							loadAll();
						}
						if(i == 1){
							loadUniversitario();
						}
						if(i==2){
							loadTecnico();
							
						}
						if(i == 3){
							loadObrero();
						}
						
					}

				
					
					
				});
				cbxfiltro.setModel(new DefaultComboBoxModel(new String[] {"Todos", "Universitatio", "T\u00E9cnico", "Obrero"}));
				cbxfiltro.setBounds(77, 22, 118, 21);
				panel.add(cbxfiltro);
			}
			{
				JLabel lblRncEmpresa = new JLabel("RNC Empresa:");
				lblRncEmpresa.setBounds(211, 25, 85, 14);
				panel.add(lblRncEmpresa);
			}
			{
				JFormattedTextField formattedTextField = new JFormattedTextField();
				formattedTextField.setBounds(306, 22, 118, 21);
				panel.add(formattedTextField);
			}
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 63, 1022, 363);
				panel.add(scrollPane);
				{
					table = new JTable();
					modeloTabla = new DefaultTableModel();
					loadAll();
					scrollPane.setViewportView(table);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnEliminar = new JButton("Eliminar");
				buttonPane.add(btnEliminar);
			}
			{
				JButton btnModificar = new JButton("Modificar");
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private void loadAll() {
		String[] nombreColumna = { "C�digo", "Empresa","Solicitado" ,"Vacantes", "Experiencia", "Rango Edad", "Contrato","Vehiculo", "Provincia", "Reubicaci�n" };
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitud soli : BolsaLaboral.getInstance().getMisSolicitudes()) {
			fila[0] = soli.getCodigo();
			fila[1] = soli.getEmpresa().getNombre();
			if(soli instanceof SolicitudUniversitario){
				fila[2] = "Universitario";
			}
			if(soli instanceof SolicitudTecnico){
				fila[2] = "T�cnico";
			}
			if(soli instanceof SolicitudObrero){
				fila[2] = "Obrero";
			}
			fila[3] = soli.getCantVacantes();
			fila[4]=soli.getAnnosExperiencia();
			String min = Integer.toString(soli.getEdadMin());
			String max = Integer.toString(soli.getEdadMax());
			fila[5] = min+" - "+max;
			fila[6] = soli.getTipoContrato();
			if(soli.isVehiculoPropio()){
				fila[7] = "Si";
			}else{
				fila[7] = "No";
			}
			fila[8] = soli.getLocalidad();
			if(soli.isMudarse()){
				fila[9] = "Si";
			}else{
				fila[9] = "No";
			}
			modeloTabla.addRow(fila);			
		}
		table.setModel(modeloTabla);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		centrar.setHorizontalAlignment(SwingConstants.CENTER); 		
		for (int i = 0; i < nombreColumna.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}
		
		columnModel.getColumn(0).setPreferredWidth(65);
		columnModel.getColumn(1).setPreferredWidth(180);
		columnModel.getColumn(2).setPreferredWidth(110);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(80);
		columnModel.getColumn(5).setPreferredWidth(125);
		columnModel.getColumn(6).setPreferredWidth(125);
		columnModel.getColumn(7).setPreferredWidth(60);
		columnModel.getColumn(8).setPreferredWidth(110);
		columnModel.getColumn(9).setPreferredWidth(84);
	}
	
	private void loadUniversitario() {
		String[] nombreColumna = { "C�digo", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato","Vehiculo", "Provincia", "Reubicaci�n", "Carrera", "PostGrado" };
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitud soli : BolsaLaboral.getInstance().getMisSolicitudes()) {
			if(soli instanceof SolicitudUniversitario){
				fila[0] = soli.getCodigo();
				fila[1] = soli.getEmpresa().getNombre();
				fila[2] = soli.getCantVacantes();
				fila[3]=soli.getAnnosExperiencia();
				String min = Integer.toString(soli.getEdadMin());
				String max = Integer.toString(soli.getEdadMax());
				fila[4] = min+" - "+max;
				fila[5] = soli.getTipoContrato();
				if(soli.isVehiculoPropio()){
					fila[6] = "Si";
				}else{
					fila[6] = "No";
				}
				fila[7] = soli.getLocalidad();
				if(soli.isMudarse()){
					fila[8] = "Si";
				}else{
					fila[8] = "No";
				}
				fila[9] = ((SolicitudUniversitario)soli).getCarrera();
				if(((SolicitudUniversitario)soli).isPostGrado()){
					fila[10] = "Si";
				}else{
					fila[10] = "No";
				}
				modeloTabla.addRow(fila);			
				
			}
			
		}
		table.setModel(modeloTabla);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		centrar.setHorizontalAlignment(SwingConstants.CENTER); 		
		for (int i = 0; i < nombreColumna.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}
		
		
		columnModel.getColumn(0).setPreferredWidth(65);
		columnModel.getColumn(1).setPreferredWidth(149);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(60);
		columnModel.getColumn(7).setPreferredWidth(110);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(115);
		columnModel.getColumn(10).setPreferredWidth(80);
	}
	
	private void loadTecnico() {
		String[] nombreColumna = { "C�digo", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato","Vehiculo", "Provincia", "Reubicaci�n", "Area" };
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitud soli : BolsaLaboral.getInstance().getMisSolicitudes()) {
			if(soli instanceof SolicitudTecnico){
				fila[0] = soli.getCodigo();
				fila[1] = soli.getEmpresa().getNombre();			
				fila[2] = soli.getCantVacantes();
				fila[3]=soli.getAnnosExperiencia();
				String min = Integer.toString(soli.getEdadMin());
				String max = Integer.toString(soli.getEdadMax());
				fila[4] = min+" - "+max;
				fila[5] = soli.getTipoContrato();
				if(soli.isVehiculoPropio()){
					fila[6] = "Si";
				}else{
					fila[6] = "No";
				}
				fila[7] = soli.getLocalidad();
				if(soli.isMudarse()){
					fila[8] = "Si";
				}else{
					fila[8] = "No";
				}
				fila[9] = ((SolicitudTecnico)soli).getArea();
				
				modeloTabla.addRow(fila);			
				
			}
			
		}
		table.setModel(modeloTabla);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		centrar.setHorizontalAlignment(SwingConstants.CENTER); 		
		for (int i = 0; i < nombreColumna.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}
		
		
		columnModel.getColumn(0).setPreferredWidth(65);
		columnModel.getColumn(1).setPreferredWidth(149);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(120);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(60);
		columnModel.getColumn(7).setPreferredWidth(150);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(135);
		
	}
	
	private void loadObrero() {
		String[] nombreColumna = { "C�digo", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato","Vehiculo", "Provincia", "Reubicaci�n", "Habilidades" };
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitud soli : BolsaLaboral.getInstance().getMisSolicitudes()) {
			if(soli instanceof SolicitudObrero){
				fila[0] = soli.getCodigo();
				fila[1] = soli.getEmpresa().getNombre();			
				fila[2] = soli.getCantVacantes();
				fila[3]=soli.getAnnosExperiencia();
				String min = Integer.toString(soli.getEdadMin());
				String max = Integer.toString(soli.getEdadMax());
				fila[4] = min+" - "+max;
				fila[5] = soli.getTipoContrato();
				if(soli.isVehiculoPropio()){
					fila[6] = "Si";
				}else{
					fila[6] = "No";
				}
				fila[7] = soli.getLocalidad();
				if(soli.isMudarse()){
					fila[8] = "Si";
				}else{
					fila[8] = "No";
				}
				fila[9] ="Habilidasdes";
				
				modeloTabla.addRow(fila);			
				
			}
			
		}
		table.setModel(modeloTabla);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		centrar.setHorizontalAlignment(SwingConstants.CENTER); 		
		for (int i = 0; i < nombreColumna.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}
		
		
		columnModel.getColumn(0).setPreferredWidth(65);
		columnModel.getColumn(1).setPreferredWidth(149);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(120);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(60);
		columnModel.getColumn(7).setPreferredWidth(150);
		columnModel.getColumn(8).setPreferredWidth(80);
		columnModel.getColumn(9).setPreferredWidth(135);
		
	}


	

}
