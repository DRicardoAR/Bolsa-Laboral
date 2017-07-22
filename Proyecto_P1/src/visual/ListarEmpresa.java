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
import javax.swing.JOptionPane;
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
import java.text.ParseException;

import logica.BolsaLaboral;
import logica.Empresa;
import logica.Solicitud;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;

public class ListarEmpresa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private DefaultTableModel model;
	private JButton BtnUpdate;
	private BolsaLaboral bolsa = BolsaLaboral.getInstance();
	private String cod;
	private Empresa miem = null;
	private JButton btnEliminar;

	public ListarEmpresa() {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(248, 248, 255));
		setBackground(new Color(248, 248, 255));
		setTitle("Lista Empresas");
		setBounds(100, 100, 671, 498);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 255));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Listado de Empresas",
					TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
			contentPanel.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
				}
			});
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setBounds(10, 25, 625, 360);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						int aux = table.getSelectedRow();

						if (aux > -1) {

							BtnUpdate.setEnabled(true);
							btnEliminar.setEnabled(true);

							cod = (String) table.getModel().getValueAt(aux, 0);

						} else {
							BtnUpdate.setEnabled(false);
							btnEliminar.setEnabled(false);
							String cod = "";
						}
					}
				});
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] colimneNames = { "RNC", "Nombre", "Provincia", "Telefono", "Email" };
				model = new DefaultTableModel();
				model.setColumnIdentifiers(colimneNames);
				table.setModel(model);
				table.getTableHeader().setResizingAllowed(false);
				loadTable();
				scrollPane.setViewportView(table);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnEliminar = new JButton("Eliminar");
				btnEliminar.setIcon(new ImageIcon(ListarEmpresa.class.getResource("/img/borrar.png")));
				btnEliminar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (cod != "") {
							int answer = JOptionPane.showConfirmDialog(null, "Seguro que desea eliminar la empresa?",
									"Devolver publicacion", JOptionPane.YES_NO_CANCEL_OPTION,
									JOptionPane.QUESTION_MESSAGE, null);
							if (answer == JOptionPane.YES_OPTION) {
								BolsaLaboral.getInstance().eliminarEmpresa(cod);
								loadTable();
							}
						}
						BolsaLaboral.getInstance().eliminarEmpresa(cod);
					}
				});
				btnEliminar.setEnabled(false);
				buttonPane.add(btnEliminar);
			}
			{
				BtnUpdate = new JButton("Modificar");
				BtnUpdate.setIcon(new ImageIcon(ListarEmpresa.class.getResource("/img/modificar.png")));
				BtnUpdate.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if (cod != "") {
							miem = BolsaLaboral.getInstance().RetornarEmpresa(cod);

						}
						try {
							InsertarEmpresa modificaremore = new InsertarEmpresa("Modificar Empresa", true, miem, null);
							modificaremore.setModal(true);
							modificaremore.setLocationRelativeTo(null);
							modificaremore.setVisible(true);
							loadTable();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				});
				BtnUpdate.setEnabled(false);
				buttonPane.add(BtnUpdate);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setIcon(new ImageIcon(ListarEmpresa.class.getResource("/img/cancelar.png")));
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
		fila = new Object[model.getColumnCount()];
		for (Empresa miemp : bolsa.getMisEmpresas()) {
			fila[0] = miemp.getRNC();
			fila[1] = miemp.getNombre();
			fila[2] = miemp.getArea();
			fila[3] = miemp.getTelefono();
			fila[4] = miemp.getEmail();
			model.addRow(fila);

		}
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(90);
		columnModel.getColumn(1).setPreferredWidth(145);
		columnModel.getColumn(2).setPreferredWidth(115);
		columnModel.getColumn(3).setPreferredWidth(100);
		columnModel.getColumn(4).setPreferredWidth(180);

	}

}
