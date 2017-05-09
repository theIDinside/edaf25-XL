package gui;

import model.SlotSheet;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

public class SlotLabel extends ColoredLabel implements MouseListener, Observer {
    String address; // "A1" or "G7" etc..
    private SlotSheet slotSheet;
    private CurrentSlot currentCell;

    public String getAddress() {
        return address;
    }

    public SlotLabel(String addr, CurrentSlot current, SlotSheet slotSheet) {
        super("                    ", Color.WHITE, RIGHT);
        this.slotSheet = slotSheet;
        this.address = addr;
        addMouseListener(this);
        currentCell = current;
        slotSheet.addObserver(this);
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
        if(slotSheet.hasCell(address)) { // för att inte få nullpointer exceptions
            String data = slotSheet.getCellTextValue(address);
            setText(data);
        }
        else setText("");
        currentCell.deleteObserver(this);
    }

    @Override
    public String toString() { return address; }

}