package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class StringBuilderTest extends UnitTryTestCase {

    public void test_delete_outOfBounds() {
        // ## Arrange ##
        StringBuilder sb = new StringBuilder();

        // ## Act & Assert ##
        sb.delete(0, ", ".length()); // expect no exception
    }
}
