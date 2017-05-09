package gui;

import model.SlotSheet;
import util.XLException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observer;
import javax.swing.JTextField;

public class Editor extends JTextField implements ActionListener, Observer {
    private SlotSheet slotSheet;
    private CurrentSlot editSlot;
    public Editor(CurrentSlot currentSlot, SlotSheet slotSheet) {
        this.slotSheet = slotSheet;
        this.editSlot = currentSlot;
        addActionListener(this);
        editSlot.addObserver(this);
        setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = this.getText();
        String address = editSlot.getCurrentAddress();
        if(input.equals("")) {
            slotSheet.removeData(address);
            setText("");
        }
        else {
            try {
                slotSheet.addData(address, input);
                setText(String.valueOf(slotSheet.getSlot(address)));
                if(slotSheet.hasCell(address)) {
                   editSlot.setText(slotSheet.getCellTextValue(address));
                }
                else setText("");
            } catch (IOException | XLException | NumberFormatException ioe) {
                slotSheet.setErrorMsg(address, ioe.getMessage());
            } catch (NullPointerException npe) {
                System.out.print(npe.getCause() + npe.getMessage());
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        String address = editSlot.getCurrentAddress();
        if(slotSheet.hasCell(address))
            setText(String.valueOf(slotSheet.getSlot(address)));
        else
            setText("");
    }
}