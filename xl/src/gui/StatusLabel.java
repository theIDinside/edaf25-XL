package gui;

import model.SlotSheet;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class StatusLabel extends ColoredLabel implements Observer {
    SlotSheet slotSheet;
    public StatusLabel(SlotSheet slotSheet) {
        super("", Color.WHITE);
        this.slotSheet = slotSheet;
        slotSheet.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
            setText(slotSheet.statusMessage);
    }
}