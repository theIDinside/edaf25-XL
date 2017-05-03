package model;

import expr.Environment;

/**
 * Created by cx on 5/3/17.
 */
public interface SlotInterface {
    double value(Environment e);
    String display(Environment e);
    String toString();
}
