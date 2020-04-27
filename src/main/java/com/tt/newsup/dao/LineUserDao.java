package com.tt.newsup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface LineUserDao {
    @Insert("insert into t_line_user(line_user) values(#{lineUser})")
    void insert(String lineUser);

    @Insert("insert into t_line_user_click(line_user) values(#{lineUser})")
    void inserclik(String lineUser);

    @Select("select DISTINCT  line_user from t_line_user_click where line_time >#{datatime}")
    List<String> getHalfhourUser(Date datatime);

    @Select("select count(1) as cishu from t_line_user_click where  line_user=#{userid} and line_time >#{datatime}")
    Integer getcount(String userid,Date datatime);

    @Select("select DISTINCT  userid from enter_users  where sys_time >#{datatime}")
    List<String> getyixianUser(Date datatime);


    @Select("select count(1) as cishu from enter_users where  userid=#{yixianuserid} and sys_time >#{datatime}")
    Integer getyixiancount(String yixianuserid, Date datatime);

    @Insert("insert into t_black_list(black_list_user_id) values(#{userid})")
    void saveBlackList(String userid);

    @Insert("insert into t_send_mail(mail_user,mail_count) values(#{yixianuserid},#{yixiancount})")
    void saveMail1(String yixianuserid, Integer yixiancount);

    @Insert("insert into t_send_mail(mail_user,mail_count) values(#{userid},#{count})")
    void saveMail(String userid, Integer count);
}
