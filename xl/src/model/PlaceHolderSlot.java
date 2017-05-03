package model;

import expr.Environment;
import util.XLException;

/**
 * Created by cx on 5/3/17.
 */
public class PlaceHolderSlot implements SlotInterface {

    PlaceHolderSlot() {}

    @Override
    public double value(Environment e) {
        throw new XLException("Error, trying to dereference circular reference");
    }

    @Override
    public String display(Environment e) {
        return "Circular Ref.";
    }
}
