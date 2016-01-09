package main;

import main.services.AccountService;
import main.servlets.MyServlet;
import main.servlets.SignInServlet;
import main.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main implements CommandLineRunner {

    @Autowired AccountService accountService;
    @Autowired MyServlet myServlet;
    @Autowired SignInServlet signInServlet;
    @Autowired SignUpServlet signUpServlet;

    public static void main(String[] args)  throws Exception {
        SpringApplication.run(Main.class, args);
     }

    @Override
    public void run(String[] args) throws Exception{

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
