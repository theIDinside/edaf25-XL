package gui;

import model.CurrentSlot;
import model.XLSheet;
import util.XLException;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observer;
import javax.swing.JTextField;

public class Editor extends JTextField implements ActionListener, Observer {
    private XLSheet xlSheet;
    private CurrentSlot editSlot;
    public Editor(CurrentSlot currentSlot, XLSheet xlSheet) {
        this.xlSheet = xlSheet;
        this.editSlot = currentSlot;
        addActionListener(this);
        editSlot.addObserver(this);
        setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = this.getText();
        if(input.equals("")) {
            xlSheet.removeData(editSlot.getCurrentAddress());
            setText("");
        }
        else {
            try {
                xlSheet.addData(editSlot.getCurrentAddress(), input);
                setText(String.valueOf(xlSheet.getCell(editSlot.getCurrentAddress())));
                if(xlSheet.hasCell(editSlot.getCurrentAddress())) {
                    update(null, input);
                    editSlot.setText(xlSheet.getNotEvalContent(editSlot.getCurrentAddress()));
                }
                else setText("");
            } catch (IOException | XLException ioe) {
                System.out.println(ioe.getMessage());
            } catch (CircularReferenceException cre) {
                xlSheet.forcePutSlot(cre.getLastAddress(), cre.getLastSlot());
                editSlot.setErrorText("Error");
                xlSheet.notifyObservers();
                /* We need to update:
                    status panel
                    SlotLabel (print something like "ERROR!" in the slot);
                 */
            } catch (NumberFormatException ignored) {
                System.out.println(ignored.getMessage());
            } catch (NullPointerException npe) {
                System.out.print(npe.getCause() + npe.getMessage());
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(xlSheet.hasCell(editSlot.getCurrentAddress()))
            setText(String.valueOf(xlSheet.getCell(editSlot.getCurrentAddress())));
        else
            setText("");
    }
}