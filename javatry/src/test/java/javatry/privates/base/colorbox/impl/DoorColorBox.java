package javatry.privates.base.colorbox.impl;

import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.size.BoxSize;
import javatry.privates.base.colorbox.space.BoxSpace;
import javatry.privates.base.colorbox.space.DoorBoxSpace;

/**
 * @author jflute
 */
public class DoorColorBox extends StandardColorBox {

    public DoorColorBox(BoxColor color, BoxSize spaceSize) {
        super(color, spaceSize);
    }

    @Override
    protected BoxSpace createSpace(BoxSize spaceSize) {
        return new DoorBoxSpace(spaceSize);
    }
}
