package com.zxa.service.impl;

import com.zxa.dao.UserDao;
import com.zxa.entity.User;
import com.zxa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Override
    public boolean emailIsExist(String email) {
        User user=userDao.getUserByEmail(email);
        if(user==null){
            return false;
        }
        else return true;
    }

    @Override
    public void registerUser(User user) {
        userDao.insert(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public int update(User user) {
        return userDao.update(user);
    }
}
