Overview
===========

Special shoutout to stefan-_ and rossabaker on IRC for helping to get this working.  Those guys seriously rock and took the time to help get this working.

We needed a way to use Slick to accomplish a few things

1. We wanted a trait extended by ScalatraBootstrap that would globally setup and tear down the connectionpool for 2 sets of DB clusters.  One for our master DB and one for our slave DB VIp

2. We needed a clean way to share that across controllers

3. We needed to make sure we were not re-creating connection pools for each servlet 

Assumptions to get this demo working
----------------------------------
For our tests we setup a sample users table in 2 different databases and then  pointed each data source in c3p0-config.xml to use a different one to simulate connecting to different databases.

We modeled out a users table with a single email field just as a test.  Modify SlickSupport.scala so that it suits your DB needs by changing the model name and the fields in the table.  You can then update the getUsers method in SlickSupport and the controller to reference whatever methods you need from your "model".  This is just an example of how we think this can work.

Where should I look ?
-----------------
 

**SlickInit.scala**

A trait extended just by ScalatraBootstrap which is used to initialize the connection pool.  All this does is create 2 vals that each hold a reference to the db object that get passed to the contructor of your servlet classes.  This trait is to hold vals/defs needed for startup and shutdown of the app 

**SlickSupport.scala**

Here you can put your support methods to help with any database queries.  Not entirely necessary, but it certainly helps abstract away "model" behaviours into a trait out of your controller 

**c3p0-config.xml**

Here you can configure multiple data sources that SlickInit will read and connect to.  This allows easy configuration of the DataSources by name.

**ScalatraBootstrap.scala**

Check out here how we create a val for each DB instance that we need and then pass those to the constructor of the servlets.  Also note that we needed to add the destroy method to ensure we tear down the connection pool on app shutdown 

**MyScalatraServlet**

Sample controller servlet that you would use.  Notice how we extend the SlickSupport trait and also notice how we add constructor params to the Servlet.  This allows us to pass in an instance of the db vals from the Bootstrap file to obtain a DB Session

