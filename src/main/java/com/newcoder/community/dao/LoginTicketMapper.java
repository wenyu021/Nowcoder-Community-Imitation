package com.newcoder.community.dao;

import com.newcoder.community.entity.LoginTicket;
import org.apache.ibatis.annotations.*;

/**
 * @author Wenyu Wang
 * @project community
 * @date 2022-07
 * @Description
 */

@Mapper
public interface LoginTicketMapper {

    @Insert({
            "insert into login_ticket(user_id,ticket,status,expired) ",
            "value(#{userId},#{ticket},#{status},#{expired}) "
    })
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insertLoginTicket(LoginTicket loginTicket);

    @Select({
            "select id, user_id,ticket,status,expired ",
            "from login_ticket where ticket=#{ticket} "
    })
    LoginTicket selectByTicket(String ticket);

    @Update({
            "<script>",
            "update login_ticket set status=#{status} ",
            "where ticket=#{ticket} ",
            "<if test=\"ticket!=null\"> ",
            "</if>",
            "</script>"
    })
    int updateStatus(String ticket, int status);
}
