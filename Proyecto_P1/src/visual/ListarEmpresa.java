package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.UIManager;

import java.awt.Color;

import logica.BolsaLaboral;
import logica.Empresa;

public class ListarEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private DefaultTableModel model;
	private JButton BtnUpdate;
	private BolsaLaboral bolsa= BolsaLaboral.getInstance();
	private String nom;
	
	

	
	public ListarEmpresa() {
		setTitle("Lista Empresas");
		setBounds(100, 100, 614, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Empresas", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 25, 576, 182);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int aux = table.getSelectedRow();
						
						if (aux > -1){
			
						BtnUpdate.setEnabled(true);
						
						nom =(String) table.getModel().getValueAt(aux, 0);
						
						}else {
							BtnUpdate.setEnabled(false);
							
							String nombreVehi = "";
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String [] colimneNames  = { "RNC","Nombre Empresa","Provincia","Telefono","Email"};
				model = new DefaultTableModel();
				model.setColumnIdentifiers(colimneNames);
				table.setModel(model);
				loadTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				BtnUpdate = new JButton("Modificar");
				BtnUpdate.setEnabled(false);
				buttonPane.add(BtnUpdate);
			}
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

	private void loadTable() {
		model.setRowCount(0);
		fila = new Object [model.getColumnCount()];
		for (Empresa miemp : bolsa.getMisEmpresas()) 
		{ 
		fila[0] = miemp.getRNC();
		fila[1] =miemp.getNombre();
		fila[2] = miemp.getArea();
		fila[3] = miemp.getTelefono();
		fila[4] = miemp.getEmail();
		model.addRow(fila);
	    
	    	
	    	}
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(77);
		columnModel.getColumn(1).setPreferredWidth(145);
		columnModel.getColumn(2).setPreferredWidth(115);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(140);
		

		
		
	}
}