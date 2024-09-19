ThisBuild / name := "multi-project"
ThisBuild / version := "1.0"
ThisBuild / scalaVersion := "3.5.0"

lazy val core = (project in file("module/core"))
lazy val app = (project in file("module/app")).dependsOn(core)

lazy val root = (project in file(".")).aggregate(core, app)