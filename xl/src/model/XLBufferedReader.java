package model;

import util.XLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.stream.Stream;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<String, SlotInterface> map) {
        try {
        while (ready()) {
                String[] string = readLine().split("=");
                // här "försvinner" = tecken, om det finns flera =tecken på en rad.
                // Antagandet är dock att filerna är korrekt uppbyggda.
                map.put(string[0], SlotBuilder.BuildSlot(string[1]));
        }
        } catch (Exception e) {
            throw new XLException("Error: " + e.getMessage());
        }
    }
}
