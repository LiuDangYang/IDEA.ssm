package com.baidu.dao;

import com.baidu.model.User;
import org.springframework.stereotype.Repository;

/**
 * Created by 67545 on 2017/11/14.
 */

@Repository
public interface IUserDao {
    User selectUser(String id);
}

