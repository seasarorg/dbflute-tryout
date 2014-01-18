package javatry.privates.base.colorbox.impl;

import javatry.privates.base.colorbox.AbstractColorBox;
import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.size.BoxSize;
import javatry.privates.base.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public class CompactColorBox extends AbstractColorBox {

    public CompactColorBox(BoxColor color, BoxSize spaceSize) {
        super(color);
        addSpace(new BoxSpace(spaceSize));
        addSpace(new BoxSpace(spaceSize));
    }

    public BoxSpace getUpperSpace() {
        return getSpaceList().get(1);
    }

    public BoxSpace getLowerSpace() {
        return getSpaceList().get(0);
    }
}
