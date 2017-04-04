package gui;

import model.XLCell;
import model.XLCurrentCell;
import model.XLSheet;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements MouseListener, Observer {
    String address; // "A1" or "G7" etc..
    private XLSheet xlSheet;
    XLCurrentCell currentCell;

    public String getAddress() {
        return address;
    }

    // reference to Sheet?
    // public static String clickAddress = null;
    public SlotLabel(String addr, XLCurrentCell current, XLSheet xlSheet) {
        super("                    ", Color.WHITE, RIGHT);
        // super(addr, Color.WHITE, RIGHT); // for testing purposes
        this.xlSheet = xlSheet;
        this.address = addr;
        addMouseListener(this);
        currentCell = current;
        xlSheet.addObserver(this);
        if(xlSheet.getEntries().contains(address))
            xlSheet.addObserver(this);
        // if we register _all_ slotlabels here, with xlSheet, then *every* slotlabel
        // will be notified and updated every time a change has been made. If we hade 10 000 x 10 000 cells,
        // that would lead up to 100 000 000 (one hundred million) notifications... murder death kill.

    }

    public void attach() {

    }

    public void detach() {
        xlSheet.deleteObserver(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        currentCell.restoreLook();
        this.setBackground(Color.CYAN);
        System.out.print(address);
        currentCell.setObserver(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void update(Observable o, Object arg) {
        String viewText = Double.toString(xlSheet.value(address));
        setText(viewText);
        currentCell.deleteObserver(this);
    }

    @Override
    public String toString() { return address; }

    public void attachTo(XLSheet modelData) {
        modelData.registerViewElement(this);
    }
    // needs to register with corresponding cell in Sheet
    // create methods for handling mouse input (clicks)

}