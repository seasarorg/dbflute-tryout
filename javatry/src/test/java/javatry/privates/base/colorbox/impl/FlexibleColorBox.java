package javatry.privates.base.colorbox.impl;

import java.util.List;

import javatry.privates.base.colorbox.AbstractColorBox;
import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public class FlexibleColorBox extends AbstractColorBox {

    public FlexibleColorBox(BoxColor color, CompactColorBox compactColorBox) {
        super(color);
        List<BoxSpace> spaceList = compactColorBox.getSpaceList();
        for (BoxSpace space : spaceList) {
            addSpace(space);
        }
    }

    @Override
    public void addSpace(BoxSpace boxSpace) {
        super.addSpace(boxSpace);
    }
}
