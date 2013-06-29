import com.slickdbexample.app._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle with SlickInit {

  override def init(context: ServletContext) {
  	val dbMaster = setupDB("master")
  	val dbSlave = setupDB("slave")
    context.mount(new MyScalatraServlet(dbMaster,dbSlave), "/*")
  }

  override def destroy(context: ServletContext) {
    closeDbConnections()
  }

}
