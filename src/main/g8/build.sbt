name := "k"

version := "0.1.0-SNAPSHOT"

organization := "myorg"

// Scala compiler settings

scalaVersion := "2.9.2"

scalacOptions ++= Seq ("-deprecation", "-unchecked")

// Interactive settings

logLevel := Level.Warn

shellPrompt <<= (name, version) { (n, v) => _ => n + " " + v + "> " }

// Fork the runs and connect sbt's input and output to the forked process so
// that we are immune to version clashes with the JLine library used by sbt

fork in run := true

connectInput in run := true

outputStrategy in run := Some (StdoutOutput)

// Execution

parallelExecution in Test := false

// Dependencies

libraryDependencies ++=
    Seq (
        "com.googlecode.kiama" %% "kiama" % "1.4.0"
    )

resolvers ++= Seq (
    Resolver.sonatypeRepo ("releases"),
    Resolver.sonatypeRepo ("snapshots")
)

// Source code locations

// Specify how to find source and test files.  Main sources are
//    - in src directory
//    - all .scala files, except
// Test sources, which are
//    - files whose names end in Tests.scala, which are actual test sources

scalaSource <<= baseDirectory { _ / "src" }

scalaSource in Compile <<= scalaSource

scalaSource in Test <<= scalaSource

unmanagedSources in Test <<= (scalaSource in Test) map { s => {
    (s ** "*Tests.scala").get
}}

unmanagedSources in Compile <<=
    (scalaSource in Compile, unmanagedSources in Test) map { (s, tests) =>
        ((s ** "*.scala") --- tests).get
    }

// Resources

unmanagedResourceDirectories in Compile <<= (scalaSource in Compile) { Seq (_) }

unmanagedResourceDirectories in Test <<= unmanagedResourceDirectories in Compile

// There are no compile resources
unmanagedResources in Compile := Seq ()

// Test resources are the non-Scala files in the source that are not hidden
unmanagedResources in Test <<= (scalaSource in Test) map { s => {
    (s ** (-"*.scala" && -HiddenFileFilter)).get
}}

// Rats! setup

sbtRatsSettings

ratsMainModule <<= (scalaSource in Compile) { _ / "syntax" / "ExpParser.syntax" }

ratsUseScalaLists := true

ratsUseScalaOptions := true

ratsUseScalaPositions := true

ratsDefineASTClasses := true

ratsDefinePrettyPrinter := true

ratsUseKiama := true
