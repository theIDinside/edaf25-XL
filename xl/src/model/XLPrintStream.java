package model;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Map.Entry;
import java.util.Set;

public class XLPrintStream extends PrintStream {
    public XLPrintStream(String fileName) throws FileNotFoundException {
        super(fileName);
    }

    public void save(Set<Entry<String, SlotInterface>> set) {
        set.forEach((e) -> println(e.getKey() + "=" + e.getValue())); // ooh there's something sexy about lambdas & Java 1.8
        flush();
        close();
    }
}
