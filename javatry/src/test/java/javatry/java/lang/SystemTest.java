package javatry.java.lang;

import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class SystemTest extends UnitTryTestCase {

    public void test_getProperties() throws Exception {
        Properties properties = System.getProperties();
        Set<Entry<Object, Object>> entrySet = properties.entrySet();
        for (Entry<Object, Object> entry : entrySet) {
            log(entry.getKey() + " = " + entry.getValue());
        }
    }

    public void test_nanoTime() throws Exception {
        long before = System.nanoTime();
        Thread.sleep(1000);
        long after = System.nanoTime();
        log("sleep=" + (after - before));
    }

    public void test_currentTimeMillis() throws Exception {
        long before = System.currentTimeMillis();
        Thread.sleep(1000);
        long after = System.currentTimeMillis();
        log("sleep=" + (after - before));
    }
}
