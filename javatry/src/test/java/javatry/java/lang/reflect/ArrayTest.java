package javatry.java.lang.reflect;

import java.lang.reflect.Array;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ArrayTest extends UnitTryTestCase {

    public void test_getLength() {
        assertEquals(2, Array.getLength(new String[] { "a", "b" }));
        assertEquals(3, Array.getLength(new String[] { "a", "b", "c" }));
        assertEquals(1, Array.getLength(new String[] { "a" }));
        assertEquals(0, Array.getLength(new String[] {}));
        try {
            Array.getLength(null);
            fail();
        } catch (NullPointerException e) {
            // OK
        }
    }
}
