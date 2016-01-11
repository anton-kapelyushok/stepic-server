package main;

import main.config.JerseyConfig;
import main.servlets.SignInServlet;
import main.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication

@ComponentScan(basePackages = { "main"} )
public class Main implements CommandLineRunner{

    @Autowired SignInServlet signInServlet;
    @Autowired SignUpServlet signUpServlet;
    @Autowired JerseyConfig jerseyCfg;
    @Autowired ServletContainer jerseyServlet;

    @Bean
    ServletContainer getJerseyServlet() {
        return new ServletContainer(jerseyCfg);
    }

    public static void main(String[] args)  throws Exception {
        SpringApplication.run(Main.class, args);
     }

    @Override
    public void run(String[] args) throws Exception{
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(jerseyServlet), "/jersey/*");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
