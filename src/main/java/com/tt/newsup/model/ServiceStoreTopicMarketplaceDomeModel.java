package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:27 上午
 * @description：市场课题的默认题目
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStoreTopicMarketplaceDomeModel implements Serializable {
    private static final long serialVersionUID = -8492299010142204038L;
    private Integer serviceStoreTopicMarketplaceId;//service_store_topic_marketplace_id
    private String serviceStoreTopicMarketplaceName;//service_store_topic_marketplace_name
}
