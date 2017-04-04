package model;

import expr.Environment;
import gui.XL;

import java.io.PrintStream;

/**
 * Created by cx on 2017-04-02.
 */
// Define what we need XLCell to do:
interface CellStrategy {
    double getValue(Environment e);
}

// defines interface to handle how to print the data of a cell
// could be used to handle how images are represented,
// amongst other types of data
interface printStrategy<V> {
    V print();
}

public class XLCell<V> {
    public V data;
    CellStrategy cStrategy;
    printStrategy pStrategy;
    public XLCell(V data, CellStrategy cs) {
        this.data = data;
        setCellStrategy(cs);
        this.setPrintStrategy(() -> "");
    }
    public XLCell(V data, CellStrategy cs, printStrategy ps) {
        this.data = data;
        setCellStrategy(cs);
        setPrintStrategy(ps);
    }

    public double getValue(Environment e) {
        return cStrategy.getValue(e);
    }

    public void setCellStrategy(CellStrategy cStrategy){
        this.cStrategy = cStrategy;
    }
    public CellStrategy getCellStrategy(){
        return cStrategy;
    }

    public void setPrintStrategy(printStrategy pStrategy){
        this.pStrategy = pStrategy;
    }
    public printStrategy getPrintStrategy(){
        return pStrategy;
    }

    public String toString() { //return data.toString();
        return (String)pStrategy.print();
    }
}