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
        if(countObservers() > 0)
        notifyObservers();
        setChanged();
        addObserver(sl);
    }

    public void setText(String str) {
        watchedLabel.setText(str);
        setChanged();
        notifyObservers();
    }
    public void setErrorText(String str) {
        watchedLabel.setText(str);
        watchedLabel.setBackground(Color.RED);
    }
    public void restoreLook() { watchedLabel.setBackground(Color.WHITE);}
    public String toString() { return watchedLabel.toString(); }

    public String getCurrentAddress() {
        return watchedLabel.getAddress();
    }
}
