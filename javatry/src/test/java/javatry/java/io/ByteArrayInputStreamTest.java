package javatry.java.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ByteArrayInputStreamTest extends UnitTryTestCase {

    public void test_ByteArrayInputStream_read() {
        // ## Arrange ##
        final String text = "あいうえお～です。\nかきくけこ－です。\nさしすせそ＠です。\n";
        final String encoding = "UTF-8";
        final ByteArrayInputStream byteArray;
        final byte[] bytes;
        try {
            bytes = text.getBytes(encoding);
            byteArray = new ByteArrayInputStream(bytes);
        } catch (UnsupportedEncodingException e) {
            String msg = "The encoding is unsupported: encoding=" + encoding;
            throw new IllegalStateException(msg);
        }
        final byte[] resultBytes = new byte[bytes.length];
        try {
            // ## Act ##
            final int read = byteArray.read(resultBytes);

            // ## Assert ##
            assertEquals(bytes.length, read);
        } catch (IOException e) {
            String msg = "byteArray.read(resultBytes) threw the exception: resultBytes=" + resultBytes;
            throw new IllegalStateException(msg, e);
        }

        try {
            // ## Assert ##
            final String resultString = new String(resultBytes, encoding);
            assertEquals(text, resultString);
        } catch (UnsupportedEncodingException e) {
            String msg = "The encoding is unsupported: encoding=" + encoding;
            throw new IllegalStateException(msg);
        }
    }
}
