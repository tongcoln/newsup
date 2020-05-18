package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：tt
 * @date ：Created in 2020/4/30 5:16 下午
 * @description：管理者执行表图片管理模型
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceManageImgModel {
    private Integer serviceManageNetworkImgId; //service_manage_network_img_id
    private String serviceManageNetworkImgUserid; //service_manage_network_img_userid
    private String serviceManageNetworkImgTableName; //service_manage_network_img_table_name
    private Integer serviceManageNetworkImgTableId; //service_manage_network_img_table_id
    private Integer serviceManageNetworkImgIndex; //service_manage_network_img_index
    private String serviceManageNetworkImgPath; //service_manage_network_img_path
}
