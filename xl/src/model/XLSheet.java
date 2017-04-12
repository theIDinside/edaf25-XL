package model;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;
import gui.CircularReferenceException;
import util.XLException;

import java.io.IOException;
import java.util.*;

/**
 * Created by cx(theIDinside) on 2017-04-02.
 *
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
            final Expr exp = new ExprParser().build(theData);  // build expression from input
            // objects are init'd as final, because they need to be 'effectively final' for lambdas
            Slot xlslot;
            try {
                // construct new slot
                // pass evaluation strategy as 2nd param
                xlslot = new Slot<>(exp, (ss) -> String.valueOf(exp.value(this)));
            }
            catch (NumberFormatException nfe) {
                NumberFormatException se = new NumberFormatException(nfe.getMessage());
                se.initCause(nfe);
                throw se;
            }
            // retrieve the old slot, if there is one, replace it with the new one,
            // which has a "circular reference strategy"
            Slot oldSlot = theSheet.get(address);
            theSheet.replace(address, new Slot<>(exp, (e) -> {
                statusMessage = errorMsg = String.format("Circular reference %s", exp.toString());
                setChanged();
                throw new CircularReferenceException(
                        String.format("Circular reference error at address %s", address),
                        address,
                        exp, oldSlot);
            }));
            // throws exception, if the slot to be eval'd, tries to be "dereferenced"
            // (c++ terminology here, perhaps not fitting)
            xlslot.read(this);
            // passed, exception was not thrown
            System.out.println("Faulty motherfucker");
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

}


