import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.InetSocketAddress;
import java.util.Optional;
public class App {

    static Optional<Integer> toInt(String raw) {
        try {
            return Optional.of(Integer.parseInt(raw));
        } catch (Exception x) {
            return Optional.empty();
        }
    }

    public static void main(String[] args) throws Exception {

//        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.1.107", 24);
//        Server server = new Server(inetSocketAddress);
//

//
//        Integer port = Optional.ofNullable(System.getenv("PORT"))
//                .flatMap(App::toInt)
//                .orElse(8080);
//
//        Server server = new Server(port);

        Server server = new Server(HerokuEnv.port());

        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new HelloServlet("Hello World")), "/");
        String osStaticLocation = ResourcesOps.dirUnsafe("static-content");
        handler.addServlet(new ServletHolder(new StaticContentServlet(osStaticLocation)), "/static-content/*");
        handler.addServlet(new ServletHolder(new TestServlet()), "/test");
        handler.addServlet(new ServletHolder(new PeopleListServlet()), "/people");

        handler.addServlet(new ServletHolder(new UsersPageServlet()), "/users");
        handler.addServlet(new ServletHolder(new ChatPageServlet()), "/messages/{id}");
        handler.addServlet(new ServletHolder(new LoginFormServlet()), "/login");
        handler.addServlet(new ServletHolder(new LikePageServlet()), "/liked");

        server.setHandler(handler);
        server.start();
        server.join();
    }
}
