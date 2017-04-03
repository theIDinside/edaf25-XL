package gui;

import model.XLCell;
import model.XLSheet;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import static java.awt.event.InputEvent.BUTTON1_DOWN_MASK;
import static java.awt.event.InputEvent.CTRL_DOWN_MASK;
import static java.awt.event.InputEvent.SHIFT_DOWN_MASK;

public class SlotLabel extends ColoredLabel implements MouseListener, Observer {
    String address; // "A1" or "G7" etc..
    private XLSheet xlSheet;
        // reference to Sheet?
    // public static String clickAddress = null;
    public SlotLabel(String addr, XLSheet xlSheet) {
        // super("                    ", Color.WHITE, RIGHT);
        super(addr, Color.WHITE, RIGHT); // for testing purposes
        this.xlSheet = xlSheet;
        this.address = addr;
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent event) {
        this.setBackground(Color.CYAN);
        System.out.println(event.getComponent().getParent().toString());
        System.out.println(event.getComponent().getParent().getParent().toString());
        System.out.println(event.getComponent().getParent().getParent().getParent().getComponentCount());
        Component c = event.getComponent().getParent().getParent();

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

    }

    public void attachTo(XLSheet modelData) {
        modelData.registerViewElement(this);
    }

    public void update() {

    }
    // needs to register with corresponding cell in Sheet
    // create methods for handling mouse input (clicks)

}