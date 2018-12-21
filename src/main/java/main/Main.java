package main;

import DB.DataBase;
import accounts.AccountService;
import accounts.ShopService;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import javax.servlet.MultipartConfigElement;

public class Main {
    public static void main(String[] args) throws Exception {

        DataBase dataBase2 = new DataBase("shop.txt");
        ShopService shopService = new ShopService(dataBase2);

        DataBase dataBase = new DataBase("users.txt"); // хранить пароли в файле как хеши
        AccountService accountService = new AccountService(dataBase);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        ServletHolder addHolder = new ServletHolder(new AddServlet(shopService));
        addHolder.getRegistration().setMultipartConfig(new MultipartConfigElement("data/tmp"));
        //context.AddServlet(new ServletHolder(new UsersServlet(accountService)), "/api/v1/users");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/auth");
        context.addServlet(new ServletHolder(new DirectRequestServlet()), "/");
        context.addServlet(new ServletHolder(new LoginServlet()), "/login");
        context.addServlet(new ServletHolder(new RegistServlet(accountService)),"/reg");
        context.addServlet(new ServletHolder(new InitDataBaseServlet(dataBase, dataBase2)),"/init");
        context.addServlet(addHolder, "/add");
        context.addServlet(new ServletHolder(new AllRequestsServlet(dataBase2)), "/main");

        ResourceHandler resource_handler = new ResourceHandler();
       // resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        server.join();
    }
}
