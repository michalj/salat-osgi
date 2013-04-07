import sbt._
import sbtrelease.ReleasePlugin._
import com.typesafe.sbt.osgi.SbtOsgi.{ OsgiKeys, osgiSettings }
import Keys._

object SalatOsgiBuild extends Build {
  lazy val root = Project(id = "salat-osgi",
    base = file("."),
    settings = Project.defaultSettings ++ osgiSettings ++ releaseSettings ++ Seq(
      name := "salat-osgi",
      organization := "eu.semantiq.common",
      scalaVersion := "2.9.1",
      OsgiKeys.exportPackage ++= Seq(
        "com.mongodb",
        "com.mongodb.casbah",
        "com.mongodb.casbah.commons",
        "com.novus.salat",
        "com.novus.salat.dao",
        "com.novus.salat.global",
        "com.novus.salat.json",
        "com.novus.salat.util",
        "net.liftweb.json",
        "scala.tools.scalap.scalax.rules.scalasig"),
      OsgiKeys.importPackage ++= Seq(
        "org.aopalliance.aop",
        "net.sf.cglib.proxy;version=\"[2,3)\"",
        "net.sf.cglib.core;version=\"[2,3)\"",
        "net.sf.cglib.reflect;version=\"[2,3)\"",
        "javax.servlet;version=\"2.5.0\"",
        "javax.servlet.http;version=\"2.5.0\"",
        "javax.inject;resolution:=optional",
        "org.scalatest.matchers;resolution:=optional",
        "classycle;resolution:=optional",
        "classycle.graph;resolution:=optional",
        "org.scalatools.testing;resolution:=optional",
        "org.specs2;resolution:=optional",
        "org.specs2.data;resolution:=optional",
        "org.specs2.matcher;resolution:=optional",
        "org.specs2.mutable;resolution:=optional",
        "org.specs2.time;resolution:=optional",
        "org.apache.tools.ant;resolution:=optional",
        "org.apache.tools.ant.taskdefs;resolution:=optional",
        "org.apache.tools.ant.types;resolution:=optional",
        "org.apache.tools.ant.util;resolution:=optional",
        "scala.tools.jline;resolution:=optional",
        "scala.tools.jline.console;resolution:=optional",
        "scala.tools.jline.console.history;resolution:=optional",
        "scala.tools.jline.console.completer;resolution:=optional"),
      OsgiKeys.embeddedJars <<= Keys.externalDependencyClasspath in Compile map {
        deps =>
          val jarNames = Seq("paranamer", "salat", "casbah", "mongo", "scalaj",
              "scalap", "scala-compiler", "commons-httpclient", "commons-codec",
              "lift-json")
          deps filter (d => jarNames exists (jar => d.data.getName startsWith jar)) map (d => d.data)
      },
      libraryDependencies ++= Seq(
        "com.novus" % "salat_2.9.1" % "1.9.1",
        "org.scala-lang" % "scala-compiler" % "2.9.1")))
}
