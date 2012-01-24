package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

import models.container.UserTraffic;

public class LogModel extends TreeMap<String, ArrayList<UserTraffic>> {
	ArrayList<String[]> arrayListUnsorted;
	SimpleDateFormat formatter;

	public LogModel() {
		arrayListUnsorted = new ArrayList<String[]>();
	}

	public void addEntryToLogList(String ip, String date, String time, String bytes, String direction) {
		arrayListUnsorted.add(new String[] { ip, direction, date, time, bytes });
	}

	public void resetDatabase() {
		clear();
		arrayListUnsorted.clear();
	}

	public void filterDuplicateIPs() {

		for (String[] entry : arrayListUnsorted) {
			if (entry.length > 3) {
				try {
					formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					if (!containsKey(entry[0])) {
						ArrayList<UserTraffic> arrayListSorted = new ArrayList<UserTraffic>();
						arrayListSorted.add(new UserTraffic(formatter.parse(entry[2] + " " + entry[3]), Integer
								.parseInt(entry[4]), entry[1]));
						put(entry[0], arrayListSorted);
					} else {
						get(entry[0]).add(
								new UserTraffic(formatter.parse(entry[2] + " " + entry[3]), Long.parseLong(entry[4]),
										entry[1]));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Array not complete for filtering!!");
			}
		}
	}

	public TreeMap<String, ArrayList<UserTraffic>> getLogList() {
		return this;
	}

	public long calculateTrafficForIP(String ip, String direction) {
		long bytes = 0;
		ArrayList<UserTraffic> values = get(ip);
		for (int j = 0; j < values.size(); j++) {
			if (values.get(j).getDirection().equals(direction)) {
				bytes += values.get(j).getTraffic();
			}
		}
		return bytes;
	}


	public HashMap<Date, UserTraffic> calculateTrafficForDay(Date date, ArrayList<UserTraffic> trafficList, String direction) {
		long bytes = 0;
		SimpleDateFormat withoutTimeformatter = new SimpleDateFormat("yyyy-MM-dd");
		HashMap<Date, UserTraffic> daylyTrafficList = new HashMap<Date, UserTraffic>();
		for(int i=0; i <  trafficList.size(); i++ ) {
			Date userDate = trafficList.get(i).getDate();
			if(trafficList.get(i).getDirection().equals(direction) && withoutTimeformatter.format(date).equals(withoutTimeformatter.format(userDate))) {
				 bytes+= trafficList.get(i).getTraffic();
			}
		}
		daylyTrafficList.put(date, new UserTraffic(date, bytes, direction));
		return daylyTrafficList;
	}

}
