package gov.usgs.cia.aquarius;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.temporal.Temporal;

import org.junit.Test;

import gov.usgs.cida.aquarius.DateConverter;

public class DateConverterTest {
	
	@Test
	public void testConvert() {
		Temporal test1 = DateConverter.parseDate("2014-12-05T00:00:00.123456-06:00");
		Temporal test2 = DateConverter.parseDate("2015-11-10T02:59:22.9912972Z");
		
		assertTrue(test1 instanceof OffsetDateTime);
		assertTrue(test2 instanceof OffsetDateTime);
		
		assertEquals(((OffsetDateTime)test1).getYear(), 2014);
		assertEquals(((OffsetDateTime)test2).getYear(), 2015);
		
		assertEquals("Full format preserved", "2014-12-05T00:00:00.123456-06:00", DateConverter.printDate(test1));
		assertEquals("Full format preserved", "2015-11-10T02:59:22.9912972Z", DateConverter.printDate(test2));
	}
	
	
	@Test
	public void testConvert_DailyValues() {
		Temporal test1 = DateConverter.parseDate("2014-12-05T24:00:00.0000000-06:00"); //example of a DV timestamp in Aquarius
		
		assertTrue(test1 instanceof LocalDate);
		
		assertEquals(((LocalDate)test1).getYear(), 2014);
		assertEquals(((LocalDate)test1).getMonthValue(), 12);
		assertEquals(((LocalDate)test1).getDayOfMonth(), 5);
		
		assertEquals("Daily values do not include a time", "2014-12-05", DateConverter.printDate(test1));
	}
}
