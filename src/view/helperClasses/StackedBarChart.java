package view.helperClasses;

import java.awt.Dimension;

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
	}

	public void setData(int month, int year) {

		for (int i = 1; i < 31; i++) {
			 addToDataset(month, year, i);
		}

		JFreeChart jfreechart = createChart(timetablexydataset);
		chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(500, 270));
	}

	public ChartPanel getPanel() {
		return chartpanel;
	}

	private void addToDataset(int month, int year, int i) {
		timetablexydataset.add(new Day(i, month, year), 1.0D, "Series 1");
		timetablexydataset.add(new Day(i, month, year), 1.7D, "Series 2");
	}

	private JFreeChart createChart(TableXYDataset tablexydataset) {
		DateAxis dateaxis = new DateAxis("Date");
		dateaxis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
		NumberAxis numberaxis = new NumberAxis("Y");
		StackedXYBarRenderer stackedxybarrenderer = new StackedXYBarRenderer(0.10000000000000001D);
		stackedxybarrenderer.setDrawBarOutline(false);
		XYPlot xyplot = new XYPlot(tablexydataset, dateaxis, numberaxis, stackedxybarrenderer);
		JFreeChart jfreechart = new JFreeChart("Monatlicher Traffic", xyplot);
		return jfreechart;
	}

}
