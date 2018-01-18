package com.baidu.service.impl;
//Settings - Editor - Inspections - Spring - Spring Core - Code - Autowiring for Bean Class - disable(不可用)

import com.baidu.dao.IUserDao;
import com.baidu.model.User;
import com.baidu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by 67545 on 2017/11/14.
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public User selectUser(String userId) {
        return this.userDao.selectUser(userId);
    }

}