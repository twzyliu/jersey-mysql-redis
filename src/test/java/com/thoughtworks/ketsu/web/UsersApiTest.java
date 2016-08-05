package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserInterface;
import com.thoughtworks.ketsu.domain.user.UserRole;
import com.thoughtworks.ketsu.infrastructure.repositories.UserRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(ApiTestRunner.class)
public class UsersApiTest extends ApiSupport {
    @Inject
    UserRepository userRepository;

    @Test
    public void return_URI_when_POST_users_with_parameters() {
        Response post = post("users", TestHelper.usermap());
        Assert.assertThat(post.getStatus(), Is.is(201));
        Assert.assertThat(post.getLocation().toString().contains("/users/"), Is.is(true));
    }

    @Test
    public void return_400_when_POST_fail() {
        Response post = post("users", TestHelper.userfailmap());
        Assert.assertThat(post.getStatus(), Is.is(400));
    }

    @Test
    public void return_user_when_GET_by_uid() {
        Map<String, Object> userinfo = TestHelper.usermap();
        User user = userRepository.createUser(new User(userinfo));
        Response get = get("users/"+userinfo.get("uid"));
        assertThat(get.getStatus(), is(200));

        Map usermap = get.readEntity(Map.class);
        assertThat(usermap.get("uid"), is(user.getUid().id()));
        assertThat(usermap.get("username"), is(user.getUsername()));
        assertThat(usermap.get("nickname"), is(user.getNickname()));
        assertThat(usermap.get("email"), is(user.getEmail()));
        assertThat(usermap.get("role"), is(user.getRole().toString()));
        assertThat(usermap.get("department"), is(user.getDepartment()));
        List urls = (List) usermap.get("links");
        assertThat(urls.size(), is(2));
        assertThat(canFindLink(urls, "self", "/users/"+user.getUid().id()), is(true));
        assertThat(canFindLink(urls, "programs", "/users/"+user.getUid().id()+"/programs"), is(true));
    }

    @Test
    public void return_404_when_GET_by_uid_fail() {
        Response get = get("users/a");
        assertThat(get.getStatus(), is(404));
    }

    @Test
    public void return_204_when_PUT_with_parameters_by_uid() {
        Map<String, Object> userinfo = TestHelper.usermap();
        User user = userRepository.createUser(new User(userinfo));
        String userid = user.getUid().id();
        Response put = put("users/" + userid, TestHelper.userPUTmap());
        assertThat(put.getStatus(), is(204));

        User newUser = userRepository.findById(user.getUid()).get();
        assertThat(newUser.getNickname(), is("GeBiLaoWang"));
        assertThat(newUser.getRole().toString(), is("PM"));
        assertThat(newUser.getDepartment(), is("PS"));
    }

    @Test
    public void return_400_when_PUT_fail() {
        Map<String, Object> userinfo = TestHelper.usermap();
        User user = userRepository.createUser(new User(userinfo));
        String userid = user.getUid().id();
        Response put = put("users/" + userid, TestHelper.userPUTfailmap());
        assertThat(put.getStatus(), is(400));
    }


//    @Test
//    public void should_import_user_success() throws Exception {
//        Map<String, Object> userInfo = new HashMap<String, Object>() {{
//            put("id", "123");
//            put("email", "scxu@thoughtworks.com");
//            put("name", "scxu");
//            put("role", "DEV");
//            put("password", "123");
//        }};
//
//        final Response post = post("/users", userInfo);
//        assertThat(post.getStatus(), is(201));
//    }
//
//    @Test
//    public void should_400_if_id_not_valid() throws Exception {
//        Map<String, Object> userInfo = new HashMap<String, Object>() {{
//            put("email", "scxu@thoughtworks.com");
//            put("name", "Xu Shanchuan");
//            put("role", "Dev");
//        }};
//
//        final Response post = post("/users", userInfo);
//        assertThat(post.getStatus(), is(400));
//    }
//
//    @Test
//    public void should_400_if_email_not_valid() throws Exception {
//        Map<String, Object> userInfo = new HashMap<String, Object>() {{
//            put("id", "123");
//            put("name", "Xu Shanchuan");
//            put("role", "Dev");
//        }};
//
//        final Response post = post("/users", userInfo);
//        assertThat(post.getStatus(), is(400));
//    }
//
//    @Test
//    public void should_400_when_create_user_if_user_exist() throws Exception {
//        final User user = TestHelper.userForTest("123", "scxu", UserRole.DEV);
//        userRepository.save(user);
//
//        Map<String, Object> userInfo = new HashMap<String, Object>() {{
//            put("id", "123");
//            put("name", "scxu");
//            put("email", "scxu@tw.com");
//            put("role", "Dev");
//        }};
//
//        final Response post = post("/users", userInfo);
//        assertThat(post.getStatus(), is(400));
//    }
//
//    @Test
//    public void should_get_user_by_id() throws Exception {
//        final User user = TestHelper.userForTest("123", "scxu", UserRole.DEV);
//        userRepository.save(user);
//
//        final Response response = get("/users/" + user.getUserId().id());
//        assertThat(response.getStatus(), is(200));
//        final Map userMap = response.readEntity(Map.class);
//        assertThat(userMap.get("id"), is(user.getUserId().id()));
//        assertThat(userMap.get("name"), is(user.getName()));
//        assertThat(userMap.get("email"), is(user.getEmail()));
//        List urls = (List) userMap.get("links");
//        assertThat(urls.size(), is(1));
//        assertThat(canFindLink(urls, "self", "/users/123"), is(true));
//    }
//
//    @Test
//    public void should_404_if_user_not_exist() throws Exception {
//        final Response response = get("/users/abc");
//        assertThat(response.getStatus(), is(404));
//    }
}
