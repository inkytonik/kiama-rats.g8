This is a giter8 template for sbt 0.13.x projects in the style that
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

* sources and resources located directly under `src`

* stub `Main` object with a dummy `main` method

* tests located with sources with suffix `Tests.scala`

* Mercurial `.hgignore` file

### Usage

Install [giter8 (g8)](http://github.com/n8han/giter8#readme) and [sbt
0.13.x](http://www.scala-sbt.org).

In a shell run the following:

    g8 inkytonik/kiama-rats.g8

The `g8` command will prompt you for information needed to setup the
project.

Run the generated project on the file `input.exp` as follows:

    cd $name$
    sbt
    > run input.exp
    e = Add(Mul(Add(Num(1),Num(2)),Num(3)),Mul(Num(0),Num(7)))
    e tree:
    Add (Mul (Add (Num (1), Num (2)), Num (3)), Mul (Num (0), Num (7)))
    e tree pretty printed:
    (1 + 2) * 3 + 0 * 7
    value (e) = 9
    e optimised = Mul(Add(Num(1),Num(2)),Num(3))
    value (e optimised) = 9
    exp> ^D
    >

where `$name$` is the value you entered when running the `g8` command.
Use Control-D to get out of the REPL.
