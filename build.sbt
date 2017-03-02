name := "bitly"

version := "1.0"

scalaVersion := "2.12.1"

// SELECT Path, ShortName, Version FROM libraryDependencies GROUP BY Language ORDER BY ShortName ASC
libraryDependencies ++= Seq(
  // Scala: Add Scala version to package name, e.g. "_2.11", or just use %% to automatically add the current version
//  "com.lihaoyi"             %% "ammonite-ops"            % "0.8.1",  // File system library
//  "com.github.tototoshi"    %% "scala-csv"               % "1.3.4",  // CSV support library
//  "org.scala-lang.modules"  %% "scala-xml"               % "1.0.6",  // Resolve conflicting dependencies
  "org.scalatest"           %% "scalatest"               % "3.0.1",  // Unit testing framework
//  "org.scaldi"              %% "scaldi"                  % "0.5.8",  // Dependency injection framework
  // Java
//  "com.typesafe"            %  "config"                  % "1.3.1",  // Configuration library
//  "com.google.inject"       %  "guice"                   % "4.1.0",  // Dependency injection framework
//  "org.pegdown"             %  "pegdown"                 % "1.6.0",  // Markdown support (for reporting)
//  "org.scala-lang"          %  "scala-reflect"           % "2.11.8", // Resolve conflicting dependencies
  "org.seleniumhq.selenium" %  "selenium-firefox-driver" % "2.53.1",  // Firefox driver API
  "org.seleniumhq.selenium" %  "selenium-java"           % "2.53.1"   // Web automation framework
)