package javatry.java.util.concurrent;

import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import javatry.unit.UnitTryTestCase;

import org.seasar.dbflute.unit.core.thread.ThreadFireExecution;
import org.seasar.dbflute.unit.core.thread.ThreadFireOption;
import org.seasar.dbflute.unit.core.thread.ThreadFireResource;

/**
 * @author jflute
 */
public class ConcurrentHashMapTest extends UnitTryTestCase {

    public void test_entrySet() throws Exception {
        final ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String, String>();
        threadFire(new ThreadFireExecution<Void>() {
            public Void execute(ThreadFireResource resource) {
                final long threadId = resource.getThreadId();
                if (threadId % 2 > 0) {
                    map.put("foo1", "bar1");
                    map.put("foo2", "bar2");
                    log("...Looping entry set: " + map.size());
                    int count = 0;
                    for (Entry<String, String> entry : map.entrySet()) {
                        sleep(1000);
                        log(entry.getKey());
                        ++count;
                    }
                    log("after loop: " + map);
                    assertEquals(3, map.size());
                    assertEquals(2, count);
                } else {
                    sleep(300);
                    log("...Putting three");
                    map.put("foo3", "bar3");
                    log("after put: " + map);
                    assertEquals(3, map.size());
                }
                return null;
            }
        }, new ThreadFireOption().threadCount(2).repeatCount(1));
    }
}
