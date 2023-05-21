import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

public class PeopleListServlet extends HttpServlet {

    private int i;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
      cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
      cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("tpl")));

        try {
            Users.usersList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

      HashMap<String, Object> data = new HashMap<>();
      ArrayList<Users> items = new ArrayList<>();

        try {
            IntStream.range(1, (Users.mapUsers.quantity() + 1)).forEachOrdered(i -> {
                try {
                    items.add(new Users(Users.mapUsers.load(i).id(), Users.mapUsers.load(i).name(), Users.mapUsers.load(i).link()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                data.put("items", items);
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try (PrintWriter w = resp.getWriter()) {
        cfg
                .getTemplate("people-list.ftl")
                .process(data, w);
      } catch (TemplateException x) {
        throw new RuntimeException(x);
      }
    }
}
