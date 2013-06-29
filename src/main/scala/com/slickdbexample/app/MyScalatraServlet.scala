package com.slickdbexample.app

import org.scalatra._
import scalate.ScalateSupport
import scala.slick.driver.MySQLDriver.simple._
import Database.threadLocalSession

class MyScalatraServlet(dbMaster: Database, dbSlave: Database) extends SlickpoolappStack with SlickSupport {

  get("/") {
  	dbMaster withSession {
  	  val UserList = getUsers
      <html>
       <body>
        <h1>Hello,</h1>
        Users: { UserList.list.map { case (u) => "  " + u  } mkString "<br />"}
      </body>
    </html>
  	}
   
  }

  get("/slave") {
  	dbSlave withSession {
  	  val UserList = getUsers
      <html>
       <body>
        <h1>Hello,</h1>
        Users: { UserList.list.map { case (u) => "  " + u  } mkString "<br />"}
      </body>
    </html>
  	}
  }
  
}
