Overview
===========

Special shoutout to stefan-_ and rossabaker on IRC for helping to get this working.  Those guys seriously rock and took the time to help get this working.

We needed a way to use Slick to accomplish a few things

1. We wanted a trait extended by ScalatraBootstrap that would globally setup and tear down the connectionpool for 2 sets of DB clusters.  One for our master DB and one for our slave DB VIp

2. We needed a clean way to share that across controllers

3. We needed to make sure we were not re-creating connection pools for each servlet 


Where should I look ?
-----------------
 

**SlickInit.scala**

A trait extended just by ScalatraBootstrap which is used to initialize the connection pool.  All this does is create 2 vals that each hold a reference to the db object that get passed to the contructor of your servlet classes.  This trait is to hold vals/defs needed for startup and shutdown of the app 

**SlickSupport.scala**

Here you can put your support methods to help with any database queries.  Not entirely necessary, but it certainly helps abstract away "model" behaviours into a trait out of your controller 

**c3p0-config.xml**

Here you can configure multiple data sources that SlickInit will read and connect to.  This allows easy configuration of the DataSources by name.

