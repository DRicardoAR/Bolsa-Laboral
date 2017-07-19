package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import logica.BolsaLaboral;
import logica.Obrero;
import logica.Solicitante;
import logica.Tecnico;
import logica.Universitario;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.UIManager;
import java.awt.Color;

public class ListarSolicitante extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel modeloTabla;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JComboBox cbxFiltro;
	private JButton cancelButton;
	private String cedulaCliente = "";
	private JComboBox<String>habilidades;
	private static JComboBox<String>cbxIdioma;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			ListarSolicitante dialog = new ListarSolicitante();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ListarSolicitante() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				cbxFiltro.setSelectedIndex(0);
				loadTablaG();
			}
		});
		setTitle("Listar Solicitantes\r\n");
		setResizable(false);
		setBounds(100, 100, 961, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Solicitudes de  Solicitantes", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
 
			cbxFiltro = new JComboBox();
			cbxFiltro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String seleccion = cbxFiltro.getSelectedItem().toString();
					if(seleccion.equalsIgnoreCase("General")){
						loadTablaG();
					}if(seleccion.equalsIgnoreCase("Universitarios")){
						loadTablaU();
					}if(seleccion.equalsIgnoreCase("Obreros")){
						loadTablaO();
					}if(seleccion.equalsIgnoreCase("Técnicos")){
						loadTablaT();
					}
				}
			});
			cbxFiltro.setModel(
					new DefaultComboBoxModel(new String[] {"General", "Obreros", "T\u00E9cnicos", "Universitarios"}));
			cbxFiltro.setBounds(763, 37, 119, 20);
			panel.add(cbxFiltro);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 68, 925, 343);
			panel.add(scrollPane);
			{
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int aux = table.getSelectedRow();

					if (aux > -1) {
						btnModificar.setEnabled(true);
						btnEliminar.setEnabled(true);
						 cedulaCliente =(String)table.getModel().getValueAt(aux, 0);
					} else {
						btnModificar.setEnabled(false);
						btnEliminar.setEnabled(false);
						cedulaCliente = "";
					}
				}
			});
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.getTableHeader().setResizingAllowed(false);
			modeloTabla = new DefaultTableModel() {
				
				@Override
				public boolean isCellEditable(int row, int column) {
					return true ;
				}
			};
			String tipo = cbxFiltro.getSelectedItem().toString();
			
			loadTabla(tipo);
			scrollPane.setViewportView(table);
			}

		

			JLabel lblFiltrarPor = new JLabel("Filtrar por :");
			lblFiltrarPor.setBounds(667, 40, 63, 14);
			panel.add(lblFiltrarPor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				btnModificar = new JButton("Modificar");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Solicitante persona = BolsaLaboral.getInstance().retornarSolicitante(cedulaCliente);
						InsertarSolicitate soli = new InsertarSolicitate("Modificar Solicitante",true,persona);
						soli.setModal(true);
						soli.setLocationRelativeTo(null);
						soli.setVisible(true);
					}
				});
				btnModificar.setEnabled(false);
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
public static void loadTabla(String seleccion){
	if(seleccion.equalsIgnoreCase("General")){
		loadTablaG();
	}if(seleccion.equalsIgnoreCase("Universitarios")){
		loadTablaU();
		
	}if(seleccion.equalsIgnoreCase("Obreros")){
		
	}if(seleccion.equalsIgnoreCase("Técnicos")){
		
	}
}
	public static void loadTablaG() {
		String tipo = "";
		String[] nombreColumna = { "Cédula", "Nombre", "Edad", "Años de Experiencia", "Tipo" };
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitante soli : BolsaLaboral.getInstance().getMisPersonas()) {
				if (!soli.isContratado()) {
					tipo = "Universitario";
					if (soli instanceof Obrero) {
						tipo = "Obrero";
					}
					if (soli instanceof Tecnico) {
						tipo = "Técnico";
					}
					fila[0] = soli.getCedula();
					fila[1] = soli.getNombres() + " " + soli.getApellidos();
					fila[2] = soli.getEdad()+" años";
					fila[3] = soli.getAnnosExperiencia()+" años";
					fila[4] = tipo;
					modeloTabla.addRow(fila);
					
					table.setModel(modeloTabla);
					table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
					table.getTableHeader().setReorderingAllowed(false);
					TableColumnModel modeloColumna = table.getColumnModel();
					modeloColumna.getColumn(0).setPreferredWidth(160);
					modeloColumna.getColumn(1).setPreferredWidth(282);
					modeloColumna.getColumn(2).setPreferredWidth(160);
					modeloColumna.getColumn(3).setPreferredWidth(170);
					modeloColumna.getColumn(4).setPreferredWidth(150);
				}
			
			
		}
		
		
	}
	public static void loadTablaU(){
		
		String[] nombreColumna = { "Cédula", "Nombre", "Edad","Carrera", "Idiomas","Años de Experiencia","Teléfono","E-Mail"};
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitante soli : BolsaLaboral.getInstance().getMisPersonas()) {
			if(soli instanceof Universitario){
				String []idioma = llenado(soli.getIdiomas());
				cbxIdioma = new JComboBox<String>(idioma);
				setComboIdiomas();
				
				fila[0] = soli.getCedula();
				fila[1] = soli.getNombres() + " " + soli.getApellidos();
				fila[2] = soli.getEdad()+" años";
				fila[3] = ((Universitario) soli).getCarrera();
				fila[4] = "Hacer click";
				fila[5] = soli.getAnnosExperiencia()+" años";
				fila[6] = soli.getTelefono();
				fila[7] = soli.getEmail();
				modeloTabla.addRow(fila);
				
				table.setModel(modeloTabla);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumnModel modeloColumna = table.getColumnModel();
				modeloColumna.getColumn(0).setPreferredWidth(95);
				modeloColumna.getColumn(1).setPreferredWidth(150);
				modeloColumna.getColumn(2).setPreferredWidth(80);
				modeloColumna.getColumn(3).setPreferredWidth(110);
				modeloColumna.getColumn(4).setPreferredWidth(100);
				modeloColumna.getColumn(5).setPreferredWidth(160);
				modeloColumna.getColumn(6).setPreferredWidth(100);
				modeloColumna.getColumn(7).setPreferredWidth(140);
			}
			
		}
	}
	
	public void loadTablaO(){
	
		String[] nombreColumna = { "Cédula", "Nombre", "Edad","Habilidades", "Idiomas","Años de Experiencia","Teléfono","E-Mail"};
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitante soli : BolsaLaboral.getInstance().getMisPersonas()) {
			if(soli instanceof Obrero){
				String []idioma = llenado(soli.getIdiomas());
				String []habilidad = llenado(((Obrero) soli).getHabilidades());
				habilidades = new JComboBox<String>(habilidad);
				cbxIdioma = new JComboBox<String>(idioma);
				setComboIdiomas();
				setCombo();
				
				fila[0] = soli.getCedula();
				fila[1] = soli.getNombres() + " " + soli.getApellidos();
				fila[2] = soli.getEdad()+" años";
				fila[3] = "Hacer click";
				fila[4] = "Hacer click";
				fila[5] = soli.getAnnosExperiencia()+" años";
				fila[6] = soli.getTelefono();
				fila[7] = soli.getEmail();
				modeloTabla.addRow(fila);
				
				table.setModel(modeloTabla);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumnModel modeloColumna = table.getColumnModel();
				modeloColumna.getColumn(0).setPreferredWidth(90);
				modeloColumna.getColumn(1).setPreferredWidth(140);
				modeloColumna.getColumn(2).setPreferredWidth(110);
				modeloColumna.getColumn(3).setPreferredWidth(100);
				modeloColumna.getColumn(4).setPreferredWidth(100);
				modeloColumna.getColumn(5).setPreferredWidth(160);
				modeloColumna.getColumn(6).setPreferredWidth(100);
				modeloColumna.getColumn(7).setPreferredWidth(140);
			}
			
		}
	}
	public void loadTablaT(){
		String[] nombreColumna = { "Cédula", "Nombre", "Edad","Área","Idiomas", "Años de Experiencia","Teléfono","E-Mail"};
		modeloTabla.setColumnIdentifiers(nombreColumna);
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
		for (Solicitante soli : BolsaLaboral.getInstance().getMisPersonas()) {
			if(soli instanceof Tecnico){
				String []idioma = llenado(soli.getIdiomas());
				cbxIdioma = new JComboBox<String>(idioma);
				setComboIdiomas();
				
				fila[0] = soli.getCedula();
				fila[1] = soli.getNombres() + " " + soli.getApellidos();
				fila[2] = soli.getEdad()+" años";
				fila[3] = ((Tecnico) soli).getArea();
				fila[4] = "Hacer click";
				fila[5] = soli.getAnnosExperiencia()+" años";
				fila[6] = soli.getTelefono();
				fila[7] = soli.getEmail();
				modeloTabla.addRow(fila);
				
				table.setModel(modeloTabla);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				table.getTableHeader().setReorderingAllowed(false);
				TableColumnModel modeloColumna = table.getColumnModel();
				modeloColumna.getColumn(0).setPreferredWidth(90);
				modeloColumna.getColumn(1).setPreferredWidth(140);
				modeloColumna.getColumn(2).setPreferredWidth(110);
				modeloColumna.getColumn(3).setPreferredWidth(100);
				modeloColumna.getColumn(4).setPreferredWidth(100);
				modeloColumna.getColumn(5).setPreferredWidth(160);
				modeloColumna.getColumn(6).setPreferredWidth(100);
				modeloColumna.getColumn(7).setPreferredWidth(140);
			}
			
		}
		
	}
	public static  void setComboIdiomas(){
		TableColumn col = table.getColumnModel().getColumn(4);
		col.setCellEditor(new DefaultCellEditor(cbxIdioma));
	}
	public void setCombo(){
		TableColumn col = table.getColumnModel().getColumn(3);
		col.setCellEditor(new DefaultCellEditor(habilidades));
		habilidades.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
	}
	public static String[] llenado (ArrayList<String>copiado){
		String [] arr = new String[copiado.size()];
		copiado.toArray(arr);
		return arr;
	}
}
