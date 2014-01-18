package javatry.java.util;

import java.util.Locale;
import java.util.Map;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class LocaleTest extends UnitTryTestCase {

    public void test_asHashKey() {
        // ## Arrange ##
        Map<Locale, String> map = newHashMap();
        map.put(Locale.JAPAN, "jp");
        map.put(Locale.JAPANESE, "ja");

        // ## Act ##
        // ## Assert ##
        assertEquals("jp", map.get(Locale.JAPAN));
        assertEquals("ja", map.get(Locale.JAPANESE));
    }
}
