package com.autotest.utils;

import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;


import com.automation.utils.RelativeDates;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

    /**
     * Created by efenner on 8/12/2015.
     */

    public class RelativeDatesTest {

        private Date baseDate;
        private String dateForTest = "01012015";
        DateFormat dateFormat = new SimpleDateFormat("MMddyyyy");
        DateFormat dateTimeFormat = new SimpleDateFormat("MMddyyyy HH:mm:ss");
        Calendar cal;

        @Before
        public void setUp() throws Exception {
            baseDate = dateFormat.parse(dateForTest);
            cal = Calendar.getInstance();
            cal.setTime(baseDate);
        }

        @Test
        public void testDateParseDay() throws ParseException {
            /* We cannot compare 'today' with new Date() since today captures the current date when RelativeDates is first loaded
             * In order to test 'today' we're going to compare it with +0d */
            assertEquals(dateTimeFormat.format(RelativeDates.parseDate("+0d")), dateTimeFormat.format(RelativeDates.parseDate("today")));
            //Add one day
            assertEquals("01022015 00:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d", baseDate)));
            //Subtract one day
            assertEquals("12312014 00:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("-1d", baseDate)));
            //Add one day at 17:00
            assertEquals("01022015 17:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 17:00", baseDate)));
        }

        @Test
        public void testDateParseWeek() throws ParseException {
            //Next week
            assertEquals("01082015 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1w",baseDate)));
            //Last week
            assertEquals("12252014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1w", baseDate)));
            //Monday of next week
            assertEquals("01052015 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1Mon", baseDate)));
            //Tuesday of last week
            assertEquals("12232014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1Tue",baseDate)));
            //Next week at 17:00
            assertEquals("01082015 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1w 17:00",baseDate)));
            //Tuesday of last week at 17:00
            assertEquals("12232014 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1Tue 17:00",baseDate)));
        }

        @Test
        public void testDateParseMonth() throws ParseException {
            //Next month
            assertEquals("02012015 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1m",baseDate)));
            //Last Month
            assertEquals("12012014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1m", baseDate)));
            //End of next month
            assertEquals("02282015 00:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1mL", baseDate)));
            //15th of last month
            assertEquals("12152014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1m15",baseDate)));
            //Next month at 17:00
            assertEquals("02012015 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1m 17:00",baseDate)));
            //15th of last month at 17:00
            assertEquals("12152014 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1m15 17:00",baseDate)));
        }

        @Test
        public void testDateParseYear() throws ParseException {
            //Next year
            assertEquals("01012016 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1y", baseDate)));
            //Last year
            assertEquals("01012014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1y", baseDate)));
            //End of next year
            assertEquals("12312016 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1yL", baseDate)));
            //15th of last year
            assertEquals("01152014 00:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1y15", baseDate)));
            //Next year at 17:00
            assertEquals("01012016 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1y 17:00", baseDate)));
            //15th of last year at 17:00
            assertEquals("01152014 17:00:00",dateTimeFormat.format(RelativeDates.parseDateFrom("-1y15 17:00", baseDate)));
        }

        @Test(expected = IllegalArgumentException.class)
        public void testDateParseException(){
            //Invalid parse string
            RelativeDates.parseDateFrom("+1dE",baseDate);
        }

        @Test
        public void preservingTime() throws Exception {
            assertEquals("01022016 23:59:59",dateTimeFormat.format(RelativeDates.parseDateFrom("+1d", dateTimeFormat.parse("01012016 23:59:59"))));
            assertEquals("01022016 00:01:00",dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 00:01", dateTimeFormat.parse("01012016 23:59:59"))));
        }

        @Test
        public void testSameWeekWeekdays() throws Exception {
            assertEquals("01072015",dateFormat.format(RelativeDates.parseDateFrom("+0Wed", dateFormat.parse("01062015"))));
            assertEquals("01072015",dateFormat.format(RelativeDates.parseDateFrom("+0Wed", dateFormat.parse("01082015"))));

        }

        @Test
        public void testSimpleDate() throws Exception{
            assertEquals("01022015 01:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 1:00", baseDate)));
            assertEquals("01022015 00:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 0:00", baseDate)));
            assertEquals("01022015 09:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 9:00", baseDate)));
            assertEquals("01022015 19:00:00", dateTimeFormat.format(RelativeDates.parseDateFrom("+1d 19:00", baseDate)));

        }
    }

