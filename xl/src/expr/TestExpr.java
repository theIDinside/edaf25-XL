package expr;

import java.io.IOException;

public class TestExpr {
    public static void main(String[] args) {
        ExprParser parser = new ExprParser();
        try {
            Expr expr = parser.build("1+2*3");
            System.out.println("toString(0): " + expr.toString(0));
            System.out.println("value(null): " + expr.value(null));
            if(expr instanceof BinaryExpr) System.out.print("is type of BinaryExpr \n");
            if(expr instanceof Mul) System.out.print("is type of Sub\n");
            System.out.println("typeof: " + expr.value(null));
            expr = parser.build("A3+A2*A1");
            Expr expr2 = parser.build("((A3+A2)*A1+14-A1)*15");
            Add add = new Add(expr, expr2);
            System.out.println("Expression toString, with higher precedence: " + expr.toString(1));
            Environment env = name -> {
                if (name.equals("A3"))  // <- this is where variable v "ends up", since we're passing the lambda object of type Environment
                    return 1;           // to the value() method of variable v later
                if (name.equals("A2"))
                    return 2;
                if (name.equals("A1"))
                    return 3;
                System.out.println(name + " is undefined");
                return 0;
            };
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