package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class StatusPanel extends BorderPanel {
    protected StatusPanel(StatusLabel statusLabel, CurrentSlot currentCell) {
        add(WEST, new CurrentLabel(currentCell));
        add(CENTER, statusLabel);
    }
}