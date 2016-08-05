package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by zyongliu on 16/8/5.
 */
@RunWith(DatabaseTestRunner.class)
public class UserDBTest {
    @Inject
    UserRepository userRepository;

    @Test
    public void return_user_when_find_by_uid() {
        Map<String, Object> userinfo = TestHelper.usermap();
        userRepository.createUser(new User(userinfo));
        Optional<User> user = userRepository.findById(new UserId(userinfo.get("uid").toString()));
        assertThat(user.isPresent(), is(true));
        assertThat(user.get(), is(new User(userinfo)));
    }
}
