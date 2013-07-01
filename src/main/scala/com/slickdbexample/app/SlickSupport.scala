package com.slickdbexample.app

import slick.driver.MySQLDriver.simple._

//----------------------------------------------
// Define your models here 
//-----------------------------------------------
object Users extends Table[(String)]("USERS") {
  def email = column[String]("EMAIL")
  def * = email 

}

//------------------------------------------------------
// Define methods here that you can call from
// your controller to keep things tidy.  This acts like
// your "model"
// If you wanted you can probably create multiple support
// traits that you extend on each controller to keep model
// and db logic separate
//-------------------------------------------------------
trait SlickSupport {

  def getUserEmails = {  Users.map( _.email.asColumnOf[String]) }
 
}