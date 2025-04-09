package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public User findUserByJwtToken(String jwt) throws UserException;

    public User findUserByEmail(String email) throws UserException;

}
