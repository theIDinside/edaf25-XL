package gui;

import javafx.beans.Observable;
import model.XLCurrentCell;
import model.XLSheet;

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
        System.out.print("Action performed by editor");
        if(input.equals("")) {
            xlSheet.removeData(""); // <- change this!
        }
        else {
            if(input != null) System.out.println("Input:" + input);
            try {
                xlSheet.addData(editCell.getAddress(), input);
            } catch (IOException ioe) {
                ioe.getMessage();
            }

            update(null, input);
/*
            xlSheet.addObserver(
                    xlSheet.slotLabelAt(
                    editCell.getAddress()
                    )
            );*/
        }
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
           setText(xlSheet.getStringExpression(editCell.toString()));
    }
}