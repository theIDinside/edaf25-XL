package gui.menu;

import gui.CurrentSlot;
import model.SlotSheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

class ClearMenuItem extends JMenuItem implements ActionListener {
    SlotSheet sheet;
    CurrentSlot currentSlot;
    public ClearMenuItem(SlotSheet slotSheet, CurrentSlot currentSlot) {
        super("Clear");
        addActionListener(this);
        this.sheet = slotSheet;
        this.currentSlot = currentSlot;
    }

    public void actionPerformed(ActionEvent e) {
        sheet.removeData(currentSlot.getCurrentAddress());
    }
}