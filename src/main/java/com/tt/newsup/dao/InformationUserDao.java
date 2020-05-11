package com.tt.newsup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InformationUserDao {

    @Insert("insert t_information_user_tj(information_user_phone)  values(#{ghinput})")
     void saveUser(String ghinput);
}
