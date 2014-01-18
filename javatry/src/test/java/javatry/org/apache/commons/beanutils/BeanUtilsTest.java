package javatry.org.apache.commons.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

import javatry.unit.UnitTryTestCase;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author jflute
 */
public class BeanUtilsTest extends UnitTryTestCase {

    @SuppressWarnings("unchecked")
    public void test_describe_and_populate() {
        // ## Arrange ##
        final BeanUtilsBean bean = new BeanUtilsBean();
        bean.setAaa("aaa");
        bean.setBbb(new BigDecimal(2));
        bean.setCcc(Timestamp.valueOf("2007-12-12 12:34:56.123"));
        bean.setDdd(4);
        try {
            // ## Act ##
            @SuppressWarnings("rawtypes")
            final Map map = BeanUtils.describe(bean);

            // ## Assert ##
            final BeanUtilsBean nextBean = new BeanUtilsBean();
            map.put("ignore", "ignore");
            BeanUtils.populate(nextBean, map);
            log(nextBean);
            assertEquals(bean.toString(), nextBean.toString());
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e.getCause());
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static class BeanUtilsBean {
        protected String aaa;

        protected BigDecimal bbb;

        protected Timestamp ccc;

        protected int ddd;

        public String getAaa() {
            return aaa;
        }

        public void setAaa(String aaa) {
            this.aaa = aaa;
        }

        public BigDecimal getBbb() {
            return bbb;
        }

        public void setBbb(BigDecimal bbb) {
            this.bbb = bbb;
        }

        public Timestamp getCcc() {
            return ccc;
        }

        public void setCcc(Timestamp ccc) {
            this.ccc = ccc;
        }

        public int getDdd() {
            return ddd;
        }

        public void setDdd(int ddd) {
            this.ddd = ddd;
        }

        public String toString() {
            return aaa + ", " + bbb + ", " + ccc + ", " + ddd;
        }
    }
}
