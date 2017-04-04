package model;

import expr.Environment;
import expr.Expr;
import expr.ExprParser;
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
    Map<String, XLCell> theSheet;
    // this could possibly be Map<Variable, XLCell>
    public String errorMsg = null;
    public Stack<String> errorMessageStack;
    public XLSheet() {
        theSheet = new HashMap<>();
        errorMessageStack = new Stack<>();
    }

    public String getLastErrorMessage() {
        if(!errorMessageStack.empty()) {
            return errorMessageStack.pop();
        } else return "";
    }

    /**
     * Build & add a new cell, corresponding to view cell at address address, from theData to theSheet
     * @param address
     * @param theData
     */
    public void addData(String address, String theData) throws IOException {
        XLCell xlcell = null;
        if(theData.length() > 0 && theData.charAt(0) == '#') // the following will be a valid string
        {
            // input in box is a string, prefixed by '#'
            xlcell = new XLCell<String>(theData.substring(1), e -> 0.0, () -> theData.substring(1));
            theSheet.put(address, xlcell);
        } else { // otherwise it is an expression.. let the recursive descent parser do it's magic
            ExprParser ep = new ExprParser();
            Expr exp = ep.build(theData);  // build expression from input
            XLCell xlc = new XLCell<Expr>(exp,exp::value, () -> address);
            theSheet.put(address, new XLCell<Expr>(exp, (e) -> {
                System.out.println("Hello from lambda! This son'o'abitch should throw");
                throw new XLException("Circular reference error");
            }, () -> "Error! Circular reference at: " + address)); // pass in method reference

                try {
                    xlc.getValue(this);
                } catch (XLException xle) {
                    errorMsg = "Erroneous input.";
                    System.out.println("Error parsing expression! " + xle.getMessage());
                    removeData(address);
                    return;
                } catch (NullPointerException npe) {
                    errorMsg = "No cell found";
                    System.out.println("Nullpointer exception!" + npe.getMessage());
                    removeData(address);
                    return;
                }
            theSheet.put(address, xlc);
            }
        theSheet.entrySet().forEach(System.out::println);
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
    public double value(String address) {
        if(theSheet.get(address) == null) {
            throw new XLException(String.format("Cell %s does not exist.", address));
        }
        return theSheet.get(address).getValue(this);
    }

    public Set<Map.Entry<String, XLCell>> getEntries() {
        return theSheet.entrySet();
    }

    public String getStringExpression(String address) {
        XLCell cell = theSheet.get(address);
        if(cell == null) {
            System.out.print("FUCUCUAUSCUUASC");
            return "";
        }
        try {
            return cell.toString();
        } catch (XLException xle) {
            return "Error";
        }
    }

    public String cellValueToString(String address) {
        if(theSheet.get(address) == null) {
            return "";
        } else
        {

        }
        return null; // unimplemented!
    }

     /**
     * methods:
     * removeCell(String address), unregister ViewElement <- XLSheet.XLCell, remove cell from XLSheet
     * getData(XLCell cell),
     * clear(), called from gui.menu.ClearMenuItem // for all elements: null
     * save(), save theSheet
     * load(), load theSheet with data from util.XLBufferedReader (might be moved to another pkg, according to assignment)
     * validateInput(), check for errors, circular dependencies, etc. Read assignment material for more info
     * as this method will "walk" over the cells, in order to validate for example an expression, we should be able
     * to pass along a function that, depending on what we're after, maps cellcontent -> updating notifyList
     * Since our XLCell will either be an abstract class, or an interface definining behavior instead of inheritance,
     * a static builder class is in it's place. Using the cStrategy pattern (in which case XLCell will be an interface)
     * one can define different behaviors, depending on "who" is calling the methods, that the interface require the class to have.
     * According to design meeting prep questions, using the cStrategy pattern will help with dealing with circular dependencies.
     * This will be achieved by changing the behavior of the XLCell, while "walking" over an expression. So if there is a circular dep.
     * when the "walk over"-algorithm reaches back to the first cell again, it can throw an exception, or take a different if branch,
     * or something to that effect.
    **/

}


