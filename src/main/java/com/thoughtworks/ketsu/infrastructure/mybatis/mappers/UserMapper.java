package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.domain.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface UserMapper {
    void createUser(@Param("user") User user);

    User findById(@Param("uid") String id);

    void updateUser(@Param("user") User user);

}
