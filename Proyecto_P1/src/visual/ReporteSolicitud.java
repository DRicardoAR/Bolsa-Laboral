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
import logica.SolicitudObrero;
import logica.SolicitudTecnico;
import javax.swing.ImageIcon;

public class ReporteSolicitud extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private static Object[] fila;
	private DefaultTableModel model;
	private BolsaLaboral bolsa = BolsaLaboral.getInstance();

	public ReporteSolicitud() {
		setResizable(false);
		setModal(true);
		getContentPane().setBackground(new Color(248, 248, 255));
		setBackground(new Color(248, 248, 255));
		setTitle("Reporte De Solicitudes");
		setBounds(100, 100, 719, 472);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(248, 248, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(248, 248, 255));
			panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Reporte Solicitudes",
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
			scrollPane.setBounds(10, 25, 684, 341);
			panel.add(scrollPane);
			{
				table = new JTable();
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				String[] colimneNames = { "Nombre Empresa", "Tipo Solicitud", "Cantidad Vacantes",
						"Porcentaje Completado" };
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
			buttonPane.setBackground(new Color(248, 248, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Aceptar");
				cancelButton.setIcon(new ImageIcon(ReporteSolicitud.class.getResource("/img/aceptar.png")));
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
		float cant1 = 0;
		float cant2 = 0;
		float porciento = 0;
		
		model.setRowCount(0);
		fila = new Object[model.getColumnCount()];
		for (Solicitud soli : bolsa.getMisSolicitudes()) {
			
			fila[0] = soli.getEmpresa().getNombre();
			fila[1] = tipoSolicitud(soli);
			fila[2] = soli.getCantVacantes();
			cant1 = soli.getCantVacantes();
			cant2 = soli.getCantReal();
			porciento = (cant2 / cant1 )*100;
			
			fila[3] = porciento+" %";
			model.addRow(fila);

		}
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getTableHeader().setReorderingAllowed(false);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(180);
		columnModel.getColumn(1).setPreferredWidth(167);
		columnModel.getColumn(2).setPreferredWidth(167);
		columnModel.getColumn(3).setPreferredWidth(167);

	}

	public String tipoSolicitud(Solicitud soli) {
		String tipoSoli = null;
		if (soli instanceof SolicitudObrero) {
			tipoSoli = "Solicitud Obrero";
		}
		if (soli instanceof SolicitudTecnico) {
			tipoSoli = "Solicitud Tecnico";
		} else {
			tipoSoli = "Solicitud Universitario";
		}
		return tipoSoli;
	}

}
