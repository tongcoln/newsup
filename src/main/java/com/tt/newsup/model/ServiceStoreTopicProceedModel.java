package com.tt.newsup.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author ：tt
 * @date ：Created in 2020/4/27 10:35 上午
 * @description：
 * @modified By：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceStoreTopicProceedModel implements Serializable {
    private static final long serialVersionUID = -2441720209839376899L;
    private  Integer serviceStoreMytopicId;//service_store_mytopic_id
    private String serviceStoreMytopicContext;// service_store_mytopic_context
    private String serviceStoreMytopicUser;//service_store_mytopic_user
    private Integer serviceStoreMytopicType;//service_store_mytopic_type
}
