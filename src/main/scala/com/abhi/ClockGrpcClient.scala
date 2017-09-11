package com.abhi

import io.grpc.ManagedChannelBuilder
import com.abhi.grpc.clock.{ClockGrpc, TimeRequest, TimeResponse}
import io.grpc.stub.StreamObserver
import org.joda.time.DateTime

import scala.io.StdIn

/**
  * Created by ASrivastava on 7/4/17.
  */
object ClockGrpcClient extends App {
   val channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext(true).build()
   val stub = ClockGrpc.stub(channel)
   val observer = new StreamObserver[TimeResponse] {
      override def onError(t: Throwable): Unit = println(s"failed with error ${t}")
      override def onCompleted(): Unit = println("closing observer")
      override def onNext(value: TimeResponse): Unit = println(s"received time ${new DateTime(value)}")
   }
   stub.streamTime(TimeRequest(10), observer)
   StdIn.readLine()
}
