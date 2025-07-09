package ch.zli.m223.auth;

import io.smallrye.jwt.build.Jwt;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;

import ch.zli.m223.model.Entry;
import ch.zli.m223.model.User;
import ch.zli.m223.service.UserService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Path("/auth")
public class AuthService{

    @Inject
    UserService userService;

    @POST
    @Path("/admin")
    public Response generateToken() {
        try {

            String token =
                    Jwt.upn("jdoe@quarkus.io").issuer("https://example.com/issuer") 
                    .groups(new HashSet<>(Arrays.asList("User", "Admin"))) 
                .sign();


            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    public Response create(User user) {
       try {

            String token =
                    Jwt.upn("jdoe@quarkus.io").issuer("https://example.com/issuer") 
                    .groups(new HashSet<>(Arrays.asList(user.getUsername(), user.getRole()))) 
                .sign();


            return Response.ok(token).build();

        } catch (Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}