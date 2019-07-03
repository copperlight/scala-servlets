import java.util.concurrent.atomic.AtomicInteger

import com.typesafe.scalalogging.StrictLogging
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object Servlets extends StrictLogging {
  private val HTML_UTF_8 = "text/html; charset=utf-8"
  private val JSON_UTF_8 = "application/json; charset=utf-8"

  private val SC_OK = HttpServletResponse.SC_OK

  private val requestCount: AtomicInteger = new AtomicInteger(0)

  private def mkResponse(
    res: HttpServletResponse,
    contentType: String,
    statusCode: Int,
    message: String
  ): Unit = {
    res.setContentType(contentType)
    res.setStatus(statusCode)
    res.getWriter.println(message)
  }

  class RootServlet extends HttpServlet {
    override protected def doGet(
      req: HttpServletRequest,
      res: HttpServletResponse
    ): Unit = {
      val scheme = req.getScheme
      val host = req.getHeader("host")

      val description = Map(
        "endpoints" -> List(
          s"$scheme://$host",
          s"$scheme://$host/increment",
          s"$scheme://$host/reset",
        )
      )

      logger.info(s"pathInfo=${req.getPathInfo}")
      mkResponse(res, JSON_UTF_8, SC_OK, JsonUtil.toJson(description))
    }
  }

  class IncrementServlet extends HttpServlet {
    override protected def doGet(
      req: HttpServletRequest,
      res: HttpServletResponse
    ): Unit = {
      requestCount.getAndIncrement()

      logger.info(s"pathInfo=${req.getPathInfo}")
      mkResponse(res, HTML_UTF_8, SC_OK, s"<h2>Increment received. Count is now $requestCount.</h2>")
    }
  }

  class ResetServlet extends HttpServlet {
    override protected def doGet(
      req: HttpServletRequest,
      res: HttpServletResponse
    ): Unit = {
      requestCount.set(0)

      logger.info(s"pathInfo=${req.getPathInfo}")
      mkResponse(res, HTML_UTF_8, SC_OK, "<h2>Counter reset to 0.</h2>")
    }
  }
}
