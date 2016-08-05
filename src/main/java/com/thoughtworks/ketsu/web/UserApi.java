package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.domain.user.UserRole;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class UserApi {
    private User user;

    public UserApi(User user) {
        this.user = user;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return user;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") String uid , Map<String, Object> info , @Context UserMapper userMapper) {
        User user = userMapper.findById(uid);
        if(info.getOrDefault("username","")!="")
            throw new IllegalArgumentException("can not update username!");
        if(info.getOrDefault("uid","")!="")
            throw new IllegalArgumentException("can not update uid!");
        if(info.getOrDefault("password","")!="")
            throw new IllegalArgumentException("can not update password!");
        if(info.getOrDefault("email","")!="")
            throw new IllegalArgumentException("can not update email!");

            user.setNickname(info.getOrDefault("nickname", user.getNickname()).toString());
        user.setDepartment(info.getOrDefault("department", user.getDepartment()).toString());
        String userRole = info.getOrDefault("role", null).toString();
        if (userRole != null)
            user.setRole(UserRole.valueOf(userRole));
        userMapper.updateUser(user);
        return Response.status(204).build();
    }
}

