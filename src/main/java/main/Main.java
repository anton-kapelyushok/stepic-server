package main;

import main.config.JerseyConfig;
import main.servlets.SignInServlet;
import main.servlets.SignUpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;


@Configuration
@ComponentScan
public class Main {

    @Autowired SignInServlet signInServlet;
    @Autowired SignUpServlet signUpServlet;
    @Autowired JerseyConfig jerseyCfg;
    @Autowired ServletContainer jerseyServlet;
    @Autowired WebApplicationContext webAppContext;
    @Autowired DispatcherServlet dispatcherServlet;


    @Bean
    ServletContainer jerseyServlet() {
        return new ServletContainer(jerseyCfg);
    }

    @Bean
    DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet(webAppContext);
    }

    public static void main(String[] args)  throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(Main.class);
        context.refresh();
        context.getBean(Main.class).run(args);
     }

    public void run(String[] args) throws Exception{
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(signInServlet), "/signin");
        context.addServlet(new ServletHolder(signUpServlet), "/signup");
        context.addServlet(new ServletHolder(jerseyServlet), "/jersey/*");
        context.addServlet(new ServletHolder(dispatcherServlet), "/spring/*");

        Server server = new Server(8080);
        server.setHandler(context);
        server.start();
        System.out.println("Server started!");
        server.join();
    }
}
