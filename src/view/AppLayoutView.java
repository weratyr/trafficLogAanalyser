package view;

import javax.swing.JComponent;
import javax.swing.JTable;

public interface AppLayoutView {
	public interface Controller {
		void setSelectedTabelRow(int row);

	}
	void setController(Controller controller);
	JComponent asContainer();
  JTable getViewTable();
  void resetTable();
}
