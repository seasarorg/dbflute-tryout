package javatry.java.util;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class CalendarTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                            TimeZone
    //                                                                            ========
    public void test_getInstance_TimeZone() throws Exception {
        // ## Arrange ##
        TimeZone gmtZone = TimeZone.getTimeZone("GMT");
        Calendar gmtCal = Calendar.getInstance(gmtZone);
        TimeZone japanZone = TimeZone.getTimeZone("Asia/Tokyo"); // GMT+09:00, Etc/GMT-9
        Calendar japanCal = Calendar.getInstance(japanZone);
        TimeZone mtZone = TimeZone.getTimeZone("Canada/Mountain"); // GMT-07:00, Etc/GMT+7
        Calendar mtCal = Calendar.getInstance(mtZone);
        TimeZone directZone = TimeZone.getTimeZone("Etc/GMT+7");
        Calendar directCal = Calendar.getInstance(directZone);
        log(toString(gmtCal, "yyyy/MM/dd HH:mm:ss"), toString(japanCal, "yyyy/MM/dd HH:mm:ss"),
                toString(mtCal, "yyyy/MM/dd HH:mm:ss"), toString(directCal, "yyyy/MM/dd HH:mm:ss"));

        // ## Act ##
        Date justDate = toDate("2013/07/31 09:00:00");
        gmtCal.setTime(justDate);
        japanCal.setTime(justDate);
        mtCal.setTime(justDate);
        directCal.setTime(justDate);

        // ## Assert ##
        String gmtStr = toString(gmtCal, "yyyy/MM/dd HH:mm:ss");
        String japanStr = toString(japanCal, "yyyy/MM/dd HH:mm:ss");
        String mtStr = toString(mtCal, "yyyy/MM/dd HH:mm:ss");
        String directStr = toString(directCal, "yyyy/MM/dd HH:mm:ss");
        log(gmtStr, japanStr, mtStr, directStr);
        assertEquals(gmtStr, japanStr);
        assertEquals(gmtStr, mtStr);
        assertEquals(gmtStr, directStr);

        int gmtDay = gmtCal.get(Calendar.DAY_OF_MONTH); // 31
        int gmtHour = gmtCal.get(Calendar.HOUR_OF_DAY); // 0
        int japanDay = japanCal.get(Calendar.DAY_OF_MONTH); // 31
        int japanHour = japanCal.get(Calendar.HOUR_OF_DAY); // 9
        int mtDay = mtCal.get(Calendar.DAY_OF_MONTH); // 30
        int mtHour = mtCal.get(Calendar.HOUR_OF_DAY); // 18 *daylight
        int directDay = directCal.get(Calendar.DAY_OF_MONTH); // 30
        int directHour = directCal.get(Calendar.HOUR_OF_DAY); // 17
        log(gmtDay + "-" + gmtHour, japanDay + "-" + japanHour, mtDay + "-" + mtHour, directDay + "-" + directHour);
        assertEquals(gmtDay, japanDay);
        assertEquals(gmtHour, japanHour - 9);
        assertEquals(gmtDay, mtDay + 1);
        assertEquals(gmtHour, mtHour - 18);
        assertEquals(gmtDay, directDay + 1);
        assertEquals(gmtHour, directHour - 17);
        assertFalse(gmtZone.useDaylightTime());
        assertFalse(gmtZone.inDaylightTime(gmtCal.getTime()));
        assertFalse(japanZone.useDaylightTime());
        assertFalse(japanZone.inDaylightTime(japanCal.getTime()));
        assertTrue(mtZone.useDaylightTime());
        assertTrue(mtZone.inDaylightTime(mtCal.getTime()));
        assertFalse(directZone.useDaylightTime());
        assertFalse(directZone.inDaylightTime(directCal.getTime()));
    }

    public void test_getInstance_TimeZone_daylight_border() throws Exception {
        // ## Arrange ##
        TimeZone mtZone = TimeZone.getTimeZone("Canada/Mountain"); // GMT-07:00, Etc/GMT+7

        // ## Act ##
        Calendar cal = Calendar.getInstance(mtZone);
        cal.setTime(toDate("2013/03/10 18:00:00")); // border

        // ## Assert ##
        log(mtZone.inDaylightTime(cal.getTime()), toString(cal, "yyyy/MM/dd HH:mm:ss"));
        log(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY));
        assertTrue(mtZone.inDaylightTime(cal.getTime()));
        assertEquals(10, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(3, cal.get(Calendar.HOUR_OF_DAY));

        cal.add(Calendar.SECOND, -1);
        log(mtZone.inDaylightTime(cal.getTime()), toString(cal, "yyyy/MM/dd HH:mm:ss"));
        log(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY));
        assertFalse(mtZone.inDaylightTime(cal.getTime()));
        assertEquals(10, cal.get(Calendar.DAY_OF_MONTH));
        assertEquals(1, cal.get(Calendar.HOUR_OF_DAY));

        cal.set(Calendar.HOUR_OF_DAY, 3);
        log(mtZone.inDaylightTime(cal.getTime()), toString(cal, "yyyy/MM/dd HH:mm:ss"));
        log(cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY));
    }

    // ===================================================================================
    //                                                                    addWeekOfMonth()
    //                                                                    ================
    public void test_addWeekOfMonth_basic() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/17")); // Thursday

        // ## Act ##
        cal.add(Calendar.WEEK_OF_MONTH, 1);

        // ## Assert ##
        String actual = toString(toDate(cal), "yyyy/MM/dd");
        log("actual=" + actual);
        assertEquals("2011/11/24", actual);
    }

    public void test_addWeekOfMonth_monthEnd() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/29")); // Tuesday

        // ## Act ##
        cal.add(Calendar.WEEK_OF_MONTH, 1);

        // ## Assert ##
        String actual = toString(toDate(cal), "yyyy/MM/dd");
        log("actual=" + actual);
        assertEquals("2011/12/06", actual);
    }

    public void test_addWeekOfYear() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/17")); // Thursday

        // ## Act ##
        cal.add(Calendar.WEEK_OF_YEAR, 1);

        // ## Assert ##
        String actual = toString(toDate(cal), "yyyy/MM/dd");
        log("actual=" + actual);
        assertEquals("2011/11/24", actual);
    }

    public void test_getDayOfWeek() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/17")); // Thursday

        // ## Act ##
        int actual = cal.get(Calendar.DAY_OF_WEEK);

        // ## Assert ##
        log("actual=" + actual);
        assertEquals(Calendar.THURSDAY, actual);
    }

    public void test_getWeekOfMonth() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/17")); // Thursday

        // ## Act ##
        int actual = cal.get(Calendar.WEEK_OF_MONTH);

        // ## Assert ##
        log("actual=" + actual);
        assertEquals(3, actual);
    }

    public void test_getWeekOfYear() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.setTime(toDate("2011/11/17")); // Thursday

        // ## Act ##
        int actual = cal.get(Calendar.WEEK_OF_YEAR);

        // ## Assert ##
        log("actual=" + actual);
        assertEquals(47, actual);
    }
}
