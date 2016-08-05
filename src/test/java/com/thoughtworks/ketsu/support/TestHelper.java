package com.thoughtworks.ketsu.support;

import java.util.HashMap;
import java.util.Map;

public class TestHelper {

    public static Map<String, Object> usermap() {
        return new HashMap<String, Object>() {{
            put("uid", "12312");
            put("username", "jksun");
            put("email", "jksun@thoughtworks.com");
            put("nickname", "iankang Sun");
            put("role", "DEV");
            put("department", "Professional Services");
            put("password", "123");
        }};
    }

    public static Map<String, Object> userfailmap() {
        return new HashMap<String, Object>() {{
            put("uid", "11111");
            put("username", "jksun");
            put("email", "");
            put("nickname", "iankang Sun");
            put("role", "DEV");
            put("department", "Professional Services");
            put("password", "");
        }};
    }

    public static Map<String, Object> userPUTmap() {
        return new HashMap<String, Object>() {{
            put("nickname", "GeBiLaoWang");
            put("role", "PM");
            put("department", "PS");
        }};
    }

    public static Map<String, Object> userPUTfailmap() {
        return new HashMap<String, Object>() {{
            put("nickname", "GeBiLaoWang");
            put("username", "GeBiLaoWang");
            put("role", "PM");
            put("department", "PS");
        }};
    }


//    private static int auto_increment_key = 1;
//    public static Map<String, Object> deployment(String appName, String releaseId) {
//        return new HashMap<String, Object>() {{
//            put("app", String.format("http://service-api.tw.com/apps/%s", appName));
//            put("release", String.format("http://service-api.tw.com/apps/%s/releases/%s", appName, releaseId));
//        }};
//    }
//
//    public static Map<String, Object> stackMap(String id, String name) {
//        Map<String, Object> stackMap = new HashMap<String, Object>() {{
//            put("id", id);
//            put("name", name);
//        }};
//        return stackMap;
//    }
//
//    public static Map<String, Object> userMap(String email, String name) {
//        return new HashMap<String, Object>() {{
//            put("name", name);
//            put("email", email);
//        }};
//    }
//
//    public static User userForTest(String id, String name, UserRole role) {
//        String password_123 = "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW";
//        return new User(new UserId(id), name, name + "@tw.com", role, password_123);
//    }
//
//    public static User userFixture(UserInterface userRepository, UserRole role) {
//        final String id = new Integer(auto_increment_key++).toString();
//        User user = userForTest(id, "name-" + id, role);
//        userRepository.save(user);
//        return user;
//    }
//
//    public static Map<String, Object> userJsonForTest(User user) {
//        return new HashMap<String, Object>() {{
//            put("id", user.getUserId().id());
//            put("role", user.getRole());
//        }};
//    }
}
