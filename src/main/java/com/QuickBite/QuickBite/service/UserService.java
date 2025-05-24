package com.QuickBite.QuickBite.service;

import com.QuickBite.QuickBite.Exception.UserException;
import com.QuickBite.QuickBite.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface UserService {

    public User findUserByJwtToken(String jwt) throws UserException;

    public User findUserByEmail(String email) throws UserException;

    @Transactional
    public void deleteUserByEmail(String email) throws UserException;
}
