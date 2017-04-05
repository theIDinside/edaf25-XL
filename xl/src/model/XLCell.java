package model;

import expr.Environment;
import javafx.scene.control.Cell;
import util.XLException;

import java.util.function.Function;

/**
 * Created by cx on 2017-04-02.
 */
// Define what we need XLCell to do:
interface CellStrategy {
    String getValue(Environment e);
}

public class XLCell<DataType> {
    private DataType data;
    CellStrategy cStrategy;

    protected XLCell(DataType data, CellStrategy cs) {
        this.data = data;
        setCellStrategy(cs);
        //      setPrintStrategy(data::toString);
    }

    public String getValue(Environment e) {
        String value;
        try{
            value = String.valueOf(cStrategy.getValue(e));
            return value;
        } catch (XLException xle) {
            System.out.print("Division by zero");
            return "Error";
        }
    }

    public DataType getData() {
        return this.data;
    }

    public void setCellStrategy(CellStrategy cStrategy){
        this.cStrategy = cStrategy;
    }

    public String toString() {
        return data.toString();
    }
}