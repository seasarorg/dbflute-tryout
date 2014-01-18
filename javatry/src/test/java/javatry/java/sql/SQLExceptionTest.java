package javatry.java.sql;

import java.sql.SQLException;

import javatry.unit.UnitTryTestCase;

import org.seasar.dbflute.util.DfTypeUtil;

/**
 * @author jflute
 */
public class SQLExceptionTest extends UnitTryTestCase {

    public void test_initCause() {
        // ## Arrange ##
        SQLException sqlEx = new SQLException("test");

        // ## Act ##
        sqlEx.initCause(new IllegalStateException("state"));

        // ## Assert ##
        String stackTrace = DfTypeUtil.toString(sqlEx);
        log(stackTrace);
        assertTrue(stackTrace.contains("state"));
    }
}
