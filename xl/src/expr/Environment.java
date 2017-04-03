package expr;
// A functional interface... which seems to have flown "over the original creator's head". Or maybe
// it was pre-lambda Java
public interface Environment {
    double value(String address);
}