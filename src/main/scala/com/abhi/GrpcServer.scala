package com.abhi

/**
  * Created by ASrivastava on 7/4/17.
  */

import io.grpc.{Server, ServerBuilder, ServerServiceDefinition}
import java.util.logging.Logger

import scala.io.StdIn

class GrpcServer {
   private val logger = Logger.getLogger("GrpcServer")
   var server: Server = null

   def runServer(ssd: ServerServiceDefinition, serviceName: String): Unit = {
      server = ServerBuilder
         .forPort(50051)
         .addService(ssd)
         .build()
      logger.info(s"Going to start ${serviceName} Service")
      server.start()
      Runtime.getRuntime.addShutdownHook(new Thread(){
         override def run(): Unit = {
            logger.info(s"going to shutdown the ${serviceName} Service")
            GrpcServer.this.shutdown()
         }
      })
      server.awaitTermination()
   }

   def shutdown() = {
      logger.info("Going to shutdown Hello World Service")
      server.shutdown()
   }
}
