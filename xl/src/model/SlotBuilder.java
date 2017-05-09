package model;

import expr.Expr;
import expr.ExprParser;
import util.XLException;

import java.io.IOException;

/**
 * Created by cx on 5/3/17.
 */
public class SlotBuilder {
    public static SlotInterface BuildSlot(String data) throws XLException {
        if(data.charAt(0) == '#') {
            return new TextSlot(data);
        }
        try {
            return new ExpressionSlot(new ExprParser().build(data));
        }  catch (IOException e) {
            throw new XLException("Error parsing: " + e.getMessage());
        } catch (XLException xle) {
            throw new XLException(xle.getMessage()); // trailing garbage exception måste kastas explicit här ifrån
        }
    }
}
