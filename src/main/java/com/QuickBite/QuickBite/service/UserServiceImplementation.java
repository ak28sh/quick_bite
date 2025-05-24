package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.config.JwtProvider;
import com.QuickBite.QuickBite.model.User;
import com.QuickBite.QuickBite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws UserException {
        System.out.print("jwt--" + jwt);
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        System.out.println();
        System.out.print("EMAIL-" + email + " " + email.getClass().getName() + '\n');
        User user = userRepository.findByEmail(email);
        if(user==null) {
            throw new UserException("user not exist with email "+email);
        }
        System.out.print("USER from repo-" + user + '\n');
        return user;
    }

    @Override
    public User findUserByEmail(String email) throws UserException {

//        Find user in repo
        User user = userRepository.findByEmail(email);

//        If user not found
        if (user == null) {
            throw new UserException("User Not found");
        }
        return user;
    }


    @Override
    public  void deleteUserByEmail(String email) throws UserException {

//        Check if user is present
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new UserException("User Not found");
        }

        userRepository.deleteByEmail(email);
    }
}
