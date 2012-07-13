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
        "com.googlecode.kiama" %% "kiama" % "1.3.0"
    )

resolvers += "Sonatype OSS Snapshots Repository" at
    "https://oss.sonatype.org/content/repositories/snapshots"

// Source code locations

// Specify how to find source and test files.  Main sources are
//    - in src directory
//    - all .scala files, except
// Test sources, which are
//    - files whose names end in Tests.scala, which are actual test sources

scalaSource <<= baseDirectory { _ / "src" }

unmanagedSources in Test <<= scalaSource map { s => {
    (s ** "*Tests.scala").get
}}

unmanagedSources in Compile <<= (scalaSource, unmanagedSources in Test) map { (s, tests) =>
    ((s ** "*.scala") --- tests).get
}

// Resources

unmanagedResourceDirectories <<= scalaSource { Seq (_) }

unmanagedResourceDirectories in Test <<= unmanagedResourceDirectories

// Test resources are the non-Scala files in the source that are not hidden
unmanagedResources in Test <<= scalaSource map { s => {
    (s ** (-"*.scala" && -HiddenFileFilter)).get
}}

// Rats! setup

seq (sbtRatsSettings : _*)

ratsMainModule <<= scalaSource { _ / "syntax" / "ExpParser.syntax" }

ratsUseScalaLists := true

ratsUseScalaOptions := true

ratsUseScalaPositions := true

ratsDefineASTClasses := true

ratsDefinePrettyPrinter := true

ratsUseKiama := true

