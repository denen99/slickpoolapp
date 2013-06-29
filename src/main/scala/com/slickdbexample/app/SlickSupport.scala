package com.slickdbexample.app

import slick.driver.MySQLDriver.simple._

object Users extends Table[(String)]("USERS") {
  def email = column[String]("EMAIL")
  def * = email 

}

trait SlickSupport {

  def getUsers = {
  	val UserList = for {
  			u <- Users 
  		}yield (u.email.asColumnOf[String])
  	UserList
  }

}