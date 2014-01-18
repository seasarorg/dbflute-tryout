package javatry.java.sql;

import java.sql.Date;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 * @since 1.0 (2009/03/25 Wednesday)
 */
public class SqlDateTest extends UnitTryTestCase {

    public void test_toString() {
        Date time = new Date(new java.util.Date().getTime());
        log(time.toString());
    }
}
