package javatry.java.text;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javatry.unit.UnitTryTestCase;

import org.seasar.dbflute.util.DfStringUtil;
import org.seasar.dbflute.util.DfTypeUtil;

/**
 * @author jflute
 */
public class SimpleDateFormatTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                             parse()
    //                                                                             =======
    public void test_SimpleDateFormat_parse_basic() {
        // ## Arrange ##
        final String target = "3007-04-05 00:00:00";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ## Act ##
        Date date;
        try {
            date = format.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the string: " + target, e);
        }

        // ## Assert ##
        log(format.format(date));
        assertNotNull(date);
        assertEquals(target, format.format(date));
    }

    public void test_SimpleDateFormat_parse_nanos() {
        // ## Arrange ##
        final String target = "3007-04-05 00:00:00";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ## Act ##
        Date date;
        try {
            date = format.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the string: " + target, e);
        }

        // ## Assert ##
        log(format.format(date));
        assertNotNull(date);
        assertEquals(target, format.format(date));
    }

    public void test_SimpleDateFormat_parse_BC_Yyy() {
        // ## Arrange ##
        final String target = "紀元前107-04-05 00:00:00";
        final SimpleDateFormat format = new SimpleDateFormat("GGGyyyy-MM-dd HH:mm:ss");

        // ## Act ##
        Date date;
        try {
            date = format.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the string: " + target, e);
        }

        // ## Assert ##
        String actual = toString(date, "GGGyyyy-MM-dd HH:mm:ss.SSS");
        log(actual);
        assertEquals("紀元前0107-04-05 00:00:00.000", actual);
        assertNotNull(date);
    }

    public void test_SimpleDateFormat_parse_minusYyyy() {
        // ## Arrange ##
        final String target = "-2007-04-05 00:00:00";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ## Act ##
        Date date;
        try {
            date = format.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the string: " + target, e);
        }

        // ## Assert ##
        String actual = toString(date, "GGGyyyy-MM-dd HH:mm:ss.SSS");
        log(actual);
        assertEquals("紀元前2008-04-05 00:00:00.000", actual);
        assertNotNull(date);
    }

    public void test_SimpleDateFormat_parse_invalidDate() {
        // ## Arrange ##
        final String target = "3007-13-05 00:00:00";
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ## Act ##
        Date date;
        try {
            date = dateFormat.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse the string: " + target, e);
        }

        // ## Assert ##
        assertNotNull(date);
        log(dateFormat.format(date));
        assertEquals("3008-01-05 00:00:00", dateFormat.format(date));
    }

    public void test_SimpleDateFormat_parse_invalidDate_setLenient() {
        // ## Arrange ##
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);

        // ## Act ##
        try {
            dateFormat.parse("3007-13-05 00:00:00");

            // ## Assert ##
            fail();
        } catch (ParseException e) {
            // OK
            log(e.getMessage());
        }
    }

    public void test_SimpleDateFormat_parse_millis_short() {
        // ## Arrange ##
        final String target = "2011-04-05 12:34:56.7";
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        // ## Act ##
        Date date;
        try {
            date = format.parse(target);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        // ## Assert ##
        log(format.format(date));
        assertNotNull(date);
        assertEquals("2011-04-05 12:34:56.007", format.format(date));
    }

    // ===================================================================================
    //                                                                            format()
    //                                                                            ========
    public void test_SimpleDateFormat_format() {
        // ## Arrange ##
        final SimpleDateFormat dateFormat = getDateFormat("3007-04-05 00:00:00", Locale.getDefault());
        final Date date = DfTypeUtil.toDate("3007-04-05 12:34:56");

        // ## Act ##
        String value = dateFormat.format(date);

        // ## Assert ##
        log(value);
        assertNotNull(value);
    }

    // ===================================================================================
    //                                                                         Test Helper
    //                                                                         ===========
    protected SimpleDateFormat getDateFormat(String s, Locale locale) {
        String pattern = getPattern(locale);
        String shortPattern = removeDelimiter(pattern);
        String delimitor = findDelimiter(s);
        if (delimitor == null) {
            if (s.length() == shortPattern.length()) {
                return new SimpleDateFormat(shortPattern);
            }
            if (s.length() == shortPattern.length() + 2) {
                return new SimpleDateFormat(DfStringUtil.replace(shortPattern, "yy", "yyyy"));
            }
        } else {
            List<String> splieList = DfStringUtil.splitList(s, delimitor);
            for (String element : splieList) {
                if (element.length() == 4) {
                    pattern = DfStringUtil.replace(pattern, "yy", "yyyy");
                    break;
                }
            }
            return new SimpleDateFormat(pattern);
        }
        return new SimpleDateFormat();
    }

    protected String getPattern(Locale locale) {
        SimpleDateFormat df = (SimpleDateFormat) DateFormat.getDateInstance(DateFormat.SHORT, locale);
        String pattern = df.toPattern();
        int index = pattern.indexOf(' ');
        if (index > 0) {
            pattern = pattern.substring(0, index);
        }
        if (pattern.indexOf("MM") < 0) {
            pattern = DfStringUtil.replace(pattern, "M", "MM");
        }
        if (pattern.indexOf("dd") < 0) {
            pattern = DfStringUtil.replace(pattern, "d", "dd");
        }
        return pattern;
    }

    protected String findDelimiter(String value) {
        for (int i = 0; i < value.length(); ++i) {
            char c = value.charAt(i);
            if (Character.isDigit(c)) {
                continue;
            }
            return Character.toString(c);
        }
        return null;
    }

    protected String findDelimiterFromPattern(String pattern) {
        String ret = null;
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (c != 'y' && c != 'M' && c != 'd') {
                ret = String.valueOf(c);
                break;
            }
        }
        return ret;
    }

    protected String removeDelimiter(String pattern) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < pattern.length(); ++i) {
            char c = pattern.charAt(i);
            if (c == 'y' || c == 'M' || c == 'd') {
                buf.append(c);
            }
        }
        return buf.toString();
    }
}
