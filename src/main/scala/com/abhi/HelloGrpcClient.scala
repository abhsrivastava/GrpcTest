package com.abhi

import com.abhi.grpc.helloworld.ToBeGreeted
import io.grpc.ManagedChannelBuilder
import com.abhi.grpc.helloworld.HelloWorldGrpc

import scala.concurrent.Await
import scala.concurrent.duration.Duration
/**
  * Created by ASrivastava on 7/4/17.
  */
object HelloGrpcClient extends App {
   val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build()
   val toBeGreeted = ToBeGreeted("Abhishek")
   val stub = HelloWorldGrpc.stub(channel)
   val greetingFuture = stub.sayHello(toBeGreeted)
   val greeting = Await.result(greetingFuture, Duration.Inf)
   println(greeting)
}
