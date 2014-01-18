package javatry.java.io;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class BufferedReaderTest extends UnitTryTestCase {

    public void test_readLine_basic() {
        // ## Arrange ##
        final String text = "あいうえお～です。\nかきくけこ－です。\nさしすせそ＠です。\n";
        final String encoding = "UTF-8";

        final ByteArrayInputStream byteArray;
        try {
            byteArray = new ByteArrayInputStream(text.getBytes(encoding));
        } catch (UnsupportedEncodingException e1) {
            String msg = "The encoding is unsupported: encoding=" + encoding;
            throw new IllegalStateException(msg);
        }

        final BufferedReader br;
        try {
            br = new BufferedReader(new InputStreamReader(byteArray, encoding));
        } catch (UnsupportedEncodingException e1) {
            String msg = "The encoding is unsupported: encoding=" + encoding;
            throw new IllegalStateException(msg);
        }
        String lineString = null;
        final StringBuilder sb = new StringBuilder();
        try {
            while (true) {
                // ## Act ##
                lineString = br.readLine();
                if (lineString == null) {
                    break;
                }
                sb.append(lineString).append("\n");
            }
        } catch (IOException e) {
            String msg = "bufferedReader.readLine() threw the exception: current-line=" + lineString;
            throw new IllegalStateException(msg, e);
        }
        final String resultText = sb.toString();

        // ## Assert ##
        assertEquals(text, resultText);
    }
}
