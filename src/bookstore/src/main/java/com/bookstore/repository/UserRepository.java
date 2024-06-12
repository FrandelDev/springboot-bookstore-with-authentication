package com.bookstore.repository;

import com.bookstore.models.UserEntity;

public interface UserRepository{
    UserEntity getUser(String username);
    UserEntity createUser(UserEntity user);
}
