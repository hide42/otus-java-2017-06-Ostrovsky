import Servlet.CacheServlet;
import Servlet.LoginServlet;
import cache.CacheEngineImpl;
import dataSets.AddressDataSet;
import dataSets.PhoneDataSet;
import dataSets.User;
import dbservice.DBService;
import dbservice.DBServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {

    private final static int PORT = 8090;
    private final static String PUBLIC_HTML = "public_html";

    public static void main(String[] args) throws Exception {
        DBService serviceDB = new DBServiceImpl(new CacheEngineImpl<>(2, 0, 0, true));

        User user1=new User("Alex",
                new AddressDataSet("Moskovskaya ,123"),
                new PhoneDataSet("4000620246"));
        User user2=new User("John",
                new AddressDataSet("Moskovskaya ,126"),
                new PhoneDataSet("8500600700"));




        serviceDB.save(user1);
        serviceDB.save(user2);

        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(2));
        System.out.println(serviceDB.read(1));

        System.out.println(serviceDB.getCacheStatus());
        serviceDB.shutdown();


        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(PUBLIC_HTML);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet("", "")), "/login");
        context.addServlet(new ServletHolder(new CacheServlet(serviceDB)), "/cache");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }
}
