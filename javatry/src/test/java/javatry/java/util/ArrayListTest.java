package javatry.java.util;

import java.util.ArrayList;
import java.util.List;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ArrayListTest extends UnitTryTestCase {

    public void test_subList_basic() {
        // ## Arrange ##
        ArrayList<String> list = new ArrayList<String>();
        list.add("foo");
        list.add("bar");
        list.add("qux");
        list.add("corge");

        // ## Act ##
        List<String> subList = list.subList(1, 3);

        // ## Assert ##
        assertEquals(2, subList.size());
        assertEquals("bar", subList.get(0));
        assertEquals("qux", subList.get(1));
    }
}
