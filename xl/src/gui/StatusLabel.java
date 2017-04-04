package gui;

import model.XLSheet;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class StatusLabel extends ColoredLabel implements Observer {
    XLSheet xlSheet;
    public StatusLabel(XLSheet xlSheet) {
        super("", Color.WHITE);
        this.xlSheet = xlSheet;
        xlSheet.addObserver(this);
    }

    public void update(Observable observable, Object arg) {
        if(arg instanceof String) setText((String)arg);
        else setText(xlSheet.getLastErrorMessage());
    }
}