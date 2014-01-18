package javatry.java.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class LinkedHashMapTest extends UnitTryTestCase {

    public void test_put_override() {
        // ## Arrange ##
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("bbb", "B");
        map.put("ccc", "C");
        map.put("aaa", "A");
        map.put("eee", "E");
        map.put("ddd", "D");

        // ## Act ##
        map.put("ccc", "CCC");

        // ## Assert ##
        List<String> list = new ArrayList<String>(map.keySet());
        log(list);
        assertEquals("ccc", list.get(1));
    }

    public void test_entrySet() {
        // ## Arrange ##
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("bbb", "B");
        map.put("ccc", "C");
        map.put("aaa", "A");
        map.put("eee", "E");
        map.put("ddd", "D");

        // ## Act ##
        Set<Entry<String, String>> entrySet = map.entrySet();

        // ## Assert ##
        assertNotSame(0, entrySet.size());
        List<String> keyList = new ArrayList<String>();
        List<String> valueList = new ArrayList<String>();
        for (Entry<String, String> entry : entrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            keyList.add(key);
            valueList.add(value);
        }
        assertEquals("bbb", keyList.get(0));
        assertEquals("ccc", keyList.get(1));
        assertEquals("aaa", keyList.get(2));
        assertEquals("eee", keyList.get(3));
        assertEquals("ddd", keyList.get(4));
        assertEquals("B", valueList.get(0));
        assertEquals("C", valueList.get(1));
        assertEquals("A", valueList.get(2));
        assertEquals("E", valueList.get(3));
        assertEquals("D", valueList.get(4));
    }
}
