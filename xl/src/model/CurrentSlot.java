package model;

import gui.SlotLabel;
import java.awt.*;
import java.util.Observable;

/**
 * Created by cx(theIDinside) on 2017-04-03.
 */
public class CurrentSlot extends Observable {
    private SlotLabel watchedLabel;
    public CurrentSlot() {

    }
    public void setObserver(SlotLabel sl) {
        watchedLabel = sl;
        setChanged();
        notifyObservers();
        addObserver(sl);
    }

    public void setText(String str) {
        watchedLabel.setText(str);
        setChanged();
        notifyObservers();
    }

    public void restoreLook() { watchedLabel.setBackground(Color.WHITE);}
    public String toString() { return watchedLabel.toString(); }

    public String getCurrentAddress() {
        return watchedLabel.getAddress();
    }
}
