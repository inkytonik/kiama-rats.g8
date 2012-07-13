/**
 * Use attribution to evaluate an expression.
 */
object Evaluator {
        
    import org.kiama.attribution.Attribution.attr
    import syntax.Syntax._

    val expvalue : Exp => Int =
        attr {
            case Num (i)    => i
            case Add (l, r) => (l->expvalue) + (r->expvalue)
            case Mul (l, r) => (l->expvalue) * (r->expvalue)
        }

}
