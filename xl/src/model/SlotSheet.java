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
    String lastMessage;
    public SlotSheet() {
        theSheet = new HashMap<>();
    }

    public String getLastErrorMessage() {
        return lastMessage;
    }
    public void setLastErrorMessage(String msg) { lastMessage = msg; }
    public void setErrorMsg(String address) {
        statusMessage = "Error at: " + address + " " + getLastErrorMessage();
    }

    public SlotInterface getSlot(String addr) {
        if(theSheet.containsKey(addr)) return theSheet.get(addr);
        else return null;
    }

    public void addData(String address, String data) throws IOException, XLException {
        SlotInterface slot;
        try {
            slot = SlotBuilder.BuildSlot(data);
        } catch (NumberFormatException nfe) {
            System.out.println("Number format exception!");
            throw nfe;
        }
        if(validateSlot(address, slot)) {
            theSheet.put(address, slot);
            if(statusMessage.contains(address)) {
                statusMessage = "";
            }
        } else {
            StringBuilder errorMessageStringBuilder = new StringBuilder();
            errorMessageStringBuilder.append("Error at: ").append(address).append(" ").append(getLastErrorMessage());
            statusMessage = errorMessageStringBuilder.toString();
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
            setLastErrorMessage(exception.getMessage());
            theSheet.put(address, currentSlot); // put back old slot if there was one before
            return false;
        }
        return true;
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
        HashMap<String, SlotInterface> temp = theSheet; // make temporary backup of "old" sheet
        Iterator<Map.Entry<String, SlotInterface>> itr = map.entrySet().iterator(); // iterator over entries..
        theSheet = map;
        boolean isOk = true;
        while(itr.hasNext() && isOk) {
            Map.Entry<String, SlotInterface> entry = itr.next();
            String key = entry.getKey();
            SlotInterface value = entry.getValue();
            map.put(key, new PlaceHolderSlot()); // "dereference" this to see if the loaded map has circular references
            try {
                if (validateSlot(key, value)) {
                    theSheet.put(key, value);
                } else {
                    theSheet = temp;
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

    public String display(String address) {
        if(theSheet.containsKey(address)) try {
            return theSheet.get(address).display(this);
        } catch (XLException | NullPointerException xlnpe) {
            // if the "dereferencing" while _loading_ from files and throwing exceptions defined in expr, handle here
            setErrorMsg(xlnpe.getMessage());
            throw xlnpe; // re-throw the exception to be handled somewhere else.
        }
        else return "";
    }
}


