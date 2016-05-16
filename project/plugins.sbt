logLevel := Level.Warn

resolvers ++= Seq(
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Maven repository" at "https://repo.typesafe.com/typesafe/maven-releases/",
  "Typesafe Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.3")