In the comments of ExprParser it says this;
 ``` Java
 /*
 *    expr ::= term {addop term}
 *    term ::= factor {mulop factor}
 *    factor ::= number | variable | "(" expr ")"
 *    addop ::= "+" | "-"
 *    mulop ::= "*" | "/"
 */
 ```
 According to SO, this symbolizes 'symbol derivation rule', and ::= is taken to be meaningm "is defined as".
 So that would explain these comments as such:
 expr is defined as a term {+ terms}, where {} are expressions that can be repeated arbitrarily, or not at all.
So... again; it means that
expr is a term { an arbitrary (+ operations) amount of terms }

These are basically computer science grammar rules. These are not inherently necessary to understand or even know, but 
they do explain how and why ExprParser and Expr and all the operations work.


http://en.wikipedia.org/wiki/Backus%E2%80%93Naur_Form
