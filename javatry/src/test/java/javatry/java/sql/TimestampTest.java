package javatry.java.sql;

import java.sql.Timestamp;
import java.util.Calendar;

import javatry.unit.UnitTryTestCase;

import org.seasar.dbflute.util.DfTypeUtil;

/**
 * @author jflute
 */
public class TimestampTest extends UnitTryTestCase {

    public void test_getNanos() {
        // ## Arrange ##
        Timestamp timestamp = DfTypeUtil.toTimestamp("2009/01/12 12:34:56.147");

        // ## Act ##
        int nanos = timestamp.getNanos();

        // ## Assert ##
        log("nanos=" + nanos);
        int millisecond = DfTypeUtil.toCalendar(timestamp).get(Calendar.MILLISECOND);
        assertEquals(String.valueOf(millisecond), String.valueOf(nanos).substring(0, 3));
    }

    public void test_setNanos() {
        // ## Arrange ##
        Timestamp timestamp = DfTypeUtil.toTimestamp("2009/01/12 12:34:56.147");
        log(timestamp + ", millis=" + timestamp.getTime());

        // ## Act ##
        timestamp.setNanos(888);

        // ## Assert ##
        log(timestamp + ", millis=" + timestamp.getTime());
        assertEquals("2009-01-12 12:34:56.000000888", timestamp.toString());
    }

    public void test_getTime_nanos() {
        // ## Arrange ##
        Timestamp firstStamp = Timestamp.valueOf("2009-01-12 12:34:56.123456781");
        Timestamp secondStamp = Timestamp.valueOf("2009-01-12 12:34:56.123456782");

        // ## Act ##
        long firstTime = firstStamp.getTime();
        long secondTime = secondStamp.getTime();

        // ## Assert ##
        log(firstStamp + ", millis=" + firstTime);
        log(secondStamp + ", millis=" + secondTime);
        assertEquals(firstTime, secondTime);
    }

    public void test_valueOf_nanos() {
        log(Timestamp.valueOf("2009-01-12 12:34:56.123456789"));
        log(Timestamp.valueOf("2009-01-12 12:34:56.12345678"));
        log(Timestamp.valueOf("2009-01-12 12:34:56.123"));
    }
}
