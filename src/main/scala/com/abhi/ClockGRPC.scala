package com.abhi

/**
  * Created by ASrivastava on 7/4/17.
  */


import com.abhi.grpc.clock.{ClockGrpc, TimeRequest, TimeResponse}
import io.grpc.stub.StreamObserver

import scala.concurrent.ExecutionContext


object ClockGrpcServer extends GrpcServer with App {
   val ssd = ClockGrpc.bindService(new ClockGRPC(), ExecutionContext.global)
   runServer(ssd, "Clock")
}

class ClockGRPC extends ClockGrpc.Clock {
   override def streamTime(request: TimeRequest, responseObserver: StreamObserver[TimeResponse]): Unit = {
      var counter = 0;
      while(counter < request.count) {
         counter = counter + 1
         responseObserver.onNext(TimeResponse(System.currentTimeMillis))
         Thread.sleep(2000)
      }
   }
}