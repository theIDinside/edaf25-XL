package model;

/**
 * Created by cx(theIDinside) on 2017-04-07.
 */
public class ErrorSlot<DataType> extends Slot {
    DataType errorData; // holding the new, invalid expression
                        // the restoreData, the previous valid expression
    public ErrorSlot(DataType restoreData, SlotStrategy ss) {
        super(restoreData, ss);
    }

    public void setErrorData(DataType errorData) {
        this.errorData = errorData;
    }

}
