package javatry.java.util;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import javatry.unit.UnitTryTestCase;

import org.seasar.dbflute.helper.HandyDate;

/**
 * @author jflute
 */
public class TimeZoneTest extends UnitTryTestCase {

    public void test_showZone() {
        showZone(TimeZone.getTimeZone("GMT"));
        showZone(TimeZone.getTimeZone("Asia/Tokyo")); // GMT+09:00, Etc/GMT-9
        showZone(TimeZone.getTimeZone("Canada/Mountain")); // GMT-07:00, Etc/GMT+7
    }

    public void test_calculateTimeDifference_level1() {
        TimeZone japanZone = TimeZone.getTimeZone("Asia/Tokyo"); // GMT+09:00, Etc/GMT-9
        TimeZone gmtZone = TimeZone.getTimeZone("GMT");
        Date japanDate = new HandyDate("2013/07/31 11:00:00").getDate();
        int diff = gmtZone.getRawOffset() - japanZone.getRawOffset();
        Date resolved = new Date(japanDate.getTime() + diff);
        String actual = toString(resolved, "yyyy/MM/dd HH:mm:ss.SSS");
        log(actual);
        assertEquals("2013/07/31 02:00:00.000", actual);
    }

    public void test_calculateTimeDifference_level2() {
        TimeZone japanZone = TimeZone.getTimeZone("Asia/Tokyo"); // GMT+09:00, Etc/GMT-9
        TimeZone mtZone = TimeZone.getTimeZone("Canada/Mountain"); // GMT-07:00, Etc/GMT+7
        Date japanDate = new HandyDate("2013/07/31 11:00:00").getDate();
        int diff = mtZone.getRawOffset() - japanZone.getRawOffset();
        Date resolved = new Date(japanDate.getTime() + diff);
        String actual = toString(resolved, "yyyy/MM/dd HH:mm:ss.SSS");
        log(actual);
        assertEquals("2013/07/30 19:00:00.000", actual);
    }

    protected void showZone(TimeZone zone) {
        log(zone.getID(), zone.getDisplayName(), zone.getDisplayName(Locale.US), zone.getDSTSavings(),
                zone.getRawOffset());
    }
}
