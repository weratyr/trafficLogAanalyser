package view;

import javax.swing.JPanel;

import view.helperClasses.StackedBarChart;

public class UserViewImpl extends JPanel implements UserView {

	StackedBarChart chart;

	public UserViewImpl() {
		chart = new StackedBarChart();
		chart.setData(12, 2011);
		//add(new JLabel("Stuff für genaure Einstellungen"));
		add(chart.getPanel());
	}

	@Override
	public JPanel asPanel() {
		return this;
	}

}
