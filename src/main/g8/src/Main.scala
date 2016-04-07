import org.kiama.util.Compiler
import syntax.ExpParserSyntax.Exp

object Main extends Compiler[Exp] {

    import Evaluator.expvalue
    import java.io.Reader
    import Optimiser.optimise
    import org.kiama.output.PrettyPrinterTypes.Document
    import org.kiama.util.Config
    import syntax.ExpParser
    import syntax.ExpParserPrettyPrinter
    import syntax.ExpParserPrettyPrinter.{any, layout}
    import scala.collection.immutable.Seq

    val parser = null

    override def makeast (reader : Reader, filename : String, config : Config) : Either[Exp,String] = {
        val p = new ExpParser (reader, filename)
        val pr = p.pExp (0)
        if (pr.hasValue)
            Left (p.value (pr).asInstanceOf[Exp])
        else
            Right (p.format (pr.parseError))
    }

    override def process (filename : String, e : Exp, config : Config) {
        val output = config.output()
        output.emitln ("e = " + e)
        output.emitln ("e tree:")
        output.emitln (layout (any (e)))
        output.emitln ("e tree pretty printed:")
        output.emitln (format (e).layout)
        output.emitln ("value (e) = " + expvalue (e))
        val o = optimise (e)
        output.emitln ("e optimised = " + o)
        output.emitln ("value (e optimised) = " + expvalue (o))
    }

    def format (ast : Exp) : Document =
        ExpParserPrettyPrinter.format (ast, 5)

}
