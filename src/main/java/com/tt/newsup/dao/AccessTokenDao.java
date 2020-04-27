package com.tt.newsup.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * accessToken的Dao接口
 */
@Mapper
public interface AccessTokenDao {

    @Insert("insert into  t_access_token(access_token) values(#{accessToken})")
    void insertAccess(String accessToken);

    @Select("select access_token from t_access_token where access_id = (select MAX(access_id)  from t_access_token)")
    String selectAccess();
}
