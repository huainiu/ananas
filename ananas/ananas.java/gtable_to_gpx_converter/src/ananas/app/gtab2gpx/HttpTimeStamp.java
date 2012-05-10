package ananas.app.gtab2gpx;

import java.util.Calendar;
import java.util.Hashtable;
import java.util.Vector;

public class HttpTimeStamp {

	private static Hashtable<String, Integer> sMonthTable;
	private int year;
	private int hour;
	private int minute;
	private int second;
	private int day;
	private int month;

	public static HttpTimeStamp parse(String str) {
		Vector<String> list = _spliteString(str);
		String strYY = list.get(3);
		String strMM = list.get(2);
		String strDD = list.get(1);
		String strhh = list.get(5);
		String strmm = list.get(7);
		String strss = list.get(9);
		HttpTimeStamp hts = new HttpTimeStamp();
		hts.year = Integer.parseInt(strYY);
		hts.month = _parseMonth(strMM);
		hts.day = Integer.parseInt(strDD);
		hts.hour = Integer.parseInt(strhh);
		hts.minute = Integer.parseInt(strmm);
		hts.second = Integer.parseInt(strss);
		return hts;
	}

	private static int _parseMonth(String str) {
		Hashtable<String, Integer> tab = sMonthTable;
		if (tab == null) {
			tab = new Hashtable<String, Integer>();
			String[] list = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul",
					"Aug", "Sep", "Oct", "Nov", "Dec" };
			for (int i = list.length - 1; i >= 0; i--) {
				tab.put(list[i].toLowerCase(), i);
			}
			sMonthTable = tab;
		}
		str = str.trim().toLowerCase();
		Integer rlt = tab.get(str);
		if (rlt == null) {
			return 0;
		} else {
			return rlt.intValue();
		}
	}

	private static Vector<String> _spliteString(String str) {
		StringBuffer sbuf = new StringBuffer();
		Vector<String> v = new Vector<String>();
		char[] chs = str.trim().toCharArray();
		boolean isCurInNum = false;
		for (char ch : chs) {
			final boolean isInNum = ('0' <= ch && ch <= '9');
			if (isCurInNum != isInNum) {
				isCurInNum = isInNum;
				v.addElement(sbuf.toString());
				sbuf.setLength(0);
			}
			sbuf.append(ch);
		}
		return v;
	}

	public long toLong() {
		Calendar cale = Calendar.getInstance();
		cale.set(year, month, day, hour, minute, second);
		return cale.getTimeInMillis();
	}

}
