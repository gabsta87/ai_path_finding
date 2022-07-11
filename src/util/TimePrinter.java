package util;

public class TimePrinter {
	public static String getTime(long timeInMS) {
		long timeInSeconds = timeInMS/1000;
		long hours = timeInSeconds/3600;
		long minutes = timeInSeconds%3600/60;
		long seconds = timeInSeconds%3600%60;
		String result = (hours>0?hours+"h":"")+(minutes>0?minutes+"m":"")+(seconds>0?seconds+"s":"");
		if(result.length() == 0)
			result = timeInMS+"ms";
		return result;
	}
}
