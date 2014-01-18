package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class CharacterTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                               Digit
    //                                                                               =====
    public void test_isDigit() throws Exception {
        // ## Arrange ##
        assertFalse(Character.isDigit('a'));
        assertFalse(Character.isDigit('b'));
        assertFalse(Character.isDigit('A'));
        assertFalse(Character.isDigit('B'));
        assertTrue(Character.isDigit('1'));
        assertTrue(Character.isDigit('2'));
    }

    // ===================================================================================
    //                                                                           UpperCase
    //                                                                           =========
    public void test_isUpperCase() throws Exception {
        // ## Arrange ##
        assertFalse(Character.isUpperCase('a'));
        assertFalse(Character.isUpperCase('b'));
        assertTrue(Character.isUpperCase('A'));
        assertTrue(Character.isUpperCase('B'));
        assertFalse(Character.isUpperCase('1'));
        assertFalse(Character.isUpperCase('2'));
    }
}
