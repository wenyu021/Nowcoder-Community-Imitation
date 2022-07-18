package com.newcoder.community.dao;

import com.newcoder.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    // offset起始行号 limit每页数据
    // userid为了之后个人主页帖子
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // 动态条件 有且只有一个参数 必须加param
    int selectDiscussPostRows(@Param("userId") int userId);
}
