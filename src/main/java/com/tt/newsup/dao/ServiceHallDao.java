package com.tt.newsup.dao;

import com.tt.newsup.model.*;
import com.tt.newsup.server.ServiceHallService;
import lombok.Data;
import org.apache.ibatis.annotations.*;

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

    @Select("select * from t_service_store_topic_network_dome ")
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

    /**
     * 根据USERID查询出抢单后的站厅
     * @param userid
     * @return
     */
    @Select("select DISTINCT service_store_ditch_code from t_service_store_proceed where service_store_user = #{userid}")
    String getStoreId(String userid);

    @Select("select * from  t_service_store where 1=1 and service_store_ditch_code = #{serviceStoreDitchCode}")
    ServiceHallModel getStoremess(String serviceStoreDitchCode);

    /**
     * 查询用户的调查表做到第几步
     * @param user
     * @return
     */
    @Select("select service_store_active from t_service_store_proceed where service_store_user = #{user} ")
    Integer getactive(String user);




    /**
     * 查询服务营销动作的网络线条项目名称
     */
    @Select("select DISTINCT manage_table_name  from t_service_store_manage_network where 1=1 ")
    List<String> getmaagenetworkname();


    /**
     * 查询服务营销动作的市场线条项目名称
     */
    @Select("select DISTINCT manage_table_name  from t_service_store_manage_market where 1=1")
    List<String> getmaagemarketname();

    /**
     * 根据项目名称查询市场线条的具体内容
     * @param manageTableName
     * @return
     */
    @Select("select manage_table_question from t_service_store_manage_market where manage_table_name = #{manageTableName} ")
    List<String> getmanagequestionmarket(String manageTableName);

    /**
     * 根据项目名称查询网络线条的具体内容
     * @param manageTableName
     * @return
     */
    @Select("select manage_table_question from t_service_store_manage_network where manage_table_name = #{manageTableName} ")
    List<String> getmanagequestionnetwork(String manageTableName);


    /**
     * 保存管理执行表的图片
     * @param manageTableName
     * @param index
     * @param tableId
     * @param url
     */
    @Insert("insert into t_service_manage_network_img(service_manage_network_img_table_name," +
            "service_manage_network_img_index," +
            "service_manage_network_img_table_id," +
            "service_manage_network_img_userid," +
            "service_manage_network_img_path) values(" +
            "#{manageTableName}," +
            "#{index}," +
            "#{tableId}," +
            "#{userid}," +
            "#{url})")
    void insermanageImage(String manageTableName, Integer index, Integer tableId, String url,String userid);


    @Select("select * from t_service_manage_network_img where service_manage_network_img_userid =#{userid} and service_manage_network_img_table_name=#{tablename} and service_manage_network_img_table_id = #{i} ")
    List<ServiceManageImgModel> getmanageImage(String userid, String tablename,Integer i);

    @Select("select max(service_manage_network_img_table_id) from t_service_manage_network_img where service_manage_network_img_userid =#{userid} and service_manage_network_img_table_name=#{tablename}")
    Integer getMaxTableId(String userid, String tablename);

    /**
     * 保存网络线条的临时答案
     */
    @Insert("insert into t_manage_answer_network_bank(manage_answer_user_id,manage_answer_table_name,manage_answer_radio,manage_answer_textarea,manage_answer_num) values(#{userid},#{manageTableName},#{radioContext},#{messageContext},#{ansertNum}) ")
    void saveManageAnswerBlank(String radioContext, String messageContext, String userid, Integer ansertNum, String manageTableName);

    /**
     * 保存市场类的临时答案
     * @param radioContext
     * @param messageContext
     * @param userid
     * @param ansertNum
     * @param manageTableName
     */
    @Insert("insert into t_manage_answer_market_bank(manage_answer_user_id,manage_answer_table_name,manage_answer_radio,manage_answer_textarea,manage_answer_num) values(#{userid},#{manageTableName},#{radioContext},#{messageContext},#{ansertNum}) ")
    void saveManageAnswerBlankMarket(String radioContext, String messageContext, String userid, Integer ansertNum, String manageTableName);

    /**
     * 判断网络线条条件类的同类型的
     * @param userid
     * @param manageTableName
     * @return
     */
    @Select("select * from t_manage_answer_network_bank where manage_answer_user_id = #{userid} and manage_answer_table_name =#{manageTableName}")
    List<ServiceManageAnswerModel> getManageBlackAnswerNetwork(String userid, String manageTableName);

    /**
     * 判断市场线条类的同类型的临时答案是否存在
     * @param userid
     * @param manageTableName
     * @return
     */
    @Select("select * from t_manage_answer_market_bank where manage_answer_user_id = #{userid} and manage_answer_table_name =#{manageTableName}")
    List<ServiceManageAnswerModel> getManageBlackAnswerMarket(String userid, String manageTableName);

    /**
     * 如果存在则删除原有的答案重新插入网络线条
     * @param userid
     * @param manageTableName
     */
    @Delete("delete from t_manage_answer_network_bank where  manage_answer_user_id = #{userid} and manage_answer_table_name =#{manageTableName}")
    void delManageBlackAnswerNetwork(String userid, String manageTableName);

    /**
     * 如果存在则删除原有的答案重新插入市场线条
     * @param userid
     * @param manageTableName
     */
    @Delete("delete from t_manage_answer_market_bank where  manage_answer_user_id = #{userid} and manage_answer_table_name =#{manageTableName}")
    void delManageBlackAnswerMarket(String userid, String manageTableName);

    /**
     * 管理者执行表删除图片
     */

    @Delete("delete from t_service_manage_network_img where service_manage_network_img_path = #{url}")
    void delemanagenetworkimg(String url);


    @Delete("delete from t_service_manage_market_img where service_manage_network_img_path = #{url}")
    void delemanagemarketimg(String url);

    /**
     * 获取管理者执行表的临时答案-网络线条
     * @param userid
     * @param manageTableName
     */

    @Select("select * from t_manage_answer_network_bank where manage_answer_user_id = #{userid} and manage_answer_table_name=#{manageTableName} order by manage_answer_num Asc")
    List<ServiceManageAnswerModel> getmanagenetworkaswerblack(String userid, String manageTableName);


    /**
     * 获取管理者执行表的临时答案-市场线条
     * @param userid
     * @param manageTableName
     */
    @Select("select * from t_manage_answer_market_bank where manage_answer_user_id = #{userid} and manage_answer_table_name=#{manageTableName} order by manage_answer_num Asc")
    List<ServiceManageAnswerModel> getmanagemarketaswerblack(String userid, String manageTableName);

    @Select("select service_store_mytopic_id from t_service_store_mytopic_proceed where service_store_mytopic_user=#{userid}")
    Integer getAnswerTopId(String userid);

    @Select("select service_store_proceed_id from  t_service_store_proceed where service_store_user = #{userid}  ")
    Integer getAnswerProceedId(String userid);

    /**
     * 保存最后答案到正式表中-管理者执行表网络线条
     * @param userid
     * @param roidzongtistr
     * @param messagezongtistr
     * @param imgpathzongtistr
     * @param manageAnswerTopicId
     * @param manageAnswerProceedId
     */

    @Insert("insert into t_manage_answer_network(manage_answer_user_id,manage_answer_radio,manage_answer_textarea,manage_answer_img,manage_answer_topic_id,manage_answer_proceed_id) values(#{userid},#{roidzongtistr},#{messagezongtistr},#{imgpathzongtistr},#{manageAnswerTopicId},#{manageAnswerProceedId})")
    void inserNetworkAnswer(String userid,String roidzongtistr,String  messagezongtistr,String imgpathzongtistr, Integer manageAnswerTopicId, Integer manageAnswerProceedId);


    /**
     * 保存最后的答案到正式表中-管理者执行表市场线条
     * @param userid
     * @param roidzongtistr
     * @param messagezongtistr
     * @param imgpathzongtistr
     * @param manageAnswerTopicId
     * @param manageAnswerProceedId
     */
    @Insert("insert into t_manage_answer_market(manage_answer_user_id,manage_answer_radio,manage_answer_textarea,manage_answer_img,manage_answer_topic_id,manage_answer_proceed_id) values(#{userid},#{roidzongtistr},#{messagezongtistr},#{imgpathzongtistr},#{manageAnswerTopicId},#{manageAnswerProceedId})")
    void inserMarketAnswer(String userid, String roidzongtistr, String messagezongtistr, String imgpathzongtistr, Integer manageAnswerTopicId, Integer manageAnswerProceedId);

    /**
     * 删除管理者执行表-市场线条的临时答案
     * @param userid
     */
    @Delete("delete from t_manage_answer_market_bank where manage_answer_user_id = #{userid}")
    void delemanageMarketAnswerBlack(String userid);


    @Update("update t_service_store_proceed set service_store_active = 1 where service_store_user = #{userid}")
    void updatanetworkactive(String userid);

    @Delete("delete from t_manage_answer_network_bank where manage_answer_user_id = #{userid}")
    void delemanagenetAnswerBlack(String userid);

    @Delete("delete from t_service_manage_network_img where service_manage_network_img_userid =#{userid}")
    void delemanagenetworkimgall(String userid);

    /**
     * 查询网络线条的体验者执行表问题
     * @return
     */
    @Select("select * from t_service_store_taste_network order by taste_table_id asc")
    List<ServiceHallTasteQuestionModel> gettastedatanetwork();

    /**
     * 查询市场线条的体验者执行表问题
     * @return
     */
    @Select("select * from t_service_store_taste_market order by taste_table_id asc")
    List<ServiceHallTasteQuestionModel> gettastedatamarket();

    /**
     * 保存体验者图片
     * @param index
     * @param tableId
     * @param url
     * @param userid
     */
    @Insert("insert into t_service_taste_network_img(service_taste_market_img_userid,service_taste_market_img_table_id,service_taste_market_img_index,service_taste_market_img_path)" +
            "values(#{userid},#{tableId},#{index},#{url})")
    void inserTasteImg(Integer index, Integer tableId, String url, String userid);

    /**
     * 查询最大的题目序号
     * @param userid
     * @return
     */
    @Select("select max(service_taste_market_img_table_id) from t_service_taste_network_img")
    Integer gettasteMaxTableId(String userid);

    /**
     * 查询每道题的图片，并进行打包
     * @param userid
     * @param i
     * @return
     */

    @Select("select * from t_service_taste_network_img where service_taste_market_img_userid =#{userid}  and service_taste_market_img_table_id = #{i} ")
    List<ServiceTasteImgModel> gettasteImage(String userid,Integer i);


    @Delete("delete from t_service_taste_network_img where service_taste_market_img_path = #{url}")
    void deletasteimg(String url);

    /**
     * 保存体验者执行表答案
     * @param userid
     * @param roidzongtistr
     * @param messagezongtistr
     * @param imgpathzongtistr
     * @param manageAnswerTopicId
     * @param manageAnswerProceedId
     */

    @Insert("insert into t_taste_answer_network(taste_answer_user_id,taste_answer_radio,taste_answer_textarea,taste_answer_img,taste_answer_topic_id,taste_answer_proceed_id)" +
            " VALUES(#{userid},#{roidzongtistr},#{messagezongtistr},#{imgpathzongtistr},#{manageAnswerTopicId},#{manageAnswerProceedId})")
    void insertTasteAnswer(String userid, String roidzongtistr, String messagezongtistr, String imgpathzongtistr, Integer manageAnswerTopicId, Integer manageAnswerProceedId);


    @Insert("insert into t_taste_answer_market(taste_answer_user_id,taste_answer_radio,taste_answer_textarea,taste_answer_img,taste_answer_topic_id,taste_answer_proceed_id)" +
            " VALUES(#{userid},#{roidzongtistr},#{messagezongtistr},#{imgpathzongtistr},#{manageAnswerTopicId},#{manageAnswerProceedId})")
    void insertMarketTasteAnswer(String userid, String roidzongtistr, String messagezongtistr, String imgpathzongtistr, Integer manageAnswerTopicId, Integer manageAnswerProceedId);


    /**
     * 删除执行者保存的临时图片
     */
    @Delete("delete from t_service_taste_network_img where service_taste_market_img_userid =#{userid}")
    void deleteTasteImg(String userid);

    /**
     * 改变进行中列表的active的值
     * @param userid
     */
    @Update("update t_service_store_proceed set service_store_active = 2 where service_store_user = #{userid}")
    void updatetasteActive(String userid);

    /**
     * 保存课题总结答案
     * @param userid
     * @param message
     * @param manageAnswerTopicId
     * @param manageAnswerProceedId
     */
    @Insert("insert into t_summary_answer_network(summary_answer_user_id,summary_answer_textarea,summary_answer_topic_id,summary_answer_proceed_id)" +
            " VALUES(#{userid},#{message},#{manageAnswerTopicId},#{manageAnswerProceedId})")
    void saveSummary(String userid, String message, Integer manageAnswerTopicId, Integer manageAnswerProceedId);

    @Update("update t_service_store_proceed set service_store_active = 5 where service_store_user = #{userid}")
    void updateSummaryActive(String userid);


    @Insert("insert into t_service_store_proceed_end(service_store_user," +
            "service_store_ditch_code," +
            "service_store_date," +
            "service_store_time," +
            "service_store_grab_time," +
            "service_store_mytopic_id)  select service_store_user," +
            "service_store_ditch_code," +
            "service_store_date," +
            "service_store_time," +
            "service_store_grab_time," +
            "service_store_mytopic_id  from t_service_store_proceed where service_store_user= #{userid} ")
    void insertProgreeEnd(String userid);

    @Delete("delete from t_service_store_proceed where service_store_user =#{userid}")
    void deleteProgree(String userid);

    /**
     * 执行表完全提交后把进行中的课题转变成已完成的课题
     * @param userid
     */

    @Insert("insert into t_service_store_mytopic_proceed_end(service_store_mytopic_id," +
            "service_store_mytopic_context,service_store_mytopic_user," +
            "service_store_mytopic_type,serice_store_mytopic_time)  " +
            "select  service_store_mytopic_id,service_store_mytopic_context," +
            "service_store_mytopic_user,service_store_mytopic_type," +
            "serice_store_mytopic_time " +
            "from t_service_store_mytopic_proceed where " +
            "service_store_mytopic_user=#{userid}")
    void inserMytopicEnd(String userid);

    @Delete("delete from t_service_store_mytopic_proceed where service_store_mytopic_user = #{userid}")
    void deleteMytopicProgree(String userid);

    @Insert("insert into t_service_store_problem_mamage(service_store_problem_userid," +
            "service_store_manage_table_name," +
            "service_store_grab_id," +
            "service_store_manage_id," +
            "service_store_problem_title," +
            "service_store_problem_context," +
            "service_store_problem_img_path) values(" +
            "#{userid}," +
            "#{tablename}," +
            "#{manageAnswerProceedId}," +
            "#{problemId}," +
            "#{problemtitle}," +
            "#{problemcontext}," +
            "#{imgString})")
    void saveManageProblem(String userid, String tablename, Integer problemId, String problemtitle, Integer manageAnswerProceedId,String problemcontext,String imgString);


    /**
     * 体验者执行表派发一线直通车的工单
     * @param userid
     * @param problemId
     * @param problemtitle
     * @param manageAnswerProceedId
     * @param problemcontext
     * @param imgString
     */

    @Insert("insert into t_service_store_problem_taste(service_store_problem_userid," +
            "service_store_grab_id," +
            "service_store_manage_id," +
            "service_store_problem_title," +
            "service_store_problem_context," +
            "service_store_problem_img_path) values(" +
            "#{userid}," +
            "#{manageAnswerProceedId}," +
            "#{problemId}," +
            "#{problemtitle}," +
            "#{problemcontext}," +
            "#{imgString})")
    void saveTasteProblem(String userid, Integer problemId, String problemtitle, Integer manageAnswerProceedId, String problemcontext, String imgString);


    @Select("select * from t_service_store where service_store_admin = #{userid}")
    List<ServiceHallModel> getStoreHallByUserId(String userid);
    //根据渠道编码获取需评价清单
    @Select("select * from t_service_store_proceed_end where service_store_ditch_code = #{serviceStoreDitchCode}")
    List<ServiceStoreProceedEndModel> getServiceStoreEnd(String serviceStoreDitchCode);
    //查询巡检人姓名
    @Select("select user_name  from  t_user_baseinfo where user_id = #{useridstr}")
    String getUserName(String useridstr);

    @Select("select service_store_mytopic_type from  t_service_store_mytopic_proceed_end where service_store_mytopic_id = #{serviceStoreMytopicId}")
    Integer getMytopicType(Integer serviceStoreMytopicId);
    //获取网络线条的rodioslist
    @Select("select manage_answer_radio from t_manage_answer_network where manage_answer_topic_id = #{serviceStoreMytopicId}")
    String getManaRodiosNetwork(Integer serviceStoreMytopicId);

    @Select("select  manage_answer_radio from   t_manage_answer_market where manage_answer_topic_id=#{serviceStoreMytopicId}")
    String getManaRodiosMarket(Integer serviceStoreMytopicId);

    @Select("select manage_answer_textarea from t_manage_answer_network where manage_answer_topic_id = #{serviceStoreMytopicId}")
    String getManaMessagesNetwork(Integer serviceStoreMytopicId);

    @Select("select manage_answer_textarea from t_manage_answer_market where manage_answer_topic_id = #{serviceStoreMytopicId}")
    String getManaMessagesMarket(Integer serviceStoreMytopicId);

    @Select("select manage_answer_img from t_manage_answer_network  where manage_answer_topic_id = #{serviceStoreMytopicId}")
    String getManaImgsNetwork(Integer serviceStoreMytopicId);

    @Select("select manage_answer_img from t_manage_answer_market  where manage_answer_topic_id = #{serviceStoreMytopicId}")
    String getManaImgsMarket(Integer serviceStoreMytopicId);


    @Select("select taste_answer_radio from t_taste_answer_network where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteRodiosNetwork(Integer serviceStoreMytopicId);

    @Select("select taste_answer_radio from t_taste_answer_market where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteRodiosmarket(Integer serviceStoreMytopicId);


    @Select("select taste_answer_textarea from t_taste_answer_network where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteMessageNetwork(Integer serviceStoreMytopicId);

    @Select("select taste_answer_img from t_taste_answer_market where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteMessagemarket(Integer serviceStoreMytopicId);

    @Select("select taste_answer_img from t_taste_answer_network where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteImgsNetwork(Integer serviceStoreMytopicId);

    @Select("select taste_answer_img from t_taste_answer_market where taste_answer_topic_id = #{serviceStoreMytopicId}")
    String getTasteImgsmarket(Integer serviceStoreMytopicId);
}
