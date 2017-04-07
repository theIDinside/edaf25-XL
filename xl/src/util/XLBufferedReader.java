package util;

import expr.Expr;
import expr.ExprParser;
import model.Slot;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

//TODO move to another package
public class XLBufferedReader extends BufferedReader {
    public XLBufferedReader(String name) throws FileNotFoundException {
        super(new FileReader(name));
    }

    // TODO Change Object to something appropriate
    public void load(Map<String, Slot> map) {
        try {
            while (ready()) {
                String string = readLine();
                String[] token = string.split("=");
                String name = token[0];
                String content = token[1];
                Expr e = new ExprParser().build(content);
                // map.put(name, new Slot(e, ));
            }
        } catch (Exception e) {
            throw new XLException(e.getMessage());
        }
    }
}
