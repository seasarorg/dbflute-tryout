package javatry.privates.base.colorbox.impl;

import javatry.privates.base.colorbox.AbstractColorBox;
import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.size.BoxSize;
import javatry.privates.base.colorbox.space.BoxSpace;

/**
 * @author jflute
 */
public class StandardColorBox extends AbstractColorBox {

    public StandardColorBox(BoxColor color, BoxSize spaceSize) {
        super(color);
        addSpace(createSpace(spaceSize));
        addSpace(createSpace(spaceSize));
        addSpace(createSpace(spaceSize));
    }

    protected BoxSpace createSpace(BoxSize spaceSize) {
        return new BoxSpace(spaceSize);
    }

    public BoxSpace getUpperSpace() {
        return getSpaceList().get(2);
    }

    public BoxSpace getMiddleSpace() {
        return getSpaceList().get(1);
    }

    public BoxSpace getLowerSpace() {
        return getSpaceList().get(0);
    }
}
