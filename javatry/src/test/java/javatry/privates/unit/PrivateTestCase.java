package javatry.privates.unit;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javatry.privates.base.colorbox.ColorBox;
import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.impl.CompactColorBox;
import javatry.privates.base.colorbox.impl.DoorColorBox;
import javatry.privates.base.colorbox.impl.StandardColorBox;
import javatry.privates.base.colorbox.size.BoxSize;
import javatry.unit.UnitTryTestCase;

/**
 * @author jflute
 */
public abstract class PrivateTestCase extends UnitTryTestCase {

    private ColorBox firstColorBox;
    private ColorBox secondColorBox;
    private ColorBox thirdColorBox;
    private ColorBox fourthColorBox;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        initializeMyColorBox();
    }

    private void initializeMyColorBox() {
        setupColorBox();
    }

    private void setupColorBox() {
        { // 1個目箱
            StandardColorBox colorBox = new StandardColorBox(new BoxColor("green"), new BoxSize(40, 50, 30));
            colorBox.getUpperSpace().addContents("にーるずやーどれめでぃーず"); // getUpperSpace = getSpaceList().get(2);が入ってる
            colorBox.getMiddleSpace().addContents(null); // empty
            colorBox.getLowerSpace().addContents(3); // lowerSpaceが最初に表示されて続いて、middleSpace,upperSpaceの順に表示。この場合、「3,かまくらのいぬ」
            firstColorBox = colorBox;
        }
        { // 2個目箱
            DoorColorBox colorBox = new DoorColorBox(new BoxColor("red"), new BoxSize(50, 30, 40));
            colorBox.getUpperSpace().addContents(926);
            colorBox.getMiddleSpace().addContents(604);
            colorBox.getLowerSpace().addContents("かまくらのいぬ");
            secondColorBox = colorBox;
        }
        { // 3個目箱
            CompactColorBox colorBox = new CompactColorBox(new BoxColor("blue"), new BoxSize(50, 30, 40));
            colorBox.getUpperSpace().addContents(new File("/tmp/jflute.txt"));
            Map<String, Integer> map = new LinkedHashMap<String, Integer>();
            map.put("じゃがいろ", 198);
            map.put("みはねあいす", 390);
            map.put("こまつなびーがんなん", 480);
            colorBox.getLowerSpace().addContents(map);
            thirdColorBox = colorBox;
        }
        { // 4個目箱
            StandardColorBox colorBox = new StandardColorBox(new BoxColor("yellow"), new BoxSize(40, 50, 30));
            colorBox.getUpperSpace().addContents(toDate("2012/06/04"));
            colorBox.getMiddleSpace().addContents(toTimestamp("2012/09/26 12:34:56"));
            List<BigDecimal> decimalList = new ArrayList<BigDecimal>();
            decimalList.add(new BigDecimal(2));
            decimalList.add(new BigDecimal("3.14159"));
            decimalList.add(new BigDecimal("4.753"));
            decimalList.add(new BigDecimal("2.0"));
            decimalList.add(new BigDecimal("1.4"));
            colorBox.getLowerSpace().addContents(decimalList);
            fourthColorBox = colorBox;
        }
    }

    protected List<ColorBox> getColorBoxList() {
        List<ColorBox> colorBoxList = new ArrayList<ColorBox>(); // カラーボックス用のarrayList作成して、4つの箱を追加して戻す
        colorBoxList.add(firstColorBox);
        colorBoxList.add(secondColorBox);
        colorBoxList.add(thirdColorBox);
        colorBoxList.add(fourthColorBox);
        return colorBoxList;
    }
}
