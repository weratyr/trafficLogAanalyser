package controller;

import java.awt.Container;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import models.LogModel;
import view.UserView;

public class UserController implements Controller, UserView.Controller {

	UserView view;
	LogModel model;
	SimpleDateFormat dayFormatter;
	SimpleDateFormat formatter;
	SimpleDateFormat yearFormatter;
	SimpleDateFormat monthFormatter;
	SimpleDateFormat yearMonthFormatter;

	public UserController(UserView view, LogModel model) {
		this.view = view;
		this.model = model;

		dayFormatter = new SimpleDateFormat("dd");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		monthFormatter = new SimpleDateFormat("MM");
		yearFormatter = new SimpleDateFormat("yyyy");
		yearMonthFormatter = new SimpleDateFormat("yyyy-MM");
	}

	public void updateChart(String userIP) {
		view.getChart().resetDataset();

		int tmpDay = 0;
		int emptyDay = 0;
		Date itemDate = null;

		for (int index = 1; index < 32; index++) {
			for (int j = 0; j < model.get(userIP).size(); j++) {
				itemDate = model.get(userIP).get(j).getDate();
				int itemDay = Integer.parseInt(dayFormatter.format(itemDate));

				if (itemDay == index && tmpDay != itemDay) {
					view.getChart().addToDataset(
							itemDate,
							model.calculateTrafficForDay(itemDate, model.get(userIP), "IN").get(itemDate)
									.getTraffic(), "Download");
					view.getChart().addToDataset(
							itemDate,
							model.calculateTrafficForDay(itemDate, model.get(userIP), "OUT").get(itemDate)
									.getTraffic(), "Upload");
					tmpDay = itemDay;
				} else {
					emptyDay = index;
				}
			}
			// falls der Tag ohne Traffic gleich dem index ist
			// und der Tag mit Traffic ungleich dem index ist
			if (emptyDay == index && tmpDay != index) {
				Date emptyTrafficDate = null;
				try {
					emptyTrafficDate = formatter.parse(yearMonthFormatter.format(itemDate) + "-" + index);
					// setzte den Traffic fuer den Tag auf 0
					view.getChart().addToDataset(emptyTrafficDate, 0, "Download");
					view.getChart().addToDataset(emptyTrafficDate, 0, "Upload");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void go(Container container) {
		container.add(view.asPanel());
	}

}
