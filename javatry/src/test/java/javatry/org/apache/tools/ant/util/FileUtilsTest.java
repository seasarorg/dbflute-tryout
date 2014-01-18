package javatry.org.apache.tools.ant.util;

import java.io.File;
import java.io.IOException;

import javatry.unit.UnitTryTestCase;

import org.apache.tools.ant.util.FileUtils;

/**
 * @author jflute
 */
public class FileUtilsTest extends UnitTryTestCase {

    public void test_copyFile() throws IOException {
        // ## Arrange ##
        new File("/tmp/foo").mkdirs();
        new File("/tmp/qux").delete();
        new File("/tmp/foo/bar").createNewFile();

        // ## Act ##
        FileUtils.getFileUtils().copyFile("/tmp/foo/bar", "/tmp/qux");

        // ## Assert ##
        File actual = new File("/tmp/qux");
        assertTrue(actual.exists());
        assertTrue(actual.isFile());
        new File("/tmp/foo/bar").delete();
        new File("/tmp/foo").delete();
        new File("/tmp/qux").delete();
    }
}
