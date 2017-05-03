package gui;

import model.CurrentSlot;
import model.SlotSheet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements MouseListener, Observer {
    String address; // "A1" or "G7" etc..
    private SlotSheet slotSheet;
    CurrentSlot currentCell;

    public String getAddress() {
        return address;
    }

    public SlotLabel(String addr, CurrentSlot current, SlotSheet slotSheet) {
        super("                    ", Color.WHITE, RIGHT);
        // super(addr, Color.WHITE, RIGHT); // for testing purposes
        this.slotSheet = slotSheet;
        this.address = addr;
        addMouseListener(this);
        currentCell = current;
        slotSheet.addObserver(this);
        // if we register _all_ slotlabels here, with slotSheet, then *every* slotlabel
        // will be notified and updated every time a change has been made. If we hade 10 000 x 10 000 cells,
        // that would lead up to 100 000 000 (one hundred million) notifications... murder death kill.
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        currentCell.restoreLook();
        currentCell.setObserver(this);
        this.setBackground(Color.ORANGE);
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
        if(slotSheet.hasCell(address)) {
            String data = slotSheet.display(address);
            setText(data);
            if(getText().contains("----"))
            this.setBackground(Color.RED);
        }
        else setText("");
        currentCell.deleteObserver(this);
    }

    @Override
    public String toString() { return address; }

}