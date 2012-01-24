package view.helperClasses;

import java.awt.Dimension;
import java.util.Date;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StackedXYBarRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeTableXYDataset;
import org.jfree.data.xy.TableXYDataset;

public class StackedBarChart {

	ChartPanel chartpanel;
	TimeTableXYDataset timetablexydataset;

	public StackedBarChart() {
		timetablexydataset = new TimeTableXYDataset();

		JFreeChart jfreechart = createStackedBarChart(timetablexydataset);
		chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 370));
	}

	public ChartPanel getPanel() {
		return chartpanel;
	}

	public void addToDataset(Date date, long traffic, String serie) {
		timetablexydataset.add(new Day(date), (traffic / 1000000), serie);
		//System.out.println("add to time xy day:" + day + " Direc:" + serie + " traffic: " + (traffic/1000));
	}

	public void resetDataset() {
		timetablexydataset.clear();
	}


	private JFreeChart createStackedBarChart(TableXYDataset tablexydataset) {
		DateAxis dateaxis = new DateAxis("Date");
		dateaxis.setLabel("Traffic in MBytes");

		dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		NumberAxis numberaxis = new NumberAxis();
		numberaxis.setAutoRangeMinimumSize(0.1D);
		StackedXYBarRenderer stackedxybarrenderer = new StackedXYBarRenderer(0.1D);
		XYPlot xyplot = new XYPlot(tablexydataset, dateaxis, numberaxis, stackedxybarrenderer);

		JFreeChart jfreechart = new JFreeChart("Monatlicher Traffic", xyplot);
		return jfreechart;
	}

}
