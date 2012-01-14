package view;

import javax.swing.JPanel;

import view.helperClasses.StackedBarChart;

public class UserViewImpl extends JPanel implements UserView {

	StackedBarChart chart;

	public UserViewImpl() {
		chart = new StackedBarChart();
		//HistogramChart chart1 = new HistogramChart("Historgram");
		//add(new JLabel("Stuff für genaure Einstellungen"));
		add(chart.getPanel());
		//add(chart1.getPanel());
	}

	@Override
	public JPanel asPanel() {
		return this;
	}

	@Override
	public StackedBarChart getChart() {
		return chart;
	}

}
