package gui;

import javafx.beans.Observable;
import model.XLCurrentCell;

import java.awt.Color;
import java.util.Observer;

public class CurrentLabel extends ColoredLabel implements Observer {
    XLCurrentCell currentCell;
    public CurrentLabel(XLCurrentCell currentCell) {
        super("A1", Color.WHITE);
        this.currentCell = currentCell;
        currentCell.addObserver(this);
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        this.setText(currentCell.toString());
    }
}