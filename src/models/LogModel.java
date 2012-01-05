package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class LogModel extends TreeMap<String, ArrayList<String[]>> {
	ArrayList<String[]> arrayListUnsorted;
	HashMap<String, ArrayList<String>> userTrafficMap;

	public LogModel() {
		arrayListUnsorted = new ArrayList<String[]>();
	}

	public void addEntryToLogList(String ip, String date, String time, String bytes, String direction) {
		arrayListUnsorted.add(new String[] { ip, direction, date, time, bytes });
	}

	public void filterDuplicateIPs() {
		for (String[] entry : arrayListUnsorted) {
			if (entry.length > 3) {
				if (!containsKey(entry[0])) {
					ArrayList<String[]> arrayListSorted = new ArrayList<String[]>();
					arrayListSorted.add(new String[] { entry[1], entry[2], entry[3], entry[4] });
					put(entry[0], arrayListSorted);
				} else {
					get(entry[0]).add(new String[] { entry[1], entry[2], entry[3], entry[4] });
				}
			} else {
				System.out.println("Array not complete for filtering!!");
			}
		}
	}


	public TreeMap<String, ArrayList<String[]>> getLogList() {
		return this;
	}


	public long calculateTrafficForIP(String ip, String direction) {
		long bytes = 0;
		ArrayList<String[]> values = get(ip);
		for (int j = 0; j < values.size(); j++) {
			if (values.get(j)[0].equals(direction)) {
				bytes += Integer.parseInt(values.get(j)[3]);
			}
		}
		return bytes;
	}

}
