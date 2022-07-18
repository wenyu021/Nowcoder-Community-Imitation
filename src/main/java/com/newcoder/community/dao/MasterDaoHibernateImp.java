package com.newcoder.community.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 */
@Repository("masterHibernates")
public class MasterDaoHibernateImp implements MasterDao{

    @Override
    public String select() {
        return "Hibernates success!";
    }
}
