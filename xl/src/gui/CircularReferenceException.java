package gui;

import expr.Expr;
import model.Slot;

/**
 * Created by cx on 2017-04-07.
 */
public class CircularReferenceException extends RuntimeException {
    String address;
    Expr expr;
    Slot oldSlot;
    public CircularReferenceException(String message) {
        super(message);
    }
    public CircularReferenceException(String message, String address) {
        super(message);
        this.address = address;
    }
    public CircularReferenceException(String message, String address, Expr expr) {
        super(message);
        this.address = address;
        this.expr = expr;
    }
    public CircularReferenceException(String message, String address, Expr exceptionGeneratingExpr, Slot oldSlot) {
        super(message);
        this.address = address;
        this.expr = exceptionGeneratingExpr;
    }

    public String getFaultyExpression() { return expr.toString(); }
    public Slot getLastSlot() { return oldSlot; }
    public String getLastAddress() { return address; }

}
