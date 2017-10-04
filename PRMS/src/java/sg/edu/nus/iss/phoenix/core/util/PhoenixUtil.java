package sg.edu.nus.iss.phoenix.core.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PhoenixUtil {
	
	public static final DateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
	private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
	private static final DateFormat dateTimeAMPMFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

}
