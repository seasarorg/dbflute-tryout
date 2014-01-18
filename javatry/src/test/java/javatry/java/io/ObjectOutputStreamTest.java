package javatry.java.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ObjectOutputStreamTest extends UnitTryTestCase {

    private static final int BYTE_ARRAY_SIZE = 8 * 1024;

    public void test_fromObjectToBinary() {
        // ## Arrange ##
        String targetString = "あいうえお";

        // ## Act ##
        byte[] bytes = fromObjectToBinary(targetString);

        // ## Assert ##
        Object reversedValue = fromBinaryToObject(bytes);
        assertEquals(targetString, reversedValue);
    }

    protected static byte[] fromObjectToBinary(Object o) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(BYTE_ARRAY_SIZE);
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            try {
                oos.writeObject(o);
            } finally {
                oos.close();
            }
            return baos.toByteArray();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    protected static Object fromBinaryToObject(byte[] binary) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(binary);
            ObjectInputStream ois = new ObjectInputStream(bais);
            try {
                return ois.readObject();
            } finally {
                ois.close();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}