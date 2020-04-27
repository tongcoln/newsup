package com.tt.newsup.dao;

import com.tt.newsup.model.OpenNetworkNewModel;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OpenNetworkNewDao {


    @Insert("insert into t_open_network_new(open_network_city,open_network_area,pon_order_id,prospect_order_id,open_network_identifying,open_network_title,pon_process_classification,pon_order_time,pon_state,pon_end_time,pon_state_now,pon_state_rejected,prospect_exploration_time,prospect_contract_time,prospect_construction_time,pon_open_time,prospect_process_classification,prospect_order_time,prospect_sate,prospect_end_time,prospect_statw_now,prospect_state_reject)\n" +
            "values(#{openNetworkNewModel.openNetworkCity},#{openNetworkNewModel.openNetworkArea},#{openNetworkNewModel.ponOrderId},#{openNetworkNewModel.prospectOrderId},#{openNetworkNewModel.openNetworkIdentifying},#{openNetworkNewModel.openNetworkTitle},#{openNetworkNewModel.ponProcessClassification},#{openNetworkNewModel.ponOrderTime},#{openNetworkNewModel.ponState},#{openNetworkNewModel.ponEndTime},#{openNetworkNewModel.ponStateNow},#{openNetworkNewModel.ponStateRejected},#{openNetworkNewModel.prospectExplorationTime},  #{openNetworkNewModel.prospectContractTime},  #{openNetworkNewModel.prospectConstructionTime},  #{openNetworkNewModel.ponOpenTime},  #{openNetworkNewModel.prospectProcessClassification},  #{openNetworkNewModel.prospectOrderTime},  #{openNetworkNewModel.prospectSate},  #{openNetworkNewModel.prospectEndTime},  #{openNetworkNewModel.prospectStatwNow},  #{openNetworkNewModel.prospectStateReject})")
    void save(@Param("openNetworkNewModel") OpenNetworkNewModel openNetworkNewModel);
}
