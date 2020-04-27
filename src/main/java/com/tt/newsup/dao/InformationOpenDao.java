package com.tt.newsup.dao;

import com.tt.newsup.model.BlackListModel;
import com.tt.newsup.model.InformationOpenModel;
import com.tt.newsup.model.LineWhiteListModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface InformationOpenDao {


    /**
     * 查询一级界面∂
     * @return
     */
    List<InformationOpenModel> getbossAll(String bossMerberNum,String bossGroupCoding,String bossSetName,String bossBusinessName,String informationLinkMan);


    @Select("select information_state_new," +
            "information_bandwidth," +
            "information_cilent_name," +
            "information_buss_type," +
            "information_order_title," +
            "information_linkman," +
            "information_phone,information_order_first_time," +
            "information_debug_time,information_app_time,information_reject_time," +
            "contacts_install_company,contacts_phone,information_dredge_reject_cause," +
            "boss_set_name,boss_line_name,information_order_id from t_information_open_boss " +
            "where information_identifying = #{informationIdentifying} and 1=1")
    List<InformationOpenModel> getInformation(String informationIdentifying);





    List<InformationOpenModel> selectby( String state1, String businessnameinput, String nameinput,String gongsiput);



    //获取流程
    @Select("select  pon_process_classification," +
            "pon_state,pon_end_time," +
            "pon_state_now,pon_state_rejected," +
            "prospect_exploration_time," +
            "prospect_contract_time," +
            "prospect_construction_time," +
            "pon_open_time," +
            "prospect_process_classification," +
            "prospect_order_time," +
            "prospect_sate,prospect_end_time," +
            "prospect_statw_now,prospect_state_reject," +
            "information_premier_time,information_cilent_order_time," +
            "information_order_accept_time,information_company," +
            "information_dredge_reject_cause,information_design_reject_cause," +
            "boss_business_name,boss_set_name,boss_bandwidth," +
            "boss_debug_start,contacts_install_company,contacts_phone,boss_business_time,boss_line_name,open_network_area " +
            "from t_open_information_boss  where boss_merber_num = #{bossMerberNum}")
    InformationOpenModel getliucheng(String bossMerberNum);
    //判断是否在黑名单中
    @Select("select * from t_black_list where black_list_user_id= #{userid} ")
    BlackListModel isblacklist(String userid);

    @Select("select * from t_line_white_list where line_white_list_user_id= #{userid}")
    LineWhiteListModel iswhitelist(String userid);

    @Select("select DISTINCT information_linkman  from t_open_information_boss where information_phone = #{userid} ")
    List<String> isManager(String userid);

    List<InformationOpenModel> getlinkmanboss(String bossMerberNum, String bossGroupCoding, String bossSetName, String bossBusinessName, String informationLinkMan, String userid);
}
