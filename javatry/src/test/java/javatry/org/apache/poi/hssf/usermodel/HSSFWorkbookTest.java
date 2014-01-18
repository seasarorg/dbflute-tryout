package javatry.org.apache.poi.hssf.usermodel;

import java.io.InputStream;
import java.util.Iterator;

import javatry.unit.UnitTryTestCase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * @author jflute
 */
public class HSSFWorkbookTest extends UnitTryTestCase {

    protected static final String LIST_TEMPLATE_RESOURCE = "poifile/ListTemplate.xls";

    public void test_read_workbook() throws Exception {
        // ## Arrange ##
        final InputStream ins = getClass().getClassLoader().getResourceAsStream(LIST_TEMPLATE_RESOURCE);
        assertNotNull(ins);

        // ## Act ##
        final HSSFWorkbook workbook = new HSSFWorkbook(ins);

        // ## Assert ##
        log(workbook);
        int numberOfSheets = workbook.getNumberOfSheets();
        assertTrue(numberOfSheets > 0);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            final HSSFSheet sheet = workbook.getSheetAt(i);
            log("  sheet[" + workbook.getSheetName(i) + "]");
            for (Iterator<?> rowIte = sheet.rowIterator(); rowIte.hasNext();) {
                final HSSFRow row = (HSSFRow) rowIte.next();
                assertNotNull(row);
                final StringBuilder sb = new StringBuilder();
                int count = 0;
                for (Iterator<?> cellIte = row.cellIterator(); cellIte.hasNext();) {
                    final HSSFCell cell = (HSSFCell) cellIte.next();
                    assertNotNull(cell);
                    final HSSFRichTextString richStringCellValue = cell.getRichStringCellValue();
                    if (count > 0) {
                        sb.append(", ");
                    }
                    assertNotNull(richStringCellValue);
                    sb.append("\"" + richStringCellValue + "\"");
                    ++count;
                }
                log("    row[" + row.getRowNum() + "]: " + sb);
            }
        }
    }
}
