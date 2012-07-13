/**
 * Use attribution to evaluate an expression.
 */
object Evaluator {
        
    import org.kiama.attribution.Attribution.attr
    import Syntax._

    val value : Exp => Int =
        attr {
            case Num (i)    => i
            case Add (l, r) => (l->value) + (r->value)
            case Mul (l, r) => (l->value) * (r->value)
        }

}
