package javatry.java.lang;

import java.io.IOException;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ProcessBuilderTest extends UnitTryTestCase {

    public void test_string_array() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("java", "-version");
        Process process = builder.start();
        int exitCode = process.waitFor();
        log("exitCode: " + exitCode);
    }

    public void test_string_bar() throws Exception {
        ProcessBuilder builder = new ProcessBuilder("java -version");
        try {
            builder.start();

            fail();
        } catch (IOException e) {
            log(e.getMessage());
        }
    }
}
