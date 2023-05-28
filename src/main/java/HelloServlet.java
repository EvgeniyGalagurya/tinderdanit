import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class HelloServlet extends HttpServlet {
  int i = 0;
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
