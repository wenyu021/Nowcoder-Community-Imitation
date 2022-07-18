package com.newcoder.community.service;

import com.newcoder.community.dao.MasterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 */

@Service
//@Scope("prototype")
public class MasterService {

    @Autowired
    private MasterDao masterDao;

    public String find(){
        return masterDao.select();
    }

    public MasterService(){
        System.out.println("implement master service");
    }

    @PostConstruct
    public void init(){
        System.out.println("初始化 Initiliase Master Service");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("destroy");
    }
}
