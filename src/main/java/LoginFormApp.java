import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginFormApp extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = ResourcesOps.dirUnsafe("tpl/login.ftl");

        Path fileWithFullPath = Paths.get(uri);

        try (PrintWriter w = resp.getWriter()) {
            Files
                    .readAllLines(fileWithFullPath)
                    .forEach(w::println);
        }
    }
}



