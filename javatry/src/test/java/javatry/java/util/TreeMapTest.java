package javatry.java.util;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeMap;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class TreeMapTest extends UnitTryTestCase {

    public void test_compare() {
        // ## Arrange ##
        TreeMap<String, String> treeMap = new TreeMap<String, String>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                int result = o1.compareTo(o2);
                log(o1 + " / " + o2 + " = " + result);
                return result;
            }
        });

        // ## Act ##
        treeMap.put("bbb", null);
        treeMap.put("ccc", null);
        treeMap.put("aaa", null);
        treeMap.put("eee", null);
        treeMap.put("ddd", null);

        // ## Assert ##
        Set<String> keySet = treeMap.keySet();
        for (String key : keySet) {
            log(key);
        }
    }
}
