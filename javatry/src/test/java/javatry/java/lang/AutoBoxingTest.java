package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class AutoBoxingTest extends UnitTryTestCase {

    public void test_asObject_canBeTreatedAsWrapper() {
        Object obj = 1;
        assertTrue(obj instanceof Integer);
    }

    public void test_Wrapper_NullPointer() throws Exception {
        try {
            int nullInt = getNullInteger(); // null pointer
            fail("nullInt=" + nullInt);
        } catch (NullPointerException e) {
            log(e);
        }
        try {
            Integer nullInteger = getNullInteger();
            Integer plusOne = nullInteger + 1; // null pointer
            fail("plusOne=" + plusOne);
        } catch (NullPointerException e) {
            log(e);
        }
    }

    protected Integer getNullInteger() {
        return null;
    }
}
