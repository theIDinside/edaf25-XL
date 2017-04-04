package model;

import gui.SlotLabel;
import java.awt.*;
import java.util.Observable;

/**
 * Created by cx on 2017-04-03.
 */
public class XLCurrentCell extends Observable {
    private SlotLabel watchedLabel;
    public XLCurrentCell() {

    }
    public void setObserver(SlotLabel sl) {
        watchedLabel = sl;
        setChanged();
        notifyObservers();
        addObserver(sl);
    }

    public void restoreLook() { watchedLabel.setBackground(Color.WHITE);}
    public String toString() { return watchedLabel.toString(); }

    public String getAddress() {
        return watchedLabel.getAddress();
    }
}
