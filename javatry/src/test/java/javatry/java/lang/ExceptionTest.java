package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ExceptionTest extends UnitTryTestCase {

    public void test_StackTrace_nestedExceptionHasFullStackTarce() throws Exception {
        // ## Arrange ##
        FirstObj obj = new FirstObj();

        // ## Act ##
        try {
            obj.first();

            // ## Assert ##
            fail();
        } catch (IllegalStateException e) {
            log("[First]");
            log(e.getMessage());
            showStackTrace(e);

            log("[Second]");
            Throwable secondCause = e.getCause();
            log(secondCause.getMessage());
            showStackTrace(secondCause);
        }
    }

    protected void showStackTrace(Throwable e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            String className = element.getClassName();
            String methodName = element.getMethodName();
            int lineNumber = element.getLineNumber();
            log(className + "." + methodName + "():" + lineNumber);
        }
    }

    protected static class FirstObj {
        public void first() {
            SecondObj obj = new SecondObj();
            try {
                obj.second();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("first", e);
            }
        }
    }

    protected static class SecondObj {
        public void second() {
            throw new IllegalStateException("second");
        }
    }
}
