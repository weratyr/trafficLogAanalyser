package view;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public interface AppLayoutView {
	public interface Controller {
		void setSelectedTabelRow(int row);
		void setLogResultToTable();
	}
	void setController(Controller controller);
	JComponent asContainer();
  JTable getViewTable();
  DefaultTableModel getViewTableModel();
  void resetTableView();
}
