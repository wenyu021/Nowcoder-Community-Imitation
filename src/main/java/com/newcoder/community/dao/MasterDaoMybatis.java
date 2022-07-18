package com.newcoder.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 */

@Repository
@Primary
public class MasterDaoMybatis implements MasterDao{
    @Override
    public String select() {
        return "Mybatis";
    }
}
