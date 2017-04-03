package model;

import expr.Environment;

/**
 * Created by cx on 2017-04-02.
 */
// Define what we need XLCell to do:
interface CellStrategy {
    double getValue(Environment e);
}

class XLCell<V> {
    V data;
    CellStrategy strategy;

    public XLCell(V data, CellStrategy cs) {
        strategy = cs;
        this.data = data;
    }

    public double getValue(Environment e) {
        return strategy.getValue(e);
    }

    public void setStrategy(CellStrategy strategy){
        this.strategy = strategy;
    }

    public String toString() { return data.toString(); }
}
