package com.example.mockito.service;

import com.example.mockito.Users;
import com.example.mockito.UserService;
import com.example.mockito.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(()
                ->new NullPointerException("User with id : "+id+" not found!"));
    }

    @Override
    public Users createUser(Users users) {
        Users user = userRepository.save(users);
        user.setUserIdentificationNo(idGenerator(user.getId()));
        return userRepository.save(user);
    }

    public static String idGenerator(Long id){
//        String userIdentity = String.format("%03d", id);
        Calendar calendar = Calendar.getInstance();
        return String.valueOf(calendar.get(Calendar.YEAR)) + "/DECA/" + String.valueOf(String.format("%03d", id));

    }
}
