package model;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;
import gui.CircularReferenceException;
import util.XLException;

import java.io.IOException;
import java.util.*;

/**
 * Created by cx on 2017-04-02.
 */
// The subject, in the Publish-Subject design pattern, or what the
// Java The Complete Reference (2014) calls the Model-Delegate architecture.
// According to Herbert & Schildt, Swing uses a modified version of the MVC, and not true MVC.

public class XLSheet extends Observable implements Environment {
    Map<String, Slot> theSheet;
    // this could possibly be Map<Variable, Slot>
    public String errorMsg = null;
    public String statusMessage = "";
    public XLSheet() {
        theSheet = new HashMap<>();
    }

    public String getLastErrorMessage() {
        return statusMessage;
    }

    public Slot getCell(String addr) {
        if(theSheet.containsKey(addr)) return theSheet.get(addr);
        else return null;
    }

    /**
     * Build & add a new cell, corresponding to view cell at address address, from theData to theSheet
     * @param address
     * @param theData
     */
    public void addData(String address, String theData) throws IOException, CircularReferenceException, NumberFormatException {
        if(theData.length() > 0 && theData.charAt(0) == '#') // the following will be a valid string
        {
            // input in box is a string, prefixed by '#'
            final String content = theData.substring(1);
            Slot slot = new Slot<String>(theData, e -> content);
            theSheet.put(address, slot);
        } else {
            Expr exp;

            exp = new ExprParser().build(theData);  // build expression from input
            Slot xlslot;
            try {
                xlslot = new Slot<>(exp, (ss) -> String.valueOf(exp.value(this)));
            }
            catch (NumberFormatException nfe) {
                NumberFormatException se = new NumberFormatException(nfe.getMessage());
                se.initCause(nfe);
                throw se;
            }
            Slot oldSlot = theSheet.replace(address, new Slot<>(exp, (e) -> {
                errorMsg = String.format("Circular reference %s", exp.toString());
                statusMessage = errorMsg;
                throw new CircularReferenceException("Circular reference error", address, exp);
            }));
            xlslot.read(this); // throws exception
            // passed
            theSheet.put(address, xlslot);
            }
            theSheet.entrySet().forEach(System.out::println);
            setChanged();
            notifyObservers();
            statusMessage = "";
        }

    /**
     * Forcefully puts a slot at address, quietly
     * @param address
     * @param slot
     */
        public void forcePutSlot(String address, Slot slot) {
            theSheet.put(address, slot);
        }

    public boolean removeData(String address) {
        if(theSheet.containsKey(address)) {
            theSheet.remove(address);
            setChanged();
            notifyObservers();
            return true;
        } else
            return false;
    }

    // will probably be removed
    public <ViewElement> void registerViewElement(ViewElement elem) {
        addObserver((Observer) elem);
    }

    /***
     * Evaluates expression at address, of type String, returns real-value double
     * @param address
     * @return value
     */
    @Override
    public double value(String address) throws NullPointerException, NumberFormatException {
        if(theSheet.get(address) == null) {
            throw new NullPointerException(String.format("Cell %s does not exist.", address));
        }
            return Double.valueOf(theSheet.get(address).read(this));
    }

    public boolean hasCell(String address) {
        return theSheet.get(address) != null;
    }

    public String getNotEvalContent(String address) {
        Slot cell = theSheet.get(address);
        return cell.read(this);
    }

    /**
     * methods:
     * removeCell(String address), unregister ViewElement <- XLSheet.Slot, remove cell from XLSheet
     * getData(Slot cell),
     * clear(), called from gui.menu.ClearMenuItem // for all elements: null
     * save(), save theSheet
     * load(), load theSheet with data from util.XLBufferedReader (might be moved to another pkg, according to assignment)
     * validateInput(), check for errors, circular dependencies, etc. Read assignment material for more info
     * as this method will "walk" over the cells, in order to validate for example an expression, we should be able
     * to pass along a function that, depending on what we're after, maps cellcontent -> updating notifyList
     * Since our Slot will either be an abstract class, or an interface definining behavior instead of inheritance,
     * a static builder class is in it's place. Using the slotStrategy pattern (in which case Slot will be an interface)
     * one can define different behaviors, depending on "who" is calling the methods, that the interface require the class to have.
     * According to design meeting prep questions, using the slotStrategy pattern will help with dealing with circular dependencies.
     * This will be achieved by changing the behavior of the Slot, while "walking" over an expression. So if there is a circular dep.
     * when the "walk over"-algorithm reaches back to the first cell again, it can throw an exception, or take a different if branch,
     * or something to that effect.
    **/

}


