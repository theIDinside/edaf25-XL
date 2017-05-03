package gui.menu;

import model.SlotSheet;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

class ClearAllMenuItem extends JMenuItem implements ActionListener {
    SlotSheet slotSheet;
    public ClearAllMenuItem(SlotSheet slotSheet) {
        super("Clear all");
        addActionListener(this);
        this.slotSheet = slotSheet;
    }

    public void actionPerformed(ActionEvent e) {
        slotSheet.clear();
    }
}