package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public class StringTest extends UnitTryTestCase {

    public void test_replaceAll_escapedChar() {
        // ## Arrange ##
        String str = "abc\rdef\nghi";

        // ## Act ##
        str = str.replaceAll("\r", "@");

        // ## Assert ##
        assertEquals("abc@def\nghi", str);
    }

    public void test_indexOf() throws Exception {
        String str = "jflute"; // [0] j [1] f [2] l [3] u [4] t [5] e [6]

        assertEquals(1, str.indexOf("f"));
        assertEquals(3, str.indexOf("u"));
        assertEquals(0, str.indexOf("j"));
        assertEquals(-1, str.indexOf("x")); // その文字が存在しない場合
    }

    public void test_substring() throws Exception {
        String str = "jflute"; // [0] j [1] f [2] l [3] u [4] t [5] e [6]

        assertEquals("flute", str.substring(1)); // A. 最初の一文字を除去
        assertEquals("lute", str.substring(2)); // B. 最初の二文字を除去
        assertEquals("jfl", str.substring(0, 3)); // C. 最初の3文字
        assertEquals("flu", str.substring(1, 4)); // D. 一つ飛ばして3文字

        int len = str.length();
        assertEquals("e", str.substring(len - 1, len)); // E. 最後の一文字を取得
        assertEquals("te", str.substring(len - 2, len)); // F. 最後の二文字を取得
    }

    public void test_lastIndexOf_substring() throws Exception {
        String path = "topic/programming/java/string.html";
        String delimiter = "/";
        int index = path.lastIndexOf(delimiter);
        String fileName;
        if (index >= 0) { // 指定した文字が存在するなら
            fileName = path.substring(index + delimiter.length()); // "string.html"
        } else {
            fileName = path; // 既にパス部分が存在していないのでそのまま
        }
        log(fileName);
        assertEquals("string.html", fileName);
    }

    public void test_format() throws Exception {
        assertEquals("0000123", String.format("%07d", 123));
    }
}
