import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class HelloServlet extends HttpServlet {

  private final String helloMessage;

  public HelloServlet(String helloMessage) {
    super();
    this.helloMessage = helloMessage;
    System.out.println("Servlet is starting...");
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    try (Writer w = resp.getWriter()) {
      w.write(helloMessage);
    }
  }

}
