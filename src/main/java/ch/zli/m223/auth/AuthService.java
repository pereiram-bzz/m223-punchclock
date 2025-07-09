package ch.zli.m223.auth;

import io.smallrye.jwt.build.Jwt;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Path("/token")
public class AuthService{

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
}