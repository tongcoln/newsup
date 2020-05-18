package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:22 上午
 * @description： 网络线条的默认课题内容
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStoreTopicNetworkDomeModel implements Serializable {
    private static final long serialVersionUID = -8576268906710439618L;
    private Integer serviceStoreTopicNetworkId;//service_store_topic_network_id
    private String serviceStoreTopicNetworkName;//service_store_topic_network_name

}
