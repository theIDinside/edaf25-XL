package gui;

import java.awt.Color;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer {
    CurrentSlot currentCell;
    public CurrentLabel(CurrentSlot currentCell) {
        super("A1", Color.WHITE);
        this.currentCell = currentCell;
        currentCell.addObserver(this);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        this.setText(currentCell.toString());
    }
}