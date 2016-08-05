package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.domain.user.UserInterface;
import com.thoughtworks.ketsu.domain.user.UserId;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.UserMapper;

import javax.inject.Inject;
import java.util.Optional;

public class UserRepository implements UserInterface {
    @Inject
    UserMapper userMapper;

    @Override
    public User createUser(User user) {
        userMapper.createUser(user);
        return userMapper.findById(user.getUid().id());
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.ofNullable(userMapper.findById(id.id()));
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }
}
