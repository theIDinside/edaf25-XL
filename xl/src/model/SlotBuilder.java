package model;

import expr.Expr;
import expr.ExprParser;
import util.XLException;

import java.io.IOException;

/**
 * Created by cx on 5/3/17.
 */
public class SlotBuilder {
    public static SlotInterface BuildSlot(String data) {
        if(data.charAt(0) == '#') {
            // return textslot
            return new TextSlot(data);
        }
        try {
            Expr expr = new ExprParser().build(data);
            ExprSlot es = new ExprSlot(expr);
            return es;
        }  catch (IOException e) {
            throw new XLException("Error parsing: " + e.getMessage());
        }
    }
}
