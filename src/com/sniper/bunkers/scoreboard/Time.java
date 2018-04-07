package com.sniper.bunkers.scoreboard;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.time.DurationFormatUtils;

public class Time {
	public static DecimalFormat getDecimalFormat() {
		return new DecimalFormat("0.0");
	}

	public static class IntegerTime {
		public static int convertMillisecondsToSeconds(Long paramLong) {
			return (int) (paramLong / 1000L);
		}

		public static String setHMSFormat(Integer paramInteger) {
			int remainder = paramInteger * 1000;

			int seconds = remainder / 1000 % 60;
			int minutes = remainder / 60000 % 60;
			int hours = remainder / 3600000 % 24;

			return (hours > 0 ? String.format("%02d" + ":", new Object[] { Integer.valueOf(hours) }) : "")
					+ String.format("%02d:%02d", new Object[] { Integer.valueOf(minutes), Integer.valueOf(seconds) });
		}

		public static String setMSFormat(Integer paramInteger) {
			int minutes = (int) (paramInteger / 60.0D);
			int seconds = paramInteger % 60;

			return String.format("%d:%02d", new Object[] { Integer.valueOf(minutes), Integer.valueOf(seconds) });
		}
	}

	// LongTime Methods

	public static class LongTime {
		public static long convertSecondsToMilliseconds(Integer paramInteger) {
			return (long) (paramInteger * 1000L);
		}

		public static String setHMSFormat(Long paramLong) {
			if (paramLong < TimeUnit.MINUTES.toMillis(1L)) {
				return getDecimalFormat().format(paramLong / 1000.0D) + "s";
			}
			// HH'h' || mm'm' ss's'
			return DurationFormatUtils.formatDuration(paramLong,
					(paramLong >= TimeUnit.HOURS.toMillis(1L) ? "HH:" : "") + "mm:ss");
		}
	}
}
