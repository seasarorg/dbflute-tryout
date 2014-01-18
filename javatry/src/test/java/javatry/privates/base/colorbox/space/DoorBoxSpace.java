package javatry.privates.base.colorbox.space;

import javatry.privates.base.colorbox.size.BoxSize;

public class DoorBoxSpace extends BoxSpace {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private boolean open;

    // ===================================================================================
    //                                                                         Constructor
    //                                                                         ===========
    public DoorBoxSpace(BoxSize size) {
        super(size);
    }

    // ===================================================================================
    //                                                                          Open/Close
    //                                                                          ==========
    public boolean isOpen() {
        return open;
    }

    public void openTheDoor() {
        open = true;
    }
}
