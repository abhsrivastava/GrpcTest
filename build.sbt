name := "GRPCTest"

version := "1.0"

scalaVersion := "2.12.2"

PB.targets in Compile := Seq(
   scalapb.gen() -> (sourceManaged in Compile).value
)

libraryDependencies ++= Seq(
   "com.trueaccord.scalapb" %% "scalapb-runtime" % com.trueaccord.scalapb.compiler.Version.scalapbVersion % "protobuf",
   "com.trueaccord.scalapb" %% "scalapb-runtime-grpc" % com.trueaccord.scalapb.compiler.Version.scalapbVersion,
   "io.grpc" % "grpc-netty" % com.trueaccord.scalapb.compiler.Version.grpcJavaVersion

)