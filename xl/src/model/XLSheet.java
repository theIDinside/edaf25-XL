package model;

import expr.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by cx on 2017-04-02.
 */
// The subject, in the Publish-Subject design pattern, or what the
// Java The Complete Reference (2014) calls the Model-Delegate architecture.
// According to Herbert & Schildt, Swing uses a modified version of the MVC.

public class XLSheet extends Observable implements Environment {
    Map<String, XLCell> theSheet;
    // this could possibly be Map<Variable, XLCell>

    public XLSheet() {
        theSheet = new HashMap<>();
    }

    public void addData(String address, String theData) {

    }

    public <ViewElement> void registerViewElement(ViewElement elem) {
        addObserver((Observer) elem);
    }

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

     /**
     * methods:
     * addCell(String address, String theData), add cell to XLSheet, register ViewElement -> XLSheet.XLCell
     * removeCell(String address), unregister ViewElement <- XLSheet.XLCell, remove cell from XLSheet
     * getData(XLCell cell),
     * clear(), called from gui.menu.ClearMenuItem // for all elements: null
     * save(), save theSheet
     * load(), load theSheet with data from util.XLBufferedReader (might be moved to another pkg, according to assignment)
     * validateInput(), check for errors, circular dependencies, etc. Read assignment material for more info
     *   as this method will "walk" over the cells, in order to validate for example an expression, we should be able
     *   to pass along a function that, depending on what we're after, maps cellcontent -> updating notifyList
     * Since our XLCell will either be an abstract class, or an interface definining behavior instead of inheritance,
     * a static builder class is in it's place. Using the strategy pattern (in which case XLCell will be an interface)
     * one can define different behaviors, depending on "who" is calling the methods, that the interface require the class to have.
     * According to design meeting prep questions, using the strategy pattern will help with dealing with circular dependencies.
     * This will be achieved by changing the behavior of the XLCell, while "walking" over an expression. So if there is a circular dep.
     * when the "walk over"-algorithm reaches back to the first cell again, it can throw an exception, or take a different if branch,
     * or something to that effect.
    **/

    static class CellBuilder {
        public static XLCell CellBuilder(String theRawData) {
            return null;
        }
    }
}


