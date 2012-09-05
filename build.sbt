name := "herbstcampus"

organization := "ag.bett.lift.herbstcampus"

version := "0.1"

scalaVersion := "2.9.1"

seq(webSettings: _*)

port in container.Configuration := 8081

scalacOptions ++= Seq("-unchecked", "-deprecation")

// If using JRebel with 0.1.0 of the sbt web plugin
//jettyScanDirs := Nil
// using 0.2.4+ of the sbt web plugin
scanDirectories in Compile := Nil

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/snapshots/",
  "Repo Maven" at "http://repo1.maven.org/maven2/",
  "Nexus Releases" at "http://nexus.scala-tools.org/content/repositories/releases",
  "Nexus Snapshots" at "http://nexus.scala-tools.org/content/repositories/snapshots",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Bryan J Swift Repository" at "http://repos.bryanjswift.com/maven2/",
  "Evil Packet" at "http://evil-packet.org/m2/"
)

// if you have issues pulling dependencies from the scala-tools repositories (checksums don't match), you can disable checksums
//checksums := Nil

libraryDependencies ++= {
  val liftVersion = "2.4"
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-json" % liftVersion % "compile->default")
}

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-webapp" % "7.1.0.RC1" % "container",
  "org.scala-tools.testing" % "specs_2.9.0" % "1.6.8" % "test", // For specs.org tests
  "junit" % "junit" % "4.8" % "test->default", // For JUnit 4 testing
  "javax.servlet" % "servlet-api" % "2.5" % "provided->default",
  "ch.qos.logback" % "logback-classic" % "0.9.26" % "compile->default"
)
