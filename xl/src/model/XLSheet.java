package model;

import expr.Environment;

import java.util.Map;
import java.util.Observable;

/**
 * Created by cx on 2017-04-02.
 */
public class XLSheet extends Observable implements Environment {
    Map<String, XLCell> theSheet;
    // this could possibly be Map<Variable, XLCell>
    /***
     * Evaluates expression at address name: String, returns real-value double
     * @param name
     * @return
     */
    @Override
    public double value(String name) {
        /*
               theSheet.get(
               Variable v;
               v.value(this) => a double value
         */
        // theSheet.computeIfAbsent(name, n -> ())
        return 0;
    }

    // methods:
    // addCell(String address, String theData)
    // removeCell(String address)
    // getData(XLCell cell),
    // clear(), called from gui.menu.ClearMenuItem
    // save()
    // load(), load theSheet with data from util.XLBufferedReader (might be moved to another pkg, according to assignment)
    //
}
