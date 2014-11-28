/**
 * Use attribution to evaluate an expression.
 */
object Evaluator {

    import org.kiama.attribution.Attribution.attr
    import syntax.ExpParserSyntax._

    val expvalue : Exp => Int =
        attr {
            case Num (i)    => i
            case Add (l, r) => expvalue (l) + expvalue (r)
            case Mul (l, r) => expvalue (l) * expvalue (r)
        }

}
