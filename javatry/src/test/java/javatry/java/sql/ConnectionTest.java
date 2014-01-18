package javatry.java.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class ConnectionTest extends UnitTryTestCase {

    public void test_setAutoCommit_commit_rollback_close() {
        Connection conn = null;
        try {
            conn = getConnection();
            assertTrue(conn.getAutoCommit()); // AutoCommit should be true at this timing!
            conn.setAutoCommit(false);
            assertFalse(conn.getAutoCommit()); // AutoCommit should be false at this timing!

            // ...omitting

            conn.commit();
        } catch (Exception e) {
            throw new RuntimeException("Connectino threw the exception: conn=" + conn, e);
        } finally {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
                try {
                    assertFalse(conn.isClosed()); // Connection should NOT be closed at this timing!
                    conn.close();
                    assertTrue(conn.isClosed()); // Connection should be closed at this timing!
                } catch (SQLException ignored) {
                }
            }
        }
    }
}
