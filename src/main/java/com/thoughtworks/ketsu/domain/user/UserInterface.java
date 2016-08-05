package com.thoughtworks.ketsu.domain.user;

import java.util.Optional;

public interface UserInterface {
    User createUser(User user);

    Optional<User> findById(UserId id);

    void updateUser(User user);

}
