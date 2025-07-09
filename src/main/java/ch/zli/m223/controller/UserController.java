package ch.zli.m223.controller;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.auth.AuthService;
import ch.zli.m223.model.User;

@Path("/users")
public class UserController {

    @Inject
    AuthService authService;
    
    @POST
    @Path("/register")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Creates a new User.", description = "Creates a new User and returns the JWT.")
    public Response register(User user) {
        return authService.create(user);
    }
}
