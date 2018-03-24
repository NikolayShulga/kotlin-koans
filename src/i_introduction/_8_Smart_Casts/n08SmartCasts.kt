package i_introduction._8_Smart_Casts

import util.TODO
import util.doc8

// 'sealed' modifier restricts the type hierarchy:
// all the subclasses must be declared in the same file
sealed class Expr
class Num(val value: Int) : Expr()
class Sum(val left: Expr, val right: Expr) : Expr()

fun eval(e: Expr): Int {
        when (e) {
            is Num -> return e.value

            is Sum -> return eval(e.left) + eval(e.right)
        }
    return 0
}


/*Nothing = TODO(
    """
        Task 8.
        Complete the implementation of the 'eval' function above using smart casts and 'when' expression.
        The 'JavaCode8.eval' method provides the similar functionality written in Java.
    """,
    documentation = doc8(),
    references = { JavaCode8().eval(expr) })
*/
/*
public class JavaCode8 extends JavaCode {
    public int eval(Expr expr) {
        if (expr instanceof Num) {
            return ((Num) expr).getValue();
        }
        if (expr instanceof Sum) {
            Sum sum = (Sum) expr;
            return eval(sum.getLeft()) + eval(sum.getRight());
        }
        throw new IllegalArgumentException("Unknown expression");
    }
}
 */