package test;

import static org.junit.Assert.assertTrue;
import models.LogModel;

import org.junit.Test;


public class LogModelTest {


	@Test
	public void testFilterDuplicateIP() {
		LogModel lModel = new LogModel();
		lModel.addEntryToLogList("10.10.1.169", "", "", "3", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "5", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "2", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "2", "IN");

		lModel.filterDuplicateIPs();

//		assertTrue(lModel.getLogList().get("10.10.1.169").size() == 4);

	}

	@Test
	public void testCalculateTrafficForIP() {

		LogModel lModel = new LogModel();
		lModel.addEntryToLogList("10.10.1.169", "", "", "3", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "5", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "2", "OUT");
		lModel.addEntryToLogList("10.10.1.169", "", "", "2", "IN");
		lModel.addEntryToLogList("10.10.1.169", "", "", "2", "IN");

		lModel.filterDuplicateIPs();

		assertTrue(lModel.calculateTrafficForIP("10.10.1.169","OUT")==10);
		assertTrue(lModel.calculateTrafficForIP("10.10.1.169","IN")==4);

	}

}
