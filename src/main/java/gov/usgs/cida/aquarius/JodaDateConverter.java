package gov.usgs.cida.aquarius;

import org.joda.time.DateTime;
import org.joda.time.format.ISODateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class JodaDateConverter {
	private static final Logger log = LoggerFactory.getLogger(JodaDateConverter.class);

	public static String printDate(DateTime value) {
		String result = null;
		if (null != value) {
			result = value.toString(ISODateTimeFormat.dateTime());
		}
		return result;
	}

	public static DateTime parseDate(String value) {
		DateTime result = null;
		if (null != value) {
			try {
			String newValue = value.replaceFirst("T24:00", "T00:00");
			result = ISODateTimeFormat.dateTime().parseDateTime(newValue);
			if(!newValue.equals(value)){
				result = result.plusDays(1);
			}
			} catch (Exception e) {
				log.warn("Problem parsing date string " + value + ", null is being returned.", e);
			}
		}
		return result;
	}
}
