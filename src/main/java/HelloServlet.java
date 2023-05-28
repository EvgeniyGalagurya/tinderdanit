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
//    try (Writer w = resp.getWriter()) {
//      w.write(helloMessage);
//    }
    Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
    cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
    cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("tpl")));
    HashMap<String, Object> data = new HashMap<>();
    try {
      Users.usersList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    i = i + 1;
    try {
      if (i > Users.mapUsers.quantity()){
        i = 1;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String userName;
    try {
      userName = Users.mapUsers.load(i).name();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String userLink;
    try {
      userLink = Users.mapUsers.load(i).link();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    data.put("name", userName);
    data.put("linkpage", userLink);

    try (PrintWriter w = resp.getWriter()) {
      cfg
              .getTemplate("users-page.ftl")
              .process(data, w);
    } catch (TemplateException x) {
      throw new RuntimeException(x);
    }
  }

}
