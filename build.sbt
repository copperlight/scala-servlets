name := "scala-servlets"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "org.eclipse.jetty" % "jetty-servlet" % "9.4.18.v20190429",
  "org.eclipse.jetty" % "jetty-server" % "9.4.18.v20190429",
  "com.fasterxml.jackson.core" % "jackson-databind" % "2.9.9",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.9.9",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)
