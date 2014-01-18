package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class LongTest extends UnitTryTestCase {

    public void test_limit() throws Exception {
        // ## Arrange ##
        String eighteenExp = "123456789012345678";
        String eighteenMax = "999999999999999999";
        String nineteenExp = "1234567890123456789";
        String nineteenMax = "9999999999999999999";

        // ## Act & Assert ##
        assertEquals(Long.valueOf(123456789012345678L), Long.valueOf(eighteenExp));
        assertEquals(Long.valueOf(999999999999999999L), Long.valueOf(eighteenMax));
        assertEquals(Long.valueOf(1234567890123456789L), Long.valueOf(nineteenExp));
        try {
            Long.valueOf(nineteenMax);
        } catch (RuntimeException e) {
            // OK
            log(e.getMessage());
        }
        assertEquals(19, String.valueOf(Long.MAX_VALUE).length());
        log("max : " + Long.MAX_VALUE);
    }
}
