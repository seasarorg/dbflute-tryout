package javatry.privates.base.colorbox;

import java.util.List;

import javatry.privates.base.colorbox.color.BoxColor;
import javatry.privates.base.colorbox.size.BoxSize;
import javatry.privates.base.colorbox.space.BoxSpace;

/**
 * 俗に言うカラーボックス。
 * @author jflute
 */
public interface ColorBox {

    BoxColor getColor();

    BoxSize getSize();

    int getSpaceCount();

    List<BoxSpace> getSpaceList();
}
