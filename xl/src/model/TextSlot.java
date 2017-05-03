package model;

import expr.Environment;

/**
 * Created by cx on 5/3/17.
 */
public class TextSlot implements SlotInterface {
    String data;
    TextSlot(String data) {
        this.data = data;
    }
    @Override
    public double value(Environment e) {
        return 0.0;
    }

    @Override
    public String display(Environment e) {
        return data.substring(1);
    }
    @Override
    public String toString() {
        return data;
    }
}
