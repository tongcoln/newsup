package com.tt.newsup.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OpenNetworkModel {
    private Integer openNetworkId;//open_network_id
    private String openNetworkCity;//open_network_city
    private String openNetworkArea;//open_network_area
    private String openNetworkOrderId;//open_network_order_id
    private String openNetworkIdentifying;//open_network_identifying
    private String openNetworkCrmOrder;//open_network_crm_order
    private String openNetworkTitle;//open_network_title
    private String openNetworkTitle1;//open_network_title1
    private String openNetworkProcessClassification;//open_network_process_classification
    private String openNetworkShuttleType;//open_network_shuttle_type
    private String openNetworkOrderTime;        //open_network_order_time
    private String openNetworkState;//open_network_state
    private String openNetworkEndTime;        //open_network_end_time
    private String openNetworkStateNow;//open_network_state_now
    private String openNetworkStateRejected;        //open_network_state_rejected
    private String openNetworkIsproject;//open_network_isproject
    private String openNetworkProjectId;        //open_network_project_id
    private String openNetworkProjectName;//open_network_project_name
    private String openNetworkExplorationTime;//open_network_exploration_time
    private String openNetworkContractTime;//open_network_contract_time
    private String openNetworkConstructionTime;//open_network_construction_time
    private String openNetworkOpenTime;//open_network_open_time
}
