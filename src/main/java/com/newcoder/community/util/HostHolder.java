package com.newcoder.community.util;

import com.newcoder.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description hold user information, replace session
 */

@Component
public class HostHolder {

    private ThreadLocal<User> users = new ThreadLocal<>();

    public void setUser(User user){
        users.set(user);
    }

    public User getUser(){
        return users.get();
    }

    public void clear(){
        users.remove();
    }
}
