name := "ms-shopping-basket-scala"

version := "1.0"

lazy val `ms-shopping-basket-scala` = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  jdbc , cache , ws   , specs2 % Test,
  "org.mockito"                 %  "mockito-all"                % "1.10.19"   % Test,
  "com.typesafe.play"           %% "play-specs2"                % "2.5.3"   % Test,
  "com.typesafe.play"           %% "play-test"                  % "2.5.3"   % Test,
  "com.typesafe.akka"           %% "akka-testkit"               % "2.4.2"  % Test
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

// https://www.playframework.com/documentation/2.5.x/Migration24#routing
routesGenerator := InjectedRoutesGenerator

resolvers ++= Seq(
  "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Typesafe Maven repository" at "https://repo.typesafe.com/typesafe/maven-releases/",
  "Typesafe Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/"
)
