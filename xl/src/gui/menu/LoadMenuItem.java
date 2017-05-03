package gui.menu;

import gui.StatusLabel;
import gui.XL;
import model.SlotInterface;
import model.XLBufferedReader;
import util.XLException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;

class LoadMenuItem extends OpenMenuItem {
 
    public LoadMenuItem(XL xl, StatusLabel statusLabel) {
        super(xl, statusLabel, "Load");
    }

    protected void action(String path) throws FileNotFoundException {
        XLBufferedReader bufferedReader = null;
        HashMap<String, SlotInterface> oldMapData = xl.getMapData();
        xl.getMapData().clear();
        try {
            bufferedReader = new XLBufferedReader(path);
        } catch (FileNotFoundException fnfe) {
            statusLabel.setText(fnfe.getMessage());
        }
        HashMap<String, SlotInterface> slotMap = new HashMap<>();
        try {
            bufferedReader.load(slotMap); // load content into slotMap
            for(Map.Entry<String, SlotInterface> e : slotMap.entrySet()) {
                System.out.println(e.getKey() +":"+ e.getValue());
            }
        } catch (XLException | NullPointerException xle) {
            String errorMessage = String.format("Error while loading content from file: %s", xle.getMessage());
            statusLabel.setText(errorMessage);
            xl.setMapData(oldMapData);
            return;
        }
        xl.setMapData(slotMap);
    }

    protected int openDialog(JFileChooser fileChooser) {
        return fileChooser.showOpenDialog(xl);
    }
}