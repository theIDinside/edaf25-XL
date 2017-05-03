package model;

import expr.Environment;
import expr.Expr;

/**
 * Created by cx on 5/3/17.
 */
public class ExpressionSlot implements SlotInterface {
    Expr exp;

    ExpressionSlot(Expr expr) {
        exp = expr;
    }

    @Override
    public double value(Environment e) {
        return exp.value(e);
    }

    @Override
    public String display(Environment e) {
        return Double.toString(value(e));
    }

    @Override
    public String toString() {
        return exp.toString();
    }
}
