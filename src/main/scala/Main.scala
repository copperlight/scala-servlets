import com.typesafe.scalalogging.StrictLogging
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletHandler

object Main extends App with StrictLogging {
  import Servlets._

  val token = Option(System.getenv("GITHUB_API_TOKEN"))

  val port = {
    if (args.length == 1) {
      try {
        args(0).toInt
      } catch {
        case _: Exception =>
          logger.error(s"argument '${args(0)}' is not an integer")
          7101
      }
    } else {
      7101
    }
  }

  val server = new Server(port)
  val handler = new ServletHandler()

  server.setHandler(handler)

  handler.addServletWithMapping(classOf[RootServlet], "/")
  handler.addServletWithMapping(classOf[IncrementServlet], "/increment/*")
  handler.addServletWithMapping(classOf[ResetServlet], "/reset/*")

  server.start()

  logger.info(s"Jetty server started on port $port.")
}
