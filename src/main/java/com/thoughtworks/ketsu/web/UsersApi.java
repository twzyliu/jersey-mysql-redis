package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.EncryptionService;
import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.domain.user.UserRole;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("users")
public class UsersApi{
    @Context
    UserRepository userRepository;

    @Context
    Routes routes;

    @Context
    EncryptionService encryptionService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(Map<String, Object> info) {
        User user = new User(info);
        user.check();
        userRepository.createUser(user);
        return Response.created(routes.userUrl(user)).build();
    }

    @Path("{userId}")
    public UserApi ApiById(@PathParam("userId") String uid) {
        return userRepository.findById(new UserId(uid))
                .map(UserApi::new)
                .orElseThrow(() -> new WebApplicationException(Response.Status.NOT_FOUND));
    }

}
