This is a giter8 template for sbt 1.x projects in the style that
I (inkytonik) commonly use. The generated project will be set up to use the
[Kiama language processing library](http://kiama.googlecode.com) and will
contain a simple example of Kiama use with the
[Rats! parser generator](http://cs.nyu.edu/rgrimm/xtc/rats-intro.html) via
the
[sbt-rats sbt plugin](https://bitbucket.org/inkytonik/sbt-rats).

See [`inkytonik/kiama.g8`](https://github.com/inkytonik/kiama.g8) for
variant that uses Kiama without Rats!,  and
[`inkytonik/plain.g8`](https://github.com/inkytonik/plain.g8) for a variant
that does not use Kiama or Rats!.

### Properties

* illustration of how to use simple Kiama features to build a simple
language processor which reads simple expression language programs
from files

* scalac options `-deprecation` and `-unchecked` turned on

* sbt log level is warning

* sbt shell prompt contains project name and version

* uses sbt-rats plugin

* stub `Main` object with a dummy `main` method

### Usage

Install [sbt 1.x](http://www.scala-sbt.org).

Then

    sbt new inkytonik/kiama-rats.g8

Run the generated project on the file `input.exp` as follows:

    cd $name$
    sbt
    > run input.exp
    e = Add(Mul(Add(Num(1),Num(2)),Num(3)),Mul(Num(0),Num(7)))
    e tree:
    Add(Mul(Add(Num(1), Num(2)), Num(3)), Mul(Num(0), Num(7)))
    e tree pretty printed:
    (1 + 2) * 3 + 0 * 7
    value (e) = 9
    e optimised = Mul(Add(Num(1),Num(2)),Num(3))
    value (e optimised) = 9
    >

where `$name$` is the value you entered when running the `sbt new` command.
Use Control-D to get out of the REPL.
