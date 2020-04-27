package com.tt.newsup.dao;


import com.tt.newsup.model.OpenNetworkModel;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OpenNetworkDao {
    @Select("select * from t_open_network where 1=1")
    List<OpenNetworkModel> getAll();

    @Delete("delete from t_open_network where open_network_id = #{openNetworkId}")
    void deledata(Integer openNetworkId);

    @Select("SELECT DISTINCT open_network_identifying from t_open_network")
    List<String> getidentifying();

    @Select("select * from t_open_network where open_network_identifying = #{identifying}")
    List<OpenNetworkModel> getAlldata(String identifying);
}
