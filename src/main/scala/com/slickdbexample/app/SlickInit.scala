package com.slickdbexample.app

import org.scalatra.ScalatraServlet
import org.slf4j.LoggerFactory
import com.mchange.v2.c3p0.ComboPooledDataSource
import java.util.Properties
import scala.slick.session.Database

trait SlickInit {

  val logger = LoggerFactory.getLogger(getClass)

  val cpdsMaster = {
    val props = new Properties
    props.load(getClass.getResourceAsStream("/c3p0.properties"))
    val cpds = new ComboPooledDataSource
    cpds.setProperties(props)
    cpds.setJdbcUrl(props.getProperty("c3p0.jdbcUrlMaster"))
    logger.info("Created Master c3p0 connection pool")
    cpds
  }

  val cpdsSlave = {
    val props = new Properties
    props.load(getClass.getResourceAsStream("/c3p0.properties"))
    val cpds = new ComboPooledDataSource
    cpds.setProperties(props)
    cpds.setJdbcUrl(props.getProperty("c3p0.jdbcUrlSlave"))
    logger.info("Created Slave c3p0 connection pool")
    cpds
  }

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