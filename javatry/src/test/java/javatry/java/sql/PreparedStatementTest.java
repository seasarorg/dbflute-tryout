package javatry.java.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javatry.unit.UnitTryTestCase;
import javatry.unit.CDef;

import org.seasar.dbflute.util.DfTypeUtil;

/**
 * @author jflute
 */
public class PreparedStatementTest extends UnitTryTestCase {

    public void test_executeQuery() throws Exception {
        final Integer targetAuthorId = 3;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            ps = conn.prepareStatement("select * from MEMBER where MEMBER_ID = ?");
            ps.setInt(1, targetAuthorId); // 1 origin
            rs = ps.executeQuery();
            int count = 0;

            while (rs.next()) {
                count++;
                final String memberId = rs.getString("MEMBER_ID");
                final String memberName = rs.getString("MEMBER_NAME");
                log("- - - - - - - - - - - - - - - {" + count + "}");
                log("memberId   = " + memberId);
                log("memberName = " + memberName);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute the SQL statements.", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignored) {
                    ignored.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignored) {
                    ignored.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                    ignored.printStackTrace();
                }
                try {
                    conn.close();
                } catch (SQLException ignored) {
                    ignored.printStackTrace();
                }
            }
        }
    }

    public void test_executeUpdate() {
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            log("{Execute Update}");
            String insertSql = buildMemberInsertSql();
            ps = conn.prepareStatement(insertSql);
            {
                ps.setString(1, "One Member");
                ps.setString(2, "OneMb");
                ps.setString(3, CDef.MemberStatus.Formalized.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.execute();
            }
            {
                ps.setString(1, "Two Member");
                ps.setString(2, "TwoMb");
                ps.setString(3, CDef.MemberStatus.Provisional.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.execute();
            }
            {
                ps.setString(1, "Three Member");
                ps.setString(2, "ThreeMb");
                ps.setString(3, CDef.MemberStatus.Withdrawal.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.execute();
            }

            log("{Execute Query}");
            String selectSql = "select * from MEMBER where MEMBER_ACCOUNT in ('OneMb', 'TwoMb')";
            st = conn.createStatement();
            final ResultSet rs = st.executeQuery(selectSql);
            int count = 0;
            while (rs.next()) {
                count++;
                final String memberId = rs.getString("MEMBER_ID");
                final String memberName = rs.getString("MEMBER_NAME");
                log("- - - - - - - - - - - - - - - {" + count + "}");
                log("memberId   = " + memberId);
                log("memberName = " + memberName);
            }

            // roll-back forcedly here because of test
            //conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to execute the SQL statements.", e);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignored) {
                }
            }
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

    public void test_executeBatch() {
        Connection conn = null;
        PreparedStatement ps = null;
        Statement st = null;
        try {
            conn = getConnection();
            conn.setAutoCommit(false);

            String insertSql = buildMemberInsertSql();
            ps = conn.prepareStatement(insertSql);

            log("{Query Timeout}");
            log("queryTimeout : " + ps.getQueryTimeout());
            ps.setQueryTimeout(10);
            log("queryTimeout : " + ps.getQueryTimeout());

            log("{Execute Batch}");
            {
                ps.setString(1, "One Member");
                ps.setString(2, "OneMb");
                ps.setString(3, CDef.MemberStatus.Formalized.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.addBatch();
            }
            {
                ps.setString(1, "Two Member");
                ps.setString(2, "TwoMb");
                ps.setString(3, CDef.MemberStatus.Provisional.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.addBatch();
            }
            {
                ps.setString(1, "Three Member");
                ps.setString(2, "ThreeMb");
                ps.setString(3, CDef.MemberStatus.Withdrawal.code());
                ps.setTimestamp(4, toTimestamp("2011/12/22"));
                ps.setDate(5, DfTypeUtil.toSqlDate("1990/12/22"));
                ps.addBatch();
            }
            final int[] batchCount = ps.executeBatch();
            assertEquals(3, batchCount.length);
            for (int i : batchCount) {
                log("batchCount(i): " + i);
            }

            log("{Execute Query}");
            String selectSql = "select * from MEMBER where MEMBER_ACCOUNT in ('OneMb', 'TwoMb')";
            st = conn.createStatement();
            final ResultSet rs = st.executeQuery(selectSql);
            int count = 0;
            while (rs.next()) {
                count++;
                final String memberId = rs.getString("MEMBER_ID");
                final String memberName = rs.getString("MEMBER_NAME");
                log("- - - - - - - - - - - - - - - {" + count + "}");
                log("memberId   = " + memberId);
                log("memberName = " + memberName);
            }

            // roll-back forcedly here because of test
            //conn.commit();
        } catch (Exception e) {
            throw new RuntimeException("Failed to execute the SQL statements.", e);
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ignored) {
                }
            }
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ignored) {
                }
                try {
                    conn.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    protected String buildMemberInsertSql() {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into MEMBER(");
        sb.append("MEMBER_NAME, MEMBER_ACCOUNT, MEMBER_STATUS_CODE");
        sb.append(", FORMALIZED_DATETIME, BIRTHDATE");
        sb.append(", REGISTER_DATETIME, REGISTER_USER");
        sb.append(", UPDATE_DATETIME, UPDATE_USER");
        sb.append(", VERSION_NO");
        sb.append(") values(?, ?, ?, ?, ?, current_timestamp, 'test-user', current_timestamp, 'test-user', 0)");
        return sb.toString();
    }
}
