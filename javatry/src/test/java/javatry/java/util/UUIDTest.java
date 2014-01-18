package javatry.java.util;

import java.util.UUID;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class UUIDTest extends UnitTryTestCase {

    public void test_UUID_fromString_illegal() {
        try {
            UUID.fromString("Illegal");

            fail();
        } catch (RuntimeException e) {
            // OK
            log(e.getMessage());
        }
    }
}
