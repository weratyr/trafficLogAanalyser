package view;

import javax.swing.JPanel;

import view.helperClasses.StackedBarChart;


public interface UserView {
	public interface Controller {

	}
	JPanel asPanel();
	StackedBarChart getChart();
}
