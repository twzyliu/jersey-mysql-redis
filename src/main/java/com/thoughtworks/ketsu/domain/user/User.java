package com.thoughtworks.ketsu.domain.user;

import com.thoughtworks.ketsu.domain.AssertionConcern;
import com.thoughtworks.ketsu.infrastructure.records.Record;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.asList;

public class User extends AssertionConcern implements Record {
    private UserId uid;
    private String username;
    private String nickname;
    private String email;
    private UserRole role;
    private String password;
    private String department;

    private User() {

    }

    public User( String uid, String username, String nickname, String email, UserRole role, String password, String department) {
        this.uid = new UserId(uid);
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.department = department;
    }

    public User( UserId uid, String username, String nickname, String email, UserRole role, String password, String department) {
        this.uid = uid;
        this.username = username;
        this.nickname = nickname;
        this.email = email;
        this.role = role;
        this.password = password;
        this.department = department;
    }

    public User( Map<String, Object> info) {
        this.uid = new UserId(info.getOrDefault("uid","").toString());
        this.username = info.getOrDefault("username","").toString();
        this.nickname = info.getOrDefault("nickname","").toString();
        this.email = info.getOrDefault("email","").toString();
        this.role = info.getOrDefault("role","") == null ? UserRole.DEV : UserRole.valueOf(info.get("role").toString());
        this.password = info.getOrDefault("department","").toString();
        this.department = info.getOrDefault("password","").toString();
    }

    public void check() {
        String error = "";
        if (username == error)
            throw new IllegalArgumentException("username of user is null!");
        if (nickname == error)
            throw new IllegalArgumentException("nickname of user is null!");
        if (email == error)
            throw new IllegalArgumentException("email of user is null!");
        if (role.toString() == error)
            throw new IllegalArgumentException("role of user is null!");
        if (password == error)
            throw new IllegalArgumentException("password of user is null!");
        if (department == error)
            throw new IllegalArgumentException("department of user is null!");
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return new HashMap<String, Object>() {{
            put("uid", uid.id());
            put("username", username);
            put("nickname", nickname);
            put("email", email);
            put("role", role);
            put("department", department);
            put("links", asList(
                    new HashMap<String, Object>() {{
                        put("rel", "self");
                        put("uri", routes.userUrl(User.this));
                    }},
                    new HashMap<String, Object>() {{
                        put("rel", "programs");
                        put("uri", routes.userUrl(User.this) + "/programs");
                    }}));
        }} ;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return toRefJson(routes);
    }


    public UserId getUid() {
        return uid;
    }

    public String getUsername() {
        return username;
    }

    public String getNickname() {
        return nickname;
    }

    public String getEmail() {
        return email;
    }

    public UserRole getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getDepartment() {
        return department;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
