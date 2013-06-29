package com.slickdbexample.app

import org.scalatra.ScalatraServlet
import org.slf4j.LoggerFactory
import com.mchange.v2.c3p0.ComboPooledDataSource
import java.util.Properties
import scala.slick.session.Database

trait SlickInit {

  val logger = LoggerFactory.getLogger(getClass)

  def createDataSource(name: String): ComboPooledDataSource = {
    val cpds = new ComboPooledDataSource(name)
    logger.info(f"Created c3p0 connection pool: $name")
    cpds
  }
 
  val cpdsMaster = createDataSource("master")
  val cpdsSlave = createDataSource("slave")

  def setupDB(instance: String): Database = instance match { 
  	case "master" => Database.forDataSource(cpdsMaster)
  	case "slave" => Database.forDataSource(cpdsSlave)
    case _ => Database.forDataSource(cpdsMaster)
  }

  def closeDbConnections() {
    logger.info("Closing c3p0 connection pool")
    cpdsMaster.close
    cpdsSlave.close
  }

}