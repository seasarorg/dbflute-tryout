package javatry.java.lang;

import java.math.BigDecimal;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ClassTest extends UnitTryTestCase {

    public void test_getSimpleName_noNameInnerClass() throws Exception {
        // ## Arrange ##
        Object target = new Object() {
        };

        // ## Act ##
        String simpleName = target.getClass().getSimpleName();

        // ## Assert ##
        log("simpleName = " + simpleName);
        assertEquals("", simpleName); // Oops!
    }

    public void test_compare_basic() throws Exception {
        assertTrue(Class.class.equals(BigDecimal.class.getClass()));
        assertFalse(Class.class.equals(new BigDecimal(3).getClass()));
    }
}
