package javatry.java.util;

import java.util.Arrays;
import java.util.Comparator;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ArraysTest extends UnitTryTestCase {

    public void test_sort() {
        // ## Arrange ##
        String[] ary = new String[] { "quxfoo", "foo_abc", "foobar" };

        // ## Act ##
        Arrays.sort(ary, new Comparator<String>() {
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // ## Assert ##
        for (String string : ary) {
            log(string);
        }
    }
}
