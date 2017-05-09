package gui;

import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import gui.menu.XLMenuBar;
import model.SlotInterface;
import model.SlotSheet;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class XL extends JFrame implements Printable {
    private static final int ROWS = 10, COLUMNS = 8;
    private XLCounter counter;
    // lift out variables
    private SlotSheet slotSheet;
    private CurrentSlot currentSlot;
    private XLList xlList;

    public XL(XL oldXL) {
        this(oldXL.xlList, oldXL.counter);
    }

    public XL(XLList xlList, XLCounter counter) {
        super("Untitled-" + counter);
        this.xlList = xlList;
        this.counter = counter;
        xlList.add(this);
        currentSlot = new CurrentSlot();
        counter.increment();

        slotSheet = new SlotSheet();
        StatusLabel statusLabel = new StatusLabel(slotSheet);
        JPanel statusPanel = new StatusPanel(statusLabel, currentSlot);
        JPanel sheetPanel = new SheetPanel(ROWS, COLUMNS, currentSlot, slotSheet);
        Editor editor = new Editor(currentSlot, slotSheet);
        add(NORTH, statusPanel);
        add(CENTER, editor);
        add(SOUTH, sheetPanel);
        setJMenuBar(new XLMenuBar(this, xlList, statusLabel, slotSheet, currentSlot));
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public int print(Graphics g, PageFormat pageFormat, int page) {
        if (page > 0)
            return NO_SUCH_PAGE;
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        printAll(g2d);
        return PAGE_EXISTS;
    }

    public void setMapData(HashMap<String, SlotInterface> mapData) {
        slotSheet.loadDataFrom(mapData);
    }
    public HashMap<String, SlotInterface> getMapData() {
        return slotSheet.getData();
    }

    public void rename(String title) {
        setTitle(title);
        xlList.setChanged();
    }

    public static void main(String[] args) {
        new XL(new XLList(), new XLCounter());
    }
}