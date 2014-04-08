import org.kiama.util.{CompilerBase, Config}
import syntax.PrettyPrinter
import syntax.Syntax.Exp

object Main extends CompilerBase[Exp,Config] with PrettyPrinter {

    import Evaluator.expvalue
    import java.io.Reader
    import Optimiser.optimise
    import org.kiama.attribution.Attribution.initTree
    import org.kiama.util.{Console, Emitter}
    import syntax.ExpParser
    import scala.collection.immutable.Seq

    def createConfig (args : Seq[String], emitter : Emitter = new Emitter) : Config =
        new Config (args, emitter)

    def makeast (reader : Reader, filename : String, config : Config) : Either[Exp,String] = {
        val p = new ExpParser (reader, filename)
        val pr = p.pExp (0)
        if (pr.hasValue)
            Left (p.value (pr).asInstanceOf[Exp])
        else
            Right (p.format (pr.parseError))
    }

    override def process (filename : String, e : Exp, config : Config) {
        val emitter = config.emitter
        emitter.emitln ("e = " + e)
        emitter.emitln ("e tree:")
        emitter.emitln (pretty_any (e))
        emitter.emitln ("e tree pretty printed:")
        emitter.emitln (pretty (e))
        emitter.emitln ("value (e) = " + expvalue (e))
        val o = optimise (e)
        emitter.emitln ("e optimised = " + o)
        emitter.emitln ("value (e optimised) = " + expvalue (o))
    }

}
