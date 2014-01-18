package javatry.java.io;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class FileTest extends UnitTryTestCase {

    public void test_getName() {
        File file = new File("/tmp/foo.txt");
        assertEquals("foo.txt", file.getName());
    }

    public void test_getPath() {
        File file = new File("/tmp/foo.txt");
        assertEquals("/tmp/foo.txt", replace(file.getPath(), "\\", "/"));
    }

    public void test_listFiles_FilenameFilter() {
        // ## Arrange ##
        File tmpDir = new File("/tmp");
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return true;
            }
        };

        // ## Act ##
        File[] listFiles = tmpDir.listFiles(filter);

        // ## Assert ##
        assertNotSame(0, listFiles);
        for (File file : listFiles) {
            log(file);
        }
    }

    public void test_getAbsolutePath() {
        log(new File("").getAbsolutePath());
        log(new File(".").getAbsolutePath());
        log(new File("/").getAbsolutePath());
    }

    public void test_getCanonicalPath() throws IOException {
        log(new File("").getCanonicalPath());
        log(new File(".").getCanonicalPath());
        log(new File("/").getCanonicalPath());
    }
}