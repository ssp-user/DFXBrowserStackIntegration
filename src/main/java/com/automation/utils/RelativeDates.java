package com.automation.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
     * Contains a set of static methods to get different representations of a date
     * that is relative to a base date. Special syntax is being used to specify offset.
     * This syntax is somewhat similar to a Jira/Bugzilla relative date format.<br>
     * <p>Offset is a string:
     * <p>{@codeoffset><datepart><inner offset>[ <hh>:<mm>]}
     * <p>where:
     *      <li>offset - number of dateparts to offset
     *      <li>detepart - d,w,m,y or a short name of a week day (Sun, Mon, Tue, etc.)
     *      <li>inner offset - number of days within current datepart
     *      <li>hh:mm - specific time to be attached to a date</ul>
     *
     * <p>Examples:
     *      <li>+2d - today + 2
     *      <li>-1y - today a year back
     *      <li>-1Mon - last monday
     *      <li>+1m15 - the 15 of next month
     *      <li>-1mL - last of last month
     *      <li>+1d 17:00 - today + 1 at 17:00<\li></ul>
     *
     * <p>Created on 2/15/2018.
     * @autor efenner
     * @author David
     * @since 6.1.5
     */
    public class RelativeDates {

        private static Map<String,Integer> daysOfWeek = new HashMap<>();
        static{
            daysOfWeek.put("Sun", Calendar.SUNDAY);
            daysOfWeek.put("Mon",Calendar.MONDAY);
            daysOfWeek.put("Tue",Calendar.TUESDAY);
            daysOfWeek.put("Wed",Calendar.WEDNESDAY);
            daysOfWeek.put("Thu",Calendar.THURSDAY);
            daysOfWeek.put("Fri",Calendar.FRIDAY);
            daysOfWeek.put("Sat",Calendar.SATURDAY);
        }
        private static Map<String,RelDate> dateClassMap = new HashMap<>();
        static{
            dateClassMap.put("d",new RelDateDay());
            dateClassMap.put("w",new RelDateWeek());
            dateClassMap.put("m",new RelDateMonth());
            dateClassMap.put("y",new RelDateYear());
        }
        private static Date baseDate = DateHelper.truncateToDays(new Date());

        /**
         * Converts a string representation of a date to a java.utils.Date. By default this returns a clean date. A time parameter can also be added to set a specific time.
         * @param strDate string representation of a date
         *                <p>possible formats:
         *                <li>20151231
         *                <li>2015-12-31
         *                <li>relative date offset
         * @return parsed date
         * @throws ParseException
         */
        public static Date parseDate(String strDate) throws ParseException {
            if (strDate.matches("^\\d{8}$")) {
                return new SimpleDateFormat("yyyyMMdd").parse(strDate);
            } else if (strDate.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
                return new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            } else if (strDate.equalsIgnoreCase("today")) {
                return baseDate;
            } else if (strDate.matches("^[+-]\\d+([dwmy]|Sun|Mon|Tue|Wed|Thu|Fri|Sat)(\\d{0,2}|[L]?)?\\s?(\\d{1,2}:\\d{2})?$")) {
                return parseDateFrom(strDate,baseDate);
            }else{
                throw new IllegalArgumentException("The string "+strDate+" could not be parsed to a date.");
            }
        }

        /**
         * Returns a java.util.Date object based on a base date and a relative date string.
         * @param strDate relative date offset
         * @param date base date
         * @return parse date
         */

        public static Date parseDateFrom(String strDate,Date date){
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            Pattern pat = Pattern.compile("^([+-]\\d+)([dwmy]|Sun|Mon|Tue|Wed|Thu|Fri|Sat)(\\d{0,2}|[L]?)?\\s?(\\d{1,2}:\\d{2})?$");
            Matcher match = pat.matcher(strDate);
            if(match.find()) {
                String offset = match.group(1);
                if (match.group(2).matches("Sun|Mon|Tue|Wed|Thu|Fri|Sat")) {
                    RelDate rd = new RelDateWeek();
                    return rd.getRelativeDate(cal, offset, String.valueOf(daysOfWeek.get(match.group(2))), match.group(4));
                } else {
                    RelDate rd = dateClassMap.get(match.group(2));
                    return rd.getRelativeDate(cal, offset, match.group(3),match.group(4));
                }
            }
            throw new IllegalArgumentException("The string "+strDate+" could not be parsed to a date.");
        }

        private static abstract class RelDate{

            protected abstract Date getRelativeDate(Calendar cal, String initOffset, String secOffset, String timeOffset);
            protected void setTime(Calendar cal, String time) {
                cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time.split(":")[0]));
                cal.set(Calendar.MINUTE, Integer.parseInt(time.split(":")[1]));
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND, 0);
            }
        }

        private static class RelDateDay extends RelDate{

            @Override
            public Date getRelativeDate(Calendar cal, String initOffset, String secOffset, String timeOffset) {
                cal.add(Calendar.DATE, Integer.parseInt(initOffset));
                if(timeOffset != null){
                    setTime(cal, timeOffset);
                }
                return cal.getTime();
            }
        }
        private static class RelDateWeek extends RelDate{

            @Override
            public Date getRelativeDate(Calendar cal, String initOffset, String secOffset, String timeOffset) {
                cal.add(Calendar.WEEK_OF_YEAR, (Integer.parseInt(initOffset)));
                if(timeOffset != null)
                    setTime(cal, timeOffset);
                if(secOffset.matches("[1-7]"))
                    cal.set(Calendar.DAY_OF_WEEK, Integer.parseInt(secOffset));

                return cal.getTime();
            }
        }
        private static class RelDateMonth extends RelDate{

            @Override
            public Date getRelativeDate(Calendar cal, String initOffset, String secOffset, String timeOffset) {
                cal.add(Calendar.MONTH,(Integer.parseInt(initOffset)));
                if(secOffset.equalsIgnoreCase("L"))
                    cal.setTime(DateHelper.getMonthEndDate(cal.getTime()));
                if(secOffset.matches("\\d+"))
                    cal.setTime(DateHelper.getUnitMonth(secOffset, false, cal.getTime()));
                if(timeOffset != null)
                    setTime(cal, timeOffset);

                return cal.getTime();
            }
        }
        private static class RelDateYear extends RelDate{

            @Override
            public Date getRelativeDate(Calendar cal, String initOffset, String secOffset, String timeOffset) {
                cal.add(Calendar.YEAR,(Integer.parseInt(initOffset)));
                if(secOffset.equalsIgnoreCase("L"))
                    cal.setTime(DateHelper.getLastDayOfYear(cal.getTime()));
                if(secOffset.matches("\\d+"))
                    cal.setTime(DateHelper.getUnitYear(secOffset, false, cal.getTime()));
                if(timeOffset != null)
                    setTime(cal, timeOffset);

                return cal.getTime();
            }
        }
    }

