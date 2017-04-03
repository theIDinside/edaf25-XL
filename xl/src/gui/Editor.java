package gui;

import model.XLSheet;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;

public class Editor extends JTextField implements ActionListener {
    private XLSheet xlSheet;
    public Editor(XLSheet xlSheet) {
        this.xlSheet = xlSheet;
        setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = this.getText();
        if(input.equals(""));
            // remove what is in Cell
        else {
            // add input into XLSheet, creating a XLCell in the process
            // validate the input, into either:
            //      A. A string, holding some text
            //      B. Expression
            //      C. Real value
        }
    }
}