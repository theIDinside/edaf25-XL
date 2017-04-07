package model;

import expr.Environment;
import gui.CircularReferenceException;
import util.XLException;

/**
 * Created by cx on 2017-04-02.
 */
// Define what we need Slot to do:
interface SlotStrategy {
    String evaluate(Environment e);
}

public class Slot<DataType> {
    private DataType data; // Expr, String, Image, 'you can have whatever you like'
    SlotStrategy slotStrategy;
    public Slot(DataType data, SlotStrategy ss) {
        this.data = data;
        slotStrategy = ss;
    }

    public String read(Environment e) throws NumberFormatException, CircularReferenceException, XLException {
            return String.valueOf(slotStrategy.evaluate(e));
    }

    public DataType getData() {
        return this.data;
    }

    public void setSlotStrategy(SlotStrategy cStrategy){
        this.slotStrategy = cStrategy;
    }

    public String toString() {
        return data.toString();
    }
}