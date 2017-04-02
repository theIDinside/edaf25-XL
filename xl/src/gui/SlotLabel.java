package gui;

import java.awt.Color;

public class SlotLabel extends ColoredLabel {
    String address; // "A1" or "G7" etc..
        // reference to Sheet?
    public SlotLabel() {
        super("                    ", Color.WHITE, RIGHT);
    }
    // needs to register with corresponding cell in Sheet
    // create methods for handling mouse input (clicks)

}