package javatry.java.util.regex;

import java.util.regex.Pattern;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class MatcherTest extends UnitTryTestCase {

    public void test_matches_BehaviorQueryPathPattern() throws Exception {
        // ## Arrange ##
        final Pattern behaviorQueryPathPattern = Pattern.compile(".+/exbhv/.+Bhv_.+.sql$");

        // ## Act & Assert ##
        assertTrue(behaviorQueryPathPattern.matcher("aaa/bbb/exbhv/MemberBhv_selectList.sql").matches());
        assertFalse(behaviorQueryPathPattern.matcher("aaa/bbb/bhv/MemberBhv_selectList.sql").matches());
    }

    public void test_matches_SharpPrefix() throws Exception {
        // ## Arrange ##
        final Pattern sharpPrefixPattern = Pattern.compile("#.+");

        // ## Act & Assert ##
        assertTrue(sharpPrefixPattern.matcher("#abc123").matches());
        assertFalse(sharpPrefixPattern.matcher("abc123").matches());
        assertTrue(sharpPrefixPattern.matcher("#a").matches());
        assertFalse(sharpPrefixPattern.matcher("a").matches());
    }
}
