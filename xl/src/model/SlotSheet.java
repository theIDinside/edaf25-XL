package model;

import expr.Environment;
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

public class SlotSheet extends Observable implements Environment {
    HashMap<String, SlotInterface> theSheet;
    // this could possibly be Map<Variable, Slot>
    public String statusMessage = "";
    private String lastErrorMessage;
    public SlotSheet() {
        theSheet = new HashMap<>();
    }
    public String getLastErrorMessage() {
        return lastErrorMessage;
    }
     // public void setLastErrorMessage(String msg) { lastErrorMessage = msg; }
    public void setErrorMsg(String address, String errorMsg) {
        statusMessage = "Error at: " + address + " " + errorMsg;
        setChanged();
        notifyObservers();
    }
    public SlotInterface getSlot(String addr) {
        if(theSheet.containsKey(addr)) return theSheet.get(addr);
        else return null;
    }
    public void addData(String address, String data) throws IOException, XLException {
        SlotInterface slot;
        try {
            slot = SlotBuilder.BuildSlot(data);
        } catch (XLException | NumberFormatException nfe) {
            System.err.println(nfe.getMessage());
            throw new XLException(nfe.getMessage());
        }
        if(validateSlot(address, slot)) {
            theSheet.put(address, slot);
            if(statusMessage.contains(address)) {
                statusMessage = "";
            }
        } else {
            statusMessage = "Error at: " + address + " " + getLastErrorMessage();
        }
        setChanged();
        notifyObservers();
    }
    private boolean validateSlot(String address, SlotInterface slot) {
        SlotInterface currentSlot = theSheet.get(address);
        theSheet.put(address, new PlaceHolderSlot());
        try {
            slot.value(this);
        } catch (XLException | NullPointerException exception) { // if slot.value(this) fails
            statusMessage = "Erroneous input " + exception.getMessage();
            lastErrorMessage = exception.getMessage();
            theSheet.put(address, currentSlot); // put back old slot if there was one before
            return false;
        }
        return true;
    }
    public void removeData(String address) {
        if(theSheet.containsKey(address)) {
            theSheet.remove(address);
            setChanged();
            notifyObservers();
        }
    }
    public void clear() {
        theSheet = new HashMap<>(); // empty & update
        setChanged();
        notifyObservers();
    }
    public HashMap<String, SlotInterface> getData() {
        return theSheet;
    }
    public void loadDataFrom(HashMap<String, SlotInterface> map) {
        System.out.println("Loading data...");
        Map<String, SlotInterface> temp = theSheet; // make temporary backup of "old" sheet
        Iterator<Map.Entry<String, SlotInterface>> itr = map.entrySet().iterator(); // iterator over entries..
        theSheet = map;
        boolean isOk = true;
        while(isOk && itr.hasNext()) {
            Map.Entry<String, SlotInterface> entry = itr.next();
            String key = entry.getKey();
            SlotInterface value = entry.getValue();
            map.put(key, new PlaceHolderSlot()); // "dereference" this to see if the loaded map has circular references
            try {
                if (validateSlot(key, value)) {
                    theSheet.put(key, value);
                } else {
                    theSheet = (HashMap<String, SlotInterface>) temp;
                    isOk = false;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        setChanged();
        notifyObservers();
        statusMessage="";
    }
    @Override
    public double value(String address) throws NullPointerException, NumberFormatException {
        if(theSheet.get(address) == null) {
            throw new XLException(String.format("Cell %s does not exist.", address));
        }
            return theSheet.get(address).value(this);
    }
    public boolean hasCell(String address) {
        return theSheet.get(address) != null;
    }
    public String getCellTextValue(String address) {
        if(theSheet.containsKey(address))
            try {
            return theSheet.get(address).textValue(this);
        } catch (NullPointerException xlnpe) {
            // if the "dereferencing" while _loading from files_ and throwing exceptions defined in expr, catch here
            setErrorMsg(address, xlnpe.getMessage());
            throw xlnpe; // re-throw the exception to be handled somewhere else.
        }
        else return "";
    }
}


