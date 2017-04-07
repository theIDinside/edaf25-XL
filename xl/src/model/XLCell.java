package model;

import expr.Environment;

/**
 * Created by cx on 2017-04-05.
 */
public interface XLCell {
    double value(Environment env);
    String toString();
    String stringFrom(Environment env);
}
