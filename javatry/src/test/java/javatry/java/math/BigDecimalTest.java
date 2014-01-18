package javatry.java.math;

import java.math.BigDecimal;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class BigDecimalTest extends UnitTryTestCase {

    public void test_constructor() {
        assertEquals(1L, new BigDecimal("1").longValue());
        assertEquals(1L, new BigDecimal("1").longValueExact());
        assertEquals(1L, new BigDecimal("1.1").longValue());
        try {
            new BigDecimal("1.1").longValueExact();

            fail();
        } catch (ArithmeticException e) {
            // OK
        }
    }

    public void test_containsLongLimit() throws Exception {
        // ## Arrange ##
        String eighteenExp = "123456789012345678";
        String eighteenMax = "999999999999999999";
        String nineteenExp = "1234567890123456789";
        String nineteenMax = "9999999999999999999";

        // ## Act & Assert ##
        assertEquals(new BigDecimal(123456789012345678L), new BigDecimal(eighteenExp));
        assertEquals(new BigDecimal(999999999999999999L), new BigDecimal(eighteenMax));
        assertEquals(new BigDecimal(1234567890123456789L), new BigDecimal(nineteenExp));
        assertEquals(new BigDecimal("9999999999999999999"), new BigDecimal(nineteenMax));
    }
}