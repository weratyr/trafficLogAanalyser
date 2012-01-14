package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import models.LogModel;
import models.container.UserTraffic;
import view.helperClasses.MyMenuBar;

public class AppLayoutViewImpl extends JFrame implements AppLayoutView {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	JScrollPane jsp;
	JTable resultTable;
	DefaultTableModel tableModel;
	MyMenuBar menu;
	JPanel container;
	LogModel model;
	Controller controller;

	public AppLayoutViewImpl(LogModel model) {

		menu = new MyMenuBar();
		this.setMenu(menu.getMenu());
		this.model = model;
		container = new JPanel();
		container.setVisible(true);

		tableModel = new DefaultTableModel();
		tableModel.addColumn("IP");
		tableModel.addColumn("");
		tableModel.addColumn("Output Bytes in MB");
		tableModel.addColumn("In Bytes in MB");

		resultTable = new JTable(tableModel);
		jsp = new JScrollPane(resultTable);
		jsp.setVisible(true);
		jsp.setSize(700, 200);

		getContentPane().add(container, BorderLayout.CENTER);

		container.add(jsp);

		setTitle("Log Monitor");
		pack();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2, (dim.height - getY()) / 2);

		// ... finalize layout
		this.setTitle("Log Analyser for IPTables");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void updateUI() {
		setLogResultToTable();
	}

	public void setMenuOeffenenListener(ActionListener listener) {
		menu.setMenuOeffenen(listener);
	}

	public void setTableSelectionListener(ListSelectionListener listener) {
		resultTable.getSelectionModel().addListSelectionListener(listener);
	}

	public JPanel getViewContainer() {
		return this.container;
	}

	@Override
	public void resetTable() {
		for (int i = tableModel.getRowCount() - 1; i >= 0; i--) {
			tableModel.removeRow(i);
		}
	}

	public void setLogResultToTable() {
		for (Entry<String, ArrayList<UserTraffic>> entrys : model.getLogList().entrySet()) {
			tableModel.addRow(new Object[] { entrys.getKey(), "",
					(model.calculateTrafficForIP(entrys.getKey(), "OUT") / 1000000),
					(model.calculateTrafficForIP(entrys.getKey(), "IN") / 1000000) });
		}
	}

	public void setMenu(JMenuBar menu) {
		this.setJMenuBar(menu);
	}

	@Override
	public JComponent asContainer() {
		return container;
	}

	@Override
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public JTable getViewTable() {
		return resultTable;
	}

}
