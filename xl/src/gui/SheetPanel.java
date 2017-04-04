package gui;

import model.XLCurrentCell;
import model.XLSheet;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.WEST;

public class SheetPanel extends BorderPanel {
    public SheetPanel(int rows, int columns, XLCurrentCell currentCell, XLSheet xlSheet) {
        add(WEST, new RowLabels(rows));
        add(CENTER, new SlotLabels(rows, columns, currentCell, xlSheet));
    }
}