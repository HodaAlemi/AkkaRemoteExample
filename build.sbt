

lazy val commonSettings = Seq(
  version := "0.1.0",
  scalaVersion := "2.12.4",
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.5.20",
    "com.typesafe.akka" %% "akka-remote" % "2.5.20"),
  resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

lazy val remote = (project in file("remote"))
  .settings(
    commonSettings,
    name := "remote"
  )

lazy val local = (project in file("local"))
  .settings(
    commonSettings,
    name := "local"
  )


lazy val root = (project in file("."))
  .aggregate(remote, local)
