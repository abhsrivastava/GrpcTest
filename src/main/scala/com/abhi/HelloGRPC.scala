package com.abhi

/**
  * Created by ASrivastava on 7/4/17.
  */
import com.abhi.grpc.helloworld.{Greeting, HelloWorldGrpc, ToBeGreeted}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._

object HelloWorldGrpcServer extends GrpcServer with App {
   val ssd = HelloWorldGrpc.bindService(new HelloGRPC(), ExecutionContext.global)
   runServer(ssd, "Hello World")
}

class HelloGRPC extends HelloWorldGrpc.HelloWorld {
   override def sayHello(request: ToBeGreeted): Future[Greeting] = {
      Future.successful(Greeting(s"Hello World ${request.person}"))
   }
}
