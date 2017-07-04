package com.abhi

/**
  * Created by ASrivastava on 7/4/17.
  */


import com.abhi.grpc.clock.{ClockGrpc, TimeRequest, TimeResponse}
import io.grpc.stub.StreamObserver
import monix.execution.Scheduler
import monix.execution.Scheduler.{global => scheduler}
import scala.concurrent.duration._

object ClockGrpcServer extends GrpcServer with App {
   val ssd = ClockGrpc.bindService(new ClockGRPC(), Scheduler.global)
   runServer(ssd, "Clock")
}

class ClockGRPC extends ClockGrpc.Clock {
   override def streamTime(request: TimeRequest, responseObserver: StreamObserver[TimeResponse]): Unit = {
      scheduler.scheduleWithFixedDelay(0.seconds, 3.seconds) {
         responseObserver.onNext(TimeResponse(System.currentTimeMillis))
      }
   }
}