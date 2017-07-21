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
import javax.swing.text.MaskFormatter;

import logica.BolsaLaboral;
import logica.Empresa;
import logica.Obrero;
import logica.Solicitante;
import logica.Tecnico;
import logica.Universitario;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.text.ParseException;

public class ListarEmpleados extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel modeloTabla;
	private JButton btnVerEmpleado;
	private String cedulaCliente = "";
	private static JFormattedTextField formattedTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ListarEmpleados dialog = new ListarEmpleados();
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
	public ListarEmpleados()  {
		setTitle("Reporte de Empleados");
		setResizable(false);
		setBounds(100, 100, 770, 412);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(null, "Empleados Cnotratados por Empresa", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			
			{
				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 57, 734, 272);
				panel.add(scrollPane);
				{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						int aux = table.getSelectedRow();

						if (aux > -1) {
							btnVerEmpleado.setEnabled(true);
							cedulaCliente = (String) table.getModel().getValueAt(aux, 0);

						} else {
							btnVerEmpleado.setEnabled(false);
							cedulaCliente = "";
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setResizingAllowed(false);
				String[] nombreColumna = { "Cédula", "Nombre", "Tipo", "Fecha de Contrato" };
				modeloTabla = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int coumn) {
						return false;
					}
				};
				modeloTabla.setColumnIdentifiers(nombreColumna);
				
				scrollPane.setViewportView(table);
				}
			}
			JLabel lblEmpresa = new JLabel("Empresa:");
			lblEmpresa.setBounds(10, 31, 65, 14);
			panel.add(lblEmpresa);
			
			JButton btnBuscar = new JButton("");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					loadTabla();
				}
			});
			btnBuscar.setBounds(189, 28, 27, 21);
			panel.add(btnBuscar);
			try {
				MaskFormatter mask = new MaskFormatter("##########");
				formattedTextField = new JFormattedTextField(mask);
				formattedTextField.setBounds(73, 28, 106, 21);
				panel.add(formattedTextField);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			

			
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVerEmpleado = new JButton("Ver Empleado");
				buttonPane.add(btnVerEmpleado);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
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
	public static void loadTabla(){
		Empresa empresa = BolsaLaboral.getInstance().RetornarEmpresa(formattedTextField.getText());
		String tipo = "";
		modeloTabla.setRowCount(0);
		fila = new Object[modeloTabla.getColumnCount()];
	
		if(empresa!=null){
			for (Solicitante soli: empresa.getMisContratados()) {
				if(soli instanceof Tecnico){
					tipo = "Tecnico";
				}if(soli instanceof Obrero){
					tipo = "Obrero";
				}if(soli instanceof Universitario){
					tipo = "Universitario";
				}
				fila[0] = soli.getCedula();
				fila[1] = soli.getNombres()+" "+soli.getApellidos();
				fila[2] = tipo;
				fila[3] = soli.getFechaContratado();
				modeloTabla.addRow(fila);
			}
			table.setModel(modeloTabla);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			table.getTableHeader().setReorderingAllowed(false);
			TableColumnModel modeloColumna = table.getColumnModel();
			modeloColumna.getColumn(0).setPreferredWidth(90);
			modeloColumna.getColumn(1).setPreferredWidth(140);
			modeloColumna.getColumn(2).setPreferredWidth(110);
			modeloColumna.getColumn(3).setPreferredWidth(100);
		}else{
			JOptionPane.showMessageDialog(null, "No se encontró ningún solicitud para el RNC dado.",
					"ATENCIÓN", JOptionPane.ERROR_MESSAGE, null);
		}
	}
}
