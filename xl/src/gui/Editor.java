package gui;

import model.XLCurrentCell;
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
    private XLCurrentCell editCell;
    public Editor(XLCurrentCell currentCell, XLSheet xlSheet) {
        this.xlSheet = xlSheet;
        this.editCell = currentCell;
        addActionListener(this);
        editCell.addObserver(this);
        setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = this.getText();
        if(input.equals("")) {
            xlSheet.removeData(editCell.getCurrentAddress());
            setText("");
            editCell.setText("");
        }
        else {
            try {
                xlSheet.addData(editCell.getCurrentAddress(), input);
                setText(String.valueOf(xlSheet.getCell(editCell.getCurrentAddress())));
                if(xlSheet.hasCell(editCell.getCurrentAddress())) {
                    update(null, input);
                    editCell.setText(xlSheet.getNotEvalContent(editCell.getCurrentAddress()));
                }
                else setText("");
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            } catch (XLException xle) {
                System.out.println("XLException caught. What to do now?.. Hmm..?");
            }
            catch (Exception ex)
            {   // default
                System.out.println(ex.toString());
            }
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        if(xlSheet.hasCell(editCell.getCurrentAddress()))
            setText(String.valueOf(xlSheet.getCell(editCell.getCurrentAddress())));
        else
            setText("");
    }
}