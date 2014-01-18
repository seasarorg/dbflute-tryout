package javatry.java.sql;

import java.sql.Time;
import java.util.Date;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 * @since 1.0 (2009/03/25 Wednesday)
 */
public class TimeTest extends UnitTryTestCase {

    public void test_toString() {
        Time time = new Time(new Date().getTime());
        log(time.toString());
        assertFalse(time.toString().contains(".")); // should not contain millis
    }
}
