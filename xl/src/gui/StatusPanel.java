package gui;

import model.XLCurrentCell;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
    protected StatusPanel(StatusLabel statusLabel, XLCurrentCell currentCell) {
        add(WEST, new CurrentLabel(currentCell));
        add(CENTER, statusLabel);
    }
}