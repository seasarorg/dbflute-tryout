package javatry.workshop.beginners;

import java.io.File;

import javatry.unit.UnitTryTestCase;

/**
 * The resource for Java Beginner's Workshop 1st.
 * <pre>
 * o Object Oriented
 * o Exception Message & StackTrace
 * o NullPointerException
 * o equals() and "=="
 * o Primitives and Wrappers
 * </pre>
 * @author jflute
 */
public class JavaBeginnersWorkshop1stTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                     Object Oriented
    //                                                                     ===============
    public void test_ObjectOriented() throws Exception {
        // Object has data and behaviors
        File file = new File("/tmp/java-beginners-workshop.txt"); // has file path and connection to the file
        if (file.exists()) { // behavior (determination about the file)
            file.delete(); // behavior (manipulation to the file)
        }
        log("file.exists(): " + file.exists());
        log("file.canRead(): " + file.canRead());
        log("file.canWrite(): " + file.canWrite());
        log("file.getAbsolutePath(): " + file.getAbsolutePath());
        log("file.getCanonicalPath(): " + file.getCanonicalPath());
    }

    // ===================================================================================
    //                                                      Exception Message & StackTrace
    //                                                      ==============================
    public void test_ExceptionMessageAndStackTrace() throws Exception {
        try {
            throwWrapperException();
        } catch (IllegalStateException e) {
            log(e);
        }
    }

    protected void throwWrapperException() {
        try {
            throwInnerException();
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Wrapper Exception", e);
        }
    }

    protected void throwInnerException() {
        throw new IllegalStateException("Inner Exception");
    }

    // ===================================================================================
    //                                                                NullPointerException
    //                                                                ====================
    public void test_NullPointerException() throws Exception {
        String nullStr = getNullString();
        String notNullStr = getNotNullString();
        try {
            nullStr.length(); // null pointer
            fail();
        } catch (NullPointerException e) {
            log(e);
        }
        try {
            log(notNullStr.length(), nullStr.length()); // which is null?
            fail();
        } catch (NullPointerException e) {
            log(e);
        }
    }

    protected String getNullString() {
        return null;
    }

    protected String getNotNullString() {
        return "foo";
    }

    // ===================================================================================
    //                                                                   equals() and "=="
    //                                                                   =================
    public void test_EqualsAndEqualEqual() throws Exception {
        File file1 = new File("/tmp/memo.txt");
        File file2 = new File("/tmp/memo.txt");
        File file3 = file2;
        assertTrue(file1.equals(file2));
        assertFalse(file1 == file2);
        assertTrue(file2 == file3);

        // Integer
        assertTrue(Integer.valueOf(2).equals(Integer.valueOf(2)));
        assertTrue(Integer.valueOf(2).equals(new Integer(2)));
        assertFalse(Integer.valueOf(2) == new Integer(2));
        assertTrue(Integer.valueOf(2) == Integer.valueOf(2));
        assertFalse(Integer.valueOf(128) == Integer.valueOf(128));
    }

    // ===================================================================================
    //                                                             Primitives and Wrappers
    //                                                             =======================
    public void test_PrimitivesAndWrappers() throws Exception {
        int countInt = Integer.valueOf(2);
        long countLong = Long.valueOf(5);
        double countDouble = Double.valueOf(3.2);

        log(countInt, countLong, countDouble);

        try {
            int nullInt = getNullInteger(); // null pointer
            fail("nullInt=" + nullInt);
        } catch (NullPointerException e) {
            log(e);
        }
        try {
            Integer nullInteger = getNullInteger();
            Integer plusOne = nullInteger + 1; // null pointer
            fail("plusOne=" + plusOne);
        } catch (NullPointerException e) {
            log(e);
        }
    }

    protected Integer getNullInteger() {
        return null;
    }
}
