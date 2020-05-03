name := "GithubRepoAPI"

version := "0.1"

scalaVersion := "2.13.1"

libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.12"
libraryDependencies += "io.spray" %%  "spray-json" % "1.3.5"
libraryDependencies += "org.json4s" %% "json4s-jackson" % "3.7.0-M3"
libraryDependencies += "com.typesafe" % "config" % "1.4.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.8" % "test"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"