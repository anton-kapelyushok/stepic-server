package main.servlets;

import main.models.User;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Component
@Path("/")
public class JerseyController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User test() {
        return new User("a", "d");
    }
}