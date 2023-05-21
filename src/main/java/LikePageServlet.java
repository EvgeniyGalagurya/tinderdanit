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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class LikePageServlet extends HttpServlet {
  int id = 0;
  int i = 0;
  int d = 0;
  int l = 0;
  public int getId() throws Exception {
    id = id + 1;
    if (id > Users.mapUsers.quantity()) {
      id = 1;
    }
    return id;
  }

  public void getDislikeClick() throws Exception {
    d = d + 1;
  }

  public void getLikeClick() throws Exception {
    l = l + 1;
  }
  static ArrayList<Integer> peopleLikeId = new ArrayList<>();

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

    try {
      getDislikeClick();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    HashMap<String, Object> data = new HashMap<>();
    try {
      if (d-1 >= Users.mapUsers.quantity()){
        String uri = ResourcesOps.dirUnsafe("tpl/people-list_null.ftl");
        Path fileWithFullPath = Paths.get(uri);
        try (PrintWriter w = resp.getWriter()) {
          Files
                  .readAllLines(fileWithFullPath)
                  .forEach(w::println);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      if (l + d - 1 >= Users.mapUsers.quantity()) {
        HashMap<String, Object> data2 = new HashMap<>();
        ArrayList<Users> items = new ArrayList<>();
        for (int i = 0; i < peopleLikeId.size(); i++) {
          try {
            items.add(new Users(Users.mapUsersLiked.load(peopleLikeId.get(i)).id(), Users.mapUsersLiked.load((Integer) peopleLikeId.get(i)).name(), Users.mapUsersLiked.load((Integer) peopleLikeId.get(i)).link()));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }}
        data2.put("items", items);
        try (PrintWriter w = resp.getWriter()) {
          cfg
                  .getTemplate("people-list.ftl")
                  .process(data2, w);
        } catch (TemplateException x) {
          throw new RuntimeException(x);
        }
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      getId();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String userName;
    try {
      userName = Users.mapUsers.load(id).name();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    String userLink;
    try {
      userLink = Users.mapUsers.load(id).link();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    data.put("name", userName);
    data.put("linkpage", userLink);

    try (PrintWriter w = resp.getWriter()) {
      cfg
              .getTemplate("like-page.ftl")
              .process(data, w);
    } catch (TemplateException x) {
      throw new RuntimeException(x);
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    try {
      getLikeClick();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    try {
      if (id <= Users.mapUsers.quantity()) {
        Users.mapUsersLiked.save(Users.mapUsers.load(id));
        peopleLikeId.add(id);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

    try {
      if (l + d - 1 >= Users.mapUsers.quantity()) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("tpl")));

        HashMap<String, Object> data = new HashMap<>();
        ArrayList<Users> items = new ArrayList<>();

        for (int i = 0; i < peopleLikeId.size(); i++) {
          try {
            items.add(new Users(Users.mapUsersLiked.load(peopleLikeId.get(i)).id(), Users.mapUsersLiked.load((Integer) peopleLikeId.get(i)).name(), Users.mapUsersLiked.load((Integer) peopleLikeId.get(i)).link()));
          } catch (Exception e) {
            throw new RuntimeException(e);
          }}
          data.put("items", items);
          try (PrintWriter w = resp.getWriter()) {
            cfg
                    .getTemplate("people-list.ftl")
                    .process(data, w);
          } catch (TemplateException x) {
            throw new RuntimeException(x);
          }
        }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_32);
        cfg.setDefaultEncoding(String.valueOf(StandardCharsets.UTF_8));
        cfg.setDirectoryForTemplateLoading(new File(ResourcesOps.dirUnsafe("tpl")));

            try {
              Users.usersList();
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            HashMap<String, Object> data1 = new HashMap<>();
            try {
              getId();
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            String userName;
            try {
              userName = Users.mapUsers.load(id).name();
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            String userLink;
            try {
              userLink = Users.mapUsers.load(id).link();
            } catch (Exception e) {
              throw new RuntimeException(e);
            }
            data1.put("name", userName);
            data1.put("linkpage", userLink);
            try (PrintWriter w = resp.getWriter()) {
              cfg
                      .getTemplate("like-page.ftl")
                      .process(data1, w);
            } catch (TemplateException x) {
              throw new RuntimeException(x);
            }
  }
}
