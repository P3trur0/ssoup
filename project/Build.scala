import sbt._
import Keys._
import java.net.URL

object SSoup extends Build {

   val project = (Project("ssoup", file(".")) settings(
     organization := "org.filippodeluca.ssoup",
     name := "ssoup",
     version := "1.0-SNAPSHOT",
     scalaVersion := "2.11.8",
     licenses := Seq("Apache License, Version 2.0"->new URL("http://www.apache.org/licenses/LICENSE-2.0.html")),
     libraryDependencies ++= dependencies,
     autoCompilerPlugins := true,
     scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked")
   ) settings(publishSettings:_*))

   def publishSettings: Seq[Setting[_]] = Seq(
     // If we want on maven central, we need to be in maven style.
     publishMavenStyle := true,
     publishArtifact in Test := false,
     // The Nexus repo we're publishing to.
     publishTo := Some(Resolver.file("file",  new File(Path.userHome.absolutePath+"/.m2/repository"))),
     // Maven central cannot allow other repos.  We're ok here because the artifacts we
     // we use externally are *optional* dependencies.
     pomIncludeRepository := { x => false },
     // Maven central wants some extra metadata to keep things 'clean'.
     pomExtra := (
 	    <licenses>
 		    <license>
 			    <name>Apache License, Version 2.0</name>
 			    <url>http://www.apache.org/licenses/LICENSE-2.0.html</url>
 			    <distribution>repo</distribution>
 		    </license>
 	    </licenses>
       <scm>
         <url>git@github.com:filosganga/scala-wurfl.git</url>
         <connection>scm:git:git@github.com:filosganga/scala-wurfl.git</connection>
       </scm>
       <developers>
         <developer>
           <id>filosganga</id>
           <name>Filippo De Luca</name>
           <url>http://filippodeluca.com</url>
         </developer>
       </developers>)
   )

   def dependencies = Seq(
     "org.jsoup" % "jsoup" % "1.9.2",
     "org.scalatest" %% "scalatest" % "3.0.0" % "test",
     "org.mockito" % "mockito-all" % "1.9.0" % "test",
     "org.scala-lang.modules" %% "scala-xml" % "1.0.1"
    )

}
