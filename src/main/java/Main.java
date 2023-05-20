import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
//        String port = System.getenv().get("PORT");
//        if (port == null || port.equals("")){
//            port="8080";
//        }
//        Server server = new Server(Integer.parseInt(port));

        InetSocketAddress inetSocketAddress = new InetSocketAddress("192.168.1.107", 24);
        Server server = new Server(inetSocketAddress);

        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(new ServletHolder(new HelloServlet("Hello World")), "/");

        String osStaticLocation = ResourcesOps.dirUnsafe("static-content");
        handler.addServlet(new ServletHolder(new StaticContentServlet(osStaticLocation)), "/static-content/*");

        handler.addServlet(new ServletHolder(new TestServlet()), "/test");
        handler.addServlet(new ServletHolder(new UsersPageServlet()), "/users");


        handler.addServlet(new ServletHolder(new ChatPageApp()), "/chat");
        handler.addServlet(new ServletHolder(new LoginFormApp()), "/login");
        handler.addServlet(new ServletHolder(new LikePageApp()), "/liked");
        handler.addServlet(new ServletHolder(new PeopleListApp()), "/people");



        server.setHandler(handler);
        server.start();
        server.join();

    }


}
