package util;

import expr.Expr;
import expr.ExprParser;

import java.io.IOException;

/**
 * Created by cx on 2017-03-31.
 */
public class ExprParserException extends IOException {
    public ExprParserException(String errorMessage) { super(errorMessage); }
}
