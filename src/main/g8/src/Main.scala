import org.kiama.util.CompilerBase
import syntax.PrettyPrinter
import syntax.Syntax.Exp

object Main extends CompilerBase[Exp] with PrettyPrinter {

    import Evaluator.expvalue
    import java.io.Reader
    import Optimiser.optimise
    import org.kiama.attribution.Attribution.initTree
    import org.kiama.util.{Console, Emitter}
    import org.kiama.util.Messaging._
    import syntax.ExpParser

    def makeast (reader : Reader, filename : String, emitter : Emitter) : Either[Exp,String] = {
        val p = new ExpParser (reader, filename)
        val pr = p.pExp (0)
        if (pr.hasValue)
            Left (p.value (pr).asInstanceOf[Exp])
        else
            Right (p.format (pr.parseError))
    }

    def process (e : Exp, console : Console, emitter : Emitter) : Boolean = {
        println ("e = " + e)
        println ("e tree:")
        println (pretty_any (e))
        println ("e tree pretty printed:")
        println (pretty (e))
        println ("value (e) = " + expvalue (e))
        val o = optimise (e)
        println ("e optimised = " + o)
        println ("value (e optimised) = " + expvalue (o))
        true
    }

}
