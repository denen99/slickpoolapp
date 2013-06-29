Special shoutout to stefan-_ and rossabaker on IRC for helping to get this working.

We needed a way to use Slick to accomplish a few things

1) We wanted a trait extended by ScalatraBootstrap that would globally setup and tear down the connectionpool for 2 sets of DB clusters.  One for our master DB and one for our slave DB VIp
2) We needed a clean way to share that across controllers
3) We needed to make sure we were not re-creating connection pools for each servlet 


Our solution was to create two traits (per the persistence docs recommendations), one called SlickInit which has sole responsibility for setting up the c3p0 connection pool and another called SlickSupport which would be extended by all the controllers.


