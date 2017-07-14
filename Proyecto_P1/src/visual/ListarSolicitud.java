package visual;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.MaskFormatter;

import logica.BolsaLaboral;
import logica.Empresa;

import javax.swing.JFormattedTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ListarSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JFormattedTextField ftxtRNCEmpresa;
	private JButton btnEliminar;
	private JButton btnModificar;
	private static JTable table;
	private static Object[] fila;
	private static DefaultTableModel modelo;
	private static DefaultTableCellRenderer centrar = new DefaultTableCellRenderer();
	private static TableColumnModel columnModel;
	private int indexSelec = 0;
	private BolsaLaboral bolsa = BolsaLaboral.getInstance();

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
		setTitle("Bolsa Laboral - Lista de Solicitudes");
		setBounds(100, 100, 1070, 553);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Lista de Solicitudes",
					TitledBorder.LEADING, TitledBorder.TOP, null, null));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JPanel panel_1 = new JPanel();
			panel_1.setBorder(null);
			panel_1.setBounds(10, 21, 478, 38);
			panel.add(panel_1);
			panel_1.setLayout(null);

			JLabel lblTipo = new JLabel("Tipo:");
			lblTipo.setBounds(10, 12, 46, 14);
			panel_1.add(lblTipo);

			JComboBox cbxTipo = new JComboBox();
			cbxTipo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnEliminar.setEnabled(false);
					btnModificar.setEnabled(false);
					if (cbxTipo.getSelectedIndex() == 0) {
						loadTable(0);
					}
					if (cbxTipo.getSelectedIndex() == 1) {
						loadTable(1);
					}
					if (cbxTipo.getSelectedIndex() == 2) {
						loadTable(2);
					}
					if (cbxTipo.getSelectedIndex() == 3) {
						loadTable(3);
					}

				}
			});
			cbxTipo.setModel(
					new DefaultComboBoxModel(new String[] { "Todos", "Universitarios", "T\u00E9cnicos", "Obreros" }));
			cbxTipo.setBounds(67, 8, 139, 21);
			panel_1.add(cbxTipo);

			JLabel lblEmpresa = new JLabel("Empresa:");
			lblEmpresa.setBounds(228, 12, 72, 14);
			panel_1.add(lblEmpresa);

			try {
				MaskFormatter mascara = new MaskFormatter("##########");
				ftxtRNCEmpresa = new JFormattedTextField(mascara);
				ftxtRNCEmpresa.setBounds(287, 8, 139, 21);
				panel_1.add(ftxtRNCEmpresa);

				JButton button = new JButton("");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Empresa miEmpresa = bolsa.RetornarEmpresa(ftxtRNCEmpresa.getText());
						if (miEmpresa != null) {
							
							

						}
					}
				});
				button.setBounds(430, 8, 27, 21);
				panel_1.add(button);
				{
					JScrollPane scrollPane = new JScrollPane();
					scrollPane.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							btnModificar.setEnabled(false);
							btnEliminar.setEnabled(false);
						}
					});
					scrollPane.setBounds(10, 70, 1022, 374);
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
								}

							}
						});
						modelo = new DefaultTableModel();
						loadTable(indexSelec);
						table.setModel(modelo);
						scrollPane.setViewportView(table);
					}
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar");
				btnModificar.setEnabled(false);
				buttonPane.add(btnModificar);
			}
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setEnabled(false);
				btnEliminar.setActionCommand("OK");
				buttonPane.add(btnEliminar);
				getRootPane().setDefaultButton(btnEliminar);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private static void loadTable(int indexSeleccion) {
		if (indexSeleccion == 0) {
			loadTableAll(indexSeleccion);
		}
		if (indexSeleccion == 1) {
			loadTableAll(indexSeleccion);
		}
		if (indexSeleccion == 2) {
			loadTableAll(indexSeleccion);
			// loadTecnico();
		}
		if (indexSeleccion == 3) {
			loadTableAll(indexSeleccion);
			// loadObrero();
		}

	}

	private static void loadTableAll(int indexSelection) {
		String[] columnNames = title(indexSelection);
		modelo.setColumnIdentifiers(columnNames);
		modelo.setRowCount(0);
		fila = new Object[modelo.getColumnCount()];

		if (indexSelection == 0) {
			/*
			 * Codigo aqui
			 */

		}
		if (indexSelection == 1) {
			/*
			 * Codigo aqui
			 */
		}
		if (indexSelection == 2) {
			/*
			 * Codigo aqui
			 */
		}
		if (indexSelection == 3) {
			/*
			 * Codigo aqui
			 */
		}

		table.setModel(modelo);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);

		centrar.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < columnNames.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centrar);
		}

		tamano(indexSelection);

	}

	public static void tamano(int indexSelection) {
		columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(65);
		columnModel.getColumn(1).setPreferredWidth(149);
		columnModel.getColumn(2).setPreferredWidth(80);
		columnModel.getColumn(3).setPreferredWidth(80);
		columnModel.getColumn(4).setPreferredWidth(100);
		columnModel.getColumn(5).setPreferredWidth(100);
		columnModel.getColumn(6).setPreferredWidth(60);
		columnModel.getColumn(7).setPreferredWidth(110);
		columnModel.getColumn(8).setPreferredWidth(80);
		if (indexSelection == 0) {
			columnModel.getColumn(1).setPreferredWidth(180);
			columnModel.getColumn(7).setPreferredWidth(150);
			columnModel.getColumn(6).setPreferredWidth(90);
			columnModel.getColumn(4).setPreferredWidth(150);
			columnModel.getColumn(3).setPreferredWidth(100);
			columnModel.getColumn(2).setPreferredWidth(104);
		}
		if (indexSelection == 1) {
			columnModel.getColumn(9).setPreferredWidth(115);
			columnModel.getColumn(10).setPreferredWidth(80);
		}
		if (indexSelection == 2) {
			columnModel.getColumn(9).setPreferredWidth(135);
			columnModel.getColumn(7).setPreferredWidth(150);
			columnModel.getColumn(4).setPreferredWidth(120);
		}
		if (indexSelection == 3) {
			columnModel.getColumn(9).setPreferredWidth(135);
			columnModel.getColumn(7).setPreferredWidth(150);
			columnModel.getColumn(4).setPreferredWidth(120);
		}

	}

	private static String[] title(int indexSelection) {
		String[] columnNamesAux = null;
		if (indexSelection == 0) {
			String[] columnNames = { "Código", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato",
					"Vehiculo", "Provincia", "Reubicación" };
			columnNamesAux = columnNames;
		}
		if (indexSelection == 1) {
			String[] columnNames = { "Código", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato",
					"Vehiculo", "Provincia", "Reubicación", "Carrera", "PostGrado" };
			columnNamesAux = columnNames;
		}
		if (indexSelection == 2) {
			String[] columnNames = { "Código", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato",
					"Vehiculo", "Provincia", "Reubicación", "Area" };
			columnNamesAux = columnNames;
		}
		if (indexSelection == 3) {
			String[] columnNames = { "Código", "Empresa", "Vacantes", "Experiencia", "Rango Edad", "Contrato",
					"Vehiculo", "Provincia", "Reubicación", "Habilidades" };
			columnNamesAux = columnNames;
		}
		return columnNamesAux;
	}
}
