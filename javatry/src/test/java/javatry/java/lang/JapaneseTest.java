package javatry.java.lang;

import javatry.unit.UnitTryTestCase;

import org.junit.Test;

/**
 * @author jflute
 */
public class JapaneseTest extends UnitTryTestCase {

    // ===================================================================================
    //                                                                          Definition
    //                                                                          ==========
    // Double-byte KATAKANA without voiced sound, containing Japanese marks
    protected static final String ZEN_KATAKANA_DIC = "\u3002\u300c\u300d\u3001\u30fb"
            + "\u30a1\u30a3\u30a5\u30a7\u30a9\u30e3\u30e5\u30e7\u30c3\u30fc" + "\u30a2\u30a4\u30a6\u30a8\u30aa" // AIUEO
            + "\u30ab\u30ad\u30af\u30b1\u30b3" // KAKIKUKEKO
            + "\u30b5\u30b7\u30b9\u30bb\u30bd" // SASHISUSESO
            + "\u30bf\u30c1\u30c4\u30c6\u30c8" // TACHITSUTETO
            + "\u30ca\u30cb\u30cc\u30cd\u30ce" // NANINUNENO
            + "\u30cf\u30d2\u30d5\u30d8\u30db" // HAHIHUHEHO
            + "\u30de\u30df\u30e0\u30e1\u30e2" // MAMIMUMEMO
            + "\u30e4\u30e6\u30e8" // YAYUYO
            + "\u30e9\u30ea\u30eb\u30ec\u30ed" // RARIRURERO
            + "\u30ef\u30f2\u30f3" // WAWON
            + "\u309b\u309c";

    // (uff71 - uff9d)
    protected static final String ZEN_VOICED_SOUND_NORMAL_KATAKANA = "\u30a2\u30a4\u30f4\u30a8\u30aa" // AIVUEO
            + "\u30ac\u30ae\u30b0\u30b2\u30b4" // GAGIGUGEGO
            + "\u30b6\u30b8\u30ba\u30bc\u30be" // ZAJIZUZEZO
            + "\u30c0\u30c2\u30c5\u30c7\u30c9" // DAJIZUDEDO
            + "\u30ca\u30cb\u30cc\u30cd\u30ce" // NANINUNENO
            + "\u30d0\u30d3\u30d6\u30d9\u30dc" // BABIBUBEBO
            + "\u30de\u30df\u30e0\u30e1\u30e2" // MAMIMUMEMO
            + "\u30e4\u30e6\u30e8" // YAYUYO
            + "\u30e9\u30ea\u30eb\u30ec\u30ed" // RARIRURERO
            + "\u30ef\u30f3"; // WAWON

    // (uff66 - uff6f)
    protected static final String ZEN_VOICED_SOUND_SPECIAL_KATAKANA = "\u30fa\u30a1\u30a3\u30a5\u30a7\u30a9\u30e3\u30e5\u30e7";

    // (u30cf - u30dd)
    protected static final String ZEN_SEMI_VOICED_SOUND_KATAKANA = "\u30d1\u30d4\u30d7\u30da\u30dd";

    // ===================================================================================
    //                                                                            KATAKANA
    //                                                                            ========
    protected String convertToZenKatakana(String target) {
        if (target == null) {
            return target;
        }

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            final char currentChar = target.charAt(i);
            final char nextChar;
            if (i < target.length() - 1) {
                nextChar = target.charAt(i + 1);
            } else {
                nextChar = ' ';
            }

            if (isVoicedSoundKana(currentChar, nextChar)) {
                if (currentChar >= 0xff66 && currentChar <= 0xff6f) { // voiced sound special KATAKANA
                    sb.append(ZEN_VOICED_SOUND_SPECIAL_KATAKANA.charAt(currentChar - 0xff66));
                    i++;
                } else if (currentChar >= 0xff71 && currentChar <= 0xff9d) { // voiced sound normal KATAKANA
                    sb.append(ZEN_VOICED_SOUND_NORMAL_KATAKANA.charAt(currentChar - 0xff71));
                    i++;
                }

            } else if (isSemiVoicedSoundKana(currentChar, nextChar)) {
                sb.append(ZEN_SEMI_VOICED_SOUND_KATAKANA.charAt(currentChar - 0xff8a));
                i++;
            } else if (currentChar != 0xff9e && currentChar != 0xff9f) {
                if (currentChar >= 0xff61 && currentChar <= 0xff9f) {
                    sb.append(ZEN_KATAKANA_DIC.charAt(currentChar - 0xff61));
                } else {
                    sb.append(currentChar);
                }
            }
        }
        return sb.toString();
    }

    protected boolean isVoicedSoundKana(final char currentChar, final char nextChar) {
        return ((currentChar >= 0xff66 && currentChar <= 0xff6f) || (currentChar >= 0xff71 && (currentChar <= 0xff9d)))
                && (nextChar == 0xff9e);
    }

    protected boolean isSemiVoicedSoundKana(final char currentChar, final char nextChar) {
        return (currentChar >= 0xff8a && currentChar <= 0xff8e) && (nextChar == 0xff9f);
    }

    // ===================================================================================
    //                                                                         Single Byte
    //                                                                         ===========
    @Test
    public void test_convertToSingleByte() throws Exception {
        assertEquals("ss1-dd２", convertToSingleByteAlphabet("sｓ1-dｄ２"));
        assertEquals("sｓ1-dｄ2", convertToSingleByteNumber("sｓ1-dｄ２"));
    }

    protected String convertToSingleByteAlphabet(String target) {
        if (target == null) {
            return target;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            final char currentChar = target.charAt(i);

            if (currentChar >= 0xff21 && currentChar <= 0xff3a) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else if (currentChar >= 0xff41 && currentChar <= 0xff5a) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    protected String convertToSingleByteNumber(String target) {
        if (target == null) {
            return target;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            final char currentChar = target.charAt(i);
            if (currentChar >= 0xff10 && currentChar <= 0xff19) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    protected String convertToSingleByteAlphabetNumber(String target) {
        if (target == null) {
            return target;
        }
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < target.length(); i++) {
            final char currentChar = target.charAt(i);

            if (currentChar >= 0xff10 && currentChar <= 0xff19) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else if (currentChar >= 0xff21 && currentChar <= 0xff3a) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else if (currentChar >= 0xff41 && currentChar <= 0xff5a) {
                sb.append(doConvertToSingleByteCharacter(currentChar));
            } else {
                sb.append(currentChar);
            }
        }
        return sb.toString();
    }

    protected char doConvertToSingleByteCharacter(final char currentChar) {
        return (char) (currentChar - 0xfee0);
    }
}
