package com.example.mockito;

public interface UserService {
    Users getUserById(Long Id);
    Users createUser(Users users);
}
