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
import java.util.HashMap;

public class ChatPageApp extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("tpl")));

        String id = req.getParameter("id");
        int idUser = Integer.parseInt(id);

        HashMap<String, Object> data = new HashMap<>();
        String userName;
        try {
            userName = Users.mapUsersLiked.load(idUser).name();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String userLink;
        try {
            userLink = Users.mapUsersLiked.load(idUser).link();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        data.put("name", userName);
        data.put("linkpage", userLink);

        try (PrintWriter w = resp.getWriter()) {
            cfg
                    .getTemplate("chat.ftl")
                    .process(data, w);
        } catch (TemplateException x) {
            throw new RuntimeException(x);
        }
    }


}



