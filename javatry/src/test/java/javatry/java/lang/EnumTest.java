package javatry.java.lang;

import javatry.unit.UnitTryTestCase;
import javatry.unit.CDef;
import javatry.unit.Foonum;

import org.seasar.dbflute.util.DfReflectionUtil;

/**
 * @author jflute
 */
public class EnumTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                       ENUM Handling
    //                                                                       =============
    public void test_equals() throws Exception {
        // ## Arrange ##
        final Foonum fooNum = Foonum.FOO;

        // ## Act & Assert ##
        assertTrue(fooNum == Foonum.FOO);
        assertTrue(fooNum.equals(Foonum.FOO));
        assertFalse(fooNum.equals("FOO"));
    }

    public void test_toString() throws Exception {
        // ## Arrange ##
        final Foonum num = Foonum.FOO;

        // ## Act ##
        String actual = num.toString();

        // ## Assert ##
        assertEquals("FOO", actual);
    }

    // ===================================================================================
    //                                                                          Reflection
    //                                                                          ==========
    public void test_Reflection_forName_basic() throws Exception {
        // ## Arrange ##
        String name = Foonum.class.getName();

        // ## Act ##
        Class<?> clazz = DfReflectionUtil.forName(name);

        // ## Assert ##
        assertEquals(name, clazz.getName());
    }

    public void test_Reflection_forName_innerEnum() throws Exception {
        // ## Arrange ##
        String name = CDef.Flg.class.getName();

        // ## Act ##
        Class<?> clazz = DfReflectionUtil.forName(name);

        // ## Assert ##
        assertEquals(name, clazz.getName());
    }
}
