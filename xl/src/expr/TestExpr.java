package expr;

import java.io.IOException;

public class TestExpr {
    public static void main(String[] args) {
        ExprParser parser = new ExprParser();
        try {
            Expr expr = parser.build("1+2*3");
            System.out.println("toString(0): " + expr.toString(0));
            System.out.println("value(null): " + expr.value(null));
            expr = parser.build("A3+A2*A1");

            Environment env = address -> {
                if (address.equals("A3"))  // <- this is where variable v "ends up", since we're passing the lambda object of type Environment
                    return 1;           // to the value() method of variable v later
                if (address.equals("A2"))
                    return 2;
                if (address.equals("A1"))
                    return 3;
                System.out.println(address + " is undefined");
                return 0;
            };
            System.out.println("Expr.toString():" + expr.toString() + " Expr.value(env: E):" + expr.value(env));
            // usage of variable, depends on an environment actually holding an A1
            Variable v = new Variable("A3"); // variable v.value(
            System.out.println("Variable A3: " + v.toString(0) +":"+ v.value(env));
            System.out.println(expr);
            System.out.println(expr.value(env));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}