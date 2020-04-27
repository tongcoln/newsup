package com.tt.newsup.dao;

import com.tt.newsup.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:37 上午
 * @description：
 * @modified By：
 * @version:
 */
@Mapper
public interface ServiceHallDao {
    /**
     * 获取网络线条的默认课题
     * @return
     */

    @Select("select * from t_service_store_topic_network_dome")
    List<ServiceStoreTopicNetworkDomeModel> getTopicNetworkDome();

    /**
     * 获取市场线条的默认课题
     * @return
     */
    @Select("select * from t_service_store_topic_marketplace_dome")
    List<ServiceStoreTopicMarketplaceDomeModel> getmarketplacetopic();

    /**
     * 保存我选择好的课题
     * @param networktopic
     * @param user
     */
    @Insert("insert into t_service_store_mytopic_proceed(service_store_mytopic_context,service_store_mytopic_user,service_store_mytopic_type) VALUES(#{networktopic},#{user},1)")
    void savenetworktopic(String networktopic, String user);

    /**
     * 获取我的课题列表
     * @param user
     * @return
     */
    @Select("select * from t_service_store_mytopic_proceed")
    ServiceStoreTopicProceedModel getmytopiclist(String user);

    /**
     * 获取网络默认课题列表
     * @return
     */
    @Select("select service_store_topic_network_name from  t_service_store_topic_network_dome")
    List<String> getnetworktopic();

    /**
     * 保存选择了市场课题的课题
     * @param markettopic
     * @param user
     */
    @Insert("insert into t_service_store_mytopic_proceed(service_store_mytopic_context,service_store_mytopic_user,service_store_mytopic_type) VALUES(#{markettopic},#{user},2)")
    void savemarkettopic(String markettopic, String user);

    /**
     * 查询市场线条的默认课题
     * @return
     */
    @Select("select service_store_topic_marketplace_name from  t_service_store_topic_marketplace_dome")
    List<String> getmarkettopic();

    /**
     * 获取站厅列表
     * @return
     */
    @Select("select service_store_id,service_store_ditch_name,service_store_type,service_store_ditch_code,service_store_eare,service_store_adress  from t_service_store")
    List<ServiceHallModel> selectAll();

    /**
     * 查询是否有进行中的任务
     * @param user
     * @return
     */
    @Select("select * from t_service_store_proceed where  service_store_user = #{user}")
    ServiceHallProceedModel  getServiceHallProceed(String user);

    @Select("select count(1) from t_service_store_proceed where  service_store_ditch_code =#{serviceStoreDitchCode}  and service_store_date=#{datemodel} and service_store_time=#{datemodel1} ")
    Integer getcount(String serviceStoreDitchCode, String datemodel, String datemodel1);


    /**
     * 插入抢单信息
     * @param serviceStoreDitchCode
     * @param user
     * @param datemodel
     * @param datamodel1
     * @param mytopicId
     */
    @Insert("insert t_service_store_proceed(service_store_user,service_store_ditch_code,service_store_date,service_store_time,service_store_mytopic_id) " +
            "VALUES(#{user},#{serviceStoreDitchCode},#{datemodel},#{datamodel1},#{mytopicId});")
    void insertprogress(String serviceStoreDitchCode, String user, String datemodel, String datamodel1, Integer mytopicId);


    //搜索框进行搜索
    List<ServiceHallModel> selectAllByName(String results);
}
