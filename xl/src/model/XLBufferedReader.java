package model;

import util.XLException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    public void load(Map<String, SlotInterface> map) {
        try {
            while (ready()) {
                String[] string = readLine().split("=");
                map.put(string[0], SlotBuilder.BuildSlot(string[1]));
            }
            int howmany = 0;
            for(String s : map.keySet()) {
                howmany++;
                System.out.println(howmany + " " + s);
            }
        } catch (Exception e) {
            throw new XLException("Error: " + e.getMessage());
        }
    }
}
