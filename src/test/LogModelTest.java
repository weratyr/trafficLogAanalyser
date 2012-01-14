package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import models.LogModel;
import models.container.UserTraffic;

import org.junit.Test;


public class LogModelTest {


	@Test
	public void testFilterDuplicateIP() {
		LogModel lModel = new LogModel();
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "3", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "5", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "2", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45",  "2", "IN");

		lModel.filterDuplicateIPs();

//		assertTrue(lModel.getLogList().get("10.10.1.169").size() == 4);

	}

	@Test
	public void testCalculateTrafficForIP() {

		LogModel lModel = new LogModel();
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "3", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-25", "12:45","5", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-25", "12:45", "2", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "2", "IN");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "2", "IN");

		lModel.filterDuplicateIPs();

		assertTrue(lModel.calculateTrafficForIP("10.10.1.169","OUT")==10);
		assertTrue(lModel.calculateTrafficForIP("10.10.1.169","IN")==4);

	}

	@Test
	public void testCalculateTrafficForDay() {
		LogModel lModel = new LogModel();
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "12:45", "3", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-25", "13:45","5", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-25", "12:45", "2", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "14:45", "2", "IN");
		lModel.addEntryToLogList("10.10.1.169", "2011-12-24", "15:45", "2", "IN");

		lModel.filterDuplicateIPs();
		Date date = lModel.get("10.10.1.169").get(0).getDate();
		ArrayList<UserTraffic> trafficList = lModel.get("10.10.1.169");

		HashMap<Date, UserTraffic>  t_in =  lModel.calculateTrafficForDay(date, trafficList, "IN");
		HashMap<Date, UserTraffic>  t_out =  lModel.calculateTrafficForDay(date, trafficList, "OUT");

		assertTrue(t_in.get(date).getTraffic()==4);
		assertTrue(t_out.get(date).getTraffic()==3);

	}

}
