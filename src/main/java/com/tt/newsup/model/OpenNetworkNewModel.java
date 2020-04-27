package com.tt.newsup.model;

/**
 * 设计到网开数据的处理，最终生成一张中间过程表
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OpenNetworkNewModel {
    private  Integer  openNetworkId;// open_network_id;
    private  String openNetworkCity;
    private  String openNetworkArea;
    private  String ponOrderId;
    private  String prospectOrderId;
    private  String openNetworkIdentifying;
    private  String openNetworkTitle;
    private  String ponProcessClassification;
    private  String ponOrderTime;
    private  String ponState;
    private  String ponEndTime;
    private  String ponStateNow;
    private  String ponStateRejected;
    private  String prospectExplorationTime;
    private  String prospectContractTime;
    private  String prospectConstructionTime;
    private  String ponOpenTime;
    private  String prospectProcessClassification;
    private  String prospectOrderTime;
    private  String prospectSate;
    private  String prospectEndTime;
    private  String prospectStatwNow;
    private  String prospectStateReject;
}
