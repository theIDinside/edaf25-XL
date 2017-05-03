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
        // ooh lambdas are sexy
        set.forEach((e) -> println(e.getKey() + "=" + e.getValue()));
        flush();
        close();
    }
}
